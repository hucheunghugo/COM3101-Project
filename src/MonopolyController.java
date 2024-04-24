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
    public void updatePosition(int player, int[] nextPlayerPosition, int[] boardPosition){
        view.updatePosition(player,  nextPlayerPosition, boardPosition);
    }

    public void modelRollDice(){
        model.rollDice();
    }
    public void updateDice(int dice){
        view.updateDice(dice);
    }

    public void showBuyOption(int price){
        view.showBuyOption(price);
    }

    public void showPayNotify(double price, int ownership){
        view.showPayNotify(price, ownership);
    }
    public void buyLand(){
        model.buyLand();
    }
    public void showGiftNotify(){
        view.showGiftNotify();
    }

    public void showChanceNotify(String msg){
        view.showChanceNotify(msg);
    }

    public void showJailNotify(int days){
        view.showJailNotify(days);
    }

    public void updateOwner(int player, int pos, int[] boardPosition){
        view.updateOwner(player, pos, boardPosition);
    }
    public void updateBalance(int[] balance){
        view.updateBalance(balance);
    }
}
