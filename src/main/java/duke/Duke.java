package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.stage.Stage;



public class Duke {

    private Storage storage;
    private TaskList ls;
    private Ui ui;


    /**
     * Constructor for Duke
     *
     * @param filePath For file duke.txt.
     * @throws IOException For input error.
     */
    public Duke(String filePath) throws IOException{
        ui = new Ui();
        storage = new Storage(filePath);
        ls = new TaskList(storage.loadFile());
    }


    /**
     * Initialize Duke
     *
     * @throws IOException For input error.
     */
    public String getResponse(String command) throws IOException{
        ui.sayHi();


        while (!command.equals("bye")) {
            if (command.equals("list")) {
                return ui.listTask(ls);
            } else if (command.startsWith("unmark")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    return ui.MarkNotDone(taskNum, ls);
                } catch (Exception exc) {
                    return ui.invalidInputNumber();
                }

            } else if (command.startsWith("mark")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    return ui.MarkDone(taskNum, ls);
                } catch (Exception exc) {
                    return ui.invalidInputNumber();
                }

            } else if (command.startsWith("delete")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    return ui.taskRemoved(taskNum, ls);
                } catch (Exception exc) {
                    return ui.invalidInputNumber();
                }
            } else if (command.startsWith("find")) {
                try {
                    String keyWord = (Parser.parseCommand(command))[1];
                    return ui.findMatchingTask(keyWord, ls);

                } catch (Exception exc) {
                    return ui.invalidKeyWord();

                }
            } else {
                String[] cmd = Parser.parseCommand(command);
                return ui.addTask(cmd, ls);
            }
        }

        if (command.equals("bye")) {
            //Update the file
            Storage.saveFile(storage.getFile(), ls);

            return ui.sayBye();
        }
        return "";
    }







}
