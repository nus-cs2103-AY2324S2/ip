package dino;


import java.time.LocalDate;
import java.util.List;


import dino.tasks.TaskList;
import dino.tasks.Event;
import dino.tasks.Deadline;



public class Reminders {

    public static void getReminders(TaskList tasks, List<String> messages) {
        TaskList[] lists = filterOutTodo(tasks);
        TaskList todayDeadlines = getTodayDeadlines(lists[0]);
        TaskList todayEvents = getTodayEvents(lists[1]);

        messages.add("\nReminders for Today");

        getDeadlineMessage(todayDeadlines, messages);

        getEventMessage(todayEvents, messages);

    }

    private static TaskList getTodayDeadlines(TaskList deadlineList) {
        LocalDate today = LocalDate.now();
        for (int i = deadlineList.size() - 1; i >= 0; i--) {
            Deadline task = (Deadline) deadlineList.get(i);
            LocalDate taskDate = task.getDate().toLocalDate();
            if (!taskDate.equals(today)) {
               deadlineList.remove(i);
            }
        }
        return deadlineList;
    }

    private static TaskList getTodayEvents(TaskList eventList) {
        LocalDate today = LocalDate.now();
        for (int i = eventList.size() - 1; i >= 0; i--) {
            Event task = (Event) eventList.get(i);
            LocalDate startDate = task.getStart().toLocalDate();
            LocalDate endDate = task.getEnd().toLocalDate();
            if ((today.isEqual(startDate) || today.isAfter(startDate)) &&
                    (today.isEqual(endDate) || today.isBefore(endDate))) {
                eventList.remove(i);
            }
        }
        return eventList;
    }

    private static TaskList[] filterOutTodo(TaskList tasks) {
        TaskList deadlineList = new TaskList();
        TaskList eventList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Event) {
                eventList.add(tasks.get(i));
            } else if (tasks.get(i) instanceof Deadline) {
                deadlineList.add(tasks.get(i));
            }
        }

        TaskList[] result = new TaskList[2];
        result[0] = deadlineList;
        result[1] = eventList;
        return result;
    }

    private static void getDeadlineMessage(TaskList tasks, List<String> messages) {
        int number = 0;
        if (tasks.isEmpty()) {
            messages.add("\nNo tasks due today.");
        } else {
            messages.add("\nTasks due Today:");
            for (int i = 0; i < tasks.size(); i++) {
                number++;
                messages.add(number + ". " + tasks.get(i).toString());
            }
        }
    }

    private static void getEventMessage(TaskList tasks, List<String> messages) {
        int number = 0;
        if (tasks.isEmpty()) {
            messages.add("\nNo events is happening today.");
        } else {
            messages.add("\nEvents that are happening today:");
            for (int i = 0; i < tasks.size(); i++) {
                number++;
                messages.add(number + ". " + tasks.get(i).toString());
            }
        }
    }
}
