import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    private static final String FILE_PATH = "data/duke.txt";
    public static void main(String[] args) throws DukeException {

        Scanner scanner = new Scanner(System.in);
        File f = new File(FILE_PATH);
        System.out.println("file exists?: " + f.exists());


        try {
            if (!f.exists()) {

                if (f.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("Unable to create file");
                }
            } else {
                System.out.println("Loading data");
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }

        List<Task> list = new ArrayList<>();

        try {
            list = retrieveData(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Hello! I'm tars.");
        System.out.println("What can I do for you?");

        while (scanner.hasNextLine()) {
            String comd = scanner.nextLine();

            if (comd.equals("bye")) {
                break;
            }
            if (comd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }

            else if (comd.startsWith("todo ")) {
                if (comd.length() <= 5) {
                    throw new DukeException("Empty Description");
                }
                Todo t = new Todo(comd.substring(5));
                list.add(t);
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");

                try {
                    addData(FILE_PATH, t.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

            else if (comd.startsWith("deadline ")) {
                if (comd.length() <= 9) {
                    throw new DukeException("Empty Description");
                }
                String[] data = comd.split("/");
                String task = data[0].substring(9);
                Deadline d = new Deadline(task, data[1].substring(3));
                list.add(d);

                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");

                try {
                    addData(FILE_PATH, d.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (comd.startsWith("event ")) {
                if (comd.length() <= 6) {
                    throw new DukeException("Empty Description");
                }
                String[] data = comd.split("/");
                String task = data[0].substring(6);

                Event e = new Event(task, data[1].substring(5), data[2].substring(3));
                list.add(e);
                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");

                try {
                    addData(FILE_PATH, e.toString() + System.lineSeparator());
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }

            else if(comd.startsWith("mark ")) {
                String[] res = comd.split(" ");
                String in = res[1];
                int index = Integer.parseInt(in);

                list.get(index - 1).mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(index - 1).printWithStatus());

                try {
                    write(FILE_PATH, list);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }

            else if (comd.startsWith("unmark ")) {
                String[] result = comd.split(" ");
                String in = result[1];
                int index = Integer.parseInt(in);
                list.get(index - 1).unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(list.get(index - 1).printWithStatus());

                try {
                    write(FILE_PATH, list);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (comd.startsWith("delete ")) {
                String[] result = comd.split(" ");
                int in = Integer.parseInt(result[1]);

                System.out.println("Noted. I've removed this task: ");
                System.out.println(list.get(in - 1).printWithStatus());
                list.remove(in - 1);
                System.out.println("Now you have " + list.size() + " tasks in the list.");

                try {
                    write(FILE_PATH, list);
                } catch (IOException err) {
                    System.out.println(err.getMessage());
                }
            }
            else {
                throw new DukeException("Invalid Command!");
            }
            
        }
        System.out.print("Bye. Hope to see you again soon!");
    }

    private static List<Task> retrieveData(File file) throws IOException {
        List<Task> loadedList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while(fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                loadedList.add(Task.parser(taskData));
                System.out.println("loaded");
            }
        }

        if (loadedList.isEmpty()) {
            loadedList = new ArrayList<>();
        }

        return loadedList;
    }

    private static void write(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.toString() + System.lineSeparator());

        }
        fw.close();
    }

    private static void addData(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
