import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> l;

    public Duke() {
        l = new ArrayList<>();
    }


    public static void main(String[] args) {
        System.out.println("Hello! I'm Lucifer\nWhat can I do for you?");
        System.out.println("______________________________________________________");
        Scanner sc = new Scanner(System.in);
        Duke lucifer = new Duke();
        String user_word;

        while (true) {
            user_word = sc.nextLine();
            if (user_word.equals("bye")) {
                break;
            }

            if (user_word.equals("list")) {
                lucifer.listTasks();
            } else if (user_word.contains("unmark")) {
                int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
                lucifer.unmarked_task(element_index);
            } else if (user_word.contains("mark")) {
                int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
                lucifer.markTask(element_index);
            } else if (user_word.contains("deadline")) {
                String[] array_split = user_word.split("/by ");
                Deadline deadline = new Deadline(array_split[0].substring(9), array_split[1]);
                lucifer.addDeadlineTask(deadline);
            } else if (user_word.contains("todo")) {
                ToDo todo = new ToDo(user_word.substring(5));
                lucifer.addTodoTask(todo);
            } else if (user_word.contains("event")) {
                String[] event =  user_word.split("/from | /to ");
                Event temp_event = new Event(event[0].substring(6), event[1], event[2]);
                lucifer.addEventTask(temp_event);
            } else {
                lucifer.addTask(user_word);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");
    }

    public void addTask(String user_word) {
        Task newTask = new Task(user_word);
        l.add(newTask);
        System.out.println("added: " + user_word);
        System.out.println("______________________________________________________");
    }


    public void listTasks() {
        System.out.println("______________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < l.size(); x++) {
            System.out.println(x + 1 + ". " + l.get(x));
        }
        System.out.println("______________________________________________________");
    }

    public void markTask(int element_index) {
        System.out.println("______________________________________________________");
        if (element_index >= 0 && element_index < l.size()) {
            l.get(element_index).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(l.get(element_index));
        }
        System.out.println("______________________________________________________");
    }

    public void unmarked_task(int element_index) {
        System.out.println("______________________________________________________");
        if (element_index >= 0 && element_index < l.size()) {
            l.get(element_index).mark_not_done();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(l.get(element_index));
        }
        System.out.println("______________________________________________________");
    }

    public void addDeadlineTask(Task task) {
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        l.add(task);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }
        System.out.println("______________________________________________________");
    }

    public void addTodoTask(Task task) {
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        l.add(task);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }        System.out.println("______________________________________________________");
    }
    
    public void addEventTask(Task task) {
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        l.add(task);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }        System.out.println("______________________________________________________");
    }
}

