package org.sg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by zyixc @ matthijsnieuwboer@gmail.com on 2/5/2015.
 */
public class SnippetGeneratorClient {
    public static void main(String[] args) {
        Cli cli = new Cli(args);
        SnippetGenerator snippetGenerator = null;
        try {
            long startTime = System.nanoTime();
            snippetGenerator = new SnippetGenerator(Files.lines(Paths.get(cli.getDocumentName())));
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Time in seconds it took to process file: " + ((double) elapsedTime / 1E9));
            System.out.println("Number of words in document: " + snippetGenerator.getWordCount());
            System.out.println();
        } catch(IOException e) {
            System.err.println("File not found!");
            System.exit(1);
        }

        boolean finished = false;
        Scanner input = new Scanner(System.in);
        while(!finished){
            System.out.print("Enter search term: ");
            String searchTerm = input.nextLine();

            System.out.print("Enter snippet size (Before/After): ");
            int snippetSize = Integer.parseInt(input.nextLine());

            long startTime = System.nanoTime();
            int resultCount = snippetGenerator.generateSnippets(searchTerm, snippetSize).size();
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Time in seconds it took to generate snippets: " +
                    new DecimalFormat("#.##########").format((double) elapsedTime / 1E9));
            System.out.println("Number of results: " + resultCount);

            System.out.print("Would you like to continue (Y/n): ");
            if(input.nextLine().equalsIgnoreCase("n"))
                finished = true;
            System.out.println();
        }
    }
}
