import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void markTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, splendid! Your task: {" + task.toString() + "} has been marked as done successfully.\n " +
                "Isn't it just wonderful when things go exactly as planned?\n " +
                "I'm so proud of you for getting it done!");
    }

    public void unmarkTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.unmarkAsDone();
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, you've unmarked task: {" + task.toString() + "}?\n " +
                "No worries at all! It's always okay to reevaluate and adjust your plans.\n " +
                "Flexibility is a sign of strength, you know. Keep up the great work!");
    }

    public void addTask(String input) {
        Task task = new Task(input);
        this.tasks.add(task);
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, splendid! Your task: {" + input + "} has been added successfully.\n " +
                "Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void addTodoTask(String input) {
        String processedInput = input.substring(5);
        Todo task = new Todo(processedInput);
        this.tasks.add(task);
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, splendid! Your Todo task: {" + task.toString() + "} has been added successfully.\n " +
                "Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void addDeadlineTask(String input) {
        String splitInput = input.substring(9);
        String[] splitArguments = splitInput.split(" /by ");
        Deadline task = new Deadline(splitArguments[0], splitArguments[1]);
        this.tasks.add(task);
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, splendid! Your Deadline task: {" + task.toString() + "} has been added successfully.\n " +
                "Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void addEventTask(String input) {
        String splitInput = input.substring(6);
        String[] splitArguments = splitInput.split("/");
        String description = splitArguments[0].trim();
        String fromTime = splitArguments[1].trim().substring(5); // Remove "from " prefix
        String toTime = splitArguments[2].trim().substring(3); // Remove "to " prefix
        Event task = new Event(description, fromTime, toTime);
        this.tasks.add(task);
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, splendid! Your Event task: {" + task.toString() + "} has been added successfully.\n " +
                "Isn't it just wonderful when things go exactly as planned?\n " +
                "I'm so proud of you for getting it done!\n " +
                "Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void listTasks() {
        if (this.tasks.isEmpty()) {
            Saopig.speakWithHorizontalLines("\n" +
                    "\n" +
                    "Oh dear, it looks like there are no tasks yet!\n " +
                    "But that's alright.\n " +
                    "It gives us a chance to start fresh and dream up some new plans.\n " +
                    "Whenever you're ready to add tasks, I'll be right here to assist you.\n " +
                    "Let's make it a magical journey together!");
            return;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            Saopig.speak((i + 1) + ". " + task.toString());
        }
        Saopig.speak("____________________________________________________________");
    }
}
