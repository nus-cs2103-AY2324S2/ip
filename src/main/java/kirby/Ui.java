package kirby;

import kirby.exceptions.InvalidCommandException;
import kirby.exceptions.MissingArgumentException;
import kirby.tasks.TaskList;

import java.util.Scanner;

public class Ui {

    public static void receiveInput(Scanner sc, TaskList inputs){

        String var = sc.nextLine();

        while(!var.equals("bye")){

            //list command
            if(var.equals("list")){

                inputs.list();

                var = sc.nextLine();
                continue;
            }

            try {
                //mark command
                if (var.split(" ")[0].equals("mark")) {

                    inputs.mark(var);

                    var = sc.nextLine();
                    continue;
                }

                //unmark command
                if (var.split(" ")[0].equals("unmark")) {

                    inputs.unmark(var);

                    var = sc.nextLine();
                    continue;
                }

                //todo command
                if (var.split(" ")[0].equals("todo")){

                    inputs.todo(var);

                    var = sc.nextLine();
                    continue;
                }

                //delete command
                if (var.split(" ")[0].equals("delete")){

                    inputs.delete(var);

                    var = sc.nextLine();
                    continue;
                }

                //deadline command
                if (var.split(" ")[0].equals("deadline")){

                    inputs.delete(var);

                    var = sc.nextLine();
                    continue;
                }

                //event command
                if (var.split(" ")[0].equals("event")){

                    inputs.event(var);

                    var = sc.nextLine();
                    continue;
                }

                throw new InvalidCommandException("Invalid Command");

            } catch(IndexOutOfBoundsException e){
                System.out.println("Sorry that item doesn't exist");
                // e.printStackTrace();
                var = sc.nextLine();
                continue;
            } catch(NumberFormatException e){
                System.out.println("You have to indicate the number on the list");
                var = sc.nextLine();
                continue;
            } catch(MissingArgumentException e){
                System.out.println("It seems that you are missing some details. I'm not really sure what I'm supposed to do");
                var = sc.nextLine();
                continue;

            } catch(InvalidCommandException e){
                System.out.println("Please enter a valid command");
                var = sc.nextLine();
                continue;
            }
        }

        //bye command
        System.out.println("Noooo \uD83E\uDD7A You are leaving already \uD83D\uDE16? Hope to see you again soon!\n____________________________________________________________");

    }

}
