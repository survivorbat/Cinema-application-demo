package Bioscoop.api.bioscoop.zalen;

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
public class APIZalenController {
    private DB database;
    static Logger logger = Logger.getLogger(APIZalenController.class);
    public APIZalenController(){
        database = new DB();
    }
    @ApiOperation(value = "findAllZalen", notes = "Alle zalen opvragen")
    @RequestMapping(value="/zalen",method=GET)
    public ArrayList<Zaal> zalen(){
        ArrayList<Zaal> zaalLijst = new ArrayList<Zaal>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Zaal ORDER BY ZaalID;");
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                zaalLijst.add(new Zaal(data.getString("ZaalID"), data.getInt("Rijen"), data.getInt("Kolommen"), data.getString("Locatie")));;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verkrijgen van zalen");
        }
        return zaalLijst;
    }
    //Insert query
    @ApiOperation(value = "addZaal", notes = "Een zaal toevoegen")
    @RequestMapping(value="/zaal", method=POST)
    public void insertZaal(@RequestParam(value="zaalid") String zaalid, @RequestParam(value="rijen") int rijen,@RequestParam(value="kolommen") int kolommen, @RequestParam(value="locatie") String locatie){
        try {
            database.stmt = database.con.prepareStatement("INSERT INTO Zaal (ZaalID, Rijen, Kolommen, Locatie) VALUES (?,?,?,?);");
            database.stmt.setString(1,zaalid);
            database.stmt.setInt(2,rijen);
            database.stmt.setInt(3,kolommen);
            database.stmt.setString(4,locatie);
            database.stmt.execute();
            logger.debug("Voeg zaal toe met parameters: "+zaalid+","+rijen+","+kolommen+","+locatie);
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Get query
    @ApiOperation(value = "findZaal", notes = "Vind zaal")
    @RequestMapping(value="/zaal", method=GET)
    public Zaal getZaal(@RequestParam(value="zaalid") String zaalid){
        Zaal zaal = null;
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Zaal WHERE ZaalID = ?;");
            database.stmt.setString(1,zaalid);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                zaal = new Zaal(data.getString("ZaalID"), data.getInt("Rijen"), data.getInt("Kolommen"), data.getString("Locatie"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van zaalinfo over zaal "+zaalid);
        }
        return zaal;
    }
    //Get query on location
    @ApiOperation(value = "findZaalOnLocation", notes = "Vind zaal op locatie")
    @RequestMapping(value="/zalenLocatie", method=GET)
    public List<Zaal> getZaalLocatie(@RequestParam(value="locatie") String locatie){
        logger.info("Verkrijg zalen op locatie: "+locatie);
        List<Zaal> zaalLijst = new ArrayList<Zaal>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Zaal WHERE Locatie = ?;");
            database.stmt.setString(1,locatie);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                zaalLijst.add(new Zaal(data.getString("ZaalID"), data.getInt("Rijen"), data.getInt("Kolommen"), data.getString("Locatie")));;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verkrijgen van zalen");
        }
        return zaalLijst;
    }
    //Delete query
    @ApiOperation(value = "deleteZaal", notes = "Zaal verwijderen")
    @RequestMapping(value="/zaal", method=DELETE)
    public void deleteZaal(@RequestParam(value="zaalid") String zaalid){
        try {
            logger.info("Verwijder zaal met zaalid "+zaalid);
            database.stmt = database.con.prepareStatement("DELETE FROM Zaal WHERE ZaalID = ?;");
            database.stmt.setString(1,zaalid);
            database.stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verwijderen van zaal met zaalid "+zaalid);
        }
    }
    //Update query
    @ApiOperation(value = "updateZaal", notes = "Update een zaal")
    @RequestMapping(value="/zaal", method=PUT)
    public void updateZaal(@RequestParam(value="zaalid") int zaalid){
        try {
            //TODO Put request
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Get used seats
    @ApiOperation(value="getUsedSeats", notes="Krijg een lijst aan alle gebruikte plaatsen")
    @RequestMapping(value="/zaalPlaatsenInGebruik", method=GET)
    public ArrayList<String> getUsedSeats(@RequestParam(value="voorstellingid") int voorstellingID){
        logger.info("Verkrijg stoelen die al in gebruik zijn van voorstelling "+voorstellingID);
        ArrayList<String> list = new ArrayList<String>();
        try {
            database.stmt = database.con.prepareStatement("SELECT Rijnummer,Stoelnummer FROM Zaal JOIN Voorstelling ON Voorstelling.ZaalID = Zaal.ZaalID JOIN Kaart ON Kaart.VoorstellingID = Voorstelling.VoorstellingID WHERE Voorstelling.VoorstellingID = ?;");
            database.stmt.setInt(1, voorstellingID);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                list.add(""+data.getString("Rijnummer")+","+data.getString("Stoelnummer"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Exception bij het verkrijgen van zitplaatsen voor voorstelling "+voorstellingID);
        }
        return list;
    }
}
