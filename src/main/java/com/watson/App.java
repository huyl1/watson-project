package com.watson;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.document.Document;

/*
 * The purpose of this class serves as our main
 * java file where users can run custom queries.
 * This is using the indexes, search engine, and
 * query builder that gave us the best results.
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Path dir = Paths.get("indicies/V3");
        if (Files.exists(dir)) {
            System.out.println("Index already exists. Skipping index creation.");
        } else {
            IndexBuilder.buildIndexVersion2_3("V2_3");
        }
        SearchEngine engine = new SearchEngine("V2_3");

        while (true) {
            System.out.println("Enter a query: (exit to quit)");
            String query = System.console().readLine();
            if (query.equals("exit")) {
                break;
            }
            System.out.println("Enter a topic: ");
            String topic = System.console().readLine();

            String queryString = engine.queryBuilderV2(query, topic);
            System.out.println("Searching ...");
            ArrayList<Document> results = engine.searchV2_2(queryString, 1);
            if (results.size() == 0) {
                System.out.println("No results found.");
            }
            else {
                System.out.println("Is it " + results.get(0).get("title") + "?");
            }
        }
    }

}
