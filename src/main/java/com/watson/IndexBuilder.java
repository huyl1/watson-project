package com.watson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.opennlp.OpenNLPLemmatizerFilterFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexBuilder {
    public static void main(String[] args) throws IOException {
        buildIndexExampleV3("index-exampleV3");
    }

    public static void buildIndexExample(String index_name) throws IOException {
        // Create a new index in the directory. Make sure you create a new directory for each new index. 
        // Make sure all indexes are in the same directory (indicies/)
        Directory dir = FSDirectory.open(new File("indicies/" + index_name).toPath());
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);

        // Use wikipedia parser to parse the wikipedia dump to documents
        ArrayList<Document> documents= WikipediaParser.parserV1("dataset/wiki-example.txt");

        //add each document to the index
        for (Document doc : documents) {
            writer.addDocument(doc);
        }
        //close index writer
        writer.close();
    }

    public static void buildIndexExampleV3(String index_name) throws IOException {
        // Create a new index in the directory. Make sure you create a new directory for each new index. 
        // Make sure all indexes are in the same directory (indicies/)
        Directory dir = FSDirectory.open(new File("indicies/" + index_name).toPath());
        
        Analyzer customAnalyzer = CustomAnalyzer.builder()
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop")
            .addTokenFilter(OpenNLPLemmatizerFilterFactory.class, "dictionary", "en-lemmatizer.dict", "lemmatizerModel", "en-lemmatizer.bin")
            .build();

        IndexWriterConfig config = new IndexWriterConfig(customAnalyzer);
        IndexWriter writer = new IndexWriter(dir, config);

        // Use wikipedia parser to parse the wikipedia dump to documents
        ArrayList<Document> documents= WikipediaParser.parserV1("dataset/wiki-example.txt");

        //add each document to the index
        for (Document doc : documents) {
            writer.addDocument(doc);
        }
        //close index writer
        writer.close();
    }

    /**
     * Builds an index for the wikipedia subset.
     * No techniques used
     * @param index_name
     * @throws IOException
     */
    public static void buildIndexVersion1(String index_name) throws IOException {
        int count = 0;
        Directory dir = FSDirectory.open(new File("indicies/" + index_name).toPath());
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);

        //Run WikipediaParse on all files in dataset/wiki-subset-20140602
        File folder = new File("dataset/wiki-subset-20140602");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                ArrayList<Document> documents= WikipediaParser.parserV1(file.getPath());
                for (Document doc : documents) {
                    writer.addDocument(doc);
                    count++;
                    if (count % 10000 == 0) System.out.println(count + " documents added to index");
                }
            }
        }

        writer.close();

    }

    /**
     * Builds an index for the wikipedia subset.
     * Techniques: Stopwords. Porter Stemming.
     * @param index_name
     * @throws IOException
     */
    public static void buildIndexVersion2(String index_name) throws IOException {
        int count = 0;
        Directory dir = FSDirectory.open(new File("indicies/" + index_name).toPath());

        IndexWriterConfig config = new IndexWriterConfig(new EnglishAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);

        //Run WikipediaParse on all files in dataset/wiki-subset-20140602
        File folder = new File("dataset/wiki-subset-20140602");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                ArrayList<Document> documents= WikipediaParser.parserV1(file.getPath());
                for (Document doc : documents) {
                    writer.addDocument(doc);
                    count++;
                    if (count % 10000 == 0) System.out.println(count + " documents added to index");
                }
            }
        }

        writer.close();

    }

    /**
     * Builds an index for the wikipedia subset.
     * Techniques: Stopwords. Lemmatization.
     * @param index_name
     * @throws IOException
     */
    public static void buildIndexVersion3(String index_name) throws IOException {
        int count = 0;
        Directory dir = FSDirectory.open(new File("indicies/" + index_name).toPath());


        Analyzer customAnalyzer = CustomAnalyzer.builder()
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop")
            .build();


        IndexWriterConfig config = new IndexWriterConfig(customAnalyzer);
        IndexWriter writer = new IndexWriter(dir, config);

        //Run WikipediaParse on all files in dataset/wiki-subset-20140602
        File folder = new File("dataset/wiki-subset-20140602");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                ArrayList<Document> documents= WikipediaParser.parserV1(file.getPath());
                for (Document doc : documents) {
                    writer.addDocument(doc);
                    count++;
                    if (count % 10000 == 0) System.out.println(count + " documents added to index");
                }
            }
        }

        writer.close();

    }

    
    
}
