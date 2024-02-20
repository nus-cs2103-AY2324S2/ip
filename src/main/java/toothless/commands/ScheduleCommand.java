package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;
import toothless.tasks.Todo;
import toothless.tasks.Deadline;
import toothless.tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleCommand extends Command{

    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (taskList.size() == 0) {
            throw new ToothlessException(ui.showEmptyListWarning());
        }
        HashMap<LocalDate, List<Task>> hashMap = new HashMap<>();
        addAllTask(taskList, hashMap);
        LocalDate[] keys = hashMap.keySet().toArray(new LocalDate[0]);
        sortKeys(keys);
        return getResponse(hashMap, keys);
    }

    private void addAllTask(TaskList taskList, HashMap<LocalDate, List<Task>> hashMap) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task instanceof Todo) {
                if (!hashMap.containsKey(LocalDate.MAX)) {
                    hashMap.put(LocalDate.MAX, new ArrayList<>());
                }
                hashMap.get(LocalDate.MAX).add(task);
            }
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate date = deadline.getDate().toLocalDate();
                if (!hashMap.containsKey(date)) {
                    hashMap.put(date, new ArrayList<>());
                }
                hashMap.get(date).add(deadline);
            }
            if (task instanceof Event) {
                Event event = (Event) task;
                LocalDate date = event.getStartDate().toLocalDate();
                if (!hashMap.containsKey(date)) {
                    hashMap.put(date, new ArrayList<>());
                }
                hashMap.get(date).add(event);
            }
        }
    }

    private void sortKeys(LocalDate[] keys) {
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                if (keys[i].isAfter(keys[j])) {
                    LocalDate temp = keys[i];
                    keys[i] = keys[j];
                    keys[j] = temp;
                }
            }
        }
    }

    private String getResponse(HashMap<LocalDate, List<Task>> hashMap, LocalDate[] keys){
        String response = "";
        for (int i = 0; i < keys.length; i++) {
            LocalDate key = keys[i];
            if (key.equals(LocalDateTime.MAX)) {
                response += "Tasks without a date:\n";
            } else {
                response += "Tasks on " + dateToString(key) + ":\n";
            }
            List<Task> tasks = hashMap.get(key);
            for (int j = 0; j < tasks.size(); j++) {
                Task t = tasks.get(j);
                response += (j + 1) + ". " + t + "\n";
            }
            if (i != keys.length - 1) {
                response += "\n";
            }
        }
        return response;
    }

    private String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public boolean isExit() {
        return false;
    }
}
