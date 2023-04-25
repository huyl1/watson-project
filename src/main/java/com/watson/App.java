package com.watson;
import java.util.ArrayList;

import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String test_String = "Several bridges, including El Tahrir, cross the Nile in this capital";
        String test_Topic = "AFRICAN CITIES";
        SearchEngine engine = new SearchEngine("V2_3");
        String query = engine.queryBuilderV3(test_String, test_Topic);
        ArrayList<Document> documents = engine.searchV2_2(query, 3);
        System.out.println("Search results for: " + query);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }
    }

}
