import java.util.ArrayList;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
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
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/botYue.txt").run();
    }
}

/*
public class Duke {

    private static final Task[] store = new Task[100];
    private static int count = 0;
    private static final String DATA_FILE_PATH = "./data/botYue.txt";

    public static void main(String[] args) {
        dialogue();
        loadTasks();


        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();

            try {
                check(inputs);
                saveTasks();

            } catch (DukeException e) {
                System.out.println("   ____________________________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("   ____________________________________________________________");
            }

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm BotYue");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.startsWith("list")) {
            list();

        } else if (input.startsWith("mark")) {
            mark(input);

        } else if (input.startsWith("unmark")) {
            unmark(input);

        } else if (input.startsWith("todo")) {
            add(TaskType.TODO, input.substring(4).trim());

        } else if (input.startsWith("deadline")) {
            add(TaskType.DEADLINE, input.substring(8).trim());

        } else if (input.startsWith("event")) {
            add(TaskType.EVENT, input.substring(5).trim());

        } else if (input.startsWith("delete")) {
            deleteTask(input.substring(6).trim());

    } else {
            throw new DukeException("OOPS!!! I don't know what that means. Can you make it clear?");

        }
    }

    public static void add(Task task) {
        store[count++] = task;

        System.out.println("   ____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + count + " tasks in the list.");
        System.out.println("   ____________________________________________________________");
    }

   public static void add(TaskType type, String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + type + " task cannot be empty!");
        }

        Task task;
        switch (type) {
            case TODO:
                task = new TodoTask(description);
                break;

            case DEADLINE:
                task = new DeadlineTask(description);
                break;

            case EVENT:
                task = new EventTask(description);
                break;

            default:
                throw new DukeException("OOPS!!! Unsupported task type.");
        }

        add(task);
    }


    public static void deleteTask(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num);

            if (index > 0 && index <= count) {
                System.out.println("   ____________________________________________________________");
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + store[index - 1]);

                for (int i = index - 1; i < count - 1; i++) {
                    store[i] = store[i + 1];
                }

                store[count - 1] = null;
                count--;

                System.out.println("    Now you have " + count + " tasks in the list.");
                System.out.println("   ____________________________________________________________");

            } else {
                throw new DukeException("OOPS!!! Task index is out of range.");
            }

        } catch (NumberFormatException e) {

            throw new DukeException("OOPS!!! Please enter a valid task index to delete.");
        }
    }



    public static void list() {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }

        System.out.println("   ____________________________________________________________");
    }

    public static void mark(String input) {
        int index = getIndex(input);

        if (checkValid(index)) {
            store[index - 1].markDone();

            System.out.println("   ____________________________________________________________");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    public static void unmark(String input) {
        int index = getIndex(input);
        if (checkValid(index)) {
            store[index - 1].markNotDone();
            System.out.println("   ____________________________________________________________");
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + store[index - 1]);
            System.out.println("   ____________________________________________________________");
        }
    }

    private static int getIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static boolean checkValid(int index) {
        return index > 0 && index <= count;
    }


    public static void loadTasks() {
        try {
            File file = new File(DATA_FILE_PATH);

            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");

                    if (parts.length >= 3) {
                        Task task;

                        switch (parts[0]) {
                            case "T":
                                task = new TodoTask(parts[2]);
                                break;
                            case "D":
                                task = new DeadlineTaskLoad(parts[2], parts[3]);
                                break;
                            case "E":
                                task = new EventTaskLoad(parts[2], parts[3]);
                                break;
                            default:
                                continue;
                        }
                        if (parts[1].equals("1")) {
                            task.markDone();
                        }
                        add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public static void saveTasks() {
        try {
            File file = new File(DATA_FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < count; i++) {
                Task task = store[i];
                String taskType;

                if (task instanceof TodoTask) {
                    taskType = "T";
                } else if (task instanceof DeadlineTask) {
                    taskType = "D";
                } else if (task instanceof DeadlineTaskLoad) {
                    taskType = "D";
                } else if (task instanceof EventTask) {
                    taskType = "E";
                } else if (task instanceof EventTaskLoad) {
                    taskType = "E";
                } else {
                    continue;
                }
                writer.write(taskType + " | " + (task.marked ? "1" : "0") + " | " + task.getTask());
                if (task instanceof DeadlineTask) {
                    writer.write(" | " + ((DeadlineTask) task).getDateTime());

                } else if (task instanceof EventTask) {
                    writer.write(" | " + ((EventTask) task).getDateTime());

                } else if (task instanceof EventTaskLoad) {
                    writer.write(" | " + ((EventTaskLoad) task).getTime());

                } else if (task instanceof DeadlineTaskLoad) {
                    writer.write(" | " + ((DeadlineTaskLoad) task).getBy());
                }


                writer.write(System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}*/
