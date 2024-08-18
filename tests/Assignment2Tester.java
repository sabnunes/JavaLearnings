// Test file provided by instructor and used as a guide to build classes.

import org.junit.jupiter.api.*;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.io.*;
import java.util.*;

 public class Assignment2Tester {

    @Test
    void testEvenAndOddNumbers() {
        // Test Part 1: Numbers Magic (Streams and Filters)
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("numbers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                for (String number : numbers) {
                    int num = Integer.parseInt(number);
                    if (num % 2 == 0) {
                        evenNumbers.add(num);
                    } else {
                        oddNumbers.add(num);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(Arrays.asList(2, 4, 6, 8), evenNumbers);
        Assertions.assertEquals(Arrays.asList(1, 3, 5, 7, 9), oddNumbers);
    }

    @Test
    void testVowelAndConsonantCounter() {
        // Test Part 2: Fun Window (GUI with Swing)
        // Assuming GUI is the class that creates the GUI
        GUI       GUI            = new GUI();
        JFrame    frame          = GUI.getFrame(); // Method to get the JFrame from GUI
        JTextArea textArea       = GUI.getTextArea(); // Method to get the JTextArea from GUI
        JLabel    vowelLabel     = GUI.getVowelLabel(); // Method to get the JLabel for vowels
        JLabel    consonantLabel = GUI.getConsonantLabel(); // Method to get the JLabel for consonants

        // Set text in JTextArea and simulate user typing
        textArea.setText("Hello, Java!");

        // Trigger the caret update event manually
        for (CaretListener listener : textArea.getCaretListeners()) {
            listener.caretUpdate(new CaretEvent(textArea) {
                public int getDot() { return textArea.getText().length(); }
                public int getMark() { return textArea.getText().length(); }
            });
        }

        // Assertions to verify the correct counts
        Assertions.assertEquals("Vowels: 4", vowelLabel.getText());
        Assertions.assertEquals("Consonants: 5", consonantLabel.getText());
    }

    @Test
    void testLoggerInstance() {
        // Test Part 3: Secret Keeper (Singleton Design Pattern)
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        Assertions.assertSame(logger1, logger2);
    }

    @Test
    void testConcurrency() {
        // Test Part 4: Speed Counter (Concurrency)
        Counter counter = new Counter();
        int numThreads = 5;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertEquals(5000, counter.getCount());
    }
}
