import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class GeePeeTee {

  private static ArrayList<Task> taskList = new ArrayList<>();// Array of tasks

  public static void main(String[] args) {
    try {
      taskList = loadTaskList();
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    } catch (IOException e) {
      System.out.println("Error loading the task list file.");
    } catch (DukeException e) {
      e.printErrorMessage();
    }
    String input = "";
    String botName = "GeePeeTee";
    // Welcome Message
    System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?");
    Scanner scanner = new Scanner(System.in);
    while (!input.equals("bye")) {
      try {
        input = scanner.nextLine();
        System.out.println("\n--------------------------------------------------");
        if (input.equals("bye")) {
          scanner.close();
          System.out.println("Bye. Hope to see you again soon!");
        }
        processInput(input);

      } catch (DukeException e) {
        e.printErrorMessage();
      }
      System.out.println("--------------------------------------------------\n");
    }
  }

  /*
   * Process input
   */
  private static void processInput(String input) throws DukeException {
    String command = input.split(" ")[0];
    switch (command) {
      // Help command
      case "help":
        getListOfCommands();
        break;

      // Bye command
      case "bye":
        break;

      // List command
      case "list":
        printList(taskList);
        break;

      // Mark command
      case "mark":
        if (input.trim().equals("mark")) {
          throw new DukeException("The index of a task cannot be empty.");
        }
        int markIndex = Integer.parseInt(input.split(" ")[1]);
        if (markIndex > getTaskCount()) {
          throw new DukeException("The index of a task cannot be greater than the number of tasks.");
        }
        if (markIndex <= 0) {
          throw new DukeException("The index of a task cannot be 0 or negative.");
        }
        Task taskToMark = taskList.get(markIndex - 1);
        if (taskToMark == null) {
          throw new DukeException("The task at index " + markIndex + " does not exist.");
        }
        taskToMark.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMark);
        saveTaskList();
        break;

      // Unmark command
      case "unmark":
        if (input.trim().equals("unmark")) {
          throw new DukeException("The index of a task cannot be empty.");
        }
        int unmarkIndex = Integer.parseInt(input.split(" ")[1]);
        if (unmarkIndex > getTaskCount()) {
          throw new DukeException("The index of a task cannot be greater than the number of tasks.");
        }
        if (unmarkIndex <= 0) {
          throw new DukeException("The index of a task cannot be 0 or negative.");
        }
        Task taskToUnmark = taskList.get(unmarkIndex - 1);
        if (taskToUnmark == null) {
          throw new DukeException("The task at index " + unmarkIndex + " does not exist.");
        }
        taskToUnmark.unmarkAsDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskToUnmark);
        saveTaskList();
        break;

      // Delete command
      case "delete":
        if (input.trim().equals("delete")) {
          throw new DukeException("The index of a task cannot be empty.");
        }
        int index = Integer.parseInt(input.split(" ")[1]);
        if (index > getTaskCount()) {
          throw new DukeException("The index of a task cannot be greater than the number of tasks.");
        }
        if (index <= 0) {
          throw new DukeException("The index of a task cannot be 0 or negative.");
        }
        if (taskList.get(index - 1) == null) {
          throw new DukeException("The task at index " + index + " does not exist.");
        }
        removeTask(index);
        saveTaskList();
        break;

      // Create event command
      case "event":
        Event newEvent = Event.createFromInput(input);
        addTask(newEvent);
        saveTaskList();
        break;

      // Create deadline command
      case "deadline":
        Deadline newDeadline = Deadline.createFromInput(input);
        addTask(newDeadline);
        saveTaskList();
        break;

      // Create todo command
      case "todo":
        ToDo newToDo = ToDo.createFromInput(input);
        addTask(newToDo);
        saveTaskList();
        break;

      // Invalid command
      default:
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
  }

  /*
   * Prints the list of tasks
   */
  private static void printList(ArrayList<Task> list) {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      System.out.println((i + 1) + ". " + list.get(i));
    }
  }

  /*
   * Returns the number of tasks in the list
   */
  private static int getTaskCount() {
    return taskList.size();
  }

  /*
   * Return number of task message
   */
  private static String getTaskCountMessage() {
    if (getTaskCount() == 1) {
      return "Now you have " + getTaskCount() + " task in the list.";
    } else {
      return "Now you have " + getTaskCount() + " tasks in the list.";
    }
  }

  /*
   * Prints available commands
   */
  private static void getListOfCommands() {
    System.out.println("Here are the available commands:");
    System.out.println("list");
    System.out.println("todo <description>");
    System.out.println("deadline <description> /by <date>");
    System.out.println("event <description> /from <date> /to <date>");
    System.out.println("delete <index>");
    System.out.println("mark <index>");
    System.out.println("unmark <index>");
    System.out.println("bye");
  }

  /*
   * Adds a task to the list
   */
  private static void addTask(Task task) {
    System.out.println("Got it. I've added this task:");
    taskList.add(task);
    System.out.println(task);
    System.out.println(getTaskCountMessage());
  }

  /*
   * Removes a task from the list
   */
  private static void removeTask(int index) {
    System.out.println("Noted. I've removed this task:");
    System.out.println(taskList.get(index - 1));
    taskList.remove(index - 1);
    System.out.println(getTaskCountMessage());
  }

  /*
   * Load task list from hard disk
   */
  private static ArrayList<Task> loadTaskList() throws FileNotFoundException, DukeException, IOException {
    ArrayList<Task> result = new ArrayList<Task>();
    File f = new File("./data/GeePeeTee.txt");
    Scanner s = new Scanner(f);
    while (s.hasNext()) {
      String line = s.nextLine();
      String[] parts = line.split(" \\| ");
      String type = parts[0];
      boolean isDone = parts[1].equals("1");
      String description = parts[2];
      Task task;
      switch (type) {
        case "T":
          task = new ToDo(description);
          break;
        case "D":
          task = new Deadline(description, parts[3]);
          break;
        case "E":
          task = new Event(description, parts[3], parts[4]);
          break;
        default:
          throw new DukeException("File contains invalid task type.");
      }
      if (isDone) {
        task.markAsDone();
      }
      result.add(task);
    }
    s.close();
    return result;
  }

  /*
   * Save task list to hard disk
   */
  private static void saveTaskList() throws DukeException {
    try {
      File file = new File("./data/GeePeeTee.txt");

      try (FileWriter fw = new FileWriter(file)) {
        for (Task task : taskList) {
          fw.write(task.toFileString() + System.lineSeparator());
        }
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
      throw new DukeException("Error saving the task list file.");
    }
  }

}
