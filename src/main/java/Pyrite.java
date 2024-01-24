import java.util.Arrays;
import java.util.Scanner;

public class Pyrite {
    String name = "Pyrite";
    String horizontal_line = "\t____________________________________________________________";
    String greeting = "\tHello! I'm " + name + "\n"
            + "\tWhat can I do for you?";
    String farewell = "\tBye. Hope to see you again soon!";
    String taskAddedAcknowledgement = "\t" + "Got it. I've added this task: ";
    Task[] list = new Task[100];
    int list_count = 0;
    private void printList(Task[] list, int list_count) {
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < list_count; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + ". " + list[i].toString());
        }
    }
    private int findCommand(String[] toSearch, String toFind) {
        for (int i = 0; i < toSearch.length; i++) {
            if (toSearch[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
    }
    private void assertValidId(int id) {
        if (id < 0 || id >= list_count) {
            throw new DukeException("Task to mark/unmark does not exist.");
        }
    }
    private int parseID(String parameters[]) {
        int id;
        try {
            id = Integer.parseInt(parameters[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Provide a valid id to mark/unmark.");
        }
        return id;
    }
    public void begin() {
        System.out.println(this.horizontal_line);
        System.out.println(this.greeting);
        System.out.println(this.horizontal_line);
        Scanner scanner = new Scanner(System.in);
        String input;
        // Solution below inspired by
        // https://stackoverflow.com/questions/31690570/java-scanner-command-system
        // https://stackoverflow.com/questions/4822256/java-is-there-an-easy-way-to-select-a-subset-of-an-array
        while (true) {
            System.out.println();
            input = scanner.nextLine();
            System.out.println(this.horizontal_line);
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    printList(this.list, this.list_count);
                } else {
                    boolean added_task = false;
                    // Commands with parameters
                    String parameters[] = input.split(" ");
                    if (parameters[0].equals("mark")) {
                        int id = parseID(parameters);
                        assertValidId(id);
                        list[id].setDone(true);
                        System.out.println("\t"
                                + "Nice! I've marked this task as done:\n"
                                + "\t\t"
                                + list[id].toString());
                    } else if (parameters[0].equals("unmark")) {
                        int id = parseID(parameters);
                        assertValidId(id);
                        list[id].setDone(false);
                        System.out.println("\t"
                                + "OK, I've marked this task as not done yet:\n"
                                + "\t\t"
                                + list[id].toString());
                        // 3 types of tasks
                        //  Solution below inspired by
                        //  https://stackoverflow.com/questions/11001720/get-only-part-of-an-array-in-java
                    } else if (parameters[0].equals("todo")) {
                        String description = String.join(
                                " ",
                                Arrays.copyOfRange(parameters, 1, parameters.length)
                        );
                        if (description.equals("")) {
                            throw new DukeException(
                                    "The description of a todo cannot be empty. Add the description after 'todo'."
                            );
                        }
                        list[list_count] = new ToDo(description);
                        list_count++;
                        added_task = true;
                    } else if (parameters[0].equals("deadline")) {
                        int descEnd = findCommand(parameters, "/by");
                        if (descEnd != -1){
                            String description = String.join(" ",
                                    Arrays.copyOfRange(parameters, 1, descEnd));
                            if (description.equals("")) {
                                throw new DukeException(
                                        "The description of a deadline cannot be empty. " +
                                                "Add the description after 'deadline'."
                                );
                            }
                            list[list_count] = new Deadline(
                                    description,
                                    String.join(" ",
                                            Arrays.copyOfRange(parameters, descEnd + 1, parameters.length))
                            );
                            list_count++;
                            added_task = true;
                        } else {
                            throw new DukeException("Incomplete Command. Add deadline using '/by'.");
                        }
                    } else if (parameters[0].equals("event")) {
                        int fromID = findCommand(parameters, "/from");
                        int toID = findCommand(parameters, "/to");
                        if (fromID != -1 && toID != -1) {
                            String description = String.join(
                                    " ", Arrays.copyOfRange(parameters, 1, (fromID < toID ? fromID : toID))
                            );
                            if (description.equals("")) {
                                throw new DukeException(
                                        "The description of a event cannot be empty. " +
                                                "Add the description after 'event'."
                                );
                            }
                            if (fromID < toID) {
                                list[list_count] = new Event(
                                        description,
                                        String.join(" ", Arrays.copyOfRange(parameters, fromID + 1, toID)),
                                        String.join(" ",
                                                Arrays.copyOfRange(parameters, toID + 1, parameters.length))
                                );
                                list_count++;
                            } else {
                                list[list_count] = new Event(
                                        description,
                                        String.join(" ",
                                                Arrays.copyOfRange(parameters, fromID + 1, parameters.length)),
                                        String.join(" ",
                                                Arrays.copyOfRange(parameters, toID + 1, fromID))
                                );
                                list_count++;
                            }
                        } else {
                            throw new DukeException("Incomplete Command. " +
                                    "Add start and end dates using '/from' and '/to'.");
                        }
                        added_task = true;
                    } else {
                        throw new DukeException("Unknown Command.");
                    }
                    if (added_task) {
                        System.out.println(taskAddedAcknowledgement);
                        System.out.println("\t\t" + list[list_count-1].toString());
                        System.out.println("\t" + "Now you have " + list_count + " tasks in the list.");
                    }
                }
            } catch (DukeException e) {
                System.out.println("\t" + e);
            }


            System.out.println(this.horizontal_line);
        }

        System.out.println(this.farewell);
        System.out.println(this.horizontal_line);
    }
}