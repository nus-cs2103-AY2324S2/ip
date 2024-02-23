package chatbot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Parser class handles user interactions and processes input commands.
 * It contains logic to interpret user commands and to determine the appropriate response
 * or error message, based on the current state and data of the application.
 */

public class Parser {

  public Parser() {}

  /**
   * Checks if the user wants to end the chat.
   *
   * @param words Array of strings representing the user's input, split into words.
   * @return true if the first word is "bye", indicating a request to end the chat; false otherwise.
   */
  public boolean isItDone(String[] words) {
    return words[0].equals("bye");
  }

  /**
   * Processes the user input and performs actions based on the given commands.
   * This method can add, delete, or modify tasks in the task list, mark tasks as done or not done,
   * and handle other user commands. It returns a string response that should be shown to the user.
   * In case of invalid or incomplete commands, it throws specific exceptions.
   *
   * @param io The input string provided by the user.
   * @param words Array of strings representing the user's input, split into words.
   * @param detail The detail part of the user's input, excluding the command word.
   * @param myList The current list of tasks.
   * @param st The storage handler for reading from and writing to the data file.
   * @return A string response based on the user's input and the action performed.
   * @throws EmptyTodoException If a "todo" command is given without a description.
   * @throws EmptyDeadlineException If a "deadline" command is given without a description or deadline.
   * @throws EmptyEventException If an "event" command is given without a description or event time.
   * @throws InvalidException If an invalid command is given.
   */
  String end =
    "\n____________________________________________________________\n";

  public String parseThrough(
    String io,
    String[] words,
    String detail,
    TaskList myList,
    Storage st
  ) {
    try {
      if (words[0].equals("todo") && detail.equals("")) {
        throw new EmptyTodoException();
      } else if (words[0].equals("deadline") && detail.equals("")) {
        throw new EmptyDeadlineException();
      } else if (words[0].equals("event") && detail.equals("")) {
        throw new EmptyEventException();
      } else if (words[0].equals("bye")) {
        return ("Bye. Hope to see you again soon!");
      } else if (words[0].equals("list")) {
        return handleListCommand(myList);
      } else if (words[0].equals("upcoming")) {
        return handleUpcomingCommand(myList);
      } else if (words[0].equals("mark")) {
        return handleMarkCommand(words, myList, st);
      } else if (words[0].equals("unmark")) {
        return handleUnmarkCommand(words, myList, st);
      } else if (words[0].equals("todo")) {
        return handleTodoCommand(detail, myList, st);
      } else if (words[0].equals("deadline")) {
        return handleDeadlineCommand(detail, myList, st);
      } else if (words[0].equals("event")) {
        return handleEventCommand(detail, myList, st);
      } else if (words[0].equals("delete")) {
        return handleDeleteCommand(words, myList, st);
      } else if (words[0].equals("find")) {
        return handleFindCommand(detail, myList);
      } else {
        throw new InvalidException();
      }
    } catch (EmptyTodoException e) {
      return (e.getMessage() + end);
    } catch (EmptyDeadlineException e) {
      return (e.getMessage() + end);
    } catch (EmptyEventException e) {
      return (e.getMessage() + end);
    } catch (InvalidException e) {
      return (e.getMessage() + end);
    }
  }

  /**
   * Generates a string representation of all tasks in the task list.
   * This method iterates through the entire task list, appending each task's
   * string representation to a StringBuilder along with its position in the list,
   * then returns the resulting string.
   *
   * @param myList The TaskList containing the tasks to be listed.
   * @return A String that lists all tasks in my list with their indices.
   */
  public String handleListCommand(TaskList myList) {
    StringBuilder listString = new StringBuilder();
    for (int i = 0; i < myList.len(); i++) {
      int show = i + 1;
      listString.append(show).append(".").append(myList.get(i)).append("\n");
    }
    return listString.toString();
  }

  /**
   * Generates a string representation of all upcoming deadlines in the task list.
   * This method filters tasks for instances of Deadline and appends their index,
   * description, and due date to a StringBuilder. The resulting string includes
   * only tasks that are deadlines.
   *
   * @param myList The TaskList containing the tasks to be filtered and listed.
   * @return A string representation of all Deadline tasks in the list, each prefixed with its index.
   */
  public String handleUpcomingCommand(TaskList myList) {
    StringBuilder listString = new StringBuilder();
    for (int i = 0; i < myList.len(); i++) {
      if (myList.get(i) instanceof Deadline) { // Check if it's an instance of Deadline
        int show = i + 1;
        listString
          .append(show)
          .append(".")
          .append(myList.get(i)) // This will now only append if it's a Deadline
          .append("\n");
      }
    }
    return listString.toString();
  }

  /**
   * Marks a specified task as done and updates the task list file accordingly.
   * This method marks the task at the given index (specified by the second element
   * in the {@code words} array) as done, then rewrites the task list to storage.
   *
   * @param words An array of strings where the second element is the index of the task to be marked as done.
   * @param myList The TaskList containing the task to be marked.
   * @param st The Storage instance used to rewrite the updated task list to file.
   * @return A confirmation message indicating the task has been marked as done, including the task's description.
   */
  public String handleMarkCommand(String[] words, TaskList myList, Storage st) {
    int c = Integer.parseInt(words[1]);
    myList.asDone(c);
    st.rewriteFile(myList.mine());
    return (
      "Nice! I've marked this task as done:\n" + myList.get(c - 1).toString()
    );
  }

  /**
   * Marks a specified task as not done and updates the task list file accordingly.
   * Similar to handleMarkCommand, this method marks the task at the given index
   * (specified by the second element in the {@code words} array) as not done,
   * then rewrites the task list to storage.
   *
   * @param words An array of strings where the second element is the index of the task to be marked as not done.
   * @param myList The TaskList containing the task to be unmarked.
   * @param st The Storage instance used to rewrite the updated task list to file.
   * @return A confirmation message indicating the task has been marked as not done, including the task's description.
   */
  public String handleUnmarkCommand(
    String[] words,
    TaskList myList,
    Storage st
  ) {
    int c = Integer.parseInt(words[1]);
    myList.asNotDone(c);
    st.rewriteFile(myList.mine());
    return (
      "Nice! I've marked this task as not done yet:\n" +
      myList.get(c - 1).toString()
    );
  }

  /**
   * Handles the creation and addition of a new Todo task to the task list.
   * If the detail argument is empty, this method throws an EmptyTodoException.
   * After creating the Todo task, it is added to the task list, and the list
   * is subsequently rewritten to storage.
   *
   * @param detail The description of the Todo task.
   * @param myList The TaskList to which the new task will be added.
   * @param st The Storage instance used to update the tasks file.
   * @return A confirmation message indicating the task has been added, along with the current task count.
   * @throws EmptyTodoException If the detail argument is empty.
   */
  public String handleTodoCommand(String detail, TaskList myList, Storage st)
    throws EmptyTodoException {
    if (detail.equals("")) {
      throw new EmptyTodoException();
    }
    Todo t = new Todo(detail);
    myList.add(t);
    st.rewriteFile(myList.mine());
    return buildTaskAddedMessage(t, myList);
  }

  /**
   * Handles the creation and addition of a new Deadline task to the task list.
   * The detail argument must contain the task description and the due date,
   * separated by "/by". If the detail argument is empty or improperly formatted,
   * this method throws an EmptyDeadlineException. The new Deadline task is then
   * added to the task list, which is rewritten to storage.
   *
   * @param detail The description of the Deadline task, including the due date.
   * @param myList The TaskList to which the new task will be added.
   * @param st The Storage instance used to update the tasks file.
   * @return A confirmation message indicating the task has been added, along with the current task count.
   * @throws EmptyDeadlineException If the detail argument is empty or missing a due date.
   */
  public String handleDeadlineCommand(
    String detail,
    TaskList myList,
    Storage st
  ) throws EmptyDeadlineException {
    if (detail.equals("")) {
      throw new EmptyDeadlineException();
    }
    String[] parts = detail.split("\\s*/by\\s*", 2);
    Deadline t = new Deadline(parts[0], parts[1]);
    myList.add(t);
    st.rewriteFile(myList.mine());
    return buildTaskAddedMessage(t, myList);
  }

  /**
   * Handles the creation and addition of a new Event task to the task list.
   * The detail argument must contain the task description, start time, and end time,
   * with the start and end times separated by "/from" and "/to". If the detail
   * argument is empty or improperly formatted, this method throws an EmptyEventException.
   * The new Event task is added to the task list, which is then rewritten to storage.
   *
   * @param detail The description of the Event task, including start and end times.
   * @param myList The TaskList to which the new task will be added.
   * @param st The Storage instance used to update the tasks file.
   * @return A confirmation message indicating the task has been added, along with the current task count.
   * @throws EmptyEventException If the detail argument is empty or missing time information.
   */

  public String handleEventCommand(String detail, TaskList myList, Storage st)
    throws EmptyEventException {
    if (detail.equals("")) {
      throw new EmptyEventException();
    }
    String[] firstSplit = detail.split("\\s*/from\\s*", 2);
    String[] secondSplit = firstSplit[1].split("\\s*/to\\s*", 2);
    Event t = new Event(firstSplit[0], secondSplit[0], secondSplit[1]);
    myList.add(t);
    st.rewriteFile(myList.mine());
    return buildTaskAddedMessage(t, myList);
  }

  /**
   * Builds a confirmation message for adding a task to the task list.
   * This method constructs a message that confirms the task has been added,
   * displays the added task, and informs the user of the current total number of tasks.
   *
   * @param t The task that was added.
   * @param myList The TaskList to which the task was added.
   * @return A string containing the confirmation message and current task count.
   */

  private String buildTaskAddedMessage(Task t, TaskList myList) {
    return (
      "Got it. I've added this task:\n" +
      t.toString() +
      "\nNow you have " +
      myList.len() +
      " tasks in the list."
    );
  }

  /**
   * Handles the deletion of a task from the task list based on its index.
   * This method removes the specified task from the task list and updates the storage file.
   * If the specified index is out of bounds, it returns an error message instead.
   *
   * @param words An array of strings where the second element is expected to be the index of the task to delete.
   * @param myList The TaskList from which the task will be removed.
   * @param st The Storage instance used to update the tasks file after deletion.
   * @return A confirmation message indicating the task has been removed, along with the current task count,
   *         or an error message if the specified index is out of bounds.
   */

  public String handleDeleteCommand(
    String[] words,
    TaskList myList,
    Storage st
  ) {
    int c = Integer.parseInt(words[1]);
    if (c > 0 && c <= myList.len()) {
      Task t = myList.get(c - 1);
      myList.remove(c - 1);
      st.rewriteFile(myList.mine());
      StringBuilder message = new StringBuilder();
      message
        .append("Noted. I've removed this task:\n")
        .append(t.toString())
        .append("\nNow you have ")
        .append(myList.len())
        .append(" tasks in the list.");
      return message.toString();
    } else {
      return "Error: Task number out of bounds.";
    }
  }

  /**
   * Searches for tasks that contain the given detail in their description and generates a list of matching tasks.
   * This method iterates through all tasks in the list and adds those that contain the specified detail
   * to the result string. It includes a header and footer for readability if any matches are found, or a message
   * indicating no matches were found otherwise.
   *
   * @param detail The text to search for in the task descriptions.
   * @param myList The TaskList to search through.
   * @return A string listing all tasks that contain the specified detail in their description if any matches are found;
   *         otherwise, a message indicating no tasks were found with the detail.
   */

  public String handleFindCommand(String detail, TaskList myList) {
    int count = 0;
    StringBuilder result = new StringBuilder();

    // Search through the tasks for matches
    for (Task task : myList.mine()) {
      if (task.getDescription().contains(detail)) {
        if (count == 0) {
          // Header for the result, only added before listing the first result
          result
            .append(
              "____________________________________________________________\n"
            )
            .append("Here are the matching tasks in your list:\n");
        }
        count++;
        result.append(count).append(".").append(task).append("\n");
      }
    }

    if (count > 0) {
      // Footer for the result to separate the list visually
      result.append(
        "____________________________________________________________\n"
      );
    } else {
      // Message when no tasks match the query
      result.append("No tasks found with the detail: ").append(detail);
    }

    return result.toString();
  }
}
