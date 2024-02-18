# Luke User Guide

![UI image](./Ui.png)

Have you ever wanted a friendly chatbot to keep track of your tasks and
give you emotional support? Great news! Luke can do all of that for you, 
and more! 

`/// Please give me a try... ///`

# Adding Tasks

Tasks are the core of this manager. You can add 3 types of tasks: 

1. todo
2. deadline
3. event

which you can see the details of below.

Don't worry about whitespace issues. Luke can handle it all!

## todo

Adds a todo task. 

Example: `todo homework`

Sample output:

```
I helped you add task '[T][ ] homework'. But do it yourself next time! Hmmph!
```

As you can see, each todo task description has a [T] prefix.

## deadline

Adds a deadline task. Deadlines have a "by" date. 
Please follow the date format in the example!

Example: `deadline assignment / by 27-08-2001`

Sample Output:

```
I helped you add task '[D][ ] assignment (by: 2001-08-27)'. But do it yourself next time! Hmmph!
```

As you can see, each deadline task description has a [D] prefix.

## event

Adds an event task. Events have a "from" and "to" date. 
Please follow the date format in the example!

Example: `event my dog's multi-day party / from 27-08-2024 / to 28-08-2024`

Sample Output:

```
I helped you add task '[E][ ] my dog's multi-day party (from: 2024-08-27 to: 2024-08-28)'. But do it yourself next time! Hmmph!
```

As you can see, each event task description has an [E] prefix.


# Task Commands

Once you have a couple of tasks in the manager, Luke can help you
to perform several useful functions with them! They are:

1. list
2. mark
3. delete
4. remind
5. find

which you can see the details of below. Manage your time efficiently!

## list

Lists down all tasks regardless of whether they are completed or not. 

Example: `list`

Sample Output:

```
1. [T][ ] homework
2. [T][ ] wholesome date
3. [T][ ] go out with friends
```

## mark

Marks a task as done. You will see that there is an [X]
in the task description. Use the (1-based) index to mark a task as done.

Example: `mark 1`

Sample Output:

```
Good work, I guess.
1. [T][X] homework
```

You can confirm that the task is indeed done by checking the list again:

```
1. [T][X] homework
2. [T][ ] wholesome date
3. [T][ ] go out with friends
```

## delete

Deletes a task. Use the (1-based) index to delete a task. 
You can delete a task regardless of whether it is complete.

Example: `delete 3`

Sample Output:

```
Fine! If that's what you really want...
[Removed [T][ ] go out with friends]
```

Checking the list again: 
```
1. [T][X] homework
2. [T][ ] wholesome date
```

## remind

Reminds you of which tasks you have yet to complete. 


Example: `remind`

Given the tasklist

```
1. [T][X] homework
2. [T][ ] wholesome date
```

you should see the output

```
Get to it!
1. [T][ ] wholesome date
```

## find

Finds task(s) given a substring of the task name.
No more searching through all your tasks manually!

Example: `find date`

Sample Output:

```
Here... here you go!
2. [T][ ] wholesome date
```

# Quitting

How do you quit Luke? No, it's not by using `:wq`. 

Just say `bye` to Luke. Luke wants to see you again, but is too 
shy to tell you so.

Note that if you don't quit properly, Luke might not save your history. 

# History

Luke remembers everything you told him! Even your deepest,
darkest secrets. If you have remaining tasks when you say `bye`,
Luke remembers them.

Just use `list` the next time you start Luke up and you should
see that the history has been saved. Neat! 

## Have fun trying Luke out!