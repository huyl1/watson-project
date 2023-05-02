package com.watson;
import java.util.ArrayList;

import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String test_String = "In 2010: As Sherlock Holmes on film";
        String test_Topic = "GOLDEN GLOBE WINNERS";
        SearchEngine engine = new SearchEngine("V2_3");
        String query = engine.queryBuilderV2(test_String, test_Topic);
        ArrayList<Document> documents = engine.searchV2_3(test_String, test_Topic, 1);
        System.out.println("Search results for: " + query);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }

        //System.out.println(engine.modelExpansion("Golden Globe winners", 5));
    }

}
