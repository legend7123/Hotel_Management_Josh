import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookingFrame extends JFrame {

    private JTable bookingTable;
    private JButton btnBack, btnNewBooking, btnExtend, btnCancel;

    public BookingFrame() {
        setTitle("Bookings");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table Coloumns
        String[] columns = {"Booking ID", "Guest", "Room", "Check In", "Check Out", "Status"};
        Object data[][] = {{"XYZH001", "Sarath", 101, "2025-09-06", "2025-09-10", "Active"}, {"XYZH002", "Jonas", 202, "2025-09-07", "2025-09-09", "Cancelled"}}; // Dummy data for testing purposes -- Need to connect DB 
        //Bookinf ID = XYZH + table id (3 digits)
        //Guest name from user id and users table
        //Room number from room table

        DefaultTableModel model = new DefaultTableModel(data, columns) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
            }
        };

        bookingTable = new JTable(model);
        bookingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(bookingTable), BorderLayout.CENTER);


        // Buttons for further actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnNewBooking = new JButton("New Booking");
        btnExtend = new JButton("Extend Booking");
        btnCancel = new JButton("Cancel Booking");

        buttonPanel.add(btnNewBooking);
        buttonPanel.add(btnExtend);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);

        // Back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("← Back to Dashboard");
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);


        // Button actions
        btnNewBooking.addActionListener(e -> newBooking());
        btnExtend.addActionListener(e -> handleExtendBooking());
        btnCancel.addActionListener(e -> handleCancelBooking());
        btnBack.addActionListener(e -> goBack());

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void newBooking() {
        dispose();
        //New Booking
    }
    private void handleExtendBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow >= 0) {
            JOptionPane.showMessageDialog(this, "Extend booking for: " + bookingTable.getValueAt(selectedRow, 1));
        } else {
            JOptionPane.showMessageDialog(this, "Select a booking to extend");
        }
    }

    private void handleCancelBooking() {
        int selectedRow = bookingTable.getSelectedRow();
        if (selectedRow >= 0) {
            bookingTable.setValueAt("Cancelled", selectedRow, 5);
            JOptionPane.showMessageDialog(this, "Booking cancelled.");
        } else {
            JOptionPane.showMessageDialog(this, "Select a booking to cancel");
        }
    }
    private void goBack() {
        dispose();
        //Back to Dashboard -- Jospeh!!
    }

    private void centeringDialog(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(this, "Info");
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookingFrame().setVisible(true));
    }
}