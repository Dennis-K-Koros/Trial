import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    JFrame frame;
    JPanel panel;
    JLabel jlName,jlEmail,jlPassword;
    JTextField tfName,tfEmail;
    JPasswordField pfPassword;
    JButton btnLog, btnCancel,btnClear;
    Font myFont = new Font("Arial",Font.PLAIN,16);

    Login(){
        frame = new JFrame("Login");
        frame.setSize(420,420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        jlName = new JLabel("UserName");
        jlName.setBounds(50,100,150,25);
        jlName.setFont(myFont);

        jlEmail = new JLabel("Email Address");
        jlEmail.setBounds(50,150,150,25);
        jlEmail.setFont(myFont);

        jlPassword = new JLabel("Password");
        jlPassword.setBounds(50,200,150,25);
        jlPassword.setFont(myFont);

        tfName = new JTextField();
        tfName.setBounds(200,100,150,25);
        tfName.setFont(myFont);

        tfEmail = new JTextField();
        tfEmail.setBounds(200,150,150,25);
        tfEmail.setFont(myFont);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(200,200,150,25);
        pfPassword.setFont(myFont);

        btnLog = new JButton("Log In");
        btnLog.setFont(myFont);
        btnLog.setFocusable(false);
        btnLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = getAuthenticatedUser(name,email,password);
                if (user !=null){
                    frame.dispose();
                    WelcomePage welcomePage = new WelcomePage();
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Email Or Password Invalid",
                            "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(myFont);
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                StartUp startUp = new StartUp();
            }
        });

        btnClear = new JButton("Clear");
        btnClear.setFont(myFont);
        btnClear.setFocusable(false);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfEmail.setText("");
                pfPassword.setText("");
            }
        });

        panel = new JPanel();
        panel.setBounds(50,250,300,25);
        panel.setLayout(new GridLayout(1,2,10,10));
        panel.add(btnLog);
        panel.add(btnClear);
        panel.add(btnCancel);

        frame.add(jlName);
        frame.add(jlEmail);
        frame.add(jlPassword);
        frame.add(tfName);
        frame.add(tfEmail);
        frame.add(pfPassword);
        frame.add(panel);
        frame.setVisible(true);
    }

    public User user;

    private User getAuthenticatedUser(String name, String email, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/school";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM students WHERE name=? AND emailAddress=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.emailAddress=resultSet.getString("emailAddress");
                user.address= resultSet.getString("address");
                user.phone=resultSet.getString("phone");
            }

            stmt.close();
            conn.close();

        }catch (SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }


}
