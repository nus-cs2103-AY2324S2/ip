import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Eggy {
    public static final String name = "Eggy";
    public static List<Task> taskList = new ArrayList<>();

    public enum CommandType {
        LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        loadTaskList();

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] commandArr = command.split(" ", 2);
            System.out.println("    ____________________________________________________________");
            try {
                validateCommand(commandArr);
                CommandType commandType = CommandType.valueOf(commandArr[0].toUpperCase());
                switch (commandType) {
                case LIST:
                    showList();
                    break;
                case DELETE:
                    Task task = taskList.remove(Integer.parseInt(commandArr[1]) - 1);
                    saveTaskListToFile();
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       " + task.toString());
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    break;
                case MARK:
                    taskList.get(Integer.parseInt(commandArr[1]) - 1).markDone();
                    saveTaskListToFile();
                    break;
                case UNMARK:
                    taskList.get(Integer.parseInt(commandArr[1]) - 1).unmarkDone();
                    saveTaskListToFile();
                    break;
                case TODO:
                    Todo newTodo = new Todo(commandArr[1]);
                    addTask(newTodo);
                    break;
                case DEADLINE:
                    String[] deadlineSplit = commandArr[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlineSplit[0], parseDateTime(deadlineSplit[1]));
                    addTask(newDeadline);
                    break;
                case EVENT:
                    String[] eventSplit = commandArr[1].split(" /from | /to ");
                    Event newEvent = new Event(eventSplit[0], parseDateTime(eventSplit[1]), parseDateTime(eventSplit[2]));
                    addTask(newEvent);
                    break;
                default:
                    throw new EggyException("");
                }
            } catch (EggyException e) {
                System.out.println("     " + e.getMessage());
            } finally {
                System.out.println("    ____________________________________________________________");
                command = sc.nextLine();
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }

    public static void validateCommand(String[] commandArr) throws Exception {
        try {
            CommandType commandType = CommandType.valueOf(commandArr[0].toUpperCase());
            if (commandArr.length < 2 && (commandType == CommandType.TODO || commandType == CommandType.DEADLINE
                    || commandType == CommandType.EVENT)) {
                throw new IncompleteTaskException(commandType.name().toLowerCase());
            } else if (commandType == CommandType.DELETE || commandType == CommandType.MARK
                    || commandType == CommandType.UNMARK) {
                if (commandArr.length < 2) {
                    throw new IncompleteCommandException(commandType.name().toLowerCase());
                } else {
                    try {
                        int taskNumber = Integer.parseInt(commandArr[1]);
                        if (taskNumber < 1 || taskNumber > taskList.size()) {
                            throw new TaskListIndexOutOfBoundsException(taskNumber, taskList.size());
                        }
                    } catch (NumberFormatException e) {
                        throw new TaskNumberFormatException();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }

    public static void addTask(Task task) {
        taskList.add(task);
        saveTaskListToFile();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void showList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
        }
    }

    public static void loadTaskList() {
        try {
            File file = new File("data/eggy.txt");
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    String[] taskArr = line.split(" \\| ");
                    Task task;
                    switch (taskArr[0]) {
                    case "T":
                        task = new Todo(taskArr[2], taskArr[1].equals("1"));
                        break;
                    case "D":
                        task = new Deadline(taskArr[2], LocalDateTime.parse(taskArr[3]), taskArr[1].equals("1"));
                        break;
                    case "E":
                        task = new Event(taskArr[2], LocalDateTime.parse(taskArr[3]), LocalDateTime.parse(taskArr[4]), taskArr[1].equals("1"));
                        break;
                    default:
                        throw new RuntimeException("Invalid task type");
                    }
                    taskList.add(task);
                    line = br.readLine();
                }
                br.close();
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveTaskListToFile() {
        try {
            File file = new File("data/eggy.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            for (Task task : taskList) {
                fw.write(task.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) {
        System.out.println(LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")));
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }
}
