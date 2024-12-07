import java.util.Arrays;

// TC: O(2*N + 2*M) (traverse boundaries) + O(4(N*M)) (worst case dfs traversal, each element in matrix plus 4 operations) + O(N*M) (after dfs traversal to find whether any '0' in matrix are unvisited) => O(N*M)
// SC: O(N*M) (vis[][] array) + O(N*M) (recursion stack space) => O(N*M)

public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X' } };
        solve(board);
        System.out.println();
        for (char[] bo : board) {
            System.out.println(Arrays.toString(bo));
        }
        System.out.println();
    }

    public static void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] vis = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(board, vis, i, 0);
            }
            if (board[i][col - 1] == 'O') {
                dfs(board, vis, i, col - 1);
            }
        }

        for (int j = 0; j < col; j++) {
            if (board[0][j] == 'O') {
                dfs(board, vis, 0, j);
            }
            if (board[row - 1][j] == 'O') {
                dfs(board, vis, row - 1, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O' && vis[i][j] == false) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void dfs(char[][] board, boolean[][] vis, int i, int j) {
        if (vis[i][j] == true)
            return;
        vis[i][j] = true;

        int[] rowDir = { -1, 1, 0, 0 };
        int[] colDir = { 0, 0, -1, 1 };

        for (int d = 0; d < 4; d++) {
            int ind1 = i + rowDir[d];
            int ind2 = j + colDir[d];
            if (ind1 >= 0 && ind1 < board.length && ind2 >= 0 && ind2 < board[0].length) {
                if (board[ind1][ind2] == 'O') {
                    dfs(board, vis, ind1, ind2);
                }
            }
        }
    }
}