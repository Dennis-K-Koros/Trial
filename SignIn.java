import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignIn extends Component {

    JFrame frame;
    JPanel panel;
    JTextField tfName,tfEmail,tfAddress,tfPhone;
    JPasswordField pfPassword,pfConfirm;
    JLabel jlName,jlEmail,jlAddress,jlPhone,jlPassword,jlConfirm;
    JButton btnOk,btnCancel,btnClear;
    Font myFont = new Font("Arial",Font.PLAIN,16);

    SignIn(){
        frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,450);
        frame.setLayout(null);

        jlName = new JLabel("UserName");
        jlName.setBounds(50,50,150,25);
        jlName.setFont(myFont);

        jlEmail = new JLabel("Email Address");
        jlEmail.setBounds(50,100,150,25);
        jlEmail.setFont(myFont);

        jlAddress = new JLabel("Address");
        jlAddress.setBounds(50,150,150,25);
        jlAddress.setFont(myFont);

        jlPhone = new JLabel("Phone Number");
        jlPhone.setBounds(50,200,150,25);
        jlPhone.setFont(myFont);

        jlPassword = new JLabel("Password");
        jlPassword.setBounds(50,250,150,25);
        jlPassword.setFont(myFont);

        jlConfirm = new JLabel("Confirm Password");
        jlConfirm.setBounds(50,300,150,25);
        jlConfirm.setFont(myFont);

        tfName = new JTextField();
        tfName.setBounds(200,50,150,25);
        tfName.setFont(myFont);

        tfEmail = new JTextField();
        tfEmail.setBounds(200,100,150,25);
        tfEmail.setFont(myFont);

        tfAddress = new JTextField();
        tfAddress.setBounds(200,150,150,25);
        tfAddress.setFont(myFont);

        tfPhone = new JTextField();
        tfPhone.setBounds(200,200,150,25);
        tfPhone.setFont(myFont);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(200,250,150,25);

        pfConfirm = new JPasswordField();
        pfConfirm.setBounds(200,300,150,25);

        btnOk = new JButton("Ok");
        btnOk.setFont(myFont);
        btnOk.setFocusable(false);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
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
                tfAddress.setText("");
                tfPhone.setText("");
                pfPassword.setText("");
                pfConfirm.setText("");
            }
        });



        panel = new JPanel();
        panel.setBounds(75,350,300,25);
        panel.setLayout(new GridLayout(1,2,10,10));
        panel.add(btnOk);
        panel.add(btnClear);
        panel.add(btnCancel);

        frame.add(jlName);
        frame.add(jlEmail);
        frame.add(jlAddress);
        frame.add(jlPhone);
        frame.add(jlPassword);
        frame.add(jlConfirm);
        frame.add(tfName);
        frame.add(tfAddress);
        frame.add(tfEmail);
        frame.add(tfPhone);
        frame.add(pfPassword);
        frame.add(pfConfirm);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirm = String.valueOf(pfConfirm.getPassword());

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || password.isEmpty() || confirm.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please Enter All Fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!password.equals(confirm)){
            JOptionPane.showMessageDialog(this,
                    "Please Rewrite The Confirm Password",
                    "Invalid Password",
                    JOptionPane.ERROR_MESSAGE);
        }

        user = addUserToDatabase(name,email,address,phone,password,confirm);

    }

    public User user;

    private User addUserToDatabase(String name, String email, String address,String phone, String password,String confirm) {
        User user = null;
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/school";
        final String USER = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Students(name,emailAddress,address,phone,password,confirmPassword)"+
                    "VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,phone);
            preparedStatement.setString(5,password);
            preparedStatement.setString(6,confirm);


            int addedRows = preparedStatement.executeUpdate();
            if (addedRows>0){
                user = new User();
                user.name = name;
                user.emailAddress = email;
                user.address = address;
                user.phone = phone;
                user.password = password;
                user.confirm = confirm;
            }

            stmt.close();
            conn.close();

        } catch(SQLException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        } catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }


}
