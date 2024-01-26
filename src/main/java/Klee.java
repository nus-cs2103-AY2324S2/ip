import java.util.Scanner;
import java.util.ArrayList;

public class Klee {
    enum Instruction {
        todo,
        deadline,
        event,
        mark,
        unmark,
        delete
    }

    public static String checkToDo(String input) throws KleeException {
        String[] description = input.split("todo ");
        if (description.length == 2) return description[1];
        else throw new KleeException("We should think of a name for the task!");
    }

    public static String[] checkDeadline(String input) throws KleeException {
        String[] command = input.split("deadline ");
        String[] output = new String[2];
        if (command.length == 2) {
            command = command[1].split(" /by ");
            if (command.length == 2) {
                output[0] = command[0];
                output[1] = command[1];
                return output;
            } else throw new KleeException("We should indicate when this task is due with `/by`");
        }
        else throw new KleeException("We should think of a name for the task!");
    }

    public static String[] checkEvent(String input) throws KleeException {
        String[] command = input.split("event ");
        String[] output = new String[3];
        if (command.length == 2) {
            command = command[1].split(" /from ");
            if (command.length == 2) {
                output[0] = command[0];
                command = command[1].split(" /to ");
                if (command.length == 2) {
                    output[1] = command[0];
                    output[2] = command[1];
                    return output;
                } else throw new KleeException("We should indicate when this event ends with `/to`");
            } else throw new KleeException("We should indicate when this event starts with `/from`");
        } else throw new KleeException("We should think of a name for the task!");
    }

    public static void main(String[] args) {
        String divider = "____________________________________________________________________________";
        System.out.println(divider);
        System.out.println("Hello! My name is Klee.");
        System.out.println("Are you here to break Klee out of solitary confinement?");
        System.out.println(divider);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner getInput = new Scanner(System.in);
        while (true) {
            String input = getInput.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                System.out.println("These are all the things that we have to do today:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i+1) + ". " + tasks.get(i).getStatus());
                }
                System.out.println(divider);
            } else {
                if (input.equals("")) {
                    System.out.println(divider);
                    System.out.println("Did you say something? Klee could not hear you over my bombs...");
                    System.out.println(divider);
                } else {
                    String[] command = input.split(" ");
                    try {
                        Instruction instruction = Instruction.valueOf(command[0]);
                        switch (instruction) {
                        case todo:
                            System.out.println(divider);
                            try {
                                String description = checkToDo(input);
                                Task task = new ToDo(description);
                                tasks.add(task);
                                System.out.println("Klee will help you write that down! : ");
                                System.out.println(task.getStatus());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            } catch (KleeException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(divider);
                            break;
                        case deadline:
                            System.out.println(divider);
                            try {
                                String[] output = checkDeadline(input);
                                System.out.println("Klee will help you write that down! : " + output[0]);
                                Task task = new Deadline(output[0], output[1]);
                                tasks.add(task);
                                System.out.println(task.getStatus());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            } catch (KleeException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(divider);
                            break;
                        case event:
                            System.out.println(divider);
                            try {
                                String[] output = checkEvent(input);
                                System.out.println("Klee will help you write that down! : " + output[0]);
                                Task task = new Event(output[0], output[1], output[2]);
                                tasks.add(task);
                                System.out.println(task.getStatus());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            } catch (KleeException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(divider);
                            break;
                        case mark:
                            try{
                                Integer index = Integer.parseInt(command[1]) - 1;
                                tasks.get(index).mark();
                                System.out.println(divider);
                                System.out.println("Great! Klee will put a big cross on this box:");
                                System.out.println(tasks.get(index).getStatus());
                                System.out.println(divider);
                            } catch(NumberFormatException e) {
                                System.out.println(divider);
                                System.out.println("Klee doesn't understand, there should be a number after mark right?");
                                System.out.println(divider);
                            }
                            break;
                        case unmark:
                            try{
                                Integer index = Integer.parseInt(command[1]) - 1;
                                tasks.get(index).unMark();
                                System.out.println(divider);
                                System.out.println("Oh no! Klee will burn the cross away...:");
                                System.out.println(tasks.get(index).getStatus());
                                System.out.println(divider);
                            } catch(NumberFormatException e) {
                                System.out.println(divider);
                                System.out.println("Klee doesn't understand, there should be a number after unmark right?");
                                System.out.println(divider);
                            }
                            break;
                        case delete:
                            System.out.println(divider);
                            try{
                                int index = Integer.parseInt(command[1]) - 1;
                                if (index < tasks.size()) {
                                    System.out.println("Okay, Klee will wipe this task away!");
                                    System.out.println(tasks.get(index).getStatus());
                                    tasks.remove(index);
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                                } else System.out.println("But that task does not exist!");
                            } catch(NumberFormatException e) {
                                System.out.println("Klee doesn't understand, there should be a number after delete right?");
                            }
                            System.out.println(divider);
                        }
                    } catch (IllegalArgumentException e) {
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
