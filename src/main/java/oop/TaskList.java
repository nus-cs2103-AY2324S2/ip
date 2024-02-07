package oop;

import java.util.ArrayList;
import task.Task;
import exceptions.DuplicateInstructionException;
import exceptions.OutOfIndexException;


public class TaskList {
    public ArrayList<Task> tasks;
    public static final String line = "\t______________________________________________________";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
    public void mark(int index) {
        try {
            if (tasks.size() < index) {
                throw new OutOfIndexException();
            } else if (tasks.get(index - 1).getStatusIcon().equals("X")) {
                throw new DuplicateInstructionException();
            }
            tasks.get(index - 1).markAsDone();
            System.out.println(line);
            System.out.println("\t Nice! I've marked this task as done:" + "\n\t\t" +
                    tasks.get(index - 1).print());
            System.out.println(line);
        } catch (OutOfIndexException e) {
            System.out.println(line);
            System.out.println(e.toString(tasks.size()));
            System.out.println(line);
        } catch (DuplicateInstructionException e) {
            System.out.println(line);
            System.out.println(e.toString("mark"));
            System.out.println(line);
        }
    }

    public void unmark(int index) {
        try {
            if (tasks.size() < index) {
                throw new OutOfIndexException();
            } else if (tasks.get(index - 1).getStatusIcon().equals(" ")) {
                throw new DuplicateInstructionException();
            }
            tasks.get(index - 1).unmarkAsDone();
            System.out.println(line);
            System.out.println("\t OK, I've marked this task as not done yet:" + "\n\t\t" +
                    tasks.get(index - 1).print());
            System.out.println(line);
        } catch (OutOfIndexException e) {
            System.out.println(line);
            System.out.println(e.toString(tasks.size()));
            System.out.println(line);
        } catch (DuplicateInstructionException e) {
            System.out.println(line);
            System.out.println(e.toString("unmark"));
            System.out.println(line);
        }
    }

    public void delete(int index) {
        try {
            if (tasks.size() < index || index < 1) {
                throw new OutOfIndexException();
            }
            System.out.println(line);
            System.out.println("\t OK, I've removed this task:" + "\n\t\t" +
                    tasks.get(index - 1).print());
            tasks.remove(index - 1);
            System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
        } catch (OutOfIndexException e) {
            System.out.println(line);
            System.out.println(e.toString(tasks.size()));
            System.out.println(line);
        }
    }

    public void add(Task task){
        try {
            for (Task value : tasks) {
                if (value.getDescription().equals(task.getDescription())) {
                    throw new DuplicateInstructionException();
                }
            }
            tasks.add(task);
            System.out.println(line);
            System.out.println("\t Got it. I've added this task:");
            System.out.print("\t   " + task.print());
            System.out.println("\n\t Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
        } catch (DuplicateInstructionException e) {
            System.out.println(line);
            System.out.println(e.toString(""));
            System.out.println(line);
        }
    }
}
