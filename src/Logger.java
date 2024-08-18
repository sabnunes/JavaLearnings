/**
 * A Singleton class for logging even and odd numbers.
 *
 * @author Sabrina Nunes
 */
public class Logger
{
    private static Logger        instance;
    private        StringBuilder log;

    static
    {
        instance = null;
    }

    /** Private constructor to prevent multiple instances. */
    private Logger()
    {
        log = new StringBuilder();
    }

    /**
     * Returns the single instance of the Logger class.
     *
     * @return the single instance of the Logger
     */
    public static Logger getInstance()
    {
        if(instance == null)
        {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Logs a message.
     *
     * @param numbers the even and odd numbers to be logged
     */
    public void log(final String numbers)
    {
        log.append(numbers)
           .append("\n");
    }

    /**
     * Returns the logged messages.
     *
     * @return the logged messages
     */
    public String getLog()
    {
        return log.toString();
    }
}
