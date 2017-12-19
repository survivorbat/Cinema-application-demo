package Bioscoop.api.bioscoop.Locatie;

import org.junit.Test;

import static org.junit.Assert.*;

public class APILocatiesControllerTest {
    @Test
    public void locaties() throws Exception {
        APILocatiesController locaties = new APILocatiesController();
        locaties.insertLocatie("TESTBIOS","s",0,"s");
        assertTrue(locaties.locaties().contains(new Locatie("TESTBIOS","s",0,"s")));
        locaties.deleteLocatie("TESTBIOS");
    }

    @Test
    public void insertLocatie() throws Exception {
        APILocatiesController locaties = new APILocatiesController();
        locaties.insertLocatie("TESTBIOS","s",0,"s");
        assertTrue(locaties.locaties().contains(new Locatie("TESTBIOS","s",0,"s")));
        locaties.deleteLocatie("TESTBIOS");
    }

    @Test
    public void getLocatie() throws Exception {
        APILocatiesController locaties = new APILocatiesController();
        locaties.insertLocatie("TESTBIOS","s",0,"s");
        assertTrue(locaties.getLocatie("TESTBIOS").equals(new Locatie("TESTBIOS","s",0,"s")));
        locaties.deleteLocatie("TESTBIOS");
    }

    @Test
    public void deleteLocatie() throws Exception {
        APILocatiesController locaties = new APILocatiesController();
        locaties.insertLocatie("TESTBIOS","s",0,"s");
        locaties.deleteLocatie("TESTBIOS");
        assertFalse(locaties.locaties().contains(new Locatie("TESTBIOS","s",0,"s")));
    }

    @Test
    public void updateLocatie() throws Exception {
    }

}