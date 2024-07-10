package duke.core;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Chatbot that can interact with user commands.
 */

public class Chatbot {

    private final String name;
    private TaskList tasklist;

    /**
     * Initialises a Chatbot with a custom name and a tasklist.
     *
     * @param name chatbot name
     */
    public Chatbot(String name) {
        this.name = name;
        this.tasklist = new TaskList();
    }

    /**
     * Processes a single user input and returns the chatbot's response.
     *
     * @param input the user input
     * @return the response of the chatbot
     */
    public String getResponse(String input) {
        StringBuilder responseBuilder = new StringBuilder();

        if (input.equalsIgnoreCase("bye")) {
            responseBuilder.append(Ui.bye(tasklist));
        } else if (input.contains("list")) {
            responseBuilder.append(Ui.listTasks(tasklist));
        } else if (input.contains("mark")) {
            String[] parts = input.split("\\s+", 2);
            responseBuilder.append(Ui.mark(tasklist, (parts[1]), !parts[0].contains("un")));
        } else if (input.contains("delete")) {
            String[] parts = input.split("\\s+", 2);
            responseBuilder.append(Ui.delete(tasklist, parts[1]));
        } else if (input.contains("find")) {
            String[] parts = input.split("\\s+", 2);
            responseBuilder.append(Ui.find(tasklist, parts[1]));
        } else if (input.contains("sort")) {
            responseBuilder.append(Ui.sort(tasklist));
        } else {
            responseBuilder.append(Ui.addTask(tasklist, input));
        }
        return responseBuilder.toString();
    }
}
