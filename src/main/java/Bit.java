import java.io.IOException;
import java.util.ArrayList;
import  java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;


public class Bit {

    private static final Ui UI = new Ui();




    private static  String fileName = "./data/bit.txt";
    public static void main(String[] args) {

        UI.greet();
        Tasklist tasklist = new Tasklist(UI);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        File myFile = new File(fileName);
        myFile.getParentFile().mkdirs();
        if (!createFile(myFile)) {
            UI.fileError();
            return;
        }
        loadFile(list);
        cleanList();

        while(true) {
            UI.printLineBreak();
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                UI.listOut(tasklist);
            } else if (input.contains("mark ")) {
                String[] parts = input.split(" ");
                try {
                    int i = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        tasklist.mark(i);
                    } else if (parts[0].equals("unmark")) {
                        tasklist.unmark(i);
                    } else {
                        try{tasklist.addTo(input);} catch(DukeException e) {System.out.println(e.getMessage());}


                    }

                } catch (NumberFormatException e) {
                    try{
                        tasklist.addTo(input);
                    } catch(DukeException x) {
                        System.out.println(x.getMessage());}
                }
            } else if (input.startsWith("delete")) {
                try {
                    String[] strings = input.split(" ", 2);
                    int i = Integer.parseInt(strings[1]);
                    tasklist.delete(i);
                } catch (NumberFormatException x) {
                    UI.handleErrorMessage("Not a number");
                } catch (ArrayIndexOutOfBoundsException e) {
                    UI.handleErrorMessage("forget");
                }
            } else {
                try{
                    tasklist.addTo(input);
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            }



        }
        saveAll(list);
        UI.sayBye();

    }


    public static boolean createFile(File myFile) {
        try {
            if (myFile.createNewFile()) {
                return true;
            } else {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

    }

    public static void cleanList() {
        try {
            FileWriter myCleaner = new FileWriter(fileName);
            myCleaner.write("");
            myCleaner.close();
        } catch (IOException e) {
            return;
        }
    }
    public static  void saveAll(ArrayList<Task> list) {

        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t instanceof Todo) {
                saveToFile(t.isDone, t.description);
            } else if (t instanceof Deadline) {
                saveToFile(t.isDone, t.description, ((Deadline) t).getDeadline());
            } else if (t instanceof Event) {
                saveToFile(t.isDone, t.description, ((Event) t).getStart(), ((Event) t).getEnd());
            }

        }
    }

    public static void  saveToFile(boolean marked, String description) {
        try {
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write("T/" + description);
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    public static void saveToFile(boolean marked, String description, String deadline) {
        try {
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write(("D/" + description + "/" + deadline));
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    public static void saveToFile(boolean marked, String description, String start, String end) {
        try {
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write("E/" + description + "/" + start + "/" + end);
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    public  static void loadFile(ArrayList<Task> list) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String next;
            while ((next = br.readLine()) != null) {
                String[] parts = next.split("/");
                if (parts.length == 1) {
                    return;
                }
                System.out.println(parts[0]);
                System.out.println(parts[1]);
                if (parts[0].equals("T")) {
                    Task t = new Todo(parts[1]);
                    if (parts[2].equals("M")) {
                        t.complete();
                    }
                    list.add(t);
                } else if (parts[0].equals("D")) {
                    Task d = new Deadline(parts[1], parts[2]);
                    if (parts[3].equals("M")) {
                        d.complete();
                    }
                    list.add(d);
                } else if (parts[0].equals("E")) {
                    Task e = new Event(parts[1], parts[2], parts[3]);
                    if (parts[4].equals("M")) {
                        e.complete();
                    }
                    list.add(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Errorrrr...");
        }

    }



}
