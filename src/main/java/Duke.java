public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String file, String fileParent, String name, String logo) {
        ui = new Ui(name, logo, System.in);
        storage = new Storage(file, fileParent);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";

        new Duke("Duke.txt", "./data", "JavAssist", logo).run();
    }
}

//
//
//    public static void main(String[] args) {
//        String logo = "\t  ____   __  _  _  __    ___  ___  ____  ___  ____\n\t"
//                + " (_  _) /__\\( \\/ )/__\\  / __)/ __)(_  _)/ __)(_  _)\n\t"
//                + " .-_)( /(__)\\\\  //(__)\\ \\__ \\\\__ \\ _)(_ \\__ \\  )(\n\t"
//                + "\\____)(__)(__)\\/(__)(__)(___/(___/(____)(___/ (__)\n";
//
//
//        TaskList list = new TaskList();
//
//        Storage sto = new Storage("Duke.txt", "./data");
//
//
//        list.setList(sto.readFromFile());
//
//
//        Ui ui = new Ui("JavAssist");
//
//        ui.showWelcome();
//
//        String input;
//
//
//        boolean isExit = false;
//
//        while (!isExit) {
//            input = ui.readCommand();
//            ui.showLine();
//            try {
//                Command c = Parser.parseCommand(input);
//                c.execute(list, ui, sto);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }
//
////    private static InputType getCommandType(String input) {
////
////        if (input.equals("list")) {
////            return InputType.LIST;
////        } else if (input.startsWith("mark")) {
////            return InputType.MARK;
////        } else if (input.startsWith("unmark")) {
////            return InputType.UNMARK;
////        } else if (input.startsWith("delete")) {
////            return InputType.DELETE;
////        } else if (input.equals("bye")) {
////            return InputType.BYE;
////        } else if (input.startsWith("todo")) {
////            return InputType.TODO;
////        } else if (input.startsWith("deadline")) {
////            return InputType.DEADLINE;
////        } else if (input.startsWith("event")) {
////            return InputType.EVENT;
////        } else {
////            return InputType.UNKNOWN;
////        }
////    }
//
////    private static void print(ArrayList<Task> list) {
////        if (list.isEmpty()) {
////            System.out.println("\t OOPS!!! No task in list ૮ ´• ﻌ ´• ა");
////            System.out.println("\t You may add task with keywords: todo, deadline, event.");
////        } else {
////            System.out.println("\t Here are the tasks in your list ૮ ˙Ⱉ˙ ა :");
////            int count = 1;
////            for (Task t : list) {
////                System.out.println("\t\t " + count + "." + t.printTask());
////                count++;
////            }
////        }
////    }
//
////    private static void add(Task t, ArrayList<Task> list) {
////        list.add(t);
////        int size = list.size();
////        System.out.println("\t ᨐฅ Got it! I've added this task:\n\t\t " + t.printTask());
////        System.out.println("\t Now you have " + size + (size > 1 ? " tasks" : " task") + " in the list.");
////    }
//
////    private static void delete(String input, ArrayList<Task> list) {
////        String[] s = input.split("\\s");
////        int num = Integer.parseInt(s[1]);
////        if (num <= list.size() && num >= 1) {
////            Task t = list.remove(num - 1);
////            System.out.println("\t Noted ૮ ˶ᵔ ᵕ ᵔ˶ ა I've removed this task:");
////            System.out.println("\t\t " + t.printTask());
////            System.out.println("\t Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.");
////        } else {
////            System.out.println("\t OOPS!!! Task (" + num + ") not found ૮₍ ˶0_0˶ ₎ა");
////            print(list);
////        }
////    }
////    private static void mark(String input, boolean done, TaskList list) {
////        String[] s = input.split("\\s");
////        int num = Integer.parseInt(s[1]);
////        if (num <= list.getList().size() && num >= 1) {
////            Task t = list.getList().get(num - 1);
////            if (!done) {
////                t.undo();
////                System.out.println("\t Ok, I've marked this task as not done yet:");
////            } else {
////                t.done();
////                System.out.println("\t Nice! I've marked this task as done:");
////            }
////            System.out.println("\t\t " + t.printTask());
////        } else {
////            System.out.println("\t OOPS!!! Task (" + num + ") not found.");
////            list.print();
////        }
////    }
//
////    private static void writeToFile(ArrayList<Task> list) throws IOException {
////        File f = new File("./data/Duke.txt");
////        File parent = new File("./data");
////        if (!parent.exists()) {
////            parent.mkdirs();
////        }
////        FileWriter fw = new FileWriter(f);
////        String data = write(list);
////        fw.write(data);
////        fw.close();
////    }
////
////    private static String write(ArrayList<Task> list) {
////        StringBuilder sb = new StringBuilder();
////        for (int i = 0; i < list.size(); i++) {
////            sb.append(list.get(i).toString());
////            if (i < list.size() - 1) {
////                sb.append("\n");
////            }
////        }
////        return sb.toString();
////    }
////
////    private static ArrayList<Task> readFromFile() throws FileNotFoundException {
////        ArrayList<Task> list = new ArrayList<Task>();
////        File f = new File("./data/Duke.txt");
////        Scanner s = new Scanner(f);
////        while (s.hasNext()) {
////            list.add(read(s.nextLine()));
////        }
////        return list;
////    }
////
////    private static Task read(String s) {
////        String[] cols = s.split(" \\| ");
////        Task t = null;
////        if (cols.length == 3) {
////            t = new Todo(cols[2]);
////
////        } else if (cols.length == 4) {
////            t = new Deadline(cols[2], cols[3]);
////        } else if (cols.length == 5) {
////            t = new Event(cols[2], cols[3], cols[4]);
////        }
////        if (cols[1].equals("1")) {
////            t.done();
////        } else {
////            t.undo();
////        }
////
////        return t;
////    }
//}
