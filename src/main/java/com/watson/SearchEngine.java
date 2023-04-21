package com.watson;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;

public class SearchEngine {
    private IndexSearcher searcher;
    private QueryParser parser;
    public SearchEngine(String index_name) throws IOException {
        searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File("indicies/" + index_name).toPath())));
        parser = new QueryParser("content", new StandardAnalyzer());
    }

    private TopDocs performSearch(String query, int n) throws Exception {
        return searcher.search(parser.parse(query), n);
    }

    public ArrayList<Document> search(String query, int n) throws Exception {
        TopDocs results = performSearch(query, n);
        ArrayList<Document> documents = new ArrayList<Document>();
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(searcher.doc(scoreDoc.doc));
        }
        return documents;
    }
}
