import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public void mark() {
                isDone = true;
            }

            public void unMark() {
                isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]");
            }

            @Override
            public String toString() {
                return getStatusIcon() + " " + description;
            }
        }

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Tom \nWhat can I do for you?\n");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskcount = 0;


        while (true){
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);

            if ("bye".equals(splitInput[0])){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(splitInput[0])) {
                for(int i=0; i< taskcount;i++){
                    System.out.println((i+1)+". " + tasks[i]);
                }

            } else if("mark".equals(splitInput[0])){
                int index = Integer.parseInt(splitInput[1])-1;
                tasks[index].mark();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);

            } else if ("unmark".equals(splitInput[0])) {
                int index = Integer.parseInt(splitInput[1])-1;
                tasks[index].unMark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);

            } else{
                tasks[taskcount] = new Task(input);
                System.out.println("added: "+input);
                taskcount++;
            }


        }

    }

}
