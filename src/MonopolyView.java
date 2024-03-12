import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyView extends JFrame {
    JButton start_btn, exit_btn;
    JPanel startPanel, playerNumberPanel, boardPanel, mainPanel, jLeft, jRight, jTop, jBottom, player1_infoPanel, player2_infoPanel, player3_infoPanel, player4_infoPanel, playerStandingPanel;
    JLabel playerNumberLabel;
    JTextField playerNumber;
    private MonopolyController control;

    public void setController(MonopolyController cntl) {
        this.control = cntl;
    }

    public MonopolyView(){
        super("HSUHK COM3101 Project");
        setLayout(new BorderLayout());
        setBounds(200, 50, 350, 150);
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


        //Monopoly Board Panel
        boardPanel = new JPanel(new GridLayout(11, 11,5,5));
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {

                JPanel cell = new JPanel(new BorderLayout());
                cell.setSize(70, 70);

                if (row == 0 || row == 10 || col == 0 || col == 10) {
                    if(row == 0 && col == 0){
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        cell.add(color,BorderLayout.NORTH);
                        JLabel text = new JLabel("Start");
                        text.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.add(text);
                    } else if (row == 0 && col == 1 || row == 0 && col == 2 || row == 0 && col ==3){
                        JPanel color = new JPanel();
                        color.setBackground(new Color (0,171,78));
                        cell.add(color,BorderLayout.NORTH);
                        if (col == 1){
                            JLabel text = new JLabel("<html>Kwun<br>Tong</html>");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 2){
                            JLabel text = new JLabel("<html>Choi<br>Hung</html>");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 3){
                            JLabel text = new JLabel("Lok Fu");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        }
                    } else if(row == 0 && col == 4){
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        cell.add(color,BorderLayout.NORTH);
                        JLabel text = new JLabel("Chance");
                        text.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.add(text);
                    } else if(row == 0 && col == 5 || row == 0 && col == 6 || row == 0 && col == 7){
                        JPanel color = new JPanel();
                        color.setBackground(new Color (49,29,62));
                        cell.add(color,BorderLayout.NORTH);
                        if (col == 5){
                            JLabel text = new JLabel("<html>Tsueng<br>Kwan O</html>");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 6){
                            JLabel text = new JLabel("Hang Hau");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        } else if (col == 7){
                            JLabel text = new JLabel("Po Lam");
                            text.setHorizontalAlignment(SwingConstants.CENTER);
                            cell.add(text);
                        }
                    }else if(row == 0 && col == 10){ //top row last col
                        JPanel color = new JPanel();
                        color.setBackground(Color.white);
                        cell.add(color,BorderLayout.NORTH);
                        JLabel text = new JLabel("<html>Get<br>$2000</html>");
                        text.setHorizontalAlignment(SwingConstants.CENTER);
                        cell.add(text);
                    }
                    cell.setBackground(new Color(169, 207, 255));

                    playerStandingPanel = new JPanel(new FlowLayout());
                    playerStandingPanel.setBackground(Color.gray);
                    cell.add(playerStandingPanel, BorderLayout.SOUTH);

                    //image testing
                    ImageIcon picture = createImageIcon("images/black.png");
                    JLabel picLbl = new JLabel(picture);
                    playerStandingPanel.add(picLbl);

                    boardPanel.add(cell);
                } else if (row == 5 && col == 5){ //middle grid - back to home page
                    JButton back_btn = new JButton("Back");
                    back_btn.addActionListener(e -> control.modelMainMenu());
                    cell.add(back_btn);
                    boardPanel.add(cell);
                } else {
                    boardPanel.add(cell);
                }
            }
        }

        setVisible(true);
    }
    public void game_start(){
        this.setBounds(100, 50, 1100, 850);
        mainPanel.remove(startPanel);
        mainPanel.remove(playerNumberPanel);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

        //Adding Blank Space

        jLeft = new JPanel();
        mainPanel.add(jLeft, "West");
        jLeft.setPreferredSize(new Dimension(40, 480));

        jRight = new JPanel();
        mainPanel.add(jRight, "East");
        jRight.setPreferredSize(new Dimension(200, 240));
        jRight.setLayout(new BoxLayout(jRight, BoxLayout.Y_AXIS));

        //Player Info
        player1_infoPanel = new JPanel();
        jRight.add(player1_infoPanel);
        player1_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
        player2_infoPanel = new JPanel();
        jRight.add(player2_infoPanel);
        player2_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 2"));
        player3_infoPanel = new JPanel();
        jRight.add(player3_infoPanel);
        player3_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 3"));
        player4_infoPanel = new JPanel();
        jRight.add(player4_infoPanel);
        player4_infoPanel.setBorder(BorderFactory.createTitledBorder("Player 4"));



        jTop = new JPanel();
        mainPanel.add(jTop, "North");
        jTop.setPreferredSize(new Dimension(640, 40));

        jBottom = new JPanel();
        mainPanel.add(jBottom, "South");
        jBottom.setPreferredSize(new Dimension(640, 40));
    }

    public void mainMenu(){
        this.setBounds(200, 50, 350, 150);
        mainPanel.remove(boardPanel);
        mainPanel.remove(jLeft);
        mainPanel.remove(jRight);
        mainPanel.remove(jTop);
        mainPanel.remove(jBottom);
        mainPanel.add(startPanel, BorderLayout.NORTH);
        mainPanel.add(playerNumberPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
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
}
