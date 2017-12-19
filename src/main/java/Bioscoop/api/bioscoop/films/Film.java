package Bioscoop.api.bioscoop.films;

import org.springframework.stereotype.Repository;

/**
 *
 */


public class Film {
    private int filmID;
    private String titel;
    private String beschrijving;
    private String regisseur;
    private int jaar;
    private int tijdsduur;
    private String genre;

    public Film(int filmID, String titel, String beschrijving, String regisseur, int jaar, int tijdsduur, String genre) {
        this.filmID = filmID;
        this.titel = titel;
        this.beschrijving = beschrijving;
        this.regisseur = regisseur;
        this.jaar = jaar;
        this.tijdsduur = tijdsduur;
        this.genre = genre;
    }

    public int getFilmID() {
        return filmID;
    }

    public String getTitel() {
        return titel;
    }

    public String getRegisseur() {
        return regisseur;
    }

    public int getJaar() {
        return jaar;
    }

    public int getTijdsduur() {
        return tijdsduur;
    }

    public String getGenre() {
        return genre;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (jaar != film.jaar) return false;
        if (tijdsduur != film.tijdsduur) return false;
        if (titel != null ? !titel.equals(film.titel) : film.titel != null) return false;
        if (beschrijving != null ? !beschrijving.equals(film.beschrijving) : film.beschrijving != null) return false;
        if (regisseur != null ? !regisseur.equals(film.regisseur) : film.regisseur != null) return false;
        return genre != null ? genre.equals(film.genre) : film.genre == null;
    }

    @Override
    public int hashCode() {
        int result = titel != null ? titel.hashCode() : 0;
        result = 31 * result + (beschrijving != null ? beschrijving.hashCode() : 0);
        result = 31 * result + (regisseur != null ? regisseur.hashCode() : 0);
        result = 31 * result + jaar;
        result = 31 * result + tijdsduur;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

}
