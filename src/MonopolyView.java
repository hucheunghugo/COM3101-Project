import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MonopolyView extends JFrame {
    JButton start_btn, exit_btn;
    JPanel startPanel, playerNumberPanel, boardPanel, mainPanel, jLeft, infoPanel, jTop, jBottom,
            player1_infoPanel, player2_infoPanel, player3_infoPanel, player4_infoPanel;
    JLabel playerNumberLabel,
            player1_balance, player1_land, player1_jail,
            player2_balance, player2_land, player2_jail,
            player3_balance, player3_land, player3_jail,
            player4_balance, player4_land, player4_jail;
    JTextField playerNumber;
    private int playerNo;
    private MonopolyController control;

    public void setController(MonopolyController cntl) {
        this.control = cntl;
    }

    public MonopolyView(){
        super("HSUHK COM3101 Project");
        setLayout(new BorderLayout());
        setBounds(850, 400, 400, 150);
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
                playerNo = Integer.parseInt(playerNumber.getText());
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
        this.setBounds(300, 100, 1200, 900);
        mainPanel.remove(startPanel);
        mainPanel.remove(playerNumberPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        //Monopoly Board Panel
        boardPanel = new JPanel(new GridLayout(9, 9,5,5));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                JPanel cell = new JPanel();
                cell.setLayout(new BoxLayout(cell, BoxLayout.PAGE_AXIS)); // Use BoxLayout with vertical alignment
                cell.setSize(70, 70);

                if (row == 0 || row == 8 || col == 0 || col == 8) {
                    cell.setBorder(new BevelBorder(BevelBorder.RAISED));
                    if (row == 0 && col == 0) {
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        JLabel text = new JLabel("Start");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);
                    } else if (row == 1 && col == 8 || row == 2 && col == 8 || row == 3 && col == 8) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(0, 171, 78));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15)); 
                        if (row == 1) {
                            JLabel text = new JLabel("Kwun Tong");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        } else if (row == 2) {
                            JLabel text = new JLabel("Choi Hung");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        } else {
                            JLabel text = new JLabel("Lok Fu");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }

                    } else if(row == 0 && col == 1 || row == 0 && col == 2 || row == 0 && col == 3) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(0, 247, 255));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if(col == 1) {
                            JLabel text = new JLabel("Lok Ma Chau");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else if(col == 2) {
                            JLabel text = new JLabel("Fan Ling");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else {
                            JLabel text = new JLabel("Sha Tin");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }

                    } else if(row == 0 && col == 5 || row == 0 && col == 6 || row == 0 && col == 7){
                        JPanel color = new JPanel();;
                        color.setBackground(new Color(166, 97, 0));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if(col == 5) {
                            JLabel text = new JLabel("Tuen Mun");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else if(col == 6) {
                            JLabel text = new JLabel("To Kwa Wan");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else {
                            JLabel text = new JLabel("Ma On Shan");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }

                    } else if(row == 8 && col == 5 || row == 8 && col == 6 || row == 8 && col == 7){
                        JPanel color = new JPanel();;
                        color.setBackground(new Color(255, 149, 0));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if(col == 7) {
                            JLabel text = new JLabel("Olympic");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else if(col == 6){
                            JLabel text = new JLabel("Tsing Yi");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else{
                            JLabel text = new JLabel("Tung Chung");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }

                    } else if(col == 0 && row == 5 || col == 0 && row == 6 || col == 0 && row == 7) {
                        JPanel color = new JPanel();;
                        color.setBackground(new Color(0, 108, 198));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if(row == 5) {
                            JLabel text = new JLabel("Tai Koo");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else if(row == 6){
                            JLabel text = new JLabel("Causeway Bay");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else {
                            JLabel text = new JLabel("Sheung Wan");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }
                    } else if (row == 0 && col == 4 || row == 8 && col == 4 || row == 4 && col == 8 || row == 4 && col == 0) {
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        JLabel text = new JLabel("Chance");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);

                    } else if (row == 8 && col == 1 || row == 8 && col == 2 || row == 8 && col == 3) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(155, 52, 235));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if (col == 1) {
                            JLabel text = new JLabel("Tsueng Kwan O");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        } else if (col == 2) {
                            JLabel text = new JLabel("Hang Hau");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        } else {
                            JLabel text = new JLabel("Po Lam");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }

                    } else if(col == 0 && row == 1 || col == 0 && row == 2 || col == 0 && row == 3){
                        JPanel color = new JPanel();
                        color.setBackground(new Color(176, 250, 5));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if(row == 1){
                            JLabel text = new JLabel("South Horizons");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else if(row == 3){
                            JLabel text = new JLabel("Airport");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }else{
                            JLabel text = new JLabel("Wong Chuk Hang");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }
                    } else if(col == 8 && row == 5 || col == 8 && row == 6 || col == 8 && row == 7) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(255, 0, 0));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        if (row == 5) {
                            JLabel text = new JLabel("Tsuen Wan");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        } else if (row == 6) {
                            JLabel text = new JLabel("Mei Foo");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        } else {
                            JLabel text = new JLabel("Mong Kok");
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                        }

                    } else if (row == 0 && col == 8 || row == 8 && col == 0) { //top row last col
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        JLabel text = new JLabel("Get $2000");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);

                    } else if(row == 8 && col ==8){
                        JPanel color = new JPanel();
                        color.setBackground(Color.black);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(15));
                        JLabel text = new JLabel("Jail");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);

                    }

                    cell.setBackground(new Color(169, 207, 255));
                } else if (row == 3 && col == 4){
                    JButton roll_btn = new JButton("Roll Dice");
                    roll_btn.addActionListener(e -> control.modelRollDice());
                    cell.add(roll_btn);
                } else if (row == 4 && col == 4){
                    cell.setBorder(BorderFactory.createTitledBorder("Dice"));
                    cell.setBorder(new BevelBorder(BevelBorder.RAISED));
                }
                boardPanel.add(cell);
            }
        }

        mainPanel.add(boardPanel);
        mainPanel.revalidate();
        mainPanel.repaint();

        mainPanel.add(Box.createHorizontalStrut(50)); 

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

        control.modelUpdatePosition();

    }

    public void updatePosition(int playerNum, int[] nextPlayerPosition, int[] boardPosition){

        for (int row = 0; row < 9; row ++){
            for(int col = 0; col < 9; col++){
                if (row == 0 || row == 8 || col == 0 || col == 8) {
                    JPanel grid = (JPanel) boardPanel.getComponent(coordToindex(row,col));
                    JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");
                    if (southPanel == null) {
                        southPanel = new JPanel(new FlowLayout());
                        grid.add(southPanel);
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
                southPanel.add(player1);

            } else if ( i == 1){
                System.out.println("Next Position(Player 2): " + boardPosition[nextPlayerPosition[i]]);
                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");

                ImageIcon picture = createImageIcon("images/player2.png");
                JLabel player2 = new JLabel(picture);
                southPanel.add(player2);

            } else if ( i == 2){
                System.out.println("Next Position(Player 3): " + boardPosition[nextPlayerPosition[i]]);
                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");

                ImageIcon picture = createImageIcon("images/player3.png");
                JLabel player3 = new JLabel(picture);
                southPanel.add(player3);

            } else if ( i == 3){
                System.out.println("Next Position(Player 4): " + boardPosition[nextPlayerPosition[i]]);
                JPanel grid = (JPanel) boardPanel.getComponent(boardPosition[nextPlayerPosition[i]]);
                JPanel southPanel = (JPanel) grid.getClientProperty("SouthPanel");

                ImageIcon picture = createImageIcon("images/player4.png");
                JLabel player4 = new JLabel(picture);
                southPanel.add(player4);

            }
        }
    }

    public void updateDice(int dice) {
        JPanel grid = (JPanel) boardPanel.getComponent(40);
        grid.removeAll();
        grid.setLayout(new BoxLayout(grid,BoxLayout.X_AXIS));
        ImageIcon dicepic1 = null, dicepic2 = null;
        if (dice == 4){
            dicepic1 = createDiceIcon("images/dice4.png");
            dicepic2 = createDiceIcon("images/dice5.png");
        } else {
            dicepic1 = createDiceIcon("images/dice5.png");
            dicepic2 = createDiceIcon("images/dice4.png");
        }
        JLabel dice1Label = new JLabel(dicepic1);
        JLabel dice2Label = new JLabel(dicepic2);
        dice1Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        dice1Label.setAlignmentY(Component.CENTER_ALIGNMENT);
        dice2Label.setAlignmentX(Component.CENTER_ALIGNMENT);
        dice2Label.setAlignmentY(Component.CENTER_ALIGNMENT);
        grid.add(Box.createVerticalStrut(2));
        grid.add(dice1Label);
        grid.add(Box.createVerticalStrut(2));
        grid.add(dice2Label); // Add without specifying layout constraint
        grid.add(Box.createVerticalStrut(2));
        grid.revalidate();

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
        if (playerNo == 2){
            player1_balance.setText("Balance: " + balance[0]);
            player2_balance.setText("Balance: " + balance[1]);
            player3_balance.setText("Balance: No Player");
            player4_balance.setText("Balance: No Player");
        } else if (playerNo == 3){
            player1_balance.setText("Balance: " + balance[0]);
            player2_balance.setText("Balance: " + balance[1]);
            player3_balance.setText("Balance: " + balance[2]);
            player4_balance.setText("Balance: No Player");
        } else if (playerNo == 4){
            player1_balance.setText("Balance: " + balance[0]);
            player2_balance.setText("Balance: " + balance[1]);
            player3_balance.setText("Balance: " + balance[2]);
            player4_balance.setText("Balance: " + balance[3]);
        }


    }

    public void showPayNotify(double price, int ownership){
        JOptionPane.showMessageDialog(null, "You have paid " + price + " to player " + ownership);
    }

    public void showGiftNotify(){
        JOptionPane.showMessageDialog(null, "You have get 2000 as a gift!");
    }

    public void showChanceNotify(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

    public void showJailNotify(int days){
        JOptionPane.showMessageDialog(null, "You are still in jail for " + days + " days");
    }

    public void updateOwner(int player, int pos, int[] boardPosition) {
        JPanel cell = (JPanel) boardPanel.getComponent(boardPosition[pos]);

        // Ensure that the cell panel exists
        if (cell != null) {
            // Retrieve the color panel from the cell panel
            JPanel colorPanel = null;
            if (cell.getComponentCount() > 0) {
                colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component
            }

            // Ensure that the color panel exists
            if (colorPanel != null) {
                ImageIcon picture = null;
                if (player == 0) {
                    picture = createImageIcon("images/player1.png");
                } else if (player == 1) {
                    picture = createImageIcon("images/player2.png");
                } else if (player == 2) {
                    picture = createImageIcon("images/player3.png");
                } else if (player == 3) {
                    picture = createImageIcon("images/player4.png");
                }

                if (picture != null) {
                    System.out.println("True");
                    // Set the new background color for the color panel
                    JLabel playerLabel = new JLabel(picture);

                    // Add the player label to the grid panel
                    colorPanel.removeAll(); // Remove existing components
                    colorPanel.add(playerLabel, BorderLayout.CENTER); // Add without specifying layout constraint
                    colorPanel.revalidate();
                    colorPanel.repaint();
                } else {
                    System.out.println("Image loading failed.");
                }
            }
        }
    }
    public void updateLandOwn(int[] noLandOwn){
        if (playerNo == 2){
            player1_land.setText("Land Own: " + noLandOwn[0]);
            player2_land.setText("Land Own: " + noLandOwn[1]);
            player3_land.setText("Land Own: No Player" );
            player4_land.setText("Land Own: No Player" );
        } else if (playerNo == 3){
            player1_land.setText("Land Own: " + noLandOwn[0]);
            player2_land.setText("Land Own: " + noLandOwn[1]);
            player3_land.setText("Land Own: " + noLandOwn[2]);
            player4_land.setText("Land Own: No Player" );
        } else if (playerNo == 4){
            player1_land.setText("Land Own: " + noLandOwn[0]);
            player2_land.setText("Land Own: " + noLandOwn[1]);
            player3_land.setText("Land Own: " + noLandOwn[2]);
            player4_land.setText("Land Own: " + noLandOwn[3]);
        }
    }

    public void updateJailDate(int[] jailDate){
        if (playerNo == 2){
            player1_jail.setText("Jail Date: " + jailDate[0]);
            player2_jail.setText("Jail Date: " + jailDate[1]);
            player3_jail.setText("Jail Date: No Player" );
            player4_jail.setText("Jail Date: No Player" );
        } else if (playerNo == 3){
            player1_jail.setText("Jail Date: " + jailDate[0]);
            player2_jail.setText("Jail Date: " + jailDate[1]);
            player3_jail.setText("Jail Date: " + jailDate[2]);
            player4_jail.setText("Jail Date: No Player" );
        } else if (playerNo == 4){
            player1_jail.setText("Jail Date: " + jailDate[0]);
            player2_jail.setText("Jail Date: " + jailDate[1]);
            player3_jail.setText("Jail Date: " + jailDate[2]);
            player4_jail.setText("Jail Date: " + jailDate[3]);
        }
    }
    //Image insert
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT));
        } else {
            System.err.println("Couldn't find the file: " + path);
            return null;
        }
    }
    private ImageIcon createDiceIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        } else {
            System.err.println("Couldn't find the file: " + path);
            return null;
        }
    }
    private int coordToindex(int row, int col) {     // convert 2D array to 1D array index
        return (col * 9) + row;
    }

}
