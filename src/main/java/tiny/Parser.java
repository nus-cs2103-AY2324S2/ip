package tiny;

import tiny.exceptions.TinyException;
import tiny.extensions.Client;
import tiny.extensions.Contact;
import tiny.extensions.Expense;
import tiny.extensions.LoanGiven;
import tiny.extensions.LoanTaken;
import tiny.extensions.Merchandise;
import tiny.extensions.Note;
import tiny.extensions.Place;
import tiny.extensions.Trivia;
import tiny.lists.ClientList;
import tiny.lists.ContactList;
import tiny.lists.ControlList;
import tiny.lists.ExpenseList;
import tiny.lists.LoanGivenList;
import tiny.lists.LoanTakenList;
import tiny.lists.MerchandiseList;
import tiny.lists.NoteList;
import tiny.lists.PlaceList;
import tiny.lists.TaskList;
import tiny.lists.TriviaList;
import tiny.tasks.Deadline;
import tiny.tasks.Event;
import tiny.tasks.Todo;

/**
 * Represents the parser for user commands.
 */
public class Parser {
    protected boolean isExit = false;
    protected String input;
    protected ControlList controlList;
    protected TaskList taskList;
    protected ClientList clientList;
    protected ContactList contactList;
    protected ExpenseList expenseList;
    protected LoanGivenList loanGivenList;
    protected LoanTakenList loanTakenList;
    protected MerchandiseList merchandiseList;
    protected NoteList noteList;
    protected PlaceList placeList;
    protected TriviaList triviaList;

    /**
     * Parses the user input into commands and return the appropriate response.
     *
     * @param input       The string input from the user
     * @param controlList The instance of the controlList which contains all the
     *                    lists.
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid when parsing the respective
     *                       commands.
     */
    public String parse(String input, ControlList controlList) throws TinyException {
        this.input = input;
        this.controlList = controlList;
        this.taskList = controlList.getTaskList();
        this.clientList = controlList.getClientList();
        this.contactList = controlList.getContactList();
        this.expenseList = controlList.getExpenseList();
        this.loanGivenList = controlList.getLoanGivenList();
        this.loanTakenList = controlList.getLoanTakenList();
        this.merchandiseList = controlList.getMerchandiseList();
        this.noteList = controlList.getNoteList();
        this.placeList = controlList.getPlaceList();
        this.triviaList = controlList.getTriviaList();

        try {
            if (isValidCommand("list")) {
                return listCommand();
            } else if (isValidCommand("mark")) {
                return mark();
            } else if (isValidCommand("unmark")) {
                return unmark();
            } else if (isValidCommand("todo")) {
                return todo();
            } else if (isValidCommand("deadline")) {
                return deadline();
            } else if (isValidCommand("event")) {
                return event();
            } else if (isValidCommand("client")) {
                return client();
            } else if (isValidCommand("contact")) {
                return contact();
            } else if (isValidCommand("expense")) {
                return expense();
            } else if (isValidCommand("loan given")) {
                return loanGiven();
            } else if (isValidCommand("loan taken")) {
                return loanTaken();
            } else if (isValidCommand("merchandise")) {
                return merchandise();
            } else if (isValidCommand("note")) {
                return note();
            } else if (isValidCommand("place")) {
                return place();
            } else if (isValidCommand("trivia")) {
                return trivia();
            } else if (isValidCommand("delete")) {
                return deleteCommand();
            } else if (isValidCommand("find")) {
                return find();
            } else if (input.equals("bye")) {
                isExit = true;
                return bye();
            } else {
                return cmdUnknown();
            }
        } catch (TinyException e) {
            throw e;
        }
    }

    private String listCommand() throws TinyException {
        if (isValidCommand("list task")) {
            return listTask();
        } else if (isValidCommand("list client")) {
            return listClient();
        } else if (isValidCommand("list contact")) {
            return listContact();
        } else if (isValidCommand("list expense")) {
            return listExpense();
        } else if (isValidCommand("list loan given")) {
            return listLoanGiven();
        } else if (isValidCommand("list loan taken")) {
            return listLoanTaken();
        } else if (isValidCommand("list merchandise")) {
            return listMerchandise();
        } else if (isValidCommand("list note")) {
            return listNote();
        } else if (isValidCommand("list place")) {
            return listPlace();
        } else if (isValidCommand("list trivia")) {
            return listTrivia();
        } else {
            throw new TinyException("Please input a valid list command!");
        }
    }

    private String deleteCommand() throws TinyException {
        if (isValidCommand("delete client")) {
            return deleteClient();
        } else if (isValidCommand("delete contact")) {
            return deleteContact();
        } else if (isValidCommand("delete expense")) {
            return deleteExpense();
        } else if (isValidCommand("delete loan given")) {
            return deleteLoanGiven();
        } else if (isValidCommand("delete loan taken")) {
            return deleteLoanTaken();
        } else if (isValidCommand("delete merchandise")) {
            return deleteMerchandise();
        } else if (isValidCommand("delete note")) {
            return deleteNote();
        } else if (isValidCommand("delete place")) {
            return deletePlace();
        } else if (isValidCommand("delete task")) {
            return deleteTask();
        } else if (isValidCommand("delete trivia")) {
            return deleteTrivia();
        } else {
            throw new TinyException("Please input a valid delete command!");
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Handles the "mark" command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String mark() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (isValidCode(s, 2, "mark")) {
                return "OOPS! You need to type \"mark <number>\" to change the status to done!";
            }
            int ind = Integer.parseInt(s[1]);
            taskList.get(ind - 1).taskDone();
            assert taskList.get(ind - 1).taskIsDone() == true;
            return "Nice! I've marked this task as done:\n      " + taskList.get(ind - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"mark <number>\" to change the status to done!";
        } catch (NullPointerException e) {
            return "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the “unmark" command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String unmark() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (isValidCode(s, 2, "unmark")) {
                return "OOPS! You need to type \"unmark <number>\" to change the status not done!";
            }
            int ind = Integer.parseInt(s[1]);
            taskList.get(ind - 1).taskUndone();
            assert taskList.get(ind - 1).taskIsDone() == false;
            return "OK, I've marked this task as not done yet:\n      " + taskList.get(ind - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"unmark <number>\" to change the status to not done!";
        } catch (NullPointerException e) {
            return "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the “todo” command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String todo() throws TinyException {
        try {
            String name = "";
            String[] st = input.split("");
            String[] s = input.split(" ");
            if (!s[0].equals("todo")) {
                return "OOPS! You need to type \"todo <description>\" to create a new todo!";
            } else {
                for (int i = 5; i < st.length; i++) {
                    name += st[i];
                }
                if (name == "") {
                    return "OOPS! The description of a todo cannot be empty.";
                } else {
                    taskList.add(new Todo(name));
                    return addMessage("task", taskList.get(taskList.size() - 1).toString(), taskList.size());
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"todo <description>\" to create a new todo!";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the deadline command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deadline() throws TinyException {
        try {
            String name = "";
            String[] st = input.split("/by ");
            String[] s = input.split(" ");
            if (!s[0].equals("deadline")) {
                return "OOPS! You need to type \"deadline <description> /by <yyyy-mm-dd> <time>\" "
                        + "to create a new deadline!";
            } else {
                name = st[0].substring(9);
                taskList.add(new Deadline(name.trim(), st[1]));
                return addMessage("task", taskList.get(taskList.size() - 1).toString(), taskList.size());
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"deadline <description> /by <yyyy-mm-dd> <time>\" "
                    + "to create a new deadline!";
        } catch (TinyException e) {
            throw e;
        }
    }

    /**
     * Handles the “event” command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String event() throws TinyException {
        try {
            String name = "";
            String[] s = input.split(" ");
            if (!s[0].equals("event")) {
                return "OOPS! You need to type \"event <description> /from <start date> /to <end date>\" "
                        + "to create a new deadline!";
            } else {
                String[] from = input.split("/from ");
                String[] fromTo = from[1].split("/to ");
                name = from[0].substring(5);
                taskList.add(new Event(name.trim(), fromTo[0].trim(), fromTo[1].trim()));
                return addMessage("task", taskList.get(taskList.size() - 1).toString(), taskList.size());
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "OOPS! You need to type \"event <description> /from <start date> /to <end date>\" "
                    + "to create a new deadline!";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the delete task command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteTask() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete <number>\" to delete the task!";
            }
            int ind = Integer.parseInt(s[1]);
            String output = deleteMessage("task", taskList.get(ind - 1).toString(), taskList.size() - 1);
            taskList.delete(ind - 1);
            return output;
        } catch (NumberFormatException e) {
            return "OOPS! You need to type \"delete <number>\" to delete the task!";
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "OOPS! Please type a valid number! Type \"list\" to check the lists of tasks.";
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the find command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String find() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (s.length != 2 || !s[0].equals("find")) {
                return "OOPS! You need to type \"find <keyword>\" to find the task(s)!";
            }
            return taskList.find(s[1]);
        } catch (Exception e) {
            throw new TinyException("Something went wrong...");
        }
    }

    /**
     * Handles the client command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String client() throws TinyException {
        try {
            String[] s = input.split(" ");
            String[] numSplit = input.split("/num ");
            clientList.add(new Client(s[1], numSplit[1].trim()));
            return addMessage("contact", clientList.get(clientList.size() - 1).toString(),
                    clientList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"client <name> /num "
                    + "<contact number>\" to add the client!");
        }
    }

    /**
     * Handles the contact command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String contact() throws TinyException {
        try {
            String[] s = input.split(" ");
            String[] numSplit = input.split("/num ");
            contactList.add(new Contact(s[1], numSplit[1].trim()));
            return addMessage("contact", contactList.get(contactList.size() - 1).toString(),
                    contactList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"contact <name> /num "
                    + "<contact number>\" to add the contact!");
        }
    }

    /**
     * Handles the expense command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String expense() throws TinyException {
        try {
            String[] s = input.split(" ");
            String[] onSplit = input.split("/on ");
            String[] forSplit = onSplit[0].split("/for ");
            expenseList.add(new Expense(s[1], Double.parseDouble(forSplit[1].trim()), onSplit[1].trim()));
            return addMessage("expense", expenseList.get(expenseList.size() - 1).toString(),
                    expenseList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"expense <description> /for "
                    + "<amount> /on <yyyy-MM-dd>\" to add the expense!");
        }
    }

    /**
     * Handles the loan given command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String loanGiven() throws TinyException {
        try {
            String[] onSplit = input.split("/on ");
            String[] forSplit = onSplit[0].split("/for ");
            String[] toSplit = forSplit[0].split("/to ");

            loanGivenList.add(new LoanGiven(toSplit[1].trim(), Double.parseDouble(forSplit[1].trim()),
                    onSplit[1].trim()));
            return addMessage("given loan", loanGivenList.get(loanGivenList.size() - 1).toString(),
                    loanGivenList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"loan given /to <name> /for "
                    + "<amount> /on <yyyy-MM-dd>\" to add the loan given!");
        }
    }

    /**
     * Handles the loan taken command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String loanTaken() throws TinyException {
        try {
            String[] onSplit = input.split("/on ");
            String[] forSplit = onSplit[0].split("/for ");
            String[] fromSplit = forSplit[0].split("/from ");
            loanTakenList.add(new LoanTaken(fromSplit[1].trim(), Double.parseDouble(forSplit[1].trim()),
                    onSplit[1].trim()));
            return addMessage("taken loan", loanTakenList.get(loanTakenList.size() - 1).toString(),
                    loanTakenList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"loan taken /from <name> /for "
                    + "<amount> /on <yyyy-MM-dd>\" to add the loan taken!");
        }
    }

    /**
     * Handles the merchandise command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String merchandise() throws TinyException {
        try {
            String[] s = input.split(" ");
            String[] priceSplit = input.split("/price ");
            String[] quantitySplit = priceSplit[0].split("/quantity ");
            merchandiseList.add(new Merchandise(s[1], Integer.parseInt(quantitySplit[1].trim()),
                    Double.parseDouble(priceSplit[1].trim())));
            return addMessage("merchandise", merchandiseList.get(merchandiseList.size() - 1).toString(),
                    merchandiseList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"merchandise <name> /quantity <quantity> /price <price>"
                    + "\" to add the merchandise!");
        }
    }

    /**
     * Handles the note command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String note() throws TinyException {
        try {
            String[] s = input.split(" ");
            String[] bodySplit = input.split("/body ");
            noteList.add(new Note(s[1], bodySplit[1].trim()));
            return addMessage("note", noteList.get(noteList.size() - 1).toString(), noteList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"note <title> /body <body>\" to "
                    + "add the note!");
        }
    }

    /**
     * Handles the place command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String place() throws TinyException {
        try {
            String[] s = input.split(" ");
            String[] atSplit = input.split("/at ");
            placeList.add(new Place(s[1], atSplit[1].trim()));
            return addMessage("place", placeList.get(placeList.size() - 1).toString(), placeList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"place <name> /at <address>\" to "
                    + "add the address!");
        }
    }

    /**
     * Handles the trivia command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String trivia() throws TinyException {
        try {
            String[] s = input.split(" ");
            triviaList.add(new Trivia(s[1]));
            return addMessage("trivia", triviaList.get(triviaList.size() - 1).toString(), triviaList.size());
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"trivia <content>\" to add a trivia!");
        }
    }

    /**
     * Handles the delete client command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteClient() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete contact <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("client", clientList.get(ind - 1).toString(), clientList.size() - 1);
            clientList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete contact <number>\" to delete!");
        }
    }

    /**
     * Handles the delete contact command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteContact() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete contact <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("contact", contactList.get(ind - 1).toString(), contactList.size() - 1);
            contactList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete contact <number>\" to delete!");
        }
    }

    /**
     * Handles the delete expense command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteExpense() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete expense <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("expense", expenseList.get(ind - 1).toString(), expenseList.size() - 1);
            expenseList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete expense <number>\" to delete!");
        }
    }

    /**
     * Handles the delete loan given command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteLoanGiven() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete loan given <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[3]);
            String output = deleteMessage("given loan", loanGivenList.get(ind - 1).toString(),
                    loanGivenList.size() - 1);
            loanGivenList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete loan given <number>\" to delete!");
        }
    }

    /**
     * Handles the delete loan taken command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteLoanTaken() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete loan taken <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[3]);
            String output = deleteMessage("taken loan", loanTakenList.get(ind - 1).toString(),
                    loanTakenList.size() - 1);
            loanTakenList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete loan taken <number>\" to delete!");
        }
    }

    /**
     * Handles the delete merchandise command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteMerchandise() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete merchandise <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("merchandise", merchandiseList.get(ind - 1).toString(),
                    merchandiseList.size() - 1);
            merchandiseList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete merchandise <number>\" to delete!");
        }
    }

    /**
     * Handles the delete note command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteNote() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete note <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("note", noteList.get(ind - 1).toString(), noteList.size() - 1);
            noteList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete note <number>\" to delete!");
        }
    }

    /**
     * Handles the delete place command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deletePlace() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete place <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("place", placeList.get(ind - 1).toString(), placeList.size() - 1);
            placeList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete place <number>\" to delete!");
        }
    }

    /**
     * Handles the delete trivia command.
     *
     * @return The message to be displayed to the user.
     * @throws TinyException When input is invalid.
     */
    private String deleteTrivia() throws TinyException {
        try {
            String[] s = input.split(" ");
            if (!s[0].equals("delete")) {
                return "OOPS! You need to type \"delete trivia <number>\" to delete!";
            }
            int ind = Integer.parseInt(s[2]);
            String output = deleteMessage("trivia", triviaList.get(ind - 1).toString(), triviaList.size() - 1);
            triviaList.delete(ind - 1);
            return output;
        } catch (Exception e) {
            throw new TinyException("OOPS! You need to type \"delete trivia <number>\" to delete!");
        }
    }

    /**
     * Handles the “bye” command.
     *
     * @return The message to be displayed to the user.
     */
    private String bye() {
        return "Bye. Hope to see you again soon!";
    }

    private String listTask() {
        return taskList.list();
    }

    private String listClient() {
        return clientList.list();
    }

    private String listContact() {
        return contactList.list();
    }

    private String listExpense() {
        return expenseList.list();
    }

    private String listLoanGiven() {
        return loanGivenList.list();
    }

    private String listLoanTaken() {
        return loanTakenList.list();
    }

    private String listMerchandise() {
        return merchandiseList.list();
    }

    private String listNote() {
        return noteList.list();
    }

    private String listPlace() {
        return placeList.list();
    }

    private String listTrivia() {
        return triviaList.list();
    }

    /**
     * Handles the case where no command is recognised.
     *
     * @return The message to be displayed to the user.
     */
    private String cmdUnknown() {
        return "I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Checks if the user input contains a command.
     *
     * @return True if the command from the user is the same as expected, otherwise
     *         False.
     */
    private boolean isValidCommand(String name) {
        return input.length() >= name.length() && input.substring(0, name.length()).equals(name);
    }

    private boolean isValidCode(String[] commandInput, int length, String command) {
        return commandInput.length != length || !commandInput[0].equals(command);
    }

    private String addMessage(String type, String description, int size) {
        return "Got it. I've added this " + type + ":\n" + "      " + description
                + "\nNow you have " + size + " " + type + "(s) in the list.";
    }

    private String deleteMessage(String type, String description, int size) {
        return "Noted. I've removed this " + type + ":"
                + "\n      " + description
                + "\nNow you have " + size + " " + type + "(s) in the list.";
    }
}
