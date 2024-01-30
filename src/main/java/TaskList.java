import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    private ArrayList<Task> taskStore;

    public TaskList() {
        taskStore = new ArrayList<Task>();
    }

    protected void markTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(true);
        System.out.println("*Honk!* Good Job!, Pengu has marked this task as done:\n" + currTask.toString());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    protected void unmarkTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(false);
        System.out.println("*Honk!* Pengu has marked this task as not done yet:\n" + currTask.toString());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    protected void deleteTask(int index) {
        Task deletedTask = taskStore.remove(index);
        System.out.println(String.format("*Honk* Pengu has removed the following task:\n" + deletedTask.toString()
                + "\nNow you have %s tasks left", taskStore.size()));
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    protected void listTasks() {
        int storageSize = taskStore.size();
        if (storageSize == 0) {
            System.out.println("*Honk!* You currently have no tasks");
        } else {
            System.out.println("*Honk!* Pengu has listed your current tasks below:");
            for (int k = 0; k < storageSize; k++) {
                int curr = k + 1;
                Task currTask = taskStore.get(k);
                System.out.println(curr + ". " + currTask.toString());
            }
        }
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    protected int listSize() {
        return taskStore.size();
    }

    protected void addTask(String description, boolean isLoaded) throws DukeException {
        Task newTask;
        validTaskCommand(description);
        if (description.toLowerCase().startsWith("todo")) {
            String[] descriptionArr = description.split(" ");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new ToDo(descBuilder.toString());
        } else if (description.toLowerCase().startsWith("deadline")) {
            String[] descriptionArr = description.split(" ");
            int byIndex = Arrays.asList(descriptionArr).indexOf("/by");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < byIndex; k++) {
                if (k == byIndex - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            StringBuilder byBuilder = new StringBuilder();
            for (int k = byIndex + 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    byBuilder.append(descriptionArr[k]);
                } else {
                    byBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new Deadline(descBuilder.toString(), byBuilder.toString());
        } else {
            String[] descriptionArr = description.split(" ");
            int fromIndex = Arrays.asList(descriptionArr).indexOf("/from");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < fromIndex; k++) {
                if (k == fromIndex - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            int toIndex = Arrays.asList(descriptionArr).indexOf("/to");
            StringBuilder fromBuilder = new StringBuilder();
            for (int k = fromIndex + 1; k < toIndex; k++) {
                if (k == toIndex - 1) {
                    fromBuilder.append(descriptionArr[k]);
                } else {
                    fromBuilder.append(descriptionArr[k] + " ");
                }
            }
            StringBuilder toBuilder = new StringBuilder();
            for (int k = toIndex + 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    toBuilder.append(descriptionArr[k]);
                } else {
                    toBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new Event(descBuilder.toString(), fromBuilder.toString(), toBuilder.toString());
        }
        this.taskStore.add(newTask);
        if (!isLoaded) {
            System.out.println(String.format("*Honk! Honk!* Pengu has added this task:\n" + newTask.toString()
                    + "\nGet back to work! you have %s tasks in the list\n"
                    + "―――――――――――――――――――――――――――――――――――", taskStore.size()));
        }
    }

    private boolean validTaskCommand(String str) throws DukeException {
        List<String> strArr = Arrays.asList(str.split(" "));
        String keyword = str.split(" ")[0].toLowerCase();
        if (!(keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event"))){
            throw new DukeException("*HONK* Pengu has never seen such a command before, some commands Pengu can do are: list, todo, deadline");
        } else if (keyword.equals("todo") && !(strArr.size() > 1)) {
            throw new DukeException("*HONK* Pengu needs a To Do Task description to record this down");
        } else if (keyword.equals("deadline") && !(strArr.contains("/by"))) {
            throw new DukeException("*HONK* Pengu needs a /by followed by a end date for your task");
        } else if (keyword.equals("event") && !(strArr.contains("/from") && (strArr.contains("/to")))) {
            throw new DukeException("*HONK* Pengu needs a /from followed by a from date and a /to followed by a end date for your task");
        } else if (keyword.equals("deadline") && strArr.get(1).equals("/by")) {
            throw new DukeException("Honk* Pengu cannot accept a deadline task without a description");
        } else if (keyword.equals("event") && (strArr.get(1).equals("/from") || strArr.get(1).equals("/to"))) {
            throw new DukeException("*Honk* Pengu cannot accept a event without a description");
        } else {
            return true;
        }
    }
}
