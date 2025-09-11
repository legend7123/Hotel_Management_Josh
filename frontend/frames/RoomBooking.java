
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RoomBooking extends JFrame {

private JTextField guestNameField, roomNumberField, checkInField, checkOutField;

    public RoomBooking() {
        setTitle("New Room Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Booking Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));


        formPanel.add(new JLabel("Guest Name:"));
        guestNameField = new JTextField(30);
        guestNameField.setPreferredSize(new Dimension(120, 25));
        formPanel.add(guestNameField);

        formPanel.add(new JLabel("Room Number:"));
        String[] roomTypes = {"Single", "Double", "Suite", "Deluxe"};
        JComboBox<String> roomTypeDropdown = new JComboBox<>(roomTypes);
        roomTypeDropdown.setSelectedIndex(0);
        roomTypeDropdown.setPreferredSize(new Dimension(120, 25));
        formPanel.add(roomTypeDropdown);

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
            //Calculate bill
            //Payment class logic
            PaymentFrame.main(new String[]{});
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            BookingFrame.main(new String[]{});
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
