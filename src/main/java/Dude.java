import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dude {
    private Storage storage = new Storage();

    private void add(Task task) {
        storage.createRow(task);
        ArrayList<Task> tasks = storage.listRows();
        Ui.print("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    private void list() {
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            listString += i + "." + task + "\n";
        }
        Ui.print(listString);
    }

    private void mark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.get(index);
        task.mark();
        storage.createRows(tasks);
        Ui.print("Nice! I've marked this task as done:\n" + task + "\n");
    }

    private void unmark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        storage.createRows(tasks);
        Ui.print("OK, I've marked this task as not done yet:\n" + task + "\n");

    }

    private void delete(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.remove(index);
        storage.createRows(tasks);
        Ui.print("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");

    }

    public static void main(String[] args) {
        Ui.greeting();

        Dude dude = new Dude();

        Scanner scanner = new Scanner(System.in);
        loop:
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();

            String[] inputSplit = input.split(" ", 2);
            String command = inputSplit[0];
            String ipArgs = inputSplit.length > 1 ? inputSplit[1] : "";
            ArrayList<String> parameters = new ArrayList<>();
            ArrayList<Object> formattedParameters = new ArrayList<>();
            switch (command) {
            case "bye":
                break loop;
            case "list":
                dude.list();
                break;
            case "mark":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER})
                ) {
                    continue;
                }
                dude.mark((int) formattedParameters.get(0) - 1);
                break;
            case "unmark":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER})
                ) {
                    continue;
                }
                dude.unmark((int) formattedParameters.get(0) - 1);
                break;
            case "delete":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"index"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.INTEGER})
                ) {
                    continue;
                }
                dude.delete((int) formattedParameters.get(0) - 1);
                break;
            case "todo":
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING})
                ) {
                    continue;
                }
                Todo todo = new Todo((String) formattedParameters.get(0));
                dude.add(todo);
                break;
            case "deadline": {
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description", "by"},
                        new Parser.ParameterTypes[]{Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE})
                ) {
                    continue;
                }
                Deadline deadline = new Deadline(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1));
                dude.add(deadline);
                break;
            }
            case "event": {
                if (!Parser.parse(
                        formattedParameters, command, ipArgs,
                        new String[]{"description", "from", "to"},
                        new Parser.ParameterTypes[]{
                                Parser.ParameterTypes.STRING, Parser.ParameterTypes.DATE, Parser.ParameterTypes.DATE})
                ) {
                    continue;
                }
                Event event = new Event(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1),
                        (String) formattedParameters.get(2));
                dude.add(event);
                break;
            }
            default:
                Ui.print("Unknown command detected!\n");
            }
        }
        Ui.goodbye();
    }
}
