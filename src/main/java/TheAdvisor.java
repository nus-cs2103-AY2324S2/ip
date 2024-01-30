import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;

public class TheAdvisor implements Serializable {
    private static final String FILE_PATH = "list.bin";

    private Ui ui;

    private Storage storage;

    public TheAdvisor() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
    }
    public void run() {
        // An ArrayList that stores the tasks to be done
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            taskList = storage.loadList();
        } catch (IOException err) {
            System.out.println("No list found, creating empty task list");
        } catch (ClassNotFoundException err) {
            System.out.println("Class mismatch. Check and try again");
        }

        ui.intro();

        while (true) {
            try {
                String str = ui.getUserInput();
                String[] strings = str.split(" ");
                Parser.Prompts prompt = Parser.parsePrompt(strings[0]);
                switch (prompt) {
                    case BYE:
                        ui.goodbye();
                        break;
                    case LIST:
                        ui.printList(taskList);
                        break;
                    case MARK:
                        checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                                + "mark + (number) to mark something on the list as completed.");
                        // 1-based indexing on input
                        int markNumber = Integer.parseInt(strings[1]);
                        checkIndex(markNumber, taskList.size());
                        Task mark = taskList.get(markNumber - 1);
                        checkMarked(mark);
                        mark.markDone();
                        System.out.println("     Nice! I've marked this task as done:\n" + "       " +
                                mark.toString());
                        storage.saveTasks(taskList);
                        break;
                    case UNMARK:
                        checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                                + "unmark + (number) to unmark something on the list.");
                        // 1-based indexing on input
                        int unmarkNumber = Integer.parseInt(strings[1]);
                        checkIndex(unmarkNumber, taskList.size());
                        Task unmarked = taskList.get(unmarkNumber - 1);
                        checkUnmarked(unmarked);
                        unmarked.unmark();
                        System.out.println("     OK, I've marked this task as not done yet:\n" + "       " +
                                unmarked.toString());
                        storage.saveTasks(taskList);
                        break;
                    case DELETE:
                        checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                                + "delete + (number) to delete something from the list.");
                        // 1-based indexing on input
                        int deleteNumber = Integer.parseInt(strings[1]);
                        checkIndex(deleteNumber, taskList.size());
                        Task deleted = taskList.get(deleteNumber - 1);
                        taskList.remove(deleteNumber - 1);
                        System.out.println("     Noted. I've removed this task:\n" + "       " +
                                deleted.toString() + "\n" + "     Now you have " + taskList.size() + " tasks in the list.");
                        storage.saveTasks(taskList);
                        break;
                    case TODO:
                        String todo = str.substring(4);
                        checkEmptyDescription(todo, "The description for todo cannot be empty. " +
                                "The input should be <todo> + description");
                        ToDos toDos = new ToDos(todo);
                        taskList.add(toDos);
                        System.out.println("     Got it. I've added this task:\n" +
                                "       " + toDos.toString() + "\n" +
                                "     Now you have " + taskList.size() +
                                " tasks in the list.");
                        storage.saveTasks(taskList);
                        break;
                    case DEADLINE:
                        String due = str.substring(8);
                        checkEmptyDescription(due, "The description for deadline cannot be empty. " +
                                "The input should be <deadline> + description");
                        String[] arrTask = due.split(" /by ");
                        checkArrayLength(arrTask, 2, "Invalid deadline format " +
                                "Please use the correct format: deadline + description + /by + <YYYY-MM-DD HHmm>");
                        try {
                            Deadline deadline = new Deadline(arrTask[0], LocalDateTime.parse(arrTask[1], Task.inputFormat));
                            taskList.add(deadline);
                            System.out.println("     Got it. I've added this task:\n" +
                                    "       " + deadline.toString() + "\n" +
                                    "     Now you have " + taskList.size() +
                                    " tasks in the list.");
                            storage.saveTasks(taskList);
                        } catch (DateTimeException e) {
                            throw new TheAdvisorException("Incorrect format of your timestamp! " +
                                    "Please input YYYY-MM-DD HHmm");
                        }
                        break;
                    case EVENT:
                        String event = str.substring(5);
                        checkEmptyDescription(event, "The description for event cannot be empty. The " +
                                "input should be <event> + description + /from <YYYY-MM-DD HHmm> + /to <YYYY-MM-DD HHmm>");
                        String[] eventArr = event.split(" /from ");
                        checkArrayLength(eventArr, 2, "Invalid event format. " +
                                "The input should be <event> + description + /from <YYYY-MM-DD HHmm> + /to <YYYY-MM-DD HHmm>");
                        String[] timings = eventArr[1].split(" /to");
                        checkArrayLength(timings, 2, "Invalid event format" +
                                "The input should be <event> + description + /from <YYYY-MM-DD HHmm> + /to <YYYY-MM-DD HHmm>");
                        String startStr = timings[0].trim();
                        String endStr = timings[1].trim();
                        try {
                            LocalDateTime start = LocalDateTime.parse(startStr, Task.inputFormat);
                            LocalDateTime end = LocalDateTime.parse(endStr, Task.inputFormat);
                            Events events = new Events(eventArr[0], start, end);
                            taskList.add(events);
                            System.out.println("     Got it. I've added this task:\n" +
                                    "       " + events.toString() + "\n" +
                                    "     Now you have " + taskList.size() +
                                    " tasks in the list.");
                            storage.saveTasks(taskList);
                        } catch (DateTimeException e) {
                            throw new TheAdvisorException("Incorrect format of your timestamp! " +
                                    "Please input YYYY-MM-DD HHmm");
                        }
                        break;
                    default:
                        throw new TheAdvisorException("Incorrect input, please try again with the correct input of either: "
                                + "todo, event, mark...etc");
                }
            } catch (TheAdvisorException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Please input something >_<*");
            }
        }

    }

    public static void main(String[] args) throws IOException {
        TheAdvisor advisor = new TheAdvisor();
        advisor.run();
    }

    private static void checkIndex(int index, int size) throws TheAdvisorException {
        if (index <= 0) {
            throw new TheAdvisorException("We use 1-indexing for marking. Please try again.");
        } else if (index > size) {
            throw new TheAdvisorException("Out of bounds. We use 1-indexing for marking. Please try again.");
        } else if (size == 0) {
            throw new TheAdvisorException("The list is empty! Start adding in things :)");
        }
    }

    private static void checkMarked(Task task) throws TheAdvisorException {
        if (task.isDone) {
            throw new TheAdvisorException("The task is already marked! Carry on.");
        }
    }

    private static void checkUnmarked(Task task) throws TheAdvisorException {
        if (!task.isDone) {
            throw new TheAdvisorException("The task is already unmarked! Carry on.");
        }
    }

    private static void checkEmptyDescription(String description, String errorMessage) throws TheAdvisorException {
        if (description.isEmpty()) {
            throw new TheAdvisorException(errorMessage);
        }
    }

    private static void checkArrayLength(String[] array, int expectedLength, String errorMessage) throws TheAdvisorException {
        if (array.length != expectedLength) {
            throw new TheAdvisorException(errorMessage);
        }
    }

    private static void checkDateTimeInput(String errorMessage) throws TheAdvisorException {
        throw new TheAdvisorException(errorMessage);
    }
}
