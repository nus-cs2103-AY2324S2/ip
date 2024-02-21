package chatbot;

import java.io.FileNotFoundException;

/**
 * Represents the chatbot.
 */
public class Alfred {
    /**
     * The storage of the chatbot.
     */
    private Storage storage;
    /**
     * The list of tasks.
     */
    private TaskList tasks;
    /**
     * The parser.
     */
    private Parser parser;
    /**
     * Creates a chatbot.
     * @param filepath The filepath of the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public Alfred(String filepath) throws FileNotFoundException {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.tasks = storage.processData(tasks);
    }

    /**
     * Gets the response from the chatbot.
     * @param input The input from the user.
     * @return The response from the chatbot.
     */
    public String getResponse(String input) {
        try {
            switch (input.trim()) {
            case "bye":
                storage.updateData(tasks);
                // return something and GUI/parser decides the message?
                return bye();
            case "list":
                return tasks.toString();
            case "urgent":
                return tasks.getUrgentTasks().toString();
            default:
                if (input.startsWith("unmark")) {
                    int idx = parser.parseIndex(parser.parseDescription(input));
                    return tasks.unmarkList(idx);
                } else if (input.startsWith("mark")) {
                    int idx = parser.parseIndex(parser.parseDescription(input));
                    return tasks.markList(idx);
                } else if (input.startsWith("delete")) {
                    int idx = parser.parseIndex(parser.parseDescription(input));
                    return tasks.deleteList(idx);
                } else if (input.startsWith("find")) {
                    String keyword = parser.parseDescription(input).trim();
                    TaskList foundList = tasks.findByKeyword(keyword);
                    return foundList.toString();
                } else if (input.startsWith("date")) {
                    String date = parser.parseDescription(input).trim();
                    TaskList dateList = tasks.findByDate(parser.parseDateTime(date));
                    return dateList.toString();
                } else {
                    return tasks.addTaskFromInput(input);
                }
            }
        } catch (AlfredException e) {
            return e.toString();
        }
    }

    /**
     * Greets the user.
     * @return The greeting message.
     */
    public String greet() {
        return "Hello Master Bruce\nWhat can I do for you?";
    }

    /**
     * Bids farewell to the user.
     * @return The farewell message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
