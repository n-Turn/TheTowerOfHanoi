package towerofhanoi;

import student.TestableRandom;
import cs2.Shape;
import java.awt.Color;

// -------------------------------------------------------------------------
/**
 * 
 * This class
 * represents the disk objects that we will be using to solve the tower of hanoi
 * recursion problem. The Disk class represents the disk objects that are used
 * to solve the tower of hanoi recursion problem. It extends the Shape class and
 * implements the Comparable interface to allow comparisons based on disk width.
 * Each disk is distinguished by its width, and they are visually represented
 * with random colors.
 * 
 * @author cooln
 * @version Oct 5, 2023
 */
public class Disk
    extends Shape
    implements Comparable<Disk>
{

    // ----------------------------------------------------------
    /**
     * Constructs a new Disk object with a specified width. The height of the
     * disk is determined by PuzzleWindow.DISK_HEIGHT. Each disk is initialized
     * with a random background color.
     * 
     * @param width
     *            is the width of the disk.
     */
    public Disk(int width)
    {
        super(0, 0, width, PuzzleWindow.DISK_HEIGHT);
        TestableRandom randomGenerator = new TestableRandom();

        int red = randomGenerator.nextInt(256);
        int green = randomGenerator.nextInt(256);
        int blue = randomGenerator.nextInt(256);

        Color color = new Color(red, green, blue);
        setBackgroundColor(color);
    }


    /**
     * Compares the width of this disk to another disk. This method is used to
     * determine the relative size of the two disks.
     * 
     * @param otherDisk
     *            The disk to which this disk is to be compared.
     * @return A positive integer if this disk is wider than the otherDisk, a
     *             negative integer if this disk is narrower than the otherDisk,
     *             and zero if both disks have the same width.
     * @throws IllegalArgumentException
     *             If the provided otherDisk is null.
     */
    public int compareTo(Disk otherDisk)
    {
        if (otherDisk == null)
        {
            throw new IllegalArgumentException();
        }
        if (otherDisk.getWidth() < this.getWidth())
        {
            return 1;
        }
        if (otherDisk.getWidth() > this.getWidth())
        {
            return -1; // valid push
        }
        return 0;
    }


    /**
     * Provides a string representation of the Disk, which is its width. This
     * method can be used for debugging or any textual representation of the
     * disk.
     * 
     * @return The width of the disk as a string.
     */
    @Override
    public String toString()
    {
        return "" + this.getWidth();
    }


    /**
     * Compares this disk to another object for equality based on width. This
     * method checks if the provided object is a Disk and if it has the same
     * width as this disk.
     * 
     * @param obj
     *            The object to be compared for equality with this disk.
     * @return true if the provided object is a disk with the same width as this
     *             disk; false otherwise.
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        Disk otherDisk = (Disk)obj;
        return this.getWidth() == otherDisk.getWidth();
    }

}
