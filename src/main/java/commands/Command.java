package commands;

import tasks.Task;
import tasks.TaskList;
import main.java.Ui;
import main.java.Storage;

public enum Command {
    BYE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            storage.saveTasks(tasks);
        }
    },
    YAP {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            tasks.yapTasks();
        }
    },
    MARK {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Extract task index from argument and mark the task as done
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            tasks.markTaskAsDone(index);
            storage.saveTasks(tasks);
        }
    },
    UNMARK {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Extract task index from argument and mark the task as not done
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            tasks.unmarkTaskAsDone(index);
            storage.saveTasks(tasks);
        }
    },
    ADD_TODO {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new tasks.ToDo task and add it to the task list
            Task task = tasks.initTask(message, "todo");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    ADD_DEADLINE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new tasks.Deadline task and add it to the task list
            Task task = tasks.initTask(message, "deadline");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    ADD_EVENT {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            // Create a new tasks.Event task and add it to the task list
            Task task = tasks.initTask(message, "event");
            tasks.addTasktoTaskList(task);
            ui.triggerAddMessage(task);
            storage.saveTasks(tasks);
        }
    },
    DELETE {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
            String[] inputs = message.split(" ");
            int index = Integer.parseInt(inputs[1]);
            Task task = tasks.removeTaskfromTaskList(index);
            ui.triggerDeleteMessage(task);
            storage.saveTasks(tasks);
        }
    };

    public void execute(TaskList tasks, Ui ui, Storage storage, String message) {
        throw new UnsupportedOperationException("This command does not take any arguments.");
    }


}
