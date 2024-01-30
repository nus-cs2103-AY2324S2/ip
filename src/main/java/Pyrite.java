import javax.imageio.IIOException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Pyrite {
    static String name = "Pyrite";
    static String horizontal_line = "\t____________________________________________________________";
    static String greeting = "\tHello! I'm " + name + "\n"
            + "\tWhat can I do for you?";
    static String farewell = "\tBye. Hope to see you again soon!";
    static String taskAddedAcknowledgement = "\t" + "Got it. I've added this task: ";
    TaskList list = new TaskList();
    StateFile file = new StateFile();
    private void printList(TaskList list) {
        System.out.println("\t" + "Here are the tasks in your list:");
        System.out.println("\t" + list.toString());
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
        if (id < 0 || id >= list.size()) {
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
    private void loadState() {
        try {
            this.list = file.loadObject();
        } catch (IOException | ClassNotFoundException e){
            // File issue, try to save blank state
            System.out.println("\tUnable to read saved state, creating new file...");
            this.saveState();
        }
    }
    private void saveState() {
        try {
            file.saveObject(this.list);
        } catch (IOException e){
            System.out.println("\tUnable to save state: " + e.toString());
        }
    }
    public void begin() {
        System.out.println(this.horizontal_line);
        System.out.println(this.greeting);
        // Load list from file
        this.loadState();
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
                    printList(this.list);
                } else {
                    boolean added_task = false;
                    // Commands with parameters
                    String parameters[] = input.split(" ");
                    if (parameters[0].equals("mark")) {
                        int id = parseID(parameters);
                        assertValidId(id);
                        list.setDone(id,true);
                        System.out.println("\t"
                                + "Nice! I've marked this task as done:\n"
                                + "\t\t"
                                + list.toString(id));
                    } else if (parameters[0].equals("unmark")) {
                        int id = parseID(parameters);
                        assertValidId(id);
                        list.setDone(id, false);
                        System.out.println("\t"
                                + "OK, I've marked this task as not done yet:\n"
                                + "\t\t"
                                + list.toString(id));
                        // 3 types of tasks
                        //  Solution below inspired by
                        //  https://stackoverflow.com/questions/11001720/get-only-part-of-an-array-in-java
                    } else if (parameters[0].equals("delete")) {
                        int id = parseID(parameters);
                        assertValidId(id);
                        System.out.println("\t"
                                + "Noted. I've removed this task:\n"
                                + "\t\t"
                                + list.toString(id));
                        list.remove(id);
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
                        list.add(new ToDo(description));
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
                            list.add(new Deadline(description,
                                    LocalDateTime.parse(String.join("", Arrays.copyOfRange(parameters,
                                            descEnd + 1,
                                            parameters.length)))
                            ));
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
                                list.add( new Event(
                                        description,
                                        LocalDateTime.parse(String.join("", Arrays.copyOfRange(parameters,
                                                fromID + 1,
                                                toID))),
                                        LocalDateTime.parse(String.join("", Arrays.copyOfRange(parameters,
                                                toID + 1,
                                                parameters.length)))
                                ));
                            } else {
                                list.add( new Event(
                                        description,
                                        LocalDateTime.parse(String.join("", Arrays.copyOfRange(parameters,
                                                fromID + 1,
                                                parameters.length))),
                                        LocalDateTime.parse(String.join("", Arrays.copyOfRange(parameters,
                                                toID + 1,
                                                fromID)))
                                        ));
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
                        System.out.println("\t\t" + list.toString(list.size()-1));
                        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list.");
                    }
                }
            } catch (DukeException e) {
                System.out.println("\t" + e);
            } catch (DateTimeParseException e) {
                System.out.println("\t" + "Invalid datetime format. Use yyyy-mm-ddThh:mm.");
            }


            System.out.println(this.horizontal_line);
            // Save state
            this.saveState();
        }

        System.out.println(this.farewell);
        System.out.println(this.horizontal_line);
    }
}