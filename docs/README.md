

# Tartar User Guide

Welcome to Tartar, your personal task manager assistant! With Task Bot, you can easily manage your tasks using simple commands. Below is a guide to help you get started:

## Commands

- **todo <name>**: Add a todo task.
- **deadline <name> //by <date>**: Add a deadline task.
- **event <name> //from <start date> //to <end date>**: Add an event task.
- **list**: List all tasks.
- **delete <index>**: Delete a task by its index.
- **mark <index>**: Mark a task as completed.
- **unmark <index>**: Unmark a completed task.
- **find <word>**: Find a specific word in the list of tasks.
- **sort <option>**: Sort tasks by various options.


## Adding Tasks

One of the most exciting features of this bot is its ability to add tasks effortlessly. Let's explore the three different types of tasks it can handle:

### Todos

Todos are simple tasks without any specific deadlines. You can add a todo task by typing `todo <task>`, where `<task>` is the name of the task.

For example: `todo Do Laundry`
After adding this task, you'll receive confirmation:
``
Got it. I've added this task:
[T][ ] Do Laundry
Now you have 1 task in the list.

``


### Deadlines

Deadlines are tasks that need to be completed by a certain date or time. You can add a deadline task using the format `deadline <task> //by <date/time>`. However, we recommend using the date/time format `M/d/yyyy HHmm` for a better experience.

For example: `deadline Do Homework //by 6`
Although the task is added successfully, a recommendation message will prompt you to use the preferred date/time format. If you choose to add it in the recommended format, you'll see:
``
Added the task, but recommend using the date/time format `M/d/yyyy HHmm` for better experience.
``
This is based on the American standard formatting system for dates, for example: `12/03/2022 1800`
If you type in `deadline Do Homework //by 12/03/2022 1800` it will say:
``
Got it. I've added this task:
[D][ ] Do Homework (Dec 06 2022, 6PM)
``
without the recommendation message.


### Events

Events are tasks that occur within a specific timeframe. You can add an event task using the format `event <name> //from <start time> //to <end time>`. Similar to deadlines, it's recommended to use the date/time format `M/d/yyyy HHmm`.
 
For example: `event Meeting //from 1 //to 3`
After adding the event task, you'll receive confirmation:
``
Got it. I've added this task:
[E][ ] Meeting (from 1 to 3)
``

### List

This command doesn't take any parameters and just returns the current list of items which might look something like this:
``
Here are the tasks in your list:
1. [T][ ] Do Laundry
2. [T][ ] Meet Friend
3. [D][ ] Do Math Assignment  (by 6)
4. [D][ ] Do Homework  (Dec 03 2022, 6PM)
5. [E][ ] Meeting with boss  (from 1 to 3)
``
or may return a message stating that the list is empty if so.

### Mark, Unmark, and Delete

There are three commands that take in an input *index* which are the mark, unmark, and delete commands:
If you already have a list that looks like this:
``
Here are the tasks in your list:
1. [T][ ] Do Laundry
2. [T][ ] Meet Friend
3. [D][ ] Do Math Assignment  (by 6)
4. [D][ ] Do Homework  (Dec 03 2022, 6PM)
5. [E][ ] Meeting with boss  (from 1 to 3)
``

You can *mark* a task as done by typing `mark <index>` where index is one-indexed meaning it starts from 1 to the length of the list.
If you type in `mark 1` in this example, it will mark the first task (i.e. Do Laundry) as done and you should receive confirmation `Nice! I've marked this task as done:`.
If you type in the command `list` again you should see:
``
Here are the tasks in your list:
1. [T]\[X] Do Laundry
2. [T]\[ ] Meet Friend
3. [D]\[ ] Do Math Assignment  (by 6)
4. [D]\[ ] Do Homework  (Dec 03 2022, 6PM)
5. [E]\[ ] Meeting with boss  (from 1 to 3)
``
The X signifies that a task is marked as done.

You can also *unmark* a task as undone if you committed an error and it will revert back.

You can also *delete* a task at a specific index like `delete 5` will delete the fifth task in the list.


### Find

Find will find a sentence based on a specific keyword. It is formatted like `find <keyword>`.
Using the list:
``
Here are the tasks in your list:
1. [T]\[X] Do Laundry
2. [T]\[ ] Meet Friend
3. [D]\[ ] Do Math Assignment  (by 6)
4. [D]\[ ] Do Homework  (Dec 03 2022, 6PM)
5. [E]\[ ] Meeting with boss  (from 1 to 3)
``
If you type in `find Do`, it will find all the task names that contain "Do" so it should return:

``
1. [T]\[X] Do Laundry
2. [D]\[ ] Do Math Assignment  (by 6)
3. [D]\[ ] Do Homework  (Dec 03 2022, 6PM)
``

# Sort 

This is a special feature of Tartar that will sort all the tasks based on several options.
It is formatted `sort <type>`. The types are:

- alphabetical
- start_date
- end_date
- task_type

It will sort the tasks in the entire list in ascending order based on those key features.





