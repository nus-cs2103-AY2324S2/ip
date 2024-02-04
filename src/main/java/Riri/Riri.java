package Riri;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Riri {
    public static void main(String[] args) throws RiriException {
        introduction("Riri");
        String filePath = "./data/riri.txt";

        Scanner sc = new Scanner(System.in);
        MyList mylist = new MyList();
        try {
            ArrayList<String> contents = readFileContents();
            // If the file exists and has content
            for (int i = 0; i < contents.size(); i++) {
                mylist.loadTask(parser(contents.get(i)));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            chat(mylist);
        } catch (RiriException exception) {
            System.out.println(exception.getMessage());
        }
        try {
            System.out.println("Saving data...");
            writeToFile(mylist.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------");
    }
    /**
     * Reads the contents of the previously saved file that contains the tasks previously added by the user.
     * @return string of tasks previously added by user
     * */
    private static ArrayList<String> readFileContents() throws IOException {
        ArrayList<String> contents = new ArrayList<>();

        Path dirPath = Paths.get("data");
        // If data directory does not exist return an empty string
        if (!Files.exists(dirPath)) {
            return contents;
        }
        String fileName = "riri.txt";
        Path filePath = dirPath.resolve(fileName);
        // If file does not exist return an empty string
        if (!Files.exists(filePath)) {
            return contents;
        }

        // Read the contents of the file
        Scanner sc = new Scanner(filePath); // create a Scanner using the File as the source
        while (sc.hasNext()) {
            contents.add(sc.nextLine());
        }
        return contents;
    }
    /**
     * Function writes the tasks the user has created to a file.
     * @param textToAdd the tasks to add to the to-do list
     * */
    private static void writeToFile(String textToAdd) throws IOException {
        String dirName = "data";
        // Create data directory if it does not exist
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        // Create file if it does not exist
        String fileName = "riri.txt";
        Path filePath = dirPath.resolve(fileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        // Write to proper location
        FileWriter fw = new FileWriter(filePath.toFile());
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Introduces our chatbot and prompts the user to start asking questions
     * @param name is the name given to our chatbot
     * */
    private static void introduction(String name) {
        System.out.println("--------------------------------------------------");
        System.out.println("What's up. I'm " + name + ".");
        System.out.println("I'm about to blow your world.");
        System.out.println("--------------------------------------------------");
    }
    /**
     * This method handles the logic for chatting with the user
     * @param mylist the task list that stores all the tasks created by the user
     * */
    private static void chat(MyList mylist) throws RiriException {
        boolean isOn = true;
        Scanner sc = new Scanner(System.in);

        while (isOn) {
            String command = sc.nextLine();
            if (command.matches("bye")) {
                isOn = false;
                break;
            } else if (command.matches("list")) {
                mylist.returnList();
                continue;
            } else if (command.matches("\\bmark\\b.*")) {
                String[] words = command.split("\\s+");
                mylist.mark(Integer.parseInt(words[1]));
                continue;
            } else if (command.matches("\\bunmark\\b.*")) {
                String[] words = command.split("\\s+");
                mylist.unmark(Integer.parseInt(words[1]));
                continue;
            } else if (command.matches("\\bdeadline\\b.*")) {
                String[] words = command.split("/by");
                mylist.addTask(new Deadline(words[0].trim(), words[1].trim()));
                System.out.println("Added deadline.");
                continue;
            } else if (command.matches("\\bevent\\b.*")) {
                String[] words = command.split("/from+");
                String[] from = words[1].split("/to");
                mylist.addTask(new Event(words[0].trim(), from[0].trim(), from[1].trim()));
                System.out.println("Added event.");
                continue;
            } else if (command.matches("\\btodo\\b.*")) {
                String[] words = command.split("todo");
                if (words[1] == "") {
                    throw new RiriException("You are adding nothing to your list");
                }
                mylist.addTask(new Todo(words[1].trim()));
                System.out.println("Added todo.");
                continue;
            } else if (command.matches("\\bdelete\\b.*")) {
                String[] words = command.split("\\s+");
                mylist.delete(Integer.parseInt(words[1].trim()));
            } else {
                throw new RiriException("You ain't making sense!");
            }
        }
    }
    /**
     * Parses the string to create a Task object
     * @param line string that describes a task.
     * @return a task object
     *
     * */
    private static Task parser(String line) {
        System.out.println(line);
        if (line.startsWith("[D]")) {
            return Deadline.parseDeadlineFromString(line);
        } else if (line.startsWith("[E]")) {
            return Event.parseEventFromString(line);
        } else if (line.startsWith("[T]")) {
            return Todo.parseTodoFromString(line);
        }
        return null;
    }

}