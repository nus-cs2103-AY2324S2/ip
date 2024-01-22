import java.util.*;

public class Lulu {
    private List<Task> items;

    public Lulu() {
        this.items = new ArrayList<>();
    }

    public void start() {
        print("Hello! I'm Lulu \n\tWhat can I do for you?");
    }

    public void exit() {
        print("Bye. Hope to see you again soon!");
    }

//    public void echo() {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.toLowerCase().equals("bye")) {
//                break;
//            }
//            print(input);
//        }
//    }
//
//    public void insert() {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.toLowerCase().equals("list")) {
//                for (int i = 0; i < this.items.size(); i++) {
//                    String output = i + ". " + this.items.get(i);
//                    print(output);
//                }
//            }
//            else if (input.toLowerCase().equals("bye")) {
//                break;
//            } else {
//                this.items.add(input);
//                String output = "added: " + input;
//                print(output);
//            }
//        }
//    }

    public void respond() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().strip();
            if (input.toLowerCase().equals("bye")) {
                break;
            } else if (input.toLowerCase().equals("list")) {
                print("Here are the tasks in your list:");
                for (int i = 0; i < this.items.size(); i++) {
                    String output = (i + 1) + "." + this.items.get(i);
                    print(output);
                }
            } else if (input.length() > 4 && input.substring(0, 4).toLowerCase().equals("mark")) {
                int index = Integer.valueOf(input.substring(5).strip()) - 1;
                if (index >= this.items.size() || index < 0) {
                    print("Oops! You did not give a valid index.");
                    continue;
                }
                Task task = this.items.get(index);
                if (task.getStatus()) {
                    print("This task was already marked:");
                    print(task);
                } else {
                    task.updateStatus(true);
                    print("Nice! I've marked this task as done:");
                    print(task);
                }
            } else if (input.length() > 6 && input.substring(0, 6).toLowerCase().equals("unmark")) {
                int index = Integer.valueOf(input.substring(7).strip()) - 1;
                if (index >= this.items.size() || index < 0) {
                    print("Oops! You did not give a valid index.");
                    continue;
                }
                Task task = this.items.get(index);
                if (!task.getStatus()) {
                    print("This task was already unmarked:");
                    print(task);
                } else {
                    task.updateStatus(false);
                    print("OK, I've marked this task as not done yet:");
                    print(task);
                }
            } else if (input.length() > 4 && input.substring(0, 4).toLowerCase().equals("todo")) {
                String name = input.substring(5).strip();
                Todo todo = new Todo(name);
                this.items.add(todo);
                print("Got it. I've added this task:");
                print("\t" + todo);
                print(String.format("Now you have %d tasks in the list.", this.items.size()));
            } else if (input.length() > 8 && input.substring(0, 8).toLowerCase().equals("deadline")) {
                int indexBy = input.indexOf('/');
                String name = input.substring(9, indexBy).strip();
                String by = input.substring(indexBy + 3).strip();
                Deadline deadline = new Deadline(name, by);
                this.items.add(deadline);
                print("Got it. I've added this task:");
                print("\t" + deadline);
                print(String.format("Now you have %d tasks in the list.", this.items.size()));
            } else if (input.length() > 5 && input.substring(0, 5).toLowerCase().equals("event")) {
                int indexFrom = input.indexOf('/');
                int indexTo = input.indexOf('/', indexFrom + 1);
                String name = input.substring(6, indexFrom).strip();
                String from = input.substring(indexFrom + 5, indexTo).strip();
                String to = input.substring(indexTo + 3).strip();
                Event event = new Event(name, from, to);
                this.items.add(event);
                print("Got it. I've added this task:");
                print("\t" + event);
                print(String.format("Now you have %d tasks in the list.", this.items.size()));
            }
        }
    }

    public void print(Object text) {
        System.out.println("\t" + text.toString());
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        chatbot.respond();
        chatbot.exit();
    }
}
