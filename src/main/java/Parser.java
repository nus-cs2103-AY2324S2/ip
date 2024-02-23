import java.time.format.DateTimeParseException;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.CalException;

public class Parser {
    public static Command parseCommand(String line) throws CalException {
            if (line.isEmpty()) {
                throw new IllegalArgumentException();
            }

            String[] tokens = line.split(" ");
            String command = tokens[0];
            
            String description = "";
            switch(command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    if (tokens.length < 2) {
                        throw new CalException("Task number not provided!");
                    }

                    return new MarkCommand(Integer.parseInt(tokens[1]));
                case "unmark":
                    if (tokens.length < 2) {
                        throw new CalException("Task number not provided!");
                    }

                    return new UnmarkCommand(Integer.parseInt(tokens[1]));
                case "todo":
                    description = line.substring(4).strip();
                    if (description.isBlank()) {
                        throw new CalException("Task description not provided");
                    }

                    return new AddCommand(description);
                case "deadline":
                    int byIndex = line.indexOf("/by");
                    String by;

                    try {
                        description = line.substring(8, byIndex).strip();
                        if (description.isBlank()) {
                            throw new CalException("Task description not provided");
                        }
                        by = line.substring(byIndex + 4).strip();
                        if (by.isBlank()) {
                            throw new CalException("Task due date not provided");
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new CalException("Deadline Task is not in the format: " +
                            "deadline (description) /by (due date)!");
                    } catch(DateTimeParseException e) {
                        throw new CalException(e.getMessage());
                    }

                    return new AddCommand(description, by);
                case "event":
                    int fromIndex = line.indexOf("/from");
                    int toIndex = line.indexOf("/to");
                    String startDate;
                    String endDate;

                    try {
                        description = line.substring(5, fromIndex).strip();
                        if (description.isBlank()) {
                            throw new CalException("Event description not provided");
                        }
                        startDate = line.substring(fromIndex + 5, toIndex).strip();
                        if (startDate.isBlank()) {
                            throw new CalException("Event start date not provided");
                        }
                        endDate = line.substring(toIndex + 3).strip();
                        if (endDate.isBlank()) {
                            throw new CalException("Event end date not provided");
                        }
                    } catch (StringIndexOutOfBoundsException e){
                        throw new CalException("Event Task is not in the format: " + 
                            "event (description) /from (startDate) /to (endDate)!");
                    } catch(DateTimeParseException e) {
                        throw new CalException(e.getMessage());
                    }

                    return new AddCommand(description, startDate, endDate);
                case "delete":
                    if (tokens.length < 2) {
                        throw new CalException("Task number not provided!");
                    }

                    return new DeleteCommand(Integer.parseInt(tokens[1]));
                default:
                    throw new CalException("Command not recognized.");
            }
    }
}
