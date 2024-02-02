import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ben {
  private static List<Task> tasks = new ArrayList<>();
  public static void main(String[] args) throws BenException, FileNotFoundException {

    Actions.printWelcomeMessage();
    Actions.loadTasks(tasks);
    Scanner sc = new Scanner(System.in);

    while (sc.hasNext()) {
      String input = sc.nextLine();
      System.out.println("   ______________________________________________");
      String[] tokens = input.split(" ", 2);
      String command = tokens[0];

      try {
        switch (command) {
          case "bye":
            Actions.exitProgram();

          case "list":
            Actions.listTasks(tasks);
            break;

          case "mark":
            Actions.mark(tasks, tokens);
            break;

          case "unmark":
            Actions.unmark(tasks, tokens);
            break;

          case "todo":
            Actions.addNewTodo(tasks, tokens);
            break;

          case "deadline":
            Actions.addNewDeadline(tasks, tokens);
            break;

          case "event":
            Actions.addNewEvent(tasks, tokens);
            break;

          case "delete":
            Actions.deleteTask(tasks, tokens);
            break;

          default:
            throw new BenException("   OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
      } catch (BenException e) {
        System.out.println(e.getMessage());
      }

      System.out.println("   ______________________________________________");

      Actions.saveTasks(tasks);
    }
  }
}
