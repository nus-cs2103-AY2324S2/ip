import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private boolean isActive;
    private List<String> myList;

    public Duke() {
        this.isActive = true;
        this.myList = new ArrayList<>();
    }
    public static void main(String[] args) {
        Duke ft = new Duke();
        ft.init();
    }
    public void init() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm FriendlyTool\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________"
        );
        Scanner sc = new Scanner(System.in);
        while (this.isActive) {
            String input = sc.nextLine();
            nextAction(input);
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________" );
    }

    private void nextAction(String input) {
        if (input.equalsIgnoreCase("bye")) {
            this.isActive = false;
        } else if (input.equalsIgnoreCase("list")) {
            showList();
        } else {
            addTask(input);
        }
    }

    private void addTask(String input) {
        myList.add(input);
        System.out.println("    ____________________________________________________________\n"
                + "    added: "
                + input
                + "\n    ____________________________________________________________\n");
    }

    private void showList() {
        System.out.println("    ____________________________________________________________\n");
        for (int i = 1; i < myList.size() + 1; i++) {
            System.out.println("    " + i + ". " + myList.get(i-1));
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
