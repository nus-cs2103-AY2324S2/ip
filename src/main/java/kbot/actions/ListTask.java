package kbot.actions;

import java.lang.StringBuilder;

import kbot.managers.TaskManager;

/**
 * A list command that gives the user a list of all Tasks in the array.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class ListTask extends Command {
    /**
     * Constructor for ListTask.
     */
    public ListTask() {
    }

    /**
     * Prints the Task List, labels them with numbers.
     * 
     * @return The list of Tasks represented by a String.
     */
    @Override
    public String execute() {
        assert TaskManager.getTasks().size() > 0 : "There are no tasks here. Please add a task!";
        if (TaskManager.getTasks().size() == 0) {
            return "There are no tasks here. Please add a task!";
        }
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < TaskManager.getTasks().size(); i++) {
            response.append((i + 1))
                    .append(". ")
                    .append(TaskManager.getTasks().get(i))
                    .append("\n");
        }
        return response.toString();
    }
}
