import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class MonopolyView extends JFrame {
    JButton start_btn, exit_btn;
    JPanel startPanel, playerNumberPanel, boardPanel, mainPanel, infoPanel,
            player1_infoPanel, player2_infoPanel, player3_infoPanel, player4_infoPanel;
    JLabel playerNumberLabel, player_round = new JLabel(),
            player1_balance, player1_land, player1_jail,
            player2_balance, player2_land, player2_jail,
            player3_balance, player3_land, player3_jail,
            player4_balance, player4_land, player4_jail;
    JTextField playerNumber;
    private int playerNo, backspaceCount;
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
    public void game_start(int playernum, int[] landPrice, String[] landName){
        this.setBounds(300, 100, 1200, 900);
        mainPanel.remove(startPanel);
        mainPanel.remove(playerNumberPanel);
        mainPanel.setBackground(Color.white);
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
                        cell.add(Box.createVerticalStrut(10));
                        JLabel text = new JLabel("Start");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);

                    } else if (row == 1 && col == 8 || row == 2 && col == 8 || row == 3 && col == 8) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(0, 171, 78));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if (row == 1) {
                            JLabel text = new JLabel(landName[9]);
                            JLabel price = new JLabel("Price: " + landPrice[9]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);
                            cell.add(price);

                        } else if (row == 2) {
                            JLabel text = new JLabel(landName[10]);
                            JLabel price = new JLabel("Price: " + landPrice[10]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        } else {
                            JLabel text = new JLabel(landName[11]);
                            JLabel price = new JLabel("Price: " + landPrice[11]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }

                    } else if(row == 0 && col == 1 || row == 0 && col == 2 || row == 0 && col == 3) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(0, 247, 255));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if(col == 1) {
                            JLabel text = new JLabel(landName[1]);
                            JLabel price = new JLabel("Price: " + landPrice[1]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else if(col == 2) {
                            JLabel text = new JLabel(landName[2]);
                            JLabel price = new JLabel("Price: " + landPrice[2]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else {
                            JLabel text = new JLabel(landName[3]);
                            JLabel price = new JLabel("Price: " + landPrice[3]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }

                    } else if(row == 0 && col == 5 || row == 0 && col == 6 || row == 0 && col == 7){
                        JPanel color = new JPanel();;
                        color.setBackground(new Color(166, 97, 0));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if(col == 5) {
                            JLabel text = new JLabel(landName[5]);
                            JLabel price = new JLabel("Price: " + landPrice[5]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else if(col == 6) {
                            JLabel text = new JLabel(landName[6]);
                            JLabel price = new JLabel("Price: " + landPrice[6]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else {
                            JLabel text = new JLabel(landName[7]);
                            JLabel price = new JLabel("Price: " + landPrice[7]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }

                    } else if(row == 8 && col == 5 || row == 8 && col == 6 || row == 8 && col == 7){
                        JPanel color = new JPanel();;
                        color.setBackground(new Color(255, 149, 0));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if(col == 7) {
                            JLabel text = new JLabel(landName[17]);
                            JLabel price = new JLabel("Price: " + landPrice[17]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else if(col == 6){
                            JLabel text = new JLabel(landName[18]);
                            JLabel price = new JLabel("Price: " + landPrice[18]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else{
                            JLabel text = new JLabel(landName[19]);
                            JLabel price = new JLabel("Price: " + landPrice[19]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }

                    } else if(col == 0 && row == 5 || col == 0 && row == 6 || col == 0 && row == 7) {
                        JPanel color = new JPanel();;
                        color.setBackground(new Color(0, 108, 198));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if(row == 5) {
                            JLabel text = new JLabel(landName[27]);
                            JLabel price = new JLabel("Price: " + landPrice[27]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else if(row == 6){
                            JLabel text = new JLabel(landName[26]);
                            JLabel price = new JLabel("Price: " + landPrice[26]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else {
                            JLabel text = new JLabel(landName[25]);
                            JLabel price = new JLabel("Price: " + landPrice[25]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }
                    } else if (row == 0 && col == 4 || row == 8 && col == 4 || row == 4 && col == 8 || row == 4 && col == 0) {
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        JLabel text = new JLabel("Chance");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);



                    } else if (row == 8 && col == 1 || row == 8 && col == 2 || row == 8 && col == 3) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(155, 52, 235));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if (col == 1) {
                            JLabel text = new JLabel(landName[23]);
                            JLabel price = new JLabel("Price: " + landPrice[23]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        } else if (col == 2) {
                            JLabel text = new JLabel(landName[22]);
                            JLabel price = new JLabel("Price: " + landPrice[22]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        } else {
                            JLabel text = new JLabel(landName[21]);
                            JLabel price = new JLabel("Price: " + landPrice[21]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }

                    } else if(col == 0 && row == 1 || col == 0 && row == 2 || col == 0 && row == 3){
                        JPanel color = new JPanel();
                        color.setBackground(new Color(176, 250, 5));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if(row == 1){
                            JLabel text = new JLabel(landName[31]);
                            JLabel price = new JLabel("Price: " + landPrice[31]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else if(row == 3){
                            JLabel text = new JLabel(landName[29]);
                            JLabel price = new JLabel("Price: " + landPrice[29]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }else{
                            JLabel text = new JLabel(landName[30]);
                            JLabel price = new JLabel("Price: " + landPrice[30]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }
                    } else if(col == 8 && row == 5 || col == 8 && row == 6 || col == 8 && row == 7) {
                        JPanel color = new JPanel();
                        color.setBackground(new Color(255, 0, 0));
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        if (row == 5) {
                            JLabel text = new JLabel(landName[13]);
                            JLabel price = new JLabel("Price: " + landPrice[13]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        } else if (row == 6) {
                            JLabel text = new JLabel(landName[14]);
                            JLabel price = new JLabel("Price: " + landPrice[14]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        } else {
                            JLabel text = new JLabel(landName[15]);
                            JLabel price = new JLabel("Price: " + landPrice[15]);
                            text.setAlignmentX(Component.CENTER_ALIGNMENT);
                            price.setAlignmentX(Component.CENTER_ALIGNMENT);
                            cell.add(text);
                            cell.add(price);

                        }

                    } else if (row == 0 && col == 8) { //top row last col
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        JLabel text = new JLabel("Get $500");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);
                            


                    }else if(row == 8 && col == 0) {
                        JPanel color = new JPanel();
                        color.setBackground(Color.darkGray);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        JLabel text = new JLabel("Go to Jail");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);
                            


                    } else if(row == 8 && col ==8){
                        JPanel color = new JPanel();
                        color.setBackground(Color.black);
                        color.setMaximumSize(new Dimension(100,100));
                        color.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        cell.add(color);
                        cell.add(Box.createVerticalStrut(10));
                        JLabel text = new JLabel("Jail");
                        text.setAlignmentX(Component.CENTER_ALIGNMENT);
                        cell.add(text);
                            


                    }

                    cell.setBackground(new Color(169, 207, 255));

                } else if (row == 3 && col == 4){
                    JButton roll_btn = new JButton("Roll Dice");
                    roll_btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    roll_btn.setAlignmentY(Component.CENTER_ALIGNMENT);
                    roll_btn.setBackground(Color.gray);
                    roll_btn.setPreferredSize(new Dimension(70,30));
                    roll_btn.addActionListener(e -> control.modelRollDice());
                    cell.add(Box.createVerticalStrut(30));
                    cell.add(roll_btn);

                } else if (row == 4 && col == 4){
                    cell.setBorder(BorderFactory.createTitledBorder("Dice"));
                    cell.setBorder(new BevelBorder(BevelBorder.RAISED));
                } else if (row == 5 && col == 4){
                    cell.setBorder(BorderFactory.createTitledBorder("Round"));
                    cell.setBorder(new BevelBorder(BevelBorder.RAISED));
                    cell.add(player_round);
                }
                boardPanel.add(cell);
            }
        }

        mainPanel.add(boardPanel);
        mainPanel.revalidate();
        mainPanel.repaint();

        gameEditorDetector();

        mainPanel.add(Box.createHorizontalStrut(50)); 

        infoPanel = new JPanel();
        mainPanel.add(infoPanel);
        infoPanel.setPreferredSize(new Dimension(500, 420));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        //Player Info
        player1_infoPanel = new JPanel();
        player1_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 column
        player1_infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,3), "Player 1"));
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
        player2_infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white,3), "Player 2"));
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
        player3_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 columncolumn
        player3_infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow,3), "Player 3"));
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
        player4_infoPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 2 rows and 1 columncolumn
        player4_infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red,3), "Player 4"));
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


        player1_infoPanel.setBackground(new Color(238,217,196));
        player2_infoPanel.setBackground(new Color(238,217,196));
        player3_infoPanel.setBackground(new Color(238,217,196));
        player4_infoPanel.setBackground(new Color(238,217,196));

        infoPanel.add(player1_infoPanel);
        infoPanel.add(player2_infoPanel);
        infoPanel.add(player3_infoPanel);
        infoPanel.add(player4_infoPanel);

        updateDice(0);
        updateRound(0);
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
        switch (dice) {
            case 1 -> {
                dicepic1 = createDiceIcon("images/dice1.png");
                dicepic2 = createDiceIcon("images/dice0.png");
            }
            case 2 -> {
                dicepic1 = createDiceIcon("images/dice1.png");
                dicepic2 = createDiceIcon("images/dice1.png");
            }
            case 3 -> {
                Random rand = new Random();
                int random = rand.nextInt(2);

                if(random == 1){
                    dicepic1 = createDiceIcon("images/dice1.png");
                    dicepic2 = createDiceIcon("images/dice2.png");
                }else {
                    dicepic1 = createDiceIcon("images/dice2.png");
                    dicepic2 = createDiceIcon("images/dice1.png");
                }
            }
            case 4 -> {
                Random rand = new Random();
                int random = rand.nextInt(3);
                if(random == 0){
                    dicepic1 = createDiceIcon("images/dice1.png");
                    dicepic2 = createDiceIcon("images/dice3.png");
                }else if(random == 1){
                    dicepic1 = createDiceIcon("images/dice2.png");
                    dicepic2 = createDiceIcon("images/dice2.png");
                }else{
                    dicepic1 = createDiceIcon("images/dice3.png");
                    dicepic2 = createDiceIcon("images/dice1.png");
                }
            }
            case 5 -> {
                Random rand = new Random();
                int random = rand.nextInt(3);
                switch (random) {
                    case 0 -> {
                        dicepic1 = createDiceIcon("images/dice1.png");
                        dicepic2 = createDiceIcon("images/dice4.png");
                    }
                    case 1 -> {
                        dicepic1 = createDiceIcon("images/dice2.png");
                        dicepic2 = createDiceIcon("images/dice3.png");
                    }
                    case 2 -> {
                        dicepic1 = createDiceIcon("images/dice3.png");
                        dicepic2 = createDiceIcon("images/dice2.png");
                    }
                    case 3 -> {
                        dicepic1 = createDiceIcon("images/dice4.png");
                        dicepic2 = createDiceIcon("images/dice1.png");
                    }
                }
            }
            case 6 ->{
                Random rand = new Random();
                int random = rand.nextInt(4);
                switch (random) {
                    case 0 -> {
                        dicepic1 = createDiceIcon("images/dice1.png");
                        dicepic2 = createDiceIcon("images/dice5.png");
                    }
                    case 1 -> {
                        dicepic1 = createDiceIcon("images/dice2.png");
                        dicepic2 = createDiceIcon("images/dice4.png");
                    }
                    case 2 -> {
                        dicepic1 = createDiceIcon("images/dice3.png");
                        dicepic2 = createDiceIcon("images/dice3.png");
                    }
                    case 3 -> {
                        dicepic1 = createDiceIcon("images/dice4.png");
                        dicepic2 = createDiceIcon("images/dice2.png");
                    }
                    case 4 -> {
                        dicepic1 = createDiceIcon("images/dice5.png");
                        dicepic2 = createDiceIcon("images/dice1.png");
                    }
                }
            }
            case 7 -> {
                Random rand = new Random();
                int random = rand.nextInt(5);
                switch (random) {
                    case 0 -> {
                        dicepic1 = createDiceIcon("images/dice1.png");
                        dicepic2 = createDiceIcon("images/dice6.png");
                    }
                    case 1 -> {
                        dicepic1 = createDiceIcon("images/dice2.png");
                        dicepic2 = createDiceIcon("images/dice5.png");
                    }
                    case 2 -> {
                        dicepic1 = createDiceIcon("images/dice3.png");
                        dicepic2 = createDiceIcon("images/dice4.png");
                    }
                    case 3 -> {
                        dicepic1 = createDiceIcon("images/dice4.png");
                        dicepic2 = createDiceIcon("images/dice3.png");
                    }
                    case 4 -> {
                        dicepic1 = createDiceIcon("images/dice5.png");
                        dicepic2 = createDiceIcon("images/dice2.png");
                    }
                    case 5 -> {
                        dicepic1 = createDiceIcon("images/dice6.png");
                        dicepic2 = createDiceIcon("images/dice1.png");
                    }
                }
            }
            case 8 -> {
                Random rand = new Random();
                int random = rand.nextInt(5);
                switch (random) {
                    case 0 -> {
                        dicepic1 = createDiceIcon("images/dice2.png");
                        dicepic2 = createDiceIcon("images/dice6.png");
                    }
                    case 1 -> {
                        dicepic1 = createDiceIcon("images/dice3.png");
                        dicepic2 = createDiceIcon("images/dice5.png");
                    }
                    case 2 -> {
                        dicepic1 = createDiceIcon("images/dice4.png");
                        dicepic2 = createDiceIcon("images/dice4.png");
                    }
                    case 3 -> {
                        dicepic1 = createDiceIcon("images/dice5.png");
                        dicepic2 = createDiceIcon("images/dice3.png");
                    }
                    case 4 -> {
                        dicepic1 = createDiceIcon("images/dice6.png");
                        dicepic2 = createDiceIcon("images/dice2.png");
                    }
                }
            }
            case 9 -> {
                Random rand = new Random();
                int random = rand.nextInt(4);
                switch (random) {
                    case 0 -> {
                        dicepic1 = createDiceIcon("images/dice3.png");
                        dicepic2 = createDiceIcon("images/dice6.png");
                    }
                    case 1 -> {
                        dicepic1 = createDiceIcon("images/dice4.png");
                        dicepic2 = createDiceIcon("images/dice5.png");
                    }
                    case 2 -> {
                        dicepic1 = createDiceIcon("images/dice5.png");
                        dicepic2 = createDiceIcon("images/dice4.png");
                    }
                    case 3 -> {
                        dicepic1 = createDiceIcon("images/dice6.png");
                        dicepic2 = createDiceIcon("images/dice3.png");
                    }
                }
            }
            case 10 -> {
                Random rand = new Random();
                int random = rand.nextInt(3);
                if (random == 0) {
                    dicepic1 = createDiceIcon("images/dice4.png");
                    dicepic2 = createDiceIcon("images/dice6.png");
                } else if (random == 1) {
                    dicepic1 = createDiceIcon("images/dice5.png");
                    dicepic2 = createDiceIcon("images/dice5.png");
                } else {
                    dicepic1 = createDiceIcon("images/dice6.png");
                    dicepic2 = createDiceIcon("images/dice4.png");
                }
            }
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

    public void updateRound(int player){
        JPanel round_grid = (JPanel) boardPanel.getComponent(49);
        round_grid.setLayout(new BoxLayout(round_grid,BoxLayout.Y_AXIS));
        round_grid.removeAll();
        player++;
        player_round.setText("Player "+ player );
        player_round.setAlignmentX(Component.CENTER_ALIGNMENT);
        round_grid.add(Box.createVerticalStrut(25));
        round_grid.add(player_round);
        round_grid.revalidate();
        round_grid.repaint();
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
        JOptionPane.showMessageDialog(null, "You have paid $" + price + " to player " + (ownership + 1));
    }

    public void showGiftNotify(int price){
        JOptionPane.showMessageDialog(null, "You have get $"+ price +" as a gift!");
    }

    public void showChanceNotify(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

    public void showJailNotify(int days){
        JOptionPane.showMessageDialog(null, "You are still in jail for " + days + " days");
    }

    public void showBankruptNotify(int player, int[] landOwnership, int[] boardPosition){
        JOptionPane.showMessageDialog(null, "You are bankrupt! \n You Lose!");
        for(int i = 0; i < landOwnership.length; i++){
            if (landOwnership[i] == -1){

                JPanel cell = (JPanel) boardPanel.getComponent(boardPosition[i]);

                // Ensure that the cell panel exists
                if (cell != null) {
                    // Retrieve the color panel from the cell panel
                    JPanel colorPanel = null;
                    if (cell.getComponentCount() > 0) {
                        colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component
                        colorPanel.removeAll();
                    }
                }
            }
        }


    }

    public void showGameOverNotify(int winner){
        JOptionPane.showMessageDialog(null, "Game Over! \n" +
                "And the winner is Player" + winner);
        JPanel grid = (JPanel) boardPanel.getComponent(31);
        grid.removeAll();
        grid.setLayout(new BoxLayout(grid,BoxLayout.X_AXIS));
        grid.revalidate();
        grid.repaint();
    }

    public void notAllowBuyingNotify(){
        JOptionPane.showMessageDialog(null, "You have no money to buy the land");
    }

    public void updateOwner(int[] boardPosition, int[] landOwnership){
        for(int i = 0; i < landOwnership.length; i++){
            JPanel cell = (JPanel) boardPanel.getComponent(boardPosition[i]);
            if (landOwnership[i] == -1){
                // Ensure that the cell panel exists
                if (cell != null) {
                    // Retrieve the color panel from the cell panel
                    JPanel colorPanel = null;
                    if (cell.getComponentCount() > 0) {
                        colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component
                        colorPanel.removeAll();
                        colorPanel.revalidate();
                        colorPanel.repaint();

                    }
                }

            } else if(landOwnership[i] == 0){

                // Ensure that the cell panel exists
                if (cell != null) {
                    // Retrieve the color panel from the cell panel
                    JPanel colorPanel = null;
                    if (cell.getComponentCount() > 0) {
                        colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component

                        if (colorPanel != null) {
                            ImageIcon picture = createImageIcon("images/player1.png");
                            if (picture != null) {
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
            } else if(landOwnership[i] == 1) {

                // Ensure that the cell panel exists
                if (cell != null) {
                    // Retrieve the color panel from the cell panel
                    JPanel colorPanel = null;
                    if (cell.getComponentCount() > 0) {
                        colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component

                        if (colorPanel != null) {
                            ImageIcon picture = createImageIcon("images/player2.png");
                            if (picture != null) {
                                // Set the new background color for the color panel
                                JLabel playerLabel = new JLabel(picture);

                                // Add the player label to the grid panel
                                colorPanel.removeAll(); // Remove existing components
                                colorPanel.add(playerLabel, BorderLayout.CENTER); // Add without specifying layout constraint
                                colorPanel.revalidate();
                            }
                        }
                    }
                }
            } else if(landOwnership[i] == 2) {

                // Ensure that the cell panel exists
                if (cell != null) {
                    // Retrieve the color panel from the cell panel
                    JPanel colorPanel = null;
                    if (cell.getComponentCount() > 0) {
                        colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component

                        if (colorPanel != null) {
                            ImageIcon picture = createImageIcon("images/player3.png");
                            if (picture != null) {
                                // Set the new background color for the color panel
                                JLabel playerLabel = new JLabel(picture);

                                // Add the player label to the grid panel
                                colorPanel.removeAll(); // Remove existing components
                                colorPanel.add(playerLabel, BorderLayout.CENTER); // Add without specifying layout constraint
                                colorPanel.revalidate();
                            }
                        }
                    }
                }
            } else if(landOwnership[i] == 3) {

                // Ensure that the cell panel exists
                if (cell != null) {
                    // Retrieve the color panel from the cell panel
                    JPanel colorPanel = null;
                    if (cell.getComponentCount() > 0) {
                        colorPanel = (JPanel) cell.getComponent(0); // Assuming color panel is the first component

                        if (colorPanel != null) {
                            ImageIcon picture = createImageIcon("images/player4.png");
                            if (picture != null) {
                                // Set the new background color for the color panel
                                JLabel playerLabel = new JLabel(picture);

                                // Add the player label to the grid panel
                                colorPanel.removeAll(); // Remove existing components
                                colorPanel.add(playerLabel, BorderLayout.CENTER); // Add without specifying layout constraint
                                colorPanel.revalidate();
                            }
                        }
                    }
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

    public void gameEditorDetector(){

        backspaceCount = 0;
        mainPanel.setFocusable(true); // Allow the panel to receive keyboard focus
        mainPanel.requestFocus(); // Request focus so that the panel can receive key events
        mainPanel.requestFocusInWindow(); // Request initial focus
        mainPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                mainPanel.requestFocusInWindow(); // Request focus again
            }
        });
        mainPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    backspaceCount++;
                    if (backspaceCount >= 5) {
                        control.accessData();
                        backspaceCount = 0; // Reset backspace count
                    }
                } else {
                    backspaceCount = 0; // Reset backspace count if other key is pressed
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void gameEditorFrame(int playerNumber, int[] currentPlayerPosition,int[] playerBalance, int[] jailDate, int[] landOwnership){

        JFrame gameEditorFrame = new JFrame("Game Editor");
        gameEditorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame
        gameEditorFrame.setSize(500, 550);
        gameEditorFrame.setLocationRelativeTo(null);
        gameEditorFrame.setVisible(true);
        gameEditorFrame.setResizable(false);

        JPanel editorMainPanel = new JPanel();
        editorMainPanel.setLayout(new BoxLayout(editorMainPanel, BoxLayout.Y_AXIS));
        editorMainPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        gameEditorFrame.add(editorMainPanel);


        JPanel roundControl = new JPanel();
        roundControl.setLayout(new BoxLayout(roundControl, BoxLayout.X_AXIS));
        roundControl.setBorder(new BevelBorder(BevelBorder.RAISED));
        roundControl.setMaximumSize(new Dimension(500, 50));

        JLabel round = new JLabel("Round of Players: ");
        JComboBox<Integer> roundComboBox = new JComboBox<>();
        roundComboBox.setMaximumSize(new Dimension(50,20));
        for(int i = 0; i < playerNumber; i++){
            roundComboBox.addItem(i + 1);
        }
        //check if round combo box got changed
        roundComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem() - 1;
                control.editCurrentPlayer(selectedPlayer);
            }
        });

        roundControl.add(Box.createVerticalStrut(1));
        roundControl.add(round);
        roundControl.add(Box.createVerticalStrut(1));
        roundControl.add(roundComboBox);
        roundControl.add(Box.createVerticalStrut(10));

        JPanel positionPanel = new JPanel();
        positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.Y_AXIS));
        positionPanel.setMaximumSize(new Dimension(500, 60));
        positionPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        JPanel positionPanel1 = new JPanel();
        positionPanel1.setLayout(new BoxLayout(positionPanel1, BoxLayout.X_AXIS));
        positionPanel1.setMaximumSize(new Dimension(500, 30));

        JPanel positionPanel2 = new JPanel();
        positionPanel2.setLayout(new BoxLayout(positionPanel2, BoxLayout.X_AXIS));
        positionPanel2.setMaximumSize(new Dimension(500, 30));

        JLabel p1pos = new JLabel("Player 1 Position: ");
        JComboBox<Integer> p1posComboBox = new JComboBox<>();
        p1posComboBox.setMaximumSize(new Dimension(50,20));
        JLabel p2pos = new JLabel("Player 2 Position: ");
        JComboBox<Integer> p2posComboBox = new JComboBox<>();
        p2posComboBox.setMaximumSize(new Dimension(50,20));
        JLabel p3pos = new JLabel("Player 3 Position: ");
        JComboBox<Integer> p3posComboBox = new JComboBox<>();
        p3posComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p4pos = new JLabel("Player 4 Position: ");
        JComboBox<Integer> p4posComboBox = new JComboBox<>();
        p4posComboBox.setMaximumSize(new Dimension(50, 20));

        if (playerNumber == 2){
            for(int i = 0; i < 32; i++){
                p1posComboBox.addItem(i);
                p2posComboBox.addItem(i);
            }
            p3posComboBox.addItem(-1);
            p4posComboBox.addItem(-1);
            p1posComboBox.setSelectedItem(currentPlayerPosition[0]);
            p2posComboBox.setSelectedItem(currentPlayerPosition[1]);

        } else if(playerNumber == 3){
            for(int i = 0; i < 32; i++){
                p1posComboBox.addItem(i);
                p2posComboBox.addItem(i);
                p3posComboBox.addItem(i);
            }
            p4posComboBox.addItem(-1);
            p1posComboBox.setSelectedItem(currentPlayerPosition[0]);
            p2posComboBox.setSelectedItem(currentPlayerPosition[1]);
            p3posComboBox.setSelectedItem(currentPlayerPosition[2]);

        } else if(playerNumber == 4){
            for(int i = 0; i < 32; i++){
                p1posComboBox.addItem(i);
                p2posComboBox.addItem(i);
                p3posComboBox.addItem(i);
                p4posComboBox.addItem(i);
            }
            p1posComboBox.setSelectedItem(currentPlayerPosition[0]);
            p2posComboBox.setSelectedItem(currentPlayerPosition[1]);
            p3posComboBox.setSelectedItem(currentPlayerPosition[2]);
            p4posComboBox.setSelectedItem(currentPlayerPosition[3]);

        }

        //check if position combo box got changed
        p1posComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPosition = (int) comboBox.getSelectedItem();
                control.editPosition(selectedPosition, 0);
            }
        });

        p2posComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPosition = (int) comboBox.getSelectedItem();
                control.editPosition(selectedPosition, 1);
            }
        });

        if (playerNumber == 3){
            p3posComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                    int selectedPosition = (int) comboBox.getSelectedItem();
                    control.editPosition(selectedPosition, 2);
                }
            });
        } else if(playerNumber == 4){
            p3posComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                    int selectedPosition = (int) comboBox.getSelectedItem();
                    control.editPosition(selectedPosition, 2);
                }
            });
            p4posComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                    int selectedPosition = (int) comboBox.getSelectedItem();
                    control.editPosition(selectedPosition, 3);
                }
            });
        }

        positionPanel1.add(Box.createVerticalStrut(1));
        positionPanel1.add(p1pos);
        positionPanel1.add(p1posComboBox);
        positionPanel1.add(Box.createVerticalStrut(1));
        positionPanel1.add(p2pos);
        positionPanel1.add(p2posComboBox);
        positionPanel1.add(Box.createVerticalStrut(1));


        positionPanel2.add(Box.createVerticalStrut(1));
        positionPanel2.add(p3pos);
        positionPanel2.add(p3posComboBox);
        positionPanel2.add(Box.createVerticalStrut(1));
        positionPanel2.add(p4pos);
        positionPanel2.add(p4posComboBox);
        positionPanel2.add(Box.createVerticalStrut(1));

        positionPanel.add(positionPanel1);
        positionPanel.add(positionPanel2);

        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
        balancePanel.setMaximumSize(new Dimension(500, 60));
        balancePanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        JPanel p1balancePanel = new JPanel();
        p1balancePanel.setLayout(new BoxLayout(p1balancePanel, BoxLayout.X_AXIS));
        p1balancePanel.setMaximumSize(new Dimension(500, 30));

        JPanel p2balancePanel = new JPanel();
        p2balancePanel.setLayout(new BoxLayout(p2balancePanel, BoxLayout.X_AXIS));
        p2balancePanel.setMaximumSize(new Dimension(500, 30));

        JLabel p1Balance = new JLabel("Player 1 Balance: ");
        JTextField p1Balance_tf = new JTextField(5);
        p1Balance_tf.setMaximumSize(new Dimension(50, 20));
        JLabel p2Balance = new JLabel("Player 2 Balance: ");
        JTextField p2Balance_tf = new JTextField(5);
        p2Balance_tf.setMaximumSize(new Dimension(50, 20));
        JLabel p3Balance = new JLabel("Player 3 Balance: ");
        JTextField p3Balance_tf = new JTextField(5);
        p3Balance_tf.setMaximumSize(new Dimension(50, 20));
        JLabel p4Balance = new JLabel("Player 4 Balance: ");
        JTextField p4Balance_tf = new JTextField(5);
        p4Balance_tf.setMaximumSize(new Dimension(50, 20));


        p1Balance_tf.setText(String.valueOf(playerBalance[0]));
        p2Balance_tf.setText(String.valueOf(playerBalance[1]));

        p1Balance_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_MINUS) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        p1Balance_tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int balance = Integer.parseInt(textField.getText());
                control.editBalance(balance, 0);
            }
        });
        p2Balance_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_MINUS) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        p2Balance_tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int balance = Integer.parseInt(textField.getText());
                control.editBalance(balance, 1);
            }
        });

        if (playerNumber == 2){
            p3Balance_tf.setText(String.valueOf(-1));
            p4Balance_tf.setText(String.valueOf(-1));

        } else if(playerNumber == 3){
            p3Balance_tf.setText(String.valueOf(playerBalance[2]));
            p3Balance_tf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_MINUS) ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            p3Balance_tf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField textField = (JTextField) e.getSource();
                    int balance = Integer.parseInt(textField.getText());
                    control.editBalance(balance, 2);
                }
            });
            p4Balance_tf.setText(String.valueOf(-1));

        } else if(playerNumber == 4){
            p3Balance_tf.setText(String.valueOf(playerBalance[2]));
            p3Balance_tf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_MINUS) ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            p3Balance_tf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField textField = (JTextField) e.getSource();
                    int balance = Integer.parseInt(textField.getText());
                    control.editBalance(balance, 2);
                }
            });

            p4Balance_tf.setText(String.valueOf(playerBalance[3]));
            p4Balance_tf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_MINUS) ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            p4Balance_tf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField textField = (JTextField) e.getSource();
                    int balance = Integer.parseInt(textField.getText());
                    control.editBalance(balance, 3);
                }
            });
        }

        p1balancePanel.add(Box.createVerticalStrut(1));
        p1balancePanel.add(p1Balance);
        p1balancePanel.add(p1Balance_tf);
        p1balancePanel.add(Box.createVerticalStrut(1));
        p1balancePanel.add(p2Balance);
        p1balancePanel.add(p2Balance_tf);
        p1balancePanel.add(Box.createVerticalStrut(1));

        p2balancePanel.add(Box.createVerticalStrut(1));
        p2balancePanel.add(p3Balance);
        p2balancePanel.add(p3Balance_tf);
        p2balancePanel.add(Box.createVerticalStrut(1));
        p2balancePanel.add(p4Balance);
        p2balancePanel.add(p4Balance_tf);
        p2balancePanel.add(Box.createVerticalStrut(1));

        balancePanel.add(p1balancePanel);
        balancePanel.add(p2balancePanel);

        JPanel jailPanel = new JPanel();
        jailPanel.setLayout(new BoxLayout(jailPanel, BoxLayout.Y_AXIS));
        jailPanel.setMaximumSize(new Dimension(500, 60));
        jailPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        JPanel p1JailPanel = new JPanel();
        p1JailPanel.setLayout(new BoxLayout(p1JailPanel, BoxLayout.X_AXIS));
        p1JailPanel.setMaximumSize(new Dimension(500, 30));

        JPanel p2JailPanel = new JPanel();
        p2JailPanel.setLayout(new BoxLayout(p2JailPanel, BoxLayout.X_AXIS));
        p2JailPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p1Jail = new JLabel("Player 1 Jail Date: ");
        JTextField p1Jail_tf = new JTextField(3);
        p1Jail_tf.setMaximumSize(new Dimension(50, 20));
        JLabel p2Jail = new JLabel("Player 2 Jail Date: ");
        JTextField p2Jail_tf = new JTextField(3);
        p2Jail_tf.setMaximumSize(new Dimension(50, 20));
        JLabel p3Jail = new JLabel("Player 3 Jail Date: ");
        JTextField p3Jail_tf = new JTextField(3);
        p3Jail_tf.setMaximumSize(new Dimension(50, 20));
        JLabel p4Jail = new JLabel("Player 4 Jail Date: ");
        JTextField p4Jail_tf = new JTextField(3);
        p4Jail_tf.setMaximumSize(new Dimension(50, 20));


        p1Jail_tf.setText(String.valueOf(jailDate[0]));
        p1Jail_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        p1Jail_tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int days = Integer.parseInt(textField.getText());
                control.editJail(days, 0);
            }
        });

        p2Jail_tf.setText(String.valueOf(jailDate[1]));
        p2Jail_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        p2Jail_tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int days = Integer.parseInt(textField.getText());
                control.editJail(days, 1);
            }
        });

        p3Jail_tf.setText(String.valueOf(-1));
        p4Jail_tf.setText(String.valueOf(-1));

        if(playerNumber == 3){
            p3Jail_tf.setText(String.valueOf(jailDate[2]));
            p3Jail_tf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            p3Jail_tf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField textField = (JTextField) e.getSource();
                    int days = Integer.parseInt(textField.getText());
                    control.editJail(days, 2);
                }
            });
            p4Jail_tf.setText(String.valueOf(-1));

        } else if(playerNumber == 4){
            p3Jail_tf.setText(String.valueOf(jailDate[2]));
            p3Jail_tf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            p3Jail_tf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField textField = (JTextField) e.getSource();
                    int days = Integer.parseInt(textField.getText());
                    control.editJail(days, 2);
                }
            });
            p4Jail_tf.setText(String.valueOf(jailDate[3]));
            p4Jail_tf.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        getToolkit().beep();
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            p4Jail_tf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField textField = (JTextField) e.getSource();
                    int days = Integer.parseInt(textField.getText());
                    control.editJail(days, 3);
                }
            });

        }

        p1JailPanel.add(Box.createVerticalStrut(1));
        p1JailPanel.add(p1Jail);
        p1JailPanel.add(p1Jail_tf);
        p1JailPanel.add(Box.createVerticalStrut(1));
        p1JailPanel.add(p2Jail);
        p1JailPanel.add(p2Jail_tf);
        p1JailPanel.add(Box.createVerticalStrut(1));

        p2JailPanel.add(Box.createVerticalStrut(1));
        p2JailPanel.add(p3Jail);
        p2JailPanel.add(p3Jail_tf);
        p2JailPanel.add(Box.createVerticalStrut(1));
        p2JailPanel.add(p4Jail);
        p2JailPanel.add(p4Jail_tf);
        p2JailPanel.add(Box.createVerticalStrut(1));

        jailPanel.add(p1JailPanel);
        jailPanel.add(p2JailPanel);

        JPanel landPanel = new JPanel();
        landPanel.setLayout(new BoxLayout(landPanel, BoxLayout.Y_AXIS));
        landPanel.setBorder(new BevelBorder(BevelBorder.RAISED));

        JPanel p1LandPanel = new JPanel();
        p1LandPanel.setLayout(new BoxLayout(p1LandPanel, BoxLayout.X_AXIS));
        p1LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p1land1 = new JLabel("Lok Ma Chau");
        JComboBox<Integer> p1land1ComboBox = new JComboBox<>();
        p1land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p1land2 = new JLabel("Fan Ling");
        JComboBox<Integer> p1land2ComboBox = new JComboBox<>();
        p1land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p1land3 = new JLabel("Sha Tin");
        JComboBox<Integer> p1land3ComboBox = new JComboBox<>();
        p1land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p1LandPanel.add(Box.createVerticalStrut(1));
        p1LandPanel.add(p1land1);
        p1LandPanel.add(p1land1ComboBox);
        p1LandPanel.add(Box.createVerticalStrut(1));
        p1LandPanel.add(p1land2);
        p1LandPanel.add(p1land2ComboBox);
        p1LandPanel.add(Box.createVerticalStrut(1));
        p1LandPanel.add(p1land3);
        p1LandPanel.add(p1land3ComboBox);
        p1LandPanel.add(Box.createVerticalStrut(1));

        JPanel p2LandPanel = new JPanel();
        p2LandPanel.setLayout(new BoxLayout(p2LandPanel, BoxLayout.X_AXIS));
        p2LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p2land1 = new JLabel("Tuen Mun");
        JComboBox<Integer> p2land1ComboBox = new JComboBox<>();
        p2land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p2land2 = new JLabel("To Kwa Wan");
        JComboBox<Integer> p2land2ComboBox = new JComboBox<>();
        p2land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p2land3 = new JLabel("Ma On Shan");
        JComboBox<Integer> p2land3ComboBox = new JComboBox<>();
        p2land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p2LandPanel.add(Box.createVerticalStrut(1));
        p2LandPanel.add(p2land1);
        p2LandPanel.add(p2land1ComboBox);
        p2LandPanel.add(Box.createVerticalStrut(1));
        p2LandPanel.add(p2land2);
        p2LandPanel.add(p2land2ComboBox);
        p2LandPanel.add(Box.createVerticalStrut(1));
        p2LandPanel.add(p2land3);
        p2LandPanel.add(p2land3ComboBox);
        p2LandPanel.add(Box.createVerticalStrut(1));

        JPanel p3LandPanel = new JPanel();
        p3LandPanel.setLayout(new BoxLayout(p3LandPanel, BoxLayout.X_AXIS));
        p3LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p3land1 = new JLabel("Kwun Tong");
        JComboBox<Integer> p3land1ComboBox = new JComboBox<>();
        p3land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p3land2 = new JLabel("Choi Hung");
        JComboBox<Integer> p3land2ComboBox = new JComboBox<>();
        p3land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p3land3 = new JLabel("Lok Fu");
        JComboBox<Integer> p3land3ComboBox = new JComboBox<>();
        p3land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p3LandPanel.add(Box.createVerticalStrut(1));
        p3LandPanel.add(p3land1);
        p3LandPanel.add(p3land1ComboBox);
        p3LandPanel.add(Box.createVerticalStrut(1));
        p3LandPanel.add(p3land2);
        p3LandPanel.add(p3land2ComboBox);
        p3LandPanel.add(Box.createVerticalStrut(1));
        p3LandPanel.add(p3land3);
        p3LandPanel.add(p3land3ComboBox);
        p3LandPanel.add(Box.createVerticalStrut(1));

        JPanel p4LandPanel = new JPanel();
        p4LandPanel.setLayout(new BoxLayout(p4LandPanel, BoxLayout.X_AXIS));
        p4LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p4land1 = new JLabel("Tsuen Wan");
        JComboBox<Integer> p4land1ComboBox = new JComboBox<>();
        p4land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p4land2 = new JLabel("Mei Foo");
        JComboBox<Integer> p4land2ComboBox = new JComboBox<>();
        p4land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p4land3 = new JLabel("Mong Kok");
        JComboBox<Integer> p4land3ComboBox = new JComboBox<>();
        p4land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p4LandPanel.add(Box.createVerticalStrut(1));
        p4LandPanel.add(p4land1);
        p4LandPanel.add(p4land1ComboBox);
        p4LandPanel.add(Box.createVerticalStrut(1));
        p4LandPanel.add(p4land2);
        p4LandPanel.add(p4land2ComboBox);
        p4LandPanel.add(Box.createVerticalStrut(1));
        p4LandPanel.add(p4land3);
        p4LandPanel.add(p4land3ComboBox);
        p4LandPanel.add(Box.createVerticalStrut(1));

        JPanel p5LandPanel = new JPanel();
        p5LandPanel.setLayout(new BoxLayout(p5LandPanel, BoxLayout.X_AXIS));
        p5LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p5land1 = new JLabel("Olympic");
        JComboBox<Integer> p5land1ComboBox = new JComboBox<>();
        p5land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p5land2 = new JLabel("Tsing Yi");
        JComboBox<Integer> p5land2ComboBox = new JComboBox<>();
        p5land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p5land3 = new JLabel("Tung Chung");
        JComboBox<Integer> p5land3ComboBox = new JComboBox<>();
        p5land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p5LandPanel.add(Box.createVerticalStrut(1));
        p5LandPanel.add(p5land1);
        p5LandPanel.add(p5land1ComboBox);
        p5LandPanel.add(Box.createVerticalStrut(1));
        p5LandPanel.add(p5land2);
        p5LandPanel.add(p5land2ComboBox);
        p5LandPanel.add(Box.createVerticalStrut(1));
        p5LandPanel.add(p5land3);
        p5LandPanel.add(p5land3ComboBox);
        p5LandPanel.add(Box.createVerticalStrut(1));

        JPanel p6LandPanel = new JPanel();
        p6LandPanel.setLayout(new BoxLayout(p6LandPanel, BoxLayout.X_AXIS));
        p6LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p6land1 = new JLabel("Po Lam");
        JComboBox<Integer> p6land1ComboBox = new JComboBox<>();
        p6land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p6land2 = new JLabel("Hang Hau");
        JComboBox<Integer> p6land2ComboBox = new JComboBox<>();
        p6land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p6land3 = new JLabel("Tsueng Kwan O");
        JComboBox<Integer> p6land3ComboBox = new JComboBox<>();
        p6land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p6LandPanel.add(Box.createVerticalStrut(1));
        p6LandPanel.add(p6land1);
        p6LandPanel.add(p6land1ComboBox);
        p6LandPanel.add(Box.createVerticalStrut(1));
        p6LandPanel.add(p6land2);
        p6LandPanel.add(p6land2ComboBox);
        p6LandPanel.add(Box.createVerticalStrut(1));
        p6LandPanel.add(p6land3);
        p6LandPanel.add(p6land3ComboBox);
        p6LandPanel.add(Box.createVerticalStrut(1));

        JPanel p7LandPanel = new JPanel();
        p7LandPanel.setLayout(new BoxLayout(p7LandPanel, BoxLayout.X_AXIS));
        p7LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p7land1 = new JLabel("Sheung Wan");
        JComboBox<Integer> p7land1ComboBox = new JComboBox<>();
        p7land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p7land2 = new JLabel("Causeway Bay");
        JComboBox<Integer> p7land2ComboBox = new JComboBox<>();
        p7land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p7land3 = new JLabel("Tai Koo");
        JComboBox<Integer> p7land3ComboBox = new JComboBox<>();
        p7land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p7LandPanel.add(Box.createVerticalStrut(1));
        p7LandPanel.add(p7land1);
        p7LandPanel.add(p7land1ComboBox);
        p7LandPanel.add(Box.createVerticalStrut(1));
        p7LandPanel.add(p7land2);
        p7LandPanel.add(p7land2ComboBox);
        p7LandPanel.add(Box.createVerticalStrut(1));
        p7LandPanel.add(p7land3);
        p7LandPanel.add(p7land3ComboBox);
        p7LandPanel.add(Box.createVerticalStrut(1));

        JPanel p8LandPanel = new JPanel();
        p8LandPanel.setLayout(new BoxLayout(p8LandPanel, BoxLayout.X_AXIS));
        p8LandPanel.setMaximumSize(new Dimension(500, 30));

        JLabel p8land1 = new JLabel("Airport");
        JComboBox<Integer> p8land1ComboBox = new JComboBox<>();
        p8land1ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p8land2 = new JLabel("Wong Chuk Hang");
        JComboBox<Integer> p8land2ComboBox = new JComboBox<>();
        p8land2ComboBox.setMaximumSize(new Dimension(50, 20));
        JLabel p8land3 = new JLabel("South Horizons");
        JComboBox<Integer> p8land3ComboBox = new JComboBox<>();
        p8land3ComboBox.setMaximumSize(new Dimension(50, 20));

        p8LandPanel.add(Box.createVerticalStrut(1));
        p8LandPanel.add(p8land1);
        p8LandPanel.add(p8land1ComboBox);
        p8LandPanel.add(Box.createVerticalStrut(1));
        p8LandPanel.add(p8land2);
        p8LandPanel.add(p8land2ComboBox);
        p8LandPanel.add(Box.createVerticalStrut(1));
        p8LandPanel.add(p8land3);
        p8LandPanel.add(p8land3ComboBox);
        p8LandPanel.add(Box.createVerticalStrut(1));

        landPanel.add(p1LandPanel);
        landPanel.add(p2LandPanel);
        landPanel.add(p3LandPanel);
        landPanel.add(p4LandPanel);
        landPanel.add(p5LandPanel);
        landPanel.add(p6LandPanel);
        landPanel.add(p7LandPanel);
        landPanel.add(p8LandPanel);

        p1land1ComboBox.addItem(-1);
        p1land2ComboBox.addItem(-1);
        p1land3ComboBox.addItem(-1);
        p2land1ComboBox.addItem(-1);
        p2land2ComboBox.addItem(-1);
        p2land3ComboBox.addItem(-1);
        p3land1ComboBox.addItem(-1);
        p3land2ComboBox.addItem(-1);
        p3land3ComboBox.addItem(-1);
        p4land1ComboBox.addItem(-1);
        p4land2ComboBox.addItem(-1);
        p4land3ComboBox.addItem(-1);
        p5land1ComboBox.addItem(-1);
        p5land2ComboBox.addItem(-1);
        p5land3ComboBox.addItem(-1);
        p6land1ComboBox.addItem(-1);
        p6land2ComboBox.addItem(-1);
        p6land3ComboBox.addItem(-1);
        p7land1ComboBox.addItem(-1);
        p7land2ComboBox.addItem(-1);
        p7land3ComboBox.addItem(-1);
        p8land1ComboBox.addItem(-1);
        p8land2ComboBox.addItem(-1);
        p8land3ComboBox.addItem(-1);

        p1land1ComboBox.addItem(1);
        p1land2ComboBox.addItem(1);
        p1land3ComboBox.addItem(1);
        p2land1ComboBox.addItem(1);
        p2land2ComboBox.addItem(1);
        p2land3ComboBox.addItem(1);
        p3land1ComboBox.addItem(1);
        p3land2ComboBox.addItem(1);
        p3land3ComboBox.addItem(1);
        p4land1ComboBox.addItem(1);
        p4land2ComboBox.addItem(1);
        p4land3ComboBox.addItem(1);
        p5land1ComboBox.addItem(1);
        p5land2ComboBox.addItem(1);
        p5land3ComboBox.addItem(1);
        p6land1ComboBox.addItem(1);
        p6land2ComboBox.addItem(1);
        p6land3ComboBox.addItem(1);
        p7land1ComboBox.addItem(1);
        p7land2ComboBox.addItem(1);
        p7land3ComboBox.addItem(1);
        p8land1ComboBox.addItem(1);
        p8land2ComboBox.addItem(1);
        p8land3ComboBox.addItem(1);

        p1land1ComboBox.addItem(2);
        p1land2ComboBox.addItem(2);
        p1land3ComboBox.addItem(2);
        p2land1ComboBox.addItem(2);
        p2land2ComboBox.addItem(2);
        p2land3ComboBox.addItem(2);
        p3land1ComboBox.addItem(2);
        p3land2ComboBox.addItem(2);
        p3land3ComboBox.addItem(2);
        p4land1ComboBox.addItem(2);
        p4land2ComboBox.addItem(2);
        p4land3ComboBox.addItem(2);
        p5land1ComboBox.addItem(2);
        p5land2ComboBox.addItem(2);
        p5land3ComboBox.addItem(2);
        p6land1ComboBox.addItem(2);
        p6land2ComboBox.addItem(2);
        p6land3ComboBox.addItem(2);
        p7land1ComboBox.addItem(2);
        p7land2ComboBox.addItem(2);
        p7land3ComboBox.addItem(2);
        p8land1ComboBox.addItem(2);
        p8land2ComboBox.addItem(2);
        p8land3ComboBox.addItem(2);

        if (playerNumber == 3) {
            p1land1ComboBox.addItem(3);
            p1land2ComboBox.addItem(3);
            p1land3ComboBox.addItem(3);
            p2land1ComboBox.addItem(3);
            p2land2ComboBox.addItem(3);
            p2land3ComboBox.addItem(3);
            p3land1ComboBox.addItem(3);
            p3land2ComboBox.addItem(3);
            p3land3ComboBox.addItem(3);
            p4land1ComboBox.addItem(3);
            p4land2ComboBox.addItem(3);
            p4land3ComboBox.addItem(3);
            p5land1ComboBox.addItem(3);
            p5land2ComboBox.addItem(3);
            p5land3ComboBox.addItem(3);
            p6land1ComboBox.addItem(3);
            p6land2ComboBox.addItem(3);
            p6land3ComboBox.addItem(3);
            p7land1ComboBox.addItem(3);
            p7land2ComboBox.addItem(3);
            p7land3ComboBox.addItem(3);
            p8land1ComboBox.addItem(3);
            p8land2ComboBox.addItem(3);
            p8land3ComboBox.addItem(3);
        } else if (playerNumber == 4){
            p1land1ComboBox.addItem(3);
            p1land2ComboBox.addItem(3);
            p1land3ComboBox.addItem(3);
            p2land1ComboBox.addItem(3);
            p2land2ComboBox.addItem(3);
            p2land3ComboBox.addItem(3);
            p3land1ComboBox.addItem(3);
            p3land2ComboBox.addItem(3);
            p3land3ComboBox.addItem(3);
            p4land1ComboBox.addItem(3);
            p4land2ComboBox.addItem(3);
            p4land3ComboBox.addItem(3);
            p5land1ComboBox.addItem(3);
            p5land2ComboBox.addItem(3);
            p5land3ComboBox.addItem(3);
            p6land1ComboBox.addItem(3);
            p6land2ComboBox.addItem(3);
            p6land3ComboBox.addItem(3);
            p7land1ComboBox.addItem(3);
            p7land2ComboBox.addItem(3);
            p7land3ComboBox.addItem(3);
            p8land1ComboBox.addItem(3);
            p8land2ComboBox.addItem(3);
            p8land3ComboBox.addItem(3);

            p1land1ComboBox.addItem(4);
            p1land2ComboBox.addItem(4);
            p1land3ComboBox.addItem(4);
            p2land1ComboBox.addItem(4);
            p2land2ComboBox.addItem(4);
            p2land3ComboBox.addItem(4);
            p3land1ComboBox.addItem(4);
            p3land2ComboBox.addItem(4);
            p3land3ComboBox.addItem(4);
            p4land1ComboBox.addItem(4);
            p4land2ComboBox.addItem(4);
            p4land3ComboBox.addItem(4);
            p5land1ComboBox.addItem(4);
            p5land2ComboBox.addItem(4);
            p5land3ComboBox.addItem(4);
            p6land1ComboBox.addItem(4);
            p6land2ComboBox.addItem(4);
            p6land3ComboBox.addItem(4);
            p7land1ComboBox.addItem(4);
            p7land2ComboBox.addItem(4);
            p7land3ComboBox.addItem(4);
            p8land1ComboBox.addItem(4);
            p8land2ComboBox.addItem(4);
            p8land3ComboBox.addItem(4);
        }

        p1land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(1, selectedPlayer - 1);
                } else {
                    control.editOwner(1, -1);
                }
            }
        });
        p1land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(2, selectedPlayer - 1);
                } else {
                    control.editOwner(2, -1);
                }
            }
        });
        p1land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(3, selectedPlayer - 1);
                } else {
                    control.editOwner(3, -1);
                }
            }
        });
        p2land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(5, selectedPlayer - 1);
                } else {
                    control.editOwner(5, -1);
                }
            }
        });
        p2land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(6, selectedPlayer - 1);
                } else {
                    control.editOwner(6, -1);
                }
            }
        });
        p2land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(7, selectedPlayer - 1);
                } else {
                    control.editOwner(7, -1);
                }
            }
        });
        p3land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(9, selectedPlayer - 1);
                } else {
                    control.editOwner(9, -1);
                }
            }
        });
        p3land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(10, selectedPlayer - 1);
                } else {
                    control.editOwner(10, -1);
                }
            }
        });
        p3land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(11, selectedPlayer - 1);
                } else {
                    control.editOwner(11, -1);
                }
            }
        });
        p4land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(13, selectedPlayer - 1);
                } else {
                    control.editOwner(13, -1);
                }
            }
        });
        p4land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(14, selectedPlayer - 1);
                } else {
                    control.editOwner(14, -1);
                }
            }
        });
        p4land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(15, selectedPlayer - 1);
                } else {
                    control.editOwner(15, -1);
                }
            }
        });
        p5land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(17, selectedPlayer - 1);
                } else {
                    control.editOwner(17, -1);
                }
            }
        });
        p5land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(18, selectedPlayer - 1);
                } else {
                    control.editOwner(18, -1);
                }
            }
        });
        p5land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(19, selectedPlayer - 1);
                } else {
                    control.editOwner(19, -1);
                }
            }
        });
        p6land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(21, selectedPlayer - 1);
                } else {
                    control.editOwner(21, -1);
                }
            }
        });
        p6land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(22, selectedPlayer - 1);
                } else {
                    control.editOwner(22, -1);
                }
            }
        });
        p6land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(23, selectedPlayer - 1);
                } else {
                    control.editOwner(23, -1);
                }
            }
        });
        p7land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(25, selectedPlayer - 1);
                } else {
                    control.editOwner(25, -1);
                }
            }
        });
        p7land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(26, selectedPlayer - 1);
                } else {
                    control.editOwner(26, -1);
                }
            }
        });
        p7land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(27, selectedPlayer - 1);
                } else {
                    control.editOwner(27, -1);
                }
            }
        });
        p8land1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(29, selectedPlayer - 1);
                } else {
                    control.editOwner(29, -1);
                }
            }
        });
        p8land2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(30, selectedPlayer - 1);
                } else {
                    control.editOwner(30, -1);
                }
            }
        });
        p8land3ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<Integer> comboBox = (JComboBox<Integer>) e.getSource();
                int selectedPlayer = (int) comboBox.getSelectedItem();
                if (selectedPlayer != -1) {
                    control.editOwner(31, selectedPlayer - 1);
                } else {
                    control.editOwner(31, -1);
                }
            }
        });

        editorMainPanel.add(roundControl);
        editorMainPanel.add(positionPanel);
        editorMainPanel.add(balancePanel);
        editorMainPanel.add(jailPanel);
        editorMainPanel.add(landPanel);
    }

    public void showTradeOption(int player, int ownership){
        int owner = ownership + 1;
        int player_display = player + 1;
        int result = JOptionPane.showConfirmDialog(null,
                "The Owner of this land is Player " + owner + " \n" +
                        "Do you want to trade for it? \n" +
                        "You have chance to negotiate with Player " + owner,
                "Do you want to trade this land?", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            int price = 0;
            String input;
            do {
                input =  JOptionPane.showInputDialog("Enter your ideal price");
                if (input == null) {
                    break;
                } else {
                    if (input.isEmpty()) {
                        try {
                            // Attempt to parse the input as an integer
                            price = Integer.parseInt(input);

                            int ownerresult = JOptionPane.showConfirmDialog(null,
                                    "Player " + player_display + " Offer you $" + price + " to buy your land \n" +
                                            "Do you accept it?",
                                    "Do you want to trade this land?", JOptionPane.YES_NO_OPTION);
                            if (ownerresult == JOptionPane.YES_OPTION) {
                                control.tradeLand(price, player, ownership);
                            } else {
                                JOptionPane.showMessageDialog(null, "You ignore the offer");
                            }

                            // If parsing is successful, break the loop
                            break;
                        } catch (NumberFormatException e) {
                            // If parsing fails, show an error message and continue the loop
                            JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                        }
                    }
                }
            }while (true);

            if (input == null) {
                JOptionPane.getRootFrame().dispose();
            }

        } else {
            JOptionPane.showMessageDialog(null, "You ignore the chance");
        }
    }


    //Image insert
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(7, 7, Image.SCALE_DEFAULT));
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
        return (row * 9) + col;
    }
}
