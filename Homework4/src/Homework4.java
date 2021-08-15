import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Test {
    private static final int[] LETTER_VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1,
            1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args)
    {
        String message = "default";
        while (!message.equals("")) {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the message (ENTER only to quit): ");
            message = s.nextLine();
            if (message.equals(""))
                break;
            System.out.print("Enter the key: ");
            String key = s.nextLine();
            if (key.equals("")) {
                System.err.println("\nError: empty key.");
                continue;
            }
            System.out.println("Cleaned message: " + cleanString(message).toUpperCase(Locale.ROOT));
            System.out.println("Cleaned key: " + dupeTillEqual(cleanString(key),
                    cleanString(message)).toUpperCase(Locale.ROOT));

            String decider;
            do {
                System.out.print("Encode or decode? (E or D) ");
                decider = s.nextLine();

                if (decider.equals("E") || decider.equals("e")) {
                    System.out.println("Encoded message: " + encode(message, key));
                }

                if (decider.equals("D") || decider.equals("d")) {
                    System.out.println("Decoded message: " + decode(message, key));
                }
            } while (!decider.equals("E") && !decider.equals("e")
                    && !decider.equals("D") && !decider.equals("d"));
        }
        System.out.println("Bye!");
    }


    // problem 1

    public static String stringCombine(String a, String b) {
        String r = "";
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                char aChar = a.charAt(i);
                char bChar = b.charAt(j);
                if (!r.contains(aChar + "" + bChar)) {
                    r = r + "" + aChar + bChar;
                    if (i < a.length() - 1 || j < b.length() - 1) {
                        r = r + ",";
                    }
                }
            }
        }
        return r;
    }

    // problem 2

    public static String giveAnswer() {
        String string1 = "The longest string for string a with length m";
        String string2 = "and string b with length m would have m * n terms";
        String string3 = "and be (m * n * 3) - 1 characters long (because of commas).";
        String string4 = "This string would appear when neither string a nor b";
        String string5 = "had any duplicate characters.";

        return string1 + "\n" + string2 + "\n" + string3 + "\n" + string4 + "\n" + string5;

    }

    // problem 3

    public static void oneNamePerLine(String names) {
        Scanner delimeter = new Scanner(names).useDelimiter(",");

        while (delimeter.hasNext()) {
            System.out.println(delimeter.next());
        }

        delimeter.close();
    }

    // problem 4

    public static int sumFile(String filename) {
        int counter = 0;
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File cannot be opened, check if the file exists.");
            return 0;
        }
        if (!file.exists()) {
            return 0;
        } else {
            while (scanner.hasNextLine()) {
                int data = scanner.nextInt();
                counter += data;
            }
        }
        return counter;
    }

    // problem 5

    public static class Delayer {
        public int num;

        public Delayer(int num) {
            this.num = num;
        }

        public int delay(int num) {
            int oldNum = this.num;
            this.num = num;
            return oldNum;
        }

        public String toString() {
            return "delaying " + this.num;
        }

        public boolean equals(Delayer other) {
            if (other == null) { return false; }
            if (this.num == other.num) { return true; }
            else { return false; }
        }
    }

    // final problem

    private static String dupeTillEqual(String item, String template) {
        if (template.length() <= item.length()) {
            return item.substring(0, template.length());
        }
        while (item.length() < template.length()) {
            item = item + item;
        }
        item = item.substring(0, template.length());
        return item;
    }

    private static String removeNonLetters(String string, String alphabet) {
        char[] letters = string.toCharArray();
        String s = "";
        for (int i = 0; i < letters.length; i++) {
            if (alphabet.contains(letters[i] + "")) {
                s = s + letters[i];
            }
        }
        return s;
    }

    private static String cleanString(String string) {
        String mixCase = removeNonLetters(string, ALPHABET + ALPHABET.toUpperCase(Locale.ROOT));
        return mixCase.toUpperCase(Locale.ROOT);
    }

    private static String encode(String message, String key) {
        String alphabet = ALPHABET.toUpperCase(Locale.ROOT);
        key = dupeTillEqual(key, message);
        key = cleanString(key);
        message = cleanString(message);

        char[] keyLetters = key.toCharArray();
        char[] messageLetters = message.toCharArray();

        String eMsg = "";

        for (int i = 0; i < messageLetters.length; i++) {
            int eInt = (messageLetters[i] + (keyLetters[i] - 65));
            if (eInt > 90) { eInt -= 26; }
            char eChar = (char) eInt;
            eMsg = eMsg + (eChar + "");
        }
        return eMsg;
    }

    private static String decode(String message, String key) {
        String alphabet = ALPHABET.toUpperCase(Locale.ROOT);
        key = dupeTillEqual(key, message);
        key = cleanString(key);
        message = cleanString(message);

        char[] keyLetters = key.toCharArray();
        char[] messageLetters = message.toCharArray();

        String eMsg = "";

        for (int i = 0; i < messageLetters.length; i++) {
            int eInt = (messageLetters[i] - (keyLetters[i] - 65));
            if (eInt < 65) { eInt += 26; }
            char eChar = (char) eInt;
            eMsg = eMsg + (eChar + "");
        }
        return eMsg;
    }

    // lab 2

    private static int getWordScore(String word, int doubleIndex, int tripleIndex) {
        // set indices to -1 to disable
        char[] charray = word.toCharArray();
        int counter = 0;
        for (int i = 0; i < charray.length; i++) {
            counter += getLetterScore(charray[i]);
            if (i == doubleIndex) {
                counter += getLetterScore(charray[i]);
            }
            if (i == tripleIndex) {
                counter += getLetterScore(charray[i]) * 2;
            }
        }
        return counter;
    }

    private static int getLetterScore(char letter) {
        if (!ALPHABET.contains(letter + "")) {
            return 0;
        }
        return LETTER_VALUES[letter - 97];
    }

    public static String scrabbleCalculator() {
        // initialization
        Scanner s = new Scanner(System.in);
        boolean doubleWordScore = false, tripleWordScore = false;
        int doubleIndex = -1, tripleIndex = -1;

        // get the raw word
        System.out.print("Enter a word (* for blank): ");
        String word = s.nextLine().toLowerCase(Locale.ROOT);

        // handle double or triple letter scores
        System.out.print("\nIs there a DOUBLE LETTER SCORE? [y/n] ");
        String yesOrNo = s.next();
        if (yesOrNo.equals("y")) {
            System.out.print("\tWhich index? ");
            doubleIndex = s.nextInt();
        }
        System.out.print("\nIs there a TRIPLE LETTER SCORE? [y/n] ");
        yesOrNo = s.next();
        if (yesOrNo.equals("y")) {
            System.out.print("\tWhich index? ");
            tripleIndex = s.nextInt();
        }



        // handle double or triple word scores
        System.out.print("\nIs there a DOUBLE WORD SCORE? [y/n] ");
        yesOrNo = s.next();
        if (yesOrNo.equals("y")) {
            doubleWordScore = true;
        }
        if (!doubleWordScore) {
            System.out.print("\nIs there a TRIPLE WORD SCORE? [y/n] ");
            yesOrNo = s.next();
            if (yesOrNo.equals("y")) {
                tripleWordScore = true;
            }
        }

        // compute score
        int score = getWordScore(word, doubleIndex, tripleIndex);
        if (doubleWordScore) {
            score *= 2;
        }
        if (tripleWordScore) {
            score *= 3;
        }
        if (word.length() >= 7) {
            score += 50;
        }
        return (word + ": " + score);
    }
}