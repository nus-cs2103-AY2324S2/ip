
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String welcomeMessage = "    Lamball\n" +
            "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball, your helpful sheep!\n" +
            "     Whaaat can I do for you?\n" +
            "    ____________________________________________________________\n";
    String goodbyeMessage = "    ____________________________________________________________\n" +
            "     See you again!\n" +
            "    ____________________________________________________________\n";

    ArrayList<Task> tasks;
    File savedList;
    public Lamball() {
        tasks = new ArrayList<>();
    }

    public void greetingMessage() {
        System.out.println(this.welcomeMessage);
    }
    public void goodbyeMessage() {
        System.out.println(this.goodbyeMessage);
    }

    public void printList() {
        System.out.println(indent);
        System.out.println("    Here aaaaare the taaaasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString() + "");
        }
        System.out.println(indent);
    }

    private void mark(String[] parts, boolean isInit) throws LamballParseException {
        // If not numeric
        if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
            throw new LamballParseException(indent + "    Invalid number, baa.\n" + indent);
        }
        int idx = Integer.valueOf(parts[1]) - 1;
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        Task temp = tasks.get(idx);
        temp.mark();
        if (!isInit) {
            System.out.println(indent + "    I have maaarked the task as done:\n");
            System.out.println("        " + temp.toString() + "\n" + indent);
        }
    }

    private void unMark(String[] parts) throws LamballParseException {
        // If not numeric
        if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
            throw new LamballParseException(indent + "    Invalid number, baa.\n" + indent);
        }
        int idx = Integer.valueOf(parts[1]) - 1;
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        System.out.println(indent + "    I have maaarked the task as undone:\n" + indent);
        Task temp = tasks.get(idx);
        temp.unMark();
        System.out.println("        " + temp.toString() + "\n" + indent);
    }

    private void toDo(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your todo field is empty, baaaaka.\n" + indent);
        }
        Task temp = new ToDo(parts[1]);
        tasks.add(temp);
        if (!isInit) {
            System.out.println(indent + "    Added ToDo:\n        " + temp.toString() + "\n    Now you have " + tasks.size()
                    + " tasks in the list.\n" + indent);
        }
    }
    private void deadline(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your deadline field is empty, baaaaka.\n" + indent);
        }
        String[] furtherSplit = parts[1].split(" /", 2);
        if (furtherSplit.length < 2 || !furtherSplit[1].substring(0,3).equals("by ")) {
            throw new LamballParseException(indent + "    Deadline is in the wrong formaaaaaaat, baa. :(\n    Correct fo" +
                    "rmaaat is: deadline <name> /by <time>, baa.\n" + indent);
        }
        Task temp = new Deadline(furtherSplit[0], furtherSplit[1].replaceFirst("by ", ""));
        tasks.add(temp);
        if (!isInit) {
            System.out.println(indent + "    Added Deadline:\n        " + temp.toString() + "\n    Now you have " +
                    tasks.size() + " tasks in the list.\n" + indent);
        }
    }

    private void event(String[] parts, boolean isInit) throws LamballParseException {
        // Checks if empty string (nothing after command) or only whitespaces
        if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
            throw new LamballParseException(indent + "    Your event field is empty, baaaaka.\n" + indent);
        }
        String[] furtherSplit = parts[1].split(" /", 3);
        if (furtherSplit.length < 3 || !furtherSplit[1].substring(0,5).equals("from ") ||
                !furtherSplit[2].substring(0,3).equals("to ")) {
            throw new LamballParseException(indent + "    Event is in the wrong formaaaaaaat, baa. :(\n    Correct " +
                    "formaaat is: event <name> /from <time> /to <time>, baa.\n" + indent);
        }
        Task temp = new Event(furtherSplit[0], furtherSplit[1].replaceFirst("from ", ""),
                furtherSplit[2].replaceFirst("to ", ""));
        tasks.add(temp);
        if (!isInit) {
            System.out.println(indent + "    Added Event:\n        " + temp.toString() + "\n    Now you have "
                    + tasks.size() + " tasks in the list.\n" + indent);
        }
    }

    private void deleteFromList(String[] parts) throws LamballParseException {
        // If not numeric
        if (!parts[1].matches("-?\\d+")) {
            throw new LamballParseException(indent + "    Invalid number, baa.\n" + indent);
        }
        int idx = Integer.valueOf(parts[1]) - 1;
        // Checks if index is within range of list
        if (idx >= tasks.size() || idx < 0) {
            throw new LamballParseException(indent + "    Taaask index out of range, baa.\n" + indent);
        }
        System.out.println(indent + "    I have removed this taaask:");
        Task temp = tasks.remove(idx);
        System.out.println("        " + temp.toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + indent);
    }

    public boolean parse(String msg, boolean isInit) throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        if (!isInit) {
            System.out.println("\n    Lamball");
        }
        switch(parts[0]) {
            case "mark": {
                mark(parts, isInit);
                return true;
            }
            case "unmark": {
                unMark(parts);
                return true;
            }
            case "bye":
                goodbyeMessage();
                return false;
            case "list":
                printList();
                return true;
            case "todo": {
                toDo(parts, isInit);
                return true;
            }
            case "event": {
                event(parts, isInit);
                return true;
            }
            case "deadline": {
                deadline(parts, isInit);
                return true;
            }
            case "delete": {
                deleteFromList(parts);
                return true;
            }
            default:
                throw new LamballParseException(indent + "    Sorry, I don't understaaaaaand your commaaaaand, baa. :(\n" + indent);
        }
    }

    public void obtainSavedFile() {
        File folder = new File("src/main/java/data");
        if (!folder.exists()) {
            System.out.println("Folder does not exist. Creating folder...");
            folder.mkdirs();
        }
        savedList = new File("src/main/java/data/list.txt");
        try {
            // Try to create file
            if (savedList.createNewFile()) {
                System.out.println("List created successfully at: " + savedList.getAbsolutePath());
            } else {
                System.out.println("Seems like I haave helped you before, so no new list is needed!");
            }
            this.initializeListFromText();
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    private void initializeListFromText() throws FileNotFoundException {
        File tempFile = new File("src/main/java/data/tempfile.txt");
        try {
            // Delete and creates a new tempfile
            tempFile.delete();
            tempFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(savedList);
        System.out.println("Initializing saved file...");
        int count = 0;
        while(scanner.hasNext()) {
            try {
                // Parses every line of the saved file - if error, deletes line in the file and ignores command
                String currLine = scanner.nextLine();
                String[] parts = currLine.split(" \\| ", 2);
                // Means that it is not formatted correctly in the <0 or 1> | <command> format
                if (parts.length != 2 || !(Integer.valueOf(parts[0]) == 1 || Integer.valueOf(parts[0]) == 0)) {
                    throw new LamballParseException("Corrupt format, ignoring...");
                }
                this.parse(parts[1], true);
                count++;
                // Marks task if first character is 1. Else does not.
                if (Integer.valueOf(parts[0]) == 1) {
                    this.parse("mark " + count, true);
                }
                // If code reaches here, means that the line is valid - write to temp file
                writeToFile("src/main/java/data/tempfile.txt", currLine);


            } catch (LamballParseException e) {
                // Ignores line
                System.out.println("Corrupt format, ignoring...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
        System.out.println("Done!");
        savedList.delete();
        tempFile.renameTo(savedList);
    }


}