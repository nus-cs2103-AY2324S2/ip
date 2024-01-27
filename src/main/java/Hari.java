import java.util.Scanner;

// Class for the chatbot itself
class handlerbot
{
    //Class Attributes
    private String[] arrtaskings; //To store tasks created by user for easy retrieval and listing
    private int countertaskings; //Counter for assumption that there are no more than 100 tasks

    //Class object
    public handlerbot()
    {
        arrtaskings = new String[100]; //Assumption that there are no more than 100 tasks
        countertaskings = 0; //Counter for number of tasks
    }

    // Function that handles the greeting message
    public void messagegreeting()
    {
        System.out.println("____________________________________________________________");
        System.out.println(" Hey! I'm Hari!");
        System.out.println(" How may I be of service today?");
        System.out.println("____________________________________________________________");
    }

    // Function that handles the exit message
    public void messagefarewell()
    {
        System.out.println("____________________________________________________________");
        System.out.println("Au revoir! Till we meet again!");
        System.out.println("____________________________________________________________");
    }

    // Function that handles and echoes user input (this is maintained as not all inputs are tasks)
    public void userechoedinput(String readerinput)
    {
        if (readerinput.equalsIgnoreCase("list")) //To list out tasks
        {
            System.out.println("____________________________________________________________");
            taskingsdisplay();
            System.out.println("____________________________________________________________");
        }
        else if (readerinput.equalsIgnoreCase("bye")) //To exit the chatbot program
        {
            messagefarewell();
        }
        else //Anything else, is assumed to be a new task to add
        {
            additiontaskings(readerinput);
        }
    }

    // Function to add tasks
    // No modification done to userechoedinput function as not all inputs are tasks
    public void additiontaskings(String taskings)
    {
        System.out.println("____________________________________________________________");
        System.out.println(" What's new to do? : " + taskings); //Display message that tasks has been added
        System.out.println("____________________________________________________________");
        arrtaskings[countertaskings] ="[ ] " + taskings; //Add this task to the array storing all tasks
        countertaskings++; // Increase count of tasks stored
    }

    // Function to display tasks
    // No modification done to userechoedinput function as not all inputs are tasks
    public void taskingsdisplay() {
        if (countertaskings == 0) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your task list is empty. Add tasks by simply typing them in."); //If there are no tasks, a message to guide user
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are your tasks:"); //Display all tasks
            for (int i = 0; i < countertaskings; i++) {
                System.out.println(" " + (i + 1) + ". " + arrtaskings[i]);
            }
            System.out.println("____________________________________________________________");
        }
    }

    // Function to mark task as completed
    public void completionmark(int taskrecorder) {
        if (taskrecorder > 0 && taskrecorder <= countertaskings) // If there are tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println("Another one in the bag! Well done!");
            arrtaskings[taskrecorder - 1] = arrtaskings[taskrecorder - 1].replace("[ ] ", "[X] "); //To replace the empty [ ] with a [X]
            System.out.println(" " + (taskrecorder) + "." + arrtaskings[taskrecorder - 1]);
            System.out.println("____________________________________________________________");
        } else // Error handling: There are no tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println("Hmmm...I dont see to have a task under this number");
            System.out.println("____________________________________________________________");
        }
    }

    // Function to unmark a previously marked as completed task
    public void incompletionmark(int taskrecorder)
    {
        if (taskrecorder > 0 && taskrecorder <= countertaskings) // If there are tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println("Oh dear, better get on it!");
            arrtaskings[taskrecorder - 1] = arrtaskings[taskrecorder - 1].replace("[X] ", "[ ] "); //To replace the [X] with an empty [ ]
            System.out.println(" " + (taskrecorder) + "." + arrtaskings[taskrecorder- 1]);
            System.out.println("____________________________________________________________");
        } else // Error handling: There are no tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println("Hmmm...I dont see to have a task under this number");
            System.out.println("____________________________________________________________");
        }
    }
}

//Main Class
public class Hari
{
    public static void main(String[] args)
    {
        Scanner inputread = new Scanner(System.in); //Scanner object to read and process user input
        handlerbot hari = new handlerbot(); // Create new "Hari" chatbot (handlerbot object)
        hari.messagegreeting(); // Call the messagegreeting function to greet the user

        String readerinput; // To store user input

        while (true) //Modified do-while to a while as I have now streamlined all the code in the main body and reduced number of function calls
        {
            readerinput = inputread.nextLine(); //Read and store user input inside readerinput variable

            if (readerinput.equalsIgnoreCase("bye")) {  // If "bye" is written as an input, the chatbot exits with the farewell message
                break;
            } else if (readerinput.equalsIgnoreCase("list")) { // To list out tasks
                hari.taskingsdisplay();
            } else if (readerinput.startsWith("mark")) { // To mark a task as completed
                // Extract the task number from user input
                int taskindexer = Integer.parseInt(readerinput.substring(5).trim()); // 5 is because of the word "mark"
                hari.completionmark(taskindexer);
            } else if (readerinput.startsWith("unmark")) { // To unmark a previously marked as completed tasks
                // Extract task number from user input
                int taskindexer= Integer.parseInt(readerinput.substring(7).trim()); // 7 is because of the word "unmark"
                hari.incompletionmark(taskindexer);
            } else {
                hari.userechoedinput(readerinput); //Else, it proceeds to call the user input processing function
            }
        }

        hari.messagefarewell(); // Call the messagefarewell function to display farewell message and exit the program
    }
}