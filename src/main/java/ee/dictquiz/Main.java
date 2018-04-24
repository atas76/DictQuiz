package ee.dictquiz;

import ee.dictquiz.model.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String filename = args[0];
        String line;

        List<String> lines = new ArrayList<>();

        try {

            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        playQuiz(lines, args[1]);
    }

    private static int score = 0;

    private static void playQuiz(List<String> lines, String languageMapping) {

        List<Word> words = loadWords(lines);

        Collections.shuffle(words);

        Scanner in = new Scanner(System.in);

        for (Word word: words) {
            quiz(word, languageMapping, in);
        }

        System.out.println();
        System.out.println("Score: " + score + " / " + words.size());

        in.close();
    }

    private static void quiz(Word word, String languageMapping, Scanner in) {

        String key = languageMapping.startsWith("ee") ?
                word.getTranslation("ee") : word.getTargetTranslation("ee");

        System.out.print(key + " " + word.showTranslations() + " ");

        String answer = in.nextLine();

        if (word.translatesTo(answer, languageMapping)) {
            // Print suitable message
            System.out.println("Correct!");
            ++score;
        } else {

            String confirmation = "d";

            while (!confirmation.equalsIgnoreCase("c")) {
                System.out.print("Wrong. Confirm answer or repeat?  ");
                confirmation = in.nextLine();
                if ("r".equalsIgnoreCase(confirmation)) {
                    quiz(word, languageMapping, in);
                    confirmation = "c";
                }
            }
        }
    }

    private static List<Word> loadWords(List<String> lines) {

        List<Word> words = new ArrayList<>();

        for (String line: lines) {
            words.add(new Word(line));
        }

        return words;
    }
}
