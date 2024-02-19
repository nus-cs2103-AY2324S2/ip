package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate the tasks
 */
public class TaskList {
  /**
   * Marks a task as done or undone based on the provided token.
   *
   * @param token The token array containing the command and index.
   * @param tasks The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String markTaskAsDoneOrUndone(String[] token, ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      int index = Integer.parseInt(token[1]) - 1;

      if (index >= 0 && index < tasks.size()) {
        if (token[0].equals("mark")) {
          if (tasks.get(index).getIsDone()) {
            stringBuilder.append("     This task was already marked as done previously.\n");
          } else {
            tasks.get(index).markAsDone();
            stringBuilder.append("     Nice! I've marked this task as done:\n");
            stringBuilder.append("       ").append(tasks.get(index));
          }
        } else if (token[0].equals("unmark")) {
          if (!tasks.get(index).getIsDone()) {
            stringBuilder.append("     This task was already not marked as done previously.\n");
          } else {
            tasks.get(index).markAsUndone();
            stringBuilder.append("     OK, I've marked this task as not done yet:\n");
            stringBuilder.append("       ").append(tasks.get(index));
          }
        }
      } else {
        stringBuilder.append("Invalid index. Please provide a valid task index.");
      }
    } catch (NumberFormatException e) {
      stringBuilder.append("Please provide a valid numeric index for marking.");
    }
    return stringBuilder.toString();
  }

  /**
   * Adds a deadline task to the list of tasks.
   *
   * @param input The input string containing the task description and deadline.
   * @param tasks The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String addDeadlineTask(String input, ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();

    String[] tokenD = input.split("/");
    String by = tokenD[1].substring(3).trim();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    LocalDateTime deadlineDateTime = LocalDateTime.parse(by, formatter);

    Task d = new Deadline(tokenD[0].substring(9).trim(), deadlineDateTime);
    tasks.add(d);
    stringBuilder.append("     Got it. I've added this task:\n");
    stringBuilder.append("       ").append(d).append("\n");
    stringBuilder.append("     Now you have " + tasks.size() + " tasks in the list.");
    return stringBuilder.toString();
  }

  /**
   * Adds an event task to the list of tasks.
   *
   * @param input The input string containing the task description and event time frame.
   * @param tasks The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String addEventTask(String input, ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();
    String[] tokenE = input.split("/");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    LocalDateTime fromDateTime = LocalDateTime.parse(tokenE[1].substring(5).trim(), formatter);
    LocalDateTime toDateTime = LocalDateTime.parse(tokenE[2].substring(3).trim(), formatter);

    Task e = new Events(tokenE[0].substring(6).trim(), fromDateTime, toDateTime);
    tasks.add(e);
    stringBuilder.append("     Got it. I've added this task:\n");
    stringBuilder.append("       ").append(e).append("\n");
    stringBuilder.append("     Now you have " + tasks.size() + " tasks in the list.");
    return stringBuilder.toString();
  }

  /**
   * Adds a todo task to the list of tasks.
   *
   * @param input The input string containing the todo description.
   * @param tasks The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String addTodoTask(String input, ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();
    if (input.substring(4).trim().isEmpty()) {
      stringBuilder.append("No description found for your todo.");
    } else {
      Task t = new Todos(input.substring(4).trim());
      tasks.add(t);
      stringBuilder.append("     Got it. I've added this task:\n");
      stringBuilder.append("       ").append(t).append("\n");
      stringBuilder.append("     Now you have " + tasks.size() + " tasks in the list.");
    }
    return stringBuilder.toString();
  }

  /**
   * Removes a task from the list of tasks based on the provided index.
   *
   * @param token The token array containing the command and index.
   * @param tasks The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String removeTask(String[] token, ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      int index = Integer.parseInt(token[1]) - 1;

      if (index >= 0 && index < tasks.size()) {
        stringBuilder.append("     Noted. I've removed this task:\n");
        stringBuilder.append("       ").append(tasks.get(index)).append("\n");
        stringBuilder.append("     Now you have " + (tasks.size()-1) + " tasks in the list.");
        tasks.remove(index);
      } else {
        stringBuilder.append("Invalid index. Please provide a valid task index.");
      }
    } catch (NumberFormatException e) {
      stringBuilder.append("Please provide a valid numeric index for deletion.");
    }
    return stringBuilder.toString();
  }

  /**
   * Finds tasks containing a specified keyword and prints them using the provided UI.
   *
   * @param keyword The keyword to search for within the task descriptions.
   * @param tasks   The list of tasks to search through.
   * @return A string message indicating the result of the operation.
   */
  protected static String findTask(String keyword, ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();
    ArrayList<Task> matchingTasks = new ArrayList<>();
    for (Task task : tasks) {
      if (task.toString().contains(keyword.substring(5))) {
        matchingTasks.add(task);
      }
    }
    stringBuilder.append("     Here are the matching tasks in your list:\n");
    for (int i = 1; i <= matchingTasks.size(); i++) {
      stringBuilder.append("       ").append(i).append(".").append(matchingTasks.get(i - 1)).append("\n");
    }
    return stringBuilder.toString();
  }

  /**
   * Returns a string representation of the task list.
   *
   * @param tasks The list of tasks.
   * @return The string representation of the task list.
   * @return A string message indicating the result of the operation.
   */
  protected static String getTaskList(ArrayList<Task> tasks) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      if (tasks.isEmpty()) {
        throw new DukeException("Your task list is empty.");
      } else {
        stringBuilder.append("     Here are the tasks in your list\n");
        for (int i = 1; i <= tasks.size(); i++) {
          stringBuilder.append("       ").append(i).append(".").append(tasks.get(i - 1)).append("\n");
        }
      }
    } catch (DukeException e) {
      stringBuilder.append("       ").append(e.getMessage()).append("\n");
    }
    return stringBuilder.toString();
  }
}
