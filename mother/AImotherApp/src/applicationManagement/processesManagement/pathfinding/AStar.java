package applicationManagement.processesManagement.pathfinding;

import java.io.IOException;
import java.util.PriorityQueue;

public class AStar {

    // costs for diagonal and vertical / horizontal moves
    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;

    // Cells of the grid
    private Cell[][] grid;

    /* A priority queue for open cells
    Open Cells : the set of nodes to be evaluated
    Cells with the lowest cost in first */
    private PriorityQueue<Cell> openCells;

    // Closed cells : the set of nodes already evaluated
    private boolean[][] closedCells;

    // start cell
    private int startI, startJ;

    // end cell
    private int endI, endJ;

    public AStar(int width, int height, int startI, int startJ, int endI, int endJ, int[][] blocks) {
        grid = new Cell[width][height];
        closedCells = new boolean[width][height];
        openCells = new PriorityQueue<Cell>((Cell c1, Cell c2) -> {
            return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
        });

        startCell(startI, startJ);
        endCell(endI, endJ);

        // init heuristic and cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
                grid[i][j].solution = false;
            }
        }

        grid[startI][startJ].finalCost = 0;

        // putting the blocks on the grid
        for (int i = 0; i < blocks.length; i++) {
            addBlockOnCell(blocks[i][0], blocks[i][1]);
        }
    }

    public void addBlockOnCell(int i, int j) {
        grid[i][j] = null;
    }

    public void startCell(int i, int j) {
        startI = i;
        startJ = j;
    }

    public void endCell(int i, int j) {
        endI = i;
        endJ = j;
    }

    public void updateCostIfNeeded(Cell current, Cell t, int cost) {
        if (t == null || closedCells[t.i][t.j])
            return;

        int tFinalCost = t.heuristicCost + cost;
        boolean isOpen = openCells.contains(t);

        if (!isOpen || tFinalCost < t.finalCost) {
            t.finalCost = tFinalCost;
            t.parent = current;
        }

        if (!isOpen)
            openCells.add(t);
    }

    public void process() {
        // Add the start location to open list
        openCells.add(grid[startI][startJ]);
        Cell current;

        while(true) {
            current = openCells.poll();

            if (current == null)
                break;

            closedCells[current.i][current.j] = true;

            if(current.equals(grid[endI][endJ]))
                return;

            Cell t;

            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

                if (current.j - 1 >= 0) {
                    t = grid[current.i - 1][current.j - 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i - 1][current.j + 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }

            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);


                if (current.j - 1 >= 0) {
                    t = grid[current.i + 1][current.j - 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i + 1][current.j + 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }
            }
        }
    }

    public void display() {
        System.out.println("Grid:");

        for (int i = 0; i < grid.length; i++) {
            for (int j =0; j < grid[i].length; j++) {
                if (i == startI && j == startJ)
                    System.out.print("SO  "); // Source cell
                else if(i == endI && j == endJ)
                    System.out.print("DE  "); // Destination cell
                else if (grid[i][j] != null)
                    System.out.printf("%-3d ", 0);
                else
                    System.out.print("BL  "); // Block Cell
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayScores() {
        System.out.println("\nScores for cells:");

        for (int i = 0; i < grid.length; i++) {
            for (int j =0; j < grid[i].length; j++) {
                if (grid[i][j] != null)
                    System.out.printf("%-3d ", grid[i][j].finalCost);
                else
                    System.out.print("BL  ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public void displaySolution() {
        if (closedCells[endI][endJ]) {
            // tracking back the path
            System.out.println("Path :");
            Cell current = grid[endI][endJ];
            System.out.println(current);
            grid[current.i][current.j].solution = true;

            while (current.parent != null) {
                System.out.println(" -> " + current.parent);
                grid[current.parent.i][current.parent.j].solution = true;
                current = current.parent;
            }

            System.out.println("\n");

            for (int i = 0; i < grid.length; i++) {
                for (int j =0; j < grid[i].length; j++) {
                    if (i == startI && j == startJ)
                        System.out.print("S0  "); // Source cell
                    else if(i == endI && j == endJ)
                        System.out.print("DE  "); // Destination cell
                    else if (grid[i][j] != null)
                        System.out.printf("%-3s ", grid[i][j].solution ? "X" : "0");
                    else
                        System.out.print("BL  "); // Block Cell
                }
                System.out.println();
            }
            System.out.println();
        }else
            System.out.println("No possible path");
    }

    public static void main(String[] args) {
        AStar aStar = new AStar(5, 5, 0, 0, 3, 2,
                new int[][] {
                        {0,4}, {2,2}, {3,1}, {3,3}, {2,1}, {2,3}
                });

        aStar.display();
        aStar.process(); // Apply A* algorithm
        aStar.displayScores(); // Display Scores on grid
        aStar.displaySolution(); // Display Solution Path
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
