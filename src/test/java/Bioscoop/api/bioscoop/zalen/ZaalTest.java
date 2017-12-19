package Bioscoop.api.bioscoop.zalen;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZaalTest {
    @Test
    public void getZaalID() throws Exception {
        Zaal zaal = new Zaal("ZAAL-001",2,5,"BOZ");

        String result = zaal.getZaalID();

        assertEquals("ZAAL-001",result);
    }

    @Test
    public void getLocatie() throws Exception {
        Zaal zaal = new Zaal("ZAAL-001",2,5,"BOZ");

        String result = zaal.getLocatie();

        assertEquals("BOZ",result);
    }

    @Test
    public void getZitPlaatsen() throws Exception {
        Zaal zaal = new Zaal("ZAAL-001",5,5,"BOZ");

        int result = zaal.getZitPlaatsen();

        assertEquals(25,result);
    }

    @Test
    public void getRijen() throws Exception {
        Zaal zaal = new Zaal("ZAAL-001",2,5,"BOZ");

        int result = zaal.getRijen();

        assertEquals(2,result);
    }

    @Test
    public void getKolommen() throws Exception {
        Zaal zaal = new Zaal("ZAAL-001",2,5,"BOZ");

        int result = zaal.getKolommen();

        assertEquals(5,result);
    }

}