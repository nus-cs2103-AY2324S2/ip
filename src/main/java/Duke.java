import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import task.Command;
import task.DukeException;
import task.InvalidDataFormatException;
import task.TaskList;
import task.TaskListParser;
import task.UnknownCommandException;

public class Duke {
    private static final String chatbotName = "Sylvia";

    private static final String dataFilePath = "data/duke.txt";

    public Duke() {
    }

    private TaskList list;

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private boolean runCommand(String commandString) {
        // get the first word of the input
        String[] words = commandString.split(" ", 2);
        Command command;
        try {
            command = Command.newCommand(words[0], words.length > 1 ? words[1] : "");
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

    private TaskList readData() {
        try {
            File file = new File(dataFilePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            return TaskListParser.parse(file);
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println("An error occurred while reading data from file " + System.getProperty("user.dir")
                    + dataFilePath + ": " + e.getMessage());
            System.out.println("____________________________________________________________");
            return new TaskList();
        } catch (InvalidDataFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println("e.getBotMessage()");
            System.out.println("____________________________________________________________");
            return new TaskList();
        }
    }

    public void run() {
        this.list = readData();
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
        writeData();
    }

    private void writeData() {
        try {
            TaskListParser.writeToFile(list, new File(dataFilePath));
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println("An error occurred while writing data to file " + System.getProperty("user.dir")
                    + dataFilePath + ": " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.greet();
        chatbot.run();
    }
}
