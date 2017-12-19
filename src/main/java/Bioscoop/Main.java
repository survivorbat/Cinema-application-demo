package Bioscoop;


import org.apache.log4j.Logger;

public class Main {
	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
        //addDummies();
		standardStart();
	}

	public static void standardStart(){
        BioscoopAppUI app = new BioscoopAppUI();
        logger.info("BiosApp wordt opgestart");
        app.run();
    }

    public static void  addDummies(){
        addDummies a = new addDummies();
        a.clearVoorstellingen();
        a.createDummyVoorstellingen(40);
        a.createDummyKaarten(1000);
    }
}
