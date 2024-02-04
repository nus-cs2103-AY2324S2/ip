package duke;

import duke.command.*;
import duke.exception.*;
import duke.parser.*;
import duke.ui.*;
import duke.task.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Optional;
import java.util.NoSuchElementException;


public class Duke {
    public static void main(String[] args) {
        System.out.printf(Messages.WELCOME);

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        Command<List<Task>> currentCommand = null;

        while (true) {
            try {
                System.out.printf("\n-> ");
                String userInput = scanner.nextLine();

                // Parse user input
                Command<List<Task>> parsedCommand = Parser.parseInput(userInput);

                // Execute the command
                if (parsedCommand != null) {
                    currentCommand = parsedCommand;
                    currentCommand.execute(tasks);
                }

                // Exit condition
                if (parsedCommand.getCommand().equals("bye")) {
                    System.out.printf(Messages.EXIT);
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reading user input. Exiting.");
                break;
            }

        }

        scanner.close();
    }
}
