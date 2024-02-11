package bob;

import java.util.ArrayList;

/**
 * Class that handles the task list of the chat bot.
 */
public class BobTaskList {

    private ArrayList<Task> list;
    private BobStorage storage;
    private BobUI ui;

    /**
     * Constructor of BobTaskList.
     *
     * @param storage Chatbot's storage component.
     * @param ui Chatbot's UI component.
     */
    public BobTaskList(BobStorage storage, BobUI ui) {
        this.storage = storage;
        this.ui = ui;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    private void deleteTask(int taskId) {
        this.list.remove(taskId);
        this.storage.updateTaskList(this.list);
    }

    /**
     * Mark item done.
     *
     * @param item The ID of the item to be marked done.
     */
    private void markDone(int item) {
        this.list.get(item).updateStatus(true);
        this.storage.updateTaskList(this.list);
    }

    /**
     * Mark item undone.
     *
     * @param item The ID of the item to be marked undone.
     */
    private void markUndone(int item) {
        this.list.get(item).updateStatus(false);
        this.storage.updateTaskList(this.list);
    }

    /**
     * Mark tasks as done/undone based on user input.
     *
     * @param input User input when calling the command.
     */
    public String handleTaskMarking(String input) throws BobException {

        String response = "";

        String[] args = input.split("\\s+");
        if (args.length < 2) {
            throw new BobException("The command " + args[0] + " requires a task ID.");
        }

        int taskId = Integer.parseInt(args[1]) - 1;

        if (!(taskId < this.list.size()) || taskId < 0) {
            throw new BobException("The command " + args[0] + " requires a valid ID.");
        }

        String userCommand = args[0];

        if (userCommand.equals(BobParser.MARK_COMMAND)) {
            this.markDone(taskId);
            response += this.ui.getTaskDoneText() + "\r\n";
        } else {
            this.markUndone(taskId);
            response += this.ui.getTaskUndoneText() + "\r\n";
        }

        Task task = this.list.get(taskId);
        response += this.ui.getTaskMarkText(task);

        return response;
    }

    /**
     * Handle the creation of user tasks.
     *
     * @param input User input when calling the task creation command.
     */
    public String handleTaskCreation(String input) throws BobException {

        Task t = null;

        try {
            if (input.contains(BobParser.TODO_COMMAND)) {

                if (input.trim().length() == BobParser.TODO_COMMAND.length()) {
                    throw new BobException("The command " + BobParser.TODO_COMMAND + " requires a task description.");
                }

                String description = input.substring(BobParser.TODO_COMMAND.length() + 1);

                t = this.storage.addItem(new Task(description), this.list);
            }

            if (input.contains(BobParser.DEADLINE_COMMAND)) {

                if (!input.contains("/by")) {
                    throw new BobException("The command " + BobParser.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                if (input.length() == BobParser.DEADLINE_COMMAND.length()) {
                    throw new BobException("The command " + BobParser.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                input = input.substring(BobParser.DEADLINE_COMMAND.length() + 1);

                String[] split = input.split("/by");
                if (split.length < 2) {
                    throw new BobException("The command " + BobParser.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                t = this.storage.addItem(new Deadline(split[0].substring(0, split[0].length() - 1),
                        split[1].substring(1)), this.list);
            }

            if (input.contains(BobParser.EVENT_COMMAND)) {

                if (!input.contains("/from") && !input.contains("/to")) {
                    throw new BobException("The command " + BobParser.EVENT_COMMAND
                            + " requires a task description, a start date, and an end date.");
                }

                if (input.length() == BobParser.EVENT_COMMAND.length()) {
                    throw new BobException("The command " + BobParser.EVENT_COMMAND
                            + " requires a task description, a start date, and an end date.");
                }

                input = input.substring(BobParser.EVENT_COMMAND.length() + 1);

                String[] split = input.split("/");

                if (split.length < 3) {
                    throw new BobException("The command " + BobParser.EVENT_COMMAND
                            + " requires a task description, a start date, and an end date.");
                }

                t = this.storage.addItem(new Event(split[0].substring(0, split[0].length() - 1),
                        split[1].substring(5), split[2].substring(3)), this.list);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BobException("Incorrect usage of command.");
        }

        assert t != null; // Task should be populated by now.

        return this.ui.getTaskAddText(t, this.list);
    }

    /**
     * Handle the deletion of user tasks.
     *
     * @param input User input when calling the command.
     */
    public String handleTaskDeletion(String input) throws BobException {

        String[] args = input.split("\\s+");

        if (args.length < 2) {
            throw new BobException("The command " + args[0] + " requires a task ID.");
        }

        int taskId = Integer.parseInt(args[1]) - 1;

        if (!(taskId < this.list.size()) || taskId < 0) {
            throw new BobException("The command " + args[0] + " requires a valid ID.");
        }

        Task t = this.list.get(taskId);

        this.deleteTask(taskId);
        return this.ui.getTaskDeletionText(t, this.list);
    }

    /**
     * Handles the finding of tasks and listing found items.
     *
     * @param input User input when calling the command.
     */
    public String handleFindTask(String input) throws BobException {

        String response = "";

        response += this.ui.getFindCommandText() + "\r\n";
        input = input.substring(BobParser.FIND_COMMAND.length() + 1);

        int seq = 0;

        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).description.contains(input)) {
                Task task = this.list.get(i);
                response += (seq + 1) + "." + task.getType()
                        + task.getStatus() + " " + task + "\r\n";
                seq++;
            }
        }

        return response;
    }
}
