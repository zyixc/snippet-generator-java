package org.sg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by zyixc @ matthijsnieuwboer@gmail.com on 2/5/2015.
 */
public class SnippetGeneratorClient {
    public static void main(String[] args) {
        Cli cli = new Cli(args);
        SnippetGenerator snippetGenerator = null;
        try {
            snippetGenerator = new SnippetGenerator(Files.lines(Paths.get(cli.getDocumentName())));
        } catch(IOException e) {
            System.err.println("File not found!");
        }

        boolean finished = false;
        Scanner input = new Scanner(System.in);
        while(!finished){
            System.out.println("Enter search term:");
            String searchTerm = input.nextLine();
            System.out.println("Enter snippet size (Before/After):");
            int snippetSize = Integer.parseInt(input.nextLine());
            System.out.println("Generating snippets!");
            for(String result: snippetGenerator.generateSnippets(searchTerm, snippetSize)){
                System.out.println(result);
            }
            System.out.println("Would you like to continue (Y/n)");
            if(input.nextLine().equalsIgnoreCase("n"))
                finished = true;
        }
    }
}
