import exceptions.EmptyBodyException;
import exceptions.WrongFormatException;
import exceptions.InvalidKeyException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> inputArr = new ArrayList<>();
    private KeyEnum currentKey = KeyEnum.INVALID;

    public Integer getNumOfTasks() {
        return inputArr.size();
    }

    public void taskCountToString() {
        System.out.println("Now you have "+ this.getNumOfTasks() +" tasks in the list." );
    }

    public void determineCurrentKey(String userInputKey) {
        switch (userInputKey) {
            case "bye":
                currentKey = KeyEnum.EXITKEY;
                break;
            case "list":
                currentKey = KeyEnum.LIST;
                break;
            case "mark":
                currentKey = KeyEnum.MARK;
                break;
            case "unmark":
                currentKey = KeyEnum.UNMARK;
                break;
            case "event":
                currentKey = KeyEnum.EVENT;
                break;
            case "deadline":
                currentKey = KeyEnum.DEADLINE;
                break;
            case "todo":
                currentKey = KeyEnum.TODO;
                break;
            case "delete":
                currentKey = KeyEnum.DELETE;
                break;
        }
        if (this.currentKey.equals(KeyEnum.INVALID)) {
            // raise InvalidKeyException
            System.out.println(new InvalidKeyException().getMessage());;
        }
    }

    public void run(){
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            // process the userInput
            String[] userInputSplit = userInput.split(" ");
            String userInputKey = userInputSplit[0];
            this.determineCurrentKey(userInputKey);
            // check for end the session
            if (this.currentKey.equals(KeyEnum.EXITKEY)){
                break;
            }

            // continue for the functionality
            String inputDetail = "";
            String from = "";
            String to = "";
            switch (this.currentKey) {
                case DEADLINE:
                    try {
                        if (userInput.length() <= 9) {
                            throw new EmptyBodyException();
                        }
                        inputDetail = userInput.substring(9, userInput.indexOf("/by"));
                        to = userInput.substring(userInput.indexOf("/by")+4);
                        try {
                            Task task = this.addTask(inputDetail, from, to);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                            this.taskCountToString();
                        } catch (WrongFormatException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } catch (EmptyBodyException e){
                        System.out.println(e.getMessage());
                        break;
                    } catch (Exception e) {
                        System.out.println(new WrongFormatException("\"deadline content /by time\"").getMessage());
                        break;
                    }
                    break;
                case TODO:
                    try {
                        if (userInput.length() <= 5) {
                            throw new EmptyBodyException();
                        }
                        inputDetail = userInput.substring(5);
                        try {
                            Task task = this.addTask(inputDetail, from, to);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                            this.taskCountToString();
                        } catch (WrongFormatException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } catch (EmptyBodyException e){
                        System.out.println(e.getMessage());
                        break;
                    } catch (Exception e) {
                        System.out.println(new WrongFormatException("\"todo content\"").getMessage());
                        break;
                    }
                    break;
                case EVENT:
                    try {
                        if (userInput.length() <= 6) {
                            throw new EmptyBodyException();
                        }
                        inputDetail = userInput.substring(6, userInput.indexOf("/from"));
                        from = userInput.substring(userInput.indexOf("/from")+6, userInput.indexOf("/to")-1);
                        to = userInput.substring(userInput.indexOf("/to")+4);
                        try {
                            Task task = this.addTask(inputDetail, from, to);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(task);
                            this.taskCountToString();
                        } catch (WrongFormatException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } catch (EmptyBodyException e){
                        System.out.println(e.getMessage());
                        break;
                    } catch (Exception e) {
                        System.out.println(new WrongFormatException("\"event content /from time /to time\"").getMessage());
                        break;
                    }
                    break;
                case LIST:
                    this.listTask();
                    break;
                case MARK:
                    try {
                        Integer index = new Integer(userInputSplit[1]) - 1;
                        Task markedTask = this.markTaskById(index, true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(markedTask);
                    } catch (NumberFormatException e) {
                        // Error when the user input mark key is not a number
                        System.out.println("Please input a valid number. Example: \"mark 1\"");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        this.taskCountToString();
                        System.out.println("Please input a number within the range.");
                        System.out.println("Your task list is shown below: ");
                        this.listTask();
                        break;
                    }
                    break;
                case UNMARK:
                    try {
                        Integer index = new Integer(userInputSplit[1]) - 1;
                        Task unMarkedTask = this.markTaskById(index, false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(unMarkedTask);
                    } catch (NumberFormatException e) {
                        // Error when the user input mark key is not a number
                        System.out.println("Please input a valid number. Example: \"unmark 1\"");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        this.taskCountToString();
                        System.out.println("Please input a number within the range.");
                        System.out.println("Your task list is shown below: ");
                        this.listTask();
                        break;
                    }
                    break;
                case DELETE:
                    try {
                        Integer index = new Integer(userInputSplit[1]) - 1;
                        Task deletedTask = this.deleteTaskById(index);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(deletedTask);
                        this.taskCountToString();
                    } catch (NumberFormatException e) {
                        // Error when the user input mark key is not a number
                        System.out.println("Please input a valid number. Example: \"delete 1\"");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        this.taskCountToString();
                        System.out.println("Please input a number within the range.");
                        System.out.println("Your task list is shown below: ");
                        this.listTask();
                        break;
                    }
                    break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputArr.size(); i++) {
            System.out.println((i+1)+". " + inputArr.get(i));
        }
    }

    public Task addTask(String detail, String from, String to) throws WrongFormatException {
        Task task = null;
        switch (this.currentKey) {
            case DEADLINE:
                task = new Deadline(false, detail, to);
                break;
            case TODO:
                task = new Todo(false, detail);
                break;
            case EVENT:
                task = new Event(false, detail, from, to);
                break;
        }
        // Throw empty body exception if the added
        if (task==null || detail.length() == 0) {
            throw new WrongFormatException("The task body can not be empty. Please specify the task you want to add.");
        }
        inputArr.add(task);
        return task;
    }

    public Task markTaskById(Integer id, Boolean status) {
        this.inputArr.get(id).setStatus(status);
        return this.inputArr.get(id);
    }

    public Task deleteTaskById(Integer id){
        Task taskToDelete = this.inputArr.get(id);
        this.inputArr.remove(taskToDelete);
        return taskToDelete;
    }
}
