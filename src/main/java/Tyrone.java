import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Tyrone {
    private static final String logo = "\t████████╗██╗   ██╗██████╗  ██████╗ ███╗   ██╗███████╗\n" +
            "\t╚══██╔══╝╚██╗ ██╔╝██╔══██╗██╔═══██╗████╗  ██║██╔════╝\n" +
            "\t   ██║    ╚████╔╝ ██████╔╝██║   ██║██╔██╗ ██║█████╗\n" +
            "\t   ██║     ╚██╔╝  ██╔══██╗██║   ██║██║╚██╗██║██╔══╝\n" +
            "\t   ██║      ██║   ██║  ██║╚██████╔╝██║ ╚████║███████╗\n" +
            "\t   ╚═╝      ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝\n";
    private static final String greetMsg = "\tYo, what's crackin' fam! I'm Tyrone, your digital homie.\n" +
            "\tWhat's the word? So I can help you out today.\n" +
            "\n\t____________________________________________________________\n";
    private static final Scanner reader = new Scanner(System.in);
    private static final PrintWriter writer = new PrintWriter(System.out, true);
    private static final TaskList taskList = new TaskList();

    private enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE
    }

    private static final HashMap<String, Command> cmdMap = new HashMap<>();

    public static void main(String[] args) {
        try {
            handleInitialize();
            boolean isActive = true;
            while (isActive) {
                // extract the command
                String input = reader.nextLine();
                String cmdStr = !input.contains(" ") ? input : input.substring(0, input.indexOf(" "));
                if (!cmdMap.containsKey(cmdStr)) {
                    throw new TyroneCmdException("Command entered doesn't exist.");
                }
                Command cmd = cmdMap.get(cmdStr);

                // execute cmd logic respectively
                switch (cmd) {
                case BYE:
                    handleByeCommand();
                    isActive = false;
                    break;
                case LIST:
                    handleListCommand();
                    break;
                case TODO:
                    handleTodoCommand(input);
                    break;
                case DEADLINE:
                    handleDeadlineCommand(input);
                    break;
                case EVENT:
                    handleEventCommand(input);
                    break;
                case MARK:
                    handleMarkCommand(input);
                    break;
                case UNMARK:
                    handleUnmarkCommand(input);
                    break;
                case DELETE:
                    handleDeleteCommand(input);
                    break;
                default:
                    throw new TyroneCmdException("Command entered doesn't exist.");
                }

                taskList.saveTaskListToFile();
            }
        } catch (TyroneCmdException e) {
            writer.println(Tyrone.formatStringOutput(e.getMessage()));
        }
    }

    public static void handleInitialize() throws TyroneCmdException {
        cmdMap.put("bye", Command.BYE);
        cmdMap.put("list", Command.LIST);
        cmdMap.put("todo", Command.TODO);
        cmdMap.put("deadline", Command.DEADLINE);
        cmdMap.put("event", Command.EVENT);
        cmdMap.put("mark", Command.MARK);
        cmdMap.put("unmark", Command.UNMARK);
        cmdMap.put("delete", Command.DELETE);

        writer.println(logo + greetMsg);
        taskList.loadTaskListFromFile();
    }

    private static void handleByeCommand() {
        writer.println(Tyrone.formatStringOutput("Peace out! Crossin' my fingers for a speedy reunion, ya feel?"));
    }

    private static void handleListCommand() {
        writer.println(Tyrone.formatStringOutput(
                "Peep the lineup, here's the rundown of tasks on your list:\n" + "\t" + taskList));
    }

    private static void handleTodoCommand(String input) throws TyroneCmdException {
        // validate general input
        if (isEmptyParam(input))
            throw new TyroneCmdException("Can't leave that to-do description hanging dry.\n" +
                    "\t\tGotta drop some words in there!");

        // extract input param
        ToDo newItem = new ToDo(input.substring(5));
        taskList.addItem(newItem);
        writer.println(Tyrone.formatStringOutput("Got it added homie:\n" + "\t\t" + newItem +
                "\n\tNow you have " + taskList.getListSize() + " in the list."));
    }

    private static void handleDeadlineCommand(String input) throws TyroneCmdException {
        // validate general input
        String errorMsg = "Seems like the deadline command is incorrect.\n" +
                "\t\tIt must be: \"deadline <task description> /by <date time>\".";
        if (isEmptyParam(input) || !input.contains("/by"))
            throw new TyroneCmdException(errorMsg);

        // extract input params
        Deadline newItem = generateDeadlineFromInput(input.substring(9), errorMsg);
        taskList.addItem(newItem);
        writer.println(Tyrone.formatStringOutput("Got it added homie:\n" + "\t\t" + newItem + "\n\tNow you have " + taskList.getListSize() + " in the list."));
    }

    private static Deadline generateDeadlineFromInput(String input, String errorMsg) throws TyroneCmdException {
        String[] params = input.split("/by");

        // check if enough params in the first place
        if (params.length != 2) throw new TyroneCmdException(errorMsg);

        String description = params[0].trim();
        String deadlineDateTime = params[1].trim();

        // validate params
        if (description.isEmpty() || deadlineDateTime.isEmpty())
            throw new TyroneCmdException(errorMsg);

        return new Deadline(description, deadlineDateTime);
    }

    private static void handleEventCommand(String input) throws TyroneCmdException {
        String errorMsg = "Your event command is in incorrect format.\n" +
                "\t\tGotta follow the groove: \"event <task description> /from <date time> /to <date time>\".";

        // validate general input
        if (isEmptyParam(input) || !input.contains("/from") || !input.contains("/to"))
            throw new TyroneCmdException(errorMsg);

        // extract input params
        Event newItem = generateEventFromInput(input.substring(6), errorMsg);
        taskList.addItem(newItem);
        writer.println(Tyrone.formatStringOutput("Got it added homie:\n" + "\t\t" + newItem + "\n\tNow you have " + taskList.getListSize() + " in the list."));
    }

    private static Event generateEventFromInput(String input, String errorMsg) throws TyroneCmdException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(0, fromIndex).trim();
        String startDateTime = fromIndex + 5 < toIndex ? input.substring(fromIndex + 5, toIndex).trim() : "";
        String endDateTime = toIndex + 3 < input.length() - 1 ? input.substring(toIndex + 3).trim() : "";

        // validate params
        if (description.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty())
            throw new TyroneCmdException(errorMsg);

        return new Event(description, startDateTime, endDateTime);
    }

    private static void handleMarkCommand(String input) throws TyroneCmdException {
        // validate general input
        if (isEmptyParam(input)) {
            throw new TyroneCmdException("Can't leave that markup id empty. Gotta drop some number in there!");
        }

        // extract input params
        try {
            String param = input.substring(4).trim();
            int index = Integer.parseInt(param) - 1;
            if (taskList.getListSize() <= 0 || index < 0 || index >= taskList.getListSize())
                throw new TyroneCmdException("It looks like you're trying to mark with an invalid id.\n" +
                        "\t\tDouble-check that your 0 <= id < task list size.");
            taskList.markItemDone(index);
            writer.println(Tyrone.formatStringOutput("Dope! Check it, I've tagged this task as handled:\n" +
                    "\t\t" + taskList.getItem(index)));
        } catch (NumberFormatException e) {
            throw new TyroneCmdException("Your mark parameter id is acting up.\n" +
                    "\t\tIt's gotta be a legit number matchin' up with the right task.");
        }
    }

    private static void handleUnmarkCommand(String input) throws TyroneCmdException {
        // validate general input
        if (isEmptyParam(input)) {
            throw new TyroneCmdException("Can't leave that unmarkup id empty. Gotta drop some number in there!");
        }

        try {
            String param = input.substring(6).trim();
            int index = Integer.parseInt(param) - 1;
            if (taskList.getListSize() == 0 || index < 0 || index >= taskList.getListSize())
                throw new TyroneCmdException("It looks like you're trying to unmark with an invalid id.\n" +
                        "\t\tDouble-check that your 0 <= id < task list size.");
            taskList.unmarkItemDone(index);
            writer.println(Tyrone.formatStringOutput(
                    "A'ight, I've stamped this task as still in the works:\n" +
                            "\t\t" + taskList.getItem(index)));
        } catch (NumberFormatException e) {
            throw new TyroneCmdException("Your unmark parameter id is acting up.\n" +
                    "\t\tIt's gotta be a legit number matchin' up with the right task.");
        }
    }

    private static void handleDeleteCommand(String input) throws TyroneCmdException {
        // validate general input
        if (isEmptyParam(input)) {
            throw new TyroneCmdException("Can't leave that delete id empty. Gotta drop some number in there!");
        }

        try {
            String param = input.substring(6).trim();
            int index = Integer.parseInt(param) - 1;
            if (taskList.getListSize() == 0 || index < 0 || index >= taskList.getListSize())
                throw new TyroneCmdException("It looks like you're trying to delete with an invalid id.\n" +
                        "\t\tDouble-check that your 0 <= id < task list size.");
            Task delItem = taskList.deleteItem(index);
            writer.println(Tyrone.formatStringOutput(
                    "Boom! Task officially evicted from the list. Consider it gone:\n" +
                            "\t\t" + delItem +
                            "\n\tNow you have " + taskList.getListSize() + " in the list."));
        } catch (NumberFormatException e) {
            throw new TyroneCmdException("Your delete parameter id is acting up.\n" +
                    "\t\tIt's gotta be a legit number matchin' up with the right task.");
        }
    }

    private static boolean isEmptyParam(String input) {
        return !input.trim().contains(" ");
    }

    private static String formatStringOutput(String content) {
        return ("\n\t____________________________________________________________\n" +
                "\t" + content + "\n" +
                "\n\t____________________________________________________________\n");
    }
}
