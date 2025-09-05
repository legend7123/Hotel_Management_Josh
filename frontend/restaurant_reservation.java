import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class restaurant_reservation {
    public static void main(String args[]){
        JFrame frame = new JFrame("Restaurant Reservation");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        JLabel label = new JLabel("Restaurant Reservation ");
        label.setBounds(150, 30, 300, 30);
        frame.add(label);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel = new JPanel();
        panel.setBounds(37, 90, 500, 430);
        panel.setLayout(null);
        panel.setBackground(new Color(220, 220, 220));
        frame.add(panel);


    }
}

       