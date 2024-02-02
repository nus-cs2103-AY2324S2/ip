package bartenderbob;
import java.util.Scanner;

public class InputHandler {
    public void handleInput(TaskList taskList, Ui ui) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String[] strArray = userInput.split(" ");
            String firstWord = strArray[0];
            String index;
            if (firstWord.equals("bye")) {
                ui.leave();
                break;
            } else {
                try {
                    switch (firstWord) {
                    case "list":
                        taskList.list();
                        break;
                    case "mark":
                        index = strArray[1];
                        taskList.markDone(index);
                        break;
                    case "unmark":
                        index = strArray[1];
                        taskList.unmarkDone(index);
                        break;
                    case "delete":
                        index = strArray[1];
                        taskList.delete(index);
                        break;
                    case "find":
                        String substring = strArray[1];
                        taskList.find(substring);
                        break;
                    case "todo":
                        String str = userInput.split("todo ")[1];
                        ToDo task = new ToDo(str);
                        taskList.store(task);
                        break;
                    case "deadline":
                        String[] deadlineSplit = userInput.split("deadline | /by ");
                        Deadline deadline = new Deadline(deadlineSplit[1], deadlineSplit[2]);
                        taskList.store(deadline);
                        break;
                    case "event":
                        String[] eventSplit = userInput.split("event | /from | /to ");
                        Event event = new Event(eventSplit[1], eventSplit[2], eventSplit[3]);
                        taskList.store(event);
                        break;
                    default:
                        BartenderBobException.invalidInput(userInput);
                    }
                } catch (BartenderBobException e) {
                    //This exception is when the index from user input is out of the TASKS bounds.
                    e.tasksOutOfBounds();
                } catch (IndexOutOfBoundsException e) {
                    //IndexOutOfBoundsException is for missing userInputs.
                    BartenderBobException error = new BartenderBobException(firstWord);
                    error.displayError();
                } catch (IllegalArgumentException e) {
                    ui.showInvalidDateFormat();
                }
            }
        }
    }
}
