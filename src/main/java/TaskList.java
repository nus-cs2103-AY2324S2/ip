import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static final String FILE_PATH = "./data/saopigTaskList.txt";
    private static final String FILE_DIRECTORY = "./data";
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    public static void saveTaskList(TaskList taskList) {
        try {
            if (!new File(FILE_DIRECTORY).exists()) {
                new File(FILE_DIRECTORY).mkdirs();
                System.out.println("Creating new directory");
            }
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Creating new file");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            if (taskList.getSize() == 0) {
                fileWriter.write("");
            } else {
                for (Task task : taskList.getTasks()) {
                    String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
                    String isDone = task.getIsDoneState() ? "1" : "0";
                    String description = task.getDescription();
                    if (task instanceof Deadline) {
                        description += " %&///&% " + ((Deadline) task).getBy();
                    } else if (task instanceof Event) {
                        description += " %&///&% " + ((Event) task).getStartTime() + " %&///&% " + ((Event) task).getEndTime();
                    }
                    fileWriter.write(taskType + " %&///&% " + isDone + " %&///&% " + description + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh no! I'm sorry, but I couldn't save your tasks.\n " +
                    "Please try again, or type 'bye' to exit.");
        }
    }

    public static TaskList loadTaskList(TaskList taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                Saopig.speakWithHorizontalLines("\n" +
                        "Oh dear, it looks like there are no tasks stored yet!\n " +
                        "But that's alright.\n " +
                        "It gives us a chance to start fresh and dream up some new plans.\n " +
                        "Whenever you're ready to add tasks, I'll be right here to assist you.\n " +
                        "Let's make it a magical journey together!");
                return taskList;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] splitInput = input.split(" %&///&% ");
                switch (splitInput[0]) {
                case "T":
                    taskList.tasks.add(new Todo(splitInput[2]));
                    break;
                case "D":
                    taskList.tasks.add(new Deadline(splitInput[2], splitInput[3]));
                    break;
                case "E":
                    taskList.tasks.add(new Event(splitInput[2], splitInput[3], splitInput[4]));
                    break;
                }
                if (splitInput[1].equals("1")) {
                    taskList.getTask(taskList.getSize() - 1).markAsDone();
                }
            }
            scanner.close();
            Saopig.speakWithHorizontalLines("Successfully loaded your previous task list!\n " +
                    "Now you have " + taskList.getSize() + " tasks in the list.\n");
        } catch (IOException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh no! I'm sorry, but I couldn't load your tasks.\n " +
                    "Please try again, or type 'bye' to exit.");
        }
        return taskList;
    }

    public void markTaskAsDone(String input) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = this.tasks.get(index);
            task.markAsDone();
            saveTaskList(this);
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh, splendid! Your task: {" + task.toString() + "} has been marked as done successfully.\n " +
                    "Isn't it just wonderful when things go exactly as planned?\n " +
                    "I'm so proud of you for getting it done!");
        } catch (SaopigInvaildSizeException e) {
            Saopig.speakWithHorizontalLines(e.getMessage() +
                    "\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have forgotten to give an argument for the mark command.\n " +
                    "Don't worry, it happens to most of us.\n " +
                    "Just add the index for the task you'd like to mark, and you'll be all set.\n " +
                    "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list " +
                    "or your input is not a number.");
        }
    }

    public void unmarkTaskAsDone(String input) {
        try {
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = this.tasks.get(index);
            task.unmarkAsDone();
            saveTaskList(this);
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh, you've unmarked task: {" + task.toString() + "}?\n " +
                    "No worries at all! It's always okay to reevaluate and adjust your plans.\n " +
                    "Flexibility is a sign of strength, you know. Keep up the great work!");
        } catch (SaopigInvaildSizeException e) {
            Saopig.speakWithHorizontalLines(e.getMessage() +
                    "\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have forgotten to give an argument for the unmark command.\n " +
                    "Don't worry, it happens to most of us.\n " +
                    "Just add the index for the task you'd like to unmark, and you'll be all set.\n " +
                    "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list " +
                    "or your input is not a number.");
        }
    }

    public void addTask(String input) {
        Task task = new Task(input);
        this.tasks.add(task);
        saveTaskList(this);
        Saopig.speakWithHorizontalLines("\n" +
                "Oh, splendid! Your task: {" + input + "} has been added successfully.\n " +
                "Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void addTodoTask(String input) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            String processedInput = input.substring(5);
            Todo task = new Todo(processedInput);
            this.tasks.add(task);
            saveTaskList(this);
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh, splendid! Your Todo task: {" + task.toString() + "} has been added successfully.\n " +
                    "Now you have " + this.tasks.size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            Saopig.speakWithHorizontalLines(e.getMessage() +
                    "\n" +
                    "Oh, it looks like the 'todo' command is missing some details for the task.\n " +
                    "No problem at all!\n " +
                    "Just add a bit more information about what you'd like to do, " +
                    "and it will be as perfect as a sunny day.\n " +
                    "You're doing wonderfully! ");
        }
    }

    public void addDeadlineTask(String input) {
        try {
            checkValue(input.length(), 10, Integer.MAX_VALUE);
            String splitInput = input.substring(9);
            String[] splitArguments = splitInput.split(" /by ");
            if (splitArguments.length != 2) {
                Saopig.speakWithHorizontalLines("\n" +
                        "Whoopsie!\n " +
                        "It seems like you may have forgotten to write the deadline time.");
            }
            Deadline task = new Deadline(splitArguments[0], splitArguments[1]);
            this.tasks.add(task);
            saveTaskList(this);
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh, splendid! Your Deadline task: {" + task.toString() + "} has been added successfully.\n " +
                    "Now you have " + this.tasks.size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            Saopig.speakWithHorizontalLines(e.getMessage() +
                    "\n" +
                    "Oh, it looks like the 'deadline' command is missing some details for the task.\n " +
                    "No problem at all!\n " +
                    "Just add a bit more information about what you'd like to do, " +
                    "and it will be as perfect as a sunny day.\n " +
                    "You're doing wonderfully! ");
        } catch (ArrayIndexOutOfBoundsException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Whoopsie! " +
                    "It seems like you may have forgotten to write the deadline time " +
                    "or didn't use ' /by ' in your command.\n " +
                    "Remember there is a space before and after '/by'.\n " +
                    "It's a tiny detail, " +
                    "but oh so important! Just add the deadline after '/by ', " +
                    "and you'll be as organized as a library on a quiet morning.\n " +
                    "You're doing an amazing job! ");
        }
    }

    public void addEventTask(String input) {
        try {
            checkValue(input.length(), 7, Integer.MAX_VALUE);
            String splitInput = input.substring(6);
            String[] splitArguments = splitInput.split("/");
            if (splitArguments.length != 3) {
                Saopig.speakWithHorizontalLines("\n" +
                        "Whoopsie!\n " +
                        "It seems like you may have forgotten to write the event start or end time ");
            }
            String description = splitArguments[0].trim();
            String fromTime = splitArguments[1].trim().substring(5); // Remove "from " prefix
            String toTime = splitArguments[2].trim().substring(3); // Remove "to " prefix
            Event task = new Event(description, fromTime, toTime);
            this.tasks.add(task);
            saveTaskList(this);
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh, splendid! Your Event task: {" + task.toString() + "} has been added successfully.\n " +
                    "Isn't it just wonderful when things go exactly as planned?\n " +
                    "I'm so proud of you for getting it done!\n " +
                    "Now you have " + this.tasks.size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            Saopig.speakWithHorizontalLines(e.getMessage() +
                    "\n" +
                    "Oh, it looks like the 'event' command is missing some details for the task.\n " +
                    "No problem at all!\n " +
                    "Just add a bit more information about what you'd like to do, " +
                    "and it will be as perfect as a sunny day.\n " +
                    "You're doing wonderfully! ");
        } catch (ArrayIndexOutOfBoundsException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Whoopsie!\n " +
                    "It seems like you may have forgotten to write the event start and end time\n " +
                    "or didn't use ' /from ' or ' /to ' in your command.\n " +
                    "Remember there is a space before and after '/from' and ' /to '.\n " +
                    "It's a tiny detail, " +
                    "but oh so important! Just add the deadline after '/by ', " +
                    "and you'll be as organized as a library on a quiet morning.\n " +
                    "You're doing an amazing job! ");
        }
    }

    public void deleteTask(String input) {
        try {
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(7)) - 1;
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh, splendid! Your task: {" + this.tasks.get(index).toString() + "} has been deleted successfully.\n " +
                    "Now you have " + (this.tasks.size() - 1) + " tasks in the list.");
            this.tasks.remove(index);
            saveTaskList(this);
        } catch (SaopigInvaildSizeException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have forgotten to give an argument for the delete command.\n " +
                    "Don't worry, it happens to most of us.\n " +
                    "Just add the index for the task you'd like to delete, and you'll be all set.\n " +
                    "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list " +
                    "or your input is not a number.");
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void listTasks() {
        if (this.tasks.isEmpty()) {
            Saopig.speakWithHorizontalLines("\n" +
                    "Oh dear, it looks like there are no tasks yet!\n " +
                    "But that's alright.\n " +
                    "It gives us a chance to start fresh and dream up some new plans.\n " +
                    "Whenever you're ready to add tasks, I'll be right here to assist you.\n " +
                    "Let's make it a magical journey together!");
            return;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            Saopig.speak((i + 1) + ". " + task.toString());
        }
        Saopig.speak("____________________________________________________________");
    }
}
