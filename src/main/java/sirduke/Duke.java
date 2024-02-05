package sirduke;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dukeexecpetions.DeadlineEmptyException;
import dukeexecpetions.EventEmptyException;
import dukeexecpetions.InvalidCmd;
import handler.handleCommands;
import msg.Msg;
import msg.StdMsgs;
import items.Items;
import java.io.File;

/**
 * This class represents the Sir Duke Chatbot
 */
public class Duke {
    /** The Items object that Sir Duke will use to store his tasks */
    private static Items items = new Items();

    /**
     * Runs Sir Duke
     *
     * @param args String arguments to be passes by User
     * @throws InvalidCmd If command is not in Commands
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        File data = new File("data/sirDuke.txt");
        if (data.exists()) {
            // parse through the text line by line
            Scanner parser = new Scanner(data);
            while(parser.hasNext()) {

            }
        }
        // if ./data/duke.txt does not exist
        // load data from txt file

        // Should I create a profile about the user by having them answer a few questions?
        // welcome_msg
        System.out.println(StdMsgs.LOGO);
        System.out.println(StdMsgs.WELCOME);
        // await input from user
        String userInput = "";
        Boolean isBye;
        while (true) {
            try {
                userInput = sc.nextLine();
                isBye = handleCommands.handleCommand(userInput, items);
                if (isBye) {
                    return;
                }
            } catch (InvalidCmd| DeadlineEmptyException | EventEmptyException err) {
                System.out.println(new Msg(err.toString()));
            }
        }
    }
}
