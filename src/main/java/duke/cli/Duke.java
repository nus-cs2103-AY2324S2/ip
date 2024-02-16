package duke.cli;

import duke.exception.EmptyTaskNameException;
import duke.exception.NoTaskTypeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;

import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.format.DateTimeParseException;

/**
 * Entry point for the application. CLI version.
 */
public class Duke {
    private static Ui ui = new Ui(System.in);
    private static Storage storage = new Storage("data/tasks.txt");
    private static TaskList taskList = new TaskList();

    /**
     * Initializes Duke.
     *
     * @param args  Command line arguments – not used.
     * @throws IOException If error occurred during file operations.
     */
    public static void main(String[] args) throws IOException {
        try {
            //loads tasks from file to task list.
            storage.loadFile(taskList);
        } catch (FileNotFoundException e) {
            //creates directory if the given directory path doesn't exist.
            Files.createDirectories(Paths.get(storage.getDirectoryPath()));
            ui.printMessage("New human detected!");
        }

        ui.printGreetings();
        String command = ui.getCommand();
        while(!command.equals("bye")) {
            Parser parser = new Parser(ui.getCommandDescription(), command);
            String task = "";
            switch (command) {
            case "todo" :
                try {
                    parser.checkEmptyTask();
                    task = taskList.addTask(new ToDo(parser.getTaskName(), false));
                    ui.printAddTask(task);
                } catch (EmptyTaskNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline" :
                try {
                    parser.checkEmptyTask();
                    if (parser.getDue().split(" ").length == 2) {
                        task = taskList.addTask(
                                new Deadline(parser.getTaskName(), false, parser.parseDueTime()));
                        ui.printAddTask(task);
                    } else {
                        task = taskList.addTask(new Deadline(
                                parser.getTaskName().trim(), false, parser.parseDueDate()));
                        ui.printAddTask(task);
                    }
                } catch (EmptyTaskNameException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Please specify the task's deadline using the format "
                            + "'yyyy-mm-dd hh:mm' or 'yyyy-mm-dd'");
                }
                break;
            case "event" :
                try {
                    parser.checkEmptyTask();
                    task = taskList.addTask(
                            new Event(
                                    parser.getTaskName(),
                                    false,
                                    parser.getEventStart(),
                                    parser.getEventEnd()));
                    ui.printAddTask(task);
                } catch (EmptyTaskNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "list" :
                ui.printList(taskList);
                break;
            case "mark" :
                ui.printMark(taskList.markTask(parser.parseIndex()));
                break;
            case "unmark" :
                ui.printUnmark(taskList.unmarkTask(parser.parseIndex()));
                break;
            case "delete" :
                ui.printDelete(taskList.deleteTask(parser.parseIndex()), taskList.getSize());
                break;
            case "find" :
                ui.printFind(taskList.find(parser.getUserInput()));
                break;
            default :
                try {
                    throw new NoTaskTypeException();
                } catch (NoTaskTypeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            command = ui.getCommand();
        }

        storage.writeFile(taskList);
        ui.printGoodbye();
    }
}