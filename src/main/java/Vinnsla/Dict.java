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
                return "Orðabók fyrir stafinn " + firstLetter + " fannst ekki.";
            }
            JsonNode rootNode = objectMapper.readTree(is);
            JsonNode wordNode = rootNode.path(searchWord.toUpperCase()); // Assuming the words in your JSON are uppercase
            if (!wordNode.isMissingNode()) {
                JsonNode meaningsNode = wordNode.path("MEANINGS");
                if (!meaningsNode.isMissingNode()) {
                    String meaning = meaningsNode.toString();
                    return meaning;
                }
            }
            return searchWord + " fannst ekki í orðabókinni.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Villa við að leita að orði.";
        }
    }
}

