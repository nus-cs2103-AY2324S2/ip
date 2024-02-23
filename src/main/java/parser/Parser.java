package parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.CalException;
import tasks.Task;

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
                    LocalDateTime dueDate;

                    try {
                        description = line.substring(8, byIndex).strip();
                        if (description.isBlank()) {
                            throw new CalException("Task description not provided");
                        }

                        String by = line.substring(byIndex + 4).strip();
                        if (by.isBlank()) {
                            throw new CalException("Task due date not provided");
                        }
                        dueDate = LocalDateTime.parse(by, Task.INPUT_DATE_FORMAT);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new CalException("Deadline Task is not in the format: " +
                            "deadline (description) /by (due date)!");
                    } catch(DateTimeParseException e) {
                        throw new CalException("Date should be written in the format (23/2/2019 1800)");
                    }

                    return new AddCommand(description, dueDate);
                case "event":
                    int fromIndex = line.indexOf("/from");
                    int toIndex = line.indexOf("/to");
                    LocalDateTime startDate;
                    LocalDateTime endDate;

                    try {
                        description = line.substring(5, fromIndex).strip();
                        if (description.isBlank()) {
                            throw new CalException("Event description not provided");
                        }

                        String startDateStr = line.substring(fromIndex + 5, toIndex).strip();
                        if (startDateStr.isBlank()) {
                            throw new CalException("Event start date not provided");
                        }
                        startDate = LocalDateTime.parse(startDateStr, Task.INPUT_DATE_FORMAT);

                        String endDateStr = line.substring(toIndex + 3).strip();
                        if (endDateStr.isBlank()) {
                            throw new CalException("Event end date not provided");
                        }
                        endDate = LocalDateTime.parse(endDateStr, Task.INPUT_DATE_FORMAT);
                    } catch (StringIndexOutOfBoundsException e){
                        throw new CalException("Event Task is not in the format: " + 
                            "event (description) /from (startDate) /to (endDate)!");
                    } catch(DateTimeParseException e) {
                        throw new CalException("Date should be written in the format (23/2/2019 1800)");
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
