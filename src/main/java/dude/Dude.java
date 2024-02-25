package dude;

import java.util.ArrayList;
import java.io.IOException;


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
            String msg = "";

            switch (cmd.action) {
                case BYE:
                    msg = ui.showGoodbye();
                    break;
                case LIST:
                    msg = ui.showTaskList(tasks.toArrayList());
                    break;
                case DONE:
                    index = Parser.getDoneIndex(input);

                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsDone();
                    msg = ui.showDone(tasks.get(index));
                    break;
                case UNDONE:
                    index = Parser.getDoneIndex(input);

                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    msg = ui.showUndone(tasks.get(index));
                    break;
                case DELETE:
                    index = Parser.getDeleteIndex(input, tasks.size());
                    Task removedTask = tasks.remove(index);
                    msg = ui.showDelete(removedTask);
                    break;
                case FIND:
                    String keyword = cmd.info;
                    ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                    msg = ui.showTaskList(matchingTasks);
                    break;
                case TODO:
                    assert cmd.info != null : "Command info missing";
                    ToDo todo = ToDo.fromCmd(cmd);
                    tasks.add(todo);
                    msg = ui.showAddTask(todo);
                    break;
                case DEADLINE:
                    assert cmd.info != null : "Command info missing";
                    Deadline deadline = Deadline.fromCmd(cmd);
                    tasks.add(deadline);
                    msg = ui.showAddTask(deadline);
                    break;
                case EVENT:
                    assert cmd.info != null : "Command info missing";
                    Event newEvent = Event.fromCmd(cmd);
                    tasks.add(newEvent);
                    msg = ui.showAddTask(newEvent);
                    break;
            }

            TaskStorage.saveTasksToFile(tasks.toArrayList());
            return msg;
        } catch (NumberFormatException e) {
            return ui.showError("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }

}



