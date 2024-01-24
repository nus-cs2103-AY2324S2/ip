import java.sql.SQLOutput;
import java.util.Scanner;

public class ChatBotTest {
    public static void main(String[] args) {
        Ping p = new Ping();
        String name = p.name;

        // The Welcome Part
        System.out.println("Hello! I'm "+name+
                "\nWhat can I do for you?");

        // The Scanner Part
        Scanner sc = new Scanner(System.in);
        while (true) {
            String commands = sc.nextLine();
            if (commands.equals("bye")) {
                p.goodBye();
                break;
            } else if (commands.equals("blah")) {
                System.out.println("haha, that's humorous");
            }
              else if (commands.equals("list")) {
                p.listTasks();
            } else if (commands.indexOf("delete") == 0) {
                String[] delCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(delCommand[1]) - 1;
                    p.delete(i);
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            }
              else if (commands.indexOf("mark") == 0) {
                String[] markCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(markCommand[1]) - 1;
                    p.markJobs(p.tasks.get(i));
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            } else if (commands.indexOf("unmark") == 0) {
                String[] unmarkCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(unmarkCommand[1]) - 1;
                    p.unMarkJobs(p.tasks.get(i));
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            } else if (commands.indexOf("todo") == 0) {
                String[] todoCommand = commands.split(" ");
                String rest = "";
                try {
                    for (int i = 1; i < todoCommand.length; i++) {
                        rest = rest + todoCommand[i] + " ";
                    }
                    Todo j  = new Todo(rest);
                    if (rest.length() > 0) {
                        p.todoJobs(j);
                    } else {
                        System.out.println("Todo what? you can't to do nothing right?");
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
             } else if (commands.indexOf("deadline") == 0) {
                String[] dlCommand = commands.split(" ");
                String rest = "";
                String date = "";
                try {
                    int idx = 0;
                    for (int i = 1; i < dlCommand.length; i++) {
                        if (dlCommand[i].equals("/by")) {
                            idx = i;
                            break;
                        } else {
                            rest = rest + dlCommand[i] + " ";
                        }
                    }
                    // Check for weekdays or month
                    int check = idx + 1;
                    for (int j = idx + 1; j < dlCommand.length; j++) {
                        if (check != dlCommand.length - 1) {
                            date = date + dlCommand[j] + " ";
                            check++;
                        } else {
                            date = date + dlCommand[j];
                        }
                    }
                    Deadline dl = new Deadline(rest, date);
                    if (rest.length() > 0) {
                        p.dlJobs(dl);
                    } else {
                        System.out.println("deadline? what thing make you so hurry that even dont tell me?");
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            } else if (commands.indexOf("event") == 0) {
                String[] evCommand = commands.split(" ");
                String rest = "";
                String date1 = "";
                String date2 = "";
                try {
                    int idx = 0;
                    for (int i = 1; i < evCommand.length; i++) {
                        if (evCommand[i].equals("/from")) {
                            idx = i;
                            break;
                        } else {
                            rest = rest + evCommand[i] + " ";
                        }
                    }
                    int idx2 = 0;
                    for (int j = idx + 1; j < evCommand.length; j++) {
                        if (evCommand[j].equals("/to")) {
                            idx2 = j;
                            break;
                        } else {
                            date1 = date1 + evCommand[j] + " ";
                        }
                    }
                    int check = idx2 + 1;
                    for (int k = idx2 + 1; k < evCommand.length; k++) {
                        if (check != evCommand.length - 1) {
                            date2 = date2 + evCommand[k] + " ";
                            check++;
                        } else {
                            date2 = date2 + evCommand[k];
                        }
                    }
                    Event e = new Event(rest, date1, date2);
                    if (rest.length() > 0) {
                        p.evJobs(e);
                    } else {
                        System.out.println("event what? I need a thing!");
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            }
            else {
                Task t = new Task(commands);
                p.addTask(t);
            }
        }


    }
}
