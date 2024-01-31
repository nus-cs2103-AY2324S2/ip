import exception.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("    OOPS!!! ");
            } catch (Exception e) {
                System.out.println(Ui.INDENT_SEPERATOR + "\n    OOPS!!! Something went wrong D:\n"
                        + Ui.INDENT_SEPERATOR);
            } finally {
                storage.writeTasks(tasks);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
    /*private static void writeTasks(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("./data/bee.txt");
            for (Task t : list) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private ArrayList<Task> initTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File inputFile = new File("./data/bee.txt");
            if (inputFile.createNewFile()) {
                // new file created
            } else {
                list = readTasks(inputFile);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    private ArrayList<Task> readTasks(File inputFile) {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String type = line.substring(1, 2);
                String mark = line.substring(5, 6);

                if (type.equals("T")) {
                    String description = line.substring(7);
                    Todo t = new Todo(description);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("D")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(by: ");
                    String description  = result[0];
                    String by = result[1].substring(0, result[1].length() - 1);
                    LocalDate date = LocalDate.parse(by.split(" ")[0].trim());
                    LocalTime time = LocalTime.parse(by.split(" ")[1].trim());
                    Deadline t = new Deadline(description, date, time);
                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                } else if (type.equals("E")) {
                    String temp = line.substring(7);
                    String[] result = temp.split("\\(from: ");
                    String description  = result[0];
                    result = result[1].split(" | to: ");
                    String from = result[0];
                    String to = result[1];
                    to = to.substring(0, to.length() - 1);
                    LocalDate startDate = LocalDate.parse(from.split(" ")[0].trim());
                    LocalTime startTime = LocalTime.parse(from.split(" ")[1].trim());
                    LocalDate endDate = LocalDate.parse(to.split(" ")[0].trim());
                    LocalTime endTime = LocalTime.parse(to.split(" ")[1].trim());
                    Event t = new Event(description, startDate, startTime, endDate, endTime);


                    if (mark.equals("X")) {
                        t.markAsDone();
                    }
                    list.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            return list;
        }
    }*/

    /*// print the entire list
    private static void showList(ArrayList<Task> list) {
        System.out.println(INDENT_SEPERATOR);
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println(INDENT + (i + 1) + "." + t.toString());
        }
        System.out.println(INDENT_SEPERATOR);
    }

    // mark an item in list
    private static void mark(String input, ArrayList<Task> list) {
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;

        // check for error
        if (position + 1 > list.size()) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");
        } else if (position < 0) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");

        } else {
            System.out.println(INDENT_SEPERATOR);
            Task t = list.get(position);
            if (t.getStatusIcon().equals(" ")) {
                System.out.println(INDENT + "Nice! I've marked this task as done:");
            }
            t.markAsDone();
            System.out.println(INDENT + "  " + t.toString());
        }
        System.out.println(INDENT_SEPERATOR);


    }

    // unmark an item in list
    private static void unmark(String input, ArrayList<Task> list) {
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;

        // check for error
        if (position + 1 > list.size()) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");
        } else if (position < 0) {
            System.out.println(INDENT_SEPERATOR);
            System.out.println(INDENT + "Task does not exist");

        } else {
            System.out.println(INDENT_SEPERATOR);
            Task t = list.get(position);
            if (t.getStatusIcon().equals("X")) {
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
            }
            t.markAsUndone();
            System.out.println(INDENT + "  " + t.toString());
        }
        System.out.println(INDENT_SEPERATOR);
    }
    private static void todo(String input, ArrayList<Task> list) throws EmptyInputException {
        System.out.println(INDENT_SEPERATOR);
        if (input.split(" ").length > 1) {
            String description = input.substring(4).trim();
            Todo t = new Todo(description);
            list.add(t);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + t.toString());
            System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
        } else {
            throw new EmptyInputException("todo");
        }
        System.out.println(INDENT_SEPERATOR);
    }

    private static void deadline(String input, ArrayList<Task> list)
            throws EmptyInputException, EmptyTimeException, InvalidFormatException, InvalidDateTimeException {
        System.out.println(INDENT_SEPERATOR);
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("deadline");
        }  else if (!input.contains("/by")) {
            throw new InvalidFormatException("deadline", "/by");
        } else {
            input = input.substring(8);
            String[] arrOfStr = input.split("/by");
            if (arrOfStr.length < 2) {
                throw new EmptyTimeException("deadline", "end");
            } else {
                try {
                    String description = arrOfStr[0].trim();
                    String by = arrOfStr[1].trim();
                    LocalDate date = LocalDate.parse(by.split(" ")[0].trim());
                    LocalTime time = LocalTime.parse(by.split(" ")[1].trim());
                    Deadline t = new Deadline(description, date, time);
                    list.add(t);
                    System.out.println(INDENT + "Got it. I've added this task:");
                    System.out.println(INDENT + "  " + t.toString());
                    System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
                    System.out.println(INDENT_SEPERATOR);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("deadline");
                }
            }
        }
    }
    private static void event(String input, ArrayList<Task> list)
            throws EmptyInputException, EmptyTimeException, InvalidFormatException, InvalidDateTimeException {
        System.out.println(INDENT_SEPERATOR);
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("event");
        }  else if (!input.contains("/from")) {
            throw new InvalidFormatException("event", "/from");
        }  else if (!input.contains("/to")) {
            throw new InvalidFormatException("event", "/to");
        } else {
            String temp = input.substring(5);
            String[] arrOfStr = temp.split("/");
            if (arrOfStr.length < 2) {
                throw new EmptyTimeException("event", "start");
            } else if (arrOfStr.length < 3) {
                throw new EmptyTimeException("event", "end");
            } else {
                try {
                    String description = arrOfStr[0].trim();
                    String start = arrOfStr[1].substring(4).trim();
                    LocalDate startDate = LocalDate.parse(start.split(" ")[0].trim());
                    LocalTime startTime = LocalTime.parse(start.split(" ")[1].trim());
                    String end = arrOfStr[2].substring(2).trim();
                    LocalDate endDate = LocalDate.parse(end.split(" ")[0].trim());
                    LocalTime endTime = LocalTime.parse(end.split(" ")[1].trim());
                    Event t = new Event(description, startDate, startTime, endDate, endTime);
                    list.add(t);
                    System.out.println(INDENT + "Got it. I've added this task:");
                    System.out.println(INDENT + "  " + t.toString());
                    System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
                    System.out.println(INDENT_SEPERATOR);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("deadline");
                }
            }
        }
    }

    private static void delete(String input, ArrayList<Task> list) {
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;
        System.out.println(INDENT_SEPERATOR);

        // check for error
        if (position + 1 > list.size()) {
            System.out.println(INDENT + "Task does not exist");
        } else if (position < 0) {
            System.out.println(INDENT + "Task does not exist");

        } else {
            Task t = list.get(position);
            list.remove(t);
            System.out.println(INDENT + "Noted. I've removed this task:");
            System.out.println(INDENT + "  " + t.toString());
            System.out.println(INDENT + "Now you have " + list.size() + " tasks in the list.");
        }
        System.out.println(INDENT_SEPERATOR);
    }*/

}
