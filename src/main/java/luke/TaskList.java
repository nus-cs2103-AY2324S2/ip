package luke;

import java.util.ArrayList;

//for CURRENT tasks only.
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    //fetch tasks from history
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
            System.out.println("I helped you add task '" + task.fullStatus() + "'. But do it yourself next time! Hmmph!" + "\n");
        }
    }

    public void markTask(int index) {
        try {
            tasks.get(index).complete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Jeez, you really ought to give me a number I can work with... got that?");
            System.out.println("[Item index exceeds history count]\n");
            return;
        }
        System.out.println("Good work, I guess.");
        System.out.println((index + 1) + "." + tasks.get(index).fullStatus());
        System.out.println();
    }

    public void listTasks() {
        int num = 1;
        if (tasks.size() == 0) {
            System.out.println("Looks like you have way too much free time on your hands, huh.");
            System.out.println("[No items in list]");
        }
        for (Task s : tasks) {
            if (s.isDone()) {
                System.out.println(num + "." + s.fullStatus());
            } else {
                System.out.println(num + "." + s.fullStatus());
            }
            num += 1;
        }
        System.out.println();
    }

    public void deleteTask(int index) {
        String fullStatus;
        try {
            fullStatus = tasks.get(index).fullStatus();
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's nothing there, dummy...");
            System.out.println("[Tried to remove non-existent event]\n");
            return;
        }
        System.out.println("Fine! If that's what you really want...");
        System.out.println("[Removed " + fullStatus + "]\n");
    }

    //Marks a particular task as done. Takes in a string command.
/*    public void markTask(String input) {
        int idx;
        try {
            idx = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I can't help you out if you don't tell me what to mark! ");
            System.out.println("[Missing input parameter for mark]\n");
            return;
        }
        try {
            history.get(idx).complete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Jeez, you really ought to give me a number I can work with... got that?");
            System.out.println("[Item index exceeds history count]\n");
            return;
        }
        System.out.println("Good work, I guess.");
        System.out.println((idx + 1) + "." + history.get(idx).fullStatus());
        System.out.println();
    }

    public void listHistory() {
        int num = 1;
        if (history.size() == 0) {
            System.out.println("Looks like you have way too much free time on your hands, huh.");
            System.out.println("[No items in list]");
        }
        for (luke.Task s : history) {
            if (s.isDone()) {
                System.out.println(num + "." + s.fullStatus());
            } else {
                System.out.println(num + "." + s.fullStatus());
            }
            num += 1;
        }
        System.out.println();
    }

    //Deletes a specified task. Takes in a string input.
    public void deleteTask(String input) {
        String fullStatus;
        int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
        try {
            fullStatus = history.get(index).fullStatus();
            history.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's nothing there, dummy...");
            System.out.println("[Tried to remove non-existent event]\n");
            return;
        }
        System.out.println("Fine! If that's what you really want...");
        System.out.println("[Removed " + fullStatus + "]\n");
    }*/
}
