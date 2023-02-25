import javax.swing.*;
import java.awt.*;

public class WelcomePage {

    JFrame frame;
    JLabel welcomeLabel;

    WelcomePage(){
        frame = new JFrame("Welcome Page");
        welcomeLabel = new JLabel();
        welcomeLabel.setBounds(100,150,275,35);
        welcomeLabel.setFont(new Font(null,Font.PLAIN,20));
        welcomeLabel.setText("Hello and Welcome");

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
