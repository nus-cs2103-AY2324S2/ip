import java.io.IOException;
import java.util.ArrayList;
import  java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;


public class Bit {

    public static String seperator = "---------------------------------------------------------";



    private static  String fileName = "./data/bit.txt";
    public static void main(String[] args) {

        System.out.println("Hi! This is Bit!\nWhat shall we do today?\n");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        File myFile = new File(fileName);
        myFile.getParentFile().mkdirs();
        if (!createFile(myFile)) {
            System.out.println("Error! Something went wrong while creating your file!");
            return;
        }
        loadFile(list);
        cleanList();

        while(true) {
            System.out.println(seperator);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                toList(list);
            } else if (input.contains("mark ")) {
                String[] parts = input.split(" ");
                try {
                    int i = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        mark(i, list);
                    } else if (parts[0].equals("unmark")) {
                        unmark(i, list);
                    } else {
                        try{addTo(list, input);} catch(DukeException e) {System.out.println(e.getMessage());}


                    }

                } catch (NumberFormatException e) {
                    try{addTo(list, input);} catch(DukeException x) {System.out.println(x.getMessage());}


                }
            } else if (input.startsWith("delete")) {
                try {
                    String[] strings = input.split(" ", 2);
                    int i = Integer.parseInt(strings[1]);
                    delete(i, list);
                } catch (NumberFormatException x) {
                    System.out.println("Woah! That is not a number!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Did you forget something?");
                }
            } else {
                try{addTo(list, input);} catch(DukeException e) {System.out.println(e.getMessage());}

            }



        }
        saveAll(list);
        System.out.println("Alright. See you soon!!");

    }

    public static void addTo(ArrayList<Task> list, String input) throws DukeException{
        if(input.startsWith("todo")) {
            try {
                String[] parts = input.split(" ", 2);
                if (!parts[0].equals("todo")) {
                    throw new DukeException("I have no idea what that means!");
                }
                if (parts[1].trim().isEmpty()) {
                    throw new DukeException("Hmmm, that todo is empty!");
                }
                list.add(new Todo(parts[1]));
                int i = list.size();
                System.out.println("I have added this todo: " + (i) + " " + list.get(i - 1).toString());


            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Hmmm, that todo is empty!");
            }


        } else if (input.contains("event ")) {
            try {
                String[] parts = input.split(" ", 2);
                if (!parts[0].equals("event")) {
                    throw new DukeException("What is that?");
                }
                String[] components = parts[1].split("/to");
                String end = components[1];
                String[] compo = components[0].split("/from");
                String start = compo[1];
                if (compo[0].trim().isEmpty() || compo[1].trim().isEmpty() || end.trim().isEmpty()) {
                    throw new DukeException("Missing something?");
                }
                list.add(new Event(compo[0], start, end));
                int i = list.size();
                System.out.println("I have added this event: " + (i) + " " + list.get(i - 1).toString());



            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Did you make a mistake?");

            }

        } else if (input.startsWith("deadline")) {
            try {
                String[] parts = input.split(" ", 2);
                if (!(parts[0].equals("deadline"))) {
                    throw new DukeException("Is that a typo I see?");
                }
                if (parts[0].equals("deadline")) {
                    String[] compo = parts[1].split("/by");
                    if (compo[0].trim().isEmpty() || compo[1].trim().isEmpty()) {
                        throw new DukeException("Did you miss something?");
                    }
                    Deadline d = new Deadline(compo[0], compo[1]);
                    if (!d.getValid()) {
                        System.out.println("The date you entered is invalid.");
                        return;
                    }
                    list.add(d);
                    int i = list.size();
                    System.out.println("Done! I have added this to the list:" + list.get(i - 1).toString()
                     + "\n There are now " + (i) + " items");
                }
            } catch (ArrayIndexOutOfBoundsException x) {
                throw new DukeException("Did you miss something?");
            }

        } else {
            System.out.println("Sorry, I don't understand what you mean\n");
            return;
        }




    }


    public static void toList(ArrayList<Task> list) {
        System.out.println("Sure! Here is the list:\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public static void mark(int i, ArrayList<Task> list) {
        i -= 1;
        try {
            list.get(i).complete();
            System.out.println("Done and dusted: " + list.get(i).toString());
        } catch (IndexOutOfBoundsException x) {
            System.out.println("Hey, I don't think you have added that yet!");
        }
    }

    public static void unmark(int i, ArrayList<Task> list) {
        i -= 1;
        try {
            list.get(i).incomplete();
            System.out.println("Alright, let me uncheck that for you: " + list.get(i).toString());
        } catch (IndexOutOfBoundsException x) {
            System.out.println("Hey, I don't think you have added that yet!");
        }
    }

    public static void delete(int i, ArrayList<Task> list) {
        try {
            i -= 1;
            String s = list.get(i).toString();
            list.remove(i);
            System.out.println("Got it! I have deleted this item: " + s);
        } catch (IndexOutOfBoundsException x) {
            System.out.println("I couldn't find that task! Are you sure it exists?");
        }
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
