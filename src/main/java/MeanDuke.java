import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents a MEAN chatbot with task-tracking capabilities
 */
public class MeanDuke {

    //Text art to be used
    private static final String LOGO =
            " __       __  ________   ______   __    __          _______   __    __  __    __  ________\n"
                    + "/  \\     /  |/        | /      \\ /  \\  /  |        /       \\ /  |  /  |/  |  /  |/        |\n"
                    + "$$  \\   /$$ |$$$$$$$$/ /$$$$$$  |$$  \\ $$ |        $$$$$$$  |$$ |  $$ |$$ | /$$/ $$$$$$$$/\n"
                    + "$$$  \\ /$$$ |$$ |__    $$ |__$$ |$$$  \\$$ | ______ $$ |  $$ |$$ |  $$ |$$ |/$$/  $$ |__\n"
                    + "$$$$  /$$$$ |$$    |   $$    $$ |$$$$  $$ |/      |$$ |  $$ |$$ |  $$ |$$  $$<   $$    |\n"
                    + "$$ $$ $$/$$ |$$$$$/    $$$$$$$$ |$$ $$ $$ |$$$$$$/ $$ |  $$ |$$ |  $$ |$$$$$  \\  $$$$$/\n"
                    + "$$ |$$$/ $$ |$$ |_____ $$ |  $$ |$$ |$$$$ |        $$ |__$$ |$$ \\__$$ |$$ |$$  \\ $$ |_____\n"
                    + "$$ | $/  $$ |$$       |$$ |  $$ |$$ | $$$ |        $$    $$/ $$    $$/ $$ | $$  |$$       |\n"
                    + "$$/      $$/ $$$$$$$$/ $$/   $$/ $$/   $$/         $$$$$$$/   $$$$$$/  $$/   $$/ $$$$$$$$/\n";
    private static final String SPACER = "__________________________________________________________________________________";
    private static final String INTRO = LOGO + SPACER + "\n" + "What do you want this time?\n" + SPACER;
    private static final String OUTRO = SPACER + "\n" + "Finally you're finished, thought you would never stop yapping.\n" + SPACER;

    //Creates an empty task list
    static TaskList tasklist = new TaskList();

    public static void main(String[] args) {

        //Try to load Task List from hard disk. If missing or corrupted, create a new file
        File savedTaskList = new File("./data/MeanDuke.txt");
        try {
            load(savedTaskList);
            System.out.println("Successfully loaded save file.");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Missing or corrupted save file. Creating new tasklist");
            tasklist = new TaskList();
        }

        //Prints intro
        System.out.println(INTRO);

        //Reads each line of user input and perform respective actions
        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine();
        String output;

        while (!userInput.equals("end")) {  //Session terminates when user inputs "end"
            System.out.println(SPACER);
            String[] command = userInput.split(" ", 2);
            try {
                switch (command[0]) {
                case "add":
                    output = MeanDuke.add(userInput);
                    save(savedTaskList);
                    break;
                case "list":
                    output = tasklist.toString();
                    break;
                case "mark":
                    output = MeanDuke.mark(userInput);
                    save(savedTaskList);
                    break;
                case "unmark":
                    output = MeanDuke.unmark(userInput);
                    save(savedTaskList);
                    break;
                case "delete":
                    output = MeanDuke.delete(userInput);
                    save(savedTaskList);
                    break;
                default:
                    output = "What are you saying? Read the damn user manual, it was written for a reason";
                }
            } catch (MeanDukeException e) {
                output = e.getMessage();
            }
            System.out.println(output);
            System.out.println(SPACER);
            userInput = inputScanner.nextLine();
        }

        //Outro message and end of program
        System.out.println(OUTRO);
    }

    private static String add(String input) throws MeanDukeException {
        //Check what type of task is being added
        String[] split = input.split(" ", 3);
        try {
            switch (split[1]) {

            case "todo":
                try {
                    tasklist.add(new ToDo(split[2].strip()));
                    return "Added ToDo to list: " + split[2].strip();
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MeanDukeException("Usage: \"add todo <description>\"");
                }

            case "deadline":
                try {
                    String[] desc_by = split[2].split("/by ", 2);
                    String desc = desc_by[0].strip();
                    String by = desc_by[1].strip();
                    tasklist.add(new Deadline(desc, by));
                    return "Added Deadline to list: " + desc + " (by: " + by + ")";
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MeanDukeException("Usage: \"add deadline <description> /by <deadline>\"");
                }

            case "event":
                try {
                    String[] desc_fromTo = split[2].split("/from ", 2);
                    String[] from_to = desc_fromTo[1].split("/to", 2);
                    String desc = desc_fromTo[0].strip();
                    String from = from_to[0].strip();
                    String to = from_to[1].strip();
                    tasklist.add(new Event(desc, from, to));
                    return "Added Event to list: " + desc + " (" + from + " - " + to + ")";
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MeanDukeException("Usage: \"add event <description> /from <start> /to <end>\"");
                }

            default:
                throw new MeanDukeException("Usage: \"add <type> <description> ...\"");

            }
        } catch (
                ArrayIndexOutOfBoundsException e) { //Catch any issues with formatting which results in split not working
            throw new MeanDukeException("Usage: \"add <type> <description> ...\"");
        }
    }

    private static String mark(String input) throws MeanDukeException {
        String indexString = input.substring(4).strip(); //Remove "mark"
        try {
            int index = Integer.parseInt(indexString) - 1;
            if (tasklist.markDone(index)) {
                //Task successfully changed from not done to done
                return "Marked task: " + indexString + " as completed.\nCould you have taken any longer?";
            } else {
                //Task was already completed
                return "You have already marked task " + indexString + " as completed you goldfish...";
            }
        } catch (NumberFormatException e) {
            throw new MeanDukeException("Usage: \"mark <task_number>\"");
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + indexString);
        }
    }

    private static String unmark(String input) throws MeanDukeException {
        String indexString = input.substring(6).strip(); //Remove "unmark"
        try {
            int index = Integer.parseInt(indexString) - 1;
            if (tasklist.unmarkDone(index)) {
                //Task successfully changed from done to not done
                return "Marked task: " + indexString + " as not completed.\nWhy did you mark this in the first place?";
            } else {
                //Task was already not complete
                return "Task " + indexString + " is already not completed. Maybe you should start working on it.";
            }
        } catch (NumberFormatException e) {
            throw new MeanDukeException("Usage: \"unmark <task_number>\"");
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + indexString);
        }
    }

    private static String delete(String input) throws MeanDukeException {
        String indexString = input.substring(6).strip(); //Remove "delete"
        try {
            int index = Integer.parseInt(indexString) - 1;
            return "deleted task:\n  " + tasklist.delete(index);
        } catch (NumberFormatException e) {
            throw new MeanDukeException("Usage: \"delete <task_number>\"");
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + indexString);
        }
    }

    /**
     * Loads a saved TaskList from the given File
     *
     * @param saveFile The File containing the save data
     * @throws FileNotFoundException  if the File is not found
     * @throws NoSuchElementException if the save File is not in the expected format or is corrupted
     */
    private static void load(File saveFile) throws FileNotFoundException, NoSuchElementException {
        Scanner s = new Scanner(saveFile);
        while (s.hasNext()) {
            switch (s.nextLine()) {
            case "TODO":
                tasklist.add(new ToDo(s.nextLine(), parseSaveBoolean(s.nextLine())));
                break;
            case "DEADLINE":
                String deadlineDesc = s.nextLine();
                boolean deadlineisDone = parseSaveBoolean(s.nextLine());
                String[] dateTime = s.nextLine().split(";");
                tasklist.add(new Deadline(deadlineDesc, deadlineisDone,
                        LocalDate.parse(dateTime[0]),
                        dateTime.length == 1 ? null : LocalTime.parse(dateTime[1])));
                break;
            case "EVENT":
                String eventDesc = s.nextLine();
                boolean eventIsDone = parseSaveBoolean(s.nextLine());
                String[] dateTimeFrom = s.nextLine().split(";");
                String[] dateTimeTo = s.nextLine().split(";");
                tasklist.add(new Event(eventDesc, eventIsDone,
                        LocalDate.parse(dateTimeFrom[0]),
                        dateTimeFrom.length == 1 ? null : LocalTime.parse(dateTimeFrom[1]),
                        LocalDate.parse(dateTimeTo[0]),
                        dateTimeTo.length == 1 ? null : LocalTime.parse(dateTimeTo[1])));
                break;
            default:
                throw new NoSuchElementException(s.nextLine());
            }
        }
    }

    /**
     * Parses the given string into a boolean value.
     *
     * @param str The string to be parsed
     * @return true or false depending on the string
     * @throws java.util.InputMismatchException if string is not a valid boolean value
     */
    private static boolean parseSaveBoolean(String str) throws java.util.InputMismatchException {
        if (str.equals("true")) {
            return true;
        } else if (str.equals("false")) {
            return false;
        } else {
            throw new java.util.InputMismatchException();
        }
    }

    /**
     * Save the current TaskList into disk
     *
     * @param savedTaskList The File to save the data into
     */
    private static void save(File savedTaskList) {
        try {
            new File("./data").mkdir();
            FileWriter fw = new FileWriter(savedTaskList);
            fw.write(tasklist.saveString());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occurred during saving.");
            System.out.println(e);
        }
    }
}
