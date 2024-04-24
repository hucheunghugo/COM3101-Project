import java.io.*;
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
        int dice = (int)(Math.random() * 6) + 1;
        control.updateDice(dice);

        nextPlayerPosition[currentPlayer] = currentPlayerPosition[currentPlayer] + dice;
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
        int pos = currentPlayerPosition[currentPlayer];

        if (pos == 8 || pos == 24){
            balanceUpdate(1, currentPlayer, 2000);
            control.showGiftNotify();
        } else if (pos == 4 || pos == 12 || pos == 20 || pos == 28) {

        } else {
            if (ownership == -1) {
                control.showBuyOption(price);
            } else if (ownership == currentPlayer) {

            } else {
                control.showPayNotify(price, ownership);
                balanceUpdate(2, currentPlayer, price);
            }
            System.out.println(landOwnership[pos]);
        }
    }

    public void buyLand(){
        landOwnership[currentPlayerPosition[currentPlayer]] = currentPlayer;
        balanceUpdate(2,currentPlayer, landPrice[currentPlayerPosition[currentPlayer]]);
        control.updateOwner(currentPlayer, currentPlayerPosition[currentPlayer], boardPosition);
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
