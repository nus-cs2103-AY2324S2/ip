import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GeePeeTee {

  private TaskList taskList;
  private Storage storage;

  public static void main(String[] args) {
    new GeePeeTee("./data/GeePeeTee.txt").run();
  }

  public GeePeeTee(String filePath) {
    storage = new Storage("./data/GeePeeTee.txt");
    try {
      taskList = new TaskList(storage.loadTaskList());
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    } catch (IOException e) {
      System.out.println("Error loading the task list file.");
    } catch (DukeException e) {
      e.printErrorMessage();
    }
  }

  public void run() {
    String input = "";
    String botName = "GeePeeTee";
    // Welcome Message
    System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?");
    Scanner scanner = new Scanner(System.in);
    Parser parser = new Parser(taskList, storage);
    while (!input.equals("bye")) {
      try {
        input = scanner.nextLine();
        System.out.println("\n--------------------------------------------------");
        if (input.equals("bye")) {
          scanner.close();
          System.out.println("Bye. Hope to see you again soon!");
        }
        parser.parseInput(input);

      } catch (DukeException e) {
        e.printErrorMessage();
      }
      System.out.println("--------------------------------------------------\n");
    }
  }
}