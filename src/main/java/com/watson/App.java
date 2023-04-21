package com.watson;
import java.util.ArrayList;
import org.apache.lucene.document.Document;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        SearchEngine engine = new SearchEngine("V1");
        ArrayList<Document> documents = engine.searchV1("Daniel Hertzberg & James B. Stewart of this paper shared a 1988 Pulitzer for their stories about insider trading", 10);
        for (Document doc : documents) {
            System.out.println(doc.get("title"));
        }
    }
}
