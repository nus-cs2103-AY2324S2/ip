package parser;

import commands.*;
import exceptions.BenException;

import java.time.LocalDate;

public class Parser {
  public static Command parse(String fullCommand) throws BenException {
    String[] tokens = fullCommand.split(" ", 2);
    String command = tokens[0];


      switch (command) {
        case "bye":
          return new ExitCommand();

        case "list":
          return new ListCommand();

        case "mark": {
          // empty field
          if (tokens.length < 2) {
            throw new BenException("   Key in a value!");
          }

          // obtain index of task within tasks.TaskList
          int index = Integer.parseInt(tokens[1]) - 1;

          return new MarkCommand(index);
        }

        case "unmark": {
          // empty field
          if (tokens.length < 2) {
            throw new BenException("   Key in a value!");
          }

          // obtain index of task within tasks.TaskList
          int index = Integer.parseInt(tokens[1]) - 1;

          return new UnmarkCommand(index);
        }

        case "todo": {
          // empty to-do
          if (tokens.length < 2) {
            throw new BenException("   OOPS!!! The description of a todo cannot be empty.");
          }

          String description = tokens[1];

          return new TodoCommand(description);
        }

        case "deadline": {
          // empty deadline
          if (tokens.length < 2) {
            throw new BenException("   OOPS!!! The description of a deadline cannot be empty.");
          }

          // delimiting string
          String information = tokens[1];
          String[] descTokens = information.split(" /by ");
          String description = descTokens[0];
          String by = descTokens[1];

          LocalDate deadline = LocalDate.parse(by);

          return new DeadlineCommand(description, deadline);
        }

        case "event": {
          // empty event
          if (tokens.length < 2) {
            throw new BenException("   OOPS!!! The description of an event cannot be empty.");
          }

          // delimiting string
          String information = tokens[1];
          String[] descTokens = information.split(" /from ");
          String description = descTokens[0];
          String dates = descTokens[1];
          String[] dateTokens = dates.split(" /to ");
          String startDate = dateTokens[0];
          String endDate = dateTokens[1];

          return new EventCommand(description, startDate, endDate);
        }

        case "delete": {
          // empty field
          if (tokens.length < 2) {
            throw new BenException("   Key in a value");
          }

          int index = Integer.parseInt(tokens[1]) - 1;

          return new DeleteCommand(index);
        }

        default:
          throw new BenException("   OOPS!!! I'm sorry, but I don't know what that means :-(");
      }
  }
}
