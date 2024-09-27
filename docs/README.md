# Gandalf User Guide

![Ui.png](Ui.png)


From JRR Tolkien's fantasy world of Lord Of The Rings, Gandalf is here!
Gandalf is one of the most favoured wizard in all of Middle-Earth. He plays a major
role in the many events that occured in the Third Age especially those that led towards
the ultimate destiny of the Ring. Now, he will help you organise your tasks! Enjoy!

## Overarching format

This section gives the main idea of the main formatting of this chatbot. 
To ensure that as features are explained, it would be easier to follow.
The chatbot interprets input by looking for **_consecutive_** whitespaces.
However, this is to only categorise the information, a string within the same
category can have the natural single whitespace.

Example input: `todo read a book`\
To read the input above correctly, it has to be in the format todo`ww`read`w`a`w`book,
where `w` refers to a whitespace.\
Since "todo" and "read a book" are under different categories, they have double-spacing.
The rest of the commands follow the same idea.

## Adding deadlines

Add tasks that has a deadline. Gandalf will read this and update the current list.

Example: `deadline  return a book  2024-04-02 1800`\
The correct input for the example above is: **_deadline`ww`return`w`a`w`book`ww`2024-04-02`w`1800_**

// A description of the expected outcome goes here

```
[D][ ] return a book (Tuesday, April 2, 2024 6:00PM)
```
> Note: The date format is 'yyyy-MM-dd HH:mm'



## Adding events

Add tasks that occurs withing a period. Gandalf will read this and update the current list.

Example input: event`ww`meeting`ww`2024-04-02`w`1400`ww`2024-04-02`w`1700


## Find

Filter's the list and shows the tasks whose description contains the given keyword

Example input: find`ww`book

## Delete

Deletes the task at a specified index, and updates the list.

Example input:  delete`ww`2

## List

Lists out all the tasks on the current list.

Input: list

## Mark / Unmark

Marks or unmarks the task at the specified index (if applicable)

Example input: mark`ww`2

## Expenses

As part of the BCD-extension requirement. Gandalf is able to create expenses and
help track information such as the total cost.

To add an expense: expenses`ww`school`ww`3.5

### Sum

If current list is as follows:\
```
1. [T][ ] read a book
2. expenses school 3.5
3. expenses school 2.5
4. expenses transport 2.5
5. [D][ ] return a book (Tuesday, April 2, 2024 6:00PM)
```

_sum`ww`school_\
Returns
```
Total expenses for school: 6.0 dollars
```
_sum`ww`all_\
Returns
```
Total expenses for all: 8.5 dollars
```

>### Command summary
> todo, event, deadline, expenses, find, delete, sum, mark, unmark, list, bye

>Note: images used are royalty-free, taken from https://pixabay.com/