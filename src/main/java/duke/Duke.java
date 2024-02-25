package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * This class is the main class that navigates aids in the navigation of the program.
 * @author Tang Hao Liang
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    /**
     * Constructor creates new Ui, Storage, Parser and loads the storage into the list.
     *
     * @param filePath Path to file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            ArrayList<Task> storageList = storage.load();
            tasks = new TaskList(storageList);
            assert storageList.size() == tasks.getNumberOfTasks();
        } catch (IOException e) {
            Ui.showLoadingError();
        }
    }

    public String getResponse(String input) {
        try {
            String output;
            String[] parse = parser.parse(input);
            if (parse[0].equalsIgnoreCase("bye")) {
                output = Ui.showEnd();
            } else if (parse[0].equalsIgnoreCase("help")) {
                output = Ui.showHelp();
            } else if (parse[0].equalsIgnoreCase("list")) {
                output = Ui.printList(tasks.getList());
            } else if (parse[0].equalsIgnoreCase("mark")) {
                int num = parser.stringToNum(parse[1]);
                output = tasks.mark(num);
            } else if (parse[0].equalsIgnoreCase("unmark")) {
                int num = parser.stringToNum(parse[1]);
                output = tasks.unmark(num);
            } else if (parse[0].equalsIgnoreCase("todo")) {
                String description = parser.toDo(parse[1]);
                output = tasks.toDo(description);
            } else if (parse[0].equalsIgnoreCase("deadline")) {
                String[] split = parser.deadline(parse[1]);
                LocalDateTime by = parser.stringToDateTime(split[1]);
                output = tasks.deadline(split[0], by);
            } else if (parse[0].equalsIgnoreCase("event")) {
                String[] split = parser.event(parse[1]);
                LocalDateTime from = parser.stringToDateTime(split[1]);
                LocalDateTime to = parser.stringToDateTime(split[2]);
                output = tasks.event(split[0], from, to);
            } else if (parse[0].equalsIgnoreCase("delete")) {
                int num = parser.stringToNum(parse[1]);
                int numOfTasks = tasks.getNumberOfTasks();
                output = tasks.delete(num);
                assert numOfTasks == tasks.getNumberOfTasks() + 1;
            } else if (parse[0].equalsIgnoreCase("find")) {
                ArrayList<Task> contains = tasks.findContains(parse[1]);
                output = Ui.printList(contains);
            } else {
                throw new DukeException("Please do enter a new proper command.\n");
            }
            storage.saveToFile(tasks.getList());
            return output;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
