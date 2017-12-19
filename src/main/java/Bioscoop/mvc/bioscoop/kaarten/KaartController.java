package Bioscoop.mvc.bioscoop.kaarten;

import Bioscoop.api.bioscoop.Locatie.APILocatiesController;
import Bioscoop.api.bioscoop.kaart.APIKaartController;
import Bioscoop.api.bioscoop.kaart.Kaart;
import Bioscoop.api.bioscoop.voorstellingen.APIVoorstellingenController;
import Bioscoop.api.bioscoop.zalen.APIZalenController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class KaartController {
    static Logger logger = Logger.getLogger(KaartController.class);
    private APIVoorstellingenController voorstellingen = new APIVoorstellingenController();
    private APIZalenController zalen = new APIZalenController();
    private APIKaartController kaarten = new APIKaartController();
    private APILocatiesController locaties = new APILocatiesController();

    @RequestMapping(value="/kaarten/bestellen", method=GET)
    public String kaartenBestel(@RequestParam(value="voorstellingid") int voorstelling , Model model) {
        model.addAttribute("voorstelling", voorstellingen.getVoorstelling(voorstelling));
        model.addAttribute("zitplaatsen", zalen.getUsedSeats(voorstelling));
        logger.info("Open kaart-bestellen pagina");
        logger.debug("Voorstelling ID: "+voorstelling);
        return "paginas/kaart_bestellen";
    }
    @RequestMapping(value="/kaarten/kopen", method=POST)
    public String kaartenKopen(@RequestParam(value="voorstellingid") int voorstelling, @RequestParam(value="aantal") int aantal, @RequestParam(value="stoelen") String[] stoelen, Model model){
        model.addAttribute("aantal",aantal);
        model.addAttribute("kaart", stoelen);
        model.addAttribute("voorstelling",voorstellingen.getVoorstelling(voorstelling));
        model.addAttribute("locatie",locaties.getLocatie(voorstellingen.getVoorstelling(voorstelling).getZaal().getLocatie()));
        logger.info("Open kaarten-gekocht pagina");
        logger.info("Voeg "+aantal+" kaarten toe");

        ArrayList<Kaart> kaartenLijst = new ArrayList<>();

        for(int i=0;i<aantal;i++){
            String code = ""+new Random().nextInt(200000);
            ArrayList<String> existingCodes = kaarten.getCodes();
            while(existingCodes.contains(code)){
                code = ""+new Random().nextInt(200000);
            }
            try {
                int rijnummer = Integer.parseInt(stoelen[i].split(",")[0]);
                int stoelnummer = Integer.parseInt(stoelen[i].split(",")[1]);
                logger.debug("Voeg kaart van voorstelling "+voorstelling+" toe met stoel "+rijnummer+','+stoelnummer+','+code);
                kaarten.insertKaart(voorstelling,stoelnummer,rijnummer,code);
            }
            catch (ArrayIndexOutOfBoundsException e){
                int rijnummer = Integer.parseInt(stoelen[i]);
                int stoelnummer = Integer.parseInt(stoelen[i+1]);
                logger.trace("Voeg kaart van voorstelling "+voorstelling+" toe met stoel "+rijnummer+','+stoelnummer+" MET EXCEPTION");
                kaarten.insertKaart(voorstelling,stoelnummer,rijnummer,code);
            }
            finally {
                kaartenLijst.add(kaarten.getKaartByCode(code));
            }
        }
        model.addAttribute("kaart",kaartenLijst);
        return "paginas/kaart_kopen";
    }
}
