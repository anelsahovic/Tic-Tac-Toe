package tictactoe;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

public class Database {

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tictactoe", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void migrate() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Configure Flyway
            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:mysql://127.0.0.1:3306/tictactoe", "root", "password")
                    .load();

            // Log the current Flyway info
            System.out.println("Flyway info: " + Arrays.toString(flyway.info().all()));

            // Start the migration
            flyway.migrate();
            System.out.println("Flyway migration completed successfully.");
        } catch (FlywayException e) {
            System.err.println("FlywayException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
