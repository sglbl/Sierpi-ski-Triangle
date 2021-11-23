import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends JFrame{
    
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel = new JPanel();

    private GridLayout gridLayout = new GridLayout(3, 2); 
    private int widthSize  = 800; 
    private int heightSize = 600; 
    private boolean sequentialMode = true; // true for sequential and false for parallel
    private JButton setSizeButton = new JButton("Set width and height");
    private JTextField widthText = new JTextField("800",10);
    private JTextField heightText = new JTextField("600",10);
    private JRadioButton sequentialRadioButton = new JRadioButton("Sequential", true);
    private JRadioButton parallelRadioButton = new JRadioButton("Parallel", true);
    private ButtonGroup radioGroup = new ButtonGroup();
    private JButton showBoardButton = new JButton("Show the board");
    private JTextField text1 = new JTextField("Choose sequential or parallel: ");
    private JTextField text2 = new JTextField("Enter width of board");
    private JTextField text3 = new JTextField("Enter height of board");


    public static void main(String args[]){
        Test app = new Test();

        app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        app.setSize( 800, 600 ); // set frame size
        app.setLocationRelativeTo(null);
        app.setBackground(Color.GREEN);
        app.setVisible( true );
        // board.setResizable(false); // Making not resizable

    }   //End of main

    public Test(){
        super("Project by Suleyman Golbol!!"); // Title.
        
        setSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String message = "";
                try{
                    message = String.format("Entered size is %s x %s", widthText.getText(), heightText.getText());
                    widthSize = Integer.parseInt(widthText.getText());
                    if (widthSize <= 1) {
                        message = "Error! Please enter size something bigger than 1\n";
                        widthSize = 0; // Making boardSize something not in range.
                    }
                    JOptionPane.showMessageDialog(null, message);

                    heightSize = Integer.parseInt(heightText.getText());
                    if (heightSize <= 1) {
                        message = "Error! Please enter board size something bigger than 1\n";
                        heightSize = 0; // Making boardSize something not in range.
                    }
                }
                catch(NumberFormatException n){
                    message = "Error! Please enter size something bigger than 1\n";
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });
        mainPanel.setLayout(gridLayout);   // Panel uses grid layout.

        // Asking input values like boardsize and how many player game.
        text1.setEditable( false );     //Question texts are non editable text.
        text2.setEditable( false );
        text3.setEditable( false );
        
        mainPanel.add(text1);
        mainPanel.add(sequentialRadioButton);   
        mainPanel.add(parallelRadioButton);   //Adding buttons to JFrame

        radioGroup.add( sequentialRadioButton );
        radioGroup.add( parallelRadioButton );
        //These will work when click on the radio buttons.
        sequentialRadioButton.addItemListener( new ItemListener(){
            public void itemStateChanged( ItemEvent e ){
                if(e.getSource() == sequentialRadioButton)
                    sequentialMode = true;
            }  
        });
        parallelRadioButton.addItemListener( new ItemListener(){
            public void itemStateChanged( ItemEvent e ){
                if(e.getSource() == parallelRadioButton) 
                    sequentialMode = false;
            }  
        });

        mainPanel.add(sequentialRadioButton);
        mainPanel.add(parallelRadioButton); // Adding buttons to panel.

        mainPanel.add(text2);
        mainPanel.add(widthText);
        mainPanel.add(setSizeButton);
        mainPanel.add(text3);
        mainPanel.add(heightText);
        mainPanel.add(showBoardButton);

        
        showBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                if (widthSize <= 1 || heightSize <= 1) {
                    JOptionPane.showMessageDialog(null, "Error! Please enter board size something bigger than 1\n");
                    return;
                }
                //addBoardToGame();
                BoardCreator board = new BoardCreator();

                board.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                board.setSize( widthSize, heightSize ); // set frame size
                board.setLocationRelativeTo(null);
                board.setBackground(Color.GREEN);
                board.setVisible( true );
                
            }
        });

        add(mainPanel);   //Adding panel to frame.
    }
}   //End of class