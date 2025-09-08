package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
//import models.Booking;

public class BookController {

   /* @FXML private TableView<Booking> bookingTable;
    @FXML private TableColumn<Booking, Integer> colBookingId;
    @FXML private TableColumn<Booking, String> colGuest;
    @FXML private TableColumn<Booking, Integer> colRoom;
    @FXML private TableColumn<Booking, String> colCheckIn;
    @FXML private TableColumn<Booking, String> colCheckOut;
    @FXML private TableColumn<Booking, String> colStatus;

    @FXML
    public void initialize() {
        // Booking DB
        // Load bookings from DB
    }*/

    @FXML
    private void handleNewBooking() {
        //Navigate to newBooking.fxml
    }

    @FXML
    private void handleExtendBooking() {
       // Booking selected = bookingTable.getSelectionModel().getSelectedItem();
      //  if (selected != null) {
            //Open extension dialog
      //  } else {
      //      showAlert("Select a booking to extend.");
      //  }
    }

    @FXML
    private void handleCancelBooking() {
       // Booking selected = bookingTable.getSelectionModel().getSelectedItem();
      //  if (selected != null) {
            //Cancel booking in DB and refresh table
      //  } else {
       //     showAlert("Select a booking to cancel.");
       // }
    }

    @FXML
    private void goBack() {
        // Navigate back to dashboard
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
