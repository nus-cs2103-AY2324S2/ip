import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import task.Command;
import task.CommandParser;
import task.DukeException;
import task.Storage;
import task.StorageLoadException;
import task.StorageSaveException;
import task.TaskList;
import task.TaskListParser;
import task.UnknownCommandException;

public class Duke {
    private static final String chatbotName = "Sylvia";

    private static final String dataFilePath = "data/duke.txt";

    private Storage storage;

    public Duke() {
        this.storage = new Storage(dataFilePath);
    }

    private TaskList list;

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private boolean runCommand(String commandString) {
        CommandParser parser = new CommandParser();
        Command command;
        try {
            command = Command.parse(commandString, parser);
            System.out.println("____________________________________________________________");
            boolean loopSignal = command.execute(list);
            System.out.println("____________________________________________________________");
            return loopSignal;
        } catch (UnknownCommandException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getBotMessage());
            System.out.println("____________________________________________________________");
        } catch (DukeException e) {
            System.out.println(e.getBotMessage());
            System.out.println("____________________________________________________________");
        }
        return true; // bot should continue running after invalid user input
    }

    public void run() {
        try {
            list = storage.load();
        } catch (StorageLoadException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getBotMessage());
            System.out.println("____________________________________________________________");
            list = new TaskList();
        }
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean loopSignal = true;

        while (loopSignal) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.out.println("____________________________________________________________");
                System.out.println("Sorry, something went wrong: " + e.getMessage());
                System.out.println("____________________________________________________________");
                break;
            }
            loopSignal = runCommand(input);
        }
        // only write data to file when the bot is about to exit
        try {
            storage.save(list);
        } catch (StorageSaveException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getBotMessage());
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.greet();
        chatbot.run();
    }
}
