package youdon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The main class that starts the Youdon chatbot application.
 */
public class Youdon {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Youdon() {
        // initialise ui, storage (filepath = "./data/save.txt") and tasklist
        this.ui = new Ui();
        this.storage = new Storage("./data/save.txt");
        this.tasks = new TaskList(storage.loadData());
    }

    /**
     * The main method of the application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // initialise ui and storage (filepath = "./data/save.txt")
        Ui ui = new Ui();
        Storage storage = new Storage("./data/save.txt");
        TaskList tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(ui, tasks, storage);

        // chatbot welcome message
        ui.printWelcomeMsg();

        // parse input
        parser.parse();
    }

    protected String getResponse(String input) {

        assert !input.isEmpty();

        // try-catch block for exceptions
        try {
            YoudonException.EmptyDescException.detectMissingDesc(input);
            YoudonException.InvalidCommandException.detectInvalidCommand(input);
        } catch (YoudonException.EmptyDescException | YoudonException.InvalidCommandException e) {
            // print out error message
            return ui.getYoudonErrorMsg(e.getMessage());
        }

        // if input == "bye", print chatbot bye message
        if (input.equals("bye")) {
            return ui.getByeMsg();
        }

        // if input == "list", return tasklist
        if (input.equals("list")) {
            return ui.getTaskList(tasks);
        }

        if (input.contains(" ")) {
            // if input data has 2 parts, split into command & task number
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String task = parts[1];

            // if input == "find", find all tasks with the given word
            if (command.equals("find")) {
                TaskList foundList = new TaskList();
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    if (currTask.toString().contains(task)) {
                        foundList.add(currTask);
                    }
                }
                return ui.getTaskList(foundList);
            }

            // if input == "mark", mark the specified task as done
            if (command.equals("mark")) {
                // mark task as done in array
                int taskNumber = Integer.parseInt(task);
                tasks.get(taskNumber - 1).isDone = true;
                // save in save file
                try {
                    this.storage.saveData(tasks);
                    return ui.getMarkMsg(tasks, taskNumber) + "\n Tasklist saved!";
                } catch (IOException e) {
                    return "Error!" + e.getMessage();
                }
            }

            // if input == "unmark", mark the specified task as undone
            if (command.equals("unmark")) {
                // mark task as undone in array
                int taskNumber = Integer.parseInt(task);
                tasks.get(taskNumber - 1).isDone = false;
                // save in save file
                try {
                    this.storage.saveData(tasks);
                    return ui.getUnmarkMsg(tasks, taskNumber) + "\n Tasklist saved!";
                } catch (IOException e) {
                    return "Error!" + e.getMessage();
                }
            }

            // if input == "delete", delete the specified task
            if (command.equals("delete")) {
                int taskNumber = Integer.parseInt(task);
                Task taskToDelete = tasks.get(taskNumber - 1);
                tasks.remove(taskNumber - 1);

                try {
                    storage.saveData(tasks);
                    return ui.getDeleteMsg(taskToDelete) + "\n Tasklist saved!";
                } catch (IOException e) {
                    return "Error!" + e.getMessage();
                }
            }

            // differentiate between type of tasks and add to tasklist
            if (command.equals("todo")) {

                // add to tasklist
                tasks.add(new Todo(task));

                try {
                    storage.saveData(tasks);
                    return ui.getTaskAddedMsg(tasks) + "\n Tasklist saved!";
                } catch (IOException e) {
                    return "Error!" + e.getMessage();
                }
            }

            // if task has "by", split into task and deadline
            if (task.contains("/by ")) {
                String[] chunks = task.split("/by ");
                if (command.equals("deadline")) {

                    // add to tasklist
                    String taskDesc = chunks[0];
                    String deadline = chunks[1];
                    try {
                        // attempt to parse the string into a LocalDateTime object
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime dateTimeDeadline = LocalDateTime.parse(deadline, formatter);
                        tasks.add(new Deadline(taskDesc, dateTimeDeadline));

                        try {
                            storage.saveData(tasks);
                            return ui.getTaskAddedMsg(tasks) + "\n Tasklist saved!";
                        } catch (IOException e) {
                            return "Error!" + e.getMessage();
                        }

                    } catch (Exception e) {
                        // handle the exception if the date or time is not the correct format
                        return "Oh no! That's not a correct date or time format!";
                    }
                }
            }

            if (task.contains("/from ")) {
                String[] sections = task.split("/from | /to ");
                if (command.equals("event")) {
                    // add to tasklist
                    String taskDesc = sections[0];
                    String start = sections[1];
                    String end = sections[2];
                    try {
                        // attempt to parse the strings into a LocalDateTime object
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime dateTimeStart = LocalDateTime.parse(start, formatter);
                        LocalDateTime dateTimeEnd = LocalDateTime.parse(end, formatter);
                        tasks.add(new Event(taskDesc, dateTimeStart, dateTimeEnd));

                        try {
                            storage.saveData(tasks);
                            return ui.getTaskAddedMsg(tasks) + "\n Tasklist saved!";
                        } catch (IOException e) {
                            return "Error!" + e.getMessage();
                        }

                    } catch (Exception e) {
                        // handle the exception if the date or time is not the correct format
                        return "Oh no! That's not a correct date or time format!";
                    }
                }
            }
        }
        return "Unknown Error :(";
    }

}
