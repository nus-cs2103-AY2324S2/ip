package micromanager;

import javafx.application.Application;
import micromanager.graphics.Main;
import micromanager.tasks.Task;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Todo class represents a todo task in the task list.
     * It extends the Task class and provides methods to manage todo tasks.
     */
    public static class Todo extends Task {
        /**
         * Constructs a Todo object with the specified task description.
         *
         * @param taskDescription The description of the todo task.
         */
        public Todo(String taskDescription) {
            super(taskDescription);
        }

        /**
         * Converts the todo task to a string representation for saving to a file.
         *
         * @return A string representation of the todo task for saving to a file.
         */
        public String toFileString() {
            return String.format("T,%b,%s", this.isDone, this.taskDescription);
        }

        /**
         * Returns a string representation of the todo task.
         * Overrides the toString method in the Task class.
         *
         * @return A string representation of the todo task.
         */
        @Override
        public String toString() {
            return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.taskDescription);
        }
    }
}
