import java.util.ArrayList;

public class Storage {
//    private final Task[] todoList = new Task[100];
    ArrayList<Task> todoList = new ArrayList<>();
//    private int index;

    public Storage() {
//        this.index = 0;
    }

    public String addTask(String userInput) {
        String[] userInputArray = userInput.split(" ");
        String taskType = userInputArray[0];
        // return String can be "error" or added task
        String returnOutput = "";

        try {
            // Checks if task description is empty,
            if (userInputArray.length == 1) {
                // Missing description error
                throw new HALException("Missing description!");

            // If task description is not empty, proceed as per normal
            } else {

                if (userInputArray[0].equalsIgnoreCase("todo")) {
                    String description = userInput.substring(4).trim();
//                    todoList[index] = new Todo(description);
                    todoList.add(new Todo(description));

                } else if (userInputArray[0].equalsIgnoreCase("deadline")) {

                    int keywordIndex = userInput.indexOf("/by");

                    if (keywordIndex != -1) {
                        String description = userInput.substring(8, keywordIndex).trim();
                        String deadline = userInput.substring(keywordIndex + 3).trim();

//                        Deadline deadlineObj = new Deadline(description, deadline);
//                        todoList[index] = deadlineObj;
                        todoList.add(new Deadline(description, deadline));
                    } else {
                        // Missing keyword error
                        throw new HALException("Missing keyword /by!");

                    }
                } else if (userInputArray[0].equalsIgnoreCase("event")) {
                    int keywordIndex = userInput.indexOf("/from");
                    int keyword2Index = userInput.indexOf("/to");

                    if (keywordIndex != -1 && keyword2Index != -1) {
                        String description = userInput.substring(5, keywordIndex).trim();
                        String deadlineFrom = userInput.substring(keywordIndex + 5, keyword2Index).trim();
                        String deadlineTo = userInput.substring(keyword2Index + 3).trim();

                        todoList.add(new Event(description, deadlineFrom, deadlineTo));
//                        todoList[index] = new Event(description, deadlineFrom, deadlineTo);
                    } else {
                        // Missing keyword error
                        throw new HALException("Missing keyword /from and /to!");

                    }
                }
//                index ++;

                // [index - 1] so that we increment index, but still return string from previously added task
//                returnOutput = todoList[index - 1].toString();
                Task taskObject = todoList.get(todoList.size() - 1);
                return taskObject.toString();
            }

        } catch (HALException e) {
            System.out.println(e.getMessage());
            returnOutput = "error";
        }

        return returnOutput;
    }

    public String removeTask(int taskIndex) {
        Task taskObject = todoList.get(taskIndex);
        todoList.remove(taskIndex);

        return taskObject.toString();
    }

    public String markAsDone(int taskIndex) {
//        Task t = todoList[taskIndex];
        Task t = todoList.get(taskIndex);
        t.markAsDone();
//        String taskString = String.format("[%s] %s\n", t.getStatusIcon(), t.getDescription());
        return t.toString();
    }

    public String markAsUndone(int taskIndex) {
//        Task t = todoList[taskIndex];
        Task t = todoList.get(taskIndex);
        t.markAsUndone();
//        String taskString = String.format("[%s] %s\n", t.getStatusIcon(), t.getDescription());
        return t.toString();
    }

    public void listTasks() {
        System.out.println("Tasks:");

        for (int i = 0; i < todoList.size(); i++) {

            Task t = todoList.get(i);
            System.out.printf("%d. %s\n", i + 1, t.toString());
        }
    }

    public int getNumberOfTasks() {
        return todoList.size();
    }
}
