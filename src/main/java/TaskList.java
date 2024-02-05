import java.util.ArrayList;


public class TaskList {
        ArrayList<Task> taskList;
        boolean isDoneDefault = false;

    //    ArrayList<Task> taskList = new ArrayList<>();
//    String FILE_PATH = "data/hal.txt";
//    boolean isDoneDefault = false;
//    File file;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
//    public TaskList(ArrayList<Task> prevTaskList) {
//        this.taskList = prevTaskList;
//    }

    public String addTask(String userInput) {
        String[] userInputArray = userInput.split(" ");
        String taskType = userInputArray[0];
        // return String can be "error" or added task
        String returnOutput = "";

        try {
            // Checks if task description is empty,
            if (userInputArray.length == 1) {
                // Missing description error
                throw new HALException("Missing description!");

            // If task description is not empty, proceed as per normal
            } else {

                if (userInputArray[0].equalsIgnoreCase("todo")) {
                    String description = userInput.substring(4).trim();
                    taskList.add(new Todo(isDoneDefault, description));

                } else if (userInputArray[0].equalsIgnoreCase("deadline")) {

                    int keywordIndex = userInput.indexOf("/by");

                    if (keywordIndex != -1) {
                        String description = userInput.substring(8, keywordIndex).trim();
                        String deadline = userInput.substring(keywordIndex + 3).trim();

                        taskList.add(new Deadline(isDoneDefault, description, deadline));
                    } else {
                        // Missing keyword error
                        throw new HALException("Missing keyword /by!");

                    }
                } else if (userInputArray[0].equalsIgnoreCase("event")) {
                    int keywordIndex = userInput.indexOf("/from");
                    int keyword2Index = userInput.indexOf("/to");

                    if (keywordIndex != -1 && keyword2Index != -1) {
                        String description = userInput.substring(5, keywordIndex).trim();
                        String deadlineFrom = userInput.substring(keywordIndex + 5, keyword2Index).trim();
                        String deadlineTo = userInput.substring(keyword2Index + 3).trim();

                        taskList.add(new Event(isDoneDefault, description, deadlineFrom, deadlineTo));
                    } else {
                        // Missing keyword error
                        throw new HALException("Missing keyword /from and /to!");

                    }
                }

                // [index - 1] so that we increment index, but still return string from previously added task
                Task taskObject = taskList.get(taskList.size() - 1);
//                String fileString = taskObject.getFileString();
//                writeToFile(fileString, taskList);
                return taskObject.toString();
            }

        } catch (HALException e) {
            System.out.println(e.getMessage());
            returnOutput = "error";
        }

        return returnOutput;
    }

    public String removeTask(int taskIndex) {
        Task taskObject = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        return taskObject.toString();
    }

    public String markAsDone(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.markAsDone();
        return t.toString();
    }

    public String markAsUndone(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.markAsUndone();
        return t.toString();
    }

    public void listTasks() {
        System.out.println("Tasks:");

        for (int i = 0; i < taskList.size(); i++) {

            Task t = taskList.get(i);
            System.out.printf("%d. %s\n", i + 1, t.toString());
        }
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    // add previous list form storage into newly initialised tasklist
    public void initialisePrevTaskList(ArrayList<Task> prevTaskList) {
        System.out.println(prevTaskList);
        this.taskList = prevTaskList;
    }

//    public void writeToFile(String fileString) {
//        try {
//            java.io.FileWriter fw = new java.io.FileWriter(FILE_PATH);
//            for (Task task : todoList) {
//                fw.write(task.getFileString() + "\n");
//            }
//            fw.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void readFromFile() throws FileNotFoundException {
//        Scanner sc = new Scanner(file);
//
//        try {
//
//            while (sc.hasNextLine()) {
////                System.out.println(sc.nextLine());
//                String[] parts = sc.nextLine().split(" \\| ");
////                System.out.println(parts);
//
//                String taskType = parts[0];
//                boolean isDone = (Integer.parseInt(parts[1]) == 1);
//                String description = parts[2];
//
//                switch (taskType) {
//                    case "T":
//                        todoList.add(new Todo(isDone, description));
//                        break;
//                    case "D":
//                        todoList.add(new Deadline(isDoneDefault, description, parts[3]));
//                        break;
//                    case "E":
//                        todoList.add(new Event(isDoneDefault, description, parts[3], parts[4]));
//                        break;
//                    default:
//                        System.out.println("Unknown task type");
//                }
//
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
