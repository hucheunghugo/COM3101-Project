public class MonopolyController {
    MonopolyModel model;
    MonopolyView view;
    //
    // Methods for setting up the model and the view
    //	
    public void setModel(MonopolyModel m) {
        this.model = m;
    }

    public void setView(MonopolyView v) {
        this.view = v;
    }

    public void modelGameStart(int playernum){
        model.gameStart(playernum);
    }
    public void gameStart(){
        view.game_start();
    }

    public void modelMainMenu(){
        model.mainMenu();
    }
    public void mainMenu(){
        view.mainMenu();
    }
}
