package headcube;

/**
 * The Parser class handles the parsing of user input and executing corresponding actions
 * in the HeadCube application.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor of a Parser object with references to the UI, TaskList, and Storage.
     *
     * @param ui       The UI for displaying messages to the user.
     * @param taskList The list of tasks to manage.
     * @param storage  The storage to save and load tasks.
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parses the given input string and performs the corresponding action.
     *
     * @param input The user input to parse.
     * @return The response to the input
     */
    public String parse(String input) {
        assert !input.isBlank() : "Input cannot be blank";
        try {
            String[] split = input.split(" ", 2);

            if (input.equals("list")) {
                return ui.list(taskList);
            } else if (split[0].equals("mark")) {
                return taskList.mark(Integer.parseInt(split[1]));
            } else if (split[0].equals("find")) {
                String keyword = split[1];
                TaskList foundTasks = taskList.find(keyword);
                return ui.showFoundTasks(foundTasks);
            } else if (split[0].equals("delete")) {
                return taskList.delete(Integer.parseInt(split[1]));
            } else if (input.equals("save")) {
                return storage.save(taskList);
            } else {
                String[] string = input.split(" ", 2);
                String event = string[0];
                boolean isAdd;
                isAdd = isAdd(event, split);
                if (!isAdd) {
                    return ui.duplicateMessage();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Got it. I've added this task:\n  ")
                        .append(taskList.get(taskList.size() - 1))
                        .append("\nNow you have ")
                        .append(taskList.size())
                        .append(" tasks in the list.\n");
                return sb.toString();
            }
        } catch (HeadCubeException e) {
            return ui.error(e.getMessage());
        }
    }

    private boolean isAdd(String event, String[] split) throws HeadCubeException {
        String description;
        boolean isAdd;
        if (event.equals("todo")) {
            if (split.length < 2 || split[1].isBlank()) {
                throw new HeadCubeException("Todo cannot be empty!!");
            }
            isAdd = taskList.add(new ToDos(split[1]));
        } else if (event.equals("deadline")) {
            String[] parts = split[1].split(" /by ", 2);
            description = parts[0];
            String by = parts[1];
            isAdd = taskList.add(new Deadlines(description, by));
        } else if (event.equals("event")) {
            String[] parts = split[1].split(" /from ", 2);
            description = parts[0];
            String[] times = parts[1].split(" /to ", 2);
            String start = times[0].trim();
            String end = times[1].trim();
            isAdd = taskList.add(new Events(description, start, end));
        } else {
            throw new HeadCubeException("I do not understand what that means!!");
        }
        return isAdd;
    }
}
