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
    public void gameStart(int playernum){
        view.game_start(playernum);
    }

    public void modelUpdatePosition(){
        model.updatePosition();
    }
    public void updatePosition(int player, int[] nextPlayerPosition){
        view.updatePosition(player,  nextPlayerPosition);
    }

    public void modelRollDice(){
        model.rollDice();
    }
    public void updateDice(int dice){
        view.updateDice(dice);
    }


    public void modelMainMenu(){
        model.mainMenu();
    }
    public void mainMenu(){
        view.mainMenu();
    }
}
