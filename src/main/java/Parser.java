import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    private final List<String> invalidInputs = Arrays.asList("todo", "deadline",
            "event", "mark", "unmark", "delete");
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }


    public void handleInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            ui.greeting();

            while (true) {
                String input = scanner.hasNextLine() ? scanner.nextLine() : "";

                if (Objects.equals(input, "bye")) {
                    ui.goodbye();
                    storage.saveTasksToFile();
                    break;
                }

                if (invalidInputs.contains(input)) {
                    ui.emptyInput(input);
                    continue;
                }

                taskList.action(input);

            }
        }
    }
}
