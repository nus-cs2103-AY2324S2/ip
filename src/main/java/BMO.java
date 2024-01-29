import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMO {

    static List<Task> taskLog = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Constants.introPrint);
        System.out.println(Constants.tutorialPrint);
        receive();
    }

    static void receive() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().toLowerCase().trim();

            if (input.startsWith("hi")) {
                greet();
            } else if (input.startsWith("bye")) {
                salute();
                break;
            } else if (input.startsWith("log")) {
                viewLog();
            } else if (input.startsWith("done")) {
                done(input.substring(4).trim());
            } else if (input.startsWith("redo")) {
                unDone(input.substring(4).trim());
            } else if (input.startsWith("add")) {
                addLog(input.substring(3).trim());
            } else {
                System.out.println(Constants.errorPrint.general());
            }
        }
        return;
    }

    static void greet() {
        System.out.println(Constants.hiPrint);
        return;
    }

    static void salute() {
        System.out.println(Constants.byePrint);
        return;
    }

    static void addLog(String input) {
        // create string formats for each task type for format checking
        String deadlineFormat = "^deadline\\s+(\\w+(\\s+\\w+)*)\\s+/by\\s+(\\S+(\\s+\\w+)*)$";
        Pattern deadlinePattern = Pattern.compile(deadlineFormat);
        Matcher deadlineMatcher = deadlinePattern.matcher(input);

        String eventFormat = "^event\\s+(\\w+(\\s+\\w+)*)\\s+/from\\s+(\\S+(\\s+\\w+)*)\\s+/to\\s+(\\S+(\\s+\\w+)*)$";
        Pattern eventPattern = Pattern.compile(eventFormat);
        Matcher eventMatcher = eventPattern.matcher(input);

        String toDoFormat = "^todo\\s+(\\S+(\\s+\\w+)*)$";
        Pattern toDoPattern = Pattern.compile(toDoFormat);
        Matcher toDoMatcher = toDoPattern.matcher(input);

        Task newTask;
        if (deadlineMatcher.matches()) {
            String task = deadlineMatcher.group(1);
            String by = deadlineMatcher.group(3);
            newTask = new Deadlines(task, by);
        } else if (eventMatcher.matches()) {
            String task = eventMatcher.group(1);
            String start = eventMatcher.group(3);
            String end = eventMatcher.group(5);
            newTask = new Events(task, start, end);
        } else if (toDoMatcher.matches()) {
            String task = toDoMatcher.group(1);
            newTask = new ToDos(task);
        } else {
            if (input.startsWith("deadline")) {
                System.out.println(Constants.errorPrint.deadline());
            } else if (input.startsWith("todo")) {
                System.out.println(Constants.errorPrint.todo());
            } else if (input.startsWith("event")) {
                System.out.println(Constants.errorPrint.event());
            } else if (input.isBlank()){
                System.out.println(Constants.errorPrint.emptyAdd());
            } else {
                System.out.println(Constants.errorPrint.general());
            }
            return;
        }

        taskLog.add(newTask);
        String addPrint = "-----------------------------------------\n"
                + "    added to log: " + newTask + "\n"
                + "    key in 'log' to view your current task log ^.^\n"
                + "-----------------------------------------\n";
        System.out.println(addPrint);
        return;
    }

    static void viewLog() {
        StringBuilder logPrint = new StringBuilder();
        if (taskLog.isEmpty()) {
            System.out.println(Constants.emptyLogPrint);
            return;
        }
        for (int i = 0; i < taskLog.size(); i++) {
            Task currTask = taskLog.get(i);
            logPrint.append(i + 1).append(". ").append(currTask.getStatusIcon())
                    .append(" ").append(currTask.toString())
                    .append("\n");
        }
        System.out.println(logPrint.toString());
        return;
    }

    static void done(String input) {
        if (input.isBlank() || !input.matches("\\d+")) {
            System.out.println(Constants.errorPrint.noInt());
            return;
        }

        int index = Integer.parseInt((input));

        if (index >= taskLog.size()) {
            System.out.println(Constants.errorPrint.outOfRange());
            return;
        }

        Task currTask = taskLog.get(index - 1);

        if (currTask.getStatus()) {
            System.out.println(Constants.errorPrint.alreadyDone());
            return;
        }

        currTask.setStatus(true);

        String donePrint = "-----------------------------------------\n"
                + "    Nice! Just a little more and you can play with BMO!\n"
                + "    Completed: " + taskLog.get(index - 1) + "\n"
                + "-----------------------------------------";
        System.out.println(donePrint);
        return;
    }

    static void unDone(String input) {
        if (input.isBlank() || !input.matches("\\d+")) {
            System.out.println(Constants.errorPrint.noInt());
            return;
        }

        int index = Integer.parseInt((input));

        if (index >= taskLog.size()) {
            System.out.println(Constants.errorPrint.outOfRange());
            return;
        }

        Task currTask = taskLog.get(index - 1);

        if (!currTask.getStatus()) {
            System.out.println(Constants.errorPrint.alreadyUnDone());
            return;
        }

        currTask.setStatus(false);

        String unDonePrint = "-----------------------------------------\n"
                + "    Aww come on hurry up and finish so we can play!\n"
                + "    Now I have to wait awhile longer >:(\n"
                + "    Incomplete again: " + taskLog.get(index - 1) + "\n"
                + "-----------------------------------------\n";
        System.out.println(unDonePrint);
        return;
    }
}
