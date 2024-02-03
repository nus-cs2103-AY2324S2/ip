package duke;

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
 * Entry point for the application.
 */
public class Duke {
    private static Ui ui = new Ui(System.in);
    private static Storage storage = new Storage("data/tasks.txt");
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) throws IOException {
        try {
            //loads tasks from file to task list.
            storage.loadFile(taskList);
        } catch (FileNotFoundException e) {
            //creates directory if the given directory path doesn't exist.
            Files.createDirectories(Paths.get(storage.directoryPath));
            ui.printMessage("New human detected!");
        }

        ui.printGreetings();
        String command = ui.getCommand();
        while(!command.equals("bye")) {
            Parser p = new Parser(ui.getCommandDescription(), command);
            String task = "";
            switch (command) {
                case "todo" :
                    try {
                        p.checkEmptyTask();
                        task = taskList.addTask(new ToDo(p.getTaskName(), false));
                        ui.printAddTask(task);
                    } catch (EmptyTaskNameException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline" :
                    try {
                        p.checkEmptyTask();
                        if (p.getDue().split(" ").length == 2) {
                            task = taskList.addTask(
                                    new Deadline(p.getTaskName(), false, p.parseDueTime()));
                            ui.printAddTask(task);
                        } else {
                            task = taskList.addTask(new Deadline(
                                    p.getTaskName().trim(), false, p.parseDueDate()));
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
                        p.checkEmptyTask();
                        task = taskList.addTask(
                                new Event(p.getTaskName(), false, p.getEventStart(), p.getEventEnd()));
                        ui.printAddTask(task);
                    } catch (EmptyTaskNameException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list" :
                    ui.printList(taskList);
                    break;
                case "mark" :
                    ui.printMark(taskList.markTask(p.parseIndex()));
                    break;
                case "unmark" :
                    ui.printUnmark(taskList.unmarkTask(p.parseIndex()));
                    break;
                case "delete" :
                    ui.printDelete(taskList.deleteTask(p.parseIndex()), taskList.getSize());
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