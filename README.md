# Maltese Project Template

This is a project template for a greenfield Java project. It's named after a cute puppy _Maltese_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()`

## This are the features available in Maltese
### Add a new todo task
> **code**
   ```
      todo <description>
   ```
            + " - todo <description>\n"
            + "Add a new deadline task\n"
            + " - deadline <description> /by <due date>\n"
            + "Add a new event task\n"
            + " - event <description> /from <start> /to <end>\n"
            + "View all tasks\n"
            + " - list\n"
            + "Mark task(s) as done\n"
            + " - mark <indice(s)>\n"
            + "Unmark task(s) as undone\n"
            + " - unmark <indice(s)>\n"
            + "Delete task(s)\n"
            + " - delete <indice(s)>\n"
            + "Find task(s) containing the keyword\n"
            + " - find <keyword>\n"
            + "Exit the application\n"
            + " - bye\n";

## This is my bullet list
* item x
* item y
* item z
## This is my numbered list 
1. item 1
2. item 2
3. item 3
## This is my fenced code block

```javascript
function abc() {
  console.log("The English Alphabet consists of 26 letters: A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z.");
}
```

## This is my task list 
- [ ] drink matcha latte :tea:
- [ ] eat lemon cookies :lemon:
- [ ] bake a bagel :bagel:

## This is my blockquote
> data engineer
> machine learning engineer 
> data architect

## This is my hyperlink 
This is my favourite site [DataCamp](https://www.datacamp.com/)

## This is my inline code
```
function abc() {
  console.log("The English Alphabet consists of 26 letters: A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z.");
}
```
## This is my text formatting
**This is bold text**
_This text is italicized_
~~This was mistaken text~~
This is a <sup>superscript</sup> text
This is a <sub>subscript</sub> text

