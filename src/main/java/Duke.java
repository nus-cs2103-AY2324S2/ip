import exceptions.DukeException;
import exceptions.TaskNotExistException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Duke{
    
    public enum Command {
        TODO, DEADLINE, EVENT, DELETE, MARK, UNMARK, LIST, BYE, UNKNOWN;

        public static Command fromString(String maybeCommand) {
            try {
                return Command.valueOf(maybeCommand.toUpperCase());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return UNKNOWN;
            }
        }
    }

    private static void formalities(String context) {
        if (context.equals("greet")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Wassup dawg, I'm Snoopy");
            System.out.println(" What can I do for you?");
            System.out.println("____________________________________________________________");
        } else if (context.equals("farewell")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Don't come back. jk!");
            System.out.println("____________________________________________________________");
        }
    }


    /**
     *
     *
     * @param userInput
     * @param todos
     * @param verbose helps to ensure whether we are preloading (includes the need to save the entries)
     * @throws RuntimeException
     */
    private static void processCommand(String userInput, ArrayList<Task> todos, Integer verbose) throws RuntimeException {

        String maybeCommand;
        String arr[];
        try {
            arr = userInput.split(" ", 2); // String in Array format. Useful: https://www.geeksforgeeks.org/split-string-java-examples/
            maybeCommand = arr[0];
        } catch (Exception e) {
            maybeCommand = null;
            return;
        }

        Command command = Command.fromString(maybeCommand);

        switch (command) {
        case BYE:
            formalities("farewell");
            System.exit(0);
            break; //TODO uncessary?
        case LIST:
            // list tasks
            System.out.println("____________________________________________________________");
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < todos.size(); i++) {
                Task currTask = todos.get(i);
                System.out.println((i + 1) + ". " + currTask.toString());
            }
            System.out.println("____________________________________________________________");
            break;
        case MARK:
            // mark task as done
            Integer index = Integer.valueOf(arr[1]) - 1;
            System.out.print(" Nice! I've marked this task as done:\n");
            Task currTask = todos.get(index);
            currTask.markAsDone();
            System.out.println(" " + currTask.toString());
            System.out.println("____________________________________________________________");
            if (verbose == 1) {
                updateRecords();
            }
            break;
        case UNMARK:
            // mark task as undone
            index = Integer.valueOf(arr[1]) - 1;
            System.out.println("____________________________________________________________");
            System.out.print(" OK, I've marked this task as not done yet:\n");
            currTask = todos.get(index);
            currTask.markAsUndone();
            System.out.println(" " + currTask.toString());
            System.out.println("____________________________________________________________");
            if (verbose == 1) {
                updateRecords();
            }
            break;
        case TODO:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! The description of a todo cannot be empty.");
            }
            System.out.println("____________________________________________________________");
            System.out.println("Got it. Added this task:");
            Todo todo = new Todo(arr[1]);
            todos.add(todo);
            System.out.println(todo.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
            if (verbose == 1) {
                updateRecords();
            }
            break;
        case DEADLINE:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! The description of a deadline cannot be empty.\nMake sure to add a deadline after the description with /by too!");
            }
            System.out.println("____________________________________________________________");
            System.out.println("Got it. Added this task:");
            String arguments[] = arr[1].split(" /by ");
            String description = arguments[0];
            String by = arguments[1];
            Deadline deadline = new Deadline(description, by);
            todos.add(deadline);
            System.out.println(deadline.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
            if (verbose == 1) {
                updateRecords();
            }
            break;
        case EVENT:
            if (arr.length == 4) {
                throw new DukeException(" Nuh uh! The description of an event cannot be empty.\nMake sure to add a from and to date after the description with /from and /to too!");
            }
            System.out.println("____________________________________________________________");
            System.out.println("Got it. Added this task:");
            // extraction of parameters
            String getDesc[] = arr[1].split(" /from ");
            String desc = getDesc[0];
            String getDates[] = getDesc[1].split(" /to ");
            String from = getDates[0];
            String to = getDates[1];

            //creating of event
            Event event = new Event(desc, from, to);
            todos.add(event);

            System.out.println(event.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
            if (verbose == 1) {
                updateRecords();
            }
            break;
        case DELETE:
            if (arr.length == 1) {
                throw new DukeException(" Nuh uh! Which task to delete? \nMake sure to add the task number!");
            }
            System.out.println("____________________________________________________________");
            Integer i = Integer.valueOf(arr[1]);
            Task task;
            try {
                task = todos.get(i - 1);
            } catch (Exception e) {
                throw new TaskNotExistException(Integer.toString(i));
            }
            System.out.println("Okay! I've fed this task to Woodstock, bye bye!:");
            System.out.println(task.toString());
            todos.remove(i - 1);
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
            if (verbose == 1) {
                updateRecords();
            }
            break;
        default:
            System.out.println("____________________________________________________________");
            System.out.println("Uh ah I don't understand ya " + command.toString() + "?");
            System.out.println("____________________________________________________________");
            break;
        }
    }

    private static void updateRecords() throws RuntimeException { //TODO: to continue, need to add delete task fn, mark, unmark, list
        String filePath = "./src/main/java/data/duke.txt";
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) { // true for append mode
            writer.write(todos.get(0).fileSavingString());
            for (int i = 1; i < todos.size(); i++) {
                writer.write("\n" + todos.get(i).fileSavingString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isCorrupt(File file) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task[] = scanner.nextLine().split(" \\| ");
                //TODO can add layer of check to make sure there is no new line.
                //System.out.println(Arrays.toString(task));
                String type = task[0];
                String done = task[1];
                String desc = task[2];
                String time1 = null;
                String time2 = null;
                switch (type) {
                case "T":
                    break;
                case "D":
                    time1 = task[3];
                    break;
                case "E":
                    time1 = task[3];
                    time2 = task[4];
                    break;
                }
            }
            scanner.close();
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private static boolean createFile(File file) throws Exception {
        boolean fileIsCreated = file.createNewFile(); //May throw IOException
        if (fileIsCreated) {
            return true;
        } else {
            throw new DukeException("Unable to create new file");
        }
    }
    private static void parseTodoFile(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        Integer taskCounter = 1;
        while (scanner.hasNextLine()) {
            String task[] = scanner.nextLine().split(" \\| ");
            String type = task[0];
            String done = task[1];
            String desc = task[2];
            String time1 = null;
            String time2 = null;
            switch (type) {
            case "D":
                time1 = task[3];
                processCommand("DEADLINE " + desc + " /by " + time1, todos, 0);
                break;
            case "E":
                time1 = task[3];
                time2 = task[4];
                processCommand("EVENT " + desc + " /from " + time1 + " /to " + time2, todos, 0);
                break;
            case "T":
                processCommand("TODO " + desc, todos, 0);
                break;
            }

            if (done.equals("1")) { // done
                processCommand("MARK " + taskCounter.toString(), todos, 0);
            }
            //parse each line
            taskCounter++;
        }
        scanner.close();
        System.out.println("Preload Success");
        System.out.println("____________________________________________________________");
    }
    private static void openStoredFile() throws Exception {

        //See if file already exists then parse it
        String filePath = "./src/main/java/data/duke.txt";
        File file = new File(filePath);

        try {
            if (!file.exists()) { //Create if don't exist
                createFile(file);
            } else { //file does exist
               if (isCorrupt(file)) {
                   //file corrupt
                   System.out.println("File Corrupt! Creating new file");
                   createFile(file); //TODO fix because currently cannot create new file with the same name.
               } else {
                   //parse current info and return.
                   parseTodoFile(file);
               }
            }
        } catch (Exception e) {
            System.out.println("Oh no!");
            System.out.println(e.getMessage());
        }


    }

    static ArrayList<Task> todos = new ArrayList<>();
    public static void main(String[] args) {
        formalities("greet");

        try {
            openStoredFile();
        } catch (Exception e) {
           e.getMessage();
            todos = null;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {

            //Read user input from Scanner.
            String s;
            try {
                s = scanner.nextLine(); // Use the same Scanner object
            } catch (NoSuchElementException e) {
                System.out.println("No input found. Exiting.");
                break; // Exit the loop if no input is found
            }

            //Parsing user input.
            try {
                processCommand(s, todos, 1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //continuing because exception has already been printed
                System.out.println("____________________________________________________________");
                continue;
            }

        }

    }
}
