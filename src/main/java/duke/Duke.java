package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Messages;


public class Duke {
    private static final Storage storage = new Storage();

    public static void main(String[] args) {
        System.out.printf(Messages.WELCOME);

        TaskList tasks;
        try {
            tasks = storage.load();
            System.out.println(tasks);
        } catch (FileNotFoundException e) {
            System.out.printf("Warning: something went wrong when loading the TaskList\n" +
                    e.getMessage());
            tasks = new TaskList();
        }

        Scanner sc = new Scanner(System.in);
        Command currentCommand = null;


        while (true) {
            try {
                System.out.printf("\n-> ");


                String userInput = sc.nextLine();

                // Parse user input
                Command command = Parser.parseInput(userInput
                    );


                // Execute the command
                command.execute(tasks);
                storage.save(tasks);

                // Exit condition
                if (command.getCommand().equals("bye")) {
                    System.out.printf(Messages.EXIT);
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reading user input. Exiting.");
                break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        sc.close();
    }
}
