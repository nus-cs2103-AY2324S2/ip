import java.util.Scanner;
import java.util.Arrays;

public class Alpa {
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Task[] tasks = new Task[MAX_TASKS];
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
    
    int taskCount = 0;
    boolean exit = false;
    while (!exit) {
      String input = scanner.nextLine();
      String[] parts = input.split(" ");
      String command = parts[0];

      switch (command.toLowerCase()) {
        
        case "bye":
          System.out.println("\nIt's been a pleasure grazing through your tasks! Goodbye human! Stay cozy!\n");
          System.out.println("𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧𖡼.𖤣𖥧");
          exit = true;
          break;
        
        case "list":
          System.out.println("\nYour list, human!");
          for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
          }
          System.out.println("\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒ\n");
          break;
        
        case "mark":
        case "unmark":
          int index = Integer.parseInt(parts[1]) - 1;
          if (index >= 0 && index < taskCount) {
            if (command.equals("mark")) {
              tasks[index].markAsDone();
              System.out.println("\nMarked as done, human!\n" + tasks[index]);
              System.out.println("\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ\n"); 
            } else {
              tasks[index].markAsNotDone();
              System.out.println("\nNot done with this yet, human?\n" + tasks[index]);
              System.out.println("\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ\n");
            }
          } else {
          System.out.println("\nInvalid task number human!!!\n");
          System.out.println("↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟\n");
          }
        break;
        
        case "todo":
          String todoDescription = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
          tasks[taskCount] = new ToDo(todoDescription);
          System.out.println("\nYou added a To-Do task, human!\n" + tasks[taskCount]);
          taskCount++;
          System.out.println("Now you have " + taskCount + " tasks human!");
          System.out.println("\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒ↟ᨒ\n");
          break;
        
        case "deadline":
          int byIndex = Arrays.asList(parts).indexOf("/by");
          String deadlineDescription = String.join(" ", Arrays.copyOfRange(parts, 1, byIndex));
          String deadline = String.join(" ", Arrays.copyOfRange(parts, byIndex + 1, parts.length));
          tasks[taskCount] = new Deadline(deadlineDescription, deadline);
          System.out.println("\nYou added a task with deadline, human!\n" + tasks[taskCount]);
          taskCount++;
          System.out.println("Now you have " + taskCount + " tasks human!");
          System.out.println("\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒ\n");
          break;
        
        case "event":
          int fromIndex = Arrays.asList(parts).indexOf("/from");
          int toIndex = Arrays.asList(parts).indexOf("/to");
          String eventDescription = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex));
          String from = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex));
          String to = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length));
          tasks[taskCount] = new Event(eventDescription, from, to);
          System.out.println("\nYou added an event, human!\n" + tasks[taskCount]);
          taskCount++;
          System.out.println("Now you have " + taskCount + " tasks human!");
          System.out.println("\n↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒᨒ↟↟ᨒᨒᨒ↟ᨒᨒ\n");
          break;
      }
    }
    scanner.close();
  }
}
