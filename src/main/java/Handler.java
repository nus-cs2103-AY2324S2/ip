public class Handler {
    private TaskList taskList;

    public Handler(TaskList taskList) {
        this.taskList = taskList;
    }

    public void handle(String input) {
        if (input.equals("list")) {
            handleList();
        } else if (input.startsWith("mark")) {
            handleMark(input);
        } else if (input.startsWith("unmark")) {
            handleUnmark(input);
        } else if (input.startsWith("delete")) {
            handleDelete(input);
        } else {
            handleAdd(input);
        }
    }

    private void handleList() {
        taskList.displayTasks();
    }

    private void handleMark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList.markTaskAsDone(index);
    }

    private void handleUnmark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList.unmarkTaskAsDone(index);
    }

    private void handleAdd(String input) {
        taskList.addTask(input);
    }

    private void handleDelete(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList.deleteTask(index);
    }

}
