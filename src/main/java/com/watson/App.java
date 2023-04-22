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

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // SearchEngine engine = new SearchEngine("V1");
        // ArrayList<Document> documents = engine.searchV1("Daniel Hertzberg & James B. Stewart of this paper shared a 1988 Pulitzer for their stories about insider trading", 10);
        // for (Document doc : documents) {
        //     System.out.println(doc.get("title"));
        // }

        Analyzer v2Analyzer = new EnglishAnalyzer();

        Analyzer v3Analyzer = CustomAnalyzer.builder()
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop")
            .addTokenFilter(OpenNLPLemmatizerFilterFactory.class, "dictionary", "en-lemmatizer.dict", "lemmatizerModel", "en-lemmatizer.bin")
            .build();

        ArrayList<Document> documents = WikipediaParser.parserV1("dataset/wiki-example.txt");
        String example = documents.get(1).get("content");
        System.out.println(tokenizeString(v3Analyzer, example));
        System.out.println(tokenizeString(v2Analyzer, example));
    }

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