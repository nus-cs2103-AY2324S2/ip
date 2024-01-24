import java.util.Scanner;

public class Earl {

    private static final String padding = " ".repeat(4);
    private static final Task[] tasks = new Task[100];
    private static int count = 0;

    private static void printDivider() {
        System.out.println(padding + "_".repeat(50));
    }

    private static void makeResponse(String... arr) {
        printDivider();
        for (String s : arr) {
            System.out.println(padding + s);
        }
        printDivider();
    }

    private static void handleCommand(String input) throws EarlException {
        String[] command = input.split("\\s", 2);
        switch (command[0]) {
            case "list":
                if (count > 0) {
                    String[] temp = new String[count];
                    for (int i = 0; i < count; ++i) {
                        temp[i] = i+1 + "." + tasks[i];
                    }
                    makeResponse(temp);
                } else {
                    makeResponse("There is nothing to list.");
                }
                break;
            case "mark": {
                try {
                    int idx = Integer.parseInt(command[1]) - 1;
                    if (idx >= count) {
                        throw new IndexOutOfBoundsException();
                    }
                    if (tasks[idx].markAsDone()) {
                        makeResponse(
                                "Item marked as done.",
                                padding + tasks[idx]
                        );
                    } else {
                        makeResponse("Item already marked as done.");
                    }
                    break;
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new EarlException(
                            "Error, not a valid item number within range.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "mark 3"
                    );
                } catch (Exception e) {
                    throw new EarlException(
                            "Error, unknown use of unmark.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "mark 3"
                    );
                }
            }
            case "unmark": {
                try {
                    int idx = Integer.parseInt(command[1]) - 1;
                    if (idx >= count) {
                        throw new IndexOutOfBoundsException();
                    }
                    if (tasks[idx].markUndone()) {
                        makeResponse(
                                "Item marked as not done.",
                                padding + tasks[idx]
                        );
                    } else {
                        makeResponse("Item already marked as not done.");
                    }
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new EarlException(
                            "Error, not a valid item number within range.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "unmark 3"
                    );
                } catch (Exception e) {
                    throw new EarlException(
                            "Error, unknown use of unmark.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "unmark 3"
                    );
                }
                break;
            }
            case "todo":
                try {
                    tasks[count++] = new Todo(command[1]);
                    makeResponse(
                            "Added new todo.",
                            padding + tasks[count - 1],
                            "There are " + count + " tasks tracked."
                    );
                } catch (IndexOutOfBoundsException e) {
                    throw new EarlException(
                            "Error, missing task name.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "todo <task_name>"
                    );
                } catch (Exception e) {
                    throw new EarlException(
                            "Error, unknown use of todo.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "todo <task_name>"
                    );
                }
                break;
            case "deadline":
                try {
                    command = command[1].split("\\s/by\\s");
                    tasks[count++] = new Deadline(command[0], command[1]);
                    makeResponse(
                            "Added new deadline.",
                            padding + tasks[count - 1],
                            "There are " + count + " tasks tracked."
                    );
                } catch (IndexOutOfBoundsException e) {
                    throw new EarlException(
                            "Error, invalid deadline format.\n"
                            + padding + "Example use:\n"
                            + padding + padding
                            + "deadline <task_name> /by <end>"
                    );
                } catch (Exception e) {
                    throw new EarlException(
                            "Error, unknown use of deadline.\n"
                            + padding + "Example use:\n"
                            + padding + padding
                            + "deadline <task_name> /by <end>"
                    );
                }
                break;
            case "event":
                try {
                    command = command[1].split("\\s/(from|to)\\s");
                    tasks[count++] = new Event(
                            command[0],
                            command[1],
                            command[2]
                    );
                    makeResponse(
                            "Added new event.",
                            padding + tasks[count - 1],
                            "There are " + count + " tasks tracked."
                    );
                } catch (IndexOutOfBoundsException e) {
                    throw new EarlException(
                            "Error, invalid event format.\n"
                            + padding + "Example use:\n"
                            + padding + padding
                            + "event <task_name> /from <start> /to <end>"
                    );
                } catch (Exception e) {
                    throw new EarlException(
                            "Error, unknown use of event.\n"
                            + padding + "Example use:\n"
                            + padding + padding
                            + "event <task_name> /from <start> /to <end>"
                    );
                }
                break;
            default:
                makeResponse(
                        "Error, unknown command.",
                        "Valid commands: list, mark, unmark, todo, deadline, event"
                );
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        makeResponse("Hello! I'm Earl", "What can I do for you?");

        // main loop
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                handleCommand(input);
            } catch (EarlException e) {
                makeResponse(e.getMessage());
            }
            input = sc.nextLine();
        }

        makeResponse("Goodbye! See you soon.");
        sc.close();
    }
}