import java.util.ArrayList;
import java.nio.file.Path;

public class TaskList {
    private static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:%n%s";
    private static final String MARK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:%n%s";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:%s";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.";
    private static final String MISSING_ARGUMENT_MESSAGE = "The command you entered has missing arguments. Please try again!";
    private static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.";

    private final ArrayList<Task> taskList;
    private final Storage taskStorage;

    public TaskList() {
        this.taskList = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = Path.of(root, "ip/data/tasks.txt").toString();
        this.taskStorage = new Storage(path);
        updateList();
    }

    public void addToDoTask(String toDoDescription) {
        if (toDoDescription.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        Task toDo = new ToDo(toDoDescription);
        taskList.add(toDo);
        updateStorage();
        String output = String.format(ADD_TASK_MESSAGE, toDo, taskList.size());
        System.out.println(output);
    }

    public void addDeadlineTask(String deadlineDescription) {
        if (deadlineDescription.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        String[] deadlineArgs = deadlineDescription.split(" /by ");
        Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        taskList.add(deadline);
        updateStorage();
        String output = String.format(ADD_TASK_MESSAGE, deadline, taskList.size());
        System.out.println(output);
    }

    public void addEventTask(String eventDescription) {
        if (eventDescription.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        String[] eventArgs = eventDescription.split(" /from ");
        String[] eventTime = eventArgs[1].split(" /to ");
        String startTime = eventTime[0];
        String endTime = eventTime[1];
        Task event = new Event(eventArgs[0], startTime, endTime);
        taskList.add(event);
        updateStorage();
        String output = String.format(ADD_TASK_MESSAGE, event, taskList.size());
        System.out.println(output);
    }

    public void markTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsDone();
        updateStorage();
        String output = String.format(MARK_DONE_MESSAGE, t);
        System.out.println(output);
    }

    public void unmarkTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsUndone();
        updateStorage();
        String output = String.format(MARK_UNDONE_MESSAGE, t);
        System.out.println(output);
    }

    public void deleteTask(String taskIndex) {
        if (taskIndex.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        Task taskToRemove = taskList.get(Integer.parseInt(taskIndex) - 1);
        taskList.remove(Integer.parseInt(taskIndex) - 1);
        updateStorage();
        String output = String.format(REMOVE_TASK_MESSAGE, taskToRemove, taskList.size());
        System.out.println(output);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%n%d.%s", i + 1, task));
        }
        String output = String.format(LIST_TASK_MESSAGE, sb);
        System.out.println(output);
        return output;
    }

    private void updateList() {
        try {
            String fileData = taskStorage.loadFileData();
            String[] tasks = fileData.split("\n");
            for (String t : tasks) {
                String[] taskTokens = t.split("\\|");
                for (int j = 0; j < taskTokens.length; j++) {
                    taskTokens[j] = taskTokens[j].trim();
                }
                String command = taskTokens[0];
                String status = taskTokens[1];
                String description = taskTokens[2];

                if (command.equals("T")) {
                    syncListWithStorage("T", status, description, "", "", "");
                } else if (command.equals("D")) {
                    syncListWithStorage("D", status, description, taskTokens[3], "", "");
                } else {
                    syncListWithStorage("E", status, description, "", taskTokens[3], taskTokens[4]);
                }

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void syncListWithStorage(String taskType, String status, String description, String by, String from, String to) {
        if (taskType.equals("T")) {
            taskList.add(new ToDo(description));
        } else if (taskType.equals("D")) {
            taskList.add(new Deadline(description, by));
        } else {
            taskList.add(new Event(description, from, to));
        }

        if (status.equals("X")) {
            markTask(String.valueOf(taskList.size()));
        }
    }

    private void updateStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            sb.append(t.taskFileTemplate()).append("\n");
        }
        try {
            taskStorage.saveToFile(sb.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
