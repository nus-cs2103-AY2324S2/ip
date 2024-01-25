import  java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hi! This is Bit!\nWhat shall we do today?\n");
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int total = 0;

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                toList(list, total);
            } else if (input.contains("mark ")) {
                String[] parts = input.split(" ");
                try {
                    int i = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        mark(i, list);
                    } else if (parts[0].equals("unmark")) {
                        unmark(i, list);
                    } else {
                        addTo(list, total, input);
                        total++;


                    }

                } catch (NumberFormatException e) {
                    addTo(list, total, input);
                    total++;


                }
            } else {
                addTo(list, total, input);
                total++;

            }

        }
        System.out.println("Alright. See you soon!");
    }

    public static void addTo(Task[] list, int total, String input) {
        if(input.contains("todo ")) {
            String[] parts = input.split(" ", 2);
            if (parts[0].equals("todo")) {
                list[total] = new Todo(parts[1]);
                System.out.println("Done! I have added this to the list: " + list[total].toString());

            }


        } else if (input.contains("event ")) {
            String[] parts = input.split(" ");
            if (parts[0].equals("event")) {
                try{
                    String[] components = input.split("/");
                    list[total] = new Event(components[0], components[1]);
                } catch (PatternSyntaxException e) {
                    System.out.println("Wut");
                }
            }

        } else {
            System.out.println("Sorry, I don't understand what you mean");
        }


    }


    public static void toList(Task[] list, int total) {
        for (int i = 0; i < total; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.println((i + 1) + "." + list[i].toString());
        }
    }

    public static void mark(int i, Task[] list) {
        i -= 1;
        if(list[i] == null) {
           System.out.println("Sorry, but I couldn't find that task!");
           return;
        }

        list[i].complete();
        System.out.println("Alright! Task has been done and dusted:\n" +list[i].toString());
    }

    public static void unmark(int i, Task[] list) {
        i -= 1;
        if(list[i] == null) {
            System.out.println("Sorry, but I couldn't find that task!");
            return;
        }

        list[i].incomplete();
        System.out.println("Done! Task is now incomplete:\n" + list[i].toString());
    }


}
