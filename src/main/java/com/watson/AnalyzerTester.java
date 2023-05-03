package com.watson;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.opennlp.OpenNLPLemmatizerFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;

/**
 * This class is used to test different analyzers.
 */
public class AnalyzerTester {
    public static void main(String[] args) throws IOException {
        Analyzer v2Analyzer = new EnglishAnalyzer();

        Analyzer v3Analyzer = CustomAnalyzer.builder()
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop")
            .addTokenFilter(OpenNLPLemmatizerFilterFactory.class, "dictionary", "en-lemmatizer.dict", "lemmatizerModel", "en-lemmatizer.bin")
            .build();

        String test_String2 = "The dominant paper in our nation's capital, it's among the top 10 U.S. papers in circulation";
        ArrayList<Document> documents = WikipediaParser.parserV1("dataset/wiki-example.txt");
        String example = documents.get(1).get("content");
        //System.out.println(tokenizeString(v3Analyzer, test_String));
        System.out.println(tokenizeString(v3Analyzer, test_String2));

        
    }

    /**
     * Tokenize a string using a given analyzer.
     * @param analyzer
     * @param string
     * @return
     */
    public static List<String> tokenizeString(Analyzer analyzer, String string) {
        List<String> result = new ArrayList<String>();
        try {
          TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
          stream.reset();
          while (stream.incrementToken()) {
            result.add(stream.getAttribute(CharTermAttribute.class).toString());
          }
        } catch (IOException e) {
          // not thrown b/c we're using a string reader...
          throw new RuntimeException(e);
        }
        return result;
      }
}
