import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a chat bot to keep track of user's tasks.
 */
public class    Duke {

    private static final String FILE_PATH = "./data/Duke.txt";
    private static final String INTRO = "____________________________________________________________\n"
            + "        Hello! I'm sibehupzcoder9000\n"
            + "        What you want sia\n"
            + "____________________________________________________________\n";
    private static final String OUTRO = "____________________________________________________________\n"
            + "        wow so ur gg to leave me...\n"
            + "____________________________________________________________\n";
    private static ArrayList<Task> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println(INTRO);

        //load file
        try {
            Duke.loadFileContents();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String original = sc.nextLine();

        while (!original.equals("bye")) {
            try {
                Duke.processLine(original);
            } catch (DukeException e) {
                String message = "____________________________________________________________\n"
                        + e.getMessage()
                        + "\n____________________________________________________________\n";
                System.out.println(message);
            }
            original = sc.nextLine();
        }

        try {
            Duke.writeToFile();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
            System.out.print(OUTRO);
            sc.close();
    }

    /**
     * Lists out all tasks in task list.
     */
    public static void listOut() {
        StringBuilder listOutput = new StringBuilder("____________________________________________________________\n"
                + " Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            listOutput.append(" ").append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        listOutput.append("____________________________________________________________\n");
        System.out.print(listOutput);
    }

    /**
     * Returns message string for "delete" action.
     *
     * @param i index of task to delete.
     * @return string to output for "delete" action.
     */
    public static String deleteMessage(int i) {
        String output = "____________________________________________________________\n"
                + " Noted. I've removed this task:\n"
                + "   " + list.get(i - 1)
                + "\n Now you have " + (list.size() - 1) + " tasks in the list.\n"
                + "____________________________________________________________\n";
        list.remove(i - 1);
        return output;
    }

    /**
     * Returns message string for "add" action.
     *
     * @param task new task to add to list.
     * @return string to output for "add" action.
     */
    public static String addMessage(Task task) {
        list.add(task);
        return "____________________________________________________________\n"
                + " Got it. I've added this task:\n"
                + "   " + task
                + "\n Now you have " + (list.size()) + " tasks in the list.\n"
                + "____________________________________________________________\n";
    }

    /**
     * Reads from file and writes into ArrayList.
     */
    public static void loadFileContents() throws DukeException {
        File f = new File(FILE_PATH);
        try {
            if (f.exists()) {
                try (Scanner scanner = new Scanner(f)) {
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        try {
                            Duke.processLine(s);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } else {
                throw new DukeException("eh walao i cant find the file sia where u put");
            }
        } catch (IOException e) {
            throw new DukeException("omg i cant read");
        }
    }

    /**
     * Writes current tasklist into specified file.
     *
     * @throws DukeException If there is a problem with writing into file.
     */
    public static void writeToFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : list) {
                fw.write(task.writeToFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("omg i cant write sia :( too bad lol");
        }
    }

    public static void processLine(String original) throws DukeException {
            String[] inputParts = original.split("\\s+");

            //handle mark || unmark
            if (inputParts.length == 2 && (inputParts[0].equals("mark") || inputParts[0].equals("unmark"))) {
                int inputInt = Integer.parseInt(inputParts[1]);
                System.out.println(list.get(inputInt - 1).toggle());
            } else if (original.equals("list")) {
                //handle "list"
                Duke.listOut();
            } else if (inputParts[0].equals("todo")) {
                //handle "todoo"
                String description = original.replace("todo", "");
                if (description.isEmpty()) {
                    throw new DukeException("oi todo what. todo WHATTTTTT!!!!!!!!");
                }
                Task task = new ToDo(description);
                System.out.print(Duke.addMessage(task));
            } else if (inputParts[0].equals("deadline")) {
                //handle "deadline"
                String[] parts = original.replace("deadline", "").split(" /");
                Task task = new Deadline(parts[0], parts[1].replace("by ", ""));
                System.out.print(Duke.addMessage(task));
            } else if (inputParts[0].equals("event")) {
                //handle event
                String[] parts = original.replace("event", "").split(" /");
                Task task = new Event(parts[0], parts[1].replace("from ", ""), parts[2].replace("to ", ""));
                System.out.print(Duke.addMessage(task));
            } else if (inputParts[0].equals("delete")) {
                //handle delete
                int inputInt = Integer.parseInt(inputParts[1]);
                System.out.println(Duke.deleteMessage(inputInt));
            } else {
                throw new DukeException("harh what u talking sia walao");
            }

    }

}


