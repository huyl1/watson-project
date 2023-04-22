package com.watson;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.opennlp.OpenNLPLemmatizerFilterFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;

public class SearchEngine {
    private IndexSearcher searcher;
    private Analyzer analyzerV1;
    private Analyzer analyzerV2;
    private Analyzer analyzerV3;
    public SearchEngine(String index_name) throws IOException {
        searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File("indicies/" + index_name).toPath())));

        analyzerV1 = new StandardAnalyzer();
        analyzerV2 = new EnglishAnalyzer();
        analyzerV3 = CustomAnalyzer.builder()
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop")
            .addTokenFilter("englishPossessive")
            .addTokenFilter(OpenNLPLemmatizerFilterFactory.class, "dictionary", "en-lemmatizer.dict", "lemmatizerModel", "en-lemmatizer.bin")
            .build();
        
        }

    public ArrayList<Document> searchV1(String query, int n) throws Exception {
        QueryParser parser = new QueryParser("content", analyzerV1);
        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<Document> documents = new ArrayList<Document>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(searcher.doc(scoreDoc.doc));
        }
        return documents;
    }

    public ArrayList<ScoreDoc> searchV1Scores(String query, int n) throws Exception {
        QueryParser parser = new QueryParser("content", analyzerV1);
        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<ScoreDoc> documents = new ArrayList<ScoreDoc>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(scoreDoc);
        }
        return documents;
    }

    public ArrayList<Document> searchV2(String query, int n) throws Exception {
        QueryParser parser = new QueryParser("content", analyzerV2);

        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<Document> documents = new ArrayList<Document>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(searcher.doc(scoreDoc.doc));
        }
        return documents;
    }

    public ArrayList<ScoreDoc> searchV2Scores(String query, int n) throws Exception {
        QueryParser parser = new QueryParser("content", analyzerV2);

        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<ScoreDoc> documents = new ArrayList<ScoreDoc>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(scoreDoc);
        }
        return documents;
    }

    public ArrayList<Document> searchV3(String query, int n) throws Exception {
        QueryParser parser = new QueryParser("content", analyzerV3);

        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<Document> documents = new ArrayList<Document>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(searcher.doc(scoreDoc.doc));
        }
        return documents;
    }

    public ArrayList<ScoreDoc> searchV3Scores(String query, int n) throws Exception {
        QueryParser parser = new QueryParser("content", analyzerV3);

        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<ScoreDoc> documents = new ArrayList<ScoreDoc>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(scoreDoc);
        }
        return documents;
    }
}
