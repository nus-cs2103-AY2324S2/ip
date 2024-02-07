package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.nio.file.Paths;
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final String FILE_NAME = "duke.txt";
    private static final String FILE_PATH = Paths.get(".", FILE_NAME).toString();
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<duke.task.Task> list = loadFile();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HHmm");
//
//        System.out.println("\t\tHello, my name is Xilef.\n\t\tHow may I help you today??\n");
//
//        while (true) {
//            String str = scanner.nextLine();
//            String[] arr = str.split(" ");
//            if (str.equals("bye")) {
//                break;
//            } else if (arr[0].equals("list")) {
//                if (list.size() == 0) {
//                    System.out.println("\t\tThere is nothing on your agenda");
//                }
//                else {
//                    System.out.println("\t\tThese are the things on your agenda today");
//                    for (int i = 1; i < list.size() + 1; i++) {
//                        duke.task.Task t = list.get(i - 1);
//                        System.out.println("\t\t" + i + "." + t.toString());
//                    }
//                }
//                System.out.println("");
//            } else if (arr[0].equals("unmark")) {
//                duke.task.Task t = list.get(getIndex(arr));
//                t.unmarkDone();
//                System.out.println("\t\tReminder, you have not completed this task yet:\n\t\t  " + t.toString() + "\n");
//            } else if (arr[0].equals("mark")) {
//                duke.task.Task t = list.get(getIndex(arr));
//                t.markDone();
//                System.out.println("\t\tGreat job, you have accomplished this task:\n\t\t  " + t.toString() + "\n");
//            } else if (arr[0].equals("delete")) {
//                try {
//                    int n = getIndex(arr);
//                    if (list.size() <= n) {
//                        throw new duke.DukeException("There is nothing to be deleted");
//                    } else {
//                        duke.task.Task t = list.get(n);
//                        list.remove(t);
//                        System.out.println("\t\tRemoved the following task:\n\t\t  " + t.toString());
//                        if (list.size() <= 1) {
//                            System.out.println("\t\tYou now have " + list.size() + " task remaining");
//                        } else {
//                            System.out.println("\t\tYou now have " + list.size() + " tasks remaining");
//                        }
//                    }
//                } catch (duke.DukeException e){
//                    System.out.println("\t\t" + e.getMessage());
//                }
//            } else {
//                String s = getDescripition(arr);
//                String[] newArr = s.split(" /");
//                try {
//                    if (arr[0].equals("deadline")) {
//                        LocalDateTime deadline = null;
//                        if (newArr.length < 2) {
//                            throw new duke.DukeException("Incomplete deadline information");
//                        }
//                        try {
//                            deadline = LocalDateTime.parse(newArr[1].split("by")[1].trim(), formatter);
//
//                        } catch (DateTimeParseException e) {
//                            throw new duke.DukeException("Invalid date/time");
//                        }
//                        System.out.println("\t\tAdded a new task to the list!");
//                        duke.task.Deadline d = new duke.task.Deadline(newArr[0], deadline);
//                        list.add(d);
//                        System.out.println("\t\t  " + d.toString());
//                    } else if (arr[0].equals("todo")) {
//                        System.out.println("\t\tAdded a new task to the list!");
//                        duke.task.Todo td = new duke.task.Todo(newArr[0]);
//                        list.add(td);
//                        System.out.println("\t\t  " + td.toString());
//                    } else if (arr[0].equals("event")) {
//                        if (newArr.length < 3) {
//                            throw new duke.DukeException("Incomplete event information");
//                        }
//                        LocalDateTime from = null;
//                        LocalDateTime to = null;
//                        try {
//                            from = LocalDateTime.parse(newArr[1].split("from")[1].trim(), formatter);
//                            to = LocalDateTime.parse(newArr[2].split("to")[1].trim(), formatter);
//
//                        } catch (DateTimeParseException e) {
//                            throw new duke.DukeException("Invalid date/time");
//                        }
//                        System.out.println("\t\tAdded a new task to the list!");
//
//                        duke.task.Event e = new duke.task.Event(newArr[0], from, to);
//                        list.add(e);
//                        System.out.println("\t\t  " + e.toString());
//                    } else {
//                        throw new duke.DukeException("Invalid task type");
//                    }
//                    if (list.size() <= 1) {
//                        System.out.println("\t\tYou have " + list.size() + " too many task to do!!!" +
//                                "\n\t\tQuickly start working on them!!!\n");
//                    } else {
//                        System.out.println("\t\tYou have " + list.size() + " too many tasks to do!!!" +
//                                "\n\t\tQuickly start working on them!!!\n");
//                    }
//                } catch (duke.DukeException e) {
//                    System.out.println("\t\t" + e.getMessage());
//                }
//            }
//            saveToFile(list);
//        }
//        System.out.println("\t\tBye bye, see you next time!!!");
//    }
//    private static int getIndex(String[] arr) {
//        int index = Integer.parseInt(arr[1]);
//        return index - 1;
//    }
//
//    private static String getDescripition(String[] arr) {
//        StringBuilder s = new StringBuilder();
//        for (int i = 1; i < arr.length; i++) {
//            s.append(arr[i]).append(" ");
//        }
//        return s.toString();
//    }
//
//    private static void saveToFile(ArrayList<duke.task.Task> list) {
//        try (FileWriter writer = new FileWriter(FILE_PATH)) {
//            for (duke.task.Task t : list) {
//                writer.write(t.toStringForFile() + "\n");
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving file: " + e.getMessage());
//        }
//    }
//
//    private static ArrayList<duke.task.Task> loadFile() {
//        ArrayList<duke.task.Task> list = new ArrayList<>();
//        File file = new File(FILE_PATH);
//        try (Scanner scanner = new Scanner(file)) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                duke.task.Task task = createTaskFromLine(line);
//                if (task != null) {
//                    list.add(task);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + e.getMessage());
//        } catch (NoSuchElementException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//        return list;
//    }
//
//    private static duke.task.Task createTaskFromLine(String line) {
//        duke.task.Task t = null;
//        String[] parts = line.split("\\|");
//        String taskType = parts[0].trim();
//        String taskStatus = parts[1].trim();
//        String taskDescription = parts[2].trim();
//        switch (taskType) {
//            case "T":
//                t = new duke.task.Todo(taskDescription);
//                break;
//            case "D":
//                String taskBy = parts[3].trim();
//                LocalDateTime taskDeadline = LocalDateTime.parse((taskBy));
//                t = new duke.task.Deadline(taskDescription, taskDeadline);
//                break;
//            case "E":
//                LocalDateTime taskFrom = LocalDateTime.parse(parts[3].trim());
//                LocalDateTime taskTo = LocalDateTime.parse(parts[4].trim());
//                t = new duke.task.Event(taskDescription, taskTo,  taskFrom);
//                break;
//            default:
//                System.out.println("Invalid task type: " + taskType);
//        }
//        if (taskStatus.equals("1")) {
//            t.markDone();
//        }
//        return t;
//    }
}
