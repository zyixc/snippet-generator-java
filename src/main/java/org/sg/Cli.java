package org.sg;

import org.apache.commons.cli.*;

/**
 * Created by zyixc @ matthijsnieuwboer@gmail.com on 2/5/2015.
 */
public class Cli {
    private String[] args = null;
    private Options options = new Options();
    private String documentName = null;

    public Cli(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "Show help");
        options.addOption("d", "document", true, "Document for snippet generation");
        this.parse();
    }

    private void parse() {
        CommandLineParser parser = new BasicParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                help();
            }

            if (cmd.hasOption("d")) {
                this.documentName = cmd.getOptionValue("d");
            } else {
                help();
            }
        } catch (ParseException e) {
            System.out.println("Failed to parse command line properties" + e);
            help();
        }
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("SnippetGenerator", options);
        System.exit(0);
    }

    public String getDocumentName() {
        return documentName;
    }
}
