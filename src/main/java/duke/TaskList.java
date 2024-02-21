package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate the tasks
 */
public class TaskList {
  private static final String SPACING = "     ";
  /**
   * Marks a task as done or undone based on the provided token.
   *
   * @param token The token array containing the command and index.
   * @param tasks The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String markTaskAsDoneOrUndone(String[] token, ArrayList<Task> tasks) {
    assert token != null : "Token array must not be null";
    assert token.length == 2 : "Token array must have 2 elements";
    assert tasks != null : "Tasks list must not be null";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      if(token.length < 2) {
        return "[E-ERROR? Oh, good grief... ERROR] Missing task index!";
      }

      int index = Integer.parseInt(token[1]) - 1;

      boolean isLessThanZero = index < 0;
      boolean isGreaterEqualSize = index >= tasks.size();
      boolean isInvalidIndex = isLessThanZero || isGreaterEqualSize;

      if (isInvalidIndex) {
        return "[E-ERROR? Oh, good grief... ERROR] Invalid task index!";
      }

      if (token[0].equals("mark")) {
        if (tasks.get(index).getIsDone()) {
          return "Uh... Seem like this task was already marked as done...";
        }
        tasks.get(index).markAsDone();
        stringBuilder.append("Okay... sigh. I've marked this task as done:\n");
        stringBuilder.append(SPACING).append(tasks.get(index));
      } else if (token[0].equals("unmark")) {
        if (!tasks.get(index).getIsDone()) {
          return "Uh... Seem like this task was already marked as undone...";
        }
        tasks.get(index).markAsUndone();
        stringBuilder.append("Okay... sigh. I've marked this task as not done:\n");
        stringBuilder.append(SPACING).append(tasks.get(index));
      }
    } catch (NumberFormatException e) {
      stringBuilder.append("[E-ERROR? Oh, good grief... ERROR] Invalid numeric index!");
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
    assert input != null : "Token array must not be null";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      String[] tokenD = input.split("/");
      String by = tokenD[1].substring(3).trim();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
      LocalDateTime deadlineDateTime = LocalDateTime.parse(by, formatter);

      Task d = new Deadline(tokenD[0].substring(9).trim(), deadlineDateTime);
      tasks.add(d);
      stringBuilder.append("Okay... sigh. I've added this task:\n");
      stringBuilder.append(SPACING).append(d).append("\n");
      stringBuilder.append("Now you have " + tasks.size() + " tasks in the list.");
    } catch (Exception e) {
      stringBuilder.append("[E-ERROR? Oh, good grief... ERROR] Invalid format!\n\n" +
              "deadline TASK /by DD-MM-YYYY HHMM");
    }
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
    assert input != null : "Token array must not be null";
    StringBuilder stringBuilder = new StringBuilder();

    try {
      String[] tokenE = input.split("/");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
      LocalDateTime fromDateTime = LocalDateTime.parse(tokenE[1].substring(5).trim(), formatter);
      LocalDateTime toDateTime = LocalDateTime.parse(tokenE[2].substring(3).trim(), formatter);

      Task e = new Events(tokenE[0].substring(6).trim(), fromDateTime, toDateTime);
      tasks.add(e);
      stringBuilder.append("Okay... sigh. I've added this task:\n");
      stringBuilder.append(SPACING).append(e).append("\n");
      stringBuilder.append("Now you have " + tasks.size() + " tasks in the list.");
    } catch (Exception e) {
      stringBuilder.append("[E-ERROR? Oh, good grief... ERROR] Invalid format!\n\n" +
              "event TASK /from DD-MM-YYYY HHMM /to DD-MM-YYYY HHMM");
    }
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
    assert input != null : "Token array must not be null";
    StringBuilder stringBuilder = new StringBuilder();

    if (input.substring(4).trim().isEmpty()) {
      return "[E-ERROR? Oh, good grief... ERROR] Invalid format!\n\n" +
              "todo TASK";
    }
    Task t = new Todos(input.substring(4).trim());
    tasks.add(t);
    stringBuilder.append("Okay... sigh. I've added this task:\n");
    stringBuilder.append(SPACING).append(t).append("\n");
    stringBuilder.append("Now you have " + tasks.size() + " tasks in the list.");

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
    assert token != null : "Token array must not be null";
    assert token.length == 2 : "Token array must have 2 elements";
    assert tasks != null : "Tasks list must not be null";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      if(token.length < 2) {
        return "[E-ERROR? Oh, good grief... ERROR] Missing task index!";
      }

      int index = Integer.parseInt(token[1]) - 1;

      boolean isLessThanZero = index < 0;
      boolean isGreaterEqualSize = index >= tasks.size();
      boolean isInvalidIndex = isLessThanZero || isGreaterEqualSize;

      if (isInvalidIndex) {
        return "[E-ERROR? Oh, good grief... ERROR] Invalid task index!";
      }
      stringBuilder.append("Okay... sigh. I've removed this task:\n");
      stringBuilder.append(SPACING).append(tasks.get(index)).append("\n");
      stringBuilder.append("Now you have " + (tasks.size()-1) + " tasks in the list.");
      tasks.remove(index);
    } catch (NumberFormatException e) {
      stringBuilder.append("[E-ERROR? Oh, good grief... ERROR] Invalid numeric index!");
    }
    return stringBuilder.toString();
  }

  /**
   * Updates details of an existing task.
   *
   * @param token The token array containing the command and index.
   * @param newDescription The new description for the task.
   * @param tasks      The list of tasks.
   * @return A string message indicating the result of the operation.
   */
  protected static String updateTask(String[] token, String newDescription, ArrayList<Task> tasks) {
    assert tasks != null : "Tasks list must not be null";
    StringBuilder stringBuilder = new StringBuilder();
    try {
      if(token.length <= 2) {
        return "[E-ERROR? Oh, good grief... ERROR] Invalid format!\n\n" +
                "update INDEX TASK";
      }
      Task taskToUpdate = tasks.get(Integer.parseInt(token[1]) - 1);
      if (taskToUpdate instanceof Deadline) {
        Deadline deadlineTask = (Deadline) taskToUpdate;
        deadlineTask.setDescription(newDescription.substring(9));
      } else if (taskToUpdate instanceof Events) {
        Events eventTask = (Events) taskToUpdate;
        eventTask.setDescription(newDescription.substring(9));
      } else if (taskToUpdate instanceof Todos) {
        taskToUpdate.setDescription(newDescription.substring(9));
      } else {
        return "[ERROR] Unsupported task type!";
      }
      stringBuilder.append("Okay... sigh. Task updated successfully:\n");
      stringBuilder.append(SPACING).append(taskToUpdate).append("\n");
    } catch (IndexOutOfBoundsException e) {
      stringBuilder.append("[E-ERROR? Oh, good grief... ERROR] Invalid task index!");
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
    assert keyword != null : "Token array must not be null";
    assert tasks != null : "Tasks list must not be null";
    StringBuilder stringBuilder = new StringBuilder();
    ArrayList<Task> matchingTasks = new ArrayList<>();

    if (keyword.substring(4).isEmpty()) {
      return "[E-ERROR? Oh, good grief... ERROR] Missing task index!";
    }
    for (Task task : tasks) {
      if (task.toString().contains(keyword.substring(5))) {
        matchingTasks.add(task);
      }
    }
    if(matchingTasks.size() == 0) {
      return "Uh... Seems like there are no match found...";
    }
    stringBuilder.append("Okay... sigh. Here are the matching tasks in your list:\n");
    for (int i = 1; i <= matchingTasks.size(); i++) {
      stringBuilder.append(SPACING).append(i).append(".").append(matchingTasks.get(i - 1)).append("\n");
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
        throw new DukeException("Your task list is empty...");
      }
      stringBuilder.append("Okay... sigh. Here are the tasks in your list:\n");
      for (int i = 1; i <= tasks.size(); i++) {
        stringBuilder.append(SPACING).append(i).append(".").append(tasks.get(i - 1)).append("\n");
      }
    } catch (DukeException e) {
      stringBuilder.append(e.getMessage()).append("\n");
    }
    return stringBuilder.toString();
  }
}
