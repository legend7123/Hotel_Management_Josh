package roomframes;
import javax.swing.*;
import java.awt.*;

public class RoomBooking extends JFrame {

private JTextField guestNameField, roomNumberField, checkInField, checkOutField;

    public RoomBooking() {
        setTitle("New Room Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800,900);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        formPanel.add(new JLabel("Guest Name:"));
        guestNameField = new JTextField();
        formPanel.add(guestNameField);

        formPanel.add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        formPanel.add(roomNumberField);

        formPanel.add(new JLabel("Check-In Date:"));
        checkInField = new JTextField("YYYY-MM-DD");
        formPanel.add(checkInField);

        formPanel.add(new JLabel("Check-Out Date:"));
        checkOutField = new JTextField("YYYY-MM-DD");
        formPanel.add(checkOutField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton payButton = new JButton("Confirm & Pay");
        payButton.addActionListener(e -> {
            dispose();
            //Redirects to Payment
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            new BookingFrame().setVisible(true);
        });

        buttonPanel.add(payButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomBooking().setVisible(true));
    }
}
