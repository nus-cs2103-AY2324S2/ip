import exceptions.*;

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
            String firstWord = input.split(" ")[0];
            try {
                if (input.toLowerCase().equals("bye")) {
                    break;
                } else if (input.toLowerCase().equals("list")) {
                    print("Here are the tasks in your list:");
                    for (int i = 0; i < this.items.size(); i++) {
                        String output = (i + 1) + "." + this.items.get(i);
                        print(output);
                    }
                } else if (firstWord.equals("mark")) {
                    mark(input);
                } else if (firstWord.equals("unmark")) {
                    unmark(input);
                } else if (firstWord.equals("delete")) {
                    delete(input);
                } else if (firstWord.equals("todo")) {
                    todo(input);
                } else if (firstWord.equals("deadline")) {
                    deadline(input);
                } else if (firstWord.equals("event")) {
                    event(input);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                print("Sorry, I dont think I quite understood what you meant...");
            }
        }
    }

    public void mark(String input) {
        if (input.length() <= 4) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(input.substring(5).strip()) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.get(index);
        try {
            task.updateStatus(true);
            print("Nice! I've marked this task as done:");
            print(task);
        } catch (InvalidStatusUpdateException e) {
            print("This task was already marked!");
            print(task);
        }
    }

    public void unmark(String input) {
        if (input.length() <= 6) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int index = Integer.valueOf(input.substring(7).strip()) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }
        Task task = this.items.get(index);
        try {
            task.updateStatus(false);
            print("OK, I've marked this task as not done yet:");
            print(task);
        } catch (InvalidStatusUpdateException e) {
            print("This task was already unmarked!");
            print(task);
        }
    }

    public void delete(String input) {
        if (input.length() <= 5) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }

        int index = Integer.valueOf(input.substring(6).strip()) - 1;
        if (index >= this.items.size() || index < 0) {
            print("Oops! You did not give a valid index.");
            return;
        }

        Task task = this.items.remove(index);
        print("Noted. I've removed this task:");
        print("\t" + task);
        print(String.format("Now you have %d tasks in the list.", this.items.size()));
    }

    public void todo(String input) {
        if (input.length() <= 4) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        String name = input.substring(5).strip();
        Todo todo = new Todo(name);
        this.items.add(todo);
        print("Got it. I've added this task:");
        print("\t" + todo);
        print(String.format("Now you have %d tasks in the list.", this.items.size()));
    }

    public void deadline(String input) {
        if (input.length() <= 8) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int indexBy = input.indexOf('/');
        try {
            if (!input.substring(indexBy + 1).split(" ")[0].equals("by")) {
                throw new InvalidSlashParameterException();
            }
            String name = input.substring(9, indexBy).strip();
            String by = input.substring(indexBy + 3).strip();
            Deadline deadline = new Deadline(name, by);
            this.items.add(deadline);
            print("Got it. I've added this task:");
            print("\t" + deadline);
            print(String.format("Now you have %d tasks in the list.", this.items.size()));
        } catch (InvalidSlashParameterException e) {
            print("Sorry, when would you like the deadline to be until?");
        }
    }

    public void event(String input) {
        if (input.length() <= 5) {
            print("I didn't quite understand, could you complete your command with appropriate arguments?");
            return;
        }
        int indexFrom = input.indexOf('/');
        int indexTo = input.indexOf('/', indexFrom + 1);
        try {
            if (!input.substring(indexFrom + 1).split(" ")[0].equals("from")) {
                throw new InvalidSlashParameterException();
            }
            if (!input.substring(indexTo + 1).split(" ")[0].equals("to")) {
                throw new InvalidSlashParameterException();
            }
            String name = input.substring(6, indexFrom).strip();
            String from = input.substring(indexFrom + 5, indexTo).strip();
            String to = input.substring(indexTo + 3).strip();
            Event event = new Event(name, from, to);
            this.items.add(event);
            print("Got it. I've added this task:");
            print("\t" + event);
            print(String.format("Now you have %d tasks in the list.", this.items.size()));
        } catch (InvalidSlashParameterException e) {
            print("Sorry, when would you like the event to begin and end?");
        }
    }

    public void print(Object text) {
        System.out.println("\t" + text.toString());
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        try {
            chatbot.respond();
        } catch (Exception e) {
            System.out.println("Error detected");
        }
        chatbot.exit();
    }
}
