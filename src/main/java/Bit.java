import  java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Bit {

    public static int total = 0;

    public static String seperator = "---------------------------------------------------------";
    public static void main(String[] args) {

        System.out.println("Hi! This is Bit!\nWhat shall we do today?\n");
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];


        while(true) {
            System.out.println(seperator);
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
                        try{addTo(list, input);} catch(DukeException e) {System.out.println(e);}


                    }

                } catch (NumberFormatException e) {
                    try{addTo(list, input);} catch(DukeException x) {System.out.println(x);}


                }
            } else {
                try{addTo(list, input);} catch(DukeException e) {System.out.println(e);}

            }



        }
        System.out.println("Alright. See you soon!!");
    }

    public static void addTo(Task[] list, String input) throws DukeException{
        if(input.startsWith("todo")) {
            try {
                String[] parts = input.split(" ", 2);
                if (!parts[0].equals("todo")) {
                    throw new DukeException("I have no idea what that means!");
                }
                if (parts[1].trim().isEmpty()) {
                    throw new DukeException("Hmmm, that todo is empty!");
                }
                list[total] = new Todo(parts[1]);

            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Hmmm, that todo is empty!");
            }


        } else if (input.contains("event ")) {
            String[] parts = input.split(" ", 2);
            if (parts[0].equals("event")) {
                try{
                    String[] compo = parts[1].split("/to");
                    String end = compo[1];
                    String[] components = compo[0].split("/from");
                    String start = components[1];
                    list[total] = new Event(components[0], start, end);
                    System.out.println("Done! I have added this to the list:" + list[total].toString()
                            + "\n There are now " + (total + 1) + " items");
                    total++;
                } catch (PatternSyntaxException e) {
                    System.out.println("Hmmm, did you use the proper syntax?");
                } catch (ArrayIndexOutOfBoundsException x) {
                    System.out.println("Please check your syntax");
                }
            }

        } else if (input.contains("deadline ")) {
            try {
                String[] parts = input.split(" ", 2);
                if (parts[0].equals("deadline")) {
                    String[] compo = parts[1].split("/by");
                    list[total] = new Deadline(compo[0], compo[1]);
                    System.out.println("Done! I have added this to the list:" + list[total].toString()
                     + "\n There are now " + (total + 1) + " items");
                    total++;
                }
            } catch (ArrayIndexOutOfBoundsException x) {
                System.out.println("Please check your syntax");
            }

        } else {
            System.out.println("Sorry, I don't understand what you mean\n");
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
        System.out.println("Done!! Task is now incomplete:\n" + list[i].toString());
    }


}
