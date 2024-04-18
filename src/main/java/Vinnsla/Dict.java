package Vinnsla;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.InputStream;


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
            JsonNode wordNode = rootNode.path(searchWord.toUpperCase()); // Assuming the words in your JSON are uppercase
            if (!wordNode.isMissingNode()) {
                JsonNode meaningsNode = wordNode.path("MEANINGS");
                if (!meaningsNode.isMissingNode()) {
                    StringBuilder meaningBuilder = new StringBuilder();

                    meaningsNode.fields().forEachRemaining(entry -> {
                        String key = entry.getKey(); // The sense number, not usually displayed
                        JsonNode meaningInfo = entry.getValue();

                        String partOfSpeech = meaningInfo.get(0).asText();
                        String definition = meaningInfo.get(1).asText();

                        meaningBuilder.append(partOfSpeech).append(": ").append(definition).append("\n");

                        if (meaningInfo.size() > 3) {
                            JsonNode examples = meaningInfo.get(3);
                            if (examples.isArray()) {
                                meaningBuilder.append("Example: ");
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

}
