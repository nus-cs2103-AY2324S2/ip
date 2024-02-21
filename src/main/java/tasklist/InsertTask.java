package tasklist;

import java.util.List;

import exceptions.TaylorException;
import helper.IterateList;
import helper.TaskInsertion;
import helper.WordsSplit;
import tasks.Task;

/**
 * To add Task into the ArrayList
 */
public class InsertTask {
    enum Type {
        TODO, DEADLINE, EVENT
    }
    /**
     * No constructor needed
     */
    private InsertTask() {
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
    }

    /**
     * Execute inserting the tasks
     * @param input User input
     * @param taskList List containing the different list of tasks
     * @throws TaylorException empty description field / invalid task type input
     */
    public static String execInsertTask(String input, List<List<Task>> taskList) throws TaylorException {
        String response;
        String[] wordPartition = WordsSplit.separateWords(input, " ", true);

        String content;
        Type type;
        try {
            int contentsIdx = 1;
            content = WordsSplit.getWord(wordPartition, contentsIdx);

            int actionsIdx = 0;
            type = Type.valueOf(WordsSplit.getWord(wordPartition, actionsIdx).toUpperCase());
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("The description of the task is empty.");
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
            throw new TaylorException("Invalid Task Type");
        }

        return response;
    }
}
