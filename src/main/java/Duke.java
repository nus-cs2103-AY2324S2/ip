import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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
                fileStorage.saveStorage(taskList.getTaskStore());
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
                fileStorage.saveStorage(taskList.getTaskStore());
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
                fileStorage.saveStorage(taskList.getTaskStore());
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
            fileStorage.saveStorage(taskList.getTaskStore());
        }
        return true;
    }

    public static Task parseFileLine(String fileLine) throws DukeException{
        Task t;
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        if (fileLine.charAt(1) == 'T') {
            String infoString = fileLine.substring(7);
            t = new ToDo(infoString);
        } else if (fileLine.charAt(1) == 'D') {
            String infoString = fileLine.substring(7);
            String[] strArr = infoString.split(" ");
            int byIndex = Arrays.asList(strArr).indexOf("(by:");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 0; k < byIndex; k++) {
                if (k == byIndex - 1) {
                    descBuilder.append(strArr[k]);
                } else {
                    descBuilder.append(strArr[k] + " ");
                }
            }
            StringBuilder byBuilder = new StringBuilder();
            for (int k = byIndex + 1; k < strArr.length; k++) {
                if (k == strArr.length - 1) {
                    byBuilder.append(strArr[k]);
                } else {
                    byBuilder.append(strArr[k] + " ");
                }
            }
            if (byBuilder.toString().length() == 0) {
                throw new DukeException("Wrong File Format");
            } else {
                String dateTimeStr = byBuilder.toString();
                dateTimeStr = dateTimeStr.substring(0, dateTimeStr.length() - 1);
                LocalDateTime byTime = LocalDateTime.parse(dateTimeStr, dTFormatter);
                t = new Deadline(descBuilder.toString(), byTime);
            }
        } else if (fileLine.charAt(1) == 'E') {
            String infoString = fileLine.substring(7);
            String[] strArr = infoString.split(" ");
            int fromIndex = Arrays.asList(strArr).indexOf("(from:");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 0; k < fromIndex; k++) {
                if (k == fromIndex - 1) {
                    descBuilder.append(strArr[k]);
                } else {
                    descBuilder.append(strArr[k] + " ");
                }
            }
            int toIndex = Arrays.asList(strArr).indexOf("to:");
            StringBuilder fromBuilder = new StringBuilder();
            for (int k = fromIndex + 1; k < toIndex; k++) {
                if (k == toIndex - 1) {
                    fromBuilder.append(strArr[k]);
                } else {
                    fromBuilder.append(strArr[k] + " ");
                }
            }
            StringBuilder toBuilder = new StringBuilder();
            for (int k = toIndex + 1; k < strArr.length; k++) {
                if (k == strArr.length - 1) {
                    toBuilder.append(strArr[k]);
                } else {
                    toBuilder.append(strArr[k] + " ");
                }
            }
            if (fromBuilder.toString().length() == 0 || toBuilder.toString().length() == 0) {
                throw new DukeException("Wrong File Format");
            } else {
                String fromDateTimeStr = fromBuilder.toString();
                String toDateTimeStr = toBuilder.toString();
                fromDateTimeStr = fromDateTimeStr.substring(0, fromDateTimeStr.length() - 1);
                toDateTimeStr = toDateTimeStr.substring(0, toDateTimeStr.length() - 1);
                LocalDateTime fromTime = LocalDateTime.parse(fromDateTimeStr, dTFormatter);
                LocalDateTime toTime = LocalDateTime.parse(toDateTimeStr, dTFormatter);
                t = new Event(descBuilder.toString(), fromTime, toTime);
            }
        } else {
            throw new DukeException("Wrong File Format");
        }
        return t;
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke.greeting();
        Scanner s = new Scanner(System.in);
        fileStorage = new Storage();
        if (fileStorage.isOccupied){
            System.out.println("here");
            ArrayList<Task> loadedList = fileStorage.loadStorage();
            taskList = new TaskList(loadedList);
        } else {
            System.out.println("here2");
            taskList = new TaskList();
        }

        while (true){
            String userInput = s.nextLine();
            if(!commandParser(userInput, false)){
                break;
            }
        }
    }
}
