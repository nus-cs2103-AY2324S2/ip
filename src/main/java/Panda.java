import java.util.ArrayList;
import java.util.Scanner;

public class Panda {

    private static boolean running = false;
    private static ArrayList<Task> tlist;

    private static void startUp() {
        System.out.println(
            "Hello! I'm Panda\n" + 
            "What can I do for you?"
        );
        running = true;
        tlist = new ArrayList<>();
    }

    private static void shutDown() {
        System.out.println(
            "Bye. Hope to see you again soon!"
        );
    }

    private static void printTlist() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tlist.size(); i++) {
            System.out.println((i + 1) + "." + tlist.get(i));
        }
    }

    private static void comm (String userInput) throws PandaException {
        if(userInput.equals("bye")) {
            running = false;
            return;
        }
        if(userInput.equals(("list"))) {
            Panda.printTlist();
            return;
        }
        if(userInput.split(" ")[0].equals("mark")) {
            int target;
            try {
                target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            }
            catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            if(target >= tlist.size()) {
                throw new OutOfBoundsException();
            }
            tlist.get(target).mark();
            System.out.println("Nice! I've marked this task as done:\n  " + tlist.get(target));
            return;
        }
        if(userInput.split(" ")[0].equals("unmark")) {
            int target;
            try {
                target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            }
            catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            if(target >= tlist.size()) {
                throw new OutOfBoundsException();
            }
            tlist.get(target).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tlist.get(target));
            return;
        }
        if(userInput.split(" ")[0].equals("delete")) {
            int target;
            try {
                target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            }
            catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            if(target >= tlist.size()) {
                throw new OutOfBoundsException();
            }
            Task tmp = tlist.get(target);
            tlist.remove(target);
            System.out.println("OK, I've deleted this task:\n  " + tmp + "\nNow you have " + tlist.size() + " tasks in the list.");
            return;
        }
        if(userInput.split(" ")[0].equals("todo")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyTodoException();
            }
            tlist.add(new Todo(splitted[1].trim()));
            System.out.println("Got it. I've added this task:\n " + tlist.get(tlist.size() - 1) + "\nNow you have " + tlist.size() + " tasks in the list.");
            return;
        }
        if(userInput.split(" ")[0].equals("deadline")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyDeadlineException("desc");
            }
            String[] args = splitted[1].split("/by");
            if(args.length < 2) {
                throw new EmptyDeadlineException("date");
            }
            tlist.add(new Deadline(args[0].trim(), args[1].trim()));
            System.out.println("Got it. I've added this task:\n " + tlist.get(tlist.size() - 1) + "\nNow you have " + tlist.size() + " tasks in the list.");
            return;
        }
        if(userInput.split(" ")[0].equals("event")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyEventException("desc");
            }
            String[] args = splitted[1].split("/from");
            if(args.length < 2 || args[1].split("/to").length < 2) {
                throw new EmptyEventException("date");
            }
            tlist.add(new Event(args[0].trim(), args[1].split("/to")[0].trim(), args[1].split("/to")[1].trim()));
            System.out.println("Got it. I've added this task:\n " + tlist.get(tlist.size() - 1) + "\nNow you have " + tlist.size() + " tasks in the list.");
            return;
        }
        throw new UnknownCommandException();
    }

    public static void main(String[] args) {
        Panda.startUp();
        Scanner myObj  = new Scanner(System.in);
        while(running) {
            System.out.print("> ");
            String userInput = myObj.nextLine();
            try {
                Panda.comm(userInput);
            }
            catch (PandaException e) {
                System.out.println(e.getMessage());
            }
        }
        myObj.close();
        Panda.shutDown();
    }
}
