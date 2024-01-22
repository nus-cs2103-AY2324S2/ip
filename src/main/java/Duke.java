import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static java.lang.System.exit;

public class Duke {


    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Bingus";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm \n" + name);
        System.out.println("What can I do for you?\n");// initial introductory message

        Task list[] = new Task[100];// array to store tasks given



        int n = 0;
        Scanner bfn = new Scanner(
                new InputStreamReader(System.in));// scanner to read user input
        String str = bfn.nextLine();


        while (true) {
            String[] tokens = str.split("\\s+");// split read string into individual components to read keywords
            String identifier = tokens[0];// store keywords

            if (str.equals("list")) {
                for(int a = 0; a < n;a++ ) {
                    System.out.println(a+1 + ". " + list[a].ToString());
                }
            }// if keyword is list, open list

            else if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(1);// if keyword is bye, exit the program
            }

            else if (identifier.equals("mark")){
                int no = Integer.parseInt(tokens[1])+1;
                list[no].isDone = true;
                System.out.println("Nice! I've marked this task as done:\n");
                System.out.println(list[no].ToString());
            }

            else if (identifier.equals("unmark")){
                int no = Integer.parseInt(tokens[1])+1;
                list[no].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:\n");
                System.out.println(list[no].ToString());
            }


            else {
                System.out.println(str);

                list[n] = new Task(str);
                n++;
            }


            str = bfn.nextLine();
        }
    }
}
