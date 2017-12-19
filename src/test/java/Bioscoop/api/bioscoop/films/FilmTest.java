package Bioscoop.api.bioscoop.films;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilmTest {
    @Test
    public void getFilmID() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals(1,film.getFilmID());
    }

    @Test
    public void getTitel() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals("film",film.getTitel());
    }

    @Test
    public void getRegisseur() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals("m",film.getRegisseur());
    }

    @Test
    public void getJaar() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals(1992,film.getJaar());
    }

    @Test
    public void getTijdsduur() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals(120,film.getTijdsduur());
    }

    @Test
    public void getGenre() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals("javagenre",film.getGenre());
    }

    @Test
    public void getBeschrijving() throws Exception {
        Film film = new Film(1,"film","gek","m",1992,120,"javagenre");

        assertEquals("gek",film.getBeschrijving());
    }

}