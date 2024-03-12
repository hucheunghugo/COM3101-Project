public class MonopolyModel {
    private int playerNumber = 0;
    private int[] playerPosition;
    MonopolyController control;

    public void setController(MonopolyController c) {
        this.control = c;

    }

    public void gameStart(int playernum) {
        //Set the amount of player
        playerNumber = playernum;

        playerPosition = new int[playerNumber];
        for (int i = 0; i < playerPosition.length; i++) {
            playerPosition[i] = 0;
        }

        control.gameStart(playerNumber);
    }

    public void positionUpdate(){
        for (int i = 0; i < playerPosition.length; i++) {
            control.positionUpdate(i, playerPosition[i]);
        }
    }

    public void rollDice(){
        //roll the dice
        int dice = (int)(Math.random() * 6) + 1;
        control.updateDice(dice);
    }

    public void mainMenu(){
        //reset all stats
        control.mainMenu();
        playerNumber = 0;
    }
}
