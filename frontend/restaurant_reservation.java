import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class restaurant_reservation {
    public static void main(String args[]){
  
            JFrame frame = new JFrame("Restaurant Reservation");
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(null);
            frame.setResizable(false);

            JLabel label = new JLabel("Restaurant Reservation ");
            label.setBounds(150, 30, 300, 30);
            frame.add(label);
            label.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel panel = new JPanel();
            panel.setBounds(37, 90, 500, 430);
            panel.setLayout(null);
            panel.setBackground(new Color(220, 220, 220));
            frame.add(panel);

            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setBounds(50, 50, 100, 30);
            panel.add(nameLabel);
            JTextField nameField = new JTextField();
            nameField.setBounds(150, 50, 200, 30);
            panel.add(nameField);
            JLabel dateLabel = new JLabel("Date (DD/MM/YYYY):");
            dateLabel.setBounds(50, 100, 150, 30);
            panel.add(dateLabel);
            JTextField dateField = new JTextField();
            dateField.setBounds(200, 100, 150, 30);
            panel.add(dateField);
            JLabel timeLabel = new JLabel("Time (HH:MM):");
            timeLabel.setBounds(50, 150, 100, 30);
            panel.add(timeLabel);
            JTextField timeField = new JTextField();
            timeField.setBounds(150, 150, 200, 30);
            panel.add(timeField);
            JLabel guestsLabel = new JLabel("Number of Guests:");
            guestsLabel.setBounds(50, 200, 150, 30);
            panel.add(guestsLabel);
            JTextField guestsField = new JTextField();
            guestsField.setBounds(200, 200, 150, 30);
            panel.add(guestsField);
            JButton submitButton = new JButton("Submit");
            submitButton.setBounds(200, 300, 100, 30);
            panel.add(submitButton);
            submitButton.addActionListener(e -> {
                String name = nameField.getText();
                String date = dateField.getText();
                String time = timeField.getText();
                String guests = guestsField.getText();
               
                JOptionPane.showMessageDialog(frame, "Reservation made for " + name + " on " + date + " at " + time + " for " + guests + " guests.");
            });

            frame.setVisible(true);
       
    }
}

