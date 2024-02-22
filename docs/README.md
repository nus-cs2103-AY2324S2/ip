# TalkingBot User Guide

![Ui.png](Ui.png)

TalkingBot is a friendly chatbot that allows one to easily manage their daily activities! It is very lightweight and has an intuitive command-line interface (CLI).

## Creating a task

### Creating todos

### Creating deadlines

### Creating events

## Listing tasks

To list all available tasks, type `list` and press enter. This will return a list of tasks that have been entered in previously. 

For example, if you only have a todo `read article` that is not done, TalkingBot will output the following when running `list`:

```
1. [T] [ ] read article
```

## Marking tasks as done

## Unmarking tasks as done

## Deleting a task
To delete a task, type `delete INDEX` and press enter, where `INDEX` is the index of the task you wish to delete.

For example, if we have two todos `read article` and `read essay`, running `list` can return the following:

```
1. [T] [ ] read article
2. [T] [ ] read essay
```

To delete `read article`, we can type in `delete 1`, as `1` is the index of the article. Running `list` again, we see the following:

```
1. [T] [ ] read essay
```

## Finding a task

TalkingBot is able to help you find the tasks you need. To do this, type `find KEYWORD`, where `KEYWORD` is a substring that exists in the descriptions of the tasks you want.

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