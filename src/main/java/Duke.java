import java.io.IOException;
import java.util.*;

public class Duke {

    private static TaskList taskList;

    private static Storage fileStorage;
    private static void greeting() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you?\n" +
                "\nDid you know that the noise penguins make are called \"honks\"");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon! **HONK HONK**");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    protected static boolean commandParser(String userInput, boolean isLoaded) throws DukeException, IOException{
        if (userInput.toLowerCase().equals("bye")) {
            exit();
            return false;
        } else if (userInput.toLowerCase().equals("list")) {
            taskList.listTasks();
            return true;
        } else if (userInput.toLowerCase().startsWith("mark")) {
            String[] inputArr = userInput.split(" ");
            try {
                int index = Integer.parseInt(inputArr[1]) - 1;
                taskList.markTask(index);
                fileStorage.saveStorage(userInput);
                return true;
            } catch (NumberFormatException e) {
                throw new DukeException("*HONK* Pengu thinks you need a valid task number to mark, " +
                        "consider checking the list command");
            }
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            String[] inputArr = userInput.split(" ");
            try {
                int index = Integer.parseInt(inputArr[1]) - 1;
                taskList.unmarkTask(index);
                fileStorage.saveStorage(userInput);
                return true;
            } catch (NumberFormatException e) {
                throw new DukeException("*HONK* Pengu thinks you need a valid task number to unmark, " +
                        "consider checking the list command");
            }
        } else if (userInput.toLowerCase().startsWith("delete")) {
            String[] inputArr = userInput.split(" ");
            try {
                int index = Integer.parseInt(inputArr[1]) - 1;
                taskList.deleteTask(index);
                fileStorage.saveStorage(userInput);
                return true;
            } catch (NumberFormatException e) {
                throw new DukeException("*HONK* Pengu thinks you need a valid task number to delete, " +
                        "consider checking the list command");
            }
        }
        String keyword = userInput.split(" ")[0].toLowerCase();
        if (!(keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event"))){
            throw new DukeException("*HONK* Pengu has never seen such a command before, some commands Pengu can do are: list, todo, deadline");
        }
        if (isLoaded) {
            taskList.addTask(userInput, true);
        } else {
            taskList.addTask(userInput, false);
            fileStorage.saveStorage(userInput);
        }
        return true;
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke.greeting();
        Scanner s = new Scanner(System.in);
        taskList = new TaskList();
        fileStorage = new Storage();
        while (true){
            String userInput = s.nextLine();
            if(!commandParser(userInput, false)){
                break;
            }
        }
    }
}
