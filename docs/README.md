# TalkingBot User Guide

![Ui.png](Ui.png)

TalkingBot is a friendly chatbot that allows one to easily manage their daily activities! It is very lightweight and has an intuitive command-line interface (CLI).

## Creating a task

### Creating todos

To create a todo, type `todo DESCRIPTION`, where `DESCRIPTION` is a description of the task you have.

For example, if we need to read an article that we wish to be as a todo, we can type in `todo read article`. TalkingBot will then display the following:

```
Alright, I've added this task to your list:
    [T] [ ] read article
You now have 1 tasks in the list.
```

### Creating deadlines

To create a deadline, type `deadline DESCRIPTION /by YYYY-MM-DD HHMM` and press enter, where `DESCRIPTION` is a description of the deadline you have, and `YYYY-MM-DD HHMM` is the time you have to complete the task by. 

For example, if we have to submit an assignment by 23:59, 24 February 2024, we can type in `deadline submit assignment 2024-02-24 2359`. Assuming we have the todo we created above, TalkingBot will then display the following:

```
Alright, I've added this task to your list:
    [D] [ ] submit assignment (by: Feb 24 2024 2359)
You now have 2 tasks in the list.
```

### Creating events

To create an event, type `event DESCRIPTION /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM` and press enter, where `DESCRIPTION` is a description of the event you have, while the first and second `YYYY-MM-DD HHMM` indicate the beginning and end time of the event, respectively.

For example, if we will be meeting our mentor from 18:00, 25 February 2024 to 20:00, 25 February 2024, we can type in `event meet mentor /from 2024-02-25 1800 /to 2024-02-25 2000`. Assuming we have the todo and deadline created above, TalkingBot will return the following:

```
Alright, I've added this task to your list:
    [E] [ ] meet mentor (from: Feb 25 2024 1800 to: Feb 25 2024 2000)
You now have 3 tasks in the list.
```

### Creating do within period tasks

Creating a do within period task is similar to creating an event task. We can create one by typing in `do_within_period DESCRIPTION /between YYYY-MM-DD HHMM /and YYYY-MM-DD HHMM`, where `DESCRIPTION` is a description of the task you have, while the first and second `YYYY-MM-DD HHMM` indicates the times between which you have to do the task in.

For example, if we need to solve technical interview problems from 21:00, 25 February 2024 to 22:00, 25 February 2024, we can type in `do_within_period solve technical interview problems /between 2024-02-25 2100 /and 2024-02-25 2200`. Assuming we have the todo, deadline, and event created above, TalkingBot will return the following:

```
Alright, I've added this task to your list:
    [W] [ ] solve technical interview problems (between Feb 25 2024 2100 and Feb 25 2024 2200)
You now have 4 tasks in the list.
```

## Listing tasks

To list all available tasks, type `list` and press enter. This will return a list of tasks that have been entered in previously. 

For example, if you only have a todo `read article` that is not done, TalkingBot will output the following when running `list`:

```
1. [T] [ ] read article
```

## Marking/unmarking tasks as done
### Marking tasks as done

To mark tasks as done, type `mark INDEX` and press enter, where `INDEX` is the index of the task you wish to mark as done.

For example, if we have two todos `read article` and `read essay`, running `list` can return the following:

```
1. [T] [ ] read article
2. [T] [ ] read essay
```

To mark `read article` as done, we can type in `mark 1`, as `1` is the index of the task. TalkingBot will then return the following:

```
Nice! I've marked this task as done:
[T] [X] read article
```

Running `list` again, we see the following:

```
1. [T] [X] read article
2. [T] [ ] read essay
```

### Unmarking tasks as done

To mark tasks as undone, type `unmark INDEX` and press enter, where `INDEX` is the index of the task you wish to mark as done. 

Using the same tasks as above and assuming we have run `mark 1`, we can run `unmark 1` to unmark `read article`. TalkingBot will then return the following:

```
Alright, I've marked this task as undone.
[T] [ ] read article
```

Running `list` again, we see the following:

```
1. [T] [ ] read article
2. [T] [ ] read essay
```

## Deleting a task

To delete a task, type `delete INDEX` and press enter, where `INDEX` is the index of the task you wish to delete.

For example, if we have two todos `read article` and `read essay`, running `list` can return the following:

```
1. [T] [ ] read article
2. [T] [ ] read essay
```

To delete `read article`, we can type in `delete 1`, as `1` is the index of the task. Running `list` again, we see the following:

```
1. [T] [ ] read essay
```

## Finding a task

TalkingBot is able to help you find the tasks you need. To do this, type `find KEYWORD` and press enter, where `KEYWORD` is a substring that exists in the descriptions of the tasks you want.

For example, if we have the same todos as in the [Deleting a task](#deleting-a-task) section above, we can run `find article` and TalkingBot will return the following:

```
Here are the tasks with descriptions matching your query:
1. [T] [ ] read article
```

## Saving tasks

TalkingBot is smart enough to save tasks! To do this, simply type `save` and press enter. This will save the tasks to the `./data/taskList.txt` file (relative to the location of the `talkingbot.jar` file).

After saving, TalkingBot will respond with the following message:

```
Saving tasks to file: ./data/taskList.txt
Save done!
```

## Exiting 

Once you're done with managing your daily activities using TalkingBot and wish to exit the application, type `bye` into the command line. This will also save the tasks to the same file as in the [Saving Tasks](#saving-tasks) section above.

Before exiting, TalkingBot will respond with the following message:

```
Bye. Hope to see you again soon!
```
## Invalid commands

Any other command (other than the aforementioned ones) entered into TalkingBot will be processed as an invalid command. 

Given an invalid command, TalkingBot will respond with the following message:

```
ERROR! Unknown command detected.
```