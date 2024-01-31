import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }

    private static final String FILE_PATH = "./data/duke.txt";

    private static Task readTask(String taskEntry) {
        String[] fields = taskEntry.split(" \\| ", 5);

        Task task = null;

        switch (fields[0]) {
        case "T":
            task = new Todo(fields[2]);

            if (fields[1].equals("1")) {
                task.changeMark("MARK");
            }
            break;

        case "D":
            LocalDateTime dueDateTime = LocalDateTime.parse(fields[3]);
            task = new Deadline(fields[2], dueDateTime);

            if (fields[1].equals("1")) {
                task.changeMark("MARK");
            }
            break;

        case "E":
            LocalDateTime fromDateTime = LocalDateTime.parse(fields[3]);
            LocalDateTime toDateTime = LocalDateTime.parse(fields[4]);
            task = new Event(fields[2], fromDateTime, toDateTime);

            if (fields[1].equals("1")) {
                task.changeMark("MARK");
            }
            break;
        }
        return task;
    }

    private static ArrayList<Task> readTasksFile() throws TasksFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(Duke.FILE_PATH);

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Task task = readTask(sc.nextLine());
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            createTaskFile();
        }
        return tasks;
    }

    private static void saveTasksFile(ArrayList<Task> tasks) throws TasksFileException {
        try {
            FileWriter fw = new FileWriter(Duke.FILE_PATH);
            for (Task task : tasks) {
                String formattedTask = task.formatTask();
                fw.write(formattedTask + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }

    private static void createTaskFile() throws TasksFileException {
        File tasksFile = new File(Duke.FILE_PATH);
        File dataDirectory = tasksFile.getParentFile();
        dataDirectory.mkdirs();
        try {
            tasksFile.createNewFile();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }

    public static void main(String[] args) {
        String horizontalLine = "__________________________________________________________________\n";

        String greeting = horizontalLine + "Hello! I'm KwunTalk!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(greeting);

        String goodbye = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;

        ArrayList<Task> tasks = null;

        try {
            tasks = readTasksFile();

        } catch (TasksFileException e) {
            System.out.println(horizontalLine + e + horizontalLine);
            return;
        }

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine(); // Read user input

            // Handle commands
            String[] parts = input.split(" ", 2);

            try {
                String command = parts[0].toUpperCase();

                CommandType cmdType = null;

                try {
                    cmdType = CommandType.valueOf(command);

                } catch (IllegalArgumentException e) {
                    throw new InvalidCommandException(command);
                }

                boolean shouldBreak = false;

                switch (cmdType) {

                case BYE:
                    System.out.println(goodbye);
                    shouldBreak = true;

                    try {
                        saveTasksFile(tasks);
                    } catch (TasksFileException e) {
                        System.out.println(horizontalLine + e + horizontalLine);
                    }
                    break;

                case LIST:
                    System.out.println(horizontalLine + "Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                    System.out.println(horizontalLine);
                    break;

                case DELETE:
                    try {
                        int taskId = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.get(taskId);
                        tasks.remove(taskId);
                        String taskCounter = String.format("Now you have %s tasks in the list.\n",
                                tasks.size());
                        System.out.println(horizontalLine +
                                "OK. I've deleted this task:\n" +
                                task + "\n" + taskCounter +
                                horizontalLine);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);

                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskFoundException(parts[1]);
                    }
                    break;

                case MARK:
                    try {
                        int taskId = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.get(taskId);
                        task.changeMark(command);
                        System.out.println(horizontalLine + "Nice! I've marked this task as done:\n" +
                                task + "\n" + horizontalLine);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);

                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskFoundException(parts[1]);
                    }
                    break;

                case UNMARK:
                    try {
                        int taskId = Integer.parseInt(parts[1]) - 1;
                        Task task = tasks.get(taskId);
                        task.changeMark(command);
                        System.out.println(horizontalLine + "OK, I've marked this task as not done yet:\n" +
                                task + "\n" + horizontalLine);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);

                    } catch (IndexOutOfBoundsException e) {
                        throw new NoTaskFoundException(parts[1]);
                    }
                    break;

                case TODO:
                    try {
                        Task newTask = new Todo(parts[1]);
                        tasks.add(newTask);
                        String taskCounter = String.format("Now you have %s tasks in the list.\n", tasks.size());
                        System.out.println(horizontalLine
                                + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                                + horizontalLine);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);
                    }
                    break;

                case DEADLINE:
                    try {
                        String[] splitDate = parts[1].split(" /by ", 2);

                        LocalDateTime dueDateTime;

                        try {
                            dueDateTime = LocalDateTime.parse(splitDate[1],
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                        } catch (DateTimeParseException e) {
                            throw new InvalidDateTimeFormatException(command);
                        }

                        Task newTask = new Deadline(splitDate[0], dueDateTime);
                        tasks.add(newTask);
                        String taskCounter = String.format("Now you have %s tasks in the list.\n", tasks.size());
                        System.out.println(horizontalLine
                                + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                                + horizontalLine);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);
                    }
                    break;

                case EVENT:
                    try {
                        String[] splitTaskName = parts[1].split(" /from ", 2);
                        String[] splitFromToDates = splitTaskName[1].split(" /to ", 2);

                        LocalDateTime fromDateTime;
                        LocalDateTime toDateTime;

                        try {
                            fromDateTime = LocalDateTime.parse(splitFromToDates[0],
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                            toDateTime = LocalDateTime.parse(splitFromToDates[1],
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                        } catch (DateTimeParseException e) {
                            throw new InvalidDateTimeFormatException(command);
                        }

                        Task newTask = new Event(splitTaskName[0], fromDateTime, toDateTime);
                        tasks.add(newTask);
                        String taskCounter = String.format("Now you have %s tasks in the list.\n", tasks.size());
                        System.out.println(horizontalLine
                                + "Got it. I've added this task:\n" + newTask + "\n" + taskCounter
                                + horizontalLine);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(command);
                    }
                    break;

                default:
                    throw new InvalidCommandException(command);
                }

                if (shouldBreak) {
                    break;
                }

            } catch (InvalidArgumentException e) { // Handle missing argument
                System.out.println(horizontalLine + e + horizontalLine);

            } catch (NoTaskFoundException e) { // Handle unknown task number
                System.out.println(horizontalLine + e + horizontalLine);

            } catch (InvalidCommandException e) { // Handle invalid input error
                System.out.println(horizontalLine + e + horizontalLine);

            } catch (InvalidDateTimeFormatException e) {
                System.out.println(horizontalLine + e + horizontalLine);

            }
        }
        sc.close();
    }
}
