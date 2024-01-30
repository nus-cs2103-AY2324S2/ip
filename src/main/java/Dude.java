import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dude {
    enum ParameterTypes {
        INTEGER,
        STRING
    }
    static final String spacer = "____________________________________________________________\n";
    static final String name = "Dude";
    private Storage storage = new Storage();
    private static void print(String text) {
        System.out.println(spacer + text + spacer);
    }

    private static void greeting() {
        print("Hello! I'm Dude\nWhat can I do for you?\n");
    }
    private static void goodbye() {
        print("Bye. Hope to see you again soon!\n");
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean checkParameterExists(ArrayList<String> output, String command, String parameterName, String parameter) {
        if (!parameter.isEmpty()) {
            output.add(parameter);
            return true;
        }
        print("Parameter " + parameterName + " of " + command + " cannot be empty!\n");
        return false;
    }

    private static boolean checkFlagExists(ArrayList<String> output, String command, String parameterName, String parameter) {
        String[] parameterSplit = parameter.split(" ", 2);
        if (!parameterSplit[0].equals(parameterName)) {
            print("Invalid flag name " + parameterName + " for command " + command + "\n");
            return false;
        }
        String arg = parameterSplit.length > 1 ? parameterSplit[1] : "";
        return checkParameterExists(output, command, parameterName, arg);
    }

    private static boolean getParameters(ArrayList<String> output, String command, String[] parameterNames, String args) {
        String[] argsSplit = args.split("/");
        if (argsSplit.length != parameterNames.length) {
            print("Invalid number of parameters for " + command + ", need to have: "
                    + Arrays.toString(parameterNames) + "\n");
            return false;
        }
        if (!checkParameterExists(output, command, parameterNames[0], argsSplit[0])) return false;
        for (int i = 1; i < argsSplit.length; i++) {
            if (!checkFlagExists(output, command, parameterNames[i], argsSplit[i])) return false;
        }

        return true;
    }

    private static boolean formatParameters(ArrayList<Object> foramttedParameters, ArrayList<String> parameters, ParameterTypes[] formats) {
        for (int i = 0; i < formats.length; i++) {
            if (formats[i] == ParameterTypes.INTEGER) {
                if (isNumeric(parameters.get(i))) foramttedParameters.add(Integer.parseInt(parameters.get(i)));
                else {
                    print("Format of " + parameters.get(i) + " is not an integer\n");
                }
            } else {
                foramttedParameters.add(parameters.get(i));
            }
        }
        return true;
    }

    private void echo(String input) {
        System.out.println(spacer + input + "\n" + spacer);
    }

    private void add(Task task) {
        storage.createRow(task);
        ArrayList<Task> tasks = storage.listRows();
        print("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    private void list() {
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            listString += i + "." + task + "\n";
        }
//        for (int i = 1; i < this.list.size() + 1; i++) {
//            Task task = this.list.get(i - 1);
//            listString += i + "." + task + "\n";
//        }
        print(listString);
    }

    private void mark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            print("Invalid index range\n");
            return;
        }
        Task task = tasks.get(index);
        task.mark();
        storage.createRows(tasks);
        print("Nice! I've marked this task as done:\n" + task + "\n");
    }

    private void unmark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            print("Invalid index range\n");
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        storage.createRows(tasks);
        print("OK, I've marked this task as not done yet:\n" + task + "\n");

    }

    private void delete(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            print("Invalid index range\n");
            return;
        }
        Task task = tasks.remove(index);
        storage.createRows(tasks);
        print("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");

    }

    public static void main(String[] args) {
        greeting();

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
                if (!getParameters(parameters, command, new String[]{"index"}, ipArgs)) continue;
                if (!formatParameters(
                        formattedParameters,
                        parameters,
                        new ParameterTypes[]{ParameterTypes.INTEGER})
                ) continue;
                dude.mark((int) formattedParameters.get(0) - 1);
                break;
            case "unmark":
                if (!getParameters(parameters, command, new String[]{"index"}, ipArgs)) continue;
                if (!formatParameters(
                        formattedParameters,
                        parameters,
                        new ParameterTypes[]{ParameterTypes.INTEGER})
                ) continue;
                dude.unmark((int) formattedParameters.get(0) - 1);
                break;
            case "delete":
                if (!getParameters(parameters, command, new String[]{"index"}, ipArgs)) continue;
                if (!formatParameters(
                        formattedParameters,
                        parameters,
                        new ParameterTypes[]{ParameterTypes.INTEGER})
                ) continue;
                dude.delete((int) formattedParameters.get(0) - 1);
                break;
            case "todo":
                if (!getParameters(parameters, command, new String[]{"description"}, ipArgs)) continue;
                if (!formatParameters(
                        formattedParameters,
                        parameters,
                        new ParameterTypes[]{ParameterTypes.STRING})
                ) continue;
                Todo todo = new Todo((String) formattedParameters.get(0));
                dude.add(todo);
                break;
            case "deadline": {
                if (!getParameters(parameters, command, new String[]{"description", "by"}, ipArgs)) continue;
                if (!formatParameters(
                        formattedParameters,
                        parameters,
                        new ParameterTypes[]{ParameterTypes.STRING, ParameterTypes.STRING})
                ) continue;
                Deadline deadline = new Deadline(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1));
                dude.add(deadline);
                break;
            }
            case "event": {
                if (!getParameters(parameters, command, new String[]{"description", "from", "to"}, ipArgs)) continue;
                if (!formatParameters(
                        formattedParameters,
                        parameters,
                        new ParameterTypes[]{ParameterTypes.STRING, ParameterTypes.STRING, ParameterTypes.STRING})
                ) continue;
                Event event = new Event(
                        (String) formattedParameters.get(0),
                        (String) formattedParameters.get(1),
                        (String) formattedParameters.get(2));
                dude.add(event);
                break;
            }
            default:
                print("Unknown command detected!\n");
            }
        }
        goodbye();
    }
}
