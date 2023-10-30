package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * This class
 * represents the disk objects that we will be using to solve the tower of hanoi
 * recursion problem. The Position enum serves as a representation of the
 * various positions or poles in the classic Tower of Hanoi problem. In the
 * Tower of Hanoi puzzle, there are typically three poles: a source pole, an
 * auxiliary pole, and a destination pole. These poles are represented here as
 * LEFT, MIDDLE, and RIGHT, respectively. Furthermore, this enum also provides a
 * DEFAULT position, which can be used in cases where a specific position hasn't
 * been assigned or for initialization purposes. Using this enum makes the code
 * more readable and maintainable, as it gives meaningful names to the different
 * positions rather than relying on arbitrary numbers or strings.
 * 
 * @author cooln
 * @version Oct 8, 2023
 */
public enum Position
{
    /**
     * Represents the left most pole
     */
    LEFT,
    /**
     * Represents the middle pole
     */
    MIDDLE,
    /**
     * Represents the right most pole
     */
    RIGHT,
    /**
     * Represents the default pole position
     */
    DEFAULT;
}
