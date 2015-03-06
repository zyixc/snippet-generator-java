package org.sg;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zyixc @ matthijsnieuwboer@gmail.com on 2/5/2015.
 */
public class SnippetGenerator {
    private Map<String,List<Integer>> wordHashMap = new HashMap<>();
    private Map<Integer,String> positionHashMap = new HashMap<>();
    private int wordCount = 0;

    public SnippetGenerator(Stream<String> lines){
        List<String> words = lines.flatMap(line -> Stream.of(line.split("\\W+")).map(String::toLowerCase)).collect(Collectors.toList());
        wordCount = words.size();
        for(int position=0; position<words.size(); position++){
            positionHashMap.put(position, words.get(position));
            List<Integer> tempPosition = wordHashMap.get(words.get(position));
            if(tempPosition == null) {
                List<Integer> positionArray = new ArrayList<>();
                positionArray.add(position);
                wordHashMap.put(words.get(position), positionArray);
            } else {
                tempPosition.add(position);
                wordHashMap.put(words.get(position), tempPosition);
            }
        }
    }

    public List<String> generateSnippets(String searchTerm, int snippetSize) {
        List<String> snippets = new ArrayList<>();
        List<Integer> positions = wordHashMap.get(searchTerm.toLowerCase());
        if(positions==null) return snippets;
        for(Integer position: positions){
            StringBuilder result = new StringBuilder();
            for(int iPosition=(position-snippetSize);iPosition<=(position+snippetSize);iPosition++){
                String resultIPosition = positionHashMap.get(iPosition);
                if(resultIPosition!=null){
                    if(resultIPosition.equals(searchTerm.toLowerCase())){
                        resultIPosition = '"' + resultIPosition + '"';
                    }
                    result.append(resultIPosition + " ");
                }
            }
            snippets.add(result.toString());
        }
        return snippets;
    }

    public int getWordCount() {
        return wordCount;
    }
}
