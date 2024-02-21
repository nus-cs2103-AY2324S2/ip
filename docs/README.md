# GrumbleBug

![GrumbleBug interaction](Ui.png "GrumbleBug Interaction")

GrumbleBug is an unfriendly chatbot whose purpose is to (begrudgingly) help you keep track of tasks. You can add and delete various tasks, mark them as you complete them, and get a nice view of all your tasks at will. Just beware not to enter any poorly formatted commands, or GrumbleBug might get impatient and give you a scolding!

## Getting started

1. Ensure you have the correct versions of Java and JavaFX installed — click [here](https://nus-cs2103-ay2324s2.github.io/website) to check the required versions
2. Download the jar file — you should know where to find it... otherwise you aren't authorised to see!
3. Navigate to the root `ip` folder via your terminal and enter `./gradlew run`
4. Refer to the below commands and get chatting with GrumbleBug!

## Commands

### Adding tasks

GrumbleBug accepts three task types: **To-do**, **Deadline** and **Event**.

##

**To-do** is the simplest type with only the task name as a string. The string can contain spaces, don't worry about that.

`todo Dry My Laundry`

GrumbleBug will add a To-do to your task list, with the given name (e.g. `Dry My Laundry`).

##

**Deadline** is similar, but with an extra argument for the deadline date. This is strictly in the yyyy-MM-dd format, and the task name must also not contain spaces — otherwise, GrumbleBug will get mad!

`deadline Homework 2024-02-29`

GrumbleBug will add a Deadline to your task list, with the given name and deadline date.

##

**Event** is the most complicated, with not one but _two_ dates, for start and end of event! Same idea as deadline.

`event TaylorSwiftInSingapore 2024-03-02 2024-03-09`

GrumbleBug will add an Event to your task list, with the given name, start and end dates. He is also really sad that he missed Taylor Swift's ticket sales, so he'll be missing that event.

## Viewing list

We can **list** all our tasks stored so far, and check out what we've completed and what there's left to do.

`list`

GrumbleBug spits out your entire list of tasks, including information on their dates if any, and whether each one has been marked as completed. This list will be numbered from 1, 2, ... which helps with other commands later. You'll see.

## Deleting tasks

To **delete** a task, you can tell GrumbleBug which task you don't want to see anymore, identifying it by its number in the list. See, the numbering under `list` is indeed useful!

`delete 2`

GrumbleBug will delete the corresponding task, if your input was proper. If you only have 3 tasks and try to delete item #4, GrumbleBug will again get mad!

## Marking and unmarking

**Mark** a task to label it as completed. Again, by its number in the list!

`mark 1`

**Unmark** a task to label it as incomplete. Hmm, perhaps sometimes you realise you marked something wrongly?

`unmark 1`

In both cases, GrumbleBug will tell you if it was successful. Or, you can just `list` again to check!

## Searching tasks

**Find** tasks relevant to certain keywords by searching these keywords.

`find teenage mutant ninja turtles`

You can feed it more than one query term — I entered _four_ — and GrumbleBug will cleverly return all of the tasks whose descriptions contain _any_ of these terms.

## Saving list

**Save** the current list you have into your local storage so GrumbleBug will remember your tasks the next time you wake him from his slumber to pester him.

`save`

GrumbleBug will tell you if it's been successfully saved. Don't worry about the nitty gritty — GrumbleBug creates a new file in your local device if there isn't one that matches what he needs!

## Leaving

You can say **bye** to GrumbleBug and leave him alone, which he would be delighted to hear.

`bye`

GrumbleBug will terminate without saying bye back. Because he doesn't want to talk to you in the first place!

# Summary of commands

| Comamand                       | Description                                    |
| ------------------------------ | ---------------------------------------------- |
| todo [name]                    | Add this To-do to your tasks                   |
| deadline [name] [date]         | Add this Deadline to your tasks                |
| event [name] [date 1] [date 2] | Add this Event to your tasks                   |
| list                           | Show list of tasks                             |
| delete [number]                | Delete task at the specified number            |
| mark [number]                  | Set task at the specified number as complete   |
| unmark [number]                | Set task at the specified number as incomplete |
| find [keywords]                | Search tasks that contain any of the keywords  |
| save                           | Save current tasks locally                     |
| bye                            | Leave GrumbleBug alone                         |
