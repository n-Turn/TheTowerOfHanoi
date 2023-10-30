package towerofhanoi;

import java.util.Observable;

// -------------------------------------------------------------------------
/**
 * Framework of
 * this class serves as the primary models for the Tower of Hanoi puzzle.
 * Utilizing this class, one can efficiently tackle the problem. Class
 * structures such as this are vital for visualization. Keeps in mind the three
 * main towers: left, right, and middle. As a part of a larger system, this
 * class offers versatility. Regarding its capabilities, the HanoiSolver is
 * instrumental. Inheriting from the Observable class, this module enables
 * observer notifications. Not only is it effective, but also intuitive in its
 * design. Java developers will find the architecture familiar and easy to
 * navigate. Observable pattern implementation ensures that any changes (i.e.,
 * moves made) are communicated. Yields both iterative and recursive solutions
 * for the Tower of Hanoi problem.
 * 
 * @author cooln
 * @version Oct 9, 2023
 */
@SuppressWarnings("deprecation")
public class HanoiSolver
    extends Observable
{

    private Tower left;
    private Tower right;
    private Tower middle;
    private int numDisks;

    /**
     * Constructs a new HanoiSolver object with a specified number of disks.
     * Initializes three towers to represent the Tower of Hanoi puzzle.
     * 
     * @param numDisks
     *            is the total number of disks per puzzle
     */
    public HanoiSolver(int numDisks)
    {
        this.numDisks = numDisks;

        left = new Tower(Position.LEFT);
        right = new Tower(Position.RIGHT);
        middle = new Tower(Position.MIDDLE);
    }


    /**
     * Retrieves the total number of disks in the puzzle.
     * 
     * @return The number of disks.
     */
    public int disks()
    {
        return numDisks;
    }


    /**
     * Depending on the provided position, retrieves either the left, middle, or
     * right tower.
     * 
     * @param pos
     *            The position of the desired tower (LEFT, MIDDLE, or RIGHT).
     * @return The corresponding Tower based on the given position.
     */
    public Tower getTower(Position pos)
    {
        switch (pos)
        {
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case MIDDLE:
                return middle;
            default:
                return middle;
        }
    }


    /**
     * Provides a string representation of the HanoiSolver. Concatenates the
     * string representation of left, middle, and right towers.
     * 
     * @return A combined string of the top values of each tower.
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append(left);
        str.append(middle);
        str.append(right);

        return str.toString();
    }


    /**
     * Executes a move from the source tower to the destination tower. Pops a
     * Disk from the source tower and pushes it onto the destination tower.
     * Observers are then notified of the change.
     * 
     * @param source
     *            The tower from which the disk is to be moved.
     * @param destination
     *            The tower to which the disk is to be moved.
     */
    private void move(Tower source, Tower destination)
    {
        destination.push(source.pop());
        this.setChanged();
        this.notifyObservers(destination.position());
    }


    /**
     * Recursively solves the Tower of Hanoi puzzle. If there is only one disk,
     * it Directly moves the disk from the start pole to the end pole. Uses the
     * temporary pole for intermediate moves. For more than one disk, it breaks
     * down the problem recursively.
     * 
     * @param currentDisks
     *            The number of disks to be moved in the current recursive call.
     * @param startPole
     *            The pole from which disks will be moved.
     * @param tempPole
     *            The pole to be used for temporary storage.
     * @param endPole
     *            The pole to which disks will be moved.
     */
    private void solveTowers(
        int currentDisks,
        Tower startPole,
        Tower tempPole,
        Tower endPole)
    {
        if (currentDisks == 1)
        {
            this.move(startPole, endPole);
        }
        else
        {
            this.solveTowers(currentDisks - 1, startPole, endPole, tempPole);
            this.move(startPole, endPole);
            this.solveTowers(currentDisks - 1, tempPole, startPole, endPole);
        }
    }


    /**
     * Initiates the solution for the Tower of Hanoi problem. It provides the
     * recursive solveTowers() method with the initial parameters for the
     * complete puzzle, setting the left tower as the start pole, the middle
     * tower as the temporary pole, and the right tower as the end pole.
     */
    public void solve()
    {
        solveTowers(numDisks, left, middle, right);
    }
}
