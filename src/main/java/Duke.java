import controller.Exit;
import controller.Greet;
import controller.HandleUserInput;
import duke.Storage;
import model.Task;


import java.util.*;

public class Duke {
    private final ArrayList<Task> taskList;
    private final Storage storage;
    public Duke() {
        this.storage = new Storage("./data.dat");
        if (storage.isFileExists()) {
            this.taskList = new ArrayList<>(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new ArrayList<>(100);
            storage.update(taskList);
        }
    }

    public void launch() {
        Greet greetController = new Greet();
        greetController.execute(storage);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a command:\n");
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    Exit exitController = new Exit();
                    exitController.execute(storage);
                    break;
                } else {
                    HandleUserInput handleUserInputController = new HandleUserInput(input, taskList);
                    handleUserInputController.execute(storage);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}