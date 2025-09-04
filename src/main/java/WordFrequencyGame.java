import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return frequencies.stream()
            .map(w -> w.value() + " " + w.wordCount())
            .collect(Collectors.joining("\n"));
    }

    private List<Input> countFrequencies(String[] words) {
        return Arrays.stream(words)
            .collect(Collectors.groupingBy(w -> w, Collectors.summingInt(w -> 1)))
            .entrySet().stream()
            .map(e -> new Input(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
    }
}
