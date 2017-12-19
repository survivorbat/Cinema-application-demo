package Bioscoop.mvc;

import Bioscoop.api.bioscoop.Locatie.APILocatiesController;
import Bioscoop.api.bioscoop.voorstellingen.APIVoorstellingenController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    public APIVoorstellingenController voorstellingen = new APIVoorstellingenController();
    public APILocatiesController locaties = new APILocatiesController();
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("voorstellingen", voorstellingen.voorstellingen());
        model.addAttribute("locaties",locaties.locaties());
        return "index";
    }
}
