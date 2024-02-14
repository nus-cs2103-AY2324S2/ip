package bartenderbob;

/**
 * Represents a parser that receives and handle user inputs.
 */
public class InputHandler {
    public static String handleInput(TaskList taskList, Ui ui, String userInput) {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "ui cannot be null";
        assert userInput != null : "user input cannot be null";
        String[] stringComponents = userInput.split(" ");
        String firstWord = stringComponents[0];
        String index;
        if (firstWord.equals("bye")) {
            return ui.leave();
        } else {
            try {
                switch (firstWord) {
                case "list":
                    return taskList.list();
                case "mark":
                    index = stringComponents[1];
                    return taskList.markDone(index);
                case "unmark":
                    index = stringComponents[1];
                    return taskList.unmarkDone(index);
                case "delete":
                    index = stringComponents[1];
                    return taskList.delete(index);
                case "find":
                    String substring = stringComponents[1];
                    return taskList.find(substring);
                case "todo":
                    String str = userInput.split("todo ")[1];
                    ToDo task = new ToDo(str);
                    return taskList.store(task);
                case "deadline":
                    String[] deadlineComponents = userInput.split("deadline | /by ");
                    Deadline deadline = new Deadline(deadlineComponents[1], deadlineComponents[2]);
                    return taskList.store(deadline);
                case "event":
                    String[] eventComponents = userInput.split("event | /from | /to ");
                    Event event = new Event(eventComponents[1], eventComponents[2], eventComponents[3]);
                    return taskList.store(event);
                default:
                    return BartenderBobException.invalidInput(userInput);
                }
            } catch (BartenderBobException e) {
                //This exception is when the index from user input is out of the TASKS bounds.
                return e.tasksOutOfBounds();
            } catch (IndexOutOfBoundsException e) {
                //IndexOutOfBoundsException is for missing userInputs.
                BartenderBobException error = new BartenderBobException(firstWord);
                return error.displayError();
            } catch (IllegalArgumentException e) {
                return ui.showInvalidDateFormat();
            }
        }
    }


//    /**
//     * Receives and handles the user inputs to BartenderBob chatbot.
//     *
//     * @param taskList The tasks that the user has inputted into BartenderBob.
//     * @param ui Displays appropriate messages for its corresponding user input.
//     */
//    public void handleInput(TaskList taskList, Ui ui) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String userInput = scanner.nextLine();
//            String[] stringComponents = userInput.split(" ");
//            String firstWord = stringComponents[0];
//            String index;
//            if (firstWord.equals("bye")) {
//                ui.leave();
//                break;
//            } else {
//                try {
//                    switch (firstWord) {
//                        case "list":
//                            taskList.list();
//                            break;
//                        case "mark":
//                            index = stringComponents[1];
//                            taskList.markDone(index);
//                            break;
//                        case "unmark":
//                            index = stringComponents[1];
//                            taskList.unmarkDone(index);
//                            break;
//                        case "delete":
//                            index = stringComponents[1];
//                            taskList.delete(index);
//                            break;
//                        case "find":
//                            String substring = stringComponents[1];
//                            taskList.find(substring);
//                            break;
//                        case "todo":
//                            String str = userInput.split("todo ")[1];
//                            ToDo task = new ToDo(str);
//                            taskList.store(task);
//                            break;
//                        case "deadline":
//                            String[] deadlineComponents = userInput.split("deadline | /by ");
//                            Deadline deadline = new Deadline(deadlineComponents[1], deadlineComponents[2]);
//                            taskList.store(deadline);
//                            break;
//                        case "event":
//                            String[] eventComponents = userInput.split("event | /from | /to ");
//                            Event event = new Event(eventComponents[1], eventComponents[2], eventComponents[3]);
//                            taskList.store(event);
//                            break;
//                        default:
//                            BartenderBobException.invalidInput(userInput);
//                    }
//                } catch (BartenderBobException e) {
//                    //This exception is when the index from user input is out of the TASKS bounds.
//                    e.tasksOutOfBounds();
//                } catch (IndexOutOfBoundsException e) {
//                    //IndexOutOfBoundsException is for missing userInputs.
//                    BartenderBobException error = new BartenderBobException(firstWord);
//                    error.displayError();
//                } catch (IllegalArgumentException e) {
//                    ui.showInvalidDateFormat();
//                }
//            }
//        }
//    }

}
