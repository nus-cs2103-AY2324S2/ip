# Zizhen Bot - Your Personal TodoList Assistant User Guide

![image](https://github.com/YuZizhen/ip/assets/105737133/14ee5aa6-82ae-4561-b431-b6f0d295a7d8)

Welcome to Zizhen Bot, your personal TodoList assistant! Zizhen is here to help you stay organized and on top of your tasks with ease.
**Easy Task Management**: Zizhen allows you to effortlessly create, update, and manage your tasks in a simple and intuitive manner.

## Saved TodoList

The todolist will be saved locally so that users can view the history simply by **list** command.

## Adding Todo

Users can add **todo** to the TodoList by entering "todo Task".
This allows Zizhen to catch the information and add to the TodoList.

For example:

**todo clean the house** will return you "[T][] clean the house"

## Adding Deadlines

Users can add **deadlines** to the TodoList by entering "deadline Task \by Date".
This allows Zizhen to catch the information and add to the TodoList.

For example:

**deadline Finish homework \by tomorrow** will return you "[D][] Finish homework (by:tomorrow)"

## Adding Events

Users can add **event** to the TodoList by entering "event Task \from Date \to Date".
This allows Zizhen to catch the information and add to the TodoList.

For example:

**event Hackerthon \from 2024-02-20 \to 2024-03-10** will return you "[E][] Hackerthon (from: Feb 20 2024 to: Mar 10 2024)"

## Feature Greeting

When the users say "hi" to Zizhen, Zizhen will greet the user!

## Feature Exiting

Users can say **"bye"** to Zizhen to exit the application!

## Feature List

Users can check all the tasks inside the TodoList by entering **"list"** command

## Feature Mark as Done

Users can mark tasks as Done by entering **"mark INDEX_i"**
The above command would mark the task at INDEX i as done.

For example:
**mark 1** will mark the first task as done, so that an additional **X** is added to mark the task.
The output would looks like
"Nice! I've marked this task as done:"
"[T][X] clean the house".

## Feature Unmark tasks

Users can unmark tasks by entering **"unmark INDEX_i"**
The above command would unmark the task at INDEX i.

For example:
**unmark 1** will mark the first task as not done, so the additional **X** will be deleted to unmark the task.
The output would looks like
"OK, I've marked this task as not done yet:"
"[T][] clean the house".

## Feature Delete tasks

Users can delete tasks by entering **"delete INDEX_i"**
The above command would delete the task at INDEX i.

For example:
**delete 1** will delete the first task.
In Zizhen, the deletion will automatically move the task to the **archived list**.

## Feature Find tasks

Users can find tasks by entering **"find String s"**
The above command would filter the tasks that contains the String s.

## Feature Archiving

When the user delete some tasks, Zizhen will archive the tasks instead of simply delete it!
So, it will save the archived tasks for future use if nedded!

Users can retrive the archived list by typing **"archived"** command.
Then all the archived tasks will show up in the page.
