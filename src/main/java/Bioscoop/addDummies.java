package Bioscoop;

import Bioscoop.api.bioscoop.Locatie.APILocatiesController;
import Bioscoop.api.bioscoop.Locatie.Locatie;
import Bioscoop.api.bioscoop.films.APIFilmsController;
import Bioscoop.api.bioscoop.films.Film;
import Bioscoop.api.bioscoop.kaart.APIKaartController;
import Bioscoop.api.bioscoop.kaart.Kaart;
import Bioscoop.api.bioscoop.voorstellingen.APIVoorstellingenController;
import Bioscoop.api.bioscoop.voorstellingen.Voorstelling;
import Bioscoop.api.bioscoop.zalen.APIZalenController;
import Bioscoop.api.bioscoop.zalen.Zaal;

import java.util.List;
import java.util.Random;

public class addDummies {
    private List<Film> films = new APIFilmsController().films();
    private List<Zaal> zalen = new APIZalenController().zalen();
    private List<Voorstelling> voorstellingenLijst;
    private APIKaartController kaart = new APIKaartController();
    private APIVoorstellingenController voorstellingen = new APIVoorstellingenController();
    private Random random = new Random();

    public void createDummyVoorstellingen(int a){
        for(int i=0;i<a;i++) {
            try {
                voorstellingen.insertVoorstelling(
                        films.get(random.nextInt(films.size() - 1)).getFilmID(),
                        "DEc " + random.nextInt(29) + ", 2017 " + random.nextInt(23) + ":00:00",
                        zalen.get(random.nextInt(zalen.size()-1)).getZaalID(),
                        random.nextInt(15)+random.nextDouble(),
                        "3D"
                );
            } catch(Exception e){
                System.out.println("Jammer...");
                e.printStackTrace();
            }
        }
    }
    public void createDummyKaarten(int a){
        voorstellingenLijst = new APIVoorstellingenController().voorstellingen();
        for(int i=0;i<a;i++){
            try {
                Voorstelling voorstelling = voorstellingenLijst.get(random.nextInt(voorstellingenLijst.size()-1));
                kaart.insertKaart(
                        voorstelling.getVoorstellingID(),
                        random.nextInt(voorstelling.getZaal().getKolommen()),
                        random.nextInt(voorstelling.getZaal().getRijen()),
                        ""+random.nextInt(2000000)
                );
            }
            catch (Exception e){}
        }
    }
    public void clearVoorstellingen(){
        voorstellingen.clearVoortelling();
    }
}
