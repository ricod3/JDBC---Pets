import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLconnection {

    static Connection dbConnection() {
        Connection mysqlConnection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mysqlConnection = DriverManager.getConnection("HOST","USER","PASSWORD");

            if (mysqlConnection != null) {
                System.out.println("*** Connection successfull ***");
            }
        } catch (Exception e) {
            System.out.println("!!! Connection failed !!!");
        }
        return mysqlConnection;
    }
}
