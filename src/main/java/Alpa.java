import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Alpa {
  private static final List<Task> tasks = new ArrayList<>();

  public static void main(String[] args) {
    loadTasks();
    Scanner scanner = new Scanner(System.in);
    String logo = 
              "     _    _             \n"
            + "    / \\  | |_ __   __ _ \n"
            + "   / _ \\ | | '_ \\ / _` |\n"
            + "  / ___ \\| | |_) | (_| |\n"
            + " /_/   \\_\\_| .__/ \\__,_|\n"
            + "           |_|          ";
    String[] art = {
            "⠀⠀⠀⠀⡾⣦⡀⠀⠀⡀⠀⣰⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⣠⠗⠛⠽⠛⠋⠉⢳⡃⢨⢧⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⣰⠋⠁⠀⠀⠀⠀⠀⠀⠙⠛⢾⡈⡏⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⣼⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠸⢦⡀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⢈⠟⠓⠶⠞⠒⢻⣿⡏⢳⡀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⡴⢉⠀⠀⠀⠀⠀⠈⠛⢁⣸⠇⠀⠀⠀⠀⢺⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⢧⣸⡁⠀⠀⣀⠀⠀⣠⠾⠀⠀⠀⠀⠀⠀⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠉⠓⢲⠾⣍⣀⣀⡿⠃⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⢀⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⣸⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⠦⠤⠤⣤⣄⣀⣀⡀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠳⣦⣄⠀⠀",
            "⠀⠀⢀⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣆⠀",
            "⠀⠀⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣆",
            "⠀⠀⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿",
            "⠀⠀⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼",
            "⠀⠀⠀⣏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞",
            "⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇",
            "⠀⠀⠀⠀⠈⢻⣦⣀⠀⣏⠀⠀⠀⠀⠀⠀⢸⡆⠀⠀⢠⡄⠀⠀⠀⠀⠀⢀⡿⠀",
            "⠀⠀⠀⠀⠀⠀⠻⡉⠙⢻⡆⠀⠀⠀⠀⠀⡾⠚⠓⣖⠛⣧⡀⠀⠀⠀⢀⡾⠁⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠙⡇⢀⡿⣦⡀⠀⢀⡴⠃⠀⠀⠈⣷⢈⠷⡆⠀⣴⠛⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠛⠚⠀⢸⡇⣰⠏⠁⠀⠀⠀⠀⢉⠁⢸⠷⠼⠃⠀⠀⠀⠀"
        };
    // Image scaled down by half
    StringBuilder scaledArt = new StringBuilder();
    for (int i = 0; i < art.length; i += 2) {
      for (int j = 0; j < art[i].length(); j += 2) {
        scaledArt.append(art[i].charAt(j)); 
      }   
      scaledArt.append("\n");
    }
    System.out.println("Hello Human! I am your fluffy assistant, \n" + logo + "\n the Alpaca! \n" + scaledArt);
    System.out.println("I'm here to help you sort through the woolly world of tasks.\n");
    System.out.println("෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘\n");
    
    boolean exit = false;
    while (!exit) {
      try {
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        CommandType command = CommandType.fromString(parts[0]);

        switch (command) {
        
          case BYE:
            handleBye();
            exit = true;
            break;
        
          case LIST:
            handleList();
            break;
        
          case MARK:
          case UNMARK:
            handleMarkUnmark(parts, command);
            break;
        
          case TODO:
            addTask(handleToDo(parts));
            break;
        
          case DEADLINE:
            addTask(handleDeadline(parts));
            break;
        
          case EVENT:
            addTask(handleEvent(parts));
            break;

          case DELETE:
            deleteTask(parts);
            break;

          case INVALID:
          default:
            throw new AlpaException("\nOh no, human! I'm sorry but that is not a valid task.");
        }
      } catch (AlpaException e) {
        printDecoratedMessage(e.getMessage());
      }
    }
    scanner.close();
  }

  private static void addTask(Task task) {
    tasks.add(task);
    saveTasks();
    StringBuilder message = new StringBuilder();
    message.append("\nYou added a task human!\n" + "  " + task).append("\n")
           .append("Now you have ").append(tasks.size()).append(" tasks in your list!");
    printDecoratedMessage(message.toString());
  }

  private static void loadTasks() {
    File file = new File("./data/tasks.txt");
    if (file.exists()) {
      try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNext()) {
          String line = scanner.nextLine();
          String[] parts = line.split(" \\| ");
          if (parts.length < 3) {
            continue; // Invalid line format, skipped
          }
          TaskType taskType = TaskType.fromShortName(parts[0]);
          if (taskType == null) {
            continue; // Skip if task type is unknown
          }
          Task task;
          switch (taskType) {
          case TODO:
            task = new ToDo(parts[2]);
            break;
          case DEADLINE:
            if (parts.length < 4) {
              continue; // Additional check for Deadline and Event
            }
            task = new Deadline(parts[2], parts[3]);
            break;
          case EVENT:
            if (parts.length < 4) {
              continue;
            }
            String[] times = parts[3].split(" - ");
            if (times.length < 2) {
              continue;
            }
            task = new Event(parts[2].trim(), times[0].trim(), times[1].trim());
            break;
          default:
            continue;
          }
          if ("1".equals(parts[1])) {
            task.markAsDone();
          }
          tasks.add(task);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        printDecoratedMessage("File not found human..." + e.getMessage());
      } catch (Exception e) {
        e.printStackTrace();
        printDecoratedMessage("Error reading from file..." + e.getMessage());
      }
    } else {
      new File("./data").mkdirs();
    }
  }

  private static void saveTasks() {
    try (FileWriter fw = new FileWriter("./data/tasks.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {
      for (Task task : tasks) {
        out.println(task.toFileFormat());
      }
    } catch (IOException e) {
      e.printStackTrace();
      printDecoratedMessage("\nError! Could not save tasks!" + e.getMessage());
    }
  }

  private static void handleBye() {
    System.out.println("\nIt's been a pleasure grazing through your tasks! Goodbye human! Stay cozy!\n");
    System.out.println("𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣\n");
  }

  private static void handleList() {
    if (tasks.isEmpty()) {
      printDecoratedMessage("\nYour list is empty, human!");
      return;
    }
    StringBuilder listOutput = new StringBuilder("\nYour list, human!\n");
    for (int i = 0; i < tasks.size(); i++) {
      listOutput.append("  ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
    }
    printDecoratedMessage(listOutput.toString());
  }

  private static void handleMarkUnmark(String[] parts, CommandType command) throws AlpaException {
    try {
      int index = Integer.parseInt(parts[1]) - 1;
      if (index >= 0 && index < tasks.size()) {
        Task task = tasks.get(index);
        String response;
        if (command == CommandType.MARK) {
          task.markAsDone();
          saveTasks();
          response = "\nMarked as done, human!\n" + task;
        } else if (command == CommandType.UNMARK){
          task.markAsNotDone();
          saveTasks();
          response = "\nNot done with this yet, human?\n" + task;
        } else {
          throw new AlpaException("\nI don't know what you are referring to human!!");
        }
        printDecoratedMessage(response);
      } else {
        throw new AlpaException("\nInvalid task number human!!!");
      }
    } catch (NumberFormatException e) {
      throw new AlpaException("\nInvalid input human!!");
    }
  }
  
  private static Task handleToDo(String[] parts) throws AlpaException {
    if (parts.length < 2 || parts[1].trim().isEmpty()) {
      throw new AlpaException("\nBaa-ad news, human! The description of a todo cannot be empty.");
    }
    return new ToDo(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))); 
  }

  private static Task handleDeadline(String[] parts) throws AlpaException {
    int byIndex = Arrays.asList(parts).indexOf("/by");
    if (byIndex == -1 || byIndex >= parts.length - 1) {
      throw new AlpaException("\nInvalid deadline format, human! Use '/by' to specify the deadline.");
    }
    String deadlineDescription = String.join(" ", Arrays.copyOfRange(parts, 1, byIndex));
    String deadline = String.join(" ", Arrays.copyOfRange(parts, byIndex + 1, parts.length));
    saveTasks();
    return new Deadline(deadlineDescription, deadline);
  }

  private static Task handleEvent(String[] parts) throws AlpaException {
    int fromIndex = Arrays.asList(parts).indexOf("/from");
    int toIndex = Arrays.asList(parts).indexOf("/to");
    if (fromIndex == -1 || toIndex == -1|| fromIndex >= parts.length - 1 || toIndex <= fromIndex) {
      throw new AlpaException("\nInvalid event format, human! Please use '/from' and '/to' to specify the event time.");
    }
    String eventDescription = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex)).trim();
    String from = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex)).trim();
    String to = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length)).trim();
    saveTasks();
    return new Event(eventDescription, from, to);
  }

  private static void deleteTask(String[] parts) throws AlpaException {
    try {
      int index = Integer.parseInt(parts[1]) - 1;
      if (index >= 0 && index < tasks.size()) {
        Task removedTask = tasks.remove(index);
        saveTasks();
        printDecoratedMessage("\nRemoved this task for you, human.\n" + removedTask + "\nNow you have " + tasks.size() + " tasks left human!");
      } else {
        throw new AlpaException("Invalid task number, human!!"); 
      }
    } catch (NumberFormatException e) {
      throw new AlpaException("Invalid input for delete, human!!");
    }
  }

  private static void printDecoratedMessage(String message) {
    String decoration = "\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟\n";
    System.out.println(message + decoration);
  }
}
