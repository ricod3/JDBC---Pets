/*
 *  Downloaded mysql-connector-java-8.0.28 and implemented into module JDBC---Pets
 *  as new project library
 *  Database is a MySQL Cloud Database, hosted by aiven.io / DigitalOcean server
 *  in Frankfurt, Germany
 */

import javax.naming.InvalidNameException;
import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        Connection mysqlConn = MySQLconnection.dbConnection();

        Management management = new Management(mysqlConn, sc);
        try {
            Management.startHousehold(running, sc, management);
        } catch (SQLException | InvalidNameException e) {
            throw new RuntimeException(e);
        }
    }
}
