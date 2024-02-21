# AI-assistance provided by:

##  github co-pilot

## **Week 2 uses:**

### Level-3:

1) Auto generate an isNumber() method in Bond class to iterate all chars to check that all are digits (0-9)  
```
private static Boolean isNumber(String input) {
    char[] digits = input.toCharArray();
    Boolean isNumber = true;

        for (char c : digits) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
}
```
Thoughts on the method:
- Initially, without AI-assistance, I would have written almost the exact same code except for the loop syntax (I would use the traditional `for (int i = 0; i < digits.length; i++)` instead of the enhanced for loop since I was more used to it).


- Later on in week 6, while wrapping up the IP, I realised this method had a flaw of not being able to account for negative numbers and I subsequently manually edited it to do so without any assistance.

2) The generation of many `System.out.println()` statements with sometimes suitable `String.format()` messages within them that were identical to the guide on our course website.
- This likely means gitHub co-pilot is able to monitor or even read some website information from the tabs we are keeping open in the browser, which is creepy to say the least. However, since week 2's tasks are all very guided in the expected formats and messages for the most part, it was highly beneficial for saving time spent writing many printing statements from scratch particularly because I don't have the fastest of typing speeds. 

### **Level-4:**
Similar to level 3, on the generation of suggested print statements which are sometimes acceptable, but with the addition of the following:
1) As more code logic comes into play, it often suggested that I used `.equalsIgnoreCase("taskName")` instead of `.equals("taskName")` for the checking of user inputs which was in-line with one of the possible routes we could take and seemed more user-friendly and I decided to adopt it. Since then, it would regularly generate this method call whenever I had to verify a user command.


2) It auto-generated this switch-case alternative to the if-else block I had written earlier in handling deadline task creation. I decided to adopt this as well since it was more concise and readable as compared to a similar implementation with each `case` statement replaced by an `if` block.
```
switch (state) {
case 1:
    if (!components[i].equals("/from")) {
        start += components[i] + " ";
    }
    break;

case 2:
    if (!components[i].equals("/to")) {
        end += components[i] + " ";
    }
    break;

default:
    break;
}
```

### Level-5:
1) It helped me populate a commands ArrayList as follows:
```
private static final ArrayList<String> commands = new ArrayList<>() {
        {
            add("todo");
            add("deadline");
            add("event");
            add("list");
            add("mark");
            add("unmark");
            add("bye");
        }
};
```
Which was what I set out to do instead of recording valid command types as an enum which would not come with it functionalities in checking for whether a user command type belongs to the list of valid command types. Not only did it do so functionality-wise, it used such an elegant syntax of populating an ArrayList in the least cluttered manner which I previously did not pick up in other modules where I learnt and used Java extensively.

2) As I started implementing custom exceptions, since I used `enum` to store the list of possible custom exceptions I am handling in the class `BondExceptions.java`, the generated `print` statements in the `Bond` class while I was creating guard clauses for each `if` block formed the basis in which I would continue to add to the list of custom exceptions handled manually most of the time, using `_` to divide semantic meanings in my exception types. 


### Level-6:
Similar to the rest, I only relied on co-pilot to generate suitable print statements or method calls for the method `BondException.raiseException("commandType", "exceptionType")`, which still required significant modifications to suit the character I was envisioning for the task-tracking application.


## Week 3 uses:
### Level-7:
1) It helped me generate these nested for loops for the `if` block handling `EventTask` creation in the method `parseAndAddTask()`:
```
for (int i = 0; i < components.length; i++) {
    if (components[i].equals("from:")) {

        for (int j = i + 1; j < components.length; j++) {
            if (components[j].equals("to:")) {
                break;
            }
            start += components[j] + " ";
        }

    } else if (components[i].equals("to:")) {
        end = components[i + 1];
    }
}
```
* This was initially what I had in mind when I was coding for that block as well since I used nested for loops pretty often in modules requiring java previously (namely CS2030S, CS2040S).


* However, after going through week 5 and 6, I realised that this was poor code quality and proceeded to remove this arrow-headed coding logic from my project by re-implementing in a less nested manner without the help of co-pilot.


2) Again, it helped me generate suitable print statements usually as well as function calls with mostly correct arguments from time to time which really helped me finish the ip tasks faster by a large margin compared to writing multiple function calls to the same method manually without such aid. The help I relied on co-pilot to deliver was again, more on the laborious parts of the code which did not require much code logic.


### Level-8:
1) It helped me generate a date format converter in the form of a method called `changeDateFormat`:
```
private static String changeDateFormat(String month, String day, String year) {
    String newMonth = "";
    String newDay = "";
    
    switch (month) {
        case "Jan":
            newMonth = "01";
            break;
        case "Feb":
            newMonth = "02";
            break;
        case "Mar":
            newMonth = "03";
            break;
        case "Apr":
            newMonth = "04";
            break;
        case "May":
            newMonth = "05";
            break;
        case "Jun":
            newMonth = "06";
            break;
        case "Jul":
            newMonth = "07";
            break;
        case "Aug":
            newMonth = "08";
            break;
        case "Sep":
            newMonth = "09";
            break;
        case "Oct":
            newMonth = "10";
            break;
        case "Nov":
            newMonth = "11";
            break;
        case "Dec":
            newMonth = "12";
            break;
    }
    
    if (day.length() == 1) {
        newDay = "0" + day;
    } else {
        newDay = day;
    }
    
    return year + "-" + newMonth + "-" + newDay;
}
```
* This was basically so that if a user enters a date `2024-02-29`, it would be displayed as `29th February 2024` in a different format. If I did not have such assistance, I would instead have readily jumped into `if-else` blocks like an elephant jumping into a kids' pool. So, I was particularly grateful for this much more aesthetically pleasing format. 

2) Again, I relied on co-pilot's help mainly on generating print, method call statements while I was coding the logic of the program. By now, I have realized that some reliance on AI-assistive technologies is not particularly bad so long as we do not straight up ask it to generate full-blown methods for us and instead let it handle the more menial and detail oriented tasks. By using it wisely, where we learn about more aspects of the IDE, ways in which we can structure the exact same code logic in a more readable and consistent manner, we can improve as a programmer in leaps and bounds while saving ourselves from most of the common unnecessary troubles of typos and missing semicolons.


### A-JUnit:
* The implementation of JUnit tests was surprisingly not the toughest part about this increment for me, partly due to the fact that after I come up with one test method, co-pilot often helps me generate similar exception handling blocks and expected outputs to subsequent test methods after I copy my previous test method and edit to cater to a different type of user input.


* The toughest part I would have believed was when we suddenly had to put everything in packages for the increment **_A-Packages_** which meant that we had to write individual `import` statements for every single class, each of them moreover had a distinct mix of those that were required. In this aspect, I needed to modify previous import statements after committing for **_A-Packages_** previously and could just press tab repeatedly to generate import statements recommended by co-pilot and the first few times would be mostly correct until suddenly a weird import statement pops up, then I know to stop the importing of more statements.


* Sometime after, co-pilot even started helping me auto-import or recommend import statements when I hovered over a variable or method name highlighted in red as I was implementing my code logic. This meant that I did not have to worry about whether I have already imported a particular java class or a program class that existed in another package before starting to edit my code. This made it so that I had little code quality issues regarding the import statements.


### A-MoreOOP:
* This was the toughest increment for me in the entirety of the ip particularly since I already implemented most of my main logic in Bond class with many, many `if-else` blocks. Sad to say, when it comes to making my code more OOP, co-pilot said "OOPS, I can't help you now...", and that much was true apart from occasionally helping me generate method calls and print statements.


### A-JavaDocs:
* This is **THE** element in which I believe **GitHub Co-pilot** helped me the most out of everything so far, when viewed in isolation. In essence, it was clear that GitHub Co-pilot knew much better than me, all the good practices for JavaDocs comments and could directly at the very least, generate mostly correct comments for my classes and methods, although it would sometimes generate completely and utterly wrong comments where I would have to take over for that comment and write out manually, much like a pilot preparing for landing after putting the airplane on autopilot for many hours, which may be where the name came from in the first place!


* I personally did review most if not all the JavaDocs comments previously generated by Co-pilot or those even written by me which are indistinguishable from the ones generated by Co-pilot most of the time because the tables have turned, and now I am the one accommodating and adapting to its syntax and structuring of comments. 


### Level-9:
* Again, Co-pilot mostly helped me generate print statements or method calls with mostly suitable arguments while I was coding the main logic of my program.


* In addition, it once again helped me generate a for loop in the function `findTasks()` in `TaskList` class:
```
public TaskList findTasks(String keyword) {
    ArrayList<Task> foundTasks = new ArrayList<>();

    for (Task task : this.tasks) {
        if (task.name.contains(keyword)) {
            foundTasks.add(task);
        }
    }

    return new TaskList(foundTasks);
}
```
* I would have used the traditional `for (int i = 0; i < tasks.size(); i++)` loop instead of the enhanced for loop if I did not have the help of Co-pilot, but this wouldn't have affected the program much, if even any at all. 


## Week 4 Uses:
### Level-10:
* I started using the `String: StringBuilder` class extensively for print statements and Co-pilot helped me generate `.append()` statements often as I was coding the methods individually and were often nearly fully usable and required some minor modifications to fit into my printing format.


## Week 5 Uses:
### A-Assertions:
1) Again, Co-pilot generated this month-mappings for me :
```
private static final HashMap<String, String> MONTH_FORMATS = new HashMap<>() {
    {
        put("Jan", "01");
        put("Feb", "02");
        put("Mar", "03");
        put("Apr", "04");
        put("May", "05");
        put("Jun", "06");
        put("Jul", "07");
        put("Aug", "08");
        put("Sep", "09");
        put("Oct", "10");
        put("Nov", "11");
        put("Dec", "12");
    }
};
```
* This is similar to the previous case in week 2, for the Level-5 increment, where I have the same want, and it gave me what I wanted in a crisp format.


2) It generated the certain aspects of this change in time format in the method `changeTimeFormat`:
```
public static String changeTimeFormat(String time) {

    String newTime;

    if (Parser.isNumber(time)) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(2));

        if (hours == 0) {
            newTime = "12" + (minutes > 0 ? "." + minutes : "") + "am";
        } else if (hours < 12) {
            newTime = hours + (minutes > 0 ? "." + minutes : "") + "am";
        } else {
            newTime = (hours - 12) + (minutes > 0 ? "." + minutes : "") + "pm";
        }
    } else {
        newTime = time;
    }

    return newTime;
}
```
* Again, Co-pilot provided suggestions in the code, namely the `(minutes > 0 ? "." + minutes : "")` ternary operations in determining whether the time was a full hourly time or included minutes.


3) Lastly, Co-pilot generated reasonably sound `String` messages for the Java `assert` statements I included in the code most of the time, which I kept for now since I have no reason to pay too much heed as to how standardized I want such messages to be.


### A-CodeQuality:
* Co-pilot helped me mostly by just pre-emptively generating method calls I may need while implementing my main code logic.


### A-Streams:
* I decided to use Java `Streams` in handling the parsing of user inputs in particular for deadline and event task creation. The main motivation was to reduce the length of my code although potentially making the method less efficient. 


* Co-pilot helped me initially by generating a rough `Stream` pipeline in processing a `String[]` of the breakdown of user input by whitespace. This included method calls like (`.takeWhile(), .dropWhile(), skip(), reduce(), .get()`) all the essential and most fundamental Java `Stream` methods that I still luckily remember from CS2030S.


* I was then able to make slight modifications to the `Stream` pipelines generated by Co-pilot to then fulfill the intended purpose although creating 3 pipelines essentially tripled the number of traversals I performed across the user input. Thus, it was a trade-off between efficiency and code readability, code length.


### BCD-Extension:
1) Co-pilot helped me generate the helper function `isFoundIn()` in the `UpdateCommand` class:
```
private boolean isFoundIn(String element, String... elements) {
    for (String e : elements) {
        if (element.equals(e)) {
            return true;
        }
    }
    return false;
}
```
* Which is just a low level helper function to check if a tag exists in a variable list of tags.


2) I mostly relied on Co-pilot to sometimes generate useful print, guard clause, exception handling statements and modify them accordingly to fit the program's needs.
