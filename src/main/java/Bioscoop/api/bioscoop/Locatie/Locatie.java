package Bioscoop.api.bioscoop.Locatie;

import Bioscoop.api.bioscoop.films.Film;
import Bioscoop.api.bioscoop.voorstellingen.Voorstelling;
import Bioscoop.api.bioscoop.zalen.Zaal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
public class Locatie {
    private String naam;
    private String straat;
    private int huisnummer;
    private String stad;
    private List<Zaal> zalen = new ArrayList<>();
    private List<Voorstelling> voorstellingen = new ArrayList<>();

    public Locatie(String naam, String straat, int huisnummer, String stad, List<Zaal> zalen, List<Voorstelling> voorstellingen) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.stad = stad;
        this.zalen=zalen;
        this.voorstellingen=voorstellingen;
    }

    public Locatie(String naam, String straat, int huisnummer, String stad) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.stad = stad;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public String getStad() {
        return stad;
    }

    public List<Zaal> getZalen() {
        return zalen;
    }

    public List<Voorstelling> getVoorstellingen() {
        return voorstellingen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locatie locatie = (Locatie) o;

        if (huisnummer != locatie.huisnummer) return false;
        if (naam != null ? !naam.equals(locatie.naam) : locatie.naam != null) return false;
        if (straat != null ? !straat.equals(locatie.straat) : locatie.straat != null) return false;
        return stad != null ? stad.equals(locatie.stad) : locatie.stad == null;
    }

    @Override
    public int hashCode() {
        int result = naam != null ? naam.hashCode() : 0;
        result = 31 * result + (straat != null ? straat.hashCode() : 0);
        result = 31 * result + huisnummer;
        result = 31 * result + (stad != null ? stad.hashCode() : 0);
        return result;
    }
}
