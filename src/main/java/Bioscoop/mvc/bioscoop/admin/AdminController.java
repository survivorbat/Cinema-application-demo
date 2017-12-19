package Bioscoop.mvc.bioscoop.admin;

import Bioscoop.api.bioscoop.Locatie.APILocatiesController;
import Bioscoop.api.bioscoop.voorstellingen.APIVoorstellingenController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    static Logger logger = Logger.getLogger(Bioscoop.mvc.IndexController.class);

    @RequestMapping(value="/admin/", method=RequestMethod.GET)
    public String indexPage() {
        logger.info("Admin paneel opent");
        return "admin/index";
    }
}
