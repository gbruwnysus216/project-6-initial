package com.example.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dictionary.model.Entry;
import com.example.dictionary.reference.DictionaryReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
    }
    // AI prompt used: "write an implemtation for this method"

    private static void readDictionaryFile() throws JsonProcessingException {
        //I don't remember any of the lectures discussing the DictonaryApplication at 11/14 no 11/19
        //I just read the module and ask chatGPT where to put the dictronary.json file prompt: "Where should I put the dictionary.json file?"
        //String filePath = "/Users/gbruwnysus/Documents/GitHub/project-6-initial/dictionary/src/main/java/com/example/dictionary/dictionary.json"
        String filePath = "src/main/resources/dictionary.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            Map<String, String> dictionary = objectMapper.readValue(jsonContent, new TypeReference<Map<String, String>>() {
            });
            DictionaryReference.setDictionary(dictionary);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonProcessingException("Error reading dictionary file: " + e.getMessage()) {
            };
        }
    }

    public List<Entry> getWordsStartingWith(String value) {
        return DictionaryReference.getDictionary()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(value))
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(entry -> new Entry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<Entry> getWordsThatContain(String value){
        return DictionaryReference.getDictionary()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(value))
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(entry -> new Entry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<Entry> getWordsThatContainConsecutiveDoubleLetters() {
        return DictionaryReference.getDictionary()
                .entrySet()
                .stream()
                .filter(entry -> {
                    String word = entry.getKey();
                    for (int i = 0; i < word.length() - 1; i++) {
                        if (word.charAt(i) == word.charAt(i + 1)) {
                            return true;
                        }
                    }
                    return false;
                })
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(entry -> new Entry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}