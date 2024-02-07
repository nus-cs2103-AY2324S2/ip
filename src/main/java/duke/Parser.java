package duke;

public class Parser {
    public static void commands(TaskList taskList, String request, boolean reading, boolean isDone) {
        String[] words = request.split(" ", 0);
        int length = words.length;
        boolean addSuccessful = false;

        switch (words[0]) {
            case "true": {
                commands(taskList, request.substring(5), reading, true);
                break;
            }
            case "false": {
                commands(taskList, request.substring(6), reading, false);
                break;
            }
            case "list": {
                System.out.println(taskList.list());
                break;
            }
            case "find": {
                if (words.length < 2) {
                    System.out.println("No Search Term Detected!");
                } else {
                    StringBuilder searchTerm = new StringBuilder();
                    searchTerm.append(words[1]);
                    for(int i = 2; i < words.length; i++) {
                        searchTerm.append(" ").append(words[i]);
                    }
                    taskList.find(searchTerm.toString());
                }
                break;
            }
            case "mark": {
                int index = Integer.parseInt(words[1]) - 1;
                if(index > taskList.getSize() - 1 || index < 0) {
                    System.out.println("Invalid index!");
                } else {
                    taskList.get(index).mark();
                }
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(words[1]) - 1;
                if(index > taskList.getSize() - 1 || index < 0) {
                    System.out.println("Invalid index!");
                } else {
                    taskList.get(index).unmark();
                }
                break;
            }
            case "delete": {
                int index = Integer.parseInt(words[1]) - 1;
                taskList.delete(index);
                break;
            }
            case "todo": {
                addSuccessful = taskList.addToDo(words, !reading, isDone);
                break;
            }
            case "deadline": {
                addSuccessful = taskList.addDeadline(words, !reading, isDone);
                break;
            }
            case "event": {
                addSuccessful = taskList.addEvent(words, !reading, isDone);
                break;
            }
            default:
                System.out.println("Sorry, I don't know this command :(");
                break;
        }
    }
}
