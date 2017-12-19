package Bioscoop.api.bioscoop.Locatie;

import Bioscoop.api.DB;
import Bioscoop.api.bioscoop.voorstellingen.APIVoorstellingenController;
import Bioscoop.api.bioscoop.zalen.APIZalenController;
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
public class APILocatiesController {
    private DB database;
    static Logger logger = Logger.getLogger(APILocatiesController.class);
    private APIZalenController zalen = new APIZalenController();
    private APIVoorstellingenController voorstellingen = new APIVoorstellingenController();
    public APILocatiesController(){
        database = new DB();
    }

    @ApiOperation(value = "findAllLocaties", notes = "Alle locaties opvragen")
    @RequestMapping(value="/locaties",method=GET)
    public List<Locatie> locaties(){
        List<Locatie> locatieLijst = new ArrayList<Locatie>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Locatie ORDER BY Naam;");
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                locatieLijst.add(new Locatie(data.getString("Naam"),data.getString("Straat"),data.getInt("Huisnummer"),data.getString("Stad"),zalen.getZaalLocatie(data.getString("Naam")),voorstellingen.getVoorstellingLocatie(data.getString("Naam"))));;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van locaties");
        }
        return locatieLijst;
    }
    //Insert query
    @ApiOperation(value = "addLocatie", notes = "Een locatie toevoegen")
    @RequestMapping(value="/locatie", method=POST)
    public void insertLocatie(@RequestParam(value="naam") String naam, @RequestParam(value="straat") String straat,@RequestParam(value="huisnummer") int huisnummer, @RequestParam(value="stad") String stad){
        logger.info("Voeg locatie toe met parameters: "+naam+","+straat+","+huisnummer+","+stad);
        try {
            database.stmt = database.con.prepareStatement("INSERT INTO Locatie (Naam, Straat, Huisnummer, Stad) VALUES (?,?,?,?);");
            database.stmt.setString(1,naam);
            database.stmt.setString(2,straat);
            database.stmt.setInt(3,huisnummer);
            database.stmt.setString(4,stad);
            database.stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het toevoegen van locatie");
        }
    }
    //Get query
    @ApiOperation(value = "findLocatie", notes = "Vind locatie")
    @RequestMapping(value="/locatie", method=GET)
    public Locatie getLocatie(@RequestParam(value="naam") String locatieid){
        Locatie locatie = null;
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Locatie WHERE Naam = ?;");
            database.stmt.setString(1,locatieid);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                locatie = new Locatie(data.getString("Naam"),data.getString("Straat"),data.getInt("Huisnummer"),data.getString("Stad"),zalen.getZaalLocatie(data.getString("Naam")),voorstellingen.getVoorstellingLocatie(data.getString("Naam")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van locatie "+locatieid);
        }
        return locatie;
    }
    //Delete query
    @ApiOperation(value = "deleteLocatie", notes = "Locatie verwijderen")
    @RequestMapping(value="/locatie", method=DELETE)
    public void deleteLocatie(@RequestParam(value="naam") String naam){
        logger.info("Verwijder locatie met naam "+naam);
        try {
            database.stmt = database.con.prepareStatement("DELETE FROM Locatie WHERE Naam = ?;");
            database.stmt.setString(1,naam);
            database.stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verwijderen van locatie met naam "+naam);
        }
    }
    //Update query
    @ApiOperation(value = "updateLocatie", notes = "Update een locatie")
    @RequestMapping(value="/locatie", method=PUT)
    public void updateLocatie(@RequestParam(value="locatieid") int locatieid){
        try {
            //database.DSLQuery("UPDATE Locatie SET;");
            //TODO UPDATE QUERY
        }
        catch(Exception e){e.printStackTrace();}
    }
}
