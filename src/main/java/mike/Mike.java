package mike;

import java.util.List;

import mike.command.Command;

/**
 * Mike class.
 */
public class Mike {
    private static final String FILE_PATH = "./data/mike.txt";
    private static final String GREETING =
            " Hello! I'm mike WAZOWSKI.\n"
                    + " What can I do for you?";
    private static final String LOGO = "                   .\n"
            + "          &#  #*&/,,(@%*. (#\n"
            + "           %#((,.      #%,*,\n"
            + "         %%%(/,.. *(     /,.**\n"
            + "      .%%%%#/**,%(@@&@    /.,,/\n"
            + "      %%%%#(((/*(#*(%&    ..,,,*(\n"
            + "    *#%%%##(/,#(/**,,...,...,,,**/\n"
            + "   .#%%%%%##(((//#(//*......,,,**//\n"
            + "   &#%%%%%%###(///*,,..,,,,,,,***/#*\n"
            + "   &#%%%%%%#####@@@@@@(,,**,****/(#*\n"
            + "  *&%#%%&&&%%%@@@@@@@@@@&**/////(.&/*\n"
            + "  #&, #%&&&&&&%(((/*****((((((##.  #(\n"
            + "  ##    #&&&&&&&&&&%%%%%%###%%(    (*\n"
            + "  .#/     /%%%%%%%%%%%%%%%%#       //\n"
            + "   #(     /##            *#,       (/\n"
            + "   %*     /#(             #,       //\n"
            + "   %(/.    %(            .%&      //*/\n"
            + "   %/,&,   &(            #%/      (#//,\n"
            + "   /(*     ##*           #%       &*#%\n"
            + "     @/@*  (#*.          #(.      /%%\n"
            + "      (/(/##(...........##(//&#(&/.\n"
            + "     (@%(&@@,#,..........,*@@&&@&*,\n";
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor.
     */
    public Mike() {
        // Referenced from https://nus-cs2103-ay2324s2.github.io/website/schedule/week3/project.html#a-moreoop
        this.storage = new Storage(FILE_PATH);
        this.taskList = storage.load();
    }
    /**
     * Saves the tasklist into file.
     */
    public void save() {
        storage.writeToFile(taskList);
    }

    public MikeResponse getResponse(String userInput) {
        try {
            List<Token> tokens = new CommandScanner(userInput).scanTokens();
            Command command = new CommandParser(tokens).parse();
            String response = command.execute(taskList, storage);
            return new MikeResponse(response, command.isExit());
        } catch (MikeException e) {
            return new MikeResponse(e.getMessage());
        }
    }

    /**
     * Gets the logo.
     * @return String representation of the logo.
     */
    public String getLogo() {
        return LOGO;
    }

    /**
     * Gets the greeting message.
     * @return a string with the greeting message.
     */
    public String getGreeting() {
        return GREETING;
    }
}
