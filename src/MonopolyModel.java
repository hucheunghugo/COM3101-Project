public class MonopolyModel {
    private int playerNumber = 0;
    MonopolyController control;

    public void setController(MonopolyController c) {
        this.control = c;

    }

    public void gameStart(int playernum) {
        control.gameStart();
        this.playerNumber = playernum;
    }

    public void mainMenu(){
        control.mainMenu();
        this.playerNumber = 0;
    }
}
