package jiayou.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import jiayou.exception.JiayouException;
import jiayou.function.Storage;

/**
 * Represents a task list to store all the tasks of the chatbot.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Links the given storage to the task list.
     *
     * @param storage the storage to be linked.
     */
    public void linkStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Updates the linked text file.
     */
    public void updateStorage() {
        this.storage.saveToFile();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added.
     * @return a response message.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        assert tasks.contains(task);
        updateStorage();

        String response = ">w< Got it. I've added this task:\n";
        response += ("  " + task + "\n");
        response += ("Now you have " + getSize() + " task(s) in the list.\n");
        return response;
    }

    /**
     * Deletes a new task from the task list.
     *
     * @param taskIds the task id(s) of the task(s) to be deleted.
     * @return a response message.
     */

    public String deleteTask(String ... taskIds) {
        String response = "";
        List<Integer> idsToInt = new ArrayList<>();
        try {
            for (String taskId : taskIds) {
                int idToInt = Integer.parseInt(taskId) - 1;
                if (idToInt < 0 || idToInt >= tasks.size()) {
                    throw new JiayouException("OOPS!!! The task you wanna delete doesn't exist. "
                            + "Please input a valid number!");
                }
                idsToInt.add(idToInt);
            }
            Collections.sort(idsToInt, Collections.reverseOrder());

            response += ">w< Noted. I've removed the task(s):\n";
            for (int idToInt : idsToInt) {
                Task removedTask = tasks.remove(idToInt);
                assert !tasks.contains(removedTask);
                response += ("  " + removedTask + "\n");
            }
            response += ("Now you have " + tasks.size() + " task(s) in the list.\n");

            updateStorage();
        } catch (NumberFormatException e) {
            return "OOPS!!! Please enter a valid task number!";
        } catch (JiayouException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * Marks the certain task in the list as done.
     *
     * @param taskIds the task id(s) of the task(s) to get marked done.
     * @return a response message.
     */
    public String markTask(String ... taskIds) {
        String response = "";
        List<Integer> idsToInt = new ArrayList<>();
        try {
            for (String taskId : taskIds) {
                int idToInt = Integer.parseInt(taskId) - 1;
                if (idToInt < 0 || idToInt >= tasks.size()) {
                    throw new JiayouException("OOPS!!! The task you wanna mark doesn't exist. "
                            + "Please input a valid number!");
                }
                idsToInt.add(idToInt);
            }

            response += ">w< Nice! I've marked the task(s) as done:\n";
            for (int idToInt : idsToInt) {
                Task taskToMark = tasks.get(idToInt);
                taskToMark.setStatus(true);
                assert taskToMark.getStatus();
                response += ("  " + taskToMark + "\n");
            }
            updateStorage();
        } catch (NumberFormatException e) {
            return "OOPS!!! Please enter a valid task number!";
        } catch (JiayouException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * Unmarks the certain task in the list as not done.
     *
     * @param taskIds the task id(s) of the task(s) to get masked not done.
     * @return a response message.
     */
    public String unmarkTask(String ... taskIds) {
        String response = "";
        List<Integer> idsToInt = new ArrayList<>();

        try {
            for (String taskId : taskIds) {
                int idToInt = Integer.parseInt(taskId) - 1;
                if (idToInt < 0 || idToInt >= tasks.size()) {
                    throw new JiayouException("OOPS!!! The task you wanna unmark doesn't exist. "
                            + "Please input a valid number!");
                }
                idsToInt.add(idToInt);
            }

            response += ">w< Nice! I've marked the task(s) as undone:\n";
            for (int idToInt : idsToInt) {
                Task taskToUnmark = tasks.get(idToInt);
                taskToUnmark.setStatus(false);
                assert !taskToUnmark.getStatus();
                response += ("  " + taskToUnmark + "\n");
            }
            updateStorage();
        } catch (NumberFormatException e) {
            return "OOPS!!! Please enter a valid task number!";
        } catch (JiayouException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * Searches for the tasks on a certain date and print them out.
     *
     * @param date the date to search.
     * @return a response message.
     */
    public String searchByDate(LocalDate date) {
        String response = "Here are the task(s) on " + date + " in your list:\n";
        int count = 0;

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task instanceof Event) {
                if (date.equals(((Event) task).getFrom())
                        | date.equals((((Event) task).getTo()))
                        | (date.isAfter((((Event) task).getFrom())) & date.isBefore(((Event) task).getTo()))) {
                    count += 1;
                    response += ((i + 1) + "." + task + "\n");
                }
            } else if (task instanceof Deadline) {
                if (date.equals(((Deadline) task).getByTime())) {
                    count += 1;
                    response += ((i + 1) + "." + task + "\n");
                }
            }
        }

        if (count == 0) {
            return "I am sorry.\nThere is no task on " + date + ".\nTry some other dates! > <";
        }

        return response;
    }

    /**
     * Searches for the tasks with a certain keyword and print them out.
     *
     * @param keyword the keyword to search.
     * @return a response message.
     */
    public String searchByKeyword(String keyword) {
        List<String> matchedTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .map(task -> tasks.indexOf(task) + 1 + ". " + task.toString())
                .collect(Collectors.toList());

        if (matchedTasks.isEmpty()) {
            return "I am sorry.\nThere is no task with the keyword " + keyword + ".\nTry some other keywords! > <";
        } else {
            return "Here are the task(s) with the keyword " + keyword + " in your list:\n"
                    + String.join("\n", matchedTasks);
        }
    }

    /**
     * Reschedules a task given its id and desired date.
     *
     * @param id the id of the task to be updated.
     * @param dateType the type of the date, from, to or by.
     * @param newDate the new date to be updated.
     * @return a response message.
     */

    public String reschedule(String id, String dateType, String newDate) {
        String response = "";
        try {
            int idToInt = Integer.parseInt(id) - 1;
            if (idToInt < 0 || idToInt >= tasks.size()) {
                throw new JiayouException("OOPS!!! The task you wanna unmark doesn't exist. "
                        + "Please input a valid number!");
            }

            Task taskToReschedule = tasks.get(idToInt);
            if (!isRescheduleValid(taskToReschedule, dateType)) {
                throw new JiayouException("OOPS!!! Your rescheduling is unsuccessful because "
                        + "the task doesn't have this type of date.");
            }

            response += ">w< Noted! I have rescheduled the task for you:\n";

            if (dateType.equals("/by")) {
                Deadline ddlToReschedule = (Deadline) taskToReschedule;
                ddlToReschedule.setBy(newDate);
                response += ddlToReschedule;
            } else if (dateType.equals("/from")) {
                Event eventToReschedule = (Event) taskToReschedule;
                eventToReschedule.setFrom(newDate);
                response += eventToReschedule;
            } else {
                Event eventToReschedule = (Event) taskToReschedule;
                eventToReschedule.setTo(newDate);
                response += eventToReschedule;
            }
        } catch (JiayouException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * Checks if the rescheduling is valid.
     * @param task the task to get rescheduled.
     * @param dateType the type of the date, from, to or by.
     * @return a boolean value.
     */
    private boolean isRescheduleValid(Task task, String dateType) {
        boolean isEvent = task instanceof Event;
        boolean isDeadline = task instanceof Deadline;
        boolean isDateForEvent = dateType.equals("/from") | dateType.equals("/to");
        boolean isDateForDeadline = dateType.equals("/by");

        if ((isEvent & isDateForEvent) | (isDeadline & isDateForDeadline)) {
            return true;
        }

        return false;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Prints all the tasks out.
     *
     * @return a response message.
     */
    public String printList() {
        if (this.tasks.size() == 0) {
            return "Your list is empty now!\nPlease add some new tasks > <";
        }

        String response = "Here are the task(s) in your list:\n";
        String taskListString = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).toString())
                .collect(Collectors.joining("\n"));
        return response + taskListString;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
