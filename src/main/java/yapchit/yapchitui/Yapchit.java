package yapchit.yapchitui;

import yapchit.yapchitbackend.Ui;
import yapchit.yapchitbackend.YapchitBackend;

/**
 * Yapchit is a bot that allows users to create and manage their tasks.
 * The Yapchit class is the entry point into the program and encapsulates
 * another class that enables the functionality of Yapchit.
 *
 * @author Archit Goswami
 * @version 1.0
 * @since 2024-02-01
 */
public class Yapchit {


    private YapchitBackend yapchitBackend;
    private boolean hasNext = true;

    /**
     * Constructs a new instance of the Yapchit class.
     * Yapchit is a chatbot that responds to user input
     * based off of a fixed set of criteria. Yapchit encapsulates the yapchit backend instance
     * which handles the core functionality of the bot.
     */
    public Yapchit() {
        this.yapchitBackend= new YapchitBackend("./src/main/data/dataStore.txt");
        assert yapchitBackend != null : "backend must be initialised";
    }

    /**
     * Returns reponse based on input text. Calls backend to handle response.
     *
     * @param inputText input text to get response for.
     * @return String response.
     */
    protected String getResponse(String inputText) {
        String yapchitText;
        if (yapchitBackend.checkIsBye(inputText)){
            yapchitText = yapchitBackend.getOutro();
            this.hasNext = false;
        } else {
            yapchitText = yapchitBackend.run(inputText);
        }

        return yapchitText;
    }

    /**
     * Gets hasNext boolean.
     *
     * @return Boolean value of hasNext.
     */
    protected boolean getHasNext() {
        return this.hasNext;
    }

    /**
     * Gets intro from backend.
     *
     * @return String intro.
     */
    protected String getIntro() {
        return yapchitBackend.getIntro();
    }

}
