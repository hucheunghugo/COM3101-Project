public class MonopolyModel {
    private int playerNumber = 0, currentPlayer = 0;
    private int[] currentPlayerPosition, nextPlayerPosition, boardPosition;
    MonopolyController control;

    public void setController(MonopolyController c) {
        this.control = c;

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
        boardPosition = new int[39];
        for(int i = 0; i < 11; i++){
            boardPosition[i] = i;
        }
        for (int i = 11; i < 20; i++){
            boardPosition[i] = 11 * (i - 9) - 1;
        }
        for (int i = 20; i < 31; i++){
            boardPosition[i] = 120 - (i - 19);
        }
        for (int i = 31; i < 39; i++){
            boardPosition[i] = (39 - i) * 11;
        }

        for (int i = 0; i<boardPosition.length; i++){
            System.out.println(boardPosition[i]);
        }

        control.gameStart(playerNumber);
    }

    public void updatePosition(){
        control.updatePosition(playerNumber, boardPosition);
    }

    public void rollDice(){
        //roll the dice
        int dice = (int)(Math.random() * 6) + 1;
        control.updateDice(dice);

        nextPlayerPosition[currentPlayer] = currentPlayerPosition[currentPlayer] + dice;
        if (nextPlayerPosition[currentPlayer] > 38){
            nextPlayerPosition[currentPlayer] -= 39;
        }
        control.updatePosition(playerNumber, boardPosition);

        System.out.println("Roll Dice " + currentPlayerPosition[currentPlayer] + " | " +  nextPlayerPosition[currentPlayer]);
        currentPlayerPosition[currentPlayer] = nextPlayerPosition[currentPlayer];

        if (currentPlayer == playerNumber - 1){
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public void mainMenu(){
        //reset all stats
        control.mainMenu();
        currentPlayer = playerNumber = 0;

    }
}
