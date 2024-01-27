package duke.Command;

import java.sql.SQLException;

import database.TaskORM;
import duke.Parser;
import exceptions.BadInputException;
import exceptions.BadTaskInputException;

public class AddTaskCommand extends Command {
  public static final String ADD_TODO_COMMAND = "todo";
  public static final String ADD_DEADLINE_COMMAND = "deadline";
  public static final String ADD_EVENT_COMMAND = "event";
  private final String command;
  private final String description;
  public AddTaskCommand(String command, String description) {
    this.command = command;
    this.description = description;
  }

  @Override
  public String execute(TaskORM tm) {
    try {
      task.Task task;
      switch (this.command) {
        case "todo":
          if (this.description == null || this.description.isEmpty()) {
            throw new BadTaskInputException(
              "Details of a todo cannot be empty.",
              "todo <description>",
              "todo read book",
              this.description
            );
          }

          task = tm.createTodo(this.description);
          break;

        case "deadline":
          String[] deadlineDetails = this.description.split(" /by ");

          if (deadlineDetails.length < 2) {
            throw new BadTaskInputException(
              "Details of a deadline must include a deadline.",
              "deadline <description> /by <deadline>",
              "deadline return book /by 2019-12-15",
              this.description
            );
          }

          java.time.LocalDate deadline = Parser.parseDate(deadlineDetails[1]);
          task = tm.createDeadline(deadlineDetails[0], deadline);
          break;

        case "event":
          String[] eventDetails = this.description.split(" /from | /to ");

          if (eventDetails.length < 3) {
            throw new BadTaskInputException(
              "Details of an event must include a start date and an end date.",
              "event <description> /from <start date> /to <end date>",
              "fun event /from 2019-12-1 /to 2019-12-15",
              this.description);
          }

          java.time.LocalDate startDate = Parser.parseDate(eventDetails[1]);
          java.time.LocalDate endDate = Parser.parseDate(eventDetails[2]);

          if (startDate.isAfter(endDate)) {
            throw new BadTaskInputException(
              "Start date cannot be after end date.",
              "event <description> /from <start date> /to <end date>",
              "fun event /from 2019-12-1 /to 2019-12-15",
              this.description);
          }

          task = tm.createEvent(eventDetails[0],startDate, endDate);
          break;

        default:
          throw new IllegalArgumentException("Invalid task type: " + this.command + "\n");
      }
      return "Got it. I've added this task:\n"
        + "   " + task + "\n"
        + String.format("Now you have %d tasks in the list.\n", tm.count());
    } catch (SQLException | BadInputException e) {
      return e.getMessage();
    }
  }
}
