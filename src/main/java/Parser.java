import java.time.format.DateTimeParseException;

public class Parser {
    public static Task decodeTask(String line){
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                return new Todo(parts[2], Boolean.parseBoolean(parts[1]));
            case "D":
                return new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3]);
            case "E":
                String[] time = parts[3].split("-");
                String from = time[0];
                String to  = time[1];
                return new Event(parts[2], Boolean.parseBoolean(parts[1]), from, to);
            default:
                throw new IllegalArgumentException("Unknown task type: " + parts[0]);
        }
    }

    public static void parse(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        if (userInput.toLowerCase().startsWith("todo")) {
            String taskDetails = userInput.substring(5).trim();
            Task todo = new Todo(taskDetails, false);
            taskList.addTask(todo, storage);
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            String taskDetails = userInput.substring(9).trim();
            String[] details = taskDetails.split(" /by ", 2);
            try {
                Task deadline = new Deadline(details[0], false, details[1]);
                taskList.addTask(deadline, storage);
            } catch (DateTimeParseException e) {
                throw e;
            }
        } else if (userInput.toLowerCase().startsWith("event")) {
            String taskDetails = userInput.substring(6).trim();
            String[] details = taskDetails.split(" /from ", 2);
            String[] times = details[1].split(" /to ", 2);
            try {
                Task event = new Event(details[0], false, times[0], times[1]);
                taskList.addTask(event, storage);
            } catch (DateTimeParseException e) {
                throw e;
            }
        } else if ("list".equalsIgnoreCase(userInput)) {
            taskList.printTasks();
        } else if (userInput.toLowerCase().startsWith("mark")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.markTask(taskNumber, true, storage);
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.markTask(taskNumber, false, storage);
        } else if (userInput.toLowerCase().startsWith("delete")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            taskList.deleteTask(taskNumber, storage);
        } else {
            throw new ChatbotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
