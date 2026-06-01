# Instructor Code Review Feedback

**Contact**: Dr. Yiji Zhang (yiji.zhang@northwestern.edu)

**Purpose of This Document**:
The instructor will perform code review with respect to software design, error handling, format and style on the main branch every week starting Week 6 using the letter grade A standards.
The following chapters of the textbook are considered: Chapter 1, 2, 3, 4, 5, 6, 7, 9, and 10. The corresponding lectures are considered, too.

Please note that this feedback does not include evaluation of your progress, the proper use of linters, the quality of your test cases, or your compliance of TDD/BDD workflow.  
You can find the weekly feedback from your dedicated PM/TA for that.

## Week 7-8 Code Review
This review is for the code your team developed in Week 7 and 8.
I apologize for this delayed code review (should have been given last Friday but I got really sick...).
As compensation, I will add one extra code review in Week 10 (around Thursday).

A couple of comments:
1. constants.Color class is the same as domain.piece.PieceColor. I suggest deleteing constants.Color and keeping PieceColor.
2. In the `Piece` class, you use variable names like `dist_` in `isValidMove` method. You want to keep var naming policy consistent. Seems like most of the code uses camelCase, then you want to use it everywhere.
3. The comments in `Pawn` class `isValidMove` method are very descriptive (like "is it too far?")! You can push this one step forward --- have one private method with a descriptive method name for every such comment. This way, you comply with "each function should do one thing" even better. And this principle should be applied to all the other classes too, not just`Pawn`.

Keep pushing your progress, team! You are heading to a very good direction!!

## Week 6 Code Review
I have read every line of production code currently in the main branch and I don't find any violations
of the coding standards required by letter grade A! (And also there's not much in the main yet :S.)

Look forward to reviewing more of your domain logic in the next review!

Please approve and merge the PR once the team has read the feedback. Thanks!
