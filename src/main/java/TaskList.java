import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    protected static int tasksCount = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(String input) {
        Task task = convertTask(input);
        if (task != null) {
            tasks.add(task);
            tasksCount++;
            return true;
        }
        return false;
    }

    private void catchInputError(String command) {
        switch (command.toLowerCase()) {
            case "todo":
                ChatbotException.getError(ChatbotException.ErrorType.TODO_EMPTY);
                break;
            case "deadline":
                ChatbotException.getError(ChatbotException.ErrorType.DEADLINE_EMPTY);
                break;
            case "event":
                ChatbotException.getError(ChatbotException.ErrorType.EVENT_EMPTY);
                break;
            default:
                ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_COMMAND);
        }
    }

    public Task convertTask(String input) {

        if (input.trim().isEmpty()) {
            ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_COMMAND);
            return null;
        }

        String[] inputs = input.split("\\s+", 2);
        if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
            catchInputError(inputs[0]);
            return null;
        }

        return createTask(inputs[0], inputs[1]);
    }

    public Task createTask(String command, String description) {
        if (command.startsWith("todo")) {
            return new ToDo(description);
        } else if (command.startsWith("deadline")) {
            String[] parts = description.split("/by", 2);
            return new Deadline(parts[0], parts[1]);
        } else {
            String[] parts = description.split("\\s+/from\\s+|\\s+/to\\s+");
            return new Event(parts[0], parts[1], parts[2]);
        }
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1)+ ". " + tasks.get(i).getDescription());
        }
    }

    public String getTaskDescription(int num) {
        return tasks.get(num).getDescription();
    }

    public void markTask(int num, boolean status) {
        tasks.get(num).mark(status);
    }

}
