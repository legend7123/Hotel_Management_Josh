package roomframes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomStatus extends JFrame {

    private JTable roomTable;

    public RoomStatus() {
        setTitle("Room Status - Today");
        setSize(1800, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Connect to DB, get room id, room no, type, status from bookings db
        String[] headers = {"Room No", "Type", "Price", "Status"};
        Object[][] rooms = {
                {101, "Single", 1500, "Available"},
                {102, "Double", 3000, "Booked"},
                {103, "Suite", 4500, "Under Maintenance"},
                {104, "Single", 1500, "Booked"},
                {105, "Double", 3000, "Available"}
        };

        DefaultTableModel model = new DefaultTableModel(rooms, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        roomTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(roomTable);

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            // Connect to Dashboard -- Joseph!!
        });

        JButton newBookingButton = new JButton("New Booking");
        newBookingButton.addActionListener(e -> {
            dispose();
            new RoomBooking().setVisible(true);
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(newBookingButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoomStatus().setVisible(true);
        });
    }
}