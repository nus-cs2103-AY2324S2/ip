import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    private final String name;
    private ArrayList<String> taskList = new ArrayList<>();
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
            String task = Integer.toString(i+1) + ". " + this.taskList.get(i) + "\n";
            list += task;

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

            }else {
                System.out.println(this.line + "added: "+ command + "\n"+this.line);
                taskList.add(command);
            }
        }
    }

    public static void main(String[] args) {
        Duke Lery = new Duke("Lery");


        Lery.startChat();

    }


}
