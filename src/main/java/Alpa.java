import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
    System.out.println("෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴෴⚘෴⚘෴෴⚘෴\n");
    
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
            continue; // Additional check for Deadline
            }
            LocalDateTime deadlineDateTime = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            task = new Deadline(parts[2], deadlineDateTime);
            break;
          case EVENT:
            if (parts.length < 5) {
              continue; // Additional check for Event
            }
            LocalDateTime startDateTime = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime endDateTime = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            task = new Event(parts[2].trim(), startDateTime, endDateTime);
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
    String response; 
    try {
      int index = Integer.parseInt(parts[1]) - 1;
      if (index >= 0 && index < tasks.size()) {
        Task task = tasks.get(index);
        if (command == CommandType.MARK) {
          task.markAsDone();
          response = "\nMarked as done, human!\n" + task;
        } else if (command == CommandType.UNMARK) {
          task.markAsNotDone();
          response = "\nNot done with this yet, human?\n" + task;
        } else {
          throw new AlpaException("\nI don't know what you are referring to human!!");
        }
        saveTasks(); 
      } else {
        throw new AlpaException("\nInvalid task number human!!!");
      }
    } catch (NumberFormatException e) {
      throw new AlpaException("\nInvalid input human!!");
    }
    printDecoratedMessage(response); 
  }
  
  private static Task handleToDo(String[] parts) throws AlpaException {
    if (parts.length < 2 || parts[1].trim().isEmpty()) {
      throw new AlpaException("\nBaa-ad news, human! The description of a todo cannot be empty.");
    }
    return new ToDo(String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))); 
  }

  private static Task handleDeadline(String[] parts) throws AlpaException {
    String input = String.join(" ", parts);
    // Handle '/by' with flexible spacing
    String[] inputParts = input.split("\\s*/by\\s*", 2);
    if (inputParts.length < 2) {
        throw new AlpaException("\nInvalid deadline format, human! Use '/by' to specify the deadline.");
    }
    String description = inputParts[0].trim();
    String deadlineStr = inputParts[1].trim();
    
    try {
        LocalDateTime parsedDeadlineDateTime = DateTimeUtils.parseDeadlineDateTime(deadlineStr);
        return new Deadline(description, parsedDeadlineDateTime);
    } catch (DateTimeParseException e) {
        throw new AlpaException("\nInvalid date or time format, human!!");
    }
  }

  private static Task handleEvent(String[] parts) throws AlpaException {
    String input = String.join(" ", parts);
    // Pattern to find '/from' and '/to' with flexible spacing
    Pattern fromPattern = Pattern.compile("\\s*/from\\s*");
    Pattern toPattern = Pattern.compile("\\s*/to\\s*");
    Matcher fromMatcher = fromPattern.matcher(input);
    Matcher toMatcher = toPattern.matcher(input);
    
    // Check if both markers are present
    if (!fromMatcher.find() || !toMatcher.find()) {
        throw new AlpaException("\nInvalid event format, human! Please use '/from' and '/to' to specify the event time.");
    }
    
    int fromIndex = fromMatcher.start();
    int toIndex = toMatcher.start();

    String description = input.substring(0, fromIndex).trim();
    String startStr = input.substring(fromIndex + fromMatcher.group().length(), toIndex).trim();
    String endStr = input.substring(toIndex + toMatcher.group().length()).trim();

    try {
      LocalDateTime startDateTime = DateTimeUtils.parseDateTime(startStr);
      LocalDateTime endDateTime = DateTimeUtils.tryParseEndDateTime(endStr, startDateTime.toLocalDate());
      
      if (endDateTime.isBefore(startDateTime)) {
        throw new AlpaException("\nEnd time cannot be before start time, human!");
      }

      return new Event(description, startDateTime, endDateTime);
    } catch (DateTimeParseException e) {
        throw new AlpaException("\nInvalid date and time format, human!! Start: '" + startStr + "', End: '" + endStr + "'.");
    }      
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
