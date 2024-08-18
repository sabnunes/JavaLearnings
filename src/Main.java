import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        // Logger
        Logger logger = Logger.getInstance();

        // GUI
        SwingUtilities.invokeLater(() -> new GUI());

        // NumbersMagic
        NumbersMagic numbersMagic = new NumbersMagic();
        numbersMagic.processNumbers("numbers.txt"); // Ensure this is called only once

        // Counter
        Counter counter = new Counter();
        counter.executeConcurrentCounting(5, 1000);
        logger.log("Final Count: " + counter.getCount());

        // Print the log
        System.out.println(logger.getLog());
    }
}
