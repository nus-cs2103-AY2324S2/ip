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
    public void run() throws IOException{
        ui.sayHi();
        Scanner myCom = new Scanner(System.in);
        String command = myCom.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here're the tasks in ur list:");
                int counter = 0;
                for (int i = 0; i < ls.getSize(); i++) {
                    counter++;
                    Task tk = ls.get(i);
                    System.out.println(counter + ". "
                            + tk.toString());
                }
                ui.divider();
                command = myCom.nextLine();
            } else if (command.startsWith("unmark")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    Task tk = ls.get(taskNum - 1);

                    tk.unmark();
                    String taskString = tk.toString();
                    ui.printMarkNotDone(taskString);
                    command = myCom.nextLine();
                } catch (Exception exc) {
                    ui.invalidInputNumber();
                    command = myCom.nextLine();
                }

            } else if (command.startsWith("mark")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    Task tk = ls.get(taskNum - 1);

                    tk.mark();
                    String taskString = tk.toString();
                    ui.printMarkDone(taskString);
                    command = myCom.nextLine();
                } catch (Exception exc) {
                    ui.invalidInputNumber();
                    command = myCom.nextLine();
                }

            } else if (command.startsWith("delete")) {
                try {
                    int taskNum = Parser.parseInt(command);
                    Task tk = ls.get(taskNum - 1);
                    String taskString = tk.toString();
                    ui.printRemoved(taskString);
                    ls.remove(taskNum - 1);

                    ui.printTaskAmount(ls);
                    command = myCom.nextLine();
                } catch (Exception exc) {
                    ui.invalidInputNumber();
                    command = myCom.nextLine();
                }

            } else {
                String[] cmd = Parser.parseCommand(command);
                if (cmd[0].equals("todo")) {
                    try {
                        Task t = new ToDo(cmd[1]);
                        ls.add(t);

                        String taskString = t.toString();
                        ui.printAdded(taskString);
                        ui.printTaskAmount(ls);

                        command = myCom.nextLine();
                    } catch (Exception exc) {
                        ui.invalidDescription();
                        command = myCom.nextLine();
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
                            ui.printAdded(taskString);
                            ui.printTaskAmount(ls);
                            command = myCom.nextLine();
                        }
                        catch (Exception exc) {
                            ui.invalidDdlFormat();
                            command = myCom.nextLine();
                        }
                    } catch (Exception exc) {
                        ui.invalidDescription();
                        command = myCom.nextLine();
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
                            ui.printAdded(taskString);
                            ui.printTaskAmount(ls);
                            command = myCom.nextLine();
                        } catch (Exception exc) {
                            ui.invalidEventFormat();
                            command = myCom.nextLine();
                        }
                    } catch (Exception exc) {
                        ui.invalidDescription();
                        command = myCom.nextLine();
                    }
                } else if (command.startsWith("find")) {
                    try {
                        String keyWord = cmd[1];
                        System.out.println("Here're the matching tasks in ur list:");
                        int counter = 0;
                        for (int i = 0; i < ls.getSize(); i++) {
                            Task tk = ls.get(i);
                            if (tk.getDescription().contains(keyWord)) {
                                counter++;
                                System.out.println(counter + ". " + tk.toString());
                            }
                        }
                        ui.divider();
                        command = myCom.nextLine();
                    } catch (Exception exc) {
                        ui.invalidKeyWord();
                        command = myCom.nextLine();
                    }
                }
                 else {
                    ui.invalidInput();
                    command = myCom.nextLine();
                }

            }
        }

        if (command.equals("bye")) {
            ui.sayBye();

            //Update the file
            Storage.saveFile(storage.getFile(), ls);
        }

    }


    /**
     * Create a Duke instance when the main method
     * is called.
     *
     * @param args
     * @throws IOException For input error.
     */
    public static void main(String[] args) throws IOException {
        new Duke(Storage
                .getFilePathToDukeTxt())
                .run();

    }
}
