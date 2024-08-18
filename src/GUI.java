import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that creates a graphical window with interactive elements to count vowels and consonants.
 *
 * @author Sabrina Nunes
 */
public class GUI
{
    private static final JFrame    FRAME;
    private static final JTextArea TEXT_AREA;
    private static final JLabel    VOWEL_LABEL;
    private static final JLabel    CONSONANT_LABEL;
    private static final int       WIDTH;
    private static final int       HEIGHT;
    private static final int       DEFAULT_COUNT_ZERO;
    private static final String    VOWELS;
    private static final String    CONSONANTS;

    static
    {
        WIDTH              = 400;
        HEIGHT             = 200;
        DEFAULT_COUNT_ZERO = 0;
        VOWELS             = "Vowels: ";
        CONSONANTS         = "Consonants: ";
        FRAME              = new JFrame("Fun Window");
        TEXT_AREA          = new JTextArea(5, 40);
        VOWEL_LABEL        = new JLabel(VOWELS + DEFAULT_COUNT_ZERO);
        CONSONANT_LABEL    = new JLabel(CONSONANTS + DEFAULT_COUNT_ZERO);
    }

    // Constructor to initialize and show the GUI
    public GUI()
    {
        createAndShowGUI();
    }

    // Getter methods for testing
    public JFrame getFrame()
    {
        return FRAME;
    }

    public JTextArea getTextArea()
    {
        return TEXT_AREA;
    }

    public JLabel getVowelLabel()
    {
        return VOWEL_LABEL;
    }

    public JLabel getConsonantLabel()
    {
        return CONSONANT_LABEL;
    }

    private void createAndShowGUI()
    {
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setSize(WIDTH, HEIGHT);
        FRAME.setLayout(new FlowLayout());

        TEXT_AREA.getDocument()
                 .addDocumentListener((SimpleDocumentListener) e->{
                     updateCounts();
                     logText();
                 });

        FRAME.add(TEXT_AREA);
        FRAME.add(VOWEL_LABEL);
        FRAME.add(CONSONANT_LABEL);
        FRAME.setVisible(true);

    }

    private void updateCounts()
    {
        List<String> vowelsConsonants;
        vowelsConsonants = new ArrayList<>(checkVowelsConsonants(TEXT_AREA.getText()
                                                                          .toLowerCase()));
        VOWEL_LABEL.setText(VOWELS + vowelsConsonants.get(0));
        CONSONANT_LABEL.setText(CONSONANTS + vowelsConsonants.get(1));
    }

    private void logText()
    {
        Logger logger = Logger.getInstance();
        logger.log("User typed: " + TEXT_AREA.getText());
    }

    public static List checkVowelsConsonants(final String text)
    {
        int          vowels;
        int          consonants;
        List<String> vowelsConsonants;

        consonants       = DEFAULT_COUNT_ZERO;
        vowels           = DEFAULT_COUNT_ZERO;
        vowelsConsonants = new ArrayList<>();

        for(char c : text.toCharArray())
        {
            if(c >= 'a' && c <= 'z')
            {
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y')
                {
                    vowels++;
                }
                else
                {
                    consonants++;
                }
            }
        }

        vowelsConsonants.add(String.valueOf(vowels));
        vowelsConsonants.add(String.valueOf(consonants));

        return vowelsConsonants;
    }

    // Functional interface for simplifying DocumentListener
    @FunctionalInterface
    interface SimpleDocumentListener
            extends DocumentListener
    {
        void update(DocumentEvent e);

        @Override
        default void insertUpdate(DocumentEvent e)
        {
            update(e);
        }

        @Override
        default void removeUpdate(DocumentEvent e)
        {
            update(e);
        }

        @Override
        default void changedUpdate(DocumentEvent e)
        {
            update(e);
        }
    }

}
