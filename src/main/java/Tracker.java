import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Tracker {
    public static boolean suppressMessages = false;
    /**
     * When a user adds a task, a response of PrependMessages + task is rendered
     * To add a message: Write the enum and then place a prepend and append message in brackets
     * e.g. ANGRY("You better get your ass up and", "otherwise Freddy will get you"),
     */
    enum CustomMessages {

        DEFAULT("Added: ", ""),
        SUSPENSE("Are you sure you want to: ", " Mewo looks at you, frightened"),
        HAPPY("I'm sure you can ", " I'll always be By Your Side"),
        DEMEANING("Don't tell me you can't ", " don't disappoint me");
        private final String prepend;
        private final String append;
        private static final Random RNG = new Random();
        CustomMessages(String prepend, String append) {
            this.prepend = prepend;
            this.append = append;
        }

        private static String getPrepend(CustomMessages msg) {
            return msg.prepend;
        }
        private static String getAppend(CustomMessages msg) {
            return msg.append;
        }

        public static String randomMsg(Task task) {
            CustomMessages[] pm = values();
            int msgIndex = RNG.nextInt(pm.length);
            return getPrepend(pm[msgIndex]) + task.brief() + getAppend(pm[msgIndex]);
        }
    }

    public static ArrayList<Task> tasks = new ArrayList<>(); // Globally accessible Tasks in memory.

    public static void addTask(Task task) {
        tasks.add(task);
        if (!suppressMessages) {
            System.out.println(CustomMessages.randomMsg(task));
            System.out.println(task);
        }
    }

    public static void removeTask(int i) {
        System.out.println("Exploooosion! now task " + tasks.remove(i).brief() + " has been Kazuma-ed out of existence");
        System.out.println("You now have " + tasks.size() + " tasks in your list");
    }


    /**
     * After each request by the user, erases previous contents of the file
     * and rebuilds the file based on task list
     * @param file the file to write tasks to
     * @throws IOException if file writing fails for any reason
     */
    public static void updateTasks(File file) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        // write to file
        int index = 1;
        for (Task t : tasks) {
            String task = index + " " + t.toString() + "\n";
            bufferedWriter.write(task);
            index++;
        }

        bufferedWriter.close();
    }
    public static void listTasks(BufferedReader bufferedReader) throws IOException {
        while (bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
    }

    /**
     * given a File and its contents, add it into the Tracker's taskList
     * @param file the file to read from
     * @throws IOException when a file can't be read from
     * @throws BadAppleException when the file contents are in the wrong format (i.e. non-command)
     */
    public static void parseTasks(File file) throws IOException, BadAppleException {
        // check the file to see what tasks are already available.
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int taskIndex = 1;
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            // deconstruct the line:
            ArrayList<String> args = new ArrayList<>(Arrays.asList(line.split(" ")));

            String command = "";
            String query = "";
            StringBuilder taskName;
            boolean status = args.get(2).equals("[X]");

            if (!(args.size() < 2)) {
                command = args.get(1);
            } else {
                return;
            }

            switch (command.toLowerCase()) {
                case "todo":
                    // the fourth token should be the task name for this command.
                    if (args.size() < 4) {
                        throw new BadAppleException("Todo Task in wrong format, " +
                                "should be <number> todo <status> <taskName>");
                    }
                    taskName = new StringBuilder();
                    for (int i = 3; i < args.size(); i++) {
                        taskName.append(args.get(i));
                    }

                    query = "todo " + taskName;
                    break;

                case "deadline":
                    if (args.size() < 6 || !args.contains("(by:")) {
                        throw new BadAppleException("Deadline in wrong format" +
                                "should be <number> 'deadline' <status> <description> '(by: ' <ByValue>");
                    }
                    taskName = new StringBuilder();
                    StringBuilder by = new StringBuilder();
                    int separator = args.indexOf("(by:");
                    for (int i = 3; i < separator; i++) {
                        taskName.append(args.get(i)).append(" ");
                    }
                    for (int i = separator + 1; i < args.size() - 1; i++) {
                        by.append(args.get(i)).append(" ");
                    }
                    query = "deadline " + taskName + "/by " + by;
                    break;
                case "event":
                    if (args.size() < 8 || !(args.contains("(from:") && args.contains("to:"))) {
                        throw new BadAppleException("Event in wrong format" +
                                "should be <no.> 'event' <status> <description> " +
                                "'(from: ' <fromValue> 'to: ' <toValue>");
                    }
                    taskName = new StringBuilder();
                    StringBuilder from = new StringBuilder();
                    StringBuilder to = new StringBuilder();
                    int fromSeparator = args.indexOf("(from:");
                    for (int i = 3; i < fromSeparator; i++) {
                        taskName.append(args.get(i)).append(" ");
                    }
                    int toSeparator = args.indexOf("to:");
                    for (int i = fromSeparator + 1; i < toSeparator; i++) {
                        from.append(args.get(i)).append(" ");
                    }
                    for (int i = toSeparator + 1; i < args.size() - 1; i++) {
                        to.append(args.get(i)).append(" ");
                    }
                    query = "event " + taskName + "/from " + from + "/to " + to;
                    break;

                default:
                    System.out.println("unrecognizable command detected");
                    throw new BadAppleException("The sun shined brighter when your files weren't corrupted");
            }

            // upon reconstructing the command, execute it.
            ProcessQuery(query, file);
            if (status) {
                // if this task is already complete, mark it.
                ProcessQuery("mark " + taskIndex, file);
            }
            taskIndex++;
        }
    }

    /**
     * Takes in a user query and performs the necessary reading and writing to a file on the drive.
     * The file should exist, and is by default handled by BadPingGuo
     * @param s the query the user would like to make
     * @param file the file to read or write from
     */
    public static void ProcessQuery(String s, File file) throws IOException {
        String[] tokens = s.split(" ");
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(tokens));

        switch (args.get(0).toLowerCase()) {
            case "list":
                listTasks(new BufferedReader(new FileReader(file)));
                return;
            case "mark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = tasks.get(taskIndex);
                    t.mark(true, taskIndex);
                } catch(NumberFormatException e) {
                    System.out.println("Usage: mark <taskNumber>");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Hey you don't have that task!");
                }
                break;
            case "unmark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = tasks.get(taskIndex);
                    t.mark(false, taskIndex);
                } catch(NumberFormatException e) {
                    System.out.println("Usage: mark <taskNumber>");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Calm down! You don't have THAT many tasks!");
                }
                break;
            case "todo":
                // it is possible to relegate exception handling to addTask
                // you must use fp and implement lazy evaluation
                try {
                    addTask(Todo.extractDetails(s));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "deadline":
                try {
                    addTask(Deadline.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "event":
                try {
                    addTask(Event.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "delete":
                if (tokens.length <= 1) {
                    System.out.println("Kel nuked, but he missed what task you wanted to remove!");
                    break;
                }
                int taskIndex;
                try {
                    taskIndex = parseInt(tokens[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Usage: delete <taskNumber>");
                    break;
                }
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    removeTask(taskIndex);
                } else {
                    System.out.println("welcome to BLACK SPACE, you keyed in a non-existent task!");
                }
                break;
            default:
                System.out.println("Whatcha sayin? scream 'help!' for list of my services");
                return;
        }
        updateTasks(file);
    }

}
