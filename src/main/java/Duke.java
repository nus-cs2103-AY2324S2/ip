import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "    ___________________________________________________________\n";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> listOfTasks = new ArrayList<>();
        boolean hasEnded = false;
        String botName = "Yube";
        File file = new File("./yube.txt");
        file.createNewFile();

        greet(botName);
        while (!(hasEnded)) {
            try {
                String input = reader.readLine();
                if (input.equals("bye")) {
                    bye();
                    writeArrayListToFile(listOfTasks, "./yube.txt");
                    hasEnded = true;
                } else if (input.equals("list")) {
                    printList(listOfTasks);
                } else if (input.contains("mark")) {
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]);
                    if (input.contains("unmark")) {
                        unmark(listOfTasks.get(index - 1));
                    } else {
                        mark(listOfTasks.get(index - 1));
                    }

                } else if (input.contains("todo")) {
                    Task newTask = new Todo(input.substring(5));
                    listOfTasks.add(newTask);
                    repeatFunction(newTask, listOfTasks);
                } else if (input.contains("deadline")) {
                    String[] parts = input.substring(9).split(" /");
                    Task newTask = new Deadline(parts[0], parts[1].substring(3));
                    listOfTasks.add(newTask);
                    repeatFunction(newTask, listOfTasks);
                } else if (input.contains("event")) {
                    String[] parts = input.substring(6).split(" /");
                    Task newTask = new Event(parts[0], parts[1].substring(5), parts[2].substring(3));
                    listOfTasks.add(newTask);
                    repeatFunction(newTask, listOfTasks);
                } else if (input.contains("delete")) {
                    String[] parts = input.split(" ");
                    int deleteIndex = Integer.parseInt(parts[1]) - 1;
                    Task deletedTask = listOfTasks.get(deleteIndex);
                    listOfTasks.remove(deleteIndex);
                    deleteTask(deletedTask, listOfTasks);
                } else {
                    throw new DukeException("Unable to read input");
                }
            } catch (DukeException e) {
                System.out.print(LINE);
                System.out.println("     " + e);
                System.out.println(LINE);
            }
        }

    }

    /**
     * Displays a greeting message.
     * 
     * @param botName Name of the bot.
     */
    public static void greet(String botName) {
        System.out.println(String.format(
                "%s     Hello! I'm %s \n     What can I do for you? \n%s", LINE, botName, LINE));
    }

    /**
     * Adds text into fileName
     * 
     * @param list     List of Task
     * @param fileName Filename
     */
    public static void writeArrayListToFile(ArrayList<Task> list, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task element : list) {
                writer.write(element.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a task as done.
     * 
     * @param task
     */
    public static void mark(Task task) {
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done:");
        task.setDone();
        System.out.println("       " + task.toString());
        System.out.println(LINE);
    }

    /**
     * Displays a message of the deleted task and the number of task in the list
     * 
     * @param task
     * @param listOfTasks
     */
    public static void deleteTask(Task task, ArrayList<? extends Task> listOfTasks) {
        System.out.print(LINE);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        int len = listOfTasks.size();
        System.out.println(String.format("     Now you have %d tasks in the list.", len));
        System.out.println(LINE);
    }

    /**
     * Marks a task as not done.
     * 
     * @param task
     */
    public static void unmark(Task task) {
        System.out.println(LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        task.setNotDone();
        System.out.println("       " + task.toString());
        System.out.println(LINE);
    }

    /**
     * Displays a farewell message.
     */
    public static void bye() {
        System.out.println(String.format(
                "%s     Bye. Hope to see you again soon! \n%s", LINE, LINE));
    }

    /**
     * Displays a repeated message of the input by the user and number of task in
     * list.
     * 
     * @param task        Task input
     * @param listOfTasks List of all tasks
     */
    public static void repeatFunction(Task task, ArrayList<? extends Task> listOfTasks) {
        System.out.print(LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        int len = listOfTasks.size();
        System.out.println(String.format("     Now you have %d tasks in the list.", len));
        System.out.println(LINE);
    }

    /**
     * Displays the list of Strings.
     * 
     * @param listOfStrings list of Strings.
     */
    public static void printList(ArrayList<? extends Task> listOfTasks) {
        StringBuilder finalString = new StringBuilder();
        finalString.append(LINE);
        finalString.append("     Here are the tasks in your list:\n");
        int counter = 1;
        for (Task c : listOfTasks) {
            finalString.append(String.format("     %d. %s\n", counter, c));
            counter++;
        }
        finalString.append(LINE);
        System.out.println(finalString.toString());
    }
}
