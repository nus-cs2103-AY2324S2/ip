import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The ATSISBot class represents a chatbot that can perform various tasks.
 * It allows users to interact with the bot by entering commands and receiving
 * responses.
 * The bot can manage a list of tasks, including adding, marking, and deleting
 * tasks.
 */
public class ATSISBot {

  private static final String line = "____________________________________________________________\n";

  private static final String welcomeMessage = line + "Hello! I'm ATSISBot\n" + "What can I do for you?\n" + line;
  private static final String endingMessage = line + "Bye. Hope to see you again soon!\n" + line;
  private static final String listMessage = "Here are the tasks in your list:\n";
  private static final String markMessage = "Nice! I've marked this task as done:\n";
  private static final String unmarkMessage = "OK, I've marked this task as not done yet:\n";
  private static final String addTaskMessage = "Got it. I've added this task:\n";
  private static final String deleteTaskMessage = "Noted. I've removed this task:\n";
  private static final String noDescriptionMessage = "The description of a %s cannot be empty.\n";
  private static final String invalidDeadlineFormatMessage = "Invalid deadline format. Please use: deadline <description> /by <date>\n";
  private static final String invalidEventFormatMessage = "Invalid event format. Please use: event <description> /from <date> /to <date>\n";
  private static final String invalidTaskNumberMessage = "Invalid task number. Please enter a valid task number.\n";
  private static final String unknownCommandMessage = "I'm sorry, but I don't understand that command.\n";

  private static ArrayList<Task> list = new ArrayList<>();

  public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

  /**
   * The main method is the entry point of the ATSISBot program.
   * It prompts the user for input and executes corresponding commands based on
   * the input.
   * The program continues to accept input until the user enters "bye".
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    loadList(); 

    System.out.println(ATSISBot.welcomeMessage);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    while (!input.equals("bye")) {
      System.out.print(line);
      if (input.equals("list")) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.print(listMessage);
        list.forEach(element -> System.out
            .println(index.getAndIncrement() + ". " + element.toString()));
      } else if (input.startsWith("mark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        if (elementIdx <= 0 || elementIdx > list.size()) {
          System.out.println(invalidTaskNumberMessage);
        } else {
          list.get(elementIdx - 1).markAsDone();
          saveList();
          System.out.println(markMessage);
          System.out.println("  " + list.get(elementIdx - 1).toString());
        }
      } else if (input.startsWith("unmark")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        if (elementIdx <= 0 || elementIdx > list.size()) {
          System.out.println(invalidTaskNumberMessage);
        } else {
          list.get(elementIdx - 1).markAsUndone();
          saveList();
          System.out.println(unmarkMessage);
          System.out.println("  " + list.get(elementIdx - 1).toString());
        }
      } else if (input.startsWith("todo")) {
        if (!input.contains(" ")) {
          System.out.print(String.format(noDescriptionMessage, "todo"));
        } else {
          String description = input.split(" ", 2)[1];
          list.add(new Todo(description));
          saveList();
          System.out.print(addTaskMessage);
          System.out.println("  " + list.get(list.size() - 1).toString());
          System.out.println(Task.getTaskNum());
        }
      } else if (input.startsWith("deadline")) {
        if (!input.contains(" ")) {
          System.out.print(String.format(noDescriptionMessage, "deadline"));
        } else {
          String[] descriptionAndBy = input.split(" ", 2)[1].split(" /by ");
          if (descriptionAndBy.length != 2) {
            System.out.println(invalidDeadlineFormatMessage);
          } else {
            try {
              LocalDateTime deadline = LocalDateTime.parse(descriptionAndBy[1], ATSISBot.formatter);
              list.add(new Deadline(descriptionAndBy[0], deadline));
              saveList();
              System.out.print(addTaskMessage);
              System.out.println("  " + list.get(list.size() - 1).toString());
              System.out.println(Task.getTaskNum());
            } catch (DateTimeParseException e) {
              System.out.println("Invalid deadline format. Please use: deadline <description> /by <date>");
            }
          }
        }
      } else if (input.startsWith("event")) {
        if (!input.contains(" ")) {
          System.out.print(String.format(noDescriptionMessage, "event"));
        } else {
          String[] descriptionAndFromTo = input.split(" ", 2)[1].split(" /from ");
          String[] fromTo = descriptionAndFromTo[1].split(" /to ", 2);
          if (descriptionAndFromTo.length != 2 || fromTo.length != 2) {
            System.out.println(invalidEventFormatMessage);
          } else {
            try {
              LocalDateTime startDateTime = LocalDateTime.parse(fromTo[0], ATSISBot.formatter);
              LocalDateTime endDateTime = LocalDateTime.parse(fromTo[1], ATSISBot.formatter);
              list.add(new Event(descriptionAndFromTo[0], startDateTime, endDateTime));
              saveList();
              System.out.print(addTaskMessage);
              System.out.println("  " + list.get(list.size() - 1).toString());
              System.out.println(Task.getTaskNum());
            } catch (DateTimeParseException e) {
              System.out.println("Invalid event format. Please use: event <description> /from <date> /to <date>");
            }
          }
        }
      } else if (input.startsWith("delete")) {
        int elementIdx = Integer.parseInt(input.split(" ")[1]);
        if (elementIdx <= 0 || elementIdx > list.size()) {
          System.out.println(invalidTaskNumberMessage);
        } else {
          Task removedTask = list.remove(elementIdx - 1);
          saveList();
          System.out.print(deleteTaskMessage);
          System.out.println("  " + removedTask.toString());
          System.out.println(Task.getTaskNum());
        }
      } else {
        System.out.print(unknownCommandMessage);
      }
      System.out.print(line);
      input = sc.nextLine();
    }

    System.out.println(ATSISBot.endingMessage);
    sc.close();
  }

  /**
   * Loads the list from a file.
   */
  private static void loadList() {
    try {
      File file = new File("data/taskList.txt");
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
      }
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      while ((line = br.readLine()) != null) {
        String[] taskInfo = line.split(" \\| ");
        switch (taskInfo[0]) {
          case "T":
            list.add(new Todo(taskInfo[2]));
            break;
          case "D":
            try {
              LocalDateTime deadline = LocalDateTime.parse(taskInfo[3], formatter);
              list.add(new Deadline(taskInfo[2], deadline));
            } catch (DateTimeParseException e) {
              System.out.println("Invalid deadline format in the task list: " + e.getMessage());
            }
            break;
          case "E":
            try {
              LocalDateTime startDateTime = LocalDateTime.parse(taskInfo[3], formatter);
              LocalDateTime endDateTime = LocalDateTime.parse(taskInfo[4], formatter);
              list.add(new Event(taskInfo[2], startDateTime, endDateTime));
            } catch (DateTimeParseException e) {
              System.out.println("Invalid event format in the task list: " + e.getMessage());
            }
            break;
          default:
            break;
        }
        if (taskInfo[1].equals("1")) {
          list.get(list.size() - 1).markAsDone();
        }
      }
      br.close();
    } catch (IOException e) {
      System.out.println("Error loading the task list: " + e.getMessage());
    }
  }

  private static void saveList() {
    try {
      File file = new File("data/taskList.txt");
      BufferedWriter bw = new BufferedWriter(new FileWriter(file));
      for (Task task : list) {
        bw.write(task.encode());
      }
      bw.close();
    } catch (IOException e) {
      System.out.println("Error saving the task list: " + e.getMessage());

    }
  }
}
