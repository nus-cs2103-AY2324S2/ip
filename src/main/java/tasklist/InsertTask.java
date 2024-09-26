package tasklist;

import java.util.List;

import exceptions.TaylorException;
import taskhelper.IterateList;
import taskhelper.TaskInsertion;
import taskhelper.WordsSplit;
import tasks.Task;

/**
 * Adds Task into the ArrayList.
 */
public class InsertTask {
    enum Type {
        TODO, DEADLINE, EVENT
    }
    /**
     * No constructor needed.
     */
    private InsertTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Executes tasks insertion.
     *
     * @param input User input
     * @param taskList List containing the different list of tasks
     * @throws TaylorException empty description field / invalid task type input
     */
    public static StringBuilder execInsertTask(String input, List<List<Task>> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = WordsSplit.separateWords(input, " ", true);

        String content;
        Type type;
        try {
            int contentsIdx = 1;
            content = WordsSplit.getWord(wordPartition, contentsIdx);

            int actionsIdx = 0;
            type = Type.valueOf(WordsSplit.getWord(wordPartition, actionsIdx).toUpperCase());
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("\n"
                    + "For in the void, there's room to grow,\n"
                    + "And from the ashes, new beginnings will sow\n"
                    + "==============================\n"
                    + "The description of the task is empty.");
        }

        List<Task> deadlineList = IterateList.retrieveList(taskList, "DEADLINE");
        List<Task> eventList = IterateList.retrieveList(taskList, "EVENT");
        List<Task> todoList = IterateList.retrieveList(taskList, "TODO");

        switch (type) {
        case TODO:
            response = TaskInsertion.todoTask(content, todoList);
            break;
        case DEADLINE:
            response = TaskInsertion.deadlineTask(content, deadlineList);
            break;
        case EVENT:
            response = TaskInsertion.eventTask(content, eventList);
            break;
        default:
            throw new TaylorException("\n"
                    + "Letters dance in the moon's soft glow,\n"
                    + "A language lost, I long to know.\n"
                    + "==============================\n"
                    + "Invalid Task Type");
        }

        return response;
    }
}
