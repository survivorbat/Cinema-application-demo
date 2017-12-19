package Bioscoop.api.bioscoop.zalen;

import org.springframework.stereotype.Repository;

public class Zaal {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zaal zaal = (Zaal) o;

        if (rijen != zaal.rijen) return false;
        if (kolommen != zaal.kolommen) return false;
        if (!zaalID.equals(zaal.zaalID)) return false;
        return locatie.equals(zaal.locatie);
    }

    @Override
    public int hashCode() {
        int result = zaalID.hashCode();
        result = 31 * result + rijen;
        result = 31 * result + kolommen;
        result = 31 * result + locatie.hashCode();
        return result;
    }

    private String zaalID;
    private int rijen;
    private int kolommen;
    private String locatie;

    public Zaal(String zaalID, int rijen, int kolommen, String locatie) {
        this.zaalID = zaalID;
        this.rijen = rijen;
        this.kolommen = kolommen;
        this.locatie = locatie;
    }

    public String getZaalID() {
        return zaalID;
    }

    public String getLocatie() { return locatie;}

    public int getZitPlaatsen() {
        return kolommen*rijen;
    }

    public int getRijen() {
        return rijen;
    }

    public int getKolommen() {
        return kolommen;
    }
}
