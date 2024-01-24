public class Greetings extends Commands {
    public Greetings() {
        dialogues.put("greeting1" , "Hi there! I'm " + Duke.CHATBOTNAME + " :)\n" + indentation + "What brings you to me today?\n");
        dialogues.put("greeting2", "Hello! I'm " + Duke.CHATBOTNAME + " :)\n" + indentation + "It's fantastic to see you here. What can I do for you?\n");
        dialogues.put("greeting3", "Hi, and a very warm welcome to you! I'm " + Duke.CHATBOTNAME + " :)\n" + indentation + "I'm looking forward to our chat. What would you like to talk about or get help with today?\n");
    }

//    public String getKey(int num) {
//
//    }
}
