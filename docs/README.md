# PannaBot User Guide

![Image of UI](ui.png)

PannaBot is the premier chatbot task management system. 
It uses intuitive GUI systems, along with easy
commands to make your life just a _bit_ easier :wink: 

Let us explore some of its <u>features</u>: 

### 1. Adding todos, deadlines and events

PannaBot uses state-of-the-art systems to make the process as seamless as possible!

We just simply use the respective keywords along with their arguments and PannaBot does the rest for you!

The syntax of each command is as follows: 

a) `todo name_of_todo`\
b) `deadline name_of_deadline deadline(in YYYY-MM-DD)`\
c) `Event name_of_event start(in YYYY-MM-DD) end(in YYYY-MM-DD)`

Examples:
* `todo finish homework`
* `deadline CS2103T 2024-02-23`
* `event Olympics 2022-07-26 2022-09-09`

From this: we can use the `list` command to generate the following

```
>> list

Fine... Your list is
----------------------------------------------------------
1. [T] [] Finish homework
2. [D] [] CS2103T (By: Feb 23 2024)
3. [E} [] Olympics (from: Jul 26 2022 to Sep 09 2022)
----------------------------------------------------------

```

## Mark and unmark

Through these functions, you can mark or unmark your various tasks as done, and it will appear to be done to the user.

The syntax of the following commands are: 

`mark label`\
`unmark label`

Here, label is an integer which represents which position of the list the item we want to mark is in

For example, if we consider our previous list: 

```
>> mark 1

----------------------------------------------------------
Congratulations on getting done with the task
[T] [X] Finish homework
Have a cookie! [o]
----------------------------------------------------------
```

```
>> unmark 1

----------------------------------------------------------
Awwwww Man we were doing so well!
I've marked this task as undone:
[T] [] Finish homework
I take back my cookie :[  Me <- [o] <- you
----------------------------------------------------------
```





## Delete

You can delete a task in your list using this command. The syntax is as follows:

`delete label`

label is an integer which represents which position in the list the item is.

For example: 

```
>> delete 1

----------------------------------------------------------
Goodbye task
[T] [] Finish homework
You were a good task while you lasted :(
----------------------------------------------------------
```

## Find

You can search for tasks with matching patterns to the one you provide it with!

The syntax of the `find` command is

`find Substring`

Substring is the text that you want to search for in the tasks!

For instance, 
```
>> Find olym

Woah! We found the following matches.
1. [E] [] Olympics (from: Jul 26 2022 to Sep 09 2022)

Who knew a bot could play matchmaker :]
```

## Update

The update command helps to modify the name of an already-existing task in the list!
It takes in the old task name and replaces it with the new task name!

The syntax is: 

`Update oldTask newTask`

For instance, it can be used like

```
>> update Olympics Superbowl

We have updated the name from
Olympics
to
superbowl
```

Using the `list` command, you can see the changes being reflected.


## **Coming Soon**

- [x] Release v2.0 is coming out soon!
- [x] Upgrades on customization features such as sorting deadlines
- [ ] Unlimited ice cream supply :sad: