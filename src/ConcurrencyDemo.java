/**
 * @author Sabrina Nunes
 */
class ConcurrencyDemo
{
    private static final int    TASK_LENGTH;  // Number of iterations in each task
    private static final int    DELAY_MS_10; // 10 ms delay instead of 500 ms for quicker results
    private static final int    DELAY_MS_500; // 10 ms delay instead of 500 ms for quicker results
    private static final String SEQUENTIAL;
    private static final String CONCURRENT;
    private static final String TASK_1 = "Task 1: Factorial";
    private static final String TASK_2 = "Task 2: Fibonacci";
    private static final String TASK_3 = "Task 3: Sum Prime";
    private static final String TASK_4 = "Task 4: Sum Even";
    private static final String TASK_5 = "Task 5: Sum Odd";

    static
    {
        TASK_LENGTH  = 10;
        DELAY_MS_10  = 10;
        DELAY_MS_500 = 500;
        SEQUENTIAL   = "Sequential";
        CONCURRENT   = "Concurrent";
    }

    public static void main(final String[] args)
    {
        // Sequential Execution
        System.out.println("Starting sequential tasks...");
        long startTimeSequential = System.currentTimeMillis();

        // Perform two tasks sequentially and measure the time for each
        long timeForTask1 = performTaskWithDelay(SEQUENTIAL, TASK_1, TASK_LENGTH, DELAY_MS_500);
        long timeForTask2 = performTaskWithDelay(SEQUENTIAL, TASK_2, TASK_LENGTH, DELAY_MS_500);
        long timeForTask3 = performTaskWithDelay(SEQUENTIAL, TASK_3, TASK_LENGTH, DELAY_MS_10);
        long timeForTask4 = performTaskWithDelay(SEQUENTIAL, TASK_4, TASK_LENGTH, DELAY_MS_10);
        long timeForTask5 = performTaskWithDelay(SEQUENTIAL, TASK_5, TASK_LENGTH, DELAY_MS_10);

        long totalTimeSequential = System.currentTimeMillis() - startTimeSequential;
        System.out.println(SEQUENTIAL + " tasks completed in " + totalTimeSequential + " ms.");

        // Concurrent Execution
        System.out.println("\nStarting concurrent tasks...");
        long startTimeConcurrent = System.currentTimeMillis();

        // Create threads for each task
        Thread thread1 = new Thread(()->performTaskWithDelay(CONCURRENT, TASK_1, TASK_LENGTH, DELAY_MS_500));
        Thread thread2 = new Thread(()->performTaskWithDelay(CONCURRENT, TASK_2, TASK_LENGTH, DELAY_MS_500));
        Thread thread3 = new Thread(()->performTaskWithDelay(CONCURRENT, TASK_3, TASK_LENGTH, DELAY_MS_10));
        Thread thread4 = new Thread(()->performTaskWithDelay(CONCURRENT, TASK_4, TASK_LENGTH, DELAY_MS_10));
        Thread thread5 = new Thread(()->performTaskWithDelay(CONCURRENT, TASK_5, TASK_LENGTH, DELAY_MS_10));

        // Start both threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        // Wait for both threads to finish
        try
        {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch(InterruptedException e)
        {
            System.out.println("A thread was interrupted.");
        }

        long totalTimeConcurrent = System.currentTimeMillis() - startTimeConcurrent;
        System.out.println("Concurrent tasks completed in " + totalTimeConcurrent + " ms.");
    }

    /**
     * Performs a simple arithmetic task with a delay in each iteration.
     *
     * @param taskName The name of the task to display in logs.
     * @param count    The number of iterations the task runs.
     * @param delayMs  The delay in milliseconds to add to each iteration.
     *
     * @return the time taken to complete the task in milliseconds.
     */
    private static long performTaskWithDelay(final String sequentialOrConcurrent, final String taskName,
                                             final int count, final int delayMs)
    {
        System.out.println(taskName + " starting...");
        long startTime = System.currentTimeMillis();

        long result = 0;

        switch(taskName)
        {
            case TASK_1: // Factorial
                result = 1; // Initialize result to 1 for factorial calculation
                for(int i = 1; i <= count; i++)
                {
                    result *= i;
                    // System.out.println("Factorial of " + i + " is " + result);

                    try
                    {
                        Thread.sleep(delayMs); // Introduce a delay to simulate a time-consuming operation
                    } catch(InterruptedException e)
                    {
                        System.out.println(taskName + " was interrupted.");
                    }
                }
                break;
            case TASK_2: // Fibonacci 0 1 1 2 3 5 8 13
                int n1 = 0, n2 = 1;
                for(int i = 1; i <= count; i++)
                {
                    if(result == 0)
                    {
                        result += 1;
                    }
                    else
                    {
                        result = n1 + n2;
                        n1     = n2;
                        n2     = (int) result;
                    }

                    // System.out.println("Fibonacci sequence step " + i + " is " + result);

                    try
                    {
                        Thread.sleep(delayMs); // Introduce a delay to simulate a time-consuming operation
                    } catch(InterruptedException e)
                    {
                        System.out.println(taskName + " was interrupted.");
                    }
                }
                break;

            case TASK_3: // Sum Prime
                for(int i = 1; i <= count; i++)
                {
                    if(isPrime(i))
                    {
                        result += i;
                        // System.out.println("Prime number " + i + " added to total: " + result);
                    }
                    try
                    {
                        Thread.sleep(delayMs); // Introduce a delay to simulate a time-consuming operation
                    } catch(InterruptedException e)
                    {
                        System.out.println(taskName + " was interrupted.");
                    }
                }
                break;
            case TASK_4: // Sum Even
                for(int i = 1; i <= count; i++)
                {
                    if(i % 2 == 0)
                    {
                        result += i;
                        // System.out.println("Even number " + i + " added to total: " + result);
                    }
                    try
                    {
                        Thread.sleep(delayMs); // Introduce a delay to simulate a time-consuming operation
                    } catch(InterruptedException e)
                    {
                        System.out.println(taskName + " was interrupted.");
                    }
                }
                break;
            case TASK_5: // Sum Even
                for(int i = 1; i <= count; i++)
                {
                    if(i % 2 != 0)
                    {
                        result += i;
                        // System.out.println("Odd number " + i + " added to total: " + result);
                    }
                    try
                    {
                        Thread.sleep(delayMs); // Introduce a delay to simulate a time-consuming operation
                    } catch(InterruptedException e)
                    {
                        System.out.println(taskName + " was interrupted.");
                    }
                }
                break;
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println(sequentialOrConcurrent + " " + taskName + " result: " + result);
        System.out.println(sequentialOrConcurrent + " " + taskName + " completed in " + timeTaken + " ms.");

        return timeTaken;

    }

    /**
     * Determines if a number is prime.
     *
     * @param number input number
     *
     * @return true or false
     */
    public static boolean isPrime(int number)
    {
        // done the slow way on purpose to slow things down
        for(int i = 2; i < number; i++)
        {
            if(number % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}
