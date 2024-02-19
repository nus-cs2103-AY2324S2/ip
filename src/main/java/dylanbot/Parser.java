package dylanbot;

/**
 * Represents a Parser that makes sense of user inputs
 */
public class Parser {
    private Ui ui;
    private TaskList tl;

    /**
     * Creates a Parser
     *
     * @param ui The Ui to be used
     * @param tl The TaskList that will be referenced and used
     */
    public Parser(Ui ui, TaskList tl) {
        this.ui = ui;
        this.tl = tl;
    }

    /**
     * Processes the provided user input and takes the appropriate follow-up actions
     *
     * @param command Provided user input
     * @throws DylanBotException If input provided is of an invalid format
     */
    public String process(String command) throws DylanBotException {
        String response = "";
        try {
            if (command.equals("list")) {
                if (tl.isEmpty()) {
                    throw new DylanBotException("No tasks to list right now! Add something first la");
                }
                response = ui.displayTasks(tl.getTasks());
            } else if (command.startsWith("find")) {
                if (command.split(" ").length < 2) {
                    throw new DylanBotException("HEY no search term provided");
                }
                String term = command.split(" ")[1];
                response = tl.findTerm(term);
            } else if (command.startsWith("mark")) {
                if (command.split(" ").length < 2) {
                    throw new DylanBotException("HEY no index specified for item to mark");
                }
                int idx = Integer.parseInt(command.split(" ")[1]);
                if (idx > tl.getSize() || idx < 0) {
                    throw new DylanBotException("HEY index requested is out of bounds");
                }
                response = tl.mark(idx);
            } else if (command.startsWith("unmark")) {
                if (command.split(" ").length < 2) {
                    throw new DylanBotException("HEY no index specified for item to mark");
                }
                int idx = Integer.parseInt(command.split(" ")[1]);
                if (idx > tl.getSize() || idx < 0) {
                    throw new DylanBotException("HEY index requested is out of bounds");
                }
                response = tl.unmark(idx);
            } else if (command.startsWith("todo")) {
                String desc = command.substring(5);
                if (desc.isEmpty()) {
                    throw new DylanBotException("HEY todo description cannot be empty!");
                }
                response = tl.createTodo(desc);
            } else if (command.startsWith("deadline")) {
                String[] inputArr = command.split("/by");
                String desc = inputArr[0].substring(9).trim();
                String deadlineStr = inputArr[1].trim();
                if (desc.isEmpty()) {
                    throw new DylanBotException("HEY deadline description cannot be empty!");
                }
                if (deadlineStr.isEmpty()) {
                    throw new DylanBotException("HEY deadline tasks need deadlines!");
                }
                response = tl.createDeadline(desc, deadlineStr);
            } else if (command.startsWith("event")) {
                String[] inputArr = command.split("/from|/to");
                String desc = inputArr[0].substring(6).trim();
                String fromStr = inputArr[1].trim();
                String toStr = inputArr[2].trim();
                if (desc.isEmpty()) {
                    throw new DylanBotException("HEY event description cannot be empty!");
                }
                if (fromStr.isEmpty()) {
                    throw new DylanBotException("HEY event tasks need starting dates!");
                }
                if (toStr.isEmpty()) {
                    throw new DylanBotException("HEY event tasks need ending dates!");
                }
                response = tl.createEvent(desc, fromStr, toStr);
            } else if (command.startsWith("delete")) {
                String[] inputArr = command.split(" ");
                int idx = Integer.parseInt(inputArr[1]);
                if (idx > tl.getSize() || idx < 0) {
                    throw new DylanBotException("HEY index requested is out of bounds");
                }
                response = tl.deleteTask(idx);
            } else {
                throw new DylanBotException("HEY invalid input! Try again!");
            }
            assert !response.isBlank() : "Response cannot be blank";
            return response;
        } catch (DylanBotException e) {
            return e.getMessage();
        }
    }
}
