package Bioscoop.api.bioscoop.kaart;

import Bioscoop.api.DB;
import Bioscoop.api.bioscoop.voorstellingen.APIVoorstellingenController;
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
public class APIKaartController {
    private DB database;
    static Logger logger = Logger.getLogger(APIKaartController.class);
    private APIVoorstellingenController voorstellingen = new APIVoorstellingenController();
    public APIKaartController(){
        database = new DB();
    }
    @ApiOperation(value = "findAllKaarten", notes = "Alle kaarten opvragen")
    @RequestMapping(value="/kaarten",method=GET)
    public List<Kaart> kaarten(){
        List<Kaart> kaartLijst = new ArrayList<Kaart>();
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Kaart ORDER BY KaartID;");
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                kaartLijst.add(new Kaart(data.getInt("KaartID"),voorstellingen.getVoorstelling(data.getInt("VoorstellingID")),data.getInt("Stoelnummer"),data.getInt("Rijnummer"), data.getString("Code")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Error caught bij het ophalen van kaarten");
        }
        return kaartLijst;
    }
    //Insert query
    @ApiOperation(value = "addKaart", notes = "Een kaart toevoegen")
    @RequestMapping(value="/kaart", method=POST)
    public void insertKaart(@RequestParam(value="voorstellingid") int voorstellingid, @RequestParam(value="stoelnummer") int stoelnummer,@RequestParam(value="rijnummer") int rijnummer, @RequestParam(value="code") String code){
        try {
            database.stmt = database.con.prepareStatement("INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer, Code) VALUES (?,?,?,?);");
            database.stmt.setInt(1,voorstellingid);
            database.stmt.setInt(2,stoelnummer);
            database.stmt.setInt(3,rijnummer);
            database.stmt.setString(4,code);
            database.stmt.execute();
            logger.info("Voeg kaart toe met parameters: "+voorstellingid+","+stoelnummer+","+rijnummer+","+code);
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het toevoegen");
        }
    }
    //Get query
    @ApiOperation(value = "findKaart", notes = "Vind kaart")
    @RequestMapping(value="/kaart", method=GET)
    public Kaart getKaart(@RequestParam(value="kaartid") int kaartid){
        Kaart kaart = null;
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Kaart WHERE KaartID = ?;");
            database.stmt.setInt(1,kaartid);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                kaart = new Kaart(data.getInt("KaartID"),voorstellingen.getVoorstelling(data.getInt("VoorstellingID")),data.getInt("Stoelnummer"),data.getInt("Rijnummer"), data.getString("Code"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van kaart");
        }
        return kaart;
    }
    //Get query code
    @ApiOperation(value = "findKaartByCode", notes = "Vind kaart met code")
    @RequestMapping(value="/kaartbycode", method=GET)
    public Kaart getKaartByCode(@RequestParam(value="code") String code){
        Kaart kaart = null;
        try {
            database.stmt = database.con.prepareStatement("SELECT * FROM Kaart WHERE Code = ?;");
            database.stmt.setString(1,code);
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                kaart = new Kaart(data.getInt("KaartID"),voorstellingen.getVoorstelling(data.getInt("VoorstellingID")),data.getInt("Stoelnummer"),data.getInt("Rijnummer"), data.getString("Code"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Exception bij het ophalen van kaart");
        }
        return kaart;
    }
    //Delete query
    @ApiOperation(value = "deleteKaart", notes = "Kaart verwijderen")
    @RequestMapping(value="/kaart", method=DELETE)
    public void deleteKaart(@RequestParam(value="kaartid") int kaartid){
        try {
            database.stmt = database.con.prepareStatement("DELETE FROM Kaart WHERE KaartID = ?;");
            database.stmt.setInt(1,kaartid);
            database.stmt.execute();
            logger.info("Verwijder kaart met id "+kaartid);
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Update query
    @ApiOperation(value = "updateKaart", notes = "Update een kaart")
    @RequestMapping(value="/kaart", method=PUT)
    public void updateKaart(@RequestParam(value="kaartid") int kaartid){
        try {
            //database.DSLQuery("UPDATE Kaart SET;");
            //TODO put request
        }
        catch(Exception e){e.printStackTrace();}
    }
    //Get codes
    @ApiOperation(value="getCodes", notes = "Get codes")
    @RequestMapping(value="/kaartcodes", method=GET)
    public ArrayList<String> getCodes(){
        ArrayList<String> codeLijst = new ArrayList<>();
        try {
            database.stmt = database.con.prepareStatement("SELECT Code FROM Kaart;");
            ResultSet data = database.stmt.executeQuery();
            while (data.next()) {
                codeLijst.add(data.getString("Code"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error("Error caught bij het ophalen van kaarten");
        }
        return codeLijst;
    }
}
