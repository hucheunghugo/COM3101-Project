public class MonopolyController {
    MonopolyModel model;
    MonopolyView view;

    public void setModel(MonopolyModel m) {
        this.model = m;
    }

    public void setView(MonopolyView v) {
        this.view = v;
    }

    public void modelGameStart(int playernum) {
        model.gameStart(playernum);
    }

    public void gameStart(int playernum, int[] landPrice, String[] landName) {
        view.game_start(playernum, landPrice, landName);
    }

    public void modelUpdatePosition() {
        model.updatePosition();
    }

    public void updatePosition(int player, int[] nextPlayerPosition, int[] boardPosition) {
        view.updatePosition(player, nextPlayerPosition, boardPosition);
    }

    public void modelRollDice() {
        model.rollDice();
    }

    public void updateDice(int player) {
        view.updateDice(player);
    }

    public void updateRound(int player) {
        view.updateRound(player);
    }

    public void showBuyOption(int price) {
        view.showBuyOption(price);
    }

    public void showPayNotify(double price, int ownership) {
        view.showPayNotify(price, ownership);
    }

    public void buyLand() {
        model.buyLand();
    }

    public void showGiftNotify() {
        view.showGiftNotify();
    }

    public void showChanceNotify(String msg) {
        view.showChanceNotify(msg);
    }

    public void showJailNotify(int days) {
        view.showJailNotify(days);
    }

    public void showBankruptNotify(int player, int[] landOwnership, int[] boardPosition) {
        view.showBankruptNotify(player, landOwnership, boardPosition);
    }

    public void showGameOverNotify(int winner) {
        view.showGameOverNotify(winner);
    }

    public void updateOwner(int[] boardPosition, int[] landOwner) {
        view.updateOwner(boardPosition, landOwner);
    }

    public void updateBalance(int[] balance) {
        view.updateBalance(balance);
    }

    public void updateLandOwn(int[] noLandOwn) {
        view.updateLandOwn(noLandOwn);
    }

    public void updateJailDate(int[] jailDate) {
        view.updateJailDate(jailDate);
    }

    public void accessData() {
        model.getData();
    }

    public void getData(int playerNumber, int[] currentPlayerPosition, int[] playerBalance, int[] jailDate, int[] landOwnership) {
        view.gameEditorFrame(playerNumber, currentPlayerPosition, playerBalance, jailDate, landOwnership);
    }

    public void editCurrentPlayer(int player) {
        model.editCurrentPlayer(player);
    }

    public void editPosition(int pos, int player) {
        model.editPosition(pos, player);
    }

    public void editBalance(int balance, int player) {
        model.editBalance(balance, player);
    }

    public void editJail(int days, int player) {
        model.editJail(days, player);
    }

    public void editOwner(int pos, int player) {
        model.editOwner(pos, player);
    }

    public void showTradeOption(int player, int owner){
        view.showTradeOption(player, owner);
    }
    public void tradeLand(int price, int player, int owner){
        model.tradeLand(price, player, owner);
    }
}
