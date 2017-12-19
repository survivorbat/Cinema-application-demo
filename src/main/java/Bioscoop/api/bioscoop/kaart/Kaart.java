package Bioscoop.api.bioscoop.kaart;

import Bioscoop.api.bioscoop.voorstellingen.Voorstelling;
import org.springframework.stereotype.Repository;

/**
 *
 */
public class Kaart {
    private int kaartID;
    private Voorstelling voorstelling;
    private int stoelnummer;
    private int rijnummer;
    private String code;
    /** @param kaartID het ID van het kaartje
     *  @param voorstelling Het voorstelling object waar het kaartje bij hoort
     *  @param stoelnummer het stoelnummer in de rij
     *  @param rijnummer de rij waar de stoel in staat
     *  @param code de unieke code van dit kaartje
     */
    public Kaart(int kaartID, Voorstelling voorstelling, int stoelnummer, int rijnummer, String code) {
        this.kaartID = kaartID;
        this.voorstelling = voorstelling;
        this.stoelnummer = stoelnummer;
        this.rijnummer = rijnummer;
        this.code = code;
    }

    public int getKaartID() {
        return kaartID;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public int getStoelnummer() {
        return stoelnummer;
    }

    public int getRijnummer() {
        return rijnummer;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kaart kaart = (Kaart) o;

        if (stoelnummer != kaart.stoelnummer) return false;
        if (rijnummer != kaart.rijnummer) return false;
        if (voorstelling != null ? !voorstelling.equals(kaart.voorstelling) : kaart.voorstelling != null) return false;
        return code != null ? code.equals(kaart.code) : kaart.code == null;
    }

    @Override
    public int hashCode() {
        int result = voorstelling != null ? voorstelling.hashCode() : 0;
        result = 31 * result + stoelnummer;
        result = 31 * result + rijnummer;
        return result;
    }
}
