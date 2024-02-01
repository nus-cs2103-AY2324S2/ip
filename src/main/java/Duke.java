import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static final String LINE = "    ____________________________________________________________";
    public static void main(String[] args) throws EmptyTaskNameException, NoTaskTypeException, IOException {
        Scanner sc = new Scanner(System.in);

        System.out.printf(
                "%s\n     Hello! I'm Buto\n     What can I do for you?\n%s\n",
                LINE, LINE
        );
        loadFile();

        String command = sc.next();

        while (!command.equals("bye")) {
            switch (command) {
            case "list" :
                printTaskList();
                break;
            case "todo" :
                try {
                    String todoName = sc.nextLine();
                    checkEmptyTask(todoName);
                    addTask(new ToDo(todoName.trim(), false));
                } catch (EmptyTaskNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline" :
                try {
                    String[] splitDeadline = sc.nextLine().split(" /by ");
                    checkEmptyTask(splitDeadline[0]);
                    addTask(new Deadline(splitDeadline[0].trim(), false, splitDeadline[1]));
                } catch (EmptyTaskNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "event" :
                try {
                    String[] splitName = sc.nextLine().split(" /from ");
                    checkEmptyTask(splitName[0]);
                    String[] startEnd = splitName[1].split(" /to ");
                    addTask(new Event(splitName[0].trim(), false, startEnd[0], startEnd[1]));
                } catch (EmptyTaskNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "mark" :
                int markIndex = sc.nextInt() - 1;
                taskList.get(markIndex).mark();
                printResponse("Nice! I've marked this task as done:", markIndex);
                break;
            case "unmark" :
                int unmarkIndex = sc.nextInt() - 1;
                taskList.get(unmarkIndex).unmark();
                printResponse("OK, I've marked this task as not done yet:", unmarkIndex);
                break;
            case "delete" :
                Task removed = taskList.remove(sc.nextInt()-1);
                System.out.println(LINE + "\n     Got it. I've removed this task:\n       " + removed.toString());
                System.out.println("     Now you have " + taskList.size() + " tasks in the list.\n" + LINE);
                break;
            default :
                try {
                    throw new NoTaskTypeException();
                } catch (NoTaskTypeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            command = sc.next();
        }

        sc.close();
        writeFile();
        System.out.printf("%s\n     Bye. Hope to see you again soon!\n%s",
                LINE, LINE);
    }

    public static void loadFile() throws FileNotFoundException {
        try {
            File f = new File("ip/src/main/data/tasks.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] taskDescriptions = sc.nextLine().split(" ");
                String taskName = taskDescriptions[0];
                boolean done = Boolean.parseBoolean(taskDescriptions[1]);
                switch (taskDescriptions.length) {
                    case 2 :
                        taskList.add(new ToDo(taskName, done));
                        break;
                    case 3 :
                        taskList.add(new Deadline(taskName, done, taskDescriptions[2]));
                        break;
                    case 4 :
                        taskList.add(new Event(taskName, done, taskDescriptions[2], taskDescriptions[3]));
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks!");
        }
    }
    public static void writeFile() throws IOException {
        File f = new File("ip/src/main/data/tasks.txt");
        FileWriter writer = new FileWriter(f);
        String text = "";
        for (Task t : taskList) {
            text += t.storeData() + "\n";
        }
        writer.write(text);
        writer.close();
    }
    public static void checkEmptyTask(String taskName) throws EmptyTaskNameException {
        if (taskName.trim().isEmpty()) {
            throw new EmptyTaskNameException();
        }
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println(LINE + "\n     Got it. I've added this task:\n       " + newTask.toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.\n" + LINE);
    }

    public static void printTaskList() {
        System.out.println(LINE + "\n     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.printf("     %d.%s\n", i, taskList.get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    public static void printResponse(String response, int taskIndex) {
        System.out.printf("%s\n     %s\n       %s\n%s\n",
                LINE, response, taskList.get(taskIndex).toString(), LINE);
    }
}