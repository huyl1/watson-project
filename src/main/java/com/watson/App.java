package com.watson;
import java.util.ArrayList;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SearchEngine engine = new SearchEngine("index-example");
        ArrayList<Document> documents = engine.search("politician most often credited", 10);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }
    }
}
