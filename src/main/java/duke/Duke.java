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
                    Task tk = ls.get(taskNum - 1);

                    tk.unmark();
                    String taskString = tk.toString();
                    return ui.printMarkNotDone(taskString);

                } catch (Exception exc) {
                    return ui.invalidInputNumber();

                }

            } else if (command.startsWith("mark")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    Task tk = ls.get(taskNum - 1);

                    tk.mark();
                    String taskString = tk.toString();
                    return ui.printMarkDone(taskString);

                } catch (Exception exc) {
                    return ui.invalidInputNumber();

                }

            } else if (command.startsWith("delete")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    Task tk = ls.get(taskNum - 1);
                    String taskString = tk.toString();
                    ls.remove(taskNum - 1);
                    return ui.printRemoved(taskString, ls);

                } catch (Exception exc) {
                    return ui.invalidInputNumber();
                }
            } else {
                String[] cmd = Parser.parseCommand(command);
                if (cmd[0].equals("todo")) {
                    try {
                        Task t = new ToDo(cmd[1]);
                        ls.add(t);

                        String taskString = t.toString();
                        return ui.printAdded(taskString, ls);

                    } catch (Exception exc) {
                        return ui.invalidDescription();

                    }
                }
                else if (cmd[0].equals("deadline")) {
                    try {
                        String[] date = cmd[1].split("/by",
                                2);
                        try {
                            String d = date[1].trim();
                            Task t = new Deadline(date[0], d);
                            ls.add(t);
                            String taskString = t.toString();
                            return ui.printAdded(taskString, ls);

                        }
                        catch (Exception exc) {
                           return ui.invalidDdlFormat();

                        }
                    } catch (Exception exc) {
                       return ui.invalidDescription();

                    }
                } else if (cmd[0].equals("event")) {
                    try {
                        String[] date = cmd[1].split("/", 3);
                        try {
                            Task t = new Event(date[0],
                                    date[1],
                                    date[2]);
                            ls.add(t);
                            String taskString = t.toString();
                            return ui.printAdded(taskString, ls);

                        } catch (Exception exc) {
                            return ui.invalidEventFormat();

                        }
                    } catch (Exception exc) {
                        return ui.invalidDescription();

                    }
                } else if (command.startsWith("find")) {
                    try {
                        String keyWord = cmd[1];
                        return ui.findMatchingTask(keyWord, ls);

                    } catch (Exception exc) {
                        return ui.invalidKeyWord();

                    }
                }
                 else {
                    return ui.invalidInput();

                }

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
