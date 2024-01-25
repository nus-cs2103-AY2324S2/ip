import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ListAdder newList = new ListAdder();
        newList.start();
    }    
}

class ListAdder {
    private ArrayList<Item> itemList = new ArrayList<>();
    private int itemIndex;
    private static final String line = "____________________________________________________________";

    public ListAdder() {
        this.itemIndex = 1;
    }

    public void start() {
        greeting();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark done")) { // item is already done
                int index = Integer.parseInt(input.substring(9).trim()) - 1;
                markDone(index);

            } else if (input.startsWith("mark undone")) { // item is already undone
                int index = Integer.parseInt(input.substring(11).trim()) - 1;
                markUndone(index);

            } else {
                addItem(input);
            }
            // System.out.println(line);
            input = sc.nextLine();
            System.out.println(line);
        }
        // System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        // System.out.println(line);
    }


    private void greeting() {
        // System.out.println(line);
        System.out.println("Hello! My name is Computer Helper Boy."+ "\n");
        System.out.println("Provide an input to add it to your to-do list." + "\n");
        System.out.println("Type 'list' to see your list, and 'bye' to exit the program.");
        // System.out.println(line);
    }

    // Adds item to list with ability to mark as done
    private void addItem(String item) {
        Item newItem = new Item(item);
        this.itemList.add(newItem);
        // System.out.println(line);
        System.out.println("Added task: " + item);
        // System.out.println(line);
    }

    private void printList() {
        System.out.println("Here is your to-do list:");
        for (Item item : this.itemList) {
            System.out.println(item.isDone() ? "[X] " + item : "[ ] " + item);
            this.itemIndex++;
        }
        // System.out.println(line);
    }

    private void markDone(int index) {
        try {
            if (this.itemList.get(index).isDone()) {
                System.out.println("You completed this task already!");
            } else {
                this.itemList.get(index).markDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.println("[X] " + this.itemList.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Your list isn't that long :P");
        } catch (NumberFormatException e) {
            System.out.println("This number isn't valid!");
        }
        // System.out.println(line);
    }

    private void markUndone(int index) {
        try {
            if (!this.itemList.get(index).isDone()) {
                System.out.println("Oops! You still haven't done this task!");
            } else {
                this.itemList.get(index).markUndone();
                System.out.println("I've marked this task as undone:");
                System.out.println("[ ] " + this.itemList.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Your list isn't that long :P");
        } catch (NumberFormatException e) {
            System.out.println("This number isn't valid!");
        }
        // System.out.println(line);
    }
}

class Item {
    private String item;
    private boolean isDone;

    public Item(String item) {
        this.item = item;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.item;
    }
}