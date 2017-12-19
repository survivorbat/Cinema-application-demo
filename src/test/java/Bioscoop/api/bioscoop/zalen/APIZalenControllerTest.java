package Bioscoop.api.bioscoop.zalen;

import org.junit.Test;

import static org.junit.Assert.*;

public class APIZalenControllerTest {
    @Test
    public void zalen() throws Exception {
        APIZalenController zalen = new APIZalenController();
        zalen.deleteZaal("BOZ-000");
        zalen.insertZaal("BOZ-000",10,10,"BOZScoop");
        assertTrue(zalen.zalen().contains(new Zaal("BOZ-000",10,10,"BOZScoop")));
        zalen.deleteZaal("BOZ-000");
    }

    @Test
    public void insertZaal() throws Exception {
        APIZalenController zalen = new APIZalenController();
        zalen.deleteZaal("BOZ-000");
        zalen.insertZaal("BOZ-000",10,10,"BOZScoop");
        assertTrue(zalen.zalen().contains(new Zaal("BOZ-000",10,10,"BOZScoop")));
        zalen.deleteZaal("BOZ-000");
    }

    @Test
    public void getZaal() throws Exception {
        APIZalenController zalen = new APIZalenController();
        zalen.insertZaal("BOZ-000",10,10,"BOZScoop");
        assertTrue(zalen.getZaal("BOZ-000").equals(new Zaal("BOZ-000",10,10,"BOZScoop")));
        zalen.deleteZaal("BOZ-000");
    }

    @Test
    public void getZaalLocatie() throws Exception {
        APIZalenController zalen = new APIZalenController();
        zalen.insertZaal("BOZ-000",10,10,"BOZScoop");
        zalen.insertZaal("KIN-000",10,10,"Kinepolis");
        assertTrue(zalen.getZaalLocatie("BOZScoop").contains(new Zaal("BOZ-000",10,10,"BOZScoop")));
        assertFalse(zalen.getZaalLocatie("BOZScoop").contains(new Zaal("KIN-000",10,10,"Kinepolis")));
        zalen.deleteZaal("KIN-000");
        zalen.deleteZaal("BOZ-000");
    }

    @Test
    public void deleteZaal() throws Exception {
        APIZalenController zalen = new APIZalenController();
        zalen.deleteZaal("BOZ-000");
        zalen.insertZaal("BOZ-000",10,10,"BOZScoop");
        zalen.deleteZaal("BOZ-000");
        assertFalse(zalen.zalen().contains(new Zaal("BOZ-000",10,10,"BOZScoop")));
    }
}