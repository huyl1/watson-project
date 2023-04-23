package com.watson;
import java.util.ArrayList;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String test_String = "Because it's cured & stored in brine, this crumbly white cheese made from sheep's milk is often referred to as pickled cheese";
        String test_Topic = "GREEK FOOD & DRINK";
        SearchEngine engine = new SearchEngine("V2_3");
        String query = engine.queryBuilderV2(test_String, test_Topic);
        ArrayList<Document> documents = engine.searchV2(query, 5);
        System.out.println("Search results for: " + query);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }

    }

}
