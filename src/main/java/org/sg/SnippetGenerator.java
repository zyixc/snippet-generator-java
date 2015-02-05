package org.sg;

import org.apache.commons.cli.Options;

import java.io.IOException;

/**
 * Created by zyixc @ matthijsnieuwboer@gmail.com on 2/5/2015.
 */
public class SnippetGenerator {
    public static void main(String[] args) throws IOException {
        Options options = new Cli(args).getOptions();
    }
}
