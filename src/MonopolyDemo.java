public class MonopolyDemo {
    public static void main(String[] args) {
        MonopolyModel model = new MonopolyModel();
        MonopolyView view = new MonopolyView();
        MonopolyController controller = new MonopolyController();

        model.setController(controller);
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        }
        );
    }
}