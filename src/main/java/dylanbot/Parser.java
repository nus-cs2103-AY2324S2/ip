package dylanbot;

// deals with making sense of the user command
public class Parser {
    private Ui ui;
    private TaskList tl;

    public Parser(Ui ui, TaskList tl) {
        this.ui = ui;
        this.tl = tl;
    }
    public void process(String command) throws DylanBotException {
        try {
            if (command.equals("list")) {
                if (tl.isEmpty()) {
                    throw new DylanBotException("No tasks to list right now! Add something first la");
                }
                ui.displayTasks(tl);
            } else if (command.startsWith("mark")) {
                if (command.split(" ").length < 2) {
                    throw new DylanBotException("HEY no index specified for item to mark");
                }
                int idx = Integer.parseInt(command.split(" ")[1]);
                if (idx > tl.getSize() || idx < 0) {
                    throw new DylanBotException("HEY index requested is out of bounds");
                }
                tl.mark(idx);
            } else if (command.startsWith("unmark")) {
                if (command.split(" ").length < 2) {
                    throw new DylanBotException("HEY no index specified for item to mark");
                }
                int idx = Integer.parseInt(command.split(" ")[1]);
                if (idx > tl.getSize() || idx < 0) {
                    throw new DylanBotException("HEY index requested is out of bounds");
                }
                tl.unmark(idx);
            } else if (command.startsWith("todo")) {
                String desc = command.substring(5);
                if (desc.isEmpty()) {
                    throw new DylanBotException("HEY todo description cannot be empty!");
                }
                tl.createTodo(desc);
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
                tl.createDeadline(desc, deadlineStr);
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
                tl.createEvent(desc, fromStr, toStr);
            } else if (command.startsWith("delete")) {
                String[] inputArr = command.split(" ");
                int idx = Integer.parseInt(inputArr[1]);
                if (idx > tl.getSize() || idx < 0) {
                    throw new DylanBotException("HEY index requested is out of bounds");
                }
                tl.deleteTask(idx);
            }
        } catch (DylanBotException e) {
            ui.displayError(e);
        }
    }
}
