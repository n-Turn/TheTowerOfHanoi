package towerofhanoi;

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The main front-end work and the view for the Tower of Hanoi puzzle
 *
 * @author cooln
 * @version Oct 8, 2023
 */
public class PuzzleWindow
    implements Observer
{

    private HanoiSolver game;
    private Shape left;
    private Shape middle;
    private Shape right;
    private Window window;

    /**
     * A factor in which the width of the disks are multiplied by
     */
    public static final int WIDTH_FACTOR = 15;
    /**
     * The vertical gap between each disk on the tower
     */
    public static final int DISK_GAP = 0;
    /**
     * The height of each disk on the tower
     */
    public static final int DISK_HEIGHT = 15;

    /**
     * Creates a new PuzzleWindow view for a given HanoiSolver game
     *
     * @param g
     *            the game to create a view for
     */
    public PuzzleWindow(HanoiSolver g)
    {
        this.game = g;
        game.addObserver(this);
        window = new Window();
        window.setSize(1000, 500);
        window.setTitle("Tower of Hanoi");

        // The height and Y location of each pole are the same
        int poleHeight = 400;
        int poleY = (window.getGraphPanelHeight() / 2) - (poleHeight / 2);
        left = new Shape(
            (200 - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));
        middle = new Shape(
            ((window.getGraphPanelWidth() / 2) - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));
        right = new Shape(
            ((window.getGraphPanelWidth() - 200) - 15 / 2),
            poleY,
            15,
            poleHeight,
            new Color(50, 50, 50));

        Button quitButton = new Button("Solve");
        quitButton.onClick(this, "clickedSolve");
        window.addButton(quitButton, WindowSide.NORTH);

        window.addShape(left);
        window.addShape(middle);
        window.addShape(right);

        for (int width =
            (game.disks() + 1) * WIDTH_FACTOR; width > WIDTH_FACTOR; width -=
                WIDTH_FACTOR)
        {

            Disk newDisk = new Disk(width);
            game.getTower(Position.LEFT).push(newDisk);
            window.addShape(newDisk);

            newDisk.setX(
                (left.getWidth() / 2 + left.getX()) - newDisk.getWidth() / 2);
            newDisk.setY(
                left.getY() + left.getHeight()
                    - (DISK_HEIGHT * game.getTower(Position.LEFT).size()));

        }

        window.moveToBack(left);
        window.moveToBack(middle);
        window.moveToBack(right);

    }


    /**
     * updates the front-end after the back-end has been changed.
     * 
     * @param position
     *            is to be able to peek at the disk that was moved in order to
     *            update the information for the display
     */
    private void moveDisk(Position position)
    {

        Disk currentDisk = game.getTower(position).peek();
        Shape currentPole;

        if (position == Position.LEFT)
        {
            currentPole = left;
        }
        else if (position == Position.MIDDLE)
        {
            currentPole = middle;
        }
        else
        {
            currentPole = right;
        }

        int poleX = currentPole.getX();
        int middleOfPole = currentPole.getWidth() / 2 + poleX;
        poleX = middleOfPole - currentDisk.getWidth() / 2;

        int numOfDisks = game.getTower(position).size();
        int bottomOfPole = currentPole.getY() + currentPole.getHeight()
            - (DISK_HEIGHT * numOfDisks);

        currentDisk.moveTo(poleX, bottomOfPole);
    }


    /**
     * Updates the view whenever a disk is moved in the back-end
     *
     * @param o
     *            The observable that triggered the update
     * @param arg
     *            arguments sent by the game; should be a position
     */
    @Override
    public void update(Observable o, Object arg)
    {
        if (arg.getClass() == Position.class)
        {
            Position position = (Position)arg;
            moveDisk(position);
            sleep();
        }
    }


    /**
     * Runs when the Solve button is clicked, tells the puzzle to start solving
     *
     * @param button
     *            the button that was clicked
     */
    public void clickedSolve(Button button)
    {
        button.disable();
        new Thread() {
            public void run()
            {
                game.solve();
            }
        }.start();
    }


    private void sleep()
    {
        try
        {
            Thread.sleep(75);
        }
        catch (Exception e)
        {
        }
    }
}
