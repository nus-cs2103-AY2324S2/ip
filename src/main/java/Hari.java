import java.util.Scanner;

// Class for the chatbot itself
class handlerbot
{
    // Function that handles the greeting message
    public void messagegreeting()
    {
        System.out.println("____________________________________________________________");
        System.out.println(" Hey! I'm Hari!");
        System.out.println(" How may I be of service today?");
        System.out.println("____________________________________________________________");
    }

    // Function that handles and echoes user input
    public void userechoedinput(String readerinput)
    {
        System.out.println("____________________________________________________________"); //The top border is left outside the loop, so that it does not disappear when user inputs bye

        //Special case - To avoid printing bye and the exit message
        if (!readerinput.equalsIgnoreCase("bye")) {
            System.out.println(" " + readerinput);
            System.out.println("____________________________________________________________");
        }
    }

    // Function that handles the exit message
    public void messagefarewell()
    {
        System.out.println("Au revoir! Till we meet again!");
        System.out.println("____________________________________________________________");
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

        //Level 1: Read and echo user input
        String readerinput; // To store user input

        do
        {
            readerinput = inputread.nextLine(); //Read and store user input inside readerinput variable
            hari.userechoedinput(readerinput); //Call the input reader function (userechoedinput) and pass the user input value
        }

        while (!readerinput.equalsIgnoreCase("bye")); // If "bye" is written as an input, the chatbot exits with the farewell message

        hari.messagefarewell(); // Call the messagefarewell function to display farewell message and exit
    }
}
