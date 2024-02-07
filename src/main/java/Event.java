public class Event extends Task{

    private final String from_time;
    private final String to_time;


    public static Event from(String s){
        //Expects a string in the format "event <description> /at <from_time> to <to_time>"

        //get rid of the command
        // <description> </from> time </to> time
        String rest = utils.discardFirstWord(s.trim()).trim();

        String[] arr = rest.split(" ");

        int from_occurences = utils.countOccurrences(arr, "/from");

        System.out.println("From occurences: " + from_occurences);

        if (from_occurences == 0 || from_occurences > 1){
            throw new IllegalArgumentException("Invalid format. Follow this format: event <description> /from <from time> /to <to time>. Provide one and only one '/from'.");
        }

        int to_occurences = utils.countOccurrences(arr, "/to");

        if (to_occurences == 0 || to_occurences > 1){
            throw new IllegalArgumentException("Invalid format. Follow this format: event <description> /from <from time> /to <to time>. Provide one and only one '/to'.");
        }

        //they will not be -1 as I have already checked for their occurences
        int from_index = utils.findIndex(arr, "/from");
        int to_index = utils.findIndex(arr, "/to");

        if (from_index > to_index){
            throw new IllegalArgumentException("The 'from time' of an event cannot be after the 'to time'.");
        }

        //description is from 0 to from_index
        String description = "";
        for (int i = 0; i < from_index; i++){
            description += arr[i] + " ";
        }
        description = description.trim();
        if (description.isEmpty()){
            throw new IllegalArgumentException("The description of an event cannot be empty.");
        }

        String from_time = "";
        for (int i = from_index+1; i < to_index; i++){
            from_time += arr[i] + " ";
        }
        from_time = from_time.trim();
        if (from_time.isEmpty()){
            throw new IllegalArgumentException("The 'from_time' of an event cannot be empty.");
        }

        String to_time = "";
        for (int i = to_index+1; i < arr.length; i++){
            to_time += arr[i] + " ";
        }
        to_time = to_time.trim();
        if (to_time.isEmpty()){
            throw new IllegalArgumentException("The 'to_time' of an event cannot be empty.");
        }

        return new Event(description, from_time, to_time);


//        String[] arr = rest.split(" /from ");
//        if (arr.length != 2) {
//            throw new IllegalArgumentException("Invalid format. Follow this format: event <description> /from <from time> /to <to time>. Provide one and only one '/from'.");
//        }
//
//        String description = arr[0].trim();
//        if (description.isEmpty()){
//            throw new IllegalArgumentException("The description of an event cannot be empty.");
//        }
//
//        System.out.println("Description is: " + description);
//
//        // <from time> /to <to time>
//        rest = arr[1].trim();
//        arr = rest.split(" /to ");
//        if (arr.length != 2) {
//            throw new IllegalArgumentException("Invalid format. Follow this format: event <description> /from <from time> /to <to time>. Provide one and only one '/to'.");
//        }
//
//        String from_time = arr[0].trim();
//        String to_time = arr[1].trim();
//
//        if (!description.isEmpty() && !from_time.isEmpty() && !to_time.isEmpty()){
//            return new Event(description, from_time, to_time);
//        }else {
//            throw new IllegalArgumentException("The description, from_time and to_time of an event cannot be empty.");
//        }
    }

    public Event(String description, String from_time, String to_time){
        super(description);
        this.from_time = from_time;
        this.to_time = to_time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from_time + " to: " + to_time + ")";
    }
}
