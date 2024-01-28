import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMO {

    static List<Task> taskLog = new ArrayList<>();
    static String errorPrint = "-----------------------------------------\n"
            + "    BMO don't understand ;;;\n"
            + "    You could have a formatting error\n    or typo!\n"
            + "    You can refer to the command formats\n    that appear when you switch me on. ^^\n"
            + "-----------------------------------------\n";
    static String tutorialPrint = "Command BMO with these keywords!\n"
                            + "0. hi [greet BMO]\n"
                            + "1. bye [shut BMO down]\n"
                            + "2. log [view task log]\n"
                            + "3. add todo <task> [add todo task]\n"
                            + "4. add deadline <task> /by <date> [add deadline]\n"
                            + "5. add event <event> /from <start> /to <end> [add event]\n"
                            + "6. done <task number> [check task as done]\n"
                            + "7. redo <task number> [uncheck task]\n"
                            + "-----------------------------------------\n";

    public static void main(String[] args) {
        intro();
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
            } else if (input.startsWith("done") && input.length() > 5) {
                int index = Integer.parseInt(input.substring(5));
                done(index);
            } else if (input.startsWith("redo") && input.length() > 5) {
                int index = Integer.parseInt(input.substring(5));
                unDone(index);
            } else if (input.startsWith("add") && input.length() > 4){
                addLog(input.substring(4));
            } else {
                System.out.println(errorPrint);
            }
        }
        return;
    }

    static void intro() {
        String introPrint = "-----------------------------------------\n"
                    + "    BMO chop!\n    Do you want to play video games?\n"
                    + "-----------------------------------------\n";
        System.out.println(introPrint);
        System.out.println(tutorialPrint);
    }

    static void greet() {
        String hiPrint = "-----------------------------------------\n"
                + "    Good day! What can BMO help you with?\n"
                + "-----------------------------------------\n";
        System.out.println(hiPrint);
        return;
    }

    static void salute() {
        String byePrint = "-----------------------------------------\n"
                    + "    Beep boop BMO shutting down...\n"
                    + "-----------------------------------------\n";
        System.out.println(byePrint);
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
            System.out.print(errorPrint);
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
            String emptyLogPrint = "-----------------------------------------\n"
                            + "    Wow! Your log is actually empty.\n"
                            + "    Let's play mario kart right now!!\n"
                            + "-----------------------------------------\n";
            System.out.println(emptyLogPrint);
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

    static void done(int index) {
        Task currTask = taskLog.get(index - 1);
        currTask.setStatus(true);
        String donePrint = "-----------------------------------------\n"
                + "    Nice! Just a little more and you can play with BMO!\n"
                + "    Completed: " + taskLog.get(index - 1) + "\n"
                + "-----------------------------------------";
        System.out.println(donePrint);
        return;
    }

    static void unDone(int index) {
        Task currTask = taskLog.get(index - 1);
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
