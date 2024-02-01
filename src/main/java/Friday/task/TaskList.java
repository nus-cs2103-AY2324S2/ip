package Friday.task;

import java.util.ArrayList;
import java.util.List;
import Friday.ui.Ui;
import Friday.storage.Storage;

public class TaskList {
    private List<Task> tasks ;
    private Ui ui;
    private Storage storage;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    public void displayTasks() {
        for (Task t : tasks) {
            ui.displayMessage(t.toString() + System.lineSeparator());
        }
    }

    public int length() {
        return tasks.size();
    }

    public void markTask(String userInput) {
        String num = userInput.substring(4).trim();
        if (num.isEmpty()) {
            ui.displayMessage("Error. Unknown task number to be marked.");
        } else {
            int id = Integer.parseInt(num);
            if (id > tasks.size()) {
                ui.displayMessage("Error. Task number does not exist.");
            } else {
                ui.displayMessage("Nice! I have marked this task as done: ");
                tasks.get(id - 1).markAsDone();
                ui.displayMessage(tasks.get(id - 1).toString());
            }
        }
    }

    public void unmarkTask(String userInput) {
        String num = userInput.substring(6).trim();
        if (num.isEmpty()) {
            ui.displayMessage("Error. Unknown task number to be unmarked.");
        } else {
            int id = Integer.parseInt(num);
            if (id > tasks.size()) {
                ui.displayMessage("Error. Task number does not exist.");
            } else {
                ui.displayMessage("Okay. I have unmarked this task: ");
                tasks.get(id - 1).markAsUndone();
                ui.displayMessage(tasks.get(id - 1).toString());
            }
        }
    }

    public void deleteTask(String userInput) {
        String num = userInput.substring(6).trim();
        if (num.isEmpty()) {
            ui.displayMessage("Error. Unknown task number to be deleted.");
        } else {
            int id = Integer.parseInt(num);
            if (id > tasks.size()) {
                ui.displayMessage("Error. Task number does not exist.");
            } else {
                ui.displayMessage("Okay. I have deleted this task: ");
                ui.displayMessage(tasks.get(id - 1).toString());
                tasks.remove(id - 1);
                ui.displayMessage(displayCounter(tasks.size()));
            }
        }
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            ui.displayMessage("Error. No such task in the task list.");
            return null;
        }
    }

    public void addTaskFromData(Task task) {
        tasks.add(task);
    }

    public Todo addTodo(String userInput) {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            ui.displayMessage("Error. Cannot add empty Todo.");
            return null;
        } else {
            ui.displayMessage("Got it. I have added this task: ");
            Todo t = new Todo(description);
            ui.displayMessage(t.toString());
            tasks.add(t);
            ui.displayMessage(displayCounter(tasks.size()));
            return t;
        }
    }

    public Deadline addDeadline(String userInput) {
        int id = userInput.indexOf("/by");
        String description = userInput.substring(8, id).trim();
        if (description.isEmpty()) {
            ui.displayMessage("Error. Cannot add empty Deadline.");
            return null;
        } else {
            String by = userInput.substring(id + 3).trim();
            if (by.isEmpty()) {
                ui.displayMessage("Error. Please indicate end time.");
                return null;
            } else {
                ui.displayMessage("Got it. I have added this task: ");
                Deadline d = new Deadline(description, by);
                ui.displayMessage(d.toString());
                tasks.add(d);
                ui.displayMessage(displayCounter(tasks.size()));
                return d;
            }
        }
    }

    public Event addEvent(String userInput) {
        int idFrom = userInput.indexOf("/from");
        int idTo = userInput.indexOf("/to");
        String description = userInput.substring(5, idFrom).trim();
        String fromTime = userInput.substring(idFrom + 5, idTo).trim();
        String toTime = userInput.substring(idTo + 3).trim();
        if (description.isEmpty()) {
            ui.displayMessage("Error. Cannot add empty Event.");
            return null;
        } else if (fromTime.isEmpty()) {
            ui.displayMessage("Error. Please indicate start time.");
            return null;
        } else if (toTime.isEmpty()) {
            ui.displayMessage("Error. Please indicate to time.");
            return null;
        } else {
            ui.displayMessage("Got it. I have added this task: ");
            Event e = new Event(description, fromTime, toTime);
            ui.displayMessage(e.toString());
            tasks.add(e);
            ui.displayMessage(displayCounter(tasks.size()));
            return e;
        }
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    private String displayCounter(int num) {
        if (num <= 1) {
            return "Now you have " + num + " task in the list.";
        } else {
            return "Now you have " + num + " tasks in the list.";
        }
    }
}
