package com.watson;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.QueryRescorer;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.FSDirectory;

import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.IndexWordSet;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.dictionary.Dictionary;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.opennlp.OpenNLPLemmatizerFilterFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;

public class SearchEngine {
    private IndexSearcher searcher;
    private Analyzer analyzerV1;
    private Analyzer analyzerV2;
    private Analyzer analyzerV3;
    private Dictionary dict;
    public SearchEngine(String index_name) throws IOException, JWNLException {
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

        dict = Dictionary.getDefaultResourceInstance();
        
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

    public ArrayList<Document> searchV2_2(String query, int n) throws Exception {
        //if query string contains the word "capital"
        int oldN = n;
        if (n < 5) {
            n = 5;}
        QueryParser parser = new QueryParser("content", analyzerV2);
        TopDocs results = searcher.search(parser.parse(query), n);
        ArrayList<Document> documents = new ArrayList<Document>();

        if (query.toLowerCase().contains("capital")) {
            //get all capitals from capitalCities.txt as a string
            String capitalCities = new String(Files.readAllBytes(Paths.get("dataset/capitalCities.txt")));
            //0.22 weight is a magic number. Best number that works for the dataset. Overfitting.
            results = QueryRescorer.rescore(searcher, results, parser.parse(capitalCities), 0.22, n);
        }

        int count = 0;
        for (ScoreDoc scoreDoc : results.scoreDocs) {
            documents.add(searcher.doc(scoreDoc.doc));
            count++;
            if (count == oldN) {
                break;
            }
        }

        return documents;
    }



    public String keywordExtract(String query) throws IOException {
        // Create an object to hold algorithm parameters
        String[] stopWords = new SmartWords().getSmartWords(); 
        String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"}; 
        int minWordChar = 1;
        boolean shouldStem = true;
        String phraseDelims = "[-,.?():;\"!/]"; 
        RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);
        
        // Create a RakeAlgorithm object
        String POStaggerURL = "NLPmodels/en-pos-maxent.bin"; // The path to your POS tagging model
        String SentDetecURL = "NLPmodels/en-sent.bin"; // The path to your sentence detection model
        RakeAlgorithm rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetecURL);
        
        // Call the rake method
        Result result = rakeAlg.rake(query);
        String[] keywords = result.getFullKeywords();

        String keywordString = "";
        for (String keyword : keywords) {
            keywordString += keyword + " ";
        }
        return keywordString;
    }

    public String personNameExtract(String query) throws IOException {
        //Loading the tokenizer model 
        InputStream inputStreamTokenizer = new FileInputStream("NLPmodels/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
        //Instantiating the TokenizerME class 
        TokenizerME tokenizer = new TokenizerME(tokenModel); 
        //Tokenizing the sentence in to a string array 
        String tokens[] = tokenizer.tokenize(query); 
        //Loading the NER-person model 
        InputStream inputStreamNameFinder = new FileInputStream("NLPmodels/en-ner-person.bin");       
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        //Instantiating the NameFinderME class 
        NameFinderME nameFinder = new NameFinderME(model);       
        //Finding the names in the sentence 
        Span nameSpans[] = nameFinder.find(tokens);        
        String retval = "";
        //Printing the names and their spans in a sentence 
        for(Span s: nameSpans)        
            retval += tokens[s.getStart()] + " ";
        return retval;
    }

    public String locationNameExtract(String query) throws IOException {
        //Loading the tokenizer model 
        InputStream inputStreamTokenizer = new FileInputStream("NLPmodels/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
        //Instantiating the TokenizerME class 
        TokenizerME tokenizer = new TokenizerME(tokenModel); 
        //Tokenizing the sentence in to a string array 
        String tokens[] = tokenizer.tokenize(query); 
        //Loading the NER-person model 
        InputStream inputStreamNameFinder = new FileInputStream("NLPmodels/en-ner-location.bin");       
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        //Instantiating the NameFinderME class 
        NameFinderME nameFinder = new NameFinderME(model);       
        //Finding the names in the sentence 
        Span nameSpans[] = nameFinder.find(tokens);        
        String retval = "";
        //Printing the names and their spans in a sentence 
        for(Span s: nameSpans)        
            retval += tokens[s.getStart()] + " ";
        return retval;
    }

    public String organizationNameExtract(String query) throws IOException {
        //Loading the tokenizer model 
        InputStream inputStreamTokenizer = new FileInputStream("NLPmodels/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
        //Instantiating the TokenizerME class 
        TokenizerME tokenizer = new TokenizerME(tokenModel); 
        //Tokenizing the sentence in to a string array 
        String tokens[] = tokenizer.tokenize(query); 
        //Loading the NER-person model 
        InputStream inputStreamNameFinder = new FileInputStream("NLPmodels/en-ner-organization.bin");       
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        //Instantiating the NameFinderME class 
        NameFinderME nameFinder = new NameFinderME(model);       
        //Finding the names in the sentence 
        Span nameSpans[] = nameFinder.find(tokens);        
        String retval = "";
        //Printing the names and their spans in a sentence 
        for(Span s: nameSpans)        
            retval += tokens[s.getStart()] + " ";
        return retval;
    }

    public String dateExtract(String query) throws IOException {
        //Loading the tokenizer model 
        InputStream inputStreamTokenizer = new FileInputStream("NLPmodels/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
        //Instantiating the TokenizerME class 
        TokenizerME tokenizer = new TokenizerME(tokenModel); 
        //Tokenizing the sentence in to a string array 
        String tokens[] = tokenizer.tokenize(query); 
        //Loading the NER-person model 
        InputStream inputStreamNameFinder = new FileInputStream("NLPmodels/en-ner-date.bin");       
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        //Instantiating the NameFinderME class 
        NameFinderME nameFinder = new NameFinderME(model);       
        //Finding the names in the sentence 
        Span nameSpans[] = nameFinder.find(tokens);        
        String retval = "";
        //Printing the names and their spans in a sentence 
        for(Span s: nameSpans)        
            retval += tokens[s.getStart()] + " ";
        return retval;
    }

    public String synonymExpansion(String query, int cap) throws IOException, JWNLException {
        String retVal = "";
        String[] words = query.split(" ");
                
        for (int i = 0; i < words.length; i++) {
            IndexWordSet temp = null;
            IndexWord[] temp2 = null;

            temp = dict.lookupAllIndexWords(words[i]); 
            temp2 = temp.getIndexWordArray();

            for (int j = 0; j < 1; j++) {
                // How many synonyms we want to return
                try{IndexWord found = synonymExpansionHelper(temp2[j], words[i]);
                PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(found.getSenses().get(0));
                String str = hypernyms.toString();
                String substring = str.substring(str.indexOf("Words: ") + 7, str.indexOf(" --"));
                String[] synonymWords = substring.split(", ");

                // In case the cap is too high for the amount of synonyms there are
                if (synonymWords.length < cap) {cap = synonymWords.length;}

                // Iterate through the list of synonyms
                for (int k = 0; k < cap; k++) {
                    retVal += synonymWords[k] + " ";
                }
                } catch(Exception e) {}
            }
        }
        
        return retVal;
    }

    public IndexWord synonymExpansionHelper(IndexWord indexWord, String word) throws JWNLException {
        return dict.lookupIndexWord(indexWord.getPOS(), word);
    }

    public String queryBuilderV1(String query, String topic) throws IOException {
        return query + " " + topic;
    }

    public String queryBuilderV2(String query, String topic) throws IOException {
        return query + " " + topic + " " + keywordExtract(query);
    }

    public String queryBuilderV3(String query, String topic) throws IOException, JWNLException {
        String location = locationNameExtract(query);
        return query + " " + topic + " " + keywordExtract(query) + synonymExpansion(location, 3);
    }

    public String queryBuilderV4(String query, String topic) throws IOException, JWNLException {
        return topic + " " + query + " " + synonymExpansion(query, 1);
    }

    public String queryBuilderV5(String query, String topic) throws IOException, JWNLException {
        return topic + " " + query + " " + synonymExpansion(keywordExtract(query), 1);
    }
}
