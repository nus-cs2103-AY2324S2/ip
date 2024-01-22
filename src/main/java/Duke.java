import java.util.Scanner;
public class Duke {

    private static String name = "GanAnWo";

    private static Task[] task = new Task[100];

    private static int taskCount;
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?\n");
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
                    mark(Integer.parseInt(inputs[1]) - 1);
                } else if (inputs[0].equals("unmark")) {
                    unMark(Integer.parseInt(inputs[1]) - 1);
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
        task[taskCount] = new Task(item);
        taskCount++;
        System.out.println("added: " + item);
    }

    public static void showTask(){
        for (int i = 0 ; i < taskCount; i++){
            System.out.println(i+1 + ".[" + task[i].getStatusIcon() + "] " + task[i].description);
        }
    }

    public static void mark(int n){
        task[n].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" [" + task[n].getStatusIcon() +"] "+ task[n].description);
    }

    public static void unMark(int n){
        task[n].unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" [" + task[n].getStatusIcon() +"] "+ task[n].description);
    }
}
