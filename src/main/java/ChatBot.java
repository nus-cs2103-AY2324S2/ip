import java.io.IOException;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.StandardOpenOption;

public class ChatBot {
    private String name;
    private String icon;
    private List<String> greetings;
    private List<String> farewells;
    private List<Task> tasks;

    public ChatBot(String name, String icon, List<String> greetings, List<String> farewells) {
        this.name = name;
        this.icon = icon;
        this.greetings = greetings;
        this.farewells = farewells;
        this.tasks = new ArrayList<>();
    }

    public void greet() {
        System.out.println(this.icon);
        if (this.greetings.isEmpty()) {
            System.out.println("Hello World");
        } else {
            Random randomizer = new Random();
            int dialogueOption = randomizer.nextInt(greetings.size());
            System.out.println(greetings.get(dialogueOption));
        }
    }


    public void bye() {
        if (this.farewells.isEmpty()) {
            System.out.println("Goodbye World");
        } else {
            Random randomizer = new Random();
            int dialogueOption = randomizer.nextInt(farewells.size());
            System.out.println(farewells.get(dialogueOption));
        }
    }
    public void displayTasksSize() {
        System.out.printf("Now you have %d tasks in the list.%n", this.tasks.size());
    }
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        this.tasks.add(task);
        this.displayTasksSize();
    }

    public void addTaskFromLoad(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("Missing task number \n" +
                        "try: delete <task_number>");
            }
            this.deleteTask(Integer.parseInt(parameters));
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number \n" +
                    "try: delete <task_number>");
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int taskNumber) {
        try {
            Task taskToBeDeleted = this.tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskToBeDeleted);
            this.displayTasksSize();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task does not exists in the task list.");
        }
    }
    public void listTasks() {
        int count = 0;
        System.out.println("Here are the list of your task in the list: ");
        for (Task task: this.tasks) {
            count++;
            System.out.println(count + ". " + task.toString());
        }
    }

    public void markTaskAsDone(int taskNumber) {
        try {
            Task taskToBeMarked = this.tasks.get(taskNumber - 1);
            if (taskToBeMarked.isDone()) {
                throw new ChatBotParameterException("This task is already marked done!");
            }
            System.out.println("Nice! I've marked this task as done:");
            taskToBeMarked.markDone();
            System.out.println(taskToBeMarked);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task does not exists in the task list.");
        }
    }

    public void markTaskAsUndone(int taskNumber) {
        try {
            Task taskToBeUnmarked = this.tasks.get(taskNumber - 1);
            if (!taskToBeUnmarked.isDone()) {
                throw new ChatBotParameterException("This task is already marked as undone!");
            }
            System.out.println("OK, I've marked this task as not done yet:");
            taskToBeUnmarked.markUndone();
            System.out.println(taskToBeUnmarked);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task does not exists in the task list.");
        }
    }

    public void markTaskAsDone(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("Missing task number \n" +
                        "try: mark <task_number>");
            }
            this.markTaskAsDone(Integer.parseInt(parameters));
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number \n" +
                    "try: mark <task_number>");
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markTaskAsUndone(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("Missing task number \n" +
                        "try: unmark <task_number>");
            }
            this.markTaskAsUndone(Integer.parseInt(parameters));
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number \n" +
                    "try: unmark <task_number>");
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToDo(String description, boolean isDone) {
        try {
            if (description.isEmpty()) {
                throw new ChatBotParameterException("Missing description for ToDo \n" +
                        "try: todo <todo_name>");
            }
            this.addTask(new ToDo(description, isDone));
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addToDo(String parameters) {
        this.addToDo(parameters, false);
    }




    public void addDeadline(String description, String by, boolean isDone) {
        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
            this.addTask(new Deadline(description, byDateTime, isDone));
        } catch (DateTimeParseException e) {
            System.out.println("Wrong Date Time format " + by);
        }
    }

    public void addDeadline(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("There is no description and by for Deadline \n" +
                        "try: deadline <deadline_name> /by <by>");
            }
            String[] parametersArr = parameters.split(" /by ");
            if (parametersArr.length == 1) {
                throw new ChatBotParameterException("Missing description or by for Deadline \n" +
                        "try: deadline <deadline_name> /by <by>");
            }
            this.addDeadline(parametersArr[0], parametersArr[1], false);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("There is no description and from and to for Event \n" +
                        "try: event <event_name> /by <from> /to <to>");
            }
            String[] parametersArr = parameters.split(" /from | /to ");
            if (parametersArr.length < 3) {
                throw new ChatBotParameterException("Missing description and/or from and/or to for Event \n" +
                        "try: event <event_name> /by <from> /to <to>");
            }
            this.addEvent(parametersArr[0], parametersArr[1], parametersArr[2], false);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addEvent(String description, String from, String to, boolean isDone) {
        this.addTask(new Event(description, from, to, isDone));
    }

    public void addEventFromLoad(String description, String from, String to, boolean isDone) {
        this.addTaskFromLoad(new Event(description, from, to, isDone));
    }

    public void addDeadlineFromLoad(String description, String by, boolean isDone) {
        LocalDateTime byDateTime = LocalDateTime.parse(by);
        this.addTaskFromLoad(new Deadline(description, byDateTime, isDone));
    }

    public void addTodoFromLoad(String description, boolean isDone) {
        this.addTaskFromLoad(new ToDo(description, isDone));
    }

    public StringBuilder createSaveData() {
        StringBuilder saveData = new StringBuilder();
        for (Task task: this.tasks) {
            saveData.append(task.createSaveData());
        }
        return saveData;
    }

    public void saveTasksToFile() throws IOException {
        Files.createDirectories(Paths.get("data"));
        Files.writeString(Paths.get("data","tasks.txt"), this.createSaveData(),
                StandardOpenOption.CREATE);
    }

    public void loadTasksFromFile() throws IOException {

        Path savedPath = Paths.get("data", "tasks.txt");
        if (!Files.exists(savedPath)) {
            return;
        }

        List<String> tasks = Files.readAllLines(savedPath);
        for (String taskString: tasks) {
            String[] parameters = taskString.split(" \\| ");
            switch (parameters[0]) {
            case ("T"):
                this.addTodoFromLoad(parameters[2], parameters[1].equals("1"));
                break;
            case ("D"):
                this.addDeadlineFromLoad(parameters[2], parameters[3], parameters[1].equals("1"));
                break;
            case ("E"):
                this.addEventFromLoad(parameters[2], parameters[3], parameters[4], parameters[1].equals("1"));
                break;
            }
        }
    }


    @Override
    public String toString() {
        return "ChatBot{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", greetings=" + greetings +
                ", farewells=" + farewells +
                '}';
    }



}
