package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents an instance of the chatbot Elias, which is
 * this project's rendition of project Duke. Elias is able
 * to take in commands to add, modify or delete items in a
 * ItemList. On exit, Items in the ItemList are serialized
 * and stored in the duke.txt file. The duke.txt file is created
 * when no such file exists, and is created in the ./data directory
 * unless no such directory exists, where it is added to the root.
 */
public class Duke extends Application {
    private final Storage storage;
    private final ItemList itemList;
    private final Parser parser;
    private final UI ui;

    /**
     * Creates a new instance of Duke as Elias.
     */
    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.itemList = storage.readFromFile();
        this.ui = new UI();
        this.parser = new Parser(itemList);
    }

    @Override
    public void start(Stage stage) {
        stage.show();
    }

    public UI getUi() {
        return ui;
    }

    public String getResponse(String input) {
        // to abstract out UI stuff using fxml
        String out;
        if (!input.equals("bye")) {
            try {
                out = this.parser.parse(input);
                return out;
            } catch (CustomExceptions.UnrecognizedCommandException e) {
                return "Sorry I do not recognize this command: " + input;
            } catch (CustomExceptions e) {
                return e.getMessage();
            }
        } else {
            this.storage.writeToFile(this.itemList);
            return this.ui.getBye();
        }
    }

}
