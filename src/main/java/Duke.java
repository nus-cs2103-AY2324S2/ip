import exceptions.EmptyBodyException;
import exceptions.WrongFormatException;
import exceptions.InvalidKeyException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String EXITKEY = "bye";
    private static final String LISTKEY = "list";
    private static final String MARKKEY = "mark";
    private static final String UNMARKKEY = "unmark";
    private static final String E_KEY = "event";
    private static final String D_KEY = "deadline";
    private static final String T_KEY = "todo";
    private static final String DELETEKEY = "delete";

    private static final String[] KEY_LIST = {EXITKEY, LISTKEY, MARKKEY, UNMARKKEY, E_KEY, D_KEY, T_KEY, DELETEKEY};

    private ArrayList<Task> inputArr = new ArrayList<>();

    public Integer getNumOfTasks() {
        return inputArr.size();
    }

    public void taskCountToString() {
        System.out.println("Now you have "+ this.getNumOfTasks() +" tasks in the list." );
    }

    public void run(Duke duke){
        String userInput = "";
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while(!userInput.equals(EXITKEY)) {
            userInput = scanner.nextLine();
            if (EXITKEY.equals(userInput)){
                break;
            }
            // process the userInput
            String[] userInputSplit = userInput.split(" ");
            String userInputKey = userInputSplit[0];
            // Check if the key is valid
            Boolean isValidKey = false;
            for(String key:KEY_LIST) {
                if (key.equals(userInputKey)){
                    isValidKey = true;
                }
            }
            if (!isValidKey) {
                System.out.println(new InvalidKeyException().getMessage());;
            }

            String inputDetail = "";
            String from = "";
            String to = "";
            if (userInputKey.equals(D_KEY)) {
                try {
                    if (userInput.length() <= 9) {
                        throw new EmptyBodyException();
                    }
                    inputDetail = userInput.substring(9, userInput.indexOf("/by"));
                    to = userInput.substring(userInput.indexOf("/by")+4);
                } catch (EmptyBodyException e){
                    System.out.println(e.getMessage());
                    continue;
                } catch (Exception e) {
                    System.out.println(new WrongFormatException("\"deadline content /by time\"").getMessage());
                    continue;
                }
            } else if (userInputKey.equals(T_KEY)) {
                try {
                    if (userInput.length() <= 5) {
                        throw new EmptyBodyException();
                    }
                    inputDetail = userInput.substring(5);
                } catch (EmptyBodyException e){
                    System.out.println(e.getMessage());
                    continue;
                } catch (Exception e) {
                    System.out.println(new WrongFormatException("\"todo content\"").getMessage());
                    continue;
                }
            } else if (userInputKey.equals(E_KEY)){
                try {
                    if (userInput.length() <= 6) {
                        throw new EmptyBodyException();
                    }
                    inputDetail = userInput.substring(6, userInput.indexOf("/from"));
                    from = userInput.substring(userInput.indexOf("/from")+6, userInput.indexOf("/to")-1);
                    to = userInput.substring(userInput.indexOf("/to")+4);
                } catch (EmptyBodyException e){
                    System.out.println(e.getMessage());
                    continue;
                } catch (Exception e) {
                    System.out.println(new WrongFormatException("\"event content /from time /to time\"").getMessage());
                    continue;
                }
            }

            if (LISTKEY.equals(userInputKey)) {
                duke.listTask();
            } else if (MARKKEY.equals(userInputKey)){
                try {
                    Integer index = new Integer(userInputSplit[1]) - 1;
                    Task markedTask = duke.markTaskById(index, true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markedTask);
                } catch (NumberFormatException e) {
                    // Error when the user input mark key is not a number
                    System.out.println("Please input a valid number. Example: \"mark 1\"");
                } catch (IndexOutOfBoundsException e) {
                    duke.taskCountToString();
                    System.out.println("Please input a number within the range.");
                    System.out.println("Your task list is shown below: ");
                    duke.listTask();
                }
            } else if (UNMARKKEY.equals(userInputKey)){
                try {
                    Integer index = new Integer(userInputSplit[1]) - 1;
                    Task unMarkedTask = duke.markTaskById(index, false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unMarkedTask);
                } catch (NumberFormatException e) {
                    // Error when the user input mark key is not a number
                    System.out.println("Please input a valid number. Example: \"unmark 1\"");
                } catch (IndexOutOfBoundsException e) {
                    duke.taskCountToString();
                    System.out.println("Please input a number within the range.");
                    System.out.println("Your task list is shown below: ");
                    duke.listTask();
                }

            } else if (userInputKey.equals(D_KEY) || userInputKey.equals(T_KEY) || userInputKey.equals(E_KEY)){
                // add different type of tasks
                try {
                    Task task = duke.addTask(userInputKey, inputDetail, from, to);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    duke.taskCountToString();
                } catch (WrongFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInputKey.equals(DELETEKEY)) {
                try {
                    Integer index = new Integer(userInputSplit[1]) - 1;
                    Task deletedTask = duke.deleteTaskById(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask);
                    duke.taskCountToString();
                } catch (NumberFormatException e) {
                    // Error when the user input mark key is not a number
                    System.out.println("Please input a valid number. Example: \"delete 1\"");
                } catch (IndexOutOfBoundsException e) {
                    duke.taskCountToString();
                    System.out.println("Please input a number within the range.");
                    System.out.println("Your task list is shown below: ");
                    duke.listTask();
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run(duke);
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputArr.size(); i++) {
            System.out.println((i+1)+". " + inputArr.get(i));
        }
    }

    public Task addTask(String key, String detail, String from, String to) throws WrongFormatException {
        Task task = null;
        switch (key) {
            case D_KEY:
                task = new Deadline(false, detail, to);
                break;
            case T_KEY:
                task = new Todo(false, detail);
                break;
            case E_KEY:
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
