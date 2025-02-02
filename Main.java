/*
Haley Gray
05/26/2024
Purpose: creates the game
*/
import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        //make new game obj
        TicTacToe game1 = new TicTacToe();

        //make main gui JFrame
        JFrame gui1 = new JFrame("TicTacToe");
        gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui1.setLayout(new BorderLayout()); //Main layout
        gui1.setSize(400,450);//size of window

        //panel for board --in center of JFrame
        JPanel board = new JPanel(new GridLayout(3,3));
        board.setPreferredSize(new Dimension(350, 350));
        //make 3x3 grid as buttons
        for(int i = 0; i < 9; ++i) {
            JButton boardButton = new JButton("");
            board.add(boardButton);
        }
        //panel for NEW GAME & EXIT -- bottom of JFrame
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setPreferredSize(new Dimension(200, 30));
        JButton newGame = new JButton("NewGame");
        JButton exitGame = new JButton("  Exit  ");
        controlPanel.add(newGame);
        controlPanel.add(exitGame);


        //make wrapper panel to restrict size of the game --encase (board & control panels)
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); //board wrapper
        JPanel wrapperPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER)); //control button wrapper
        wrapperPanel.add(board);
        wrapperPanel2.add(controlPanel);
        gui1.add(wrapperPanel, BorderLayout.CENTER);
        gui1.add(wrapperPanel2, BorderLayout.SOUTH);
        gui1.pack(); //enforce board & control panel are the correct size

        gui1.setVisible(true);

        //play the game
		//game.runTests();
        //game.play();


    }
}
