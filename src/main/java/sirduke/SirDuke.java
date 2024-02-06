package sirduke;
import java.io.IOException;
import java.util.Scanner;
import dukeexecpetions.DeadlineEmptyException;
import dukeexecpetions.EventEmptyException;
import dukeexecpetions.InvalidCmd;
import handler.CommandsHandler;
import handler.DataHandler;
import items.TaskList;
import msg.Msg;
import msg.StdMsgs;

/**
 * This class represents the Sir Duke Chatbot
 */
public class SirDuke {
    /** The TaskList object that Sir Duke will use to store his tasks */
    private static TaskList items = new TaskList();

    public SirDuke(String filePath) {

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
        Boolean isBye;
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
