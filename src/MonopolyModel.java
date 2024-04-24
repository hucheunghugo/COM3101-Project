import java.io.*;
import java.util.Random;

public class MonopolyModel {
    private int playerNumber = 0, currentPlayer = 0;
    private int[] currentPlayerPosition, nextPlayerPosition, playerBalance, boardPosition, landPrice = new int[32],
            landOwnership = new int [32];
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
        for (int i = 0; i < currentPlayerPosition.length; i++) {
            currentPlayerPosition[i] = 0;
            nextPlayerPosition[i] = 0;
            playerBalance[i] = 10000;
        }

        //Board Position Adjustment
        boardPosition = new int[32];
        for(int i = 0; i < 9; i++){
            boardPosition[i] = i;
        }
        for (int i = 9; i < 18; i++){
            boardPosition[i] = 9 * (i - 8) - 1;
        }
        for (int i = 18; i < 26; i++){
            boardPosition[i] = 80 - (i - 17);
        }
        for (int i = 26; i < 32; i++){
            boardPosition[i] = (32 - i) * 9;
        }

        for (int i = 0; i<boardPosition.length; i++){
            System.out.println(boardPosition[i]);
        }

        control.gameStart(playerNumber);
        control.updateBalance(playerBalance);
    }

    public void updatePosition(){
        control.updatePosition(playerNumber, nextPlayerPosition, boardPosition);
    }

    public void rollDice(){
        //roll the dice
        Random rand = new Random();
        int random = rand.nextInt(10);
        if (random == 0) {
            random = random + 1;
        }

        switch (random) {
            case 1:
                control.showDice1("images/dice01.png");
                control.showDice2("images/dice00.png");
                break;

            case 2:
                control.showDice1("images/dice02.png");
                control.showDice2("images/dice00.png");
                break;
            case 3:
                control.showDice1("images/dice03.png");
                control.showDice2("images/dice00.png");
                break;
            case 4:
                control.showDice1("images/dice04.png");
                control.showDice2("images/dice00.png");
                break;
            case 5:
                control.showDice1("images/dice05.png");
                control.showDice2("images/dice00.png");
                break;
            case 6:
                control.showDice1("images/dice06.png");
                control.showDice2("images/dice00.png");
                break;
            case 7:
                control.showDice1("images/dice04.png");
                control.showDice2("images/dice03.png");
                break;
            case 8:
                control.showDice1("images/dice04.png");
                control.showDice2("images/dice04.png");
                break;
            case 9:
                control.showDice1("images/dice05.png");
                control.showDice2("images/dice04.png");
                break;
            case 10:
                control.showDice1("images/dice05.png");
                control.showDice2("images/dice05.png");
                break;
            default:
                control.showDice1("images/dice00.png");
                control.showDice2("images/dice00.png");
        }

        nextPlayerPosition[currentPlayer] = currentPlayerPosition[currentPlayer] ;
        if (nextPlayerPosition[currentPlayer] > 31){
            nextPlayerPosition[currentPlayer] -= 32;
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

        if (currentPlayerPosition[currentPlayer] == 8 || currentPlayerPosition[currentPlayer] == 24){
            balanceUpdate(1, currentPlayer, 2000);
            control.showGiftNotify();
        } else {
            if (ownership == -1) {
                control.showBuyOption(price);
            } else if (ownership == currentPlayer) {

            } else {
                control.showPayNotify(price, ownership);
                balanceUpdate(2, currentPlayer, price);
            }
            System.out.println(landOwnership[currentPlayerPosition[currentPlayer]]);
        }
    }

    public void buyLand(){
        landOwnership[currentPlayerPosition[currentPlayer]] = currentPlayer;
        balanceUpdate(2,currentPlayer, landPrice[currentPlayerPosition[currentPlayer]]);
        control.updateOwner(currentPlayer, currentPlayerPosition[currentPlayer]);
    }

    public void balanceUpdate(int type, int player, int amount){
        if (type == 1){
            playerBalance[player] += amount;
        } else if (type == 2){
            playerBalance[player] -= amount;
        }
        control.updateBalance(playerBalance);
    }

    public void mainMenu(){
        //reset all stats
        control.mainMenu();
        currentPlayer = playerNumber = 0;

    }
}
