import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUp {

    JFrame frame;
    JPanel panel;
    JLabel jlWelcome,jlWelcome1,jlText;
    JButton btnLogin,btnSignIn,btnExit;

    StartUp(){
        frame = new JFrame("Start Up Page");
        frame.setSize(450,450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        jlWelcome = new JLabel("Welcome to Danston");
        jlWelcome.setFont(new Font("Arial",Font.BOLD,30));
        jlWelcome.setBounds(75,100,350,50);

        jlWelcome1 = new JLabel("Banking Application");
        jlWelcome1.setFont(new Font("Arial",Font.BOLD,30));
        jlWelcome1.setBounds(75,150,350,50);

        jlText = new JLabel("Is this your first time or are you Member? click the right Button");
        jlText.setFont(new Font("Arial",Font.PLAIN,12));
        jlText.setBounds(60,200,350,50);

        btnLogin = new JButton("Log In");
        btnLogin.setFocusable(false);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Login logIn = new Login();
            }
        });

        btnSignIn = new JButton("Sign In");
        btnSignIn.setFocusable(false);
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignIn signIn = new SignIn();
                User user = signIn.user;
            }
        });

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel = new JPanel();
        panel.setBounds(75,250,300,25);
        panel.setLayout(new GridLayout(1,3,5,5));
        panel.add(btnLogin);
        panel.add(btnSignIn);
        panel.add(btnExit);

        frame.add(jlWelcome);
        frame.add(jlWelcome1);
        frame.add(jlText);
        frame.add(panel);
        frame.setVisible(true);

    }

}