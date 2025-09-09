import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


// The hotel system uses a passkey which identifies each hotel and access its services
public class login{
    public static void main(String args [])
    {
        //The main login frame
        JFrame frame =new JFrame("Login");
        JPanel panel=new JPanel();
        panel.setLayout(null);
        //The login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(loginLabel.getFont().deriveFont(22.0f)); // To change the font size of the login label
        loginLabel.setBounds(230, 20, 100, 50); // Set position and size of login label
       

        JLabel passwordLabel = new JLabel("Passkey:");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(18.0f)); // To change the font size of the password label
        passwordLabel.setBounds(40, 140, 200, 30); // Set position and size of password label
        passwordLabel.setForeground(Color.BLUE); // Set text color to blue
       
        panel.add(passwordLabel);
        
        panel.add(loginLabel);
        panel.setBounds(230, 250, 500, 200);
        panel.setBackground(Color.LIGHT_GRAY);
       
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //creating the password field to enter the passkey
        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(130, 140, 200, 30);
        panel.add(passwordInput);

        
      


        JButton loginButton = new JButton("Login");//The login button
        loginButton.setBounds(340, 140, 100, 30);
        loginButton.setForeground(Color.red);
        loginButton.setFocusable(false);
        panel.add(loginButton);
        
        loginButton.addActionListener(e->{
            String passkey="admin123";
        String userInput=passwordInput.getText();
            if(userInput.equals(passkey)){
                frame.dispose();
                dashboard.main(new String[]{});
            }
            else{
                passwordInput.setText("");
            }

       });

       //Setting up the frame and adding the panel to the frame
        frame.setSize(1000, 1000);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.getContentPane().setBackground(new Color(0, 0, 128));
        frame.setLayout(null);




    }

    
}