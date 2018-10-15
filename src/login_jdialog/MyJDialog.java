package login_jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyJDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    JTextField jTextField1 = new JTextField(13);
    JPasswordField jPasswordField1 = new JPasswordField(13);
    private String userName;
    private String passWord;
    private Connection con = null;

    public MyJDialog(JFrame parent, String title) {
        super(parent, title);

        setUndecorated(true);
        // set the position of the window

        // Create username
        
        JPanel box = new JPanel(new GridLayout(2, 2));
        box.setBackground(Color.GRAY);
        box.add(new JLabel("Username", SwingConstants.CENTER));
        box.add(jTextField1);
        box.add(new JLabel("Password", SwingConstants.CENTER));
        box.add(jPasswordField1);
        // get content pane, which is usually the
        // Container of all the dialog's components.
        //getContentPane().add(box);

        // Create a button
        //JPanel buttonPane = new JPanel();
        JButton button = new JButton("Login");
        //buttonPane.add(button);
        // set action listener on the button
        button.addActionListener(new MyActionListener());
        //getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        
        //add jpanel
        JPanel p1 = new JPanel();
        p1.add(box);
        p1.add(button, BorderLayout.PAGE_END);
        p1.setBackground(Color.GRAY);
        getContentPane().add(p1);
        
        pack();
    }

    class MyActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            /*userName = jTextField1.getText();
            passWord = new String(jPasswordField1.getPassword());
            getLogin();*/
            System.exit(0);
        }

    }

    public boolean getLogin() {
        con = Connect.ConnectDB();
        Boolean status = false;
        PreparedStatement pre = null;
        try {
            String sql = " SELECT * FROM  user "
                    + " WHERE id = ? "
                    + " AND pass = ? ";
            pre = con.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, passWord);

            ResultSet rec = pre.executeQuery();
            if (rec.next()) {
                status = true;
                System.out.println("succesful");
                //can switch to 
                System.exit(0);
            } else {
                System.out.println("failed");
                JOptionPane.showMessageDialog(rootPane, "Incorrect Username/Password", "warning", JOptionPane.WARNING_MESSAGE);
                jPasswordField1.setText("");
                jTextField1.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] a) {
        MyJDialog dialog = new MyJDialog(new JFrame(), "hello JCGs");
        // set the size of the window
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        dialog.setSize(320, 100);
        //dialog.setSize(500, 100);
        dialog.setLocationRelativeTo(null);

    }

}
