import controller.Exit;
import controller.Greet;
import controller.HandleUserInput;
import model.Task;


import java.util.*;

public class Duke {
    private final ArrayList<Task> taskList;
    private final Greet greetController;
    public Duke() {
        this.taskList = new ArrayList<Task>(100);
        this.greetController = new Greet();
    }

    public void launch() {
        greetController.execute();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a command: ");
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    Exit exitController = new Exit();
                    exitController.execute();
                    break;
                } else {
                    HandleUserInput handleUserInputController = new HandleUserInput(input);
                    handleUserInputController.execute(taskList);
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}