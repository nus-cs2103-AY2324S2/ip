public class UI {
    private static final String line = "      ________________________________________________________\n";

    public static void printResponse(String output){
        System.out.print(UI.line);
        System.out.printf("      added: %s \n", output);
        System.out.print(UI.line);
    }

    public static void printResponse(int num, Task output, boolean isLast){ //printing of list items
        if (!isLast) {
            if (num == 1) {
                System.out.print(UI.line);
                System.out.printf("      Here are the tasks in your list: \n      %d.[%s] %s \n", num, output.getStatusIcon(), output.getDescription());
            } else {
                System.out.printf("      %d.[%s] %s \n", num, output.getStatusIcon(), output.getDescription());
            }
        } else {
            System.out.printf("      %d.[%s] %s \n", num, output.getStatusIcon(), output.getDescription());
            System.out.print(UI.line);
        }
    }

    public static void printMarking(Task task, boolean done) {
        if (done) {
            System.out.print(UI.line + "      Great job! I've marked this task as done: \n");
            System.out.printf("      [%s] %s \n", task.getStatusIcon(), task.getDescription());
            System.out.print(UI.line);
        } else {
            System.out.print(UI.line + "      Ok, I've marked this task as not done yet: \n");
            System.out.printf("      [%s] %s \n", task.getStatusIcon(), task.getDescription());
            System.out.print(UI.line);
        }
    }

    public static void printBye(){
        System.out.print(UI.line + "      Bye! Have a great day ahead :>\n" + UI.line);
    }
}
