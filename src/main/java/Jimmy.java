public class Jimmy {
    private final Task[] list = new Task[100];
    private int counter = 0;

    public void greetUser() {
        System.out.println("Hello! I'm Jimmy\nWhat can I do for you?\n");
    }

    public void createNewTask(String instruction, String details) throws JimmyException {
        Task newTask = null;
        switch (instruction) {
            case "todo":
                newTask = createNewTodo(details);
                break;
            case "deadline":
                newTask = createNewDeadline(details);
                break;
            case "event":
                newTask = createNewEvent(details);
                break;
        }

        list[counter] = newTask;
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println(this.generateListCounter() + "\n");
    }

    private Todo createNewTodo(String details) {
        return new Todo(details);
    }

    private Deadline createNewDeadline(String details) throws JimmyException {
        if (!details.contains(" /by ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new deadline.");
        }
        String[] deadlineDetails = details.split(" /by ", 2);
        String deadlineName = deadlineDetails[0], deadline = deadlineDetails[1];
        if (deadlineName.length() == 0 || deadline.length() == 0) {
            throw new JimmyException("Please check that you have entered a deadline name and a deadline.");
        }
        return new Deadline(deadlineName, deadline);
    }

    private Event createNewEvent(String details) throws JimmyException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new event.");
        }
        String[] eventDetails = details.split(" /from | /to ");
        String eventName = eventDetails[0], start = eventDetails[1], end = eventDetails[2];
        if (eventName.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new JimmyException("Please check that you have entered a event name, a start time and an end time.");
        }
        return new Event(eventName, start, end);
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
            if (i == counter - 1) {
                System.out.println((i + 1) + "." + list[i].toString() + "\n");
            } else {
                System.out.println((i + 1) + "." + list[i].toString());
            }
        }
    }

    public void markTaskComplete(int taskIndex) throws JimmyException {
        if (taskIndex < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (taskIndex >= this.getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = list[taskIndex];
        curr.markAsComplete();
    }

    public void markTaskIncomplete(int taskIndex) throws JimmyException {
        if (taskIndex < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (taskIndex >= this.getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = list[taskIndex];
        curr.markAsIncomplete();
    }

    public int getListSize() {
        return counter;
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
