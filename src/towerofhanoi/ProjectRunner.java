package towerofhanoi;

// -------------------------------------------------------------------------
/**
 * Runs the project
 * 
 * @author cooln
 * @version Oct 12, 2023
 */
public class ProjectRunner
{
    /**
     * Place a description of your method here.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        int disks = 6;

        if (args.length == 1)
        {
            disks = Integer.parseInt(args[0]);
        }

        HanoiSolver solver = new HanoiSolver(disks);
        PuzzleWindow projectWindow = new PuzzleWindow(solver);
    }
}
