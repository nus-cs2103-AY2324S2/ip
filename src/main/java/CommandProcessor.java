public class CommandProcessor {
    int numList = 0;
    Task[] list = new Task[100];

    public void processData(String input) {
        try {
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
                    String message = String.format("I'm sorry I didn't quite get \"%s\"", input);
                    throw new InputException(message);
            }

        } catch (ProcessingException | InputException e) {
            System.out.println(e.getMessage());
        }
        

        return;
    }

    public Task processDeadline(String input) throws ProcessingException {
        try {
            String restOfInput = input.substring(8);
            String[] splitInput = restOfInput.split(" /by ");

            String taskName = splitInput[0];
            String by = splitInput[1];

            return new Deadline(taskName, by);

        } catch (Exception e) {
            String message = "Something went wrong when processing your deadline command: \n"
                                + "Check your input again: " + input;
            throw new ProcessingException(message);
        }
    }

    public Task processTodo(String input) throws ProcessingException {
        try {
            String taskName = input.substring(4);
            return new Todo(taskName);
        } catch (Exception e) {
            String message = "Something went wrong when processing your todo command: \n"
                                + "Check your input again: " + input;
            throw new ProcessingException(message);
        }
    }

    public Task processEvent(String input) throws ProcessingException {
        try {
            String restOfInput = input.substring(5);
            String[] splitFrom = restOfInput.split(" /from ");
    
    
            String[] fromTo = splitFrom[1].split(" /to ");
    
            String taskName = splitFrom[0];
            String from = fromTo[0];
            String to = fromTo[1];
            
            return new Event(taskName, from, to);

        } catch (Exception e) {
            String message = "Something went wrong when processing your event command: \n"
                                + "Check your input again: " + input;
            throw new ProcessingException(message);
        }
    }

    public Task processMark(String input) throws ProcessingException {
        try {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = list[i];
            return task;
        } catch (Exception e) {
            String message = "Something went wrong when processing your mark command: \n"
                                + "Check your input again: " + input;
            throw new ProcessingException(message);
        }
    }

    public void markOnList(Task task) {

        task.markDone();
        System.out.println(String.format("I have marked this:\n%s", task));
    }

    public void unmarkOnList(Task task) {
        task.markUndone();
        System.out.println(String.format("I have unmarked this:\n%s", task));
    }

    public void addToList(Task task) throws ProcessingException {
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