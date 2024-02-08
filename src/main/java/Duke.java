import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Duke {
    private static final int TASKS_MAX = 100;
    private static final ArrayList<Task> tasks = new ArrayList<>(TASKS_MAX);
    private static int taskCount = 0;
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Main loop for the chatbot.
     * Waits for command line input and passes arguments to the handleInput method.
     * @param args The command line input.
     */
    public static void main(String[] args) {
        loadData();

        String logo = " _  _   __    ____  ____ \n" +
                "( \\/ ) /__\\  (  _ \\(  _ \\\n" +
                " \\  / /(__)\\  )   / )   /\n" +
                " (__)(__)(__)(_)\\_)(_)\\_)\n";

        System.out.println(logo);
        printDivider();
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                handleInput(scanner);
            } catch (IllegalArgumentException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            } catch (ExitProgramException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
                break;
            }
        }

    }

    /**
     * Method to print section dividers to the console.
     */
    private static void printDivider(){
        int length = 90;
        char divider = 0x2500 ;
        for (int i = 0; i < length; i++) {
            System.out.print(divider);
        }
        System.out.println();
    }

    /**
     * Solution below adapted from https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/
     * Takes command line input and edits the task list or prints to console as required.
     * @throws IllegalArgumentException if the command is not recognised.
     */
    private static void handleInput(Scanner scanner) {
        String input = scanner.nextLine();
        String[] wordArray = input.split(" ", 0);
        // Handle inputs
        if (input.equals("bye")) {
            throw new ExitProgramException("Fair winds to ye, me hearty! " +
                    "May the tide carry ye safely until our paths cross again.");
        } else if (input.equals("list")) {
            printDivider();
            System.out.println("Behold, yer roster of endeavors!");
            for (int i = 0; i < taskCount; i++) {
                String tempNum = Integer.toString(i + 1);
                System.out.println(tempNum + "." + tasks.get(i).toString());
            }
            printDivider();
        } else if (wordArray[0].equals("mark") || wordArray[0].equals("unmark") || wordArray[0].equals("delete")) {
            if (wordArray.length != 2) {
                throw new IllegalArgumentException("Blunder! Declare a task by number, matey!");
            } else {
                try {
                    int tempIndex = Integer.parseInt(wordArray[1]);
                    if (tempIndex > taskCount || tempIndex < 1) {
                        throw new IllegalArgumentException("Blunder! Ye only be havin' " + taskCount
                                + " tasks on the chart, matey!");
                    } else {
                        printDivider();
                        if (wordArray[0].equals("mark")) {
                            tasks.get(tempIndex - 1).markAsDone();
                            System.out.println("X marks the spot. I've crossed this task of yer list, me heartie!");
                            System.out.println(tasks.get(tempIndex - 1).toString());
                        } else if (wordArray[0].equals("unmark")) {
                            tasks.get(tempIndex - 1).markAsNotDone();
                            System.out.println("The winds be shiftin', " +
                                    "and I be lettin' this task sail with the breeze unmarked.");
                            System.out.println(tasks.get(tempIndex - 1).toString());
                        } else {
                            System.out.println("As ye command, this one has walked the plank:");
                            System.out.println("\t" + tasks.get(tempIndex - 1).toString());
                            tasks.remove(tempIndex - 1);
                            taskCount -= 1;
                            System.out.println("Only " + taskCount + " tasks remain, captain!");
                        }
                        printDivider();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Blunder! " +
                            "I be searchin' the seas but couldn't spy the task ye named, me heartie!");
                } finally {
                    saveTaskListToFile();
                }
            }
        } else {
            switch (wordArray[0]) {
                case "todo": {
                    if (wordArray.length < 2) {
                        throw new IllegalArgumentException("Blunder! Declare yer to-do as such: 'todo *', ye scurvy dog!");
                    }
                    String tempString = input.substring(5).trim();
                    printDivider();
                    tasks.add(new Todo(tempString));
                    taskCount += 1;
                    System.out.println("I've appended this to yer list:\n" + tasks.get(taskCount - 1).toString());
                    printDivider();
                    break;
                }
                case "deadline": {
                    String tempString = input.substring(9).trim();
                    String[] tempArray = tempString.split("/by", 0);
                    if (tempArray.length == 1) {
                        throw new IllegalArgumentException("Blunder! " +
                                "Declare yer deadline as such: 'deadline * /by *', ye scurvy dog!");
                    }
                    String description = tempArray[0].trim();
                    LocalDateTime by = parseDate(tempArray[1].trim());
                    printDivider();
                    tasks.add(new Deadline(description, by));
                    taskCount += 1;
                    System.out.println("I've appended this to yer list:\n" + tasks.get(taskCount - 1).toString());
                    printDivider();
                    break;
                }
                case "event": {
                    String tempString = input.substring(6).trim();
                    String[] tempArray = tempString.split("/from", 0);
                    if (tempArray.length == 1) {
                        throw new IllegalArgumentException("Blunder! " +
                                "Declare yer event as such: 'deadline * /from * /to *', ye scurvy dog!");
                    }
                    String description = tempArray[0].trim();
                    tempString = tempArray[1].trim();
                    tempArray = tempString.split("/to", 0);
                    if (tempArray.length == 1) {
                        throw new IllegalArgumentException("Blunder! " +
                                "Declare yer event as such: 'deadline * /from * /to *', ye scurvy dog!");
                    }
                    LocalDateTime from = parseDate(tempArray[0].trim());
                    LocalDateTime to = parseDate(tempArray[1].trim());
                    printDivider();
                    tasks.add(new Event(description, from, to));
                    taskCount += 1;
                    System.out.println("I've appended this to yer list:\n" + tasks.get(taskCount - 1).toString());
                    printDivider();
                    break;
                }
                default:
                    throw new IllegalArgumentException("Arrr, me apologies! I cannot fathom that.");

            }
            appendTaskToFile(tasks.get(taskCount - 1).getData());
        }
    }

    /**
     * Reads existing data from .txt file and populates task list.
     * Creates missing directory and file as required using relative filepath.
     */
    private static void loadData() {
        //String filepath = "./data/duke.txt";

        try(Scanner dataScanner = new Scanner(new File(FILE_PATH))) {
            int lineNum = 1;
            ArrayList<Integer> corruptedLines = new ArrayList<>();
            while (dataScanner.hasNextLine()) {
                try {
                    String line = dataScanner.nextLine();
                    String[] data = line.split("\\|", 0);
                    String taskType = data[0].trim();
                    String taskComplete = data[1].trim();
                    String taskDescription = data[2].trim();
                    switch (taskType) {
                        case "T":
                            //Check correct number of fields
                            if (data.length != 3) {
                                throw new IllegalArgumentException("Line " + lineNum + ": Todo does not have 3 data fields.");
                            }

                            Todo todo = new Todo(taskDescription);

                            //Check completion status is a string "1" or "0"
                            if (taskComplete.equals("0")) {
                                todo.markAsNotDone();
                            } else if (taskComplete.equals("1")) {
                                todo.markAsDone();
                            } else {
                                throw new IllegalArgumentException("Line " + lineNum + ": Task completion data is corrupted.");
                            }

                            tasks.add(todo);
                            taskCount += 1;
                            break;
                        case "D":
                            //Check correct number of fields
                            if (data.length != 4) {
                                throw new IllegalArgumentException("Line " + lineNum + ": Deadline does not have 4 data fields.");
                            }

                            LocalDateTime deadlineBy = parseDate(data[3].trim());
                            //TODO: Check if data is in yyyy-mm-dd format
                            Deadline deadline = new Deadline(taskDescription, deadlineBy);

                            //Check completion status is a string "1" or "0"
                            if (taskComplete.equals("0")) {
                                deadline.markAsNotDone();
                            } else if (taskComplete.equals("1")) {
                                deadline.markAsDone();
                            } else {
                                throw new IllegalArgumentException("Line " + lineNum + ": Task completion data is corrupted.");
                            }

                            tasks.add(deadline);
                            taskCount += 1;
                            break;
                        case "E":
                            //check correct number of fields
                            if (data.length != 5) {
                                throw new IllegalArgumentException("Line " + lineNum + ": Event does not have 5 data fields.");
                            }

                            LocalDateTime eventFrom = parseDate(data[3].trim());
                            LocalDateTime eventTo = parseDate(data[4].trim());
                            //TODO: Check if data is in yyyy-mm-dd format
                            Event event = new Event(taskDescription, eventFrom, eventTo);

                            //Check completion status is a string "1" or "0"
                            if (taskComplete.equals("0")) {
                                event.markAsNotDone();
                            } else if (taskComplete.equals("1")) {
                                event.markAsDone();
                            } else {
                                throw new IllegalArgumentException("Line " + lineNum + ": Task completion data is corrupted.");
                            }

                            tasks.add(event);
                            taskCount += 1;
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    printDivider();
                    corruptedLines.add(lineNum);
                    System.out.println(e.getMessage());
                    printDivider();
                } catch (ArrayIndexOutOfBoundsException e) {
                    printDivider();
                    corruptedLines.add(lineNum);
                    System.out.println("Line " + lineNum + ": " + e.getMessage());
                    printDivider();
                } finally {
                    lineNum += 1;
                }
            }
            if (corruptedLines.size() > 0) {
                removeCorruptedData(corruptedLines);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
            File file = new File(FILE_PATH);
            Path directoryPath = Paths.get(file.getParent());
            if (!Files.exists(directoryPath)) {
                try {
                    Files.createDirectories(directoryPath);
                    System.out.println("Created missing directory: " + directoryPath);
                } catch (IOException err) {
                    System.out.println("Error creating directory: " + directoryPath);
                    err.printStackTrace();
                }

            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    System.out.println("Created missing file: " + file);
                } catch (IOException err) {
                    System.out.println("Error creating file: " + file);
                    err.printStackTrace();
                }
            }
            printDivider();
        }
    }

    /**
     * Solution below adapted from https://www.baeldung.com/java-write-to-file
     * Helper function that appends the input string to the file at the FILE_PATH and starts a new line.
     * @param line The string to be written to the new line.
     */
    private static void appendTaskToFile(String line) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + FILE_PATH);
        }
    }

    /**
     * Helper function that overwrites the original file data using the updated ArrayList data.
     * Called after changes to the list are made by marking, unmarking and deleting tasks.
     */
    private static void saveTaskListToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                bw.write(task.getData());
                bw.newLine();
            }
        } catch (IOException e) {
                System.out.println("Error writing to file: " + FILE_PATH);
        }
    }

    /**
     * Helper function for loadData() to delete lines from the .txt data file by line number.
     * @param indexes List of line numbers (1-based indexing) to be deleted.
     */
    private static void removeCorruptedData(ArrayList<Integer> indexes) {
        ArrayList<String> lines = new ArrayList<>();
        Collections.sort(indexes);
        int offset = 0;
        try (Scanner dataScanner = new Scanner(new File(FILE_PATH))) {
            int lineNum = 1;
            while (dataScanner.hasNextLine()) {
                String line = dataScanner.nextLine();
                if (lineNum != indexes.get(offset)) {
                    lines.add(line);
                } else {
                    System.out.println("Removing entry: " + line);
                    if (offset < indexes.size() - 1) {
                        offset += 1;
                    }
                }
                lineNum += 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + FILE_PATH);
        }
    }

    /**
     * Helper function to convert date input into LocalDateTime to be passed to Task subclasses.
     * @param input Date as a String.
     * @return Returns LocalDateTime object.
     * @throws IllegalArgumentException for date String not in the correct format.
     */
    private static LocalDateTime parseDate(String input) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(input, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Blunder! " +
                    "The date format ye provided be as tangled as a ship's riggin'.\n" +
                    "Write yer dates in the format dd/MM/yyyy HHmm or dd/MM/yyyy");
        }
        return time;
    }

}

class ExitProgramException extends RuntimeException {
    public ExitProgramException(String message) {
        super(message);
    }
}