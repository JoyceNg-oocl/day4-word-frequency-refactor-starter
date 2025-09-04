import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public record Input(String value, int wordCount) {}

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<Input> frequencies = countFrequencies(words);
                frequencies.sort((w1, w2) -> w2.wordCount() - w1.wordCount());
                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private String composeOutput(List<Input> frequencies) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : frequencies) {
            String s = w.value() + " " + w.wordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> countFrequencies(String[] words) {
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        List<Input> frequencies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            frequencies.add(new Input(entry.getKey(), entry.getValue()));
        }
        return frequencies;
    }
}
