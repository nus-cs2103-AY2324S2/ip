import java.util.Scanner;
import java.util.*;
public class GPT {
    public static void main(String[] args) {
         class Task {
            boolean done;
            String todo;
            public Task(String todo) {
                this.todo = todo;
                this.done = false;
            }

            public void mark() {
                this.done = true;
            }
            public void unmark() {
                this.done = false;
            }
        }
        Scanner scn = new Scanner(System.in);
        String name = " GPT";
        String secLine = "What can I do for you?\n\n";

        System.out.println("Hello! I'm" + name);
        System.out.println(secLine);
        String s = scn.nextLine();
        ArrayList<Task> tl = new ArrayList<>();
//        ArrayList<String> sl = new ArrayList<>();
//        ArrayList<Boolean> done = new ArrayList<>();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 1; i <= tl.size(); i++) {
                    System.out.println(i + (tl.get(i - 1).done ? ". [x] " : ". [ ] ") + tl.get(i - 1));
                }

            } else if (s.contains("unmark")) {
                String[] splited = s.split("\\s+");
                if (splited[0].equals("unmark") && Integer.valueOf(splited[1]) <= tl.size()) {
                    tl.get(Integer.valueOf(splited[1])-1).unmark();
                }

            } else if (s.contains("mark")) {
                String[] splited = s.split("\\s+");
                if (splited[0].equals("mark") && Integer.valueOf(splited[1]) <= tl.size()) {
                    tl.get(Integer.valueOf(splited[1])-1).mark();
                }
            }else {
                    System.out.println("added " + s);
                    tl.add(new Task(s));
                }
                s = scn.nextLine();

            }


            System.out.println("ByeBye. Hope to see you soon");
        }
    }

