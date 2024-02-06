package seedu.mamta;

public class Parser {

    public static String transformText(String userOutput) {

        String[] splitOutput = userOutput.split(" ");

        String word = "";
        int taskNum = -1;
        String output = "";

        //in the case user wants to mark/unmark , export this into a helper later
        switch (splitOutput[0]) {
        case "mark":
        case "unmark":
        case "delete":
             word = splitOutput[0];
             taskNum = Integer.parseInt(splitOutput[1]);
             System.out.println(output);
             return Mamta.echo("default", word, taskNum, "", "");
        case "todo": {
            StringBuilder task = new StringBuilder();
            for (int i = 1; i < splitOutput.length; i++) {
                task.append(splitOutput[i]).append(" ");
            }
            System.out.println(Mamta.echo(splitOutput[0], task.toString(), taskNum, "", ""));
            return Mamta.echo(splitOutput[0], task.toString(), taskNum, "", "");
        }
        case "deadline":
        case "event": {
            StringBuilder task = new StringBuilder();
            StringBuilder deadline = new StringBuilder();
            StringBuilder endTime = new StringBuilder();
            boolean reachedBy = false;
            boolean reachedTo = false;
            //string splitting logic for parsing tasks
            for (int i = 1; i < splitOutput.length; i++) {
                if ((!reachedBy && !reachedTo) && (!splitOutput[i].equals("/by") && !splitOutput[i].equals("/from"))) {
                    if (splitOutput[i+1].equals("/by") || splitOutput[i+1].equals("/from")) {
                        task.append(splitOutput[i]);
                    } else {
                        task.append(splitOutput[i]).append(" ");
                    }
                } else if (!reachedTo && (splitOutput[i].equals("/by") || splitOutput[i].equals("/from"))) {
                    reachedBy = true;
                } else if (reachedBy && (!splitOutput[i].equals("/to"))) {
                    if (i+1 == splitOutput.length ||  (i+1 != splitOutput.length && splitOutput[i+1].equals("/to"))) {
                        deadline.append(splitOutput[i]);
                    } else {
                        deadline.append(splitOutput[i]).append(" ");
                    }
                } else if (splitOutput[i].equals("/to")) {
                    reachedTo = true;
                    reachedBy = false;
                } else if (reachedTo) {
                    endTime.append(i + 1 == splitOutput.length ? splitOutput[i] : splitOutput[i] + " ");
                }
            }
            System.out.println(Mamta.echo(splitOutput[0], task.toString(), taskNum, deadline.toString(), endTime.toString()));
            return Mamta.echo(splitOutput[0], task.toString(), taskNum, deadline.toString(), endTime.toString());
        }
        default:
            System.out.println(Mamta.echo("default", userOutput, taskNum, "", ""));
            return Mamta.echo("default", userOutput, taskNum, "", "");
        }
    }

}
