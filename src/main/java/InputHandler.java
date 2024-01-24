import java.util.Objects;
import java.util.Scanner;

public class InputHandler {
    public void handleInput(BartenderBob bartenderBob) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String[] strArray = userInput.split(" ");
            String firstWord = strArray[0];
            String index = "";
            if (firstWord.equals("bye")) {
                bartenderBob.leave();
                break;
            } else {
                try {
                    switch (firstWord) {
                        case "list":
                            bartenderBob.list();
                            break;
                        case "mark":
                            index = strArray[1];
                            bartenderBob.markDone(index);
                            break;
                        case "unmark":
                            index = strArray[1];
                            bartenderBob.unmarkDone(index);
                            break;
                        case "todo":
                            String str = userInput.split("todo ")[1];
                            Task task = new Task(str);
                            bartenderBob.store(task);
                            break;
                        case "deadline":
                            String[] deadlineSplit = userInput.split("deadline | /by ");
                            Deadline deadline = new Deadline(deadlineSplit[1], deadlineSplit[2]);
                            bartenderBob.store(deadline);
                            break;
                        case "event":
                            String[] eventSplit = userInput.split("event | /from | /to ");
                            Event event = new Event(eventSplit[1], eventSplit[2], eventSplit[3]);
                            bartenderBob.store(event);
                            break;
                        default:
                            BartenderBobException.invalidInput(userInput);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    BartenderBobException error = new BartenderBobException(firstWord);
                    error.displayError();
                }
            }
        }
    }
}
