import java.util.Objects;
import java.util.Scanner;

public class InputHandler {
    public void handleInput(BartenderBob bartenderBob) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String index = "";
            if (userInput.split(" ").length > 1) {
                index = userInput.split(" ")[1];
            }
            if (Objects.equals(userInput, "list")) {
                bartenderBob.list();
            } else if (Objects.equals(userInput, "bye")) {
                bartenderBob.leave();
                break;
            } else if (Objects.equals(userInput.split(" ")[0], "mark")){
                bartenderBob.markDone(index);
            } else if (Objects.equals(userInput.split(" ")[0], "unmark")) {
                bartenderBob.unmarkDone(index);
            } else {
                Task task = new Task(userInput);
                bartenderBob.store(task);
            }
        }
    }
}
