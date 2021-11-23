import java.awt.Color;

import javax.swing.*;

public class Test {
    public static void main(String args[]){
        Drawer board = new Drawer();

        board.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        board.setSize( 850, 600 ); // set frame size
        board.setLocationRelativeTo(null);
        board.setBackground(Color.GREEN);
        board.setVisible( true );
        // board.setResizable(false); // Making not resizable

    }   //End of main
}   //End of class