package Bioscoop.api.bioscoop.voorstellingen;

import Bioscoop.api.DB;
import Bioscoop.api.bioscoop.films.APIFilmsController;
import Bioscoop.api.bioscoop.voorstellingen.Voorstelling;
import Bioscoop.api.bioscoop.zalen.APIZalenController;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class APIVoorstellingenController {
    static Logger logger = Logger.getLogger(APIVoorstellingenController.class);
    private DB database;
    private APIZalenController zalen = new APIZalenController();
    private APIFilmsController films = new APIFilmsController();

    public APIVoorstellingenController(){
        database = new DB();
    }
    @ApiOperation(value = "findAllVoorstellingen", notes = "Alle voorstellingen opvragen")
    @RequestMapping(value="/voorstellingen",method=GET)
    public List<Voorstelling> voorstellingen(){
        List<Voorstelling> voorstellingLijst = new ArrayList<Voorstelling>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Voorstelling WHERE Datum >= GETDATE() ORDER BY Datum;");
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                voorstellingLijst.add(
                        new Voorstelling(
                                data.getInt("VoorstellingID"),
                                films.getFilm(data.getInt("FilmID")),
                                data.getDate("Datum"),
                                zalen.getZaal(data.getString("ZaalID")),
                                data.getDouble("Bedrag"),
                                data.getString("FilmUitvoering"),
                                data.getTime("Datum")
                        ));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van voorstellingen");
        }
        ArrayList<Voorstelling> remove = new ArrayList<Voorstelling>();
        for(Voorstelling i : voorstellingLijst){
            if(i.getZitplaatsenInGebruik()>=i.getZaal().getZitPlaatsen()){
                remove.add(i);
            }
        }
        for(Voorstelling i : remove){
            voorstellingLijst.remove(i);
        }
        return voorstellingLijst;
    }
    //get zitplaatsen
    @ApiOperation(value="getAmountZitplaatsen", notes = "Krijg de hoeveelheid zitplaatsen")
    @RequestMapping(value="/voorstellingAmount", method=GET)
    public int getVoorstellingZitplaatsen(@RequestParam(value="voorstellingid") int id){
        int plaatsen=0;
        try {
            database.stmt = database.con.prepareStatement("SELECT COUNT(*) AS ZitPlaatsen FROM Kaart JOIN Voorstelling ON Kaart.VoorstellingID = Voorstelling.VoorstellingID WHERE Voorstelling.VoorstellingID = ? GROUP BY Voorstelling.VoorstellingID ORDER BY Voorstelling.VoorstellingID;");
            database.stmt.setInt(1,id);
            ResultSet data = database.stmt.executeQuery();
            if(data.next()){
                plaatsen=data.getInt("ZitPlaatsen");
            }
        }
        catch (Exception e){
            logger.error("Exception bij het ophalen van zitplaatsen");
            e.printStackTrace();
        }
        return plaatsen;
    }
    //Insert query
    @ApiOperation(value = "addVoorstelling", notes = "Een voorstelling toevoegen")
    @RequestMapping(value="/voorstelling", method=POST)
    public void insertVoorstelling(@RequestParam(value="filmid") int filmid, @RequestParam(value="datum") String datum, @RequestParam(value="zaalid") String zaalid, @RequestParam(value="bedrag") double bedrag, @RequestParam(value="uitvoering") String uitvoering){
        try {
            database.stmt = database.con.prepareStatement("INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (?,?,?,?,?);");
            database.stmt.setInt(1,filmid);
            database.stmt.setString(2,datum);
            database.stmt.setString(3,zaalid);
            database.stmt.setDouble(4,bedrag);
            database.stmt.setString(5,uitvoering);
            database.stmt.execute();
            logger.debug("Voeg voorstelling toe met parameters: "+filmid+","+datum+","+zaalid+","+bedrag+","+uitvoering);
        }
        catch(Exception e){e.printStackTrace();logger.error("Error bij het ophalen van voorstelling zitplaatsen");}
    }
    //Get query
    @ApiOperation(value = "findVoorstelling", notes = "Vind voorstelling")
    @RequestMapping(value="/voorstelling", method=GET)
    public Voorstelling getVoorstelling(@RequestParam(value="voorstellingid") int voorstellingid){
        Voorstelling voorstelling = null;
        try {
            logger.debug("Krijg voorstellinginfo van voorstelling "+voorstellingid);
            database.stmt = database.con.prepareStatement("SELECT * FROM Voorstelling WHERE VoorstellingID = ?;");
            database.stmt.setInt(1,voorstellingid);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                voorstelling = new Voorstelling(
                    data.getInt("VoorstellingID"),
                    films.getFilm(data.getInt("FilmID")),
                    data.getDate("Datum"),
                    zalen.getZaal(data.getString("ZaalID")),
                    data.getDouble("Bedrag"),
                    data.getString("FilmUitvoering"),
                    data.getTime("Datum"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return voorstelling;
    }
    //Get query on location
    @ApiOperation(value = "findVoorstellingOnLocation", notes = "Vind voorstelling op locatie")
    @RequestMapping(value="/voorstellingLocatie", method=GET)
    public List<Voorstelling> getVoorstellingLocatie(@RequestParam(value="locatie") String locatie){
        List<Voorstelling> voorstellingLijst = new ArrayList<Voorstelling>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Voorstelling JOIN Zaal ON Zaal.ZaalID = Voorstelling.ZaalID WHERE Locatie =  ? AND Datum > GETDATE() ORDER BY Datum;");
            database.stmt.setString(1,locatie);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                while (data.next()) {
                    voorstellingLijst.add(
                            new Voorstelling(
                                    data.getInt("VoorstellingID"),
                                    films.getFilm(data.getInt("FilmID")),
                                    data.getDate("Datum"),
                                    zalen.getZaal(data.getString("ZaalID")),
                                    data.getDouble("Bedrag"),
                                    data.getString("FilmUitvoering"),
                                    data.getTime("Datum")
                            ));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van voorstellingen");
        }
        return voorstellingLijst;
    }
    //Delete query
    @ApiOperation(value = "deleteVoorstelling", notes = "Voorstelling verwijderen")
    @RequestMapping(value="/voorstelling", method=DELETE)
    public void deleteVoorstelling(@RequestParam(value="voorstellingid") int voorstellingid){
        try {
            database.stmt = database.con.prepareStatement("DELETE FROM Voorstelling WHERE VoorstellingID = ?;");
            database.stmt.setInt(1,voorstellingid);
            database.stmt.execute();
            logger.debug("Verwijder voorstelling met id "+voorstellingid);
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verwijderen van voorstelling "+voorstellingid);
        }
    }
    //Update query
    @ApiOperation(value = "updateVoorstelling", notes = "Update een voorstelling")
    @RequestMapping(value="/voorstelling", method=PUT)
    public void updateVoorstelling(@RequestParam(value="voorstellingid") int voorstellingid){
        try {
            //database.DSLQuery("UPDATE Voorstelling SET;");
            //TODO PUT REQUEST
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Clear query
    public void clearVoortelling(){
        try {
            database.stmt = database.con.prepareStatement("DELETE FROM Voorstelling");
            database.stmt.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
