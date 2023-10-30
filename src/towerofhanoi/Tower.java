package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * This class represents the disk objects that we will be using to solve 
 * the tower of hanoi recursion problem. 
 * The Tower class extends the LinkedStack class with the
 * parameter type Disk. Each Tower object represents one of the rods in the
 * Tower of Hanoi puzzle. A tower can have multiple disks, but a fundamental
 * rule applies: smaller disks can only be placed on top of larger ones. Any
 * violation of this rule will result in an exception being thrown. This class
 * ensures that the game's rules are strictly adhered to during the process of
 * pushing a disk onto a tower.
 * 
 * @author cooln
 * @version Oct 9, 2023
 */
public class Tower
    extends LinkedStack<Disk>
{

    private Position position;

    /**
     * Constructor for creating a new instance of a Tower. Every tower needs to
     * have a defined position, which is either LEFT, MIDDLE, or RIGHT. The
     * position of a tower remains constant once it's set and is crucial for
     * solving the Tower of Hanoi puzzle as movements are defined with respect
     * to these positions.
     *
     * @param position
     *            The position where the tower is located.
     */
    public Tower(Position position)
    {
        super();
        this.position = position;
    }


    // ~Public Methods ........................................................
    /**
     * Fetches the position of this particular tower. The position is essential
     * when determining the source, temporary, and destination poles in the
     * Tower of Hanoi puzzle-solving algorithm. This method provides a way to
     * retrieve that position for any given tower.
     * 
     * @return Position The exact position (LEFT, MIDDLE, RIGHT) of the tower.
     */
    public Position position()
    {
        return position;
    }


    /**
     * Overrides the default push method provided by LinkedStack for the Disk
     * type. Before adding a new disk to the tower, this method ensures that the
     * game's rule is maintained: a larger disk can't be placed on top of a
     * smaller one. If the rule is violated or if a null disk is provided,
     * appropriate exceptions will be thrown to maintain the game's integrity.
     *
     * @param disk
     *            The disk to be added to the top of the tower.
     * @throws IllegalArgumentException
     *             when the provided disk is null.
     * @throws IllegalStateException
     *             when attempting to push a larger disk onto a smaller one.
     */
    @Override
    public void push(Disk disk)
    {
        if (disk == null)
        {
            throw new IllegalArgumentException("disk is null");
        }

        if (isEmpty())
        {
            super.push(disk);
        }
        else if (disk.compareTo(peek()) < 0)
        {
            super.push(disk);
        }
        else
        {
            throw new IllegalStateException();
        }

    }
}
