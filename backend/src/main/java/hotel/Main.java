import com.sun.net.httpserver.HttpServer;

import controller.UserController;
import controller.PaymentController;
import controller.RoomController;
import controller.RoomBookingController;
import controller.AmenityController;
import controller.AmenityBookingController;

import service.UserService;
import service.PaymentService;
import service.RoomService;
import service.RoomBookingService;
import service.AmenityService;
import service.AmenityBookingService;

import dao.AmenityBookingDAO;
import dao.AmenityDAO;
import dao.PaymentDAO;
import dao.RoomBookingDAO;
import dao.RoomDAO;
import dao.UserDAO;
import util.MigrationRunner;
import util.Utils;

import java.net.InetSocketAddress;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Utils.getConnection()) {

            // ✅ Run DB migrations
            System.out.println("Running migrations...");
            MigrationRunner.runMigrations(conn);
            System.out.println("Migrations done!");

            // ✅ Create DAO objects
            UserDAO userDAO = new UserDAO();
            PaymentDAO paymentDAO = new PaymentDAO();
            RoomDAO roomDAO = new RoomDAO();
            RoomBookingDAO roomBookingDAO = new RoomBookingDAO();
            AmenityDAO amenityDAO = new AmenityDAO();
            AmenityBookingDAO amenityBookingDAO = new AmenityBookingDAO();


            // ✅ Create Service objects
            UserService userService = new UserService(userDAO);
            PaymentService paymentService = new PaymentService(paymentDAO);
            RoomService roomService = new RoomService(roomDAO);
            RoomBookingService roomBookingService = new RoomBookingService(roomBookingDAO);
            AmenityService amenityService = new AmenityService(amenityDAO);
            AmenityBookingService amenityBookingService = new AmenityBookingService(amenityBookingDAO);

            // ✅ Create Controller objects
            UserController userController = new UserController(userService);
            PaymentController paymentController = new PaymentController(paymentService);
            RoomController roomController = new RoomController(roomService);
            RoomBookingController roomBookingController = new RoomBookingController(roomBookingService);
            AmenityController amenityController = new AmenityController(amenityService);
            AmenityBookingController amenityBookingController = new AmenityBookingController(amenityBookingService);

            // ✅ Start HTTP Server
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // ✅ Register routes
            userController.registerRoutes(server);
            paymentController.registerRoutes(server);
            roomController.registerRoutes(server);
            roomBookingController.registerRoutes(server);
            amenityController.registerRoutes(server);
            amenityBookingController.registerRoutes(server);

            server.setExecutor(null); // default executor
            server.start();

            System.out.println("✅ Server started on http://localhost:8080");
            System.out.println("➡ Available endpoints:");
            System.out.println("   GET/POST /users");
            System.out.println("   GET/PUT/DELETE /users/{id}");
            System.out.println("   GET/POST /payments");
            System.out.println("   GET/PUT/DELETE /payments/{id}");
            System.out.println("   GET/POST /rooms");
            System.out.println("   GET/PUT/DELETE /rooms/{id}");
            System.out.println("   POST /bookings");
            System.out.println("   GET/PUT/DELETE /bookings/{id}");
            System.out.println("   GET/POST /amenities");
            System.out.println("   GET/PUT/DELETE /amenities/{id}");
            System.out.println("   GET/POST /amenity-bookings");
            System.out.println("   GET/PUT/DELETE /amenity-bookings/{id}");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting server");
            System.exit(1);
        }
    }
}
