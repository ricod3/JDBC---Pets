import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        Connection mysqlConnection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mysqlConnection = DriverManager.getConnection("jdbc:mysql://mysql-3caa43ed-rico-88c5.a.aivencloud.com:22995/codersbay-pets","avnadmin","AVNS_EwJ2Wr9Qt13jJTX0x_9");

            if (mysqlConnection != null) {
                System.out.println("*** Connection successfull ***");
            }
            else {
                System.out.println("!!! Connection failed !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Testoutput");
    }
}
