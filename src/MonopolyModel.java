import java.io.*;
import java.util.Random;

public class MonopolyModel {
    private int playerNumber = 0, currentPlayer = 0;
    private int[] currentPlayerPosition, nextPlayerPosition, playerBalance, boardPosition, landPrice = new int[32],
            landOwnership = new int[32], jailDate, noLandOwn = new int[4];
    MonopolyController control;

    public void setController(MonopolyController c) {
        this.control = c;

    }

    public MonopolyModel(){
        this.connectData();
    }

    public void connectData(){
        String fileName ="priceData.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                landPrice[Integer.parseInt(data[0])] = Integer.parseInt(data[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < landPrice.length; i++){
            System.out.println(landPrice[i]);
        }
        for (int i = 0; i <landOwnership.length; i++){
            landOwnership[i] = -1;
        }
    }
    public void gameStart(int playernum) {
        //Set the amount of player
        playerNumber = playernum;

        currentPlayerPosition = new int[playerNumber];
        nextPlayerPosition = new int[playerNumber];
        playerBalance = new int[playerNumber];
        jailDate = new int[playerNumber];
        for (int i = 0; i < currentPlayerPosition.length; i++) {
            currentPlayerPosition[i] = 0;
            nextPlayerPosition[i] = 0;
            playerBalance[i] = 10000;
            jailDate[i] = 0;
        }

        //Board Position Adjustment
        boardPosition = new int[32];
        for(int i = 0; i < 9; i++){
            boardPosition[i] = i;
        }
        for (int i = 9; i < 17; i++){
            boardPosition[i] = 9 * (i - 7) - 1;
        }
        for (int i = 17; i < 25; i++){
            boardPosition[i] = 80 - (i - 16);
        }
        for (int i = 25; i < 32; i++){
            boardPosition[i] = (32 - i) * 9;
        }

        for (int i = 0; i<boardPosition.length; i++){
            System.out.println(boardPosition[i]);
        }

        control.gameStart(playerNumber);
        control.updateBalance(playerBalance);
        control.updateLandOwn(noLandOwn);
        control.updateJailDate(jailDate);
    }

    public void updatePosition(){
        control.updatePosition(playerNumber, nextPlayerPosition, boardPosition);
    }

    public void rollDice(){
        if(jailDate[currentPlayer] > 0){
            jailDate[currentPlayer]--;
            control.showJailNotify(jailDate[currentPlayer]+1);
        } else {
            //roll the dice
            int dice = (int) (Math.random() * 10) + 1;
            control.updateDice(dice);


            nextPlayerPosition[currentPlayer] = currentPlayerPosition[currentPlayer] + dice;
            if (nextPlayerPosition[currentPlayer] > 31) {
                nextPlayerPosition[currentPlayer] -= 32;
                balanceUpdate(1, currentPlayer, 2000);
                control.showGiftNotify();
            }
        }
        control.updatePosition(playerNumber, nextPlayerPosition, boardPosition);

        System.out.println("Roll Dice " + currentPlayerPosition[currentPlayer] + " | " +  nextPlayerPosition[currentPlayer]);
        currentPlayerPosition[currentPlayer] = nextPlayerPosition[currentPlayer];

        landCheck();

        if (currentPlayer == playerNumber - 1){
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public void landCheck(){
        int ownership = landOwnership[currentPlayerPosition[currentPlayer]];
        int price = landPrice[currentPlayerPosition[currentPlayer]];
        int pos = currentPlayerPosition[currentPlayer];

        if (pos == 8 || pos == 24){
            balanceUpdate(1, currentPlayer, 2000);
            control.showGiftNotify();
        } else if (pos == 4 || pos == 12 || pos == 20 || pos == 28) {
            chance();
        } else if (pos != 16 && pos != 0 ) {
            if (ownership == -1) {
                control.showBuyOption(price);
            } else if (ownership == currentPlayer) {

            } else {
                System.out.println("Owner: " + ownership);
                control.showPayNotify(price*0.1, ownership+1);
                balanceUpdate(2, currentPlayer, price*0.1);
                balanceUpdate(1, ownership, price*0.1);
            }
        }

        for (int i = 0; i < landOwnership.length; i++){
            if (landOwnership[i] == 0) {
                noLandOwn[0]++;
            } else if (landOwnership[i] == 1) {
                noLandOwn[1]++;
            } else if (landOwnership[i] == 2) {
                noLandOwn[2]++;
            } else if (landOwnership[i] == 3) {
                noLandOwn[3]++;
            }
        }
        control.updateLandOwn(noLandOwn);
    }

    public void buyLand(){
        landOwnership[currentPlayerPosition[currentPlayer]] = currentPlayer;
        balanceUpdate(2,currentPlayer, landPrice[currentPlayerPosition[currentPlayer]]);
        control.updateOwner(currentPlayer, currentPlayerPosition[currentPlayer], boardPosition);
    }

    public void balanceUpdate(int type, int player, double amount){
        if (type == 1){
            playerBalance[player] += amount;
        } else if (type == 2){
            playerBalance[player] -= amount;
        }
        control.updateBalance(playerBalance);
    }

    public void chance(){
        //generate random int
        Random rand = new Random();
        int random = rand.nextInt(3);
        if (random == 0) {
            control.showChanceNotify("You just win a $1000 lottery");
            //add money
            balanceUpdate(1, currentPlayer, 1000);
        } else if (random == 1) {
            control.showChanceNotify("You are sick \nYou have to pay your medical fee $1500");
            //decrease money
            balanceUpdate(2, currentPlayer, 1500);

        } else {
            //jail
            control.showChanceNotify("You have committed crime \n Go to jail for 3 days");
            currentPlayerPosition[currentPlayer] = 16;
            nextPlayerPosition[currentPlayer] = 16;
            updatePosition();
            jailDate[currentPlayer] = 3;
            control.updateJailDate(jailDate);
        }
        System.out.println("Chance");
    }
}
