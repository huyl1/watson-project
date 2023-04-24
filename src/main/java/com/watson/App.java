package com.watson;
import java.util.ArrayList;

import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String test_String = "The practice of pre-authorizing presidential use of force dates to a 1955 resolution re: this island near mainland China";
        String test_Topic = "OLD YEAR'S RESOLUTIONS";
        SearchEngine engine = new SearchEngine("V2_3");
        //String query = engine.queryBuilderV3(test_String, test_Topic);
        // ArrayList<Document> documents = engine.searchV2(query, 5);
        // System.out.println("Search results for: " + query);
        // for (Document doc : documents) {
        //     System.out.println(doc.get("title"));
        // }
        engine.synonymExpansion(test_String);
    }

}
