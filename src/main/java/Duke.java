import java.util.*;

public class Duke {

    private static ArrayList<Task> storage = new ArrayList<>();
    private static void greeting() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you?\n" +
                "\nDid you know that the noise penguins make are called \"honks\"");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon! **HONK HONK**");
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void addTask(String description) throws DukeException{
        Task newTask;
        validTaskCommand(description);
        if (description.toLowerCase().startsWith("todo")) {
            String[] descriptionArr = description.split(" ");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new ToDo(descBuilder.toString());
        } else if (description.toLowerCase().startsWith("deadline")) {
            String[] descriptionArr = description.split(" ");
            int byIndex = Arrays.asList(descriptionArr).indexOf("/by");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < byIndex; k++) {
                if (k == byIndex - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            StringBuilder byBuilder = new StringBuilder();
            for (int k = byIndex + 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    byBuilder.append(descriptionArr[k]);
                } else {
                    byBuilder.append(descriptionArr[k] + " ");
                }
            }
            /*if (descriptionArr[2].toLowerCase().equals("/by")) {
                System.out.println("*HONKS ANGRILIY* Pengu thinks that the description of the deadline has to be followed by '/by'");
            }*/
            newTask = new Deadline(descBuilder.toString(), byBuilder.toString());
        } else if (description.toLowerCase().startsWith("event")) {
            String[] descriptionArr = description.split(" ");
            int fromIndex = Arrays.asList(descriptionArr).indexOf("/from");
            StringBuilder descBuilder = new StringBuilder();
            for (int k = 1; k < fromIndex; k++) {
                if (k == fromIndex - 1) {
                    descBuilder.append(descriptionArr[k]);
                } else {
                    descBuilder.append(descriptionArr[k] + " ");
                }
            }
            int toIndex = Arrays.asList(descriptionArr).indexOf("/to");
            StringBuilder fromBuilder = new StringBuilder();
            for (int k = fromIndex + 1; k < toIndex; k++) {
                if (k == toIndex - 1) {
                    fromBuilder.append(descriptionArr[k]);
                } else {
                    fromBuilder.append(descriptionArr[k] + " ");
                }
            }
            StringBuilder toBuilder = new StringBuilder();
            for (int k = toIndex + 1; k < descriptionArr.length; k++) {
                if (k == descriptionArr.length - 1) {
                    toBuilder.append(descriptionArr[k]);
                } else {
                    toBuilder.append(descriptionArr[k] + " ");
                }
            }
            newTask = new Event(descBuilder.toString(), fromBuilder.toString(), toBuilder.toString());
        } 
        Duke.storage.add(newTask);
        System.out.println(String.format("*Honk! Honk!* Pengu has added this task:\n" + newTask.toString()
                + "\nGet back to work! you have %s tasks in the list\n"
                + "―――――――――――――――――――――――――――――――――――", Duke.storage.size()));
    }

    private static void listTasks() {
        int storageSize = Duke.storage.size();
        System.out.println("*Honk!* Pengu has listed your current tasks below:");
        for (int k = 0; k < storageSize; k++){
            int curr = k + 1;
            Task currTask = storage.get(k);
            System.out.println(curr + ". " + currTask.toString());
        }
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void markTask(int index) {
        Task currTask = storage.get(index);
        currTask.updateTask(true);
        System.out.println("*Honk!* Good Job!, Pengu has marked this task as done:\n" + currTask.toString());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static void unmarkTask(int index) {
        Task currTask = storage.get(index);
        currTask.updateTask(false);
        System.out.println("*Honk!* Pengu has marked this task as not done yet:\n" + currTask.toString());
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    private static boolean validTaskCommand(String str) throws DukeException {
        List<String> strArr = Arrays.asList(str.split(" "));
        String keyword = str.split(" ")[0].toLowerCase();
        System.out.println(keyword);
        if (!(keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event"))){
            throw new DukeException("*HONK* Pengu has never seen such a command before, some commands Pengu can do are: list, todo, deadline");
        } else if (keyword.equals("todo") && !(strArr.size() > 1)) {
            throw new DukeException("*HONK* Pengu needs a To Do Task description to record this down");
        } else if (keyword.equals("deadline") && !(strArr.contains("/by"))) {
            throw new DukeException("*HONK* Pengu needs a /by followed by a end date for your task");
        } else if (keyword.equals("event") && !(strArr.contains("/from") && (strArr.contains("/to")))) {
            throw new DukeException("*HONK* Pengu needs a /from followed by a from date and a /to followed by a end date for your task");
        } else if (keyword.equals("deadline") && strArr.get(1).equals("/by")) {
            throw new DukeException("Honk* Pengu cannot accept a deadline task without a description");
        } else if (keyword.equals("event") && (strArr.get(1).equals("/from") || strArr.get(1).equals("/to"))) {
            throw new DukeException("*Honk* Pengu cannot accept a event without a description");
        } else {
            return true;
        }
    }
    public static void main(String[] args) throws DukeException {
        greeting();
        Scanner s = new Scanner(System.in);
        while (true){
            String userInput = s.nextLine();
            if (userInput.toLowerCase().equals("bye")) {
                exit();
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                listTasks();
                continue;
            } else if (userInput.toLowerCase().startsWith("mark")) {
                String[] inputArr = userInput.split(" ");
                int index = Integer.parseInt(inputArr[1]) - 1;
                markTask(index);
                continue;
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                String[] inputArr = userInput.split(" ");
                int index = Integer.parseInt(inputArr[1]) - 1;
                unmarkTask(index);
                continue;
            }
            addTask(userInput);
        }
    }
}
