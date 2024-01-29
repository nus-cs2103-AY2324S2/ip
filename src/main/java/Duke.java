import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> storage = new ArrayList<>();

    public void returnList () {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < storage.size(); i++) {
            System.out.println("    " + (i + 1) + "." + storage.get(i).toString());
        }
    }

    public void markTask(String[] split_message) {
        Integer index = Integer.parseInt(split_message[1]) - 1;
        this.storage.get(index).setStatus(true);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + this.storage.get(index).toString());
    }

    public void unmarkTask(String[] split_message) {
        Integer index = Integer.parseInt(split_message[1]) - 1;
        this.storage.get(index).setStatus(false);
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + this.storage.get(index).toString());
    }

    public void addTask(String[] split_message) {
        try {
            Task message;
            if (split_message[0].equals("todo")) {
                try {
                    if (split_message.length == 1) {
                        throw new DukeException.TODONoTaskException("No Task");
                    }
                    message = Task.TODO;
                    String task = " ";
                    for (int i = 1; i < split_message.length; i++) {
                        task = task + split_message[i] + " ";
                    }
                    task = task.strip();
                    message.setTask(task);
                } catch (DukeException.TODONoTaskException ex) {
                    System.out.println("    " + ex.getMessage() + ". Please enter the task, too.");
                    return;
                }
            } else if (split_message[0].equals("deadline")) {
                message = Task.DEADLINE;
                String content = " ";
                for (int i = 1; i < split_message.length; i++) {
                    if (split_message[i].equals("/by")) {
                        content = content.strip();
                        message.setTask(content);
                        content = "";
                    } else {
                        content = content + split_message[i] + " ";
                    }
                }
                content = content.strip();
                message.setTo(content);
            } else if (split_message[0].equals("event")) {
                message = Task.Event;
                String content = " ";
                for (int i = 1; i < split_message.length; i++) {
                    if (split_message[i].equals("/from")) {
                        content = content.strip();
                        message.setTask(content);
                        content = "";
                    } else if (split_message[i].equals("/to")) {
                        content = content.strip();
                        message.setFrom(content);
                        content = "";
                    } else {
                        content = content + split_message[i] + " ";
                    }
                }
                content = content.strip();
                message.setTo(content);
            } else {
                throw new DukeException.UnsupportedTaskException("This is unsupported task");
            }
            storage.add(message);
            System.out.println("    Got it. I've added this task:");
            System.out.println("       " + message.toString());
            System.out.println("    Now you have " + this.storage.size() + " tasks in the list.");
        } catch (DukeException.UnsupportedTaskException ex) {
            System.out.println("    " + ex.getMessage() + ". Please only enter the supported types of task.");
        }
    }

    public void deleteTask(int position) {
        Task task = this.storage.get(position - 1);
        this.storage.remove(position - 1);
        System.out.println("    Got it. I've removed this task:");
        System.out.println("        " + task.toString());
        System.out.println("    Now you have " + this.storage.size() + " task in the list.");
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        Duke chatbot = new Duke();
        System.out.println("    -----------------------------------");
        System.out.println("    Hello! I'm ByteTalker");
        System.out.println("    What can I do for you?");
        System.out.println("    -----------------------------------");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String userInput_String = userInput.nextLine().strip();
            String[] split_message = userInput_String.split(" ");
            System.out.println("    -----------------------------------");
            if (userInput_String.equals("bye")) {
                break;
            } else if (userInput_String.equals("list")) {
                chatbot.returnList();
            } else if (split_message[0].equals("mark")) {
                chatbot.markTask(split_message);
            } else if (split_message[0].equals("unmark")) {
                chatbot.unmarkTask(split_message);
            } else if (split_message[0].equals("delete")) {
                chatbot.deleteTask(Integer.parseInt(split_message[1]));
            } else {
                chatbot.addTask(split_message);
            }
            System.out.println("    -----------------------------------");
        }
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    -----------------------------------");
    }
}
