package com.watson;
import java.util.ArrayList;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SearchEngine engine = new SearchEngine("V1");
        ArrayList<Document> documents = engine.searchV1("The dominant paper in our nation's capital, it's among the top 10 U.S. papers in circulation", 10);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }
    }
}
