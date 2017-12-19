package Bioscoop.api.bioscoop.voorstellingen;

import Bioscoop.api.bioscoop.Locatie.Locatie;
import Bioscoop.api.bioscoop.films.Film;
import Bioscoop.api.bioscoop.zalen.Zaal;

import java.sql.Time;
import java.util.Date;

public class Voorstelling {
    private int voorstellingID;
    private Film film;
    private Date datum;
    private Time tijd;
    private Zaal zaal;
    private String bedrag;
    private String filmUitvoering;
    private int zitplaatsenInGebruik;

    public Voorstelling(int voorstellingID, Film film, Date datum, Zaal zaal, double bedrag, String filmUitvoering, Time tijd) {
        this.voorstellingID = voorstellingID;
        this.film = film;
        this.datum = datum;
        this.zaal = zaal;
        this.bedrag = String.format("%.2f", bedrag);
        this.filmUitvoering = filmUitvoering;
        this.tijd=tijd;
        this.zitplaatsenInGebruik=new APIVoorstellingenController().getVoorstellingZitplaatsen(voorstellingID);
    }

    public Voorstelling(Film film, Date datum, Zaal zaal, double bedrag, String filmUitvoering, Time tijd) {
        this.film = film;
        this.datum = datum;
        this.zaal = zaal;
        this.bedrag = String.format("%.2f", bedrag);
        this.filmUitvoering = filmUitvoering;
        this.tijd=tijd;
        this.zitplaatsenInGebruik=new APIVoorstellingenController().getVoorstellingZitplaatsen(voorstellingID);
    }

    public int getVoorstellingID() {
        return voorstellingID;
    }

    public int getZitplaatsenInGebruik(){ return zitplaatsenInGebruik;};

    public Time getTijd() {
        return tijd;
    }

    public Film getFilm() {
        return film;
    }

    public Date getDatum() {
        return datum;
    }

    public Zaal getZaal() {
        return zaal;
    }

    public String getBedrag() {
        return bedrag;
    }

    public String getFilmUitvoering() {
        return filmUitvoering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voorstelling that = (Voorstelling) o;

        if (voorstellingID != that.voorstellingID) return false;
        if (film != null ? !film.equals(that.film) : that.film != null) return false;
        if (datum != null ? !datum.equals(that.datum) : that.datum != null) return false;
        if (tijd != null ? !tijd.equals(that.tijd) : that.tijd != null) return false;
        if (zaal != null ? !zaal.equals(that.zaal) : that.zaal != null) return false;
        if (bedrag != null ? !bedrag.equals(that.bedrag) : that.bedrag != null) return false;
        return filmUitvoering != null ? filmUitvoering.equals(that.filmUitvoering) : that.filmUitvoering == null;
    }

    @Override
    public int hashCode() {
        int result = voorstellingID;
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (datum != null ? datum.hashCode() : 0);
        result = 31 * result + (tijd != null ? tijd.hashCode() : 0);
        result = 31 * result + (zaal != null ? zaal.hashCode() : 0);
        result = 31 * result + (bedrag != null ? bedrag.hashCode() : 0);
        result = 31 * result + (filmUitvoering != null ? filmUitvoering.hashCode() : 0);
        return result;
    }
}
