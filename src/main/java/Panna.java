import java.util.Scanner;
import java.util.ArrayList;

// to add: tag for A-Classes
public class Panna {

    private static String command = "";



    public static void main(String[] args) throws PannaException {

        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("----------------------------------------------------------\n"
        + "Hello! I'm Panna.\n" +
                "What can I do for you?\n\n" +
                "----------------------------------------------------------");


        Scanner s = new Scanner(System.in);

        Panna.command = s.nextLine();

        while (!Panna.command.equals("bye")) {





            if (Panna.command.equals("list")) {
                System.out.println("----------------------------------------------------------");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
                System.out.println("----------------------------------------------------------");
            }


            else if (Panna.command.equals("mark")) {
                System.out.println("Which one should I mark? Write the label number :] ");
                try {
                    int label = s.nextInt();
                    tasks.get(label - 1).setDone(true);

                    System.out.println("----------------------------------------------------------");
                    System.out.println("Nice! I've marked this task as done: \n"
                            + tasks.get(label - 1));
                    System.out.println("----------------------------------------------------------");

                }
                catch (Exception e) {
                    throw new PannaException("Invalid label! The number of tasks now is " + tasks.size() +
                            "\nPlease try with a more appropriate value! ");
                }
            }

            else if (Panna.command.equals("unmark")) {
                System.out.println("Which one should I unmark? Write the label number :] ");
                try {
                    int label = s.nextInt();
                    tasks.get(label - 1).setDone(false);

                    System.out.println("----------------------------------------------------------");
                    System.out.println("Okay! I've unmarked this task as done: \n"
                            + tasks.get(label - 1));
                    System.out.println("----------------------------------------------------------");
                }
                catch (Exception e) {
                    throw new PannaException("Invalid label! The number of tasks now is" + tasks.size() +
                                                "\nPlease try with a more appropriate value! ");
                }

            }

            else if (Panna.command.equals("event")) {
                try {
                    System.out.println("What kind of event? ");
                    String input = s.nextLine();
                    System.out.println("When does it start? ");
                    String start = s.nextLine();
                    System.out.println("When does it end? ");
                    String end = s.nextLine();
                    Task t = new Event(input, start, end);
                    tasks.add(t);
                    System.out.println("Got it! I've added the \n" + t + "\n event!");
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list! ");
                }
                catch (Exception e) {
                    throw new PannaException("All inputs must be Strings! Please ensure it is not empty :D");
                }

            }

            else if (Panna.command.equals("todo")) {
                try {
                    System.out.println("What kind of todo? ");
                    String input = s.nextLine();
                    Task t = new Todo(input);
                    tasks.add(t);
                    System.out.println("Got it! I've added the \n" + t + "\n todo!");
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list! ");
                }
                catch (Exception e){
                    throw new PannaException("All inputs must be Strings! Please ensure it is not empty :D");
                }
            }

            else if (Panna.command.equals("deadline")) {
                try {
                    System.out.println("What kind of deadline? ");
                    String input = s.nextLine();
                    System.out.println("When is the deadline? ");
                    String deadline = s.nextLine();

                    Task t = new Deadline(input, deadline);
                    tasks.add(t);

                    System.out.println("Got it! I've added the \n" + t + "\n deadline!");
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list! ");
                }
                catch (Exception e) {
                    throw new PannaException("All inputs must be Strings! Please ensure it is not empty :D");
                }

            }
            else if (Panna.command.equals("delete")) {
                System.out.println("Which one should I delete? Write the label number :] ");
                try {
                    int label = s.nextInt();
                    Task t = tasks.get(label-1);
                    tasks.remove(label-1);

                    System.out.println("----------------------------------------------------------");
                    System.out.println("Task successfully removed! \n"
                            + t);
                    System.out.println("----------------------------------------------------------");

                }
                catch (Exception e) {
                    throw new PannaException("Invalid label! The number of tasks now is " + tasks.size() +
                            "\nPlease try with a more appropriate value! ");
                }
            }


            Panna.command = s.nextLine();
        }




        System.out.println("----------------------------------------------------------\n"  +
                "Bye. Hope to see you again soon!\n\n" +
                "----------------------------------------------------------");
    }



}
