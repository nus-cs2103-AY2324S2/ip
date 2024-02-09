package missminutes;

import java.util.Arrays;

/**
 * MissMinutes is a personal chatbot to track your tasks
 */
public class MissMinutes {
    private final Storage storage;
    private TaskList tasks;


    /**
     * Creates a MissMinutes instance and tries to load previous state from filePath
     *
     * @param filePath Path to save and load TaskList object from
     */
    public MissMinutes(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = this.storage.loadTasks();
    }

    /**
     * No argument constructor with a default file path for use by JavaFX
     */
    public MissMinutes() {
        this("tasks.bin");
    }

    public String getResponse(String request) {
        Parser.CommandType cmdType = Parser.parseCommand(request);
        boolean isChanged = false;
        String reply;
        try {
            switch (cmdType) {
            case BYE:
                return Ui.sayBye();
            case LIST:
                return tasks.toString();
            case MARK:
                // Fallthrough
            case UNMARK:
                isChanged = true;
                // Split by space and parse into integers
                int[] indices;
                try {
                    indices = Arrays.stream(request.split(" ", 2)[1].split(" "))
                            .mapToInt(Integer::parseInt)
                            .map(x -> x - 1) // zero indexed
                            .toArray();
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
                    throw new MissMinutesException("Please enter a valid index. The correct usage is `mark <idx>`");
                }

                if (cmdType == Parser.CommandType.MARK) {
                    reply = tasks.markTask(indices);
                } else {
                    reply = tasks.unmarkTask(indices);
                }
                break;
            case DELETE:
                isChanged = true;
                reply = tasks.deleteTask(request);
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                isChanged = true;
                Task task = tasks.createTask(request);
                reply = tasks.addTask(task);
                break;
            case FIND:
                reply = tasks.findTask(request);
                break;
            case UNKNOWN:
                // Fallthrough
            default:
                throw new MissMinutesException(
                        "Oh, I'm sowwy, I didn't undewstand dat. (>_<) Can I hewp wif sumthin' else, pwease? UwU"
                );
            }
        } catch (MissMinutesException err) {
            reply = err.getMessage();
        }

        if (isChanged) {
            try {
                storage.saveTasks(tasks);
            } catch (MissMinutesException err) {
                return err.getMessage();
            }
        }

        return reply;
    }
}
