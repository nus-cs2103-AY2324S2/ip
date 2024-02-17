package parser;

import task.TaskStorage;

/**
 * Acts as a ParseExecutionable interface is to handle the outcomes of parsing operations within the application.
 * Objects implementing of this interface encapsulate different types of actions into the TaskStorage,
 * such as creating tasks, executing changes, or indicating TaskErrors.
 */
public interface ParseExecutionable {
    /**
     * Executes the necessary action created from the parsed results.
     * To be overidden with the exact operation required from each Task.
     * @param taskStorage The storage space where the action will take place.
     */
    public String execute(TaskStorage taskStorage);
}
