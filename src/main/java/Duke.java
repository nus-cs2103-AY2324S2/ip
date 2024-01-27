import exceptions.DukeException;
import fileIO.Storage;
import tasks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import static fileIO.PrintOutputs.*;
import static tasks.TaskList.*;


public class Duke {
    // class variables
    private Storage storage;
    private static TaskList taskList;
    private UI ui;

    public static void main(String[] args) throws DukeException {

        // ui = new Ui();
        // start
        printStartMessage();

        try {
            taskList = Storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            // TODO : show loading error
            throw new DukeException("Error loading the list from output.txt");
        }

        // Run main functionality of ByteBuddy
        runByteBuddy(taskList);

        // bye
        printByeMessage();
    }

    public static void runByteBuddy(TaskList taskList) throws DukeException {
        Scanner sc = new Scanner(System.in);

        // repeating user commands
        label:
        while (true) {
            String command = sc.next().toLowerCase();
            String info = sc.nextLine().trim();

            try {
                switch (command) {
                case "bye":
                    break label;
                case "list":
                    printTaskList();
                    break;
                case "mark":
                    mark(info);
                    break;
                case "unmark":
                    unmark(info);
                    break;
                case "delete":
                    delete(info);
                    break;
                case "todo":
                    todo(info);
                    break;
                case "deadline":
                    deadline(info);
                    break;
                case "event":
                    event(info);
                    break;
                default:
                    throw new DukeException("Sorry but this command does not exist~");
                }
            } catch (DukeException e) {
                printWithSolidLineBreak(e.getMessage());
            }
        }

        // closing
        sc.close();
    }



    public static ArrayList<Task> initTaskList(String filePath) throws DukeException {
        try {
            ArrayList<Task> list = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] parts = s.nextLine().split(" \\| ");
                switch (parts[0]) {
                case "T":
                    list.add(new Todo(parts[1], parts[2]));
                    break;
                case "D":
                    list.add(new Deadline(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    list.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                    break;
                default:
                    break;
                }
            }
            s.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        }
    }
}
