package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke() {
        this.ui = new Ui();

    }

    static ArrayList<Task> taskList = new ArrayList<>();
    static int currIndex = 0;
    enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
        static CommandType getCommandType(String command) {
            try {
                return valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
        }
    }

    public class TaskManager {
        private ArrayList<Task> taskList;
        //private final String FILE_PATH = "./data/duke.txt";

        public TaskManager(ArrayList<Task> taskList) {
            this.taskList = taskList;
            loadTasksFromFile();
            currIndex = taskList.size();
        }

        public void loadTasksFromFile() {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // create directory if does not exist
            }
            File file = new File(directory, "duke.txt");
            try {
                file.createNewFile();
                Scanner fileScanner = new Scanner(file);
                int lines = 0;
                while (fileScanner.hasNext()) {
                    String taskLine = fileScanner.nextLine();
                    Task task = parseTask(taskLine); // Implement this method based on your task format
                    if (task != null) {
                        taskList.add(task);
                    }
                    lines++;
                }
                ui.loadFiles(lines);
            } catch(IOException e){
                ui.createFileError(e);
            }

        }

        private Task parseTask(String line) {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                ui.taskFormatError(line);
                return null;
            }

            try {
                String type = parts[0];
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.markDone();
                    }
                    return todo;
                case "D":
                    if (parts.length < 4) {
                        ui.deadlineMissingBy(line);
                        return null;
                    }
                    String by = parts[3].trim();
                    Deadline deadline = new Deadline(description, parseDateString(by));
                    if (isDone) {
                        deadline.markDone();
                    }
                    return deadline;
                case "E":
                    if (parts.length < 5) { // missing from/to or both
                        ui.eventMissingParam(line);
                        return null;
                    }
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    Event event = new Event(description, parseDateString(from), parseDateString(to));
                    if (isDone) event.markDone();
                    return event;
                default:
                    ui.unknownTaskType(type);
                    return null;
                }
            } catch (Exception e) {
                ui.genericTaskError(e, line);
                return null;
            }
        }
        private String parseDateString(String dateString) throws DateTimeParseException{
            try {
                LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                ui.parseDateError(dateString);
                return null;
            }
        }
        public String formatTaskForFile(Task task) {
            String returnString = "";
            String type =
                    task instanceof Todo ? "T" :
                    task instanceof Deadline ? "D" :
                            task instanceof Event ? "E" : "";
            int status = task.isDone();
            String details = task.getDescription();
            returnString += type + " | " + status + " | " + details;
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                returnString += " | " + deadline.getBy();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                returnString += " | " + event.getFrom() + " | " + event.getTo();
            }
            return returnString;
        }
        public void saveTaskToFile(Task task) {
            try {
                FileWriter fileWriter = new FileWriter("./data/duke.txt", true);
                String taskLine = formatTaskForFile(task);
                fileWriter.write(taskLine + "\n");
                fileWriter.close();
            } catch (IOException e) {
                ui.saveError(e);
            }

        }
        public void saveAllTasksToFile() {
            // overwrites all files for mark/unmark functions
            try {
                FileWriter fileWriter = new FileWriter("./data/duke.txt", false);
                for (Task task : taskList) {
                    String taskLine = formatTaskForFile(task);
                    fileWriter.write(taskLine + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                ui.saveError(e);
            }
        }
    }


    public void listFunction() {
        TaskManager taskManager = new TaskManager(taskList);
        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while(!currLine.equals("bye")) {
            String[] command = currLine.split(" ", 2);
            CommandType commandType = CommandType.getCommandType(command[0]);
            switch (commandType) {
            case LIST:
                ui.displayList(taskList);
                break;
            case MARK:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < currIndex && taskIndex >= 0) {
                        Task task = taskList.get(taskIndex);
                        task.markDone();
                        ui.markTask(task.toString());
                        taskManager.saveAllTasksToFile();
                        break;
                    } else { // out of range
                        ui.taskOutOfBounds(currIndex);
                    }
                } catch (NumberFormatException e) {
                    ui.invalidTaskNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.taskOutOfBounds(currIndex);
                }
                break;
            case UNMARK:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < currIndex && taskIndex >= 0) {
                        Task task = taskList.get(taskIndex);
                        task.markUndone();
                        ui.unmarkTask(task.toString());
                        taskManager.saveAllTasksToFile();
                        break;
                    } else { // out of range
                        ui.invalidTaskNumber(command[1]);
                    }
                } catch (NumberFormatException e) {
                    ui.invalidTaskNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.taskOutOfBounds(currIndex);
                }

                break;
            case TODO:
                try {
                    Task newTodo = new Todo(command[1]);
                    taskList.add(newTodo);
                    currIndex++;
                    ui.addMessage(newTodo, currIndex);
                    taskManager.saveTaskToFile(newTodo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.todoFormatError();
                }

                break;
            case DEADLINE:
                try {
                    String[] details = command[1].split(" /by ");
                    String description = details[0];
                    String by = details[1];

                    Deadline newDeadline = new Deadline(description, by);
                    if (newDeadline.hasValidDate()) {
                        taskList.add(newDeadline);
                        currIndex++;
                        ui.addMessage(newDeadline, currIndex);
                        taskManager.saveTaskToFile(newDeadline);
                    } else {
                        ui.deadlineDateError();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.deadlineFormatError();
                }
                break;
            case EVENT:
                try {
                    String[] details = command[1].split(" /from ");
                    String description = details[0];
                    String[] fromTo = details[1].split(" /to ");
                    String from = fromTo[0];
                    String to = fromTo[1];

                    Event newEvent = new Event(description, from, to);
                    if (newEvent.hasValidDate()) {
                        taskList.add(newEvent);
                        currIndex++;
                        ui.addMessage(newEvent, currIndex);
                        taskManager.saveTaskToFile(newEvent);
                    } else {
                        ui.eventDateError();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.eventFormatError();
                }
                break;
            case DELETE:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        ui.deleteTask(removedTask.toString(), taskList);
                        taskManager.saveAllTasksToFile();
                        currIndex--;
                    } else {
                        ui.invalidTaskNumber(String.valueOf(taskIndex + 1));
                    }
                } catch (NumberFormatException e) {
                    ui.invalidTaskNumber(command[1]);
                }
                break;
            default:
                ui.unknownCommand();
                break;
            }
            currLine = scanner.nextLine();
        }
        ui.exit();

    }






    public static void main(String[] args){
        new Duke().listFunction();
        // listFunction();
    }
}

