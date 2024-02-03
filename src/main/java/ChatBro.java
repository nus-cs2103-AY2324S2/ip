import java.util.ArrayList;

public class ChatBro {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        //Database db = new Database();

        ArrayList<Task> taskList = new ArrayList<>(101);
        //taskList.add(null); // First element left empty for 1-based indexing
        for (int i = 0; i < 102; i++) {
            taskList.add(null);
        }

        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readInput();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list bro:\n" +
                            "_________________________");
                    for (int i = 1; i <= 100; i++) {
                        if (taskList.get(i) == null) {
                            break;
                        }
                        System.out.println(i + "." + taskList.get(i).toString());
                    }
                    System.out.println("_________________________\n");
                    break;

                case "todo":
                    String[] todoSplit = input.split("todo ");
                    try {
                        String todoName = todoSplit[1];
                        if (todoName.trim().isEmpty()) { // Empty task description
                            System.out.println("_________________________\n" +
                                    "Hey bro, task description cannot be empty.\n" +
                                    "_________________________\n");
                            break;
                        }
                        for (int i = 1; i <= 100; i++) {
                            if (taskList.get(i) == null) {
                                taskList.add(i, new ToDo(todoName));
                                Task.incrementTaskCount();
                                System.out.println("_________________________\n" +
                                        "Ok bro, I've added: \n" + taskList.get(i).toString() + "\n into your list.\n" +
                                        "You've got " + Task.getTaskCount() + " task(s) now.\n" +
                                        "_________________________\n");
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("_________________________\n" +
                                "Hey bro, make sure to follow the format:\n" +
                                "todo <task description> (task description cannot be empty)\n" +
                                "_________________________\n");
                        break;
                    }
                    break;

                case "deadline":
                    String[] deadlineSplit = input.split(" /by ");
                    try {
                        String deadlineName = deadlineSplit[0].substring(9); // 9 is the length of "deadline "
                        if (deadlineName.trim().isEmpty()) { // Empty task description (whitespace)
                            System.out.println("_________________________\n" +
                                    "Hey bro, the given task description cannot be empty.\n" +
                                    "_________________________\n");
                            break;
                        }
                        if (deadlineSplit[1].trim().isEmpty()) { // Empty deadline (whitespace)
                            System.out.println("_________________________\n" +
                                    "Hey bro, the given deadline cannot be empty.\n" +
                                    "_________________________\n");
                            break;
                        }
                        for (int i = 1; i <= 100; i++) {
                            if (taskList.get(i) == null) {
                                taskList.add(i, new Deadline(deadlineName, deadlineSplit[1]));
                                Task.incrementTaskCount();
                                System.out.println("_________________________\n" +
                                        "Ok bro, I've added: \n" + taskList.get(i).toString() + "\n into your list.\n" +
                                        "You've got " + Task.getTaskCount() + " task(s) now.\n" +
                                        "_________________________\n");
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                        System.out.println("_________________________\n" +
                                "Hey bro, make sure to follow the format:\n" +
                                "deadline <task description> /by <deadline date>\n" +
                                "(task description and deadline date cannot be empty)\n" +
                                "_________________________\n");
                        break;
                    }
                    break;

                case "event":
                    String[] eventFromSplit = input.split(" /from ");
                    try {
                        String eventName = eventFromSplit[0].substring(6); // 6 is the length of "event "
                        String[] eventToSplit = eventFromSplit[1].split(" /to "); // ETS[0] is from, ETS[1] is to
                        if (eventName.trim().isEmpty()) { // Empty task description (whitespace)
                            System.out.println("_________________________\n" +
                                    "Hey bro, the task description cannot be empty.\n" +
                                    "_________________________\n");
                            break;
                        }
                        if (eventToSplit[0].trim().isEmpty()) { // Empty start time (whitespace)
                            System.out.println("_________________________\n" +
                                    "Hey bro, the start time cannot be empty.\n" +
                                    "_________________________\n");
                            break;
                        }
                        if (eventToSplit[1].trim().isEmpty()) { // Empty end time (whitespace)
                            System.out.println("_________________________\n" +
                                    "Hey bro, the end time cannot be empty.\n" +
                                    "_________________________\n");
                            break;
                        }
                        for (int i = 1; i <= 100; i++) {
                            if (taskList.get(i) == null) {
                                taskList.add(i, new Event(eventName, eventToSplit[0], eventToSplit[1]));
                                Task.incrementTaskCount();
                                System.out.println("_________________________\n" +
                                        "Ok bro, I've added: \n" + taskList.get(i).toString() + "\n into your list.\n" +
                                        "You've got " + Task.getTaskCount() + " task(s) now.\n" +
                                        "_________________________\n");
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                        System.out.println("_________________________\n" +
                                "Hey bro, make sure to follow the format:\n" +
                                "event <task description> /from <start time> /to <end time>\n" +
                                "(task description, start time and end time cannot be empty)\n" +
                                "_________________________\n");
                        break;
                    }
                    break;

                case "mark":
                    int taskNum = Integer.parseInt(inputSplit[1]);
                    if (taskList.get(taskNum) == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    taskList.get(taskNum).markAsDone();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum + " is marked as done:\n" +
                            taskList.get(taskNum).toString() +
                            "\n_________________________\n");
                    break;

                case "unmark":
                    int taskNum2 = Integer.parseInt(inputSplit[1]);
                    if (taskList.get(taskNum2) == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    taskList.get(taskNum2).markAsUndone();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum2 + " is marked as not done yet:\n" +
                            taskList.get(taskNum2).toString() +
                            "\n_________________________\n");
                    break;

                case "delete":
                    int taskNum3 = Integer.parseInt(inputSplit[1]);
                    if (taskList.get(taskNum3) == null) {
                        System.out.println("_________________________\n" +
                                "Sorry bro, this task doesn't exist in your list.\n" +
                                "_________________________\n");
                        break;
                    }
                    String taskName = taskList.get(taskNum3).toString();
                    taskList.remove(taskNum3);
                    Task.decrementTaskCount();
                    System.out.println("_________________________\n" +
                            "Got it bro! Task " + taskNum3 + " has been removed:\n" +
                            taskName + "\n" +
                            "_________________________\n");
                    break;

                case "bye":
                    isRunning = false;
                    ui.printBye();
                    break;

                default:
                    System.out.println("_________________________\n" +
                            "Sorry bro, I don't understand what you mean.\n" +
                            "_________________________\n");
                    break;
            }
        }
    }
}
