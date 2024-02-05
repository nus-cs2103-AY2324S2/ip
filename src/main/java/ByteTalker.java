import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class ByteTalker {
    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void returnList () {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + this.tasks.get(i).toString());
        }
    }

    public void markTask(String[] splitMessage) {
        Integer index = Integer.parseInt(splitMessage[1]) - 1;
        this.tasks.get(index).setStatus(true);
        Storage.storeTasks(this.tasks);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + this.tasks.get(index).toString());
    }

    public void unmarkTask(String[] splitMessage) {
        Integer index = Integer.parseInt(splitMessage[1]) - 1;
        this.tasks.get(index).setStatus(false);
        Storage.storeTasks(this.tasks);
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + this.tasks.get(index).toString());
    }

    public void addTask(String[] splitMessage) {
        try {
            Task task;
            if (splitMessage[0].equals("todo")) {
                try {
                    if (splitMessage.length == 1) {
                        throw new ByteTalkerException.TodoNoTaskException("No Task");
                    }
                    String content = " ";
                    for (int i = 1; i < splitMessage.length; i++) {
                        content = content + splitMessage[i] + " ";
                    }
                    content = content.strip();
                    task = new Todo(content);
                } catch (ByteTalkerException.TodoNoTaskException ex) {
                    System.out.println("    " + ex.getMessage() + ". Please enter the task, too.");
                    return;
                }
            } else if (splitMessage[0].equals("deadline")) {
                String content = " ";
                String deadline = "";
                for (int i = 1; i < splitMessage.length; i++) {
                    boolean isContentFilled = splitMessage[i].equals("/by");
                    if (isContentFilled) {
                        content = deadline.strip();
                        deadline = "";
                    } else {
                        deadline = deadline + splitMessage[i] + " ";
                    }
                }
                deadline = deadline.strip();
                task = new Deadline(content, deadline);
            } else if (splitMessage[0].equals("event")) {
                String content = " ";
                String from = "";
                String to = "";
                for (int i = 1; i < splitMessage.length; i++) {
                    boolean isContentFilled = splitMessage[i].equals("/from");
                    boolean isFromFilled = splitMessage[i].equals("/to");
                    if (splitMessage[i].equals("/from")) {
                        content = to.strip();
                        to = "";
                    } else if (splitMessage[i].equals("/to")) {
                        from = to.strip();
                        to = "";
                    } else {
                        to = to + splitMessage[i] + " ";
                    }
                }
                to = to.strip();
                task = new Event(content, from, to);
            } else {
                throw new ByteTalkerException.UnsupportedTaskException("This is unsupported task");
            }
            this.tasks.add(task);
            Storage.storeTasks(this.tasks);
            System.out.println("    Got it. I've added this task:");
            System.out.println("       " + task.toString());
            System.out.println("    Now you have " + this.tasks.size() + " tasks in the list.");
        } catch (ByteTalkerException.UnsupportedTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please only enter the supported types of task.");
        }
    }

    public void deleteTask(int position) {
        Task task = this.tasks.get(position - 1);
        this.tasks.remove(position - 1);
        Storage.storeTasks(this.tasks);
        System.out.println("    Got it. I've removed this task:");
        System.out.println("        " + task.toString());
        System.out.println("    Now you have " + this.tasks.size() + " task in the list.");
    }

    public static void main(String[] args) {
        ByteTalker chatbot = new ByteTalker();
        System.out.println("    -----------------------------------");
        System.out.println("    Hello! I'm ByteTalker");
        System.out.println("    What can I do for you?");
        System.out.println("    -----------------------------------");
        try {
            Storage.setupDirectoryAndFile();
        } catch (IOException e) {
            System.out.println("Fail to create a new file");
        }
        Storage.loadTasks(chatbot.getTasks());
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String userInputString = userInput.nextLine().strip();
            String[] splitMessage = userInputString.split(" ");
            System.out.println("    -----------------------------------");
            if (userInputString.equals("bye")) {
                break;
            } else if (userInputString.equals("list")) {
                chatbot.returnList();
            } else if (splitMessage[0].equals("mark")) {
                chatbot.markTask(splitMessage);
            } else if (splitMessage[0].equals("unmark")) {
                chatbot.unmarkTask(splitMessage);
            } else if (splitMessage[0].equals("delete")) {
                chatbot.deleteTask(Integer.parseInt(splitMessage[1]));
            } else {
                chatbot.addTask(splitMessage);
            }
            System.out.println("    -----------------------------------");
        }
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    -----------------------------------");
    }
}
