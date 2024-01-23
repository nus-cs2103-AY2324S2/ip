import java.util.Scanner;
public class Duke {

    private static String name = "GanAnWo";

    private static Task[] task = new Task[100];

    private static int taskCount;
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?");
        String input;
        Boolean running = true;

        while (running){
            input = inp.nextLine();
            String[] inputs = input.split(" ");
            if(input.equals("bye")) {
                running = false;
                break;
            } else if (inputs.length == 2 && (inputs[0].equals("mark") || inputs[0].equals("unmark") )) {
                if (inputs[0].equals("mark")){
                    mark(inputs[1]);
                } else if (inputs[0].equals("unmark")) {
                    unMark(inputs[1]);
                }
            } else if (input.equals("list")) {
                showTask();
            } else {
                addTask(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void addTask(String item){
        String[] inputs = item.split(" ");
        String[] name;
        switch (inputs[0]){
            case "event":
                name = item.split("event ");
                String[] desFromTo = name[1].split(" /from ");
                String[] fromTo = desFromTo[1].split(" /to ");

                task[taskCount] = new Event(desFromTo[0], fromTo[0], fromTo[1]);
                break;
            case "todo":
                name = item.split("todo ");
                task[taskCount] = new ToDos(name[1]);
                break;
            case "deadline":
                name = item.split("deadline ");
                String[] desBy = name[1].split(" /by ");
                task[taskCount] = new Deadline(desBy[0], desBy[1]);
                break;
            default:
                task[taskCount] = new Task(item);
        }
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task[taskCount-1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");

    }

    public static void showTask(){
        for (int i = 0 ; i < taskCount; i++){
            System.out.println(i+1 + "." + task[i].toString());
        }
    }

    public static void mark(String n){
        int noArr;
        try {
            noArr = Integer.parseInt(n)-1;
            if (task[noArr] == null){
                throw new ArrayIndexOutOfBoundsException();
            } else {
                task[noArr].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + task[noArr].getStatusIcon() + "] " + task[noArr].description);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No task number " + n);
        } catch (NumberFormatException e){
            System.out.println("The task number given is not a number");
        }
    }

    public static void unMark(String n){
        int noArr;
        try {
            noArr = Integer.parseInt(n)-1;
            if (task[noArr] == null){
                throw new ArrayIndexOutOfBoundsException();
            } else {
                task[noArr].unMark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [" + task[noArr].getStatusIcon() +"] "+ task[noArr].description);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No task number " + n);
        } catch (NumberFormatException e){
            System.out.println("The task number given is not a number");
        }
    }
}
