import com.sun.net.httpserver.HttpServer;
import controller.UserController;
import service.UserService;
import dao.UserDAO;
import util.Utils;

import java.net.InetSocketAddress;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try(Connection conn = Utils.getConnection()) {
            // Run migrations
            System.out.println("Running migrations...");
            util.MigrationRunner.runMigrations(conn);
            System.out.println("Migrations done!");

            // Create services & controllers
            UserService userService = new UserService(new UserDAO());
            UserController userController = new UserController(userService,conn);

            // Start HTTP server
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            userController.registerRoutes(server);
            server.setExecutor(null);
            server.start();
            System.out.println("Server started on port 8000");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

