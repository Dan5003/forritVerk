package Vinnsla;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Dict {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String lookupInDictionary(String searchWord) {
        try {
            String firstLetter = searchWord.substring(0, 1).toLowerCase();
            String filename = "/data/" + firstLetter + ".json";
            InputStream is = getClass().getResourceAsStream(filename);
            if (is == null) {
                return "words for " + firstLetter + " not found.";
            }
            JsonNode rootNode = objectMapper.readTree(is);
            JsonNode wordNode = rootNode.path(searchWord.toUpperCase());
            if (!wordNode.isMissingNode()) {
                JsonNode meaningsNode = wordNode.path("MEANINGS");
                if (!meaningsNode.isMissingNode()) {
                    StringBuilder meaningBuilder = new StringBuilder();

                    meaningsNode.fields().forEachRemaining(entry -> {
                        String key = entry.getKey();
                        JsonNode meaningInfo = entry.getValue();

                        String partOfSpeech = meaningInfo.get(0).asText();
                        String definition = meaningInfo.get(1).asText();

                        meaningBuilder.append(partOfSpeech).append(": ").append(definition).append("\n");

                        if (meaningInfo.size() > 3) {
                            JsonNode examples = meaningInfo.get(3);
                            if (examples.isArray()) {
                                meaningBuilder.append("Dæmi: ");
                                examples.forEach(example -> meaningBuilder.append(example.asText()).append("; "));
                                meaningBuilder.append("\n");
                            }
                        }
                    });

                    return meaningBuilder.toString();
                }
            }

            return searchWord + " No word found";
        } catch (Exception e) {
            e.printStackTrace();
            return "search the word.";
        }
    }
    public String getRandomWord() {
        try {
            char randomLetter = getRandomLetter();
            String filename = "/data/" + randomLetter + ".json";
            InputStream is = getClass().getResourceAsStream(filename);
            JsonNode rootNode = objectMapper.readTree(is);
            List<String> words = new ArrayList<>();
            Iterator<String> fieldNames = rootNode.fieldNames();
            while (fieldNames.hasNext()) {
                words.add(fieldNames.next());
            }
            String randomWord = words.get(new Random().nextInt(words.size()));
            String meaning = lookupInDictionary(randomWord);
            if (meaning.equals(randomWord + " fannst ekki í orðabókinni.") || meaning.equals("Villa við að leita að orði.")) {
                return meaning;
            } else {
                return randomWord.toUpperCase() + ":\n" + meaning;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving random word with meaning.";
        }
    }

    private char getRandomLetter() {
        return (char) ('a' + new Random().nextInt(26)); //26 stafir í stafrófinu.
    }

}
