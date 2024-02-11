package podz;

import podz.commands.ByeCommand;
import podz.commands.Command;
import podz.parser.Parser;
import podz.storage.Storage;
import podz.task.TaskList;

/**
 * Represents the entry of a task management application.
 */
public class Podz {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private boolean isExit;

    public Podz() {
        storage = new Storage("./data/podz.txt");
        tasks = new TaskList(this.storage.loadTasks());
        parser = new Parser();
        this.isExit = false;
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = this.parser.parseCommand(input);
            command.setTasks(this.tasks);
            String responseStr = command.execute();
            if (command instanceof ByeCommand) {
                isExit = true;
            }
            return responseStr;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getGreeting() {
        return "Hello! I'm Podz.\n"
                + "What can I do for you?";
    }

    public boolean hasExit() {
        return this.isExit;
    }
}