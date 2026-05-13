# Week 6 Project Feedback by PM/TA

**Dedicated PM/TA**: Vandan Agrawal (vandanagrawal@u.northwestern.edu)

## How to Read This Feedback
> [!NOTE]
> **Purpose.** This feedback focuses on your team's progress and collaboration. It is meant as guidance, not judgement.

> [!IMPORTANT]
> **Scope.** For the BVA and TDD items, the PM/TA evaluates only the `main` branch. Ongoing work in feature branches will be evaluated after it is merged. If you'd like early feedback on work in progress, please reach out to your PM/TA directly.

> [!TIP]
> **Mistakes are expected :).** As the instructor mentioned in class, early mistakes are part of the learning process. As long as your team addresses the issues after you get the feedback, your grade will not suffer from them.

## Checklist
Status:
- ✅: All done/Good job!
- ⚠️: Attention needed
- ❌: Significant issue found
- ➖: No basis to evaluate

### Past Feedback
| # | Item                                                                                                 | Status | Reviewer Notes | Source Instructions or Resources |
|---|------------------------------------------------------------------------------------------------------|:------:|----------------|----------------------------------|
| 0 | The team has closed and merged the past Feedback PR(s), indicating that they have read the feedback. | ⚠️     | Week 5 feedback is still an **open** PR on GitHub (PR #16, “Week 5 Feedback”), so it does not yet show as merged into `main`. Please merge after the team has read/discussed it. |                                  |

### Software Process Quality
| # | Item                                                                                                                                         | Status | Reviewer Notes | Source Instructions or Resources                                                                              |
|---|----------------------------------------------------------------------------------------------------------------------------------------------|:------:|----------------|---------------------------------------------------------------------------------------------------------------|
| 1 | Checkstyle: Checkstyle is set up or there is a to-do item planned in the Project Management board for this task. (needed for Letter Grade B) | ❌     | No Checkstyle Gradle plugin, task, or `checkstyle*.xml` (or similar) appears on `main` in `build.gradle.kts` or the repo tree. I cannot verify a board-only to-do from the repository/GitHub API alone—please add tooling to the build or a clearly linked board item for the reviewer. | Week 6 Monday lecture (Lecture 11); the build script and config file in the repository for Lab: Code Coverage |
| 2 | SpotBugs: SpotBugs is set up or there is a to-do item planned in the Project Management board for this task. (needed for Letter Grade B)     | ❌     | No SpotBugs Gradle plugin or SpotBugs filter/exclude config appears on `main`. Same note as Checkstyle regarding a verifiable board to-do. | Week 6 Monday lecture (Lecture 11); the build script and config file in the repository for Lab: Code Coverage |

### Planning & Progress Evaluation
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 4 | The team documents every week’s planning and progress evaluation professionally. (needed for Letter Grade B)                                                 | ⚠️        | **Improved:** `docs/weekly-reports/report.md` now includes Week 5 and Week 6 sections with concrete tasks and PR links. **Still to fix:** remove or replace the trailing “Week X” template block so the document reads as a finished weekly log. | Week 4 Wednesday Lecture (Lecture 08), Project grading rubrics                    |

### Progress & Collaboration
| # | Item                                                                                                                                                                                   |  Status   | Reviewer Notes      | Source Instructions or Resources                 |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|--------------------------------------------------|
| 5 | Overall development progress (recall the recommended order is: Game Setup Phase -> One turn of the game -> Multiple turns -> One win condition -> Other win conditions (if applicable) | ⚠️        | **Good:** Several parallel feature PRs are in flight (e.g., Pawn, Rook, Player, Knight, Bishop, Location—see open PRs). **Gap for this rubric:** on `main`, the merged codebase is still early (domain work centered on `Piece`/`Pawn`; `ui/Main.java` effectively empty). Merge completed work regularly so `main` reflects game-setup / turn milestones. | Canvas assignment Project: Week 4 and 5 Guidance |

### The following items are not checked by the reviewer this week as they were checked in the previous weeks
If your team wants the reviewer to check any of these for any reasons, please contact them or the instructor via either email or tag/mention them in the feedback PR.

| #   | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1   | GitHub repository branch protection rules are fully set up so that people cannot push into main without a pull request approval. (needed for Letter Grade C) | ➖        | Not re-checked this week (previously evaluated). | Canvas assignment Project: Setup, Project grading rubrics                         |
| 2   | Continuous Integration (CI) is fully set up from the beginning. (needed for Letter Grade B)                                                                  | ➖        | Not re-checked this week (previously evaluated). | Canvas assignment Project: Setup, Project grading rubrics                         |
| 3   | The team uses the project management board steadily and frequently, and the description of each task is detailed. (needed for Letter Grade B)                | ➖        | Not re-checked this week (previously evaluated). | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.1 | Every functionality-related work item on the management board includes a user story, and optionally one or more use cases.                                   | ➖        | Not re-checked this week (previously evaluated). | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.2 | The design is documented somewhere, either in the work item description, or in a separate design document.                                                   | ➖        | Not re-checked this week (previously evaluated). | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.3 | Task assignments are documented clearly in the management board.                                                                                             | ➖        | Not re-checked this week (previously evaluated). | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 4   | Each active feature branch has an open draft PR against main.                                                                                                | ➖        | Not re-checked this week; note many current open PRs are **not** marked `draft`. | Week 4 Wednesday Lecture (Lecture 08)                                             |
| 5   | The team has a “definition of done” (BVA) fully documented for the part of the system that is done. (needed for Letter Grade D)                              | ➖        | Not re-checked this week; `docs/bva/Piece.md` remains the primary artifact on `main`. | Project grading rubrics                                                           |
| 6   | GitHub commit history demonstrates evidence of a TDD/BDD workflow for all the non-UI code. (needed for Letter Grade C)                                       | ➖        | Not re-checked this week; feature branches/PRs suggest ongoing test work. | Project grading rubrics                                                           |
| 7   | Collaboration: Quality of discussion in PR reviews and work item comments on the board.                                                                      | ➖        | Not re-checked this week; there is now some PR review activity (e.g., reviews on PR #14), but narrative depth is still uneven. |                                                                                   |

## Additional Comments
- **Week 6 focus (static analysis):** Add Checkstyle and SpotBugs to Gradle (and commit config files), then wire them into CI so `main` stays clean as features land.
- **Process:** Close the loop on TA feedback by merging the Week 5 feedback PR once the team has read it (currently open as PR #16).
- **Integration:** You have healthy parallel work on multiple pieces/classes; prioritize merging vertical slices to `main` so progress is visible for milestone grading (BVA/TDD are evaluated on `main`).
- **Evidence sources used:** Local tree + GitHub `main` tree / `build.gradle.kts` on `main`, open/closed PR list, and PR review metadata (public API). Project board “to-do only” paths for Checkstyle/SpotBugs were not independently verifiable from the repo alone.
