public class CommandProcessor {
    int numList = 0;
    Task[] list = new Task[100];

    public void processData(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "list":
                displayList();
                break;

            case "mark":
                markOnList(processMark(input));
                break;

            case "unmark":
                unmarkOnList(processMark(input));
                break;

            case "deadline":
                addToList(processDeadline(input));
                break;
            
            case "event":
                addToList(processEvent(input));
                break;

            case "todo":
                addToList(processTodo(input));
                break;

            default:
                System.out.println(String.format("I'm sorry I didn't quite get \"%s\"", input));
                break;
        }

        return;
    }

    public Task processDeadline(String input) {
        String restOfInput = input.substring(8);
        String[] splitInput = restOfInput.split(" /by ");

        String taskName = splitInput[0];
        String by = splitInput[1];
        
        return new Deadline(taskName, by);
    }

    public Task processTodo(String input) {
        String taskName = input.substring(4);
        return new Todo(taskName);
    }

    public Task processEvent(String input) {
        String restOfInput = input.substring(5);
        String[] splitFrom = restOfInput.split(" /from ");


        String[] fromTo = splitFrom[1].split(" /to ");

        String taskName = splitFrom[0];
        String from = fromTo[0];
        String to = fromTo[1];
        
        return new Event(taskName, from, to);
    }

    public int processMark(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    public void markOnList(int i) {
        Task task = list[i];
        task.markDone();
        System.out.println(String.format("I have marked this:\n%s", task));
    }

    public void unmarkOnList(int i) {
        Task task = list[i];
        task.markUndone();
        System.out.println(String.format("I have unmarked this:\n%s", task));
    }

    public void addToList(Task task) {
        list[numList] = task;
        numList++;
        System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task, numList));
    }
    public void displayList() {
        for (int i = 0; i < numList; i++) {
            System.out.println(String.format("%d. %s", i+1, list[i]));
        }
    }
}