public class Jimmy {
    private Task[] list = new Task[100];
    private int counter = 0;

    public void greetUser() {
        System.out.println("Hello! I'm Jimmy \nWhat can I do for you?");
    }

    public void createNewTask(String instruction, String details) {
        Task newTask = null;
        switch (instruction) {
            case "todo":
                newTask = new Todo(details);
                break;
            case "deadline":
                String[] deadlineDetails = details.split(" /by ", 2);
                String deadlineName = deadlineDetails[0], deadline = deadlineDetails[1];
                newTask = new Deadline(deadlineName, deadline);
                break;
            case "event":
                String[] eventDetails = details.split(" /from | /to ");
                String eventName = eventDetails[0], start = eventDetails[1], end = eventDetails[2];
                newTask = new Event(eventName, start, end);
                break;
        }

        list[counter] = newTask;
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println(this.generateListCounter());
    }

    public String generateListCounter() {
        if (counter == 0) {
            return "You have no tasks, create some now!";
        } else if (counter == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + counter + " tasks in the list.";
        }
    }

    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list[i].toString());
        }
    }

    public void markTaskComplete(int taskIndex) {
        Task curr = list[taskIndex];
        curr.markAsComplete();
    }

    public void markTaskIncomplete(int taskIndex) {
        Task curr = list[taskIndex];
        curr.markAsIncomplete();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
