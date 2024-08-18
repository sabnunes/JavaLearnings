/**
 * A class representing a shared counter with concurrent counting functionality.
 *
 * @author Sabrina Nunes
 */
public class Counter
{
    private int count;

    // instance
    {
        count = 0;
    }

    /** Increments the counter in a synchronized manner to ensure thread safety. */
    public synchronized void increment()
    {
        count++;
    }

    /**
     * Returns the current count.
     *
     * @return the current count
     */
    public int getCount()
    {
        return count;
    }

    /**
     * Executes the concurrent counting with the specified number of threads and increments per thread.
     *
     * @param numThreads the number of threads to use
     * @param increments the number of increments per thread
     */
    public void executeConcurrentCounting(final int numThreads, final int increments)
    {
        final Thread[] threads;

        threads = new Thread[numThreads];

        for(int i = 0; i < numThreads; i++)
        {
            threads[i] = new Thread(()->{
                for(int j = 0; j < increments; j++)
                {
                    increment();
                }
            });
            threads[i].start();
        }

        for(Thread thread : threads)
        {
            try
            {
                thread.join();
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
