package duke.ui;

import duke.storage.TaskList;

/**
 * The UI class handles the displaying of UI elements in
 * the application
 *
 * @author Ryan NgWH
 */
public abstract class Ui {
    /**
     * Displays the UI of the application
     */
    public abstract void startUI(TaskList taskList);
}
