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
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < myList.len(); i++) {
          int show = i + 1;
          listString
            .append(show)
            .append(".")
            .append(myList.get(i))
            .append("\n");
        }
        return listString.toString();
      } else if (words[0].equals("mark")) {
        int c = Integer.parseInt(words[1]);
        myList.asDone(c);
        st.rewriteFile(myList.mine());
        StringBuilder response = new StringBuilder();
        response.append("Nice! I've marked this task as done:\n"); // Append the first part of the message
        response.append(myList.get(c - 1)); // Append the task that was marked as done
        return response.toString(); // Convert StringBuilder to String and return it
      } else if (words[0].equals("unmark")) {
        int c = Integer.parseInt(words[1]);
        myList.asNotDone(c);
        st.rewriteFile(myList.mine());
        StringBuilder response = new StringBuilder();
        response.append("Nice! I've marked this task as not done yet:\n"); // Append the first part of the message
        response.append(myList.get(c - 1)); // Append the task that was marked as done
        return response.toString();
      } else if (words[0].equals("todo")) {
        Todo t = new Todo(detail);
        myList.add(t);
        st.rewriteFile(myList.mine());
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n"); // Append the first line
        message.append(t.toString()).append("\n"); // Append the toString representation of t and a newline
        message
          .append("Now you have ")
          .append(myList.len())
          .append(" tasks in the list."); // Append the third line

        String finalMessage = message.toString();

        return finalMessage;
      } else if (words[0].equals("deadline")) {
        String[] parts = detail.split("\\s*/by\\s*", 2);
        Deadline t = new Deadline(parts[0], parts[1]);
        myList.add(t);
        st.rewriteFile(myList.mine());
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n"); // Append the first line
        message.append(t.toString()).append("\n"); // Append the toString representation of t and a newline
        message
          .append("Now you have ")
          .append(myList.len())
          .append(" tasks in the list."); // Append the third line

        String finalMessage = message.toString();

        return finalMessage;
      } else if (words[0].equals("event")) {
        String[] firstSplit = detail.split("\\s*/from\\s*", 2);
        String[] secondSplit = firstSplit[1].split("\\s*/to\\s*", 2);
        Event t = new Event(firstSplit[0], secondSplit[0], secondSplit[1]);
        myList.add(t);
        st.rewriteFile(myList.mine());
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n"); // Append the first line
        message.append(t.toString()).append("\n"); // Append the toString representation of t and a newline
        message
          .append("Now you have ")
          .append(myList.len())
          .append(" tasks in the list."); // Append the third line

        String finalMessage = message.toString();

        return finalMessage;
      } else if (words[0].equals("delete")) {
        int c = Integer.parseInt(words[1]);
        Task t = myList.get(c - 1);
        myList.remove(c - 1);
        st.rewriteFile(myList.mine());
        StringBuilder message = new StringBuilder();
        message.append("Noted. I've removed this task:\n"); // Append the first line
        message.append(t.toString()).append("\n"); // Append the toString representation of t and a newline
        message
          .append("Now you have ")
          .append(myList.len())
          .append(" tasks in the list."); // Append the third line

        String finalMessage = message.toString(); // Convert StringBuilder to String

        return finalMessage;
      } else if (words[0].equals("find")) {
        int count = 0;
        StringBuilder result = new StringBuilder();

        for (Task task : myList.mine()) {
          if (task.getDescription().contains(detail)) {
            count++;
            if (count == 1) {
              result.append(
                "____________________________________________________________\n"
              );
              result.append("Here are the matching tasks in your list:\n");
            }
            result.append(count).append(".").append(task).append("\n");
          }
        }
        if (count > 0) {
          result.append(
            "____________________________________________________________\n"
          );
        } else {
          result.append("No tasks found with the detail: ").append(detail);
        }

        return result.toString();
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
}
