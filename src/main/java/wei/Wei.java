package wei;

import java.io.FileNotFoundException;
import java.io.IOException;

import commands.Command;
import exceptions.WeiException;
import parser.Parser;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Represents a chatbot that can keep track of your tasks.
 */
public class Wei {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a Wei chatbot.
     */
    public Wei() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage("./src/main/resources/data/history.txt");
            tasks = storage.read();
        } catch (FileNotFoundException e) {
            System.out.println("error reading tasks file");
        } catch (IOException e) {
            System.out.println("error creating tasks file");
        } catch (WeiException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Greets the user.
     *
     * @return reply message.
     */
    public String greet() {
        return ui.greet();
    }

    /**
     * Reminds user to complete tasks.
     *
     * @return list of tasks.
     */
    public String remind() {
        String remindList = tasks.getRemindList();
        return ui.showReminder(remindList);
    }

    /**
     * Generates response to the user.
     *
     * @param input user input.
     * @return reply message.
     */
    public String run(String input) {
        String reply = "";
        try {
            Command userCommand = parser.parse(input);
            reply = userCommand.execute(tasks, ui, storage);
        } catch (WeiException e) {
            reply = e.getMessage();
        }
        return reply;
    }
}
