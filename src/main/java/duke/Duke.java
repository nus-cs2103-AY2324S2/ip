package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 *  * Represents the main class of the application.
 *
 *
 * @author Loh Jin Hun
 *
 */
public class Duke{



    /**
     * The storage unit of the chatbot that saves and loads from the hard drive
     */
    private static Storage storage;

    /**
     * The temporary storage/tasklist that has operations to add/delete tasks in the list
     */
    private static TaskList list;

    private static Ui ui;
    private static Parser parser;
    private static final String FILE_PATH = "data/duke.txt";
    final static String BINGUS_LOGO = " B   i   n   g   u   s ";
    final static  String NAME = "Bingus";




    public Duke() throws DukeException{
        ui = new Ui();
        ui.intro();
        storage = new Storage(FILE_PATH);
        list = new TaskList(storage);// array to store tasks given
        parser = new Parser(list);
    }






    public static void main(String[] args) throws DukeException {
        ui = new Ui();
        ui.intro();
        storage = new Storage(FILE_PATH);
        list = new TaskList(storage);// array to store tasks given
        parser = new Parser(list);
        ui.read_commands(parser);
    }

    public String getResponse(String input) throws DukeException{

        return parser.parse(input);
    }

    public Parser getParser(){
        return this.parser;
    }




}






