package com.example.dictionary.reference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DictionaryReference {

    //memorize the Looger
    private static final Logger logger = LoggerFactory.getLogger(DictionaryReference.class.getName());

    //default memory allocation for the dictionary may not be enough
    private static Map<String, String> dictionary;

    //greedy initialization of the dictionary, as soon as the class is load the file get reads
    //loaded the class file is loaded into RAM memory, ra after it's declared
    //kind of constuctors with instances of a class
    //static blocks accosiated with the class, not with the instance
    static {

        try {
            readDictionaryFile();
        } catch (JsonProcessingException e) {
            logger.error("There was a problem reading the dictionary file");
        }
    }

    private DictionaryReference() {

    }

    private static void readDictionaryFile() throws JsonProcessingException {

        StopWatch sw = new StopWatch();
        sw.start();
        //stream IO file
        InputStream inputStream = DictionaryReference.class.getClassLoader()
                                                           .getResourceAsStream("dictionary.json");
        //
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //lines is a stream API, stream of lines from buffer reader one at a time

        String json = bufferedReader.lines()
                                    .collect(Collectors.joining("\n"));

        ObjectMapper mapper = new ObjectMapper();
        dictionary = mapper.readValue(json, Map.class);
        // get
        sw.stop();

        long milliseconds = sw.getLastTaskTimeMillis();

        String message = new StringBuilder().append("Dictionary created with ")
                                            .append(dictionary.size())
                                            .append(" entries in ")
                                            .append(milliseconds)
                                            .append("ms")
                                            .toString();
        logger.info(message);
    }

    public static Map<String, String> getDictionary() {
        return DictionaryReference.dictionary;
    }

    public static void setDictionary(Map<String, String> dictionary) {
        DictionaryReference.dictionary = dictionary;
    }


}
