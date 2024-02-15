import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMO {
    static List<Task> taskLog = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(Constants.introPrint);
        System.out.println(Constants.tutorialPrint);
        loadData();
        receive();
    }

    static void loadData() {
        try {
            String projectPath = System.getProperty("user.dir");
            java.nio.file.Path filePath = java.nio.file.Paths.get(projectPath, "src",
                    "main", "resources", "data", "bmo_data.txt");
            boolean fileExists = java.nio.file.Files.exists(filePath);

            if (!fileExists) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("Info: Data file not found. Created a new file.");
            } else {
                String content = Files.readString(filePath);

                if (!content.isEmpty() && isValidContentFormat(content)) {
                    parseData(content);
                    System.out.println("Tasks loaded successfully.");
                } else {
                    System.out.println("Error: Data file content is empty or corrupted.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
        }
    }

    static void parseData(String content) {
        String[] lines = content.split("\n");
        Integer indexCounter = 0;

        for (String line : lines) {
            System.out.println(line);
            String[] info = line.split("\\|");

            String taskType = info[0].trim();
            boolean isDone = info[1].trim().equals("1");
            String taskDescription = info[2].trim();

            switch (taskType) {
                case "T":
                    addLog("todo " + taskDescription);
                    if (isDone) {
                        done(indexCounter.toString());
                    }
                    indexCounter++;
                    break;
                case "D":
                    String taskDueDate = info[3].trim();
                    addLog("deadline " + taskDescription + " /by " + taskDueDate);
                    if (isDone) {
                        done(indexCounter.toString());
                    }
                    indexCounter++;
                    break;
                case "E":
                    String taskStart = info[3].trim();
                    String taskEnd = info[4].trim();
                    addLog("event " + taskDescription + " /from " + taskStart
                            + " /to " + taskEnd);
                    if (isDone) {
                        done(indexCounter.toString());
                    }
                    indexCounter++;
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType);
                    break;
            }
        }
    }

    static boolean isValidContentFormat(String content) {
        // stretch goal to be implemented
        return true;
    }

    static void saveData() {
        try {
            StringBuilder tasksContent = new StringBuilder();
            for (Task task : taskLog) {
                tasksContent.append(task.toSaveData());
            }

            String projectPath = System.getProperty("user.dir");
            java.nio.file.Path filePath = java.nio.file.Paths.get(projectPath, "src",
                    "main", "resources", "data", "bmo_data.txt");

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, tasksContent.toString().getBytes());
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save data. " + e.getMessage());
        }
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
            } else if (input.startsWith("delete")) {
                deleteTask(input.substring(6).trim());
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
        saveData();
        System.out.println(Constants.byePrint);
        return;
    }

    static LocalDateTime formatDateTime(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            System.out.println(Constants.errorPrint.erroneousAdd());
            receive();
        }

        throw new IllegalArgumentException("Unrecognized date and time format: " + input);
    }

    static void addLog(String input) {
        // create string formats for each task type for format checking
        String deadlineFormat = "^deadline\\s+(\\w+(\\s+\\w+)*)\\s+/by\\s+(\\S+(\\s+\\w+|/)*)$";
        Pattern deadlinePattern = Pattern.compile(deadlineFormat);
        Matcher deadlineMatcher = deadlinePattern.matcher(input);

        String eventFormat = "^event\\s+(\\w+(\\s+\\w+)*)\\s+/from\\s+(\\S+(\\s+\\w+|/)*)\\s+/to\\s+(\\S+(\\s+\\w+|/)*)$";
        Pattern eventPattern = Pattern.compile(eventFormat);
        Matcher eventMatcher = eventPattern.matcher(input);

        String toDoFormat = "^todo\\s+(\\S+(\\s+\\w+)*)$";
        Pattern toDoPattern = Pattern.compile(toDoFormat);
        Matcher toDoMatcher = toDoPattern.matcher(input);

        Task newTask;
        if (deadlineMatcher.matches()) {
            String task = deadlineMatcher.group(1);
            String by = deadlineMatcher.group(3);

            LocalDateTime byDateTime = formatDateTime(by);

            newTask = new Deadlines(task, byDateTime);
        } else if (eventMatcher.matches()) {
            String task = eventMatcher.group(1);
            String start = eventMatcher.group(3);
            String end = eventMatcher.group(5);

            LocalDateTime startDateTime = formatDateTime(start);
            LocalDateTime endDateTime = formatDateTime(end);

            newTask = new Events(task, startDateTime, endDateTime);
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
            } else if (input.isBlank()) {
                System.out.println(Constants.errorPrint.erroneousAdd());
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

    static Boolean isIndexCorrect(String input) {
        if (input.isBlank() || !input.matches("\\d+")) {
            System.out.println(Constants.errorPrint.noInt());
            return false;
        }

        int index = Integer.parseInt((input));

        if (index > taskLog.size() || index <= 0) {
            System.out.println(Constants.errorPrint.outOfRange());
            return false;
        }

        return true;
    }

    static void done(String input) {
        if (!isIndexCorrect(input)) {
            return;
        }

        int index = Integer.parseInt((input));
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
        if (!isIndexCorrect(input)) {
            return;
        }

        int index = Integer.parseInt((input));
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

    static void deleteTask(String input) {
        if (!isIndexCorrect(input)) {
            return;
        }

        int index = Integer.parseInt((input));
        taskLog.remove(index - 1);

        String deleteTaskPrint = "-----------------------------------------\n"
                + "    No take backs! BMO deletion starting right now.\n"
                + "    Beep...task...boop..." + index + "...deleted...\n"
                + "    FOREVER!\n"
                + "-----------------------------------------\n";

        System.out.println(deleteTaskPrint);
        return;
    }
}
