package Bioscoop.api.bioscoop.films;

import Bioscoop.api.DB;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class APIFilmsController {
    private DB database;
    static Logger logger = Logger.getLogger(APIFilmsController.class);
    public APIFilmsController(){
        database = new DB();
    }
    @ApiOperation(value = "findAllFilms", notes = "Alle films opvragen")
    @RequestMapping(value="/films",method=GET)
    public List<Film> films(){
        List<Film> filmLijst = new ArrayList<Film>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Film ORDER BY FilmID;");
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                filmLijst.add(new Film(data.getInt("FilmID"),data.getString("Titel"), data.getString("Beschrijving"), data.getString("Regisseur"),data.getInt("Jaar"),data.getInt("Tijd"),data.getString("Genre")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return filmLijst;
    }
    //Insert query
    @ApiOperation(value = "addFilm", notes = "Een film toevoegen")
    @RequestMapping(value="/film", method=POST)
    public void insertFilm(@RequestParam(value="beschrijving") String beschrijving, @RequestParam(value="titel") String titel,@RequestParam(value="regisseur") String regisseur, @RequestParam(value="jaar") int jaar, @RequestParam(value="tijd") int tijd, @RequestParam(value="genre") String genre){
        try {
            logger.info("Voeg film toe met parameters: "+titel+","+regisseur+","+jaar+","+tijd+","+genre+","+beschrijving);
            database.stmt = database.con.prepareStatement("INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES (?,?,?,?,?,?);");
            database.stmt.setString(1,titel);
            database.stmt.setString(2,regisseur);
            database.stmt.setInt(3,jaar);
            database.stmt.setInt(4,tijd);
            database.stmt.setString(5,genre);
            database.stmt.setString(6,beschrijving);
            database.stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bi jhet toevoegen van film");
        }
    }
    //Get query
    @ApiOperation(value = "findFilm", notes = "Vind film")
    @RequestMapping(value="/film", method=GET)
    public Film getFilm(@RequestParam(value="filmid") int filmid){
        Film film = null;
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Film WHERE FilmID = ?;");
            database.stmt.setInt(1,filmid);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                film = new Film(data.getInt("FilmID"),data.getString("Titel"), data.getString("Beschrijving"), data.getString("Regisseur"),data.getInt("Jaar"),data.getInt("Tijd"),data.getString("Genre"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verkrijgen van film met id "+filmid);
        }
        return film;
    }
    //GetByTitle query
    @ApiOperation(value = "findFilmByTitle", notes = "Vind film met titel")
    @RequestMapping(value="/filmByTitle", method=GET)
    public Film getFilm(@RequestParam(value="titel") String titel){
        Film film = null;
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Film WHERE Titel = ?;");
            database.stmt.setString(1,titel);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                film = new Film(data.getInt("FilmID"),data.getString("Titel"), data.getString("Beschrijving"), data.getString("Regisseur"),data.getInt("Jaar"),data.getInt("Tijd"),data.getString("Genre"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verkrijgen van film met titel "+titel);
        }
        return film;
    }
    //Delete query
    @ApiOperation(value = "deleteFilm", notes = "Film verwijderen")
    @RequestMapping(value="/film", method=DELETE)
    public void deleteFilm(@RequestParam(value="filmid") int filmid){
        logger.info("Verwijder film met id "+filmid);
        try {
            database.stmt = database.con.prepareStatement("DELETE FROM Film WHERE FilmID = ?;");
            database.stmt.setInt(1,filmid);
            database.stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verwijderen van film met id "+filmid);
        }
    }
    //Update query
    @ApiOperation(value = "updateFilm", notes = "Update een film")
    @RequestMapping(value="/film", method=PUT)
    public void updateFilm(@RequestParam(value="filmid") int filmid){
        try {
            //database.DSLQuery("UPDATE Film SET;");
        }
        catch(Exception e){e.printStackTrace();}
    }
}
