package bytetalker.task;

import bytetalker.exception.ByteTalkerException;
import bytetalker.parser.Parser;
import bytetalker.storage.Storage;
import bytetalker.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void markTask(String[] splitMessage, Storage storage, Ui ui) {
        int index = Integer.parseInt(splitMessage[1]) - 1;
        try {
            this.tasks.get(index).setStatus(true);
            storage.storeTasks(this.tasks);
            ui.markTaskMsg(this.tasks.get(index));
        } catch (IOException e) {
            ui.showErrorMsgStoreTasks();
            this.tasks.get(index).setStatus(false);
        }
    }

    public void unmarkTask(String[] splitMessage, Storage storage, Ui ui) {
        int index = Integer.parseInt(splitMessage[1]) - 1;
        try {
            this.tasks.get(index).setStatus(false);
            storage.storeTasks(this.tasks);
            ui.unmarkTaskMsg(this.tasks.get(index));
        } catch (IOException e) {
            ui.showErrorMsgStoreTasks();
            this.tasks.get(index).setStatus(true);
        }
    }

    public void addTask(String[] splitMessage, Storage storage, Ui ui) {
        try {
            Task task = null;
            boolean isTodo = splitMessage[0].equals("todo");
            boolean isDeadline = splitMessage[0].equals("deadline");
            boolean isEvent = splitMessage[0].equals("event");
            if (isTodo) {
                task = addTodo(splitMessage);
            } else if (isDeadline) {
                task = addDeadline(splitMessage);
            } else if (isEvent) {
                task = addEvent(splitMessage);
            } else {
                throw new ByteTalkerException.UnsupportedTaskException("This is unsupported task");
            }
            try {
                if (task != null) {
                    this.tasks.add(task);
                    storage.storeTasks(this.tasks);
                    ui.addTaskMsg(task, this.tasks.size());
                } else {
                    System.out.println("    No task added");
                }
            } catch (IOException e) {
                ui.showErrorMsgStoreTasks();
                this.tasks.remove(this.tasks.size() - 1);
            }
        } catch (ByteTalkerException.UnsupportedTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please only enter the supported types of task.");
        }
    }

    public Todo addTodo(String[] splitMessage) {
        Todo task = null;
        try {
            ArrayList<String> parsedTodoInputs = Parser.parseTodoInput(splitMessage);
            if (parsedTodoInputs.get(0).isEmpty()) {
                throw new ByteTalkerException.TodoNoTaskException("No Task");
            }
            task = new Todo(parsedTodoInputs.get(0));
        } catch (ByteTalkerException.TodoNoTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please enter the task, too.");
        } catch (ByteTalkerException.TodoUnsupportedFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task.");
        }
        return task;
    }

    public Deadline addDeadline(String[] splitMessage) {
        Deadline task = null;
        try {
            ArrayList<String> parsedDeadlineInput = Parser.parseDeadlineInput(splitMessage);
            System.out.println(parsedDeadlineInput);
            if (parsedDeadlineInput.get(0).isEmpty() || parsedDeadlineInput.size() != 2) {
                throw new ByteTalkerException.DeadlineWrongFormatException("This is wrong format for deadline");
            }
            task = new Deadline(parsedDeadlineInput.get(0), Parser.parseDateTime(parsedDeadlineInput.get(1)));
        } catch (ByteTalkerException.DeadlineUnsupportedFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task");
        } catch (ByteTalkerException.DeadlineWrongFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task");
        }
        return task;
    }

    public Event addEvent(String[] splitMessage) {
        Event task = null;
        try {
            ArrayList<String> parsedEventInput = Parser.parseEventInput(splitMessage);
            if (parsedEventInput.size() != 3 || parsedEventInput.get(0).isEmpty()
                    || parsedEventInput.get(1).isEmpty()
                    || parsedEventInput.get(2).isEmpty()) {
                throw new ByteTalkerException.EventWrongFormatException("This is wrong format for event");
            }
            task = new Event(parsedEventInput.get(0), Parser.parseDateTime(parsedEventInput.get(1)),
                    Parser.parseDateTime(parsedEventInput.get(2)));
        } catch (ByteTalkerException.EventWrongFormatException e) {
            System.out.println("    " + e.getMessage() + ". Please use the correct format for each task");
        }
        return task;
    }

    public void deleteTask(int position, Storage storage, Ui ui) {
        Task task = this.tasks.get(position - 1);
        try {
            this.tasks.remove(position - 1);
            storage.storeTasks(this.tasks);
            ui.deleteTaskMsg(task, this.tasks.size());
        } catch (IOException e) {
            ui.showErrorMsgStoreTasks();
            this.tasks.add(task);
        }
    }
}
