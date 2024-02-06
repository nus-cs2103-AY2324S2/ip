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
                task = addDescription(splitMessage);
            } else if (isEvent) {
                task = addEvent(splitMessage);
            } else {
                throw new ByteTalkerException.UnsupportedTaskException("This is unsupported task");
            }
            try {
                if (task != null) {
                    this.tasks.add(task);
                }
                storage.storeTasks(this.tasks);
                ui.addTaskMsg(task, this.tasks.size());
            } catch (IOException e) {
                ui.showErrorMsgStoreTasks();
                this.tasks.remove(this.tasks.size() - 1);
            }
        } catch (ByteTalkerException.UnsupportedTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please only enter the supported types of task.");
        }
    }

    public Todo addTodo(String[] splitMessage) {
        try {
            if (splitMessage.length == 1) {
                throw new ByteTalkerException.TodoNoTaskException("No bytetalker.task.Task");
            }
            String content = " ";
            for (int i = 1; i < splitMessage.length; i++) {
                content = content + splitMessage[i] + " ";
            }
            content = content.strip();
            return new Todo(content);
        } catch (ByteTalkerException.TodoNoTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please enter the task, too.");
            return null;
        }
    }

    public Deadline addDescription(String[] splitMessage) {
        String content = " ";
        String deadline = "";
        for (int i = 1; i < splitMessage.length; i++) {
            boolean isContentFilled = splitMessage[i].equals("/by");
            if (isContentFilled) {
                content = deadline.strip();
                deadline = "";
            } else {
                deadline = deadline + splitMessage[i] + " ";
            }
        }
        deadline = deadline.strip();
        return new Deadline(content, Parser.parseDateTime(deadline));
    }

    public Event addEvent(String[] splitMessage) {
        String content = " ";
        String from = "";
        String to = "";
        for (int i = 1; i < splitMessage.length; i++) {
            boolean isContentFilled = splitMessage[i].equals("/from");
            boolean isFromFilled = splitMessage[i].equals("/to");
            if (isContentFilled) {
                content = to.strip();
                to = "";
            } else if (isFromFilled) {
                from = to.strip();
                to = "";
            } else {
                to += splitMessage[i] + " ";
            }
        }
        to = to.strip();
        return new Event(content, Parser.parseDateTime(from), Parser.parseDateTime(to));
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
