package Bioscoop.api;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DB {
    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=Bioscoop;user=java;password=javajavajava;";
    public Connection con = null;
    public PreparedStatement stmt = null;
    static Logger logger = Logger.getLogger(DB.class);
    public DB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.fatal("Exception in de database klasse");
        }
    }
    public void close(){
        try {
            this.con.close();
            logger.info("Database sluiten");
        }
        catch (Exception e){
            System.out.println(e);
            logger.error("Error tijdens het sluiten van de database");
        }
    }
}