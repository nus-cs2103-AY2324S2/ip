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
