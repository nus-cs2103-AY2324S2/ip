package Luna;

import java.io.*;
import java.time.LocalDate;

/**
 * Represents the Storage for saving and loading task.
 * Read and Writes the task information into a file to maintain information when the task of the program is deleted.
 */
public class Storage {

    File directory;
    File listFile;

    public Storage(String filename) {
        createFile(filename);
    }

    /**
     * Creates the file if there is no file already existing.
     *
     * @param filename the name of the file to be created.
     */
    public void createFile(String filename) {
        try {
            //https://www.java67.com/2014/02/how-to-create-file-and-directory-in-java.html#:~:text=File%20provides%20methods%20like%20createNewFile,the%20directory%20is%20created%20successfully.
            boolean success = true;
            directory = new File("data");

            if (directory.exists()) {
                System.out.println("Directory already exists ...");

            } else {
                System.out.println("Directory not exists, creating now");

                success = directory.mkdir();
                if (success) {
                    System.out.printf("Successfully created new directory : %s%n", directory.getName());
                } else {
                    System.out.printf("Failed to create new directory: %s%n", directory.getName());
                }
            }
            listFile = new File(directory.getName() + "/" + filename + ".txt");

            if (success) {
                if (listFile.createNewFile()) {
                    System.out.println("File created: " + listFile.getName());
                } else {
                    System.out.println("File already exists. " + listFile.getAbsolutePath());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
        }
    }

    /**
     * Appends a line to the file.
     *
     * @param str to add to the end of the file
     */
    public void appendToFile(String str) {
        //https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter(listFile, true);
            myWriter.write(str);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
        }
    }

    /**
     * Appends an entry as a line to the file.
     *
     * @param entry to be converted to text to the file
     */
    public void appendEntry(ListEntry entry) {
        appendToFile(entry.type + "," + entry.isDone + "," + entry.task + "," + entry.taskStart + "," + entry.taskEnd + "\n");
    }

    /**
     * Appends all the entries from a task list to the file.
     *
     * @param tl the tl to save to the file.
     */
    public void appendList(TaskList tl) {
        for (int i = 0; i < tl.size(); i++) {
            this.appendEntry(tl.get(i));
        }
    }

    /**
     * Clears all data from a file.
     */
    public void clearFile() {
        try {
            FileWriter myWriter = new FileWriter(listFile);
            myWriter.write("");
            myWriter.close();
            System.out.println("Successfully cleared the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Loads all the entries in the file to the task list
     *
     * @param loadList to load all the entries into .
     */
    public void loadList(TaskList loadList) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(listFile));
            while (br.ready()) {
                String str = br.readLine();
                String [] words = str.split(",", 5);

                ListEntry temp;
              switch (ListEntry.taskType.valueOf(words[0])) {
                  case T:
                      temp = new ListEntryTodo(words[2],(words[1].equals("true")));
                      break;
                  case D:
                      temp = new ListEntryDeadline(words[2],(words[1].equals("true")), LocalDate.parse(words[4]));
                      break;
                  case E:
                      temp = new ListEntryEvent(words[2],(words[1].equals("true")),LocalDate.parse(words[3]), LocalDate.parse(words[4]));
                      break;
                  default:
                      temp = null;
                      System.out.println("ERROR: " + str);
                      break;
              }
                loadList.add(temp);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

}
