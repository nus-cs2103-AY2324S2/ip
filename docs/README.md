# **SnowBoy**
>"I'm just a little snowman, I have a carrot for a nose. I'm happy when it's frosty, I love it when it snows!" - The Little Snowman

_SnowBoy_ is meant to be your one-stop assistant who can help you with:
- Adding to-do tasks
- Display your list of tasks
- Mark tasks as done

As an example, here's how you might use it:
1. Add a task called read book
2. Mark it as done when completed
3. Add another task called read novel
4. View your list

To try it out for yourself, download the jar file from [here](https://github.com/soons1/ip/releases/tag/v0.3) and type in your CLI:
```
java -jar snowboy.jar
```

Here's a detailed guide of commands you can use:

**list**
- Lists all tasks in your list
- To use, type `list`

**todo**
- Adds a to-do task to your list
- To use, type `todo <task>`, where `<task>` is the task you want to add

**deadline**
- Adds a deadline task to your list that has a due date
- To use, type `deadline <task> /by <date>`, where `<task>` is the task you want to add and `<date>` is the due date
- The date should be in the format `yyyy-mm-dd`

**event**
- Adds an event task to your list that has a from and end date
- To use, type `event <task> /at <from> to <to>`, where `<task>` is the task you want to add, `<from>` is the start date and `<to>` is the end date
- The dates should be in the format `yyyy-mm-dd`

**mark**
- Marks a task as done
- To use, type `mark <index>`, where `<index>` is the index of the task you want to mark as done
- You can find the index of the task by using the `list` command

**unmark**
- Marks a task as not done
- To use, type `unmark <index>`, where `<index>` is the index of the task you want to mark as not done
- You can find the index of the task by using the `list` command

**delete**
- Deletes a task from your list
- To use, type `delete <index>`, where `<index>` is the index of the task you want to delete
- You can find the index of the task by using the `list` command

**find**
- Finds tasks that contain a specific keyword
- To use, type `find <keyword>`, where `<keyword>` is the keyword or phrase you want to search for

**help**
- Displays a list of commands
- To use, type `help`

**bye**
- Exits the program
- To use, type `bye`

If you require any further assistance or spot any bugs, feel free to reach out to me here!

Thank you for visiting⛄ 