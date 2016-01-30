
Good! Uses two explicit states, but then relies on `position+1` in between.
Uses several arrays instead of `StringBuilder`, but works well. I only found
two minor bugs.

Test cases:

  - In `testAlmostClose`, it produces an array out-of-bounds exception, because
    the string ends with a `*` and you're using `position+1` to try and find
    the closing `)`. [-2]

  - `testAlmostReopen` exposes a similar subtle bug. (My solution initially had
    it too!) [–1]

