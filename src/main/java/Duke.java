import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Duke {
    private static final String DIVIDER = "────────────────────────────────────────────────────────────";
    private static final String GREETING = "Hello! I'm Seiki\nHow may I assist you today?";
    private static final String FAREWELL = "Goodbye! If you ever need assistance in the future, don't hesitate to reach out. Take care!";
    private static final String LOGO = "  _____      _  _     _\n"
                                    + " / ____|    (_)| | _ (_)\n"
                                    + "| (___  ___  _ | |/ / _\n"
                                    + " \\___ \\/ _ \\| ||   / | |\n"
                                    + " ____) | __/| || | \\ | |\n"
                                    + "|_____/\\___||_||_|\\_\\|_|\n";
    protected TaskList taskList;
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    public Duke() {
        taskList = new TaskList();
    }

    private void run() throws DukeException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            ArrayList<String> inputArr = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
            String input = inputArr.remove(0);
            System.out.println(DIVIDER);
            Command command;
            try {
                command = Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("I'm sorry, I didn't quite understand that.");
            }

            switch (command) {
            case BYE:
                end();
            case LIST:
                printList(inputArr);
                break;
            case MARK:
                markTask(inputArr);
                break;
            case UNMARK:
                unmarkTask(inputArr);
                break;
            case TODO:
                addToDo(inputArr);
                break;
            case DEADLINE:
                addDeadline(inputArr);
                break;
            case EVENT:
                addEvent(inputArr);
                break;
            case DELETE:
                deleteTask(inputArr);
                break;
            default:
                throw new DukeException("I'm sorry, I didn't quite understand that.");
            }
            System.out.println(DIVIDER);
        }
    }

    private void start() throws DukeException, IOException {
        try {
            run();
        } catch (DukeException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(DIVIDER);
            start();
        }
    }

    private void end() {
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
        System.exit(0);
    }

    private void printList(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0) {
            taskList.printList();
        } else {
            throw new DukeException("Additional inputs have been detected.\n"
                    + "Please only type 'list' to view your list.");
        }
    }

    private void markTask(ArrayList<String> inputArr) throws DukeException {
        if (taskList.getTaskCount() == 0) {
            throw new DukeException("There are currently no tasks to be marked.");
        } else {
            if (inputArr.size() == 0) {
                throw new DukeException("Please enter a task number.");
            } else {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                    throw new DukeException("Please enter a valid task number.");
                } else {
                    Task task = taskList.getTaskByNumber(taskNumber);
                    task.markAsDone();
                    System.out.println("The following task has been marked.");
                    System.out.println("→ " + task);

                    writeToFile();
                }
            }
        }
    }

    private void unmarkTask(ArrayList<String> inputArr) throws DukeException {
        if (taskList.getTaskCount() == 0) {
            throw new DukeException("There are currently no tasks to be unmarked.");
        } else {
            if (inputArr.size() == 0) {
                throw new DukeException("Please enter a task number.");
            } else {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                    throw new DukeException("Please enter a valid task number.");
                } else {
                    Task task = taskList.getTaskByNumber(taskNumber);
                    task.markAsNotDone();
                    System.out.println("The following task has not been unmarked.");
                    System.out.println("→ " + task);

                    writeToFile();
                }
            }
        }
    }

    private void addToDo(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0) {
            throw new DukeException("The task title is missing.\n"
                    + "Please use the following format: todo [task title]");
        } else {
            String taskName = String.join(" ", inputArr);
            ToDo newTask = new ToDo(taskName);
            taskList.addTask(newTask);
            System.out.println("The following task has been added:");
            System.out.println("→ " + newTask);
            System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");

            appendToFile(newTask);
        }
    }

    private void addDeadline(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0
                || (inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()).size() == 0
                    && inputArr.subList(0, inputArr.indexOf("/by")).size() == 0)) {
            throw new DukeException("The task title and date time for the task is missing.\n"
                    + "Please use the following format: deadline [task title] /by [datetime]");
        } else if (!inputArr.contains("/by")
                || inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()).size() == 0) {
            throw new DukeException("The date time for the task is not found.\n"
                    + "Please use the following format: deadline [task title] /by [datetime]");
        } else if (inputArr.subList(0, inputArr.indexOf("/by")).size() == 0) {
            throw new DukeException("The task title is missing.\n"
                    + "Please use the following format: deadline [task title] /by [datetime]");
        } else {
            String taskName = String.join(" ", inputArr.subList(0, inputArr.indexOf("/by")));
            String dateTimeStr = String.join(" ", inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()));
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
                Deadline newTask = new Deadline(taskName, dateTime);
                taskList.addTask(newTask);
                System.out.println("The following task has been added:");
                System.out.println("→ " + newTask);
                System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");

                appendToFile(newTask);
            } catch (DateTimeParseException e) {
                throw new DukeException("The format of the date time is incorrect.\n"
                        + "Please use the following format: yyyy/MM/dd HHmm");
            }
        }
    }

    private void addEvent(ArrayList<String> inputArr) throws DukeException {
        if (inputArr.size() == 0) {
            throw new DukeException("The task title, start and end date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!inputArr.contains("/from") && !inputArr.contains("/to")) {
            throw new DukeException("The date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (inputArr.contains("/from")
                && inputArr.contains("/to")
                && (inputArr.subList(0, inputArr.indexOf("/from")).size() == 0
                    && inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()).size() == 0
                    && inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")).size() == 0)) {
            throw new DukeException("The task title, start and end date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!inputArr.contains("/to")
                || inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()).size() == 0) {
            throw new DukeException("The date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!inputArr.contains("/from")
                || inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")).size() == 0) {
            throw new DukeException("The date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (inputArr.subList(0, inputArr.indexOf("/from")).size() == 0) {
            throw new DukeException("The task title is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else {
            String taskName = String.join(" ", inputArr.subList(0, inputArr.indexOf("/from")));
            String startDateTimeStr = String.join(" ",
                    inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")));
            String endDateTimeStr = String.join(" ",
                    inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()));
            try {
                LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, DATE_TIME_FORMATTER);
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, DATE_TIME_FORMATTER);
                Event newTask = new Event(taskName, startDateTime, endDateTime);
                taskList.addTask(newTask);
                System.out.println("The following task has been added:");
                System.out.println("→ " + newTask);
                System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");

                appendToFile(newTask);
            } catch (DateTimeParseException e) {
                throw new DukeException("The format of the date time is incorrect.\n"
                        + "Please use the following format: yyyy/MM/dd HHmm");
            }
        }
    }

    private void deleteTask(ArrayList<String> inputArr) throws DukeException {
        if (taskList.getTaskCount() == 0) {
            throw new DukeException("There are currently no tasks to be deleted.");
        } else {
            if (inputArr.size() == 0) {
                throw new DukeException("Please enter a task number.");
            } else {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                if (taskNumber < 1 || taskNumber > taskList.getTaskCount()) {
                    throw new DukeException("Please enter a valid task number.");
                } else {
                    Task task = taskList.getTaskByNumber(taskNumber);
                    taskList.deleteTask(task);
                    System.out.println("The following task has been deleted.");
                    System.out.println("→ " + task);
                    System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");

                    writeToFile();
                }
            }
        }
    }

    private void load(String filePath) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            File parentFolder = file.getParentFile();

            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            } else {
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                String line = br.readLine();
                while (line != null) {
                    StringTokenizer st = new StringTokenizer(line, "|");
                    String taskType = st.nextToken().trim();
                    boolean isDone = "1".equals(st.nextToken().trim());
                    String taskTitle = st.nextToken().trim();
                    Task task;

                    switch (taskType) {
                    case "T":
                        task = new ToDo(taskTitle, isDone);
                        break;
                    case "D":
                        LocalDateTime dateTime = LocalDateTime.parse(st.nextToken().trim(), DATE_TIME_FORMATTER);
                        task = new Deadline(taskTitle, dateTime, isDone);
                        break;
                    case "E":
                        String[] dateTimeArr = st.nextToken().split("-");
                        LocalDateTime startDateTime = LocalDateTime.parse(dateTimeArr[0].trim(), DATE_TIME_FORMATTER);
                        LocalDateTime endDateTime = LocalDateTime.parse(dateTimeArr[1].trim(), DATE_TIME_FORMATTER);
                        task = new Event(taskTitle, startDateTime, endDateTime, isDone);
                        break;
                    default:
                        throw new DukeException("Invalid task type");
                    }
                    tasks.add(task);
                    line = br.readLine();
                }
            }
            taskList = new TaskList(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile() {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task : taskList.taskList) {
                fw.write(task.toFile());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void appendToFile(Task task) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt", true);
            fw.append(task.toFile());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        try {
            duke.load("./data/duke.txt");
            System.out.println(DIVIDER);
            System.out.println(LOGO);
            System.out.println(GREETING);
            System.out.println(DIVIDER);
            duke.start();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
