package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    private AggregatorRestClient aggregatorRestClient;

    public AggregatorService(AggregatorRestClient aggregatorRestClient) {
        this.aggregatorRestClient = aggregatorRestClient;
    }

    public Entry getDefinitionFor(String word) {
        return aggregatorRestClient.getDefinitionFor(word);
    }

    public List<Entry> getsWordsThatContainsSuccesiveLetterAndStartsWith(chars chars) {
        //two ends point with in the same service
        List<Entry> wordsThatStartWith = aggregatorRestClient.getWordsStartingWith(chars);
        List <Entry> wordsThatContainSuccessiveLetters = aggregatorRestClient.getWordsThatContainsSuccesiveLetter(chars);

        List<Entry> common = new ArrayList<>(wordsThatStartWith);
        common.retainAll(wordsThatContainSuccessiveLetters);
    }

}
