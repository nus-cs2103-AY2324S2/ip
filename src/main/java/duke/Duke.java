package duke;

import duke.command.Command;
import duke.exceptions.ChatException;

/**
 * The Chatbot.
 */
public class Duke {
    private TaskList taskList;

    /**
     * Constructor.
     */
    public Duke() {
        try {
            this.taskList = new TaskList(Storage.load());
        } catch (ChatException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns what Duke is supposed to say in response to userInput.
     * @param userInput what the user entered.
     * @return Duke's response.
     */
    public String getResponse(String userInput) {
        // Inspired and referenced from
        // https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        try {
            Command command = Parser.parse(userInput);
            return command.execute(this.taskList);
        } catch (ChatException e) {
            return e.getMessage();
        } finally {
            taskList.save();
        }
    }


}
