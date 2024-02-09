package sirduke;
import java.io.IOException;
import java.util.Scanner;

import command.Command;
import dukeexceptions.DeadlineEmptyException;
import dukeexceptions.EventEmptyException;
import dukeexceptions.InvalidCmd;
import handler.CommandsHandler;
import handler.DataHandler;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import msg.Msg;
import msg.StdMsgs;
import ui.Ui;

/**
 * This class represents the Sir Duke Chatbot
 */
public class SirDuke {
    /** The TaskList object that Sir Duke will use to store his tasks */
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    public SirDuke(String filePath) {
        ui = new Ui();
        // have storage and taskList to have a bidirectional navigability
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage, ui);
        storage.setTasks(tasks);
    }

    public void run() {
        ui.welcome();
        Boolean isBye;
        while (true) {
            try {
                String cmd = ui.readCommand();
                Command c = parser.parseCommand(cmd);
                c.execute(task, ui);
            } catch (DukeException e) {
                ui.printErr(e);
            }
        }
    }

    /**
     * Runs Sir Duke
     *
     * @param args String arguments to be passes by User
     * @throws InvalidCmd If command is not in Commands
     */
    public static void main(String[] args) throws IOException {
        // load tasks
        DataHandler.loadData(items);
        Scanner sc = new Scanner(System.in);
        // Should I create a profile about the user by having them answer a few questions?
        // welcome_msg
        StdMsgs.LOGO.print();
        StdMsgs.WELCOME.print();
        // await input from user
        String userInput = "";
        while (true) {
            try {
                userInput = sc.nextLine();
                isBye = CommandsHandler.handleCommand(userInput, items);
                if (isBye) {
                    return;
                }
            } catch (InvalidCmd| DeadlineEmptyException | EventEmptyException err) {
                new Msg(err.toString()).print();
            }
        }
    }
}
