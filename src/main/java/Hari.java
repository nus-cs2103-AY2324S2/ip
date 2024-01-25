// Class for the chatbot itself
class handlerbot
{
    // Function that handles the greeting message
    public void messagegreeting()
    {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Hari!");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    // Function that handles the exit message
    public void messagefarewell()
    {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

//Main Class
public class Hari
{
    public static void main(String[] args)
    {
        handlerbot hari = new handlerbot(); // Create new "Hari" chatbot (handlerbot object)
        hari.messagegreeting(); // Call the messagegreeting function to greet the user
        hari.messagefarewell(); // Call the messagefarewell function to exit
    }
}
