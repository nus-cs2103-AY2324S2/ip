import java.util.Scanner;

public class Klee {
    public static void main(String[] args) {
        String divider = "____________________________________________________________________________";
        System.out.println(divider);
        System.out.println("Hello! My name is Klee.");
        System.out.println("Are you here to break Klee out of solitary confinement?");
        System.out.println(divider);
        Task[] tasks = new Task[100];
        int currentIndex = 0;
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                System.out.println("These are all the things that we have to do today:");
                for (int i = 0; i < currentIndex; i++) {
                    System.out.println((i+1) + ". " + tasks[i].getStatus());
                }
                System.out.println(divider);
            } else {
                if (input.equals("")) {
                    System.out.println(divider);
                    System.out.println("Did you say something? Klee could not hear you over my bombs... [Empty string ignored]");
                    System.out.println(divider);
                } else {
                    String[] command = input.split(" ");
                    if (command[0].equals("mark")) {
                        try{
                            Integer index = Integer.parseInt(command[1]) - 1;
                            tasks[index].mark();
                            System.out.println(divider);
                            System.out.println("Great! Klee will put a big cross on this box:");
                            System.out.println(tasks[index].getStatus());
                            System.out.println(divider);
                        } catch(NumberFormatException e) {
                            System.out.println(divider);
                            System.out.println("Klee doesn't understand, which task is that? [There should be an int after mark]");
                            System.out.println(divider);
                        }
                    } else if (command[0].equals("unmark")) {
                        try{
                            Integer index = Integer.parseInt(command[1]) - 1;
                            tasks[index].unMark();
                            System.out.println(divider);
                            System.out.println("Oh no! Klee will burn the cross away...:");
                            System.out.println(tasks[index].getStatus());
                            System.out.println(divider);
                        } catch(NumberFormatException e) {
                            System.out.println(divider);
                            System.out.println("Klee doesn't understand, which task is that? [There should be an int after unmark]");
                            System.out.println(divider);
                        }
                    } else if (command[0].equals("todo")) {
                        System.out.println(divider);
                        System.out.println("Klee will help you write that down! : " + input);
                        tasks[currentIndex] = new ToDo(input);
                        currentIndex++;
                        System.out.println(divider);
                    } else if (command[0].equals("deadline")) {
                        System.out.println(divider);
                        System.out.println("Klee will help you write that down! : " + input);
                        String description = input.split("deadline ")[1].split("/by ")[0];
                        tasks[currentIndex] = new Deadline(description, input.split("/by ")[1]);
                        currentIndex++;
                        System.out.println(divider);
                    } else if (command[0].equals("event")) {
                        System.out.println(divider);
                        System.out.println("Klee will help you write that down! : " + input);
                        String description = input.split("event ")[1].split(" /from")[0];
                        String from = input.split("/from ")[1].split(" /to")[0];
                        String to = input.split("/to ")[1];
                        tasks[currentIndex] = new Event(description, from, to);
                        currentIndex++;
                        System.out.println(divider);
                    } else {
                        System.out.println(divider);
                        System.out.println("Klee doesn't understand, what are you talking about?");
                        System.out.println(divider);
                    }
                }
            }
        };
        System.out.println(divider);
        System.out.println("Goodbye. Klee will go back to solitary confinement now...");
        System.out.println(divider);
    }
}
