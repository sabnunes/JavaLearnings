import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that reads numbers from a file and filters them into even and odd groups.
 *
 * @author Sabrina Nunes
 */
public class NumbersMagic
{
    /**
     * Processes numbers from the specified file and logs the even and odd numbers.
     *
     * @param filePath the path to the file containing numbers
     */
    public void processNumbers(final String filePath)
    {
        final List<Integer> evenNumbers;
        final List<Integer> oddNumbers;
        final Logger        logger;

        evenNumbers = new ArrayList<>();
        oddNumbers  = new ArrayList<>();
        logger      = Logger.getInstance(); // Get the singleton instance of Logger

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            List<Integer> numbers = reader.lines()
                                          .flatMap(line -> Arrays.stream(line.split(" ")))
                                          .map(Integer::parseInt)
                                          .collect(Collectors.toList());

            for(int num : numbers)
            {
                if(num % 2 == 0)
                {
                    evenNumbers.add(num);
                }
                else
                {
                    oddNumbers.add(num);
                }
            }

            // Log the even and odd numbers
            logger.log("Even Numbers: " + evenNumbers);
            logger.log("Odd Numbers: " + oddNumbers);

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
