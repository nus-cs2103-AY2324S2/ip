package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
  protected static void markTaskAsDoneOrUndone(String[] token, ArrayList<Task> tasks) {
    try {
      int index = Integer.parseInt(token[1]) - 1;

      if (index >= 0 && index < tasks.size()) {
        if (token[0].equals("mark")) {
          tasks.get(index).markAsDone();
          Ui.printMarkAsDoneMessage(tasks.get(index));
        } else if (token[0].equals("unmark")) {
          tasks.get(index).markAsUndone();
          Ui.printMarkAsUndoneMessage(tasks.get(index));
        }
      } else {
        Ui.printErrorMessage("Invalid index. Please provide a valid task index.");
      }
    } catch (NumberFormatException e) {
      Ui.printErrorMessage("Please provide a valid numeric index for marking.");
    }
  }

  protected static void addDeadlineTask(String input, ArrayList<Task> tasks) {
    String[] tokenD = input.split("/");

    String by = tokenD[1].substring(3).trim();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    LocalDateTime deadlineDateTime = LocalDateTime.parse(by, formatter);

    Task d = new Deadline(tokenD[0].substring(9).trim(), deadlineDateTime);
    tasks.add(d);
    Ui.printAddTaskMessage(d, tasks);
  }

  protected static void addEventTask(String input, ArrayList<Task> tasks) {
    String[] tokenE = input.split("/");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    LocalDateTime fromDateTime = LocalDateTime.parse(tokenE[1].substring(5).trim(), formatter);
    LocalDateTime toDateTime = LocalDateTime.parse(tokenE[2].substring(3).trim(), formatter);

    Task e = new Events(tokenE[0].substring(6).trim(), fromDateTime, toDateTime);
    tasks.add(e);
    Ui.printAddTaskMessage(e, tasks);
  }

  protected static void addTodoTask(String input, ArrayList<Task> tasks) {
    if (input.substring(4).trim().isEmpty()) {
      Ui.printErrorMessage("No description found for your todo.");
    } else {
      Task t = new Todos(input.substring(4).trim());
      tasks.add(t);
      Ui.printAddTaskMessage(t, tasks);
    }
  }

  protected static void removeTask(String[] token, ArrayList<Task> tasks) {
    try {
      int index = Integer.parseInt(token[1]) - 1;

      if (index >= 0 && index < tasks.size()) {
        Ui.printRemoveTaskMessage(tasks.get(index), tasks);
        tasks.remove(index);
      } else {
        Ui.printErrorMessage("Invalid index. Please provide a valid task index.");
      }
    } catch (NumberFormatException e) {
      Ui.printErrorMessage("Please provide a valid numeric index for deletion.");
    }
  }
}
