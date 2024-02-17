import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Kirby {
    public static void main(String[] args) {

        String message =
                "____________________________________________________________\nHiiiiii \uD83D\uDE00! I'm Kirby Yayyyyy \uD83C\uDF8C!\nWhat can I do for you?\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);



        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home,  "data", "kirby.txt");
        File saveFile = path.toFile();

        ArrayList<Task> inputs = new ArrayList<>();

        try {
            saveFile.createNewFile();

            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            inputs = (ArrayList<Task>) objectInputStream.readObject();

            objectInputStream.close();

        }catch(IOException e){
            System.out.println("Error: Input seems to not work");
        }catch(SecurityException e){
            System.out.println("Error: Don't have permissions to create Save File");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Save File is corrupted");
        }


        System.out.println(message);

        String var = sc.nextLine();

        while(!var.equals("bye")){

            //list command
            if(var.equals("list")){

                if(inputs.isEmpty()){
                    System.out.println("You haven't added any tasks yet! I am waiting \uD83D\uDE0A");
                }

                for(int i = 0; i < inputs.size(); i++){
                    System.out.println(i + 1 + ". " + inputs.get(i));
                }

                System.out.println();

                var = sc.nextLine();
                continue;
            }

            try {
                //mark command
                if (var.split(" ")[0].equals("mark")) {

                    if (var.split(" ").length == 1){
                        throw new MissingArgumentException("Missing Argument");
                    }

                    int temp = Integer.parseInt(var.split(" ")[1]);
                    inputs.get(temp - 1).setDone(true);

                    System.out.println("Hoooray! You did it:");
                    System.out.println(temp + ". " + inputs.get(temp - 1));
                    System.out.println();

                    var = sc.nextLine();
                    continue;
                }

                //unmark command
                if (var.split(" ")[0].equals("unmark")) {

                    if (var.split(" ").length == 1){
                        throw new MissingArgumentException("Missing Argument");
                    }


                    int temp = Integer.parseInt(var.split(" ")[1]);
                    inputs.get(temp - 1).setDone(false);

                    System.out.println("Aww, don't give up! I believe you can do it:");
                    System.out.println(temp + ". " + inputs.get(temp - 1));
                    System.out.println();

                    var = sc.nextLine();
                    continue;
                }

                //todo command
                if (var.split(" ")[0].equals("todo")){

                    if (var.split(" ").length == 1){
                        throw new MissingArgumentException("Missing Argument");
                    }


                    String[] command = var.split(" ");

                    String t = "";

                    for (String s : command) {
                        if (!s.equals("todo")) {
                            t += s + " ";
                        }
                    }

                    inputs.add(new TodoTask(t));
                    System.out.println("____________________________________________________________");
                    System.out.println("Okiiiie! I will remember: " + t);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");

                    var = sc.nextLine();
                    continue;
                }

                //delete command
                if (var.split(" ")[0].equals("delete")){

                    if (var.split(" ").length == 1){
                        throw new MissingArgumentException("Missing Argument");
                    }


                    int temp = Integer.parseInt(var.split(" ")[1]);
                    
                    Task task = inputs.get(temp - 1);

                    inputs.remove(temp - 1);

                    System.out.println("Ok I will delete:");
                    System.out.println(temp + ". " + task);
                    System.out.println();

                    var = sc.nextLine();
                    continue;
                }

                //deadline command
                if (var.split(" ")[0].equals("deadline")){

                    if (var.split(" ").length == 1){
                        throw new MissingArgumentException("Missing Argument");
                    }

                    String[] command = var.split("/")[1].split(" ");

                    String[] task = var.split("/")[0].split(" ");


                    String deadline = "";
                    String t = "";


                    for(int i = 0; i < command.length; i++){
                        if(!command[i].equals("by")) {
                            deadline += command[i] + " ";
                        }
                    }

                    for(int i = 0; i < task.length; i++){
                        if(!task[i].equals("deadline")) {
                            t += task[i] + " ";
                        }
                    }

                    inputs.add(new DeadlinedTask(t, deadline));
                    System.out.println("____________________________________________________________");
                    System.out.println("Okiiiie! I will remember: " + t + "by " + deadline);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");

                    var = sc.nextLine();
                    continue;
                }

                //event command
                if (var.split(" ")[0].equals("event")){

                    if (var.split(" ").length == 1){
                        throw new MissingArgumentException("Missing Argument");
                    }


                    String[] task = var.split("/")[0].split(" ");

                    String[] from = var.split("/")[1].split(" ");

                    String[] to = var.split("/")[2].split(" ");

                    String tsk = "";

                    String frm = "";

                    String t = "";


                    for(int i = 0; i < task.length; i++){
                        if(!task[i].equals("event")) {
                            tsk += task[i] + " ";
                        }
                    }


                    for(int i = 0; i < from.length; i++){
                        if(!from[i].equals("from")) {
                            frm += from[i] + " ";
                        }
                    }

                    for(int i = 0; i < to.length; i++){
                        if(!to[i].equals("to")) {
                            t+= to[i] + " ";
                        }
                    }

                    inputs.add(new EventTask(tsk, frm, t));
                    System.out.println("____________________________________________________________");
                    System.out.println("Okiiiie! I will remember: " + tsk + " (from: " + frm + "to: " + t + ")" );
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");

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
            /*
            inputs.add(new Task(var));
            System.out.println("____________________________________________________________");
            System.out.println("Okiiiie! I will remember: " + var);
            System.out.println("____________________________________________________________\n");
            var = sc.nextLine();
            */

            // var = sc.nextLine();
        }

        //bye command
        System.out.println("Noooo \uD83E\uDD7A You are leaving already \uD83D\uDE16? Hope to see you again soon!\n____________________________________________________________");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(inputs);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch(IOException e){
            System.out.println("Error: Could not write to save file");
        }
    }
}