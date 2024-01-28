package Klee.command;

import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;
import Klee.task.Task;

import java.time.LocalDateTime;

public class Event extends Command {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event (String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        Task task = new Klee.task.Event(description, from, to);
        tasks.add(task);
        ui.showCreation(task, tasks.size());
        storage.saveTasks(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Event.class) return (this.description.equals(((Event) obj).description) && this.from.equals((((Event) obj).from)) && this.to.equals((((Event) obj).to)));
        else return false;
    }
}
