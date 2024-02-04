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
        Ui.printIntro();

        //Reads each line of user input and perform respective actions
        Scanner inputScanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {  //Session terminates when user inputs "end"
            String userInput = inputScanner.nextLine();
            Ui.printSpacer();
            try {
                Command cmd = Parser.parseUserInput(userInput, tasklist);
                cmd.execute();
                isExit = cmd.isExitCommand();
            } catch (MeanDukeException e) {
                Ui.printError(e);
            }

            Ui.printSpacer();
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
