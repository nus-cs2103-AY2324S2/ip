package Duke.duke;

import Duke.Activities.*;
import Duke.Commands.Command;
import Duke.Exception.CommandException;
import Duke.phrase.phrase;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws CommandException {
        Scanner scanner = new Scanner(System.in);
        ActivityList activityList = new ActivityList();
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Command command = phrase.phraseCommand(line);

                assert command != null;
                command.execute(activityList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}




