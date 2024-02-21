package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;


/**
 * Main chatbot program
 */

public class Duke{
    private static final String FILE_PATH = "data/duke.txt";
    private Ui ui;
    //List<Task> list;
    private TaskList tlist;

    private Storage storage = new Storage();


    /**
     * Constructs an instance of Duke
     */

    public Duke() {
        ui = new Ui();
        tlist = new TaskList();
        storage.load(tlist);
        ui.greet();

    }


    public String getResponse(String input) {
        try {
            return UserHandler.chat(input, tlist, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs Chatbot
     * @throws DukeException
     */






}
