package Bioscoop.api.bioscoop.films;

import org.junit.Test;

import static org.junit.Assert.*;

public class APIFilmsControllerTest {
    @Test
    public void films() throws Exception {
        APIFilmsController films = new APIFilmsController();
        films.insertFilm("Geweldige film","superfilm","M",2000,20,"horror");
        assertTrue(films.films().contains(new Film(0,"superfilm","Geweldige film","M",2000,20,"horror")));
        films.deleteFilm(films.getFilm("superfilm").getFilmID());
    }

    @Test
    public void insertFilm() throws Exception {
        APIFilmsController films = new APIFilmsController();
        films.insertFilm("Geweldige film","superfilm","M",2000,20,"horror");
        assertTrue(films.films().contains(new Film(0,"superfilm","Geweldige film","M",2000,20,"horror")));
        films.deleteFilm(films.getFilm("superfilm").getFilmID());
    }

    @Test
    public void getFilm() throws Exception {
        APIFilmsController films = new APIFilmsController();

        films.insertFilm("Geweldige film","superfilm","M",2000,20,"horror");

        assertTrue(films.getFilm(films.getFilm("superfilm").getFilmID()).equals(new Film(0,"superfilm","Geweldige film","M",2000,20,"horror")));

        films.deleteFilm(films.getFilm("superfilm").getFilmID());
    }

    @Test
    public void deleteFilm() throws Exception {
        APIFilmsController films = new APIFilmsController();
        films.insertFilm("Geweldige film","superfilm","M",2000,20,"horror");
        films.deleteFilm(films.getFilm("superfilm").getFilmID());
        assertFalse(films.films().contains(new Film(0,"Geweldige film","superfilm","M",2000,20,"horror")));
    }

    @Test
    public void updateFilm() throws Exception {
    }

}