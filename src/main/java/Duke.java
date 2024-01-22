import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private final String name;
    private ArrayList<Task> taskList = new ArrayList<>();
    private final String line = "____________________________________________________________\n";
    public Duke(String name) {
        this.name = name;
    }
//
    public String greet() {
        return this.line + "Hello! I'm " + this.name + "\n" + "What can I do for you?\n" + this.line;
    }
    public String exit() {
        return this.line + "Bye. Hope to see you again soon!\n" +  this.line ;
    }

    public String printList(){
        String list = this.line;
        for (int i = 0; i<this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String message = Integer.toString(i+1) + ".[" + task.getStatusIcon() +"] " + task.getDescription() + "\n";
            list += message;

        }
        return list + this.line;
    }

    public void startChat() {
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(this.exit());
                break;

            } else if (command.equalsIgnoreCase("list")){
                System.out.println(this.printList());
            } else if (command.startsWith("mark ")){
                Integer id = Integer.parseInt(command.substring(5));
                System.out.println(this.line + this.taskList.get(id-1).markAsDone() + "\n" + this.line);

            } else if (command.startsWith("unmark ")){
                Integer id = Integer.parseInt(command.substring(7));
                System.out.println(this.line + this.taskList.get(id-1).markAsDone() + "\n" + this.line);
            }else {
                System.out.println(this.line + "added: "+ command + "\n"+this.line);
                taskList.add(new Task(command));
            }
        }
    }

    public static void main(String[] args) {
        Duke Lery = new Duke("Lery");


        Lery.startChat();

    }


}
