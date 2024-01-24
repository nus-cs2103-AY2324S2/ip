import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> mylist= new ArrayList<>();
        Intro Hi = new Intro();
        Hi.response();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            String[] words = command.split(" ");
            if (command.equals("bye")) {
                Farewell Bye = new Farewell();
                Bye.response();
                break;
            }
            else if (command.equals("list")) {
                List lists2 = new List(mylist);
                lists2.response();

            }
            else if (words[0].equals("mark")) {
                String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                Task tsk = mylist.get(Integer.parseInt(result) - 1);
                tsk.mark();
                Mark mark = new Mark();
                mark.response();
                System.out.println(tsk);
            }
            else if (words[0].equals("unmark")) {
                String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                Task ts = mylist.get(Integer.parseInt(result) - 1);
                ts.unmark();
                Unmark unmark = new Unmark();
                unmark.response();
                System.out.println(ts);
            }
            else {
                mylist.add(new Task(command));
                Echo repeat = new Echo(command);
                repeat.response();
            }
        }
    }
}


