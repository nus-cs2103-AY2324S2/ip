public class Storage {
    private final Task[] todoList = new Task[100];
    private int index;

    public Storage() {
        this.index = 0;
    }

    public String addTask(String userInput) {
        String[] userInputArray = userInput.split(" ");
        String taskType = userInputArray[0];

        if (userInputArray[0].equalsIgnoreCase("todo")) {
            String description = userInput.substring(4).trim();
            todoList[index] = new Todo(description);

        } else if (userInputArray[0].equalsIgnoreCase("deadline")) {

            int keywordIndex = userInput.indexOf("/by");

            if (keywordIndex != -1) {
                String description = userInput.substring(8, keywordIndex).trim();
                String deadline = userInput.substring(keywordIndex + 3).trim();

                Deadline deadlineObj = new Deadline(description, deadline);
                todoList[index] = deadlineObj;
            }
        } else if (userInputArray[0].equalsIgnoreCase("event")) {
            int keywordIndex = userInput.indexOf("/from");
            int keyword2Index = userInput.indexOf("/to");

            if (keywordIndex != -1 && keyword2Index != -1) {
                String description = userInput.substring(5, keywordIndex).trim();
                String deadlineFrom = userInput.substring(keywordIndex + 5, keyword2Index).trim();
                String deadlineTo = userInput.substring(keyword2Index + 3).trim();

                todoList[index] = new Event(description, deadlineFrom, deadlineTo);
            }
        }

        index ++;

        // [index - 1] so that we increment index, but still return string from previously added task
        return todoList[index - 1].toString();
    }

    public String markAsDone(int taskIndex) {
        Task t = todoList[taskIndex];
        t.markAsDone();
//        String taskString = String.format("[%s] %s\n", t.getStatusIcon(), t.getDescription());
        return t.toString();
    }

    public String markAsUndone(int taskIndex) {
        Task t = todoList[taskIndex];
        t.markAsUndone();
//        String taskString = String.format("[%s] %s\n", t.getStatusIcon(), t.getDescription());
        return t.toString();
    }

    public void listTasks() {
        System.out.println("Tasks:");

        for (int i = 0; i < index; i++) {

            Task t = todoList[i];
            System.out.printf("%d. %s\n", i + 1, t.toString());
        }
    }

    public int getNumberOfTasks() {
        return index;
    }
}
