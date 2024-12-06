package com.example.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.dictionary.model.Entry;
import com.example.dictionary.reference.DictionaryReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class DictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
    }

        private static Void readDictonaryFile() throws JsonProcessingExeption {


            return null;
        }
    public List<Entry> getWordStartingWith(String value){

        return DictionaryReference.getDictionary()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(value))
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map( entry -> new Entry(entry.getKey(), entry.getBalue()))
                .collect(Collector.toList());
    }

    public List<Entry> getWordThatContainConsecutiveDoubleLetters(String value){
        return DicotrionaryReference.getDictionary()
                .entrySet()
                .stream()
                .filter(entry -> {
                    String word entry.getKey();
                }
    }
}
