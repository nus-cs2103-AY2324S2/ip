import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.function.ToDoubleFunction;

public class Duke {
    public static void main(String[] args) {

        File dir;
        File f;
        ArrayList<Task> list = new ArrayList<>();

        dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        f = new File(dir, "duke.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        try {
            list = readFileContents(f);
        } catch (FileNotFoundException fe) {
            System.out.println("File corrupted! Failed to create new file.");
        }

        String line = "_____________________________________________";
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        System.out.println(line);
        System.out.println(greeting + line + "\n");

        Scanner sc = new Scanner(System.in);
        String input;
        int index;
        Task t;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                index = 1;
                for (Task s : list) {
                    System.out.println(index + "." + s);
                    index++;
                }
            } else {
                try {
                    processInput(input, list);
                } catch (DukeException de) {
                    System.out.println(de.toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("DukeException: Please input valid integer.");
                }
            }
            System.out.println(line + "\n");
        }

        try {
            FileWriter fw = new FileWriter(f);
            for (Task task: list) {
                fw.write(task.toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong.. Failed to save list.");
        }

        String closing = "Bye. Hope to see you again soon!\n";
        System.out.println(closing + line);
    }

    private static ArrayList<Task> readFileContents(File filePath) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(filePath);
        String s;
        String[] stringSplit;
        Task t;
        while (sc.hasNext()) {
            s = sc.nextLine();
            try {
                stringSplit = s.split("-");
                for (int i = 0; i < stringSplit.length; i++) {
                    stringSplit[i] = stringSplit[i].trim();
                    System.out.println(stringSplit[i]);
                }
                boolean marked = Boolean.valueOf(stringSplit[1]);
                switch (stringSplit[0]){
                case "T":
                    t = new ToDo(stringSplit[2], marked);
                    list.add(t);
                    break;
                case "D":
                    t = new Deadline(stringSplit[2], marked, stringSplit[3]);
                    list.add(t);
                    break;
                case "E":
                    t = new Event(stringSplit[2], marked, stringSplit[3], stringSplit[4]);
                    list.add(t);
                    break;
                default:
                    throw new DukeException("Corrupted Data!");
                }
            } catch (DukeException de) {
                System.out.println(de.toString());
            }
        }
        return list;
    }

    private static void processInput(String str,  ArrayList<Task> list) throws DukeException {
        String[] stringSplit = str.split(" ");
        String command = stringSplit[0];
        String desc;
        int index;
        Task t;

        switch (command) {
        case "mark":
            // Exception handling and splitting the string
            if (stringSplit.length < 2) {
                throw new DukeException("Command description cannot be empty!");
            }
            desc = str.split(command + " ")[1];
            stringSplit = str.split(" ");
            index = Integer.parseInt(stringSplit[1]) - 1;
            if (index + 1 > list.size()) {
                throw new DukeException("You only have " + list.size() + " tasks in the list.");
            }

            // get Task, edit and put back to list
            t = list.get(index);
            t.mark();
            list.set(index, t);

            // Print system message
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t.toString());
            break;
        case "unmark":
            // Exception handling and splitting the string
            if (stringSplit.length < 2) {
                throw new DukeException("Command description cannot be empty!");
            }
            desc = str.split(command + " ")[1];
            stringSplit = str.split(" ");
            index = Integer.parseInt(stringSplit[1]) - 1;
            if (index + 1 > list.size()) {
                throw new DukeException("You only have " + list.size() + " tasks in the list.");
            }

            // get Task, edit and put back to list
            t = list.get(index);
            t.unmark();
            list.set(index, t);

            // Print system message
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(t.toString());
            break;
        case "delete":
            // Exception handling and splitting the string
            if (stringSplit.length < 2) {
                throw new DukeException("Command description cannot be empty!");
            }
            desc = str.split(command + " ")[1];
            stringSplit = str.split(" ");
            index = Integer.parseInt(stringSplit[1]) - 1;
            if (index + 1 > list.size()) {
                throw new DukeException("You only have " + list.size() + " tasks in the list.");
            }

            t = list.remove(index); // remove task

            // Print system message
            System.out.println("Noted. I've removed this task:");
            System.out.println(t.toString());
            System.out.println("No-w you have " + list.size() + " tasks in the list.");
            break;
        case "todo":
            // Exception handling and splitting the string
            if (stringSplit.length < 2) {
                throw new DukeException("Command description cannot be empty!");
            }
            desc = str.split(command + " ")[1];

            // Create Task and add to list
            t = new ToDo(desc.trim());
            list.add(t);

            // Print system message
            System.out.println("Got it. I've added this task:");
            System.out.println(t.toString());
            System.out.println("Now you have " + list.size() + " tasks in this list.");
            break;
        case "deadline":
            // Exception handling and splitting the string
            if (stringSplit.length < 2) {
                throw new DukeException("Command description cannot be empty!");
            }
            desc = str.split(command + " ")[1];
            stringSplit = desc.split("/by");
            if (stringSplit.length < 2) {
                throw new DukeException("Deadline /by cannot be empty!");
            }
            desc = stringSplit[0];
            String by = stringSplit[1];

            // Create Task and add to list
            t = new Deadline(desc.trim(), by.trim());
            list.add(t);

            // Print system message
            System.out.println("Got it. I've added this task:");
            System.out.println(t.toString());
            System.out.println("Now you have " + list.size() + " tasks in this list.");
            break;
        case "event":
            // Exception handling and splitting the string
            if (stringSplit.length < 2) {
                throw new DukeException("Command description cannot be empty!");
            }
            desc = str.split(command + " ")[1];
            stringSplit = desc.split("/from");
            if (stringSplit.length < 2) {
                throw new DukeException("Event /from cannot be empty!");
            }
            desc = stringSplit[0];
            stringSplit = stringSplit[1].split("/to");
            if (stringSplit.length < 2) {
                throw new DukeException("Event /to cannot be empty!");
            }
            String from = stringSplit[0];
            String to = stringSplit[1];

            // Create Task and add to list
            t = new Event(desc.trim(), from.trim(), to.trim());
            list.add(t);

            // Print system message
            System.out.println("Got it. I've added this task:");
            System.out.println(t.toString());
            System.out.println("Now you have " + list.size() + " tasks in this list.");
            break;
        default:
            throw new DukeException("I'm sorry, I don't know what that means.\n" +
                    "Please input valid commands (i.e. [command] [description]).\n" +
                    "You can choose from the following available commands:\n" +
                    "   * todo [desc]\n" +
                    "   * event [desc] /from [desc] /to [desc]\n" +
                    "   * deadline [desc] /by [desc]\n" +
                    "   * list\n" +
                    "   * mark [number]\n" +
                    "   * unmark [number]\n" +
                    "   * delete [number]");
        }
    }
}
