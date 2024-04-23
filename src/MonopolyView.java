import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyView extends JFrame {
    JButton start_btn, exit_btn;
    JPanel startPanel, playerNumberPanel, boardPanel, mainPanel, jLeft, infoPanel, jTop, jBottom,
            player1_infoPanel, player2_infoPanel, player3_infoPanel, player4_infoPanel;
    JLabel playerNumberLabel, diceLabel = new JLabel("0"),
            player1_balance, player1_land, player1_jail,
            player2_balance, player2_land, player2_jail,
            player3_balance, player3_land, player3_jail,
            player4_balance, player4_land, player4_jail;
    JTextField playerNumber;
    private MonopolyController control;

    public void setController(MonopolyController cntl) {
        this.control = cntl;
    }

    public MonopolyView(){
        super("HSUHK COM3101 Project");
        setLayout(new BorderLayout());
        setBounds(850, 400, 350, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main Panel Setup
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        //Start Panel
        startPanel = new JPanel(new FlowLayout());
        startPanel.setBorder(new TitledBorder("Monopoly"));
        startPanel.setSize( 300, 300 );

        start_btn = new JButton("Start");
        start_btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                control.modelGameStart(Integer.parseInt(playerNumber.getText()));
            }
        });

        startPanel.add(start_btn);

        exit_btn = new JButton("Exit");
        exit_btn.addActionListener(e -> System.exit(0));
        startPanel.add(exit_btn);

        mainPanel.add(startPanel, BorderLayout.NORTH);

        //Player Number Panel
        playerNumberPanel = new JPanel(new FlowLayout());
        playerNumberPanel.setBorder(new TitledBorder("Player Number"));

        playerNumberLabel = new JLabel("Player Number (2-4): ");
        playerNumber = new JTextField(3);
        playerNumberPanel.add(playerNumberLabel);
        playerNumberPanel.add(playerNumber);

        mainPanel.add(playerNumberPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public void game_start(int playernum){
        this.setBounds(100, 50, 1100, 900);
        mainPanel.remove(startPanel);
        mainPanel.remove(playerNumberPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        //Monopoly Board Panel
        boardPanel = new JPanel(new GridLayout(9, 9,5,5));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                JPanel cell = new JPanel(new BorderLayout());
                cell.setSize(70, 70);

                if (row == 0 || row == 8 || col == 0 || col == 8) {
                    if (row == 0 && col == 0) {
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        cell.add(color, BorderLayout.NORTH);
                        JLabel text = new JLabel("Start");
                        text.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.add(text);
                    } else if (row == 0 && col == 1 || row == 0 && col == 2 || row == 0 && col == 3) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(0, 171, 78));
                        cell.add(color, BorderLayout.NORTH);
                        if (col == 1) {
                            JLabel text = new JLabel("<html>Kwun<br>Tong</html>");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 2) {
                            JLabel text = new JLabel("<html>Choi<br>Hung</html>");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 3) {
                            JLabel text = new JLabel("Lok Fu");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        }
                    } else if (row == 0 && col == 4) {
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        cell.add(color, BorderLayout.NORTH);
                        JLabel text = new JLabel("Chance");
                        text.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.add(text);
                    } else if (row == 0 && col == 5 || row == 0 && col == 6 || row == 0 && col == 7) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(49, 29, 62));
                        cell.add(color, BorderLayout.NORTH);
                        if (col == 5) {
                            JLabel text = new JLabel("<html>Tsueng<br>Kwan O</html>");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 6) {
                            JLabel text = new JLabel("Hang Hau");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 7) {
                            JLabel text = new JLabel("Po Lam");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        }
                    } else if (row == 0 && col == 8) { //top row last col
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        cell.add(color, BorderLayout.NORTH);
                        JLabel text = new JLabel("<html>Get<br>$2000</html>");
                        text.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.add(text);
                    }
                    cell.setBackground(new Color(169, 207, 255));


                    boardPanel.add(cell);
                } else if (row == 3 && col == 4){
                    JButton roll_btn = new JButton("Roll Dice");
                    roll_btn.addActionListener(e -> control.modelRollDice());
                    cell.add(roll_btn);
                    boardPanel.add(cell);
                } else if (row == 4 && col == 4){
                    boardPanel.add(cell);
                    diceLabel.setText("0");
                    cell.add(diceLabel,BorderLayout.CENTER);
                    cell.setBorder(BorderFactory.createTitledBorder("Dice"));
                } else if (row == 5 && col == 4){ //middle grid - back to home page
                    JButton back_btn = new JButton("Back");
                    back_btn.addActionListener(e -> control.modelMainMenu());
                    cell.add(back_btn);
                    boardPanel.add(cell);
                } else {
                    boardPanel.add(cell);
                }
            }
        }

        mainPanel.add(boardPanel);
        mainPanel.revalidate();
        mainPanel.repaint();

        mainPanel.add(Box.createHorizontalStrut(50)); // Adjust the size according to your needs

        infoPanel = new JPanel();
        mainPanel.add(infoPanel);
        infoPanel.setPreferredSize(new Dimension(500, 420));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        //Player Info
        player1_infoPanel = new JPanel();
        player1_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 column
        player1_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
        player1_infoPanel.setPreferredSize(new Dimension(500, 120));

        //Player 1
        player1_balance = new JLabel("Balance:　");
        player1_balance.setBorder(new EmptyBorder(0, 5, 0, 5));
        player1_land = new JLabel("Land Own: ");
        player1_land.setBorder(new EmptyBorder(0, 5, 0, 5));
        player1_jail = new JLabel("Jail Day: ");
        player1_jail.setBorder(new EmptyBorder(0, 5, 0, 5));
        player1_infoPanel.add(player1_balance);
        player1_infoPanel.add(player1_land);
        player1_infoPanel.add(player1_jail);

        //Player 2
        player2_infoPanel = new JPanel();
        player2_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 column
        player2_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 2"));
        player2_infoPanel.setPreferredSize(new Dimension(500, 120));

        player2_balance = new JLabel("Balance:　");
        player2_balance.setBorder(new EmptyBorder(0, 5, 0, 5));
        player2_land = new JLabel("Land Own: ");
        player2_land.setBorder(new EmptyBorder(0, 5, 0, 5));
        player2_jail = new JLabel("Jail Day: ");
        player2_jail.setBorder(new EmptyBorder(0, 5, 0, 5));

        player2_infoPanel.add(player2_balance);
        player2_infoPanel.add(player2_land);
        player2_infoPanel.add(player2_jail);

        //Player 3
        player3_infoPanel = new JPanel();
        player3_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 column
        player3_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 3"));
        player3_infoPanel.setPreferredSize(new Dimension(500, 120));

        player3_balance = new JLabel("Balance:　");
        player3_balance.setBorder(new EmptyBorder(0, 5, 0, 5));
        player3_land = new JLabel("Land Own: ");
        player3_land.setBorder(new EmptyBorder(0, 5, 0, 5));
        player3_jail = new JLabel("Jail Day: ");
        player3_jail.setBorder(new EmptyBorder(0, 5, 0, 5));

        player3_infoPanel.add(player3_balance);
        player3_infoPanel.add(player3_land);
        player3_infoPanel.add(player3_jail);

        //Player 4
        player4_infoPanel = new JPanel();
        player4_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 column
        player4_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 4"));
        player4_infoPanel.setPreferredSize(new Dimension(500, 120));

        player4_balance = new JLabel("Balance:　");
        player4_balance.setBorder(new EmptyBorder(0, 5, 0, 5));
        player4_land = new JLabel("Land Own: ");
        player4_land.setBorder(new EmptyBorder(0, 5, 0, 5));
        player4_jail = new JLabel("Jail Day: ");
        player4_jail.setBorder(new EmptyBorder(0, 5, 0, 5));

        player4_infoPanel.add(player4_balance);
        player4_infoPanel.add(player4_land);
        player4_infoPanel.add(player4_jail);


        infoPanel.add(player1_infoPanel);
        infoPanel.add(player2_infoPanel);
        infoPanel.add(player3_infoPanel);
        infoPanel.add(player4_infoPanel);

        //Dice Label
        diceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        diceLabel.setVerticalAlignment(SwingConstants.CENTER);

        control.modelUpdatePosition();

    }

    public void mainMenu(){
        this.setBounds(200, 50, 350, 150);
        mainPanel.remove(boardPanel);
        mainPanel.remove(infoPanel);
        mainPanel.add(startPanel, BorderLayout.NORTH);
        mainPanel.add(playerNumberPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    public void updatePosition(int playerNum, int[] nextPlayerPosition, int[] boardPosition){

        for (int row = 0; row < 9; row ++){
            for(int col = 0; col < 9; col++){
                if (row == 0 || row == 8 || col == 0 || col == 8) {
                    JPanel grid = (JPanel) boardPanel.getComponent(coordToindex(row,col));
                    JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");
                    if (southPanel == null) {
                        southPanel = new JPanel(new FlowLayout());
                        grid.add(southPanel, BorderLayout.SOUTH);
                        grid.putClientProperty("SouthPanel", southPanel);
                    }
                    southPanel.removeAll();
                    southPanel.revalidate();
                    southPanel.repaint();
                    southPanel.setBackground(new Color(169, 207, 255));
                }
            }
        }
        for (int i = 0; i < playerNum; i++){

            if (i == 0){
                System.out.println("Next Position(Player 1): " + boardPosition[nextPlayerPosition[i]]);

                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");
                southPanel.setBackground(new Color(169, 207, 255));

                ImageIcon picture = createImageIcon("images/player1.png");
                JLabel player1 = new JLabel(picture);
                southPanel.add(player1,BorderLayout.SOUTH);

            } else if ( i == 1){
                System.out.println("Next Position(Player 2): " + boardPosition[nextPlayerPosition[i]]);
                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");

                ImageIcon picture = createImageIcon("images/player2.png");
                JLabel player2 = new JLabel(picture);
                southPanel.add(player2,BorderLayout.SOUTH);

            } else if ( i == 2){
                System.out.println("Next Position(Player 3): " + boardPosition[nextPlayerPosition[i]]);
                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");

                ImageIcon picture = createImageIcon("images/player3.png");
                JLabel player3 = new JLabel(picture);
                southPanel.add(player3,BorderLayout.SOUTH);

            } else if ( i == 3){
                System.out.println("Next Position(Player 4): " + boardPosition[nextPlayerPosition[i]]);
                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");

                ImageIcon picture = createImageIcon("images/player4.png");
                JLabel player4 = new JLabel(picture);
                southPanel.add(player4,BorderLayout.SOUTH);

            }
        }
    }

    public void updateDice(int dice) {
        JPanel grid = (JPanel) boardPanel.getComponent(60);
        diceLabel.setText(Integer.toString(dice));
        grid.add(diceLabel,BorderLayout.CENTER); // Add without specifying layout constraint
        grid.revalidate(); // Ensure layout updates correctly
    }

    public void showBuyOption(int price){
        // Show a JOptionPane with message, title, and message type
        int result = JOptionPane.showConfirmDialog(null, "This is an empty land. It cost: " + price + " \nDo you want to buy it?",
                "Do you want to buy this land?", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "You just bought a land for " + price);
            control.buyLand();
        } else {
            JOptionPane.showMessageDialog(null, "You ignore the chance");
        }
    }

    public void updateBalance(int[] balance){
        player1_balance.setText("Balance: " + balance[0]);
        player2_balance.setText("Balance: " + balance[1]);

    }

    public void showPayNotify(int price, int ownership){
        JOptionPane.showMessageDialog(null, "You have paid " + price + " to player " + ownership);
    }

    public void showGiftNotify(){
        JOptionPane.showMessageDialog(null, "You have get 2000 as a gift!");
    }


    //Image insert
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT));
        } else {
            System.err.println("Couldn't find the file: " + path);
            return null;
        }
    }
    private int coordToindex(int row, int col) {     // convert 2D array to 1D array index
        return (col * 9) + row;
    }
}
