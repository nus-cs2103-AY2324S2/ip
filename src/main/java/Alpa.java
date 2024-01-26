import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Alpa {
  private static final List<Task> tasks = new ArrayList<>();

  public static void main(String[] args) {
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
        String command = parts[0];

        switch (command.toLowerCase()) {
        
          case "bye":
            handleBye();
            exit = true;
            break;
        
          case "list":
            handleList();
            break;
        
          case "mark":
          case "unmark":
            handleMarkUnmark(parts, command);
            break;
        
          case "todo":
            addTask(handleToDo(parts));
            break;
        
          case "deadline":
            addTask(handleDeadline(parts));
            break;
        
          case "event":
            addTask(handleEvent(parts));
            break;

          case "delete":
            deleteTask(parts);
            break;

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
    printDecoratedMessage("\nYou added a task human!\n" + task);
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

  private static void handleMarkUnmark(String[] parts, String command) throws AlpaException {
    try {
      int index = Integer.parseInt(parts[1]) - 1;
      if (index >= 0 && index < tasks.size()) {
        Task task = tasks.get(index);
        String response;
        if (command.equals("mark")) {
          task.markAsDone();
          response = "\nMarked as done, human!\n" + task;
        } else {
          task.markAsNotDone();
          response = "\nNot done with this yet, human?\n" + task;
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
    return new Deadline(deadlineDescription, deadline);
  }

  private static Task handleEvent(String[] parts) throws AlpaException {
    int fromIndex = Arrays.asList(parts).indexOf("/from");
    int toIndex = Arrays.asList(parts).indexOf("/to");
    if (fromIndex == -1 || toIndex == -1|| fromIndex >= parts.length - 1 || toIndex <= fromIndex) {
      throw new AlpaException("\nInvalid event format, human! Please use '/from' and '/to' to specify the event time.");
    }
    String eventDescription = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex));
    String from = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex));
    String to = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length));
    return new Event(eventDescription, from, to);
  }

  private static void deleteTask(String[] parts) throws AlpaException {
    try {
      int index = Integer.parseInt(parts[1]) - 1;
      if (index >= 0 && index < tasks.size()) {
        Task removedTask = tasks.remove(index);
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
