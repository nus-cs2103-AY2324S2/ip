package chatbot;

import java.io.FileNotFoundException;

public class Alfred {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Alfred(String filepath) throws FileNotFoundException {
        this.storage = new Storage(filepath);
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.tasks = storage.processData(tasks);
    }

    public String getResponse(String input) {
        try {
        switch (input.trim()) {
        case "bye":
            storage.updateData(tasks);
            // return something and GUI/parser decides the message?
            return bye();
        case "list":
            return tasks.toString();
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
                return tasks.addFromInput(input);
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
        return("Hello Master Bruce\nWhat can I do for you?");
    }

    /**
     * Bids farewell to the user.
     * @return The farewell message.
     */
    public String bye() {
        return("Bye. Hope to see you again soon!");
    }
}