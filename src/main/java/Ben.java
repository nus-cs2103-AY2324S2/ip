import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ben {
  // test branch 1
  // test branch 2
  // test branch 3
  private static List<Task> tasks = new ArrayList<>();
  private static void printWelcomeMessage() {
    System.out.println("   ______________________________________________");
    System.out.println("   Hello! I'm Ben");
    System.out.println("   What can I do for you?");
    System.out.println("   ______________________________________________");
  }

  private static void exitProgram() {
    System.out.println("   Bye. Hope to see you again soon!");
    System.out.println("   ______________________________________________");
    System.exit(0);
  }

  private static void listTasks(List<Task> tasks) {
    System.out.println("   Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      Task currTask = tasks.get(i);
      System.out.println("   " + (i + 1) + ". " + currTask);
    }
  }

  private static void writeToFile(String filePath, String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(filePath);
    fw.write(textToAdd);
    fw.close();
  }

  private static void loadTasks() throws BenException, FileNotFoundException {
    String targetFolder = "data";
    String targetFile = "data/ben.txt";

    if(!Files.exists(Paths.get(targetFolder))) {
      throw new BenException("./data/ folder path does not exist.");
    }

    // File does not exist
    if (!Files.exists(Paths.get(targetFile))) {
      throw new BenException("target file does not exist.");
    }

    try {
      File file = new File(targetFile);
      Scanner sc = new Scanner(file);
      tasks.clear();

      while (sc.hasNext()) {
        String line = sc.nextLine();
        String[] tokens = line.split(" \\| ");
        String taskType = tokens[0];
        boolean isDone = tokens[1].equals("1");
        String description = tokens[2];

        switch (taskType) {
          case "T":
            tasks.add(new Todo(isDone, description));
            break;

          case "D":
            String by = tokens[3];
            tasks.add(new Deadline(isDone, description, by));
            break;

          case "E":
            String startDate = tokens[3];
            String endDate = tokens[4];
            tasks.add(new Event(isDone, description, startDate, endDate));
            break;

          default:
            break;
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }

  private static void saveTasks(List<Task> tasks) throws BenException {
    String targetFolder = "data";
    String targetFile = "data/ben.txt";

    if(!Files.exists(Paths.get(targetFolder))) {
      throw new BenException("./data/ folder path does not exist.");
    }

    // File does not exist
    if (!Files.exists(Paths.get(targetFile))) {
      throw new BenException("target file does not exist.");
    }

    StringBuilder saveInput = new StringBuilder();
    for (Task currTask : tasks) {
      saveInput.append(currTask.saveTask())
               .append(System.lineSeparator());
    }

    try {
      writeToFile(targetFile, String.valueOf(saveInput));
    } catch (IOException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws BenException, FileNotFoundException {

    printWelcomeMessage();
    loadTasks();
    Scanner sc = new Scanner(System.in);

    while (sc.hasNext()) {
      String input = sc.nextLine();
      System.out.println("   ______________________________________________");
      String[] tokens = input.split(" ", 2);
      String command = tokens[0];

      try {
        switch (command) {
          case "bye":
            exitProgram();
          case "list":
            listTasks(tasks);
            break;
          case "mark": {

            // empty field
            if (tokens.length < 2) {
              throw new BenException("   Key in a value!");
            }

            // obtain index of task within TaskList
            int index = Integer.parseInt(tokens[1]) - 1;

            // check if task list is empty
            if (tasks.isEmpty()) {
              throw new BenException("   There are no pending tasks now... Add some tasks here!");
            }

            // check if input is within bounds
            if (index < 0 || index > tasks.size() - 1) {
              throw new BenException("   Please input a valid number between 1 and " + tasks.size());
            }

            Task currTask = tasks.get(index);
            currTask.markTask();

            System.out.println("   Nice! I've marked this task as done:");
            System.out.println("      " + currTask);
            break;
          }

          // unmark a task
          case "unmark": {

            // empty field
            if (tokens.length < 2) {
              throw new BenException("   Key in a value!");
            }

            // obtain index of task within TaskList
            int index = Integer.parseInt(tokens[1]) - 1;

            // check if task list is empty
            if (tasks.isEmpty()) {
              throw new BenException("   There are no pending tasks now... Add some tasks here!");
            }

            // check if input is within bounds
            if (index < 0 || index > tasks.size() - 1) {
              throw new BenException("   Please input a valid number between 1 and " + tasks.size());
            }

            Task currTask = tasks.get(index);
            currTask.unmarkTask();

            System.out.println("   OK, I've marked this task as not done yet:");
            System.out.println("      " + currTask);
            break;
          }

          // create new to-do task
          case "todo": {
            // empty to-do
            if (tokens.length < 2) {
              throw new BenException("   OOPS!!! The description of a todo cannot be empty.");
            }

            String description = tokens[1];
            Task newTodoTask = new Todo(false, description);
            tasks.add(newTodoTask);

            System.out.println("   Got it. I've added this task:");
            System.out.println("      " + newTodoTask);
            System.out.println("   Now you have " + tasks.size() + " tasks in the list.");
            break;
          }

          // create new deadline task
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

            // create new task
            Task newDeadlineTask = new Deadline(false, description, by);
            tasks.add(newDeadlineTask);

            System.out.println("   Got it. I've added this task:");
            System.out.println("      " + newDeadlineTask);
            System.out.println("   Now you have " + tasks.size() + " tasks in the list.");
            break;
          }

          // create new event task
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

            // create new task
            Task newEventTask = new Event(false, description, startDate, endDate);
            tasks.add(newEventTask);

            System.out.println("   Got it. I've added this task:");
            System.out.println("      " + newEventTask);
            System.out.println("   Now you have " + tasks.size() + " tasks in the list.");
            break;
          }

          // delete a current task
          case "delete":

            // empty field
            if (tokens.length < 2) {
              throw new BenException("   Key in a value");
            }

            int index = Integer.parseInt(tokens[1]) - 1;

            if (tasks.isEmpty()) {
              throw new BenException("   No tasks to delete :)");
            }

            if (index < 0 || index > tasks.size() - 1) {
              throw new BenException("   Please input a valid number between 1 and " + tasks.size());
            }

            // delete task from list
            Task deletedTask = tasks.remove(index);

            System.out.println("   Noted. I've removed this task:");
            System.out.println("      " + deletedTask);
            System.out.println("   Now you have " + tasks.size() + " tasks in the list.");
            break;

          // add general task to task-list
          default:
            throw new BenException("   OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

      } catch (BenException e) {
        System.out.println(e.getMessage());
      }

      System.out.println("   ______________________________________________");

      saveTasks(tasks);
    }
  }
}
