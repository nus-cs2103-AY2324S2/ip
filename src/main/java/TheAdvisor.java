import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheAdvisor {
    public static void main(String[] args) throws IOException, TheAdvisorException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String intro = "Hello, I am The Advisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?";
        System.out.println(intro + "\n");

        // An ArrayList that stores the tasks to be done
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            try {
                String str = br.readLine();
                String[] strings = str.split(" ");
                Prompts prompts = getPrompts(strings[0]);
                switch (prompts) {
                    case BYE:
                        System.out.println("     Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                                " to help you in your investing journey!");

                        // Exit the program
                        System.exit(0);
                        break;
                    case LIST:
                        int counter = 1;
                        if (taskList.size() == 0) {
                            System.out.println("     Sorry, there are no tasks in your list. Try to add them :)");
                        } else {
                            System.out.println("     Here are the tasks in your list:");
                            for (int i = 0; i < taskList.size(); i++) {
                                System.out.println("     " + counter + ". " + taskList.get(i).toString());
                                counter++;
                            }
                        }
                        break;
                    case MARK:
                        checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                                + "mark + (number) to mark something on the list as completed.");
                        // 1-based indexing on input
                        int markNumber = Integer.parseInt(strings[1]);
                        checkIndex(markNumber, taskList.size());
                        Task mark = taskList.get(markNumber - 1);
                        mark.markDone();
                        System.out.println("     Nice! I've marked this task as done:\n" + "       " +
                                mark.toString());
                        break;
                    case UNMARK:
                        checkArrayLength(strings, 2, "Invalid format. Make sure that the format is: "
                                + "unmark + (number) to unmark something on the list.");
                        // 1-based indexing on input
                        int unmarkNumber = Integer.parseInt(strings[1]);
                        checkIndex(unmarkNumber, taskList.size());
                        Task unmarked = taskList.get(unmarkNumber - 1);
                        unmarked.unmark();
                        System.out.println("     OK, I've marked this task as not done yet:\n" + "       " +
                                unmarked.toString());
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
                        break;
                    case TODO:
                        String todo = str.substring(4);
                        checkEmptyDescription(todo, "The description for todo cannot be empty. Please try again.");
                        ToDos toDos = new ToDos(todo);
                        taskList.add(toDos);
                        System.out.println("     Got it. I've added this task:\n" +
                                "       " + toDos.toString() + "\n" +
                                "     Now you have " + taskList.size() +
                                " tasks in the list.");
                        break;
                    case DEADLINE:
                        String due = str.substring(8);
                        checkEmptyDescription(due, "The description for deadline cannot be empty. Please try again.");
                        String[] arrTask = due.split(" /by ");
                        checkArrayLength(arrTask, 2, "Invalid deadline format" +
                                "Please use the correct format: deadline + description + /by + date/day");
                        Deadline deadline = new Deadline(arrTask[0], arrTask[1]);
                        taskList.add(deadline);
                        System.out.println("     Got it. I've added this task:\n" +
                                "       " + deadline.toString() + "\n" +
                                "     Now you have " + taskList.size() +
                                " tasks in the list.");
                        break;
                    case EVENT:
                        String event = str.substring(5);
                        checkEmptyDescription(event, "The description for event cannot be empty. Please try again.");
                        String[] eventArr = event.split(" /from ");
                        checkArrayLength(eventArr, 2, "Invalid event format" +
                                "Please use the correct format: event + description + /from + date/day + /to +date/time");
                        String[] timings = eventArr[1].split(" /to");
                        checkArrayLength(eventArr, 2, "Invalid event format" +
                                "Please use the correct format: event + description + /from + date/day + /to +date/time");
                        Events events = new Events(eventArr[0], timings[0], timings[1]);
                        taskList.add(events);
                        System.out.println("     Got it. I've added this task:\n" +
                                "       " + events.toString() + "\n" +
                                "     Now you have " + taskList.size() +
                                " tasks in the list.");
                        break;
                    default:
                        throw new TheAdvisorException("Incorrect input, please try again with the correct input of either: "
                                + "todo, event, mark...etc");
                }
            } catch (TheAdvisorException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void checkIndex(int index, int size) throws TheAdvisorException {
        if (index <= 0) {
            throw new TheAdvisorException("We use 1-indexing for marking. Please try again.");
        } else if (index > size) {
            throw new TheAdvisorException("Out of bounds. We use 1-indexing for marking. Please try again.");
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

    private static Prompts getPrompts(String prompt) throws TheAdvisorException {
        try {
            return Prompts.valueOf(prompt.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TheAdvisorException("Incorrect input, please try again with the correct input of either: "
                    + "todo, event, mark...etc");
        }
    }
}
