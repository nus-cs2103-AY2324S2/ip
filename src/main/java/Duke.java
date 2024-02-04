import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static File TASKS_FILE = new File("./data/tasks.txt");
    public static String HORIZONTAL_LINE = "________________________________________________________________________\n";

    public static void initialise() {
        try {
            Scanner sc = new Scanner(TASKS_FILE);
            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                String[] task = line.split(" \\| ", 5);
                switch (task[0]) {
                    case "T":
                        list.add(new ToDo(task[2]));
                        break;
                    case "D":
                        list.add(new Deadline(task[2], stringToDateTime(task[3].trim())));
                        break;
                    case "E":
                        list.add(new Event(task[2], stringToDateTime(task[3].trim()),
                                stringToDateTime(task[4].trim())));
                        break;
                }
                if (task[1].equals("1")) {
                    list.get(index).mark();
                }
                index++;
            }
        } catch (FileNotFoundException e) {
            File parentDir = TASKS_FILE.getParentFile();
            if (!parentDir.exists()) {
                if (parentDir.mkdir()) {
                    System.out.println(HORIZONTAL_LINE
                            + "I've created a new directory for your tasks, my dear.\n"
                            + HORIZONTAL_LINE);
                } else {
                    System.out.println(HORIZONTAL_LINE
                            + "I'm afraid I've encountered an error while creating a directory for your tasks, my dear.\n"
                            + HORIZONTAL_LINE);
                }
            }
            try {
                if (TASKS_FILE.createNewFile()) {
                    System.out.println(HORIZONTAL_LINE
                            + "I've created a new file for your tasks, my dear.\n"
                            + HORIZONTAL_LINE);
                } else {
                    System.out.println(HORIZONTAL_LINE
                            + "I'm afraid I've encountered an error while creating a file for your tasks, my dear.\n"
                            + HORIZONTAL_LINE);
                }
            } catch (IOException newException) {
                System.out.println(HORIZONTAL_LINE
                        + "I'm afraid I've encountered an error while creating a file for your tasks, my dear.\n"
                        + HORIZONTAL_LINE);
            }
        } catch (DukeException e) {
            System.out.println(HORIZONTAL_LINE
                    + "I'm afraid the date/time data in the file does not fit the format required, dear.\n"
                    + HORIZONTAL_LINE);
        }
    }

    public static String greet() {
        return HORIZONTAL_LINE
                + "Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n";
    }
    public static String exit() {
        return HORIZONTAL_LINE
                + "Farewell, fleeting soul! 'Til our paths entwine once more.\n"
                + HORIZONTAL_LINE;
    }

    public static void readInput(String input) throws DukeException{
        if (input.equals("list")) {
            list();
        }
        else if (input.startsWith("mark")) {
            markUpdate(input, true);
        }
        else if (input.startsWith("unmark")) {
            markUpdate(input, false);
        }
        else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            String[] help = input.split("\\s+", 2);
            if (help.length < 2 || help[1].isBlank()) {
                throw new DukeException("Please enter a task (and date/time(s) if applicable).\n");
            }
            if (input.startsWith("todo")) {
                todo(help[1]);
            }
            if (input.startsWith("deadline")) {
                deadline(help[1]);
            }
            if (input.startsWith("event")) {
                event(help[1]);
            }
        }
        else if (input.startsWith("delete")) {
            delete(input);
            editWrite();
        }
        else {
            throw new DukeException("I'm afraid I don't understand what you mean, my dear.\n"
                    + "The requests I can process are:\n"
                    + "  list\n"
                    + "  mark <index>\n"
                    + "  unmark <index>\n"
                    + "  todo <task>\n"
                    + "  deadline <task> /by <date/time>\n"
                    + "  event <task> /from <date/time> /to <date/time>\n"
                    + "  bye\n");
        }
    }

    public static ArrayList<Task> list = new ArrayList<>(100);

    public static void list() {
        String output = HORIZONTAL_LINE
                + "Behold, my dear! Here unfurls the tasks in your list.\n";
        for (Task task : list)
            output += (list.indexOf(task) + 1) + "." + task.toString() + "\n";
        output += HORIZONTAL_LINE;
        System.out.println(output);
    }

    public static void markUpdate(String input, boolean isMark) throws DukeException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
            if (isMark) {
                list.get(index).mark();
                System.out.println(HORIZONTAL_LINE
                        + "Well, isn't this delightful! I've marked this task as done, my dear.\n"
                        + "  " + list.get(index).toString() + "\n"
                        + HORIZONTAL_LINE);
            }
            else {
                list.get(index).unmark();
                System.out.println(HORIZONTAL_LINE
                        + "Very well, my dear! I've noted this task as yet untouched.\n"
                        + "  " + list.get(index).toString() + "\n"
                        + HORIZONTAL_LINE);
            }
            editWrite();
        } catch (Exception e) {
            throw new DukeException("Please enter a valid index.\n");
        }
    }

    public static void todo(String input) {
        Task temp = new ToDo(input);
        added(temp, 'T');
    }

    public static LocalDateTime stringToDateTime(String input) throws DukeException {
        String dateTimeFormat = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        try {
            LocalDateTime parsed = LocalDateTime.parse(input, formatter);
            return parsed;
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time.\n");
        }
    }

    public static void deadline(String input) throws DukeException {
        String[] deadline = input.split("/by", 2);
        if (deadline.length < 2 || deadline[0].isBlank() || deadline[1].isBlank()) {
            throw new DukeException("Please format deadline <task> /by <dd-MM-yyyy HH:mm>.\n");
        }
        Task temp = new Deadline(deadline[0], stringToDateTime(deadline[1].trim()));
        added(temp, 'D');
    }

    public static void event(String input) throws DukeException {
        String[] event = input.split("/from|/to", 3);
        if (event.length < 3 || event[0].isBlank() || event[1].isBlank() || event[2].isBlank()) {
            throw new DukeException("Please format event <task> /from dd-MM-yyyy HH:mm /to <dd-MM-yyyy HH:mm>.\n");
        }
        Task temp = new Event(event[0], stringToDateTime(event[1].trim()), stringToDateTime(event[2].trim()));
        added(temp, 'E');
    }

    public static void added(Task task, char type) {
        list.add(task);

        try {
            FileWriter writer = new FileWriter(TASKS_FILE, true);
            writer.write(type + " | " + (task.isDone ? "1" : "0") + " | " + task.description);
            if (type == 'D') {
                writer.write("|" + ((Deadline) task).by);
            }
            if (type == 'E') {
                writer.write("|" + ((Event) task).from + "|" + ((Event) task).to);
            }
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(HORIZONTAL_LINE
                    + "I'm afraid I've encountered an error while writing your tasks, my dear.\n"
                    + HORIZONTAL_LINE);
        }

        System.out.println(HORIZONTAL_LINE
                + "Marvelous! Another task graces our repertoire:\n"
                + "  " + task.toString() + "\n"
                + "And with this latest addition, our list of tasks swells to a delightful "
                + list.size() + ".\n"
                + HORIZONTAL_LINE);

    }

    public static void delete(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
            Task temp = list.get(index);
            list.remove(index);
            System.out.println(HORIZONTAL_LINE
                    + "Very well, my dear! I've removed this task from our list.\n"
                    + "  " + temp.toString() + "\n"
                    + HORIZONTAL_LINE);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid index.\n");
        }
    }

    public static void editWrite() {
        try {
            FileWriter writer = new FileWriter(TASKS_FILE);
            for (Task task : list) {
                char type = 'T';
                if (task instanceof Deadline) {
                    type = 'D';
                }
                if (task instanceof Event) {
                    type = 'E';
                }
                writer.write(type + " | " + (task.isDone ? "1" : "0") + " | " + task.description);
                if (type == 'D') {
                    writer.write(" | " + ((Deadline) task).by);
                }
                if (type == 'E') {
                    writer.write(" | " + ((Event) task).from + " | " + ((Event) task).to);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(HORIZONTAL_LINE
                    + "I'm afraid I've encountered an error while writing your tasks, my dear.\n"
                    + HORIZONTAL_LINE);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(greet());
        initialise();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            try {
                readInput(input);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL_LINE + e.getMessage() + HORIZONTAL_LINE);
            }
        }
        System.out.println(exit());
    }
}
