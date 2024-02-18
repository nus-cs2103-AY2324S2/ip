package scribbles.parser;

import scribbles.sorter.Sorter;
import scribbles.storage.Storage;
import scribbles.task.Deadline;
import scribbles.task.Event;
import scribbles.task.Todo;
import scribbles.tasklist.TaskList;
import scribbles.ui.Ui;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * This class deals with the subsequent actions performed by Scribbles based on the first command in the user's input.
 */
public class CommandResponder {

    private Ui ui;

    /**
     * Constructs a new CommandResponder object
     */
    public CommandResponder() {
        ui = new Ui();
    }

    /**
     * Carries out the actions for the user input "list" by listing all the user's task in the task list.
     *
     * @param taskList list containing the user's tasks.
     * @return output responds to the command
     */
    public String respondToList(TaskList taskList) {
        return ui.listTasks(taskList);
    }

    /**
     * Carries out the actions for the user input "mark" by marking the task at the given index.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToMark(Parser parsedInput, TaskList taskList, Storage storage) {
        try {
            int index = parsedInput.getIndex();
            taskList.get(index - 1).markComplete();
            storage.saveFileData(taskList);
            return ui.printMarkCompletedMessage(index, taskList);
        } catch (IndexOutOfBoundsException e) {
            return ui.printInvalidIndexMessage(taskList);
        } catch (FileNotFoundException e) {
            return ui.printFileNotFoundMessage();
        }
    }

    /**
     * Carries out the actions for the user input "unmark" by unmarking the task at the given index.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToUnmark(Parser parsedInput, TaskList taskList, Storage storage) {
        try {
            int index = parsedInput.getIndex();
            taskList.get(index - 1).markIncomplete();
            storage.saveFileData(taskList);
            return ui.printMarkIncompleteMessage(index, taskList);
        } catch (IndexOutOfBoundsException e) {
            return ui.printInvalidIndexMessage(taskList);
        } catch (FileNotFoundException e) {
            return ui.printFileNotFoundMessage();
        }
    }

    /**
     * Carries out the actions for the user input "to-do" which adds a to-do task to the user's task list.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToTodo(Parser parsedInput, TaskList taskList, Storage storage) {
        try {
            String description = parsedInput.getTodoDescription();
            taskList.addTask(new Todo(description, false));
            storage.saveFileData(taskList);
            return ui.confirmTaskAddition(taskList);
        } catch (IndexOutOfBoundsException e) {
            return ui.printTaskMissingInformationMessage();
        } catch (FileNotFoundException e) {
            return ui.printFileNotFoundMessage();
        }
    }

    /**
     * Carries out the actions for the user input "deadline" which adds a deadline task to the user's task list.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToDeadline(Parser parsedInput, TaskList taskList, Storage storage) {
        if (parsedInput.isMissingDeadlineInformation()) {
            return ui.printTaskMissingInformationMessage();
        } else {
            try {
                String description = parsedInput.getDeadlineDescription();
                LocalDateTime by = parsedInput.getDeadlineBy();
                taskList.addTask(new Deadline(description, false, by));
                storage.saveFileData(taskList);
                return ui.confirmTaskAddition(taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printTaskMissingInformationMessage();
            } catch (DateTimeParseException e) {
                return ui.printWrongDateTimeFormatMessage();
            } catch (FileNotFoundException e) {
                return ui.printFileNotFoundMessage();
            }
        }
    }

    /**
     * Carries out the actions for the user input "event" which adds a event task to the user's task list.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToEvent(Parser parsedInput, TaskList taskList, Storage storage) {
        if (parsedInput.isInvalidEvent()) {
            return ui.printTaskMissingInformationMessage();
        } else {
            try {
                String description = parsedInput.getEventDescription();
                LocalDateTime start = parsedInput.getStartDateTime();
                LocalDateTime end = parsedInput.getEndDateTime();

                if (parsedInput.isInvalidStartAndEnd(start, end)) {
                    return ui.printEventStartAfterEnd();
                }

                taskList.addTask(new Event(description, false, start, end));
                storage.saveFileData(taskList);
                return ui.confirmTaskAddition(taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printTaskMissingInformationMessage();
            } catch (DateTimeParseException e) {
                return ui.printWrongDateTimeFormatMessage();
            } catch (FileNotFoundException e) {
                return ui.printFileNotFoundMessage();
            }
        }
    }

    /**
     * Carries out the actions for the user input "delete" which deletes a task from the task list at a given index.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToDelete(Parser parsedInput, TaskList taskList, Storage storage) {
        try {
            int index = parsedInput.getIndex();
            String taskRemoved = taskList.get(index - 1).toString();
            taskList.deleteTask(index - 1);
            storage.saveFileData(taskList);
            return ui.printTaskDeletionMessage(taskRemoved, taskList);
        } catch (IndexOutOfBoundsException e) {
            return ui.printInvalidIndexMessage(taskList);
        } catch (FileNotFoundException e) {
            return ui.printFileNotFoundMessage();
        }
    }

    /**
     * Carries out the actions for the user input "find" which lists all the tasks containing the given keyword
     * in the input.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @return output responds to the command
     */
    public String respondToFind(Parser parsedInput, TaskList taskList) {
        try {
            String keyword = parsedInput.getFindKeyword();
            return ui.printTasksWithKeyword(keyword, taskList);
        } catch (IndexOutOfBoundsException e) {
            return ui.printMissingKeywordMessage();
        }
    }

    /**
     * Carries out the actions for the user input "sortBy" which sorts the tasks according the the order specified by
     * the user in the input.
     *
     * @param parsedInput the parsed user input
     * @param taskList list containing the user's tasks.
     * @param storage storage that deals with the saving and loading of the user's task
     * @return output responds to the command
     */
    public String respondToSortBy(Parser parsedInput, TaskList taskList, Storage storage) {
        try {
            String order = parsedInput.getSortingOrder();
            Sorter sort = new Sorter();
            sort.sortList(taskList, order);
            storage.saveFileData(taskList);
            return ui.printTasksSortedMessage();
        } catch (IndexOutOfBoundsException e) {
            return ui.printOrderNotFoundMessage();
        } catch(IllegalArgumentException e) {
            return ui.printOrderNotFoundMessage();
        } catch (FileNotFoundException e) {
            return ui.printFileNotFoundMessage();
        }
    }

}
