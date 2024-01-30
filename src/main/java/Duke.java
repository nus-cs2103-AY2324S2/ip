import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static String name = "Lunaris";
    static String indentation = "  ";
    static String indentedLine = "  _________________________________________________________";
    // Just for convenience of copy paste.
    // System.out.println(indentedLine);

    private static final String FILE_DIR = "./data";

    private static final String FILE_PATH = "./data/taskList.txt";

    public enum Command {
        BYE, LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN;

        public static Command getCategory(String input) {
            try {
                return Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    private static ArrayList<Task> list;

    public static void main(String[] args) throws DukeException, IOException {

        list = loadTasksFile();

        // Print out greeting message
        System.out.println(indentedLine);
        System.out.println(indentation + "Hey! I'm " + name + "\n"
                + indentation + "Is there anything I can do for you?");
        System.out.println(indentedLine);

        Scanner sc = new Scanner(System.in);

        while (true) {
            Command category = Command.getCategory(sc.next());
            switch (category) {
                case BYE:
                    System.out.println(indentedLine);
                    System.out.println(indentation +
                            "Leaving so soon? Alright, have a great day ahead!");
                    System.out.println(indentedLine);
                    return;
                case LIST:
                    System.out.println(indentedLine);
                    System.out.println(indentation + "Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task currTask = list.get(i);
                        System.out.println(indentation + (i + 1) + "." + currTask.toString());
                    }
                    System.out.println(indentedLine);
                    break;
                default:
                    addTask(list, category, sc);
            }
        }
    }

    private static ArrayList<Task> loadTasksFile() throws DukeException {
        File directory = new File(FILE_DIR);
        File file = new File(FILE_PATH);
        try {
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ArrayList<Task> inputList = new ArrayList<>();
            Scanner fileSc = new Scanner(file);
            while (fileSc.hasNext()) {
                String task = fileSc.nextLine();
                String[] argument = task.split(" \\| ");
                String category = argument[0];
                String status = argument[1];
                String description = argument[2];

                switch (category) {
                case "T":
                    inputList.add(new ToDo(status, description));
                    break;
                case "D":
                    String by = argument[3];
                    inputList.add(new Deadline(status, description, by));
                    break;
                case "E":
                    String[] duration = argument[3].split(" - ");
                    String start = duration[0];
                    String end = duration[1];
                    inputList.add(new Event(status, description, start, end));
                    break;
                default:
                    break;
                }
            }
            fileSc.close();
            return inputList;
        } catch (FileNotFoundException e) {
            throw new DukeException(indentation + e.getMessage());
        }
    }

    private static void saveTasksFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        StringBuilder msg = new StringBuilder();
        for (Task task : list) {
            msg.append(task.toFile()).append("\n");
        }
        fw.write(msg.toString());
        fw.close();
    }

    public static void addTask(ArrayList<Task> taskList, Command category, Scanner sc) throws IOException {
        switch (category) {
            case UNMARK:
                int unmarkId = sc.nextInt() - 1;
                if (unmarkId < 0 || unmarkId >= list.size()) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please select a valid task for me to unmark!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                    }
                }
                taskList.get(unmarkId).markNotDone();
                System.out.println(indentedLine);
                System.out.println(indentation + "Ok, I've marked this task as not done yet:");
                System.out.println(indentation + taskList.get(unmarkId).toString());
                System.out.println(indentedLine);
                saveTasksFile();
                break;

            case MARK:
                int markId = sc.nextInt() - 1;
                if (markId < 0 || markId >= list.size()) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please select a valid task for me to mark!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        sc.next();
                    }
                }
                taskList.get(markId).markDone();
                System.out.println(indentedLine);
                System.out.println(indentation + "Nice! I've marked this task as done:");
                System.out.println(indentation + taskList.get(markId).toString());
                System.out.println(indentedLine);
                saveTasksFile();
                break;

            case DELETE:
                int deleteId = sc.nextInt() - 1;
                if (deleteId < 0 || deleteId >= list.size()) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please select a valid task for me to delete!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                    }
                }
                System.out.println(indentedLine);
                System.out.println(indentation + "Noted. I've removed this task:");
                System.out.println(indentation + taskList.get(deleteId).toString());
                taskList.remove(deleteId);
                System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                saveTasksFile();
                break;

            case TODO:
                String toDoDescription = sc.nextLine();
                if (toDoDescription.isEmpty()) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please give me a description of the todo as well! >.<\n" +
                                indentation + "Format should be todo (description)!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        return;
                    }
                }
                ToDo toDo = new ToDo(toDoDescription);
                taskList.add(toDo);
                System.out.println(indentation + toDo);
                System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                saveTasksFile();
                break;
            case DEADLINE:
                String deadlineDescription = sc.nextLine();
                if (!deadlineDescription.contains(" /by ")) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please give me a description of the deadline as well! >.<\n" +
                                indentation + "Format should be deadline (description) /by (date)!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        return;
                    }
                }
                String[] deadlineArguments = deadlineDescription.split(" /by ");
                Deadline deadline = new Deadline(deadlineArguments[0], deadlineArguments[1]);
                taskList.add(deadline);
                System.out.println(indentation + deadline);
                System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                saveTasksFile();
                break;
            case EVENT:
                String eventDescription = sc.nextLine();
                if (!eventDescription.contains(" /from ") || !eventDescription.contains(" /to ")) {
                    try {
                        System.out.println(indentedLine);
                        throw new DukeException("Sorry, please give me a description of the event as well! >.<\n" +
                                indentation + "Format should be event (description) /from (time) /to (time)!");
                    } catch (DukeException e) {
                        System.out.println(indentation + e.getMessage());
                        System.out.println(indentedLine);
                        return;
                    }
                }
                String[] eventArguments = eventDescription.split(" /from ");
                String[] eventDuration = eventArguments[1].split(" /to ");
                Event event = new Event(eventArguments[0], eventDuration[0], eventDuration[1]);
                taskList.add(event);
                System.out.println(indentation + event);
                System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(indentedLine);
                saveTasksFile();
                break;
            default:
                try {
                    System.out.println(indentedLine);
                    throw new DukeException("Sorry, I cannot understand what this is!");
                } catch (DukeException e) {
                    System.out.println(indentation + e.getMessage());
                    System.out.println(indentedLine);
                }
        }
    }
}
