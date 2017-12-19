package Bioscoop.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBTest {
    @Test(expected = NullPointerException.class)
    public void close() throws Exception {
        DB db = new DB();
        db.close();
        db.stmt.executeQuery("SELECT * FROM Film");
    }
}