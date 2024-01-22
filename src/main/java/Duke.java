import java.util.Scanner;

public class Duke {

    private int lastTaskNo = 0;

    private String[] tasks = new String[100];
    private void greet(){
        System.out.println("Hello! I'm Balom.\n" +
                "What can I do for you today?\n" +
                "Please start typing something.\n");
    }
    private void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void showTasks(){
        //Direct user if empty list
        if(lastTaskNo == 0){
            System.out.println("Add tasks to list first! Type something other than List/list or Bye/bye.");
        }
        for(int i = 0; i< lastTaskNo; i++){
            System.out.println(tasks[i]);
            System.out.println();
        }
    }
    private void chatting(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            //get input
            String echo = scanner.nextLine();

            //catch end word or list to display list
            if(echo.equals("bye") || echo.equals("Bye")){
                break;
            } else if (echo.equals("list")|| echo.equals("List")) {
                showTasks();
            } else {
                // add to tasks
                tasks[lastTaskNo] = Integer.toString(lastTaskNo + 1) + "." + echo;
                lastTaskNo++;
                System.out.println("added " + echo);
                System.out.println();
            }
        }

        bye();
    }
    public static void main(String[] args) {
        Duke Balom = new Duke();
        Balom.greet();
        Balom.chatting();
    }
}
