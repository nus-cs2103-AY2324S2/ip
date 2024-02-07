package fireraya.main;

import fireraya.command.*;
import fireraya.exception.FirerayaException;
import fireraya.exception.InvalidNumOfArgsException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {

    static Command parse(String command) throws FirerayaException {

        try {

            String[] all = command.split(" ");
            String keyword = all[0];
            int arrLen = all.length;


            if (keyword.equals("bye")) {
                return new ExitCommand();
            }

            if (keyword.equals("list")) {
                return new ListCommand();
            }

            if (keyword.equals("mark")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                int curr = Integer.parseInt(all[1]) - 1;
                return new MarkCommand(curr);
            }

            if (keyword.equals("unmark")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                int curr = Integer.parseInt(all[1]) - 1;
                return new UnmarkCommand(curr);
            }

            if (keyword.equals("todo")) {
                if (arrLen == 1) {
                    throw new FirerayaException("Error! Description of a todo cannot be empty");
                }
                String description = String.join(" ", Arrays.copyOfRange(all, 1, arrLen));
                return new ToDoCommand(description);
            }

            if (keyword.equals("deadline")) {
                int index = -1;
                int i = 0;
                String breakpoint = "/by";
                for (String element : all) {
                    if (element.equals(breakpoint)) {
                        index = i;
                        break;
                    }
                    i++;
                }
                if (index == -1) {
                    throw new FirerayaException("No /by detected in deadline");
                }

                String description = String.join(" ", Arrays.copyOfRange(all, 1, index));
                String deadline = String.join(" ", Arrays.copyOfRange(all, index + 1, arrLen));

                return new DeadlineCommand(description, deadline);
            }


            if (keyword.equals("event")) {
                int indexf = -1;
                int indext = -1;
                int f = 0;
                int t = 0;
                String fromPoint = "/from";
                String toPoint = "/to";

                for (String element : all) {
                    if (element.equals(fromPoint)) {
                        indexf = f;
                        break;
                    }
                    f++;
                }
                if (indexf == -1) {
                    throw new FirerayaException("No /from detected in deadline");
                }
                for (String element : all) {
                    if (element.equals(toPoint)) {
                        indext = t;
                        break;
                    }
                    t++;
                }
                if (indext == -1) {
                    throw new FirerayaException("No /to detected in deadline");
                }

                String description = String.join(" ", Arrays.copyOfRange(all, 1, indexf));
                String from = String.join(" ", Arrays.copyOfRange(all, indexf + 1, indext));
                String to = String.join(" ", Arrays.copyOfRange(all, indext + 1, arrLen));
                return new EventCommand(description, from, to);
            }

            if (keyword.equals("delete")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                int curr = Integer.parseInt(all[1]) - 1;

                return new DeleteCommand(curr);
            }

            throw new FirerayaException("Error: command not supported");

        } catch (FirerayaException e) {
            throw e;
        }
    }
}

