import java.util.Scanner;

public class Duke {

    private final String name;
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

    public void startChat() {
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")){
                System.out.println(this.exit());
                break;

            } else {
                System.out.println(this.line + command + "\n"+this.line);

            }
        }
    }

    public static void main(String[] args) {
        Duke Lery = new Duke("Lery");


        Lery.startChat();

    }


}
