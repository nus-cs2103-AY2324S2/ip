package kirby.tasks;

import kirby.exceptions.MissingArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> inputList;

    public TaskList(ArrayList<Task> inputList){
        this.inputList = inputList;
    }

    public void list(){
        if(inputList.isEmpty()){
            System.out.println("You haven't added any tasks yet! I am waiting \uD83D\uDE0A");
        }

        for(int i = 0; i < inputList.size(); i++){
            System.out.println(i + 1 + ". " + inputList.get(i));
        }

        System.out.println();
    }

    public void mark(String var) throws MissingArgumentException {
        if (var.split(" ").length == 1){
            throw new MissingArgumentException("Missing Argument");
        }

        int temp = Integer.parseInt(var.split(" ")[1]);
        inputList.get(temp - 1).setDone(true);

        System.out.println("Hoooray! You did it:");
        System.out.println(temp + ". " + inputList.get(temp - 1));
        System.out.println();
    }

    public void unmark(String var) throws MissingArgumentException{
        if (var.split(" ").length == 1){
            throw new MissingArgumentException("Missing Argument");
        }


        int temp = Integer.parseInt(var.split(" ")[1]);
        inputList.get(temp - 1).setDone(false);

        System.out.println("Aww, don't give up! I believe you can do it:");
        System.out.println(temp + ". " + inputList.get(temp - 1));
        System.out.println();
    }

    public void todo(String var) throws MissingArgumentException{
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

        inputList.add(new TodoTask(t));
        System.out.println("____________________________________________________________");
        System.out.println("Okiiiie! I will remember: " + t);
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    public void delete(String var) throws MissingArgumentException{
        if (var.split(" ").length == 1){
            throw new MissingArgumentException("Missing Argument");
        }


        int temp = Integer.parseInt(var.split(" ")[1]);

        Task task = inputList.get(temp - 1);

        inputList.remove(temp - 1);

        System.out.println("Ok I will delete:");
        System.out.println(temp + ". " + task);
        System.out.println();
    }

    public void deadline(String var) throws MissingArgumentException{
        if (var.split(" ").length == 1){
            throw new MissingArgumentException("Missing Argument");
        }

        String[] commandDescription = var.split("/")[1].split(" ");

        String[] task = var.split("/")[0].split(" ");


        String deadline = var.split("/by")[1].trim();
        String t = "";



        for(int i = 0; i < task.length; i++){
            if(!task[i].equals("deadline")) {
                t += task[i] + " ";
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        inputList.add(new DeadlinedTask(t, LocalDateTime.parse(deadline, formatter)));
        System.out.println("____________________________________________________________");
        System.out.println("Okiiiie! I will remember: " + t + "by " + LocalDateTime.parse(deadline, formatter));
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    public void event(String var) throws MissingArgumentException{
        if (var.split(" ").length == 1){
            throw new MissingArgumentException("Missing Argument");
        }

        //done
        String[] task = var.split("/")[0].split(" ");

        String from = var.split("/from")[1].split("/to")[0].trim();

        String to = var.split("/to")[1].trim();

        String tsk = "";

        for (String s : task) {
            if (!s.equals("event")) {
                tsk += s + " ";
            }
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        inputList.add(new EventTask(tsk, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter)));
        System.out.println("____________________________________________________________");
        System.out.println("Okiiiie! I will remember: " + tsk + " (from: " + LocalDateTime.parse(from, formatter) + " to: " + LocalDateTime.parse(to, formatter) + ")" );
        System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

}
