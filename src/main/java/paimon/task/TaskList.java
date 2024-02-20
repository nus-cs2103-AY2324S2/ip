package paimon.task;

import paimon.exception.ChatBotParameterException;
import paimon.parser.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Iterable<Task> {
    protected List<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add task to List of Task.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns an iterator over elements of type {@code duke.task.Task}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Add a ToDo to List of Task.
     * @param description String name of task.
     * @param isDone Status of the task (Done or Undone).
     * @return Task that was added successfully.
     */
    public Task addToDo(String description, boolean isDone) {
        ToDo toDo = new ToDo(description, isDone);
        this.addTask(toDo);
        return toDo;
    }

    /**
     * Add a ToDo to List of Task.
     * @param parameters String that contain all necessary fields to create a ToDo for adding.
     * @return Task that was added successfully.
     * @throws ChatBotParameterException when parameters String lack information to create a ToDo.
     */
    public Task addToDo(String parameters) throws ChatBotParameterException {
        String[] parametersArr = Parser.parseToDo(parameters);
        assert parametersArr.length == 1: "params length for todo must always be 1";
        return this.addToDo(parametersArr[0], false);
    }

    /**
     * Add a Deadline to List of Task.
     * @param description String name of deadline.
     * @param by String represents DateTime the deadline need to be done by.
     * @param isDone Status of the task (Done or Undone).
     * @return Task that was added successfully.
     * @throws ChatBotParameterException when String by is in wrong format.
     */
    public Task addDeadline(String description, String by, boolean isDone) throws ChatBotParameterException {
        // Parse String into LocalDateTime of specific formats
        LocalDateTime byDateTime = Parser.parseDateTime(by);
        Deadline deadline = new Deadline(description, byDateTime, isDone);
        this.addTask(deadline);
        return deadline;
    }

    /**
     * Add a Deadline to List of Task.
     * @param parameters String that contain all necessary fields to create a Deadline for adding.
     * @return Task that was added successfully.
     * @throws ChatBotParameterException when parameters String lack information to create a Deadline.
     */
    public Task addDeadline(String parameters) throws ChatBotParameterException {
        String[] parametersArr = Parser.parseDeadline(parameters);
        assert parametersArr.length == 2: "params length for deadline must always be 2";
        return this.addDeadline(parametersArr[0], parametersArr[1], false);

    }

    /**
     * Add an Event to List of Task.
     * @param parameters String that contain all necessary fields to create a Event for adding.
     * @return Task that was added successfully.
     * @throws ChatBotParameterException when parameters String lack information to create an Event.
     */
    public Task addEvent(String parameters) throws ChatBotParameterException {
        String[] parametersArr = Parser.parseEvent(parameters);
        assert parametersArr.length == 3: "params length for event must always be 3";
        return this.addEvent(parametersArr[0], parametersArr[1], parametersArr[2], false);
    }

    /**
     * Add an Event to List of Task.
     * @param description String name of event.
     * @param from String when the event starts.
     * @param to String when the event ends.
     * @param isDone Status of the event (Done or Undone).
     * @return Task that was added successfully.
     */
    public Task addEvent(String description, String from, String to, boolean isDone) {
        Event event = new Event(description, from, to, isDone);
        this.addTask(event);
        return event;
    }

    /**
     * Number of tasks in TaskList.
     * @return int Number of tasks in TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Mark a task inside TaskList to be Done.
     * @param parameters String that contain task number to be marked.
     * @return Task that was marked successfully.
     * @throws ChatBotParameterException when task number out of bound or attempting to mark a Done Task.
     */
    public Task markTaskAsDone(String parameters) throws ChatBotParameterException {

        Task taskToBeMarked = getTask(parameters);

        if (taskToBeMarked.isDone()) {
            throw new ChatBotParameterException("This task is already marked done!");
        }

        taskToBeMarked.markDone();
        return taskToBeMarked;
    }

    private Task getTask(String parameters) throws ChatBotParameterException {
        int taskNumber = Parser.parseInteger(parameters);

        Task taskToBeMarked;
        try {
            taskToBeMarked = this.tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotParameterException("The task does not exists in the task list.");
        }

        return taskToBeMarked;
    }

    /**
     * Mark a task inside TaskList to be Undone
     * @param parameters String that contain task number to be unmarked.
     * @return Task that was unmarked successfully.
     * @throws ChatBotParameterException when task number out of bound or attempting to mark an Undone Task.
     */
    public Task markTaskAsUndone(String parameters) throws ChatBotParameterException {

        Task taskToBeMarked = getTask(parameters);

        if (!taskToBeMarked.isDone()) {
            throw new ChatBotParameterException("This task is already marked undone!");
        }

        taskToBeMarked.markUndone();
        return taskToBeMarked;
    }

    /**
     * Delete a task inside TaskList.
     * @param parameters String that contain task number to be deleted.
     * @return Task that was deleted successfully.
     * @throws ChatBotParameterException when task number out of bound.
     */
    public Task deleteTask(String parameters) throws ChatBotParameterException {
        int taskNumber = Parser.parseInteger(parameters);

        Task taskToBeDeleted;
        try {
            taskToBeDeleted = this.tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotParameterException("The task does not exists in the task list.");
        }
        assert taskToBeDeleted != null: "Null value must have been thrown in exception";

        return taskToBeDeleted;
    }

    /**
     * retrieve a sub List containing all task that contains keyword in its description.
     * @param keyword String queries to be searched.
     * @return List of Task of which description contains the keyword.
     * @throws ChatBotParameterException when keyword is empty or only whitespaces.
     */
    public List<Task> findFromKeyword(String keyword) throws ChatBotParameterException {
        // Check if keyword is empty or only whitespaces
        if (keyword.isEmpty() || keyword.trim().isEmpty()) {
            throw new ChatBotParameterException("Find Keyword cannot be all whitespaces or empty");
        }

        assert keyword.isEmpty() && keyword.contains(" "): "Keyword must have been trimmed";

        // Filter using Stream, and match String that only has the exact substring given
        return this.tasks.stream().filter(task -> task.hasKeyword(keyword)).collect(Collectors.toList());
    }

    public void clearTaskList() {
        this.tasks.clear();
    }

    public void updateTaskList(TaskList anotherTaskList) {
        this.tasks = anotherTaskList.tasks;
    }
}
