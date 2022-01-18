import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test extends JFrame{
    
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel = new JPanel();

    private GridLayout gridLayout = new GridLayout(0, 1); 
    private int widthSize  = 800; 
    private int heightSize = 600; 
    private int length = 120;
    private int recursiveNumber = 5;
    private boolean sequentialMode = true; // true for sequential and false for parallel
    private JButton setSizeButton = new JButton("Set width, height, len & num");
    private JTextField widthText = new JTextField("800",10);
    private JTextField heightText = new JTextField("600",10);
    private JTextField lengthText = new JTextField("120",10);
    private JTextField recursiveNumberText = new JTextField("5",10);
    private JRadioButton sequentialRadioButton = new JRadioButton("Sequential", true);
    private JRadioButton parallelRadioButton = new JRadioButton("Parallel", true);
    private ButtonGroup radioGroup = new ButtonGroup();
    private JButton showBoardButton = new JButton("Show the board");
    private JTextField text1 = new JTextField("Choose sequential or parallel: ");
    private JTextField text2 = new JTextField("Enter width of board");
    private JTextField text3 = new JTextField("Enter height of board(left below)");
    private JTextField text4 = new JTextField("Enter initial length of line");
    private JTextField text5 = new JTextField("Enter number of recursive calls");

    public static void main(String args[]){
        Test app = new Test();
        app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        app.setSize( 880, 600 ); // set frame size
        app.setLocationRelativeTo(null);
        app.setBackground(Color.GREEN);
        app.setVisible( true );
    }   //End of main

    public Test(){
        super("Project by Suleyman Golbol!!"); // Title.
        
        setSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String message = "";
                try{
                    message = String.format("Entered size is %s x %s \nInitial length: %s \nRecursive calls: %s", 
                                            widthText.getText(), heightText.getText(), lengthText.getText(), recursiveNumberText.getText() );
                    widthSize = Integer.parseInt(widthText.getText());
                    if (widthSize <= 1) {
                        message = "Error! Please enter size something bigger than 1\n";
                        widthSize = 0; // Making boardSize something not in range.
                    }

                    heightSize = Integer.parseInt(heightText.getText());
                    if (heightSize <= 1) {
                        message = "Error! Please enter board size something bigger than 1\n";
                        heightSize = 0; // Making boardSize something not in range.
                    }
                    length = Integer.parseInt(lengthText.getText());
                    if(length < 1 || length > 500){
                        message = "Error! Please enter length properly between 1-500\n";
                        widthSize = 0;
                    }
                    recursiveNumber = Integer.parseInt(recursiveNumberText.getText());
                    if(recursiveNumber < 0 || recursiveNumber > 20){
                        message = "Error! Please enter recursive number properly between 0-20\n";
                        widthSize = 0;
                    }
                    JOptionPane.showMessageDialog(null, message);
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
        text4.setEditable( false );
        text5.setEditable( false );
        
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

        //Just to display 2 radio buttons in the same row; creating a temporary panel and a box layout on x axis.
        JPanel radiobuttonpanel = new JPanel();
        BoxLayout radiobuttonpanellayout = new BoxLayout(radiobuttonpanel, BoxLayout.X_AXIS);
        radiobuttonpanel.setLayout(radiobuttonpanellayout);
        radiobuttonpanel.add(sequentialRadioButton); radiobuttonpanel.add(parallelRadioButton);
        mainPanel.add(radiobuttonpanel);

        // mainPanel.add(sequentialRadioButton);
        // mainPanel.add(parallelRadioButton); // Adding buttons to panel.

        mainPanel.add(text2);
        mainPanel.add(widthText);
        
        mainPanel.add(text3);
        mainPanel.add(heightText);
        
        mainPanel.add(text4);
        mainPanel.add(lengthText);
        mainPanel.add(text5);
        mainPanel.add(recursiveNumberText);
        
        mainPanel.add(setSizeButton);
        mainPanel.add(showBoardButton);

        showBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                if (widthSize <= 1 || heightSize <= 1) {
                    JOptionPane.showMessageDialog(null, "Error! Please enter board size something bigger than 1\n");
                    return;
                }
                
                Board board = new Board(sequentialMode, length, recursiveNumber);

                board.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                board.setSize( widthSize, heightSize ); // set frame size
                board.setLocationRelativeTo(null);
                board.setBackground(new Color(251, 195, 134));
                dispose(); //Closing main window and opening board window
                board.setVisible( true );
                
            }
        });

        add(mainPanel);   //Adding panel to frame.
    }
}   //End of class