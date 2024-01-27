import java.util.ArrayList;
import  java.util.Scanner;


public class Bit {



    public static String seperator = "---------------------------------------------------------";
    public static void main(String[] args) {

        System.out.println("Hi! This is Bit!\nWhat shall we do today?\n");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();


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
                        try{addTo(list, input);} catch(DukeException e) {System.out.println(e);}


                    }

                } catch (NumberFormatException e) {
                    try{addTo(list, input);} catch(DukeException x) {System.out.println(x);}


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
                try{addTo(list, input);} catch(DukeException e) {System.out.println(e);}

            }



        }
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
                    list.add(new Deadline(compo[0], compo[1]));
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


}
