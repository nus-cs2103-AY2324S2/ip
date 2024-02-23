package Dude;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Dude class is the entry point of the application that manages tasks.
 * It is responsible for initializing the system, loading existing tasks from a
 * file,
 * and processing user commands.
 */
public class Dude {
    TaskList tasks;
    Ui ui = new Ui();

    public Dude() {
        try {
            tasks = new TaskList(TaskStorage.loadTasksFromFile());
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert tasks != null : "TaskList should not be null";

        try {
            Command cmd = Parser.getCommand(input);
            int index;

            switch (cmd.action) {
                case BYE:
                    return ui.showGoodbye();
                case LIST:
                    return ui.showTaskList(tasks.toArrayList());
                case DONE:
                    index = Parser.getDoneIndex(input);

                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsDone();
                    return ui.showDone(tasks.get(index));
                case UNDONE:
                    index = Parser.getDoneIndex(input);

                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    return ui.showUndone(tasks.get(index));
                case DELETE:
                    index = Parser.getDeleteIndex(input, tasks.size());
                    Task removedTask = tasks.remove(index);
                    return ui.showDelete(removedTask);
                case FIND:
                    String keyword = cmd.info;
                    ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                    return ui.showTaskList(matchingTasks);
                case TODO:
                    assert cmd.info != null : "Command info missing";
                    ToDo todo = new ToDo(cmd.info);
                    tasks.add(todo);
                    return ui.showAddTask(todo);
                case DEADLINE:
                    assert cmd.info != null : "Command info missing";
                    Deadline deadline = Deadline.fromCmd(cmd);
                    tasks.add(deadline);
                    return ui.showAddTask(deadline);
                case EVENT:
                    assert cmd.info != null : "Command info missing";
                    Event newEvent = Event.fromCmd(cmd);
                    tasks.add(newEvent);
                    return ui.showAddTask(newEvent);
            }

            TaskStorage.saveTasksToFile(tasks.toArrayList());
        } catch (NumberFormatException e) {
            return ui.showError("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
        return "";
    }

}



