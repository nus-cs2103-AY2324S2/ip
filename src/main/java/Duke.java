import java.util.*;
public class Duke {

    public static String horizontalLine = "_________________________________________________________________________\n";

    public static String greet() {
        return horizontalLine
                + "Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n";
    }
    public static String exit() {
        return horizontalLine
                + "Farewell, fleeting soul! 'Til our paths entwine once more.\n"
                + horizontalLine;
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
        String output = horizontalLine
                + "Behold, my dear! Here unfurls the tasks in your list.\n";
        for (Task task : list)
            output += list.indexOf(task) + "." + task.toString() + "\n";
        output += horizontalLine;
        System.out.println(output);
    }

    public static void markUpdate(String input, boolean isMark) throws DukeException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
            if (isMark) {
                list.get(index).mark();
                System.out.println(horizontalLine
                        + "Well, isn't this delightful! I've marked this task as done, my dear.\n"
                        + "  " + list.get(index).toString() + "\n"
                        + horizontalLine);
            }
            else {
                list.get(index).unmark();
                System.out.println(horizontalLine
                        + "Very well, my dear! I've noted this task as yet untouched.\n"
                        + "  " + list.get(index).toString() + "\n"
                        + horizontalLine);
            }
        } catch (Exception e) {
            throw new DukeException("Please enter a valid index.\n");
        }
    }

    public static void todo(String input) {
        Task temp = new ToDo(input);
        added(temp);
    }

    public static void deadline(String input) throws DukeException{
        String[] deadline = input.split("/by", 2);
        if (deadline.length < 2 || deadline[0].isBlank() || deadline[1].isBlank()) {
            throw new DukeException("Please format deadline <task> /by <date/time>.\n");
        }
        Task temp = new Deadline(deadline[0], deadline[1]);
        added(temp);
    }

    public static void event(String input) throws DukeException {
        String[] event = input.split("/from|/to", 3);
        if (event.length < 3 || event[0].isBlank() || event[1].isBlank() || event[2].isBlank()) {
            throw new DukeException("Please format event <task> /from <date/time> /to <date/time>.\n");
        }
        Task temp = new Event(event[0], event[1], event[2]);
        added(temp);
    }

    public static void added(Task task) {
        list.add(task);
        System.out.println(horizontalLine
                + "Marvelous! Another task graces our repertoire:\n"
                + "  " + task.toString() + "\n"
                + "And with this latest addition, our list of tasks swells to a delightful "
                + list.size() + ".\n"
                + horizontalLine);
    }

    public static void delete(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
            Task temp = list.get(index);
            list.remove(index);
            System.out.println(horizontalLine
                    + "Very well, my dear! I've removed this task from our list.\n"
                    + "  " + temp.toString() + "\n"
                    + horizontalLine);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid index.\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(greet());
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            try {
                readInput(input);
            } catch (DukeException e) {
                System.out.println(horizontalLine + e.getMessage() + horizontalLine);
            }
        }
        System.out.println(exit());
    }
}
