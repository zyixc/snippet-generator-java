package org.sg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by zyixc @ matthijsnieuwboer@gmail.com on 2/5/2015.
 */
public class SnippetGenerator {
    public static void main(String[] args) throws IOException {
        Cli cli = new Cli(args);
        HashMap<Integer,String> words = readFile(cli.getDocumentName());
    }

    private static HashMap<Integer,String> readFile(String documentName){
        HashMap<Integer,String> words = null;
        try {
            words = Files.lines(Paths.get(documentName))
                .flatMap(line -> Arrays.stream(line.split(" .")))
                .collect(Collectors.toMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
