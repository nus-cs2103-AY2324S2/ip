package alfred.util;

import alfred.task.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    private Ui ui;
    private TaskList tasks;

    public CommandManager(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Searches for a specific string within an array of strings and returns its index.
     *
     * @param checker The string to find within the array.
     * @param list    The array of strings to search through.
     * @return The index of the string if found, or -1 if not found.
     */
    public int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }
    public ArrayList<String> byeCommand() {
        return this.ui.bye();
    }

    /**
     * Displays the current list of tasks.
     *
     * @return A list of strings representing the tasks to be displayed.
     */
    public ArrayList<String> showListCommand() {
        return this.ui.showList(tasks.showList());
    }

    /**
     * Marks a specified task as done.
     *
     * @param index The index of the task in the task list to be marked as done.
     * @return A message list confirming the task has been marked as done.
     */
    public ArrayList<String> markTaskCommand(int index) {
        Task curr = tasks.getTask(index);
        curr.makeDone();
        return ui.markTask(curr);
    }

    /**
     * Marks a specified task as not done.
     *
     * @param index The index of the task in the task list to be marked as not done.
     * @return A message list confirming the task has been marked as not done.
     */
    public ArrayList<String> unmarkTaskCommand(int index) {
        Task curr = tasks.getTask(index);
        curr.makeUndone();
        return ui.unmarkTask(curr);
    }

    /**
     * Deletes a specified task from the task list.
     *
     * @param index The index of the task in the task list to be deleted.
     * @return A message list confirming the task has been deleted.
     */
    public ArrayList<String> deleteTaskCommand(int index) {
        return ui.delete(index, tasks);
    }

    /**
     * Finds tasks that match the specified keyword(s).
     *
     * @param reqParts The command parts containing the keywords to search for.
     * @return A list of tasks matching the search criteria or an error message if no keywords are provided.
     */
    public ArrayList<String> findTaskCommand(String[] reqParts) {
        if (reqParts.length < 2) {
            return ui.showError("What do you want me to find? Please specify");
        }
        String keyword = String.join(" ", Arrays.copyOfRange(reqParts, 1, reqParts.length));
        return ui.showFilteredList(keyword, tasks);
    }

    /**
     * Creates a new Todo task.
     *
     * @param reqParts The command parts containing the description of the Todo task.
     * @return The newly created Todo task.
     * @throws TaskException if the description is empty.
     */
    public Todo createTodoCommand(String[] reqParts) throws TaskException{
        if (reqParts.length < 2) {
            throw new TaskException("What do you want to do? Description of todo cannot be empty.");
        }
        String desc = String.join(" ", Arrays.copyOfRange(reqParts, 1, reqParts.length));
        Todo current = new Todo(desc);
        return current;
    }

    /**
     * Creates a new Deadline task.
     *
     * @param reqParts The command parts containing the description and the deadline.
     * @return The newly created Deadline task.
     * @throws TaskException if the deadline is not specified.
     */
    public Deadline createDeadlineCommand(String[] reqParts) throws TaskException{
        if (!Arrays.asList(reqParts).contains("/by")) {
            throw new TaskException("Please specify when is the deadline.");
        }
        int byIndex = finder("/by", reqParts);
        String desc = String.join(" ", Arrays.copyOfRange(reqParts, 1, byIndex));
        String time = String.join(" ", Arrays.copyOfRange(reqParts, byIndex + 1, reqParts.length));
        Deadline current = new Deadline(desc, time);
        return current;
    }

    /**
     * Creates a new Event task.
     *
     * @param reqParts The command parts containing the description, start time, and end time of the event.
     * @return The newly created Event task.
     * @throws TaskException if the event timeframe is not properly specified.
     */
    public Event createEventCommand(String[] reqParts) throws TaskException{
        if (!Arrays.asList(reqParts).contains("/from") && !Arrays.asList(reqParts).contains("/to")){
            throw new TaskException("Please specify when the event timeframe");
        } else if (!Arrays.asList(reqParts).contains("/from")){
            throw new TaskException("Please specify when the event starts.");
        } else if (!Arrays.asList(reqParts).contains("/to")){
            throw new TaskException("Please specify when the event ends.");
        }

        int fromIndex = finder("/from", reqParts);
        int toIndex = finder("/to", reqParts);
        String desc = String.join(" ", Arrays.copyOfRange(reqParts, 1, fromIndex));
        String start = String.join(" ", Arrays.copyOfRange(reqParts, fromIndex + 1, toIndex));
        String end = String.join(" ", Arrays.copyOfRange(reqParts, toIndex + 1, reqParts.length));

        Event current = new Event(desc, start, end);
        return current;
    }

    /**
     * Snoozes a task by a day.
     *
     * @param index The index of the task in the task list to be snoozed.
     * @return A message list confirming the task has been snoozed or an error message if the task cannot be snoozed.
     */
    public ArrayList<String> snoozeCommand(int index){
        Task curr = tasks.getTask(index);
        if (curr instanceof Todo) {
            ArrayList<String> errorList = new ArrayList<String>(Arrays.asList("Todo task cannot be snoozed"));
            return errorList;
        }
        curr.snooze();
        return ui.snooze(curr);
    }

    /**
     * Postpones a task to a later date.
     *
     * @param index The index of the task in the task list to be postponed.
     * @param days The number of days to postpone the task.
     * @return A message list confirming the task has been postponed or an error message if the task cannot be postponed.
     */
    public ArrayList<String> postponeCommand(int index, int days){
        Task curr = tasks.getTask(index);
        if (curr instanceof Todo) {
            ArrayList<String> errorList = new ArrayList<String>(Arrays.asList("Todo task cannot be snoozed"));
            return errorList;
        }
        curr.postpone(days);
        return ui.postpone(curr, days);
    }

    /**
     * Reschedules an event or deadline to a new time.
     *
     * @param reqParts The command parts containing the new schedule information.
     * @return A message list confirming the task has been rescheduled or an error message if the rescheduling is not possible.
     */
    public ArrayList<String> rescheduleCommand(String[] reqParts){
        int position;
        try {
            position = Integer.parseInt(reqParts[0]) - 1;
        } catch (NumberFormatException e) {
            return ui.showError("Please reschedule a valid task from the list");
        }
        Task curr = tasks.getTask(position);
        if (curr instanceof Todo){
            return ui.showError("Todo cannot be rescheduled");
        }
        if (curr instanceof Event) {
            if (!Arrays.asList(reqParts).contains("/from") && !Arrays.asList(reqParts).contains("/to")){
                return ui.showError("Please specify the new event timeframe");
            } else if (!Arrays.asList(reqParts).contains("/from")){
                return ui.showError("Please specify when the new start time.");
            } else if (!Arrays.asList(reqParts).contains("/to")){
                return ui.showError("Please specify when the new end time.");
            }

            int fromIndex = finder("/from", reqParts);
            int toIndex = finder("/to", reqParts);

            String start = String.join(" ", Arrays.copyOfRange(reqParts, fromIndex + 1, toIndex));
            String end = String.join(" ", Arrays.copyOfRange(reqParts, toIndex + 1, reqParts.length));

            curr.reschedule(start, end);
            return ui.reschedule(curr);
        }

        String end = String.join(" ", Arrays.copyOfRange(reqParts, 1, reqParts.length));
        curr.reschedule(end);
        return ui.reschedule(curr);
    }
}
