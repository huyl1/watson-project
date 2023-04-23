package com.watson;
import java.util.ArrayList;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String test_String = "This clear Greek liqueur is quite potent, so it's usually mixed with water, which turns it white & cloudy GREEK FOOD & DRINK";
        SearchEngine engine = new SearchEngine("V1_1");
        String keywords = engine.keywordExtract(test_String);
        ArrayList<Document> documents = engine.searchV1(test_String, 20);
        System.out.println("Search results for: " + keywords);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }

    }

}
