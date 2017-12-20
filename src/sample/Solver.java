package sample;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Node target = null;
    public class Node implements Comparable<Node> {
        private final Board board;
        private final int moves;
        private final Node prev;
        private final int priority;

        public Node(Board newBoard, Node previous) {
            this.board = newBoard;
            this.prev = previous;
            if (previous == null)
                this.moves = 0;
            else this.moves = previous.moves + 1;
            this.priority = newBoard.manhattan() + this.moves;
        }
        @Override
        public int compareTo(Node that) {
            if (this.priority > that.priority) return 1;
            if (this.priority < that.priority) return -1;
            return 0;
        }
    }

    public Solver(Board initial) {
        if (initial.isGoal()) {
            target = new Node (initial, null);
        }
        else {
            target = startSolving(initial, initial.twin());
        }
    }

    private Node startSolving(Board initial, Board twin) {
        Node min;
        Node minTwin;
        MinPQ<Node> Tree = new MinPQ<Node>();
        MinPQ<Node> twinTree = new MinPQ<Node>();
        Tree.insert(new Node(initial, null));
        twinTree.insert(new Node(twin, null));
        while (true) {
            min = Tree.delMin();
            minTwin = twinTree.delMin();
            if (min.board.isGoal()) {
                break;
            }
            if (minTwin.board.isGoal()) {
                min = null;
                break;
            }
            saveNeighbors(min, Tree);
            saveNeighbors(minTwin, twinTree);
        }
        return min;
    }

    private void saveNeighbors(Node min, MinPQ<Node> currTree) {
        for (Board n : min.board.neighbors()) {
            if (min.prev == null) {
                currTree.insert(new Node(n, min));
            } else {
                if (!n.equals(min.prev.board))
                    currTree.insert(new Node(n, min));
            }
        }
    }

    public boolean isSolvable(){
        return target != null;
    }

    public int moves(){
        if (target == null) return -1;
        return target.moves;
    }

    public Iterable<Board> solution() {
        if (!this.isSolvable())
            return null;

        Stack<Board> solutionStack = new Stack<Board>();
        for (Node curr = target; curr != null; curr = curr.prev) {
            solutionStack.push(curr.board);
        }
        return solutionStack;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In();
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println("Manhatten = " + board.manhattan());
                StdOut.println("Hamming = " + board.hamming());
                StdOut.println(board);
            }
        }
    }
}
