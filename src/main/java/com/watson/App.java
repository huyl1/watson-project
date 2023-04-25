package com.watson;
import java.util.ArrayList;

import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String test_String = "Wooden 2-story verandas in this Liberian capital are an architectural link to the U.S. south";
        String test_Topic = "AFRICAN CITIES";
        SearchEngine engine = new SearchEngine("V2_3");
        String query = engine.queryBuilderV2(test_String, test_Topic);
        ArrayList<Document> documents = engine.searchV2_2(query, 5);
        System.out.println("Search results for: " + query);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }
    }

}
