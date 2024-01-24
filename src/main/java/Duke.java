import java.util.ArrayList;
import java.util.Scanner;

class Pair {
    public boolean checked;
    public String second;

    public Pair(String second) {
        this.checked = false;
        this.second = second;
    }

    @Override
    public String toString() {
        return "[ " + (checked ? "X" : " ") + " ] " + second;
    }
}

public class Duke {
    static String line = "____________________________________________________________";

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("Hello! I'm Brian\nWhat can I do for you?");
        System.out.println(line);
        ArrayList<Pair> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] input = sc.nextLine().split(" ");
            System.out.println(line);
            switch (input[0]) {
                case "list": {
                    for (int i = 0; i < data.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, data.get(i));
                    }
                    break;
                }
                case "mark": {
                    int index = Integer.parseInt(input[1]) - 1;
                    data.get(index).checked = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(data.get(index));
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(input[1]) - 1;
                    data.get(index).checked = false;
                    System.out.println("Okay! I've marked this task as not done yet");
                    System.out.println(data.get(index));
                    break;
                }
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;
                }
                default: {
                    data.add(new Pair(input[0]));
                    System.out.println("added: " + input[0]);
                    break;
                }
            }
            System.out.println(line);
        }
    }
}
