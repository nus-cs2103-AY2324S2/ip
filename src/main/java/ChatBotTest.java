import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class ChatBotTest {
    public static void main(String[] args) {
        Ping p = new Ping();
        String name = p.name;
        p.tasks = FileManage.loadFiles();

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
                    FileManage.saveFiles(p.tasks);
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            }
              else if (commands.indexOf("mark") == 0) {
                String[] markCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(markCommand[1]) - 1;
                    p.markJobs(p.tasks.get(i));
                    FileManage.saveFiles(p.tasks);
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            } else if (commands.indexOf("unmark") == 0) {
                String[] unmarkCommand = commands.split(" ");
                try {
                    int i = Integer.parseInt(unmarkCommand[1]) - 1;
                    p.unMarkJobs(p.tasks.get(i));
                    FileManage.saveFiles(p.tasks);
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
                        FileManage.saveFiles(p.tasks);
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

                    LocalDate dt = DateTimeCheck.timeCheckOnDate(date);
                    Deadline dl = new Deadline(rest, dt);
                    if (rest.length() > 0 && dt != null) {
                        p.dlJobs(dl);
                        FileManage.saveFiles(p.tasks);
                    } else {
                        System.out.println("Did you type right?");
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
                    LocalDateTime dt1 = DateTimeCheck.timeCheckOnTime(date1.stripTrailing());
                    LocalDateTime dt2 = DateTimeCheck.timeCheckOnTime(date2);
                    boolean compareOfTime = DateTimeCheck.timeCompare(dt1, dt2);
                    Event e = new Event(rest, dt1, dt2);
                    if (!rest.isEmpty() && dt1 != null && dt2 != null && compareOfTime) {
                        p.evJobs(e);
                        FileManage.saveFiles(p.tasks);
                    } else {
                        System.out.println("Did you type right?");
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect number or command");
                }
            }
            else {
                Task t = new Task(commands);
                p.addTask(t);
                FileManage.saveFiles(p.tasks);
            }
        }

    }
}
