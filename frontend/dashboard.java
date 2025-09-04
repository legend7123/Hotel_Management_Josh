import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
public class dashboard {
    public static void main(String args[]){
        JFrame frame = new JFrame("Dashboard");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel navbar = new JPanel();
        navbar.setBounds(0, 0, 1000, 100);
        navbar.setBackground(new Color(0, 0, 51));
        frame.add(navbar);
        navbar.setLayout(null);

        JLabel title = new JLabel("Hotel Management System");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(24.0f));
        title.setBounds(20, 20, 400, 50);
        navbar.add(title);

        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard");

        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setBounds(740, 20, 400, 50);
        welcomeLabel.setForeground(new Color(255, 255, 255));
        navbar.add(welcomeLabel);

        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 100, 200, 900);
        sidebar.setBackground(new Color(1, 29, 95));
        frame.add(sidebar);
        sidebar.setLayout(null);

    JButton btn1 = new JButton("Booking");
    btn1.setBounds(10, 50, 180, 100);
    btn1.setFont(btn1.getFont().deriveFont(16.0f));
    sidebar.add(btn1);
    btn1.setBackground(new Color(31, 173, 216));
    btn1.setFocusPainted(false);

    JButton btn2 = new JButton("<html>Restaurant <br> Reservation<html>");
    btn2.setBounds(10, 200, 180, 100);
    btn2.setFont(btn2.getFont().deriveFont(15.0f));
    sidebar.add(btn2);
    btn2.setBackground(new Color(0, 255, 0));
    btn2.setFocusPainted(false);

    JButton btn3 = new JButton("<html>Rooms <br> Availability<html>");
    btn3.setBounds(10, 350, 180, 100);
    btn3.setFont(btn3.getFont().deriveFont(15.0f));
    sidebar.add(btn3);
    btn3.setBackground(new Color(255, 165, 0));
    btn3.setFocusPainted(false);

    JButton btn4 = new JButton("Logout");
    btn4.setBounds(10, 500, 180, 100);
    btn4.setFont(btn4.getFont().deriveFont(16.0f));
    sidebar.add(btn4);
    btn4.setBackground(new Color(255, 0, 0));
    btn4.setFocusPainted(false);

    }

}
