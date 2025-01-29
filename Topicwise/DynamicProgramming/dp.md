# Time Complexity

- For Recursion :
  - Number of Calls on each step = n when TC : n^(x). For example : If we are making 2 calls on each step then time comp is going to be around 2^(x). Multiple Calls = calls^(x)
  - Depth of the recursion tree will determine the value of x ( 2^(x) ). For example : If in a grid we are making 2 calls ( one to left ( same row ) and one to up ( next row ) ), then in order to reach \[0,0\] or \[len,len\] we will have to exhaust all rows and cols which makes the tree's depth to be n+m ( 2^(n+m) ). If we are only going upward or only left then we will only need to exhaust the rows therefore ( 2^(n) )