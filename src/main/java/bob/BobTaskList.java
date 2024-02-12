package bob;

import java.util.ArrayList;
import java.util.Comparator;

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
        list.remove(taskId);
        storage.updateTaskList(list);
    }

    /**
     * Mark item done.
     *
     * @param item The ID of the item to be marked done.
     */
    private void markDone(int item) {
        list.get(item).updateStatus(true);
        storage.updateTaskList(list);
    }

    /**
     * Mark item undone.
     *
     * @param item The ID of the item to be marked undone.
     */
    private void markUndone(int item) {
        list.get(item).updateStatus(false);
        storage.updateTaskList(list);
    }

    /**
     * Mark tasks as done/undone based on user input.
     *
     * @param input User input when calling the command.
     */
    public String handleTaskMarking(String input) throws BobException, IndexOutOfBoundsException {

        String[] args = input.split("\\s+");
        int taskId = Integer.parseInt(args[1]) - 1;

        boolean isMissingTaskId = args.length < 2;
        boolean isInvalidTaskId = !(taskId < list.size()) || taskId < 0;

        if (isMissingTaskId || isInvalidTaskId) {
            throw new BobException(
                    BobErrorMessages.getValidTaskIdMessage(args[0]));
        }

        String response = "";
        String userCommand = args[0];

        if (userCommand.equals(BobParser.MARK_COMMAND)) {
            markDone(taskId);
            response += ui.getTaskDoneText() + "\r\n";
        } else if (userCommand.equals(BobParser.UNMARK_COMMAND)) {
            markUndone(taskId);
            response += ui.getTaskUndoneText() + "\r\n";
        }

        Task task = list.get(taskId);
        response += ui.getTaskMarkText(task);

        return response;
    }

    /**
     * Handle the creation of user tasks.
     *
     * @param input User input when calling the task creation command.
     */
    public String handleTaskCreation(String input) throws BobException {

        Task createdTask = null;

        String[] args = input.split("\\s+");
        String command = args[0];

        try {
            if (command.equals(BobParser.TODO_COMMAND)) {
                createdTask = createTodoTask(input);
            } else if (command.equals(BobParser.DEADLINE_COMMAND)) {
                createdTask = createDeadlineTask(input);
            } else if (command.equals(BobParser.EVENT_COMMAND)) {
                createdTask = createEventTask(input);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BobException(
                    BobErrorMessages.INVALID_COMMAND_USAGE);
        }

        assert createdTask != null; // A new task should exist.
        return ui.getTaskAddText(createdTask, list);
    }

    private Task createTodoTask(String input) throws BobException {

        boolean hasNoArgs = input.trim().length() == BobParser.TODO_COMMAND.length();

        if (hasNoArgs) {
            throw new BobException(
                    BobErrorMessages.getTodoExpectFormatMsg());
        }

        String description = input.substring(BobParser.TODO_COMMAND.length() + 1);

        return storage.addItem(new Task(description), list);
    }

    private Task createDeadlineTask(String input) throws BobException {

        boolean isInvalidFormat = !input.contains("/by");
        boolean hasNoArgs = input.trim().length() == BobParser.DEADLINE_COMMAND.length();

        if (isInvalidFormat || hasNoArgs) {
            throw new BobException(
                    BobErrorMessages.getDeadlineExpectFormatMsg());
        }

        // Remove the command itself from the input.
        input = input.substring(BobParser.DEADLINE_COMMAND.length() + 1);

        String[] split = input.split("/by");
        boolean hasInsufficientArgs = split.length < 2;

        if (hasInsufficientArgs) {
            throw new BobException(
                    BobErrorMessages.getDeadlineExpectFormatMsg());
        }

        return storage.addItem(new Deadline(split[0].substring(0, split[0].length() - 1),
                split[1].substring(1)), list);
    }

    private Task createEventTask(String input) throws BobException {

        boolean isInvalidFormat = !input.contains("/from") && !input.contains("/to");
        boolean hasNoArgs = input.trim().length() == BobParser.EVENT_COMMAND.length();

        if (isInvalidFormat || hasNoArgs) {
            throw new BobException(
                    BobErrorMessages.getEventExpectFormatMsg());
        }

        // Remove the command itself from the input.
        input = input.substring(BobParser.EVENT_COMMAND.length() + 1);

        String[] split = input.split("/");
        boolean hasInsufficientArgs = split.length < 3;

        if (hasInsufficientArgs) {
            throw new BobException(
                    BobErrorMessages.getEventExpectFormatMsg());
        }

        return storage.addItem(new Event(split[0].substring(0, split[0].length() - 1),
                split[1].substring(5), split[2].substring(3)), list);
    }

    /**
     * Handle the deletion of user tasks.
     *
     * @param input User input when calling the command.
     */
    public String handleTaskDeletion(String input) throws BobException, IndexOutOfBoundsException {

        String[] args = input.split("\\s+");
        int taskId = Integer.parseInt(args[1]) - 1;

        boolean isMissingTaskId = args.length < 2;
        boolean isInvalidTaskId = !(taskId < list.size()) || taskId < 0;

        if (isMissingTaskId || isInvalidTaskId) {
            throw new BobException(
                    BobErrorMessages.getValidTaskIdMessage(args[0]));
        }

        Task t = list.get(taskId);

        deleteTask(taskId);
        return ui.getTaskDeletionText(t, list);
    }

    /**
     * Handles the finding of tasks and listing found items.
     *
     * @param input User input when calling the command.
     */
    public String handleFindTask(String input) {

        String response = "";

        response += ui.getFindCommandText() + "\r\n";
        input = input.substring(BobParser.FIND_COMMAND.length() + 1);

        int seq = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.contains(input)) {
                Task task = list.get(i);
                response += (seq + 1) + "." + task.getType()
                        + task.getStatus() + " " + task + "\r\n";
                seq++;
            }
        }

        return response;
    }

    /**
     * Handles the sorting of tasks by alphabetical order.
     *
     * @param input User input when calling the command.
     * @return A feedback based for the sorting command.
     * @throws BobException
     */
    public String handleSortTasks(String input) throws BobException {

        String processedInput = input.toLowerCase();

        boolean isInvalidFormat = !processedInput.contains("desc")
                && !processedInput.contains("asc");
        boolean hasNoArgs = input.trim().length() == BobParser.SORT_COMMAND.length();

        if (isInvalidFormat || hasNoArgs) {
            throw new BobException(
                    BobErrorMessages.getSortExpectFormatMsg());
        }

        boolean isSortByAscending = processedInput.contains("asc");
        boolean isSortByDescending = processedInput.contains("desc");

        Comparator<Task> ascendingComparator = Comparator.comparing(a -> a.description);

        if (isSortByAscending) {
            list.sort(ascendingComparator);
        } else if (isSortByDescending) {
            list.sort(ascendingComparator.reversed());
        }

        return ui.getSortedMessage(isSortByAscending);
    }

    public String handleArchiveTask(String input) throws BobException, IndexOutOfBoundsException {

        String[] args = input.split("\\s+");
        int taskId = Integer.parseInt(args[1]) - 1;

        boolean isMissingTaskId = args.length < 2;
        boolean isInvalidTaskId = !(taskId < list.size()) || taskId < 0;

        if (isMissingTaskId || isInvalidTaskId) {
            throw new BobException(
                    BobErrorMessages.getValidTaskIdMessage(args[0]));
        }

        list.get(taskId).archiveTask();
        storage.updateTaskList(list);

        return ui.getArchivedMessage();
    }
}
