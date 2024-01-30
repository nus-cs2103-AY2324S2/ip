import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private void lineBreak() {
        System.out.print("--------------------------------------------------\n");
    }

    public void processTaskCommand(String input) {
        try {
            addToList(Parser.parseTaskInput(input));
        } catch (SolaireException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToList(Task task) {
        if (task != null) {
            taskList.add(task);
            System.out.println("Added " + task + " to your list");
            lineBreak();
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void processRemoveFromList(String input) {
        try {
            String[] inputCommand = input.split(" ", 2);
            if (inputCommand.length < 2) {
                throw new SolaireException("Please specify the ID of the task you wish to delete\n");
            } else {
                Integer targetTaskId = Integer.parseInt(inputCommand[1]);
                Task taskToDelete = taskList.get(targetTaskId - 1);
                taskList.remove(taskToDelete);
                System.out.println("Removed" + taskToDelete + " from your list");
            }
        } catch (SolaireException e) {
            System.out.println(e.getMessage());
        }

    }

    public void showList() {
        System.out.print("Your list is as follows:\n " + "-------------------\n");
        for (Task item : taskList) {
            System.out.println(taskList.indexOf(item) + 1 + ". " + item.toString());
        }
    }

    public void markDone(int id) {
        for (Task item : taskList) {
            if (item.getId() == id) {
                item.markAsDone();
                System.out.print("Marked item number: " + item.getId() + "\n");
                return;
            }
        }

        System.out.print("Couldn't find task associated with given id\n");
    }

    public void unmarkDone(int id) {
        for (Task item : taskList) {
            if (item.getId() == id) {
                item.unmarkDone();
                System.out.print("Unmarked  item number: " + item.getId() + "\n");
                return;
            }
        }

        System.out.print("Couldn't find task associated with given id\n");
    }

}
