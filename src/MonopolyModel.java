import java.io.*;
public class MonopolyModel {
    private int playerNumber = 0, currentPlayer = 0;
    private int[] currentPlayerPosition, nextPlayerPosition, boardPosition, landPrice = new int[32],
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
            landOwnership[i] = 0;
        }
    }
    public void gameStart(int playernum) {
        //Set the amount of player
        playerNumber = playernum;

        currentPlayerPosition = new int[playerNumber];
        nextPlayerPosition = new int[playerNumber];
        for (int i = 0; i < currentPlayerPosition.length; i++) {
            currentPlayerPosition[i] = 0;
            nextPlayerPosition[i] = 0;
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
        if (ownership == 0){
            control.showBuyOption(landPrice[currentPlayerPosition[currentPlayer]]);
        }
    }

    public void mainMenu(){
        //reset all stats
        control.mainMenu();
        currentPlayer = playerNumber = 0;

    }
}
