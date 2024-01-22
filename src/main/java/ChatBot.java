import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatBot {
    private String name;
    private String icon;
    private List<String> greetings;
    private List<String> farewells;
    private List<String> commands;
    private List<Task> tasks;

    public ChatBot(String name, String icon, List<String> greetings, List<String> farewells, List<String> commands) {
        this.name = name;
        this.icon = icon;
        this.greetings = greetings;
        this.farewells = farewells;
        this.commands = commands;
        this.tasks = new ArrayList<Task>();
    }

    public ChatBot(String name, String icon, List<String> greetings, List<String> farewells) {
        this.name = name;
        this.icon = icon;
        this.greetings = greetings;
        this.farewells = farewells;
        this.commands = List.<String>of("greet", "bye");
        this.tasks = new ArrayList<Task>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getGreetings() {
        return greetings;
    }

    public void setGreetings(List<String> greetings) {
        this.greetings = greetings;
    }

    public void addGreeting(String greeting) {
        this.greetings.add(greeting);
    }

    public List<String> getFarewells() {
        return farewells;
    }

    public void setFarewells(List<String> farewells) {
        this.farewells = farewells;
    }

    public void addFarewell(String farewell) {
        this.farewells.add(farewell);
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void addCommand(String command) {
        this.commands.add(command);
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

    public void addTask(String description) {
        this.addTask(new Task(description));
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
        
        Task taskToBeMarked = this.tasks.get(taskNumber - 1);
        if (taskToBeMarked.isDone()) {
            System.out.println("This task is already marked done!");
            return;
        }
        System.out.println("Nice! I've marked this task as done:");
        taskToBeMarked.markDone();
        System.out.println(taskToBeMarked);
    }

    public void markTaskAsUndone(int taskNumber) {

        Task taskToBeUnmarked = this.tasks.get(taskNumber - 1);
        if (!taskToBeUnmarked.isDone()) {
            System.out.println("This task is already marked as undone!");
            return;
        }
        System.out.println(" OK, I've marked this task as not done yet:");
        taskToBeUnmarked.markUndone();
        System.out.println(taskToBeUnmarked);
    }

    public void markTaskAsDone(String parameters) {
        // To add exception
        this.markTaskAsDone(Integer.parseInt(parameters));
    }

    public void markTaskAsUndone(String parameters) {
        // To add exception
        this.markTaskAsUndone(Integer.parseInt(parameters));
    }

    public void addToDo(String description) {
        try {
            if (description.isEmpty()) {
                throw new ChatBotParameterException("Missing description for ToDo \n" +
                        "try: todo <todo_name>");
            }
            this.addTask(new ToDo(description));
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addDeadline(String description, String by) {
        this.addTask(new Deadline(description, by));
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
            this.addDeadline(parametersArr[0], parametersArr[1]);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String parameters) {
        try {
            if (parameters.isEmpty()) {
                throw new ChatBotParameterException("There is no description and from and to for Event \n" +
                        "try: deadline <event_name> /by <from> /to <to>");
            }
            String[] parametersArr = parameters.split(" /from | /to ");
            if (parametersArr.length > 3) {
                throw new ChatBotParameterException("Mising description and/or from and/or to for Event \n" +
                        "try: deadline <event_name> /by <from> /to <to>");
            }
            this.addEvent(parametersArr[0], parametersArr[1], parametersArr[2]);
        } catch (ChatBotParameterException e) {
            System.out.println(e.getMessage());
        }

    }

    private void addEvent(String description, String from, String to) {
        this.addTask(new Event(description, from, to));
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
