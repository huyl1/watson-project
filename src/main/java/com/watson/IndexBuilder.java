package com.watson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexBuilder {
    public static void main(String[] args) throws IOException {
        buildIndexExample("index-example");
    }

    public static void buildIndexExample(String index_name) throws IOException {
                // Create a new index in the directory. Make sure you create a new directory for each new index. 
                // Make sure all indexes are in the same directory (indicies/)
                Directory dir = FSDirectory.open(new File("indicies/" + index_name).toPath());
                IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
                IndexWriter writer = new IndexWriter(dir, config);
        
                // Use wikipedia parser to parse the wikipedia dump to documents
                ArrayList<Document> documents= WikipediaParser.parse("dataset/wiki-example.txt");
        
                //add each document to the index
                for (Document doc : documents) {
                    writer.addDocument(doc);
                }
                //close index writer
                writer.close();
    }
    
}
