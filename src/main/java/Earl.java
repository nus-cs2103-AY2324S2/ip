import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Earl {

    private static final String padding = " ".repeat(4);
    private static final List<Task> tasks = new ArrayList<>();

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
                if (!tasks.isEmpty()) {
                    String[] temp = new String[tasks.size()];
                    for (int i = 0; i < tasks.size(); ++i) {
                        temp[i] = i+1 + "." + tasks.get(i);
                    }
                    makeResponse(temp);
                } else {
                    makeResponse("There is nothing to list.");
                }
                break;
            case "mark": {
                try {
                    int idx = Integer.parseInt(command[1]) - 1;
                    if (tasks.get(idx).markAsDone()) {
                        makeResponse(
                                "Item marked as done.",
                                padding + tasks.get(idx)
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
                    if (tasks.get(idx).markUndone()) {
                        makeResponse(
                                "Item marked as not done.",
                                padding + tasks.get(idx)
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
                    tasks.add(new Todo(command[1]));
                    makeResponse(
                            "Added new todo.",
                            padding + tasks.get(tasks.size()-1),
                            "There are " + tasks.size() + " tasks tracked."
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
                    tasks.add(new Deadline(command[0], command[1]));
                    makeResponse(
                            "Added new deadline.",
                            padding + tasks.get(tasks.size()-1),
                            "There are " + tasks.size() + " tasks tracked."
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
                    tasks.add(new Event(command[0], command[1], command[2]));
                    makeResponse(
                            "Added new event.",
                            padding + tasks.get(tasks.size() - 1),
                            "There are " + tasks.size() + " tasks tracked."
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
            case "delete": {
                try {
                    int idx = Integer.parseInt(command[1]) - 1;
                    makeResponse(
                            "Item deleted.",
                            padding + tasks.remove(idx)
                    );
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    throw new EarlException(
                            "Error, not a valid item number within range.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "unmark 3"
                    );
                } catch (Exception e) {
                    throw new EarlException(
                            "Error, unknown use of delete.\n"
                            + padding + "Example use:\n"
                            + padding + padding + "delete 3"
                    );
                }
                break;
            }
            default:
                makeResponse(
                        "Error, unknown command.",
                        "Valid commands:",
                        padding + "list, mark, unmark, todo, deadline, event"
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