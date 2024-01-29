package chatbot;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

  public Parser() {}

  public boolean isItDone(String[] words) {
    return words[0].equals("bye");
  }

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
      } else {
        throw new InvalidException();
      }
    } catch (EmptyTodoException e) {
      return (
        e.getMessage() +
        "\n____________________________________________________________\n"
      );
    } catch (EmptyDeadlineException e) {
      return (
        e.getMessage() +
        "\n____________________________________________________________\n"
      );
    } catch (EmptyEventException e) {
      return (
        e.getMessage() +
        "\n____________________________________________________________\n"
      );
    } catch (InvalidException e) {
      return (
        e.getMessage() +
        "\n____________________________________________________________\n"
      );
    }
  }
}
