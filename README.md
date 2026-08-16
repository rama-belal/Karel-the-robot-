# Karel the Robot — Map Division

A Karel program that divides a rectangular world into the maximum number of equal-size chambers (up to 4), using beeper walls as dividers.

## Overview

Given a rectangular Karel world of unknown size, the program first measures its dimensions by walking the perimeter, then decides how to divide it into chambers based on its shape.
The goal is always to reach the largest number of equal chambers possible — 4 first, falling back to fewer only when the world is too small to support that.

## Approach

The division strategy depends entirely on the shape of the world:

- **Square worlds (width = height):** divided diagonally into 4 triangular quadrants, regardless of size.
- **Thin worlds (width = 1 or height = 1):** divided using a single line of beepers along the row/column. The exact spacing — whether the line fits cleanly, needs an edge beeper, or needs double-thick dividers — is determined by the remainder of the dimension divided by 4.
- **Worlds with one side equal to 2:** divided using a zigzag pattern for smaller sizes, and the same line-based logic as thin worlds once the other dimension exceeds 6.
- **General worlds (both sides greater than 2):** three candidate layouts are compared — vertical lines, horizontal lines, and a cross (two perpendicular lines meeting near the center) — and the layout requiring the fewest total beepers is selected.
-  Cost is calculated directly from each dimension's remainder when divided by 4, without an explicit runtime counter.

## Status

- [x] Square (NxN) — diagonal division
- [x] 1xN / Nx1 — single-line division
- [x] 2xN / Nx2 — zigzag and line-based division
- [x] General WxH — cross vs. line comparison, unified into a single `drawVerticaOrHorizontalLines` helper for both directions

## Known issues

- The `%4==0` line pattern currently places its divider beeper at the end of each segment rather than the beginning, in some cases.
- When the number of rows/columns to divide is odd, the line-drawing loop may execute one extra pass past the last real row/column. This no longer crashes (guarded with `frontIsClear()`), but beeper placement correctness for these specific cases hasn't been fully re-verified visually.

## Usage

Run against any rectangular Karel world; the program auto-detects width and height via wall-following (`getWidth()`/`getHeight()`) before selecting a division strategy.
