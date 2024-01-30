import Tasks.TaskList;
import javafx.util.Pair;
import java.util.Scanner;
public class Ui {
    public Ui() {

    }

    public TaskList run(Parser parser, TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        this.greet();
        while (!input.toLowerCase().equals("bye")) {
            input = scanner.nextLine();
            taskList = this.printMessage(input, parser, taskList);
        }
        return taskList;
    }

    public TaskList printMessage(String message, Parser parser, TaskList taskList) {
        Pair<TaskList, String> output =  parser.parse(taskList, message);
        System.out.println(output.getValue());
        return output.getKey();
    }

    public void greet() {
        Toothless.printLines();
        System.out.println("Hello! I'm Toothless!\nWhat can I do for you?");
        Toothless.printLines();
    }

    public void bye() {
        Toothless.printLines();
        System.out.println("Bye. Hope to see you again soon!");
        Toothless.printLines();
    }

    public void printLines() {
        System.out.println("____________________________________________________________");
    }

}
