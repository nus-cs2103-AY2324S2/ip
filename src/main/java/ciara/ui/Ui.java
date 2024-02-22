package ciara.ui;

import ciara.storage.TaskList;

/**
 * The UI class handles the displaying of UI elements in
 * the application
 *
 * @author Ryan NgWH
 */
public abstract class Ui {
    /**
     * Displays the UI of the application
     *
     * @param taskList Tasklist to use for the application
     * @param args     Arguments for the application
     */
    public abstract void startUI(TaskList taskList, String[] args);
}
