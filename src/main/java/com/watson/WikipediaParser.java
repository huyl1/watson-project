package com.watson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

/*  Convert articles in Wikipedia files to Lucene documents. Document is the intermediate object. */
public class WikipediaParser {
    public static void main(String[] args) {
        ArrayList<Document> articles = parse("dataset/wiki-example.txt");
        //print first 3 articles
        for (int i = 0; i < 3; i++) {
            System.out.println(articles.get(i).get("title"));
            System.out.println(articles.get(i).get("content"));
        }
    }

    /**
     * Parse a Wikipedia dump file and return a list of Document objects. 
     * Each page starts with its title, encased in double 
     * square brackets. For example, BBC’s page starts with “[[BBC]]”
     * @param file_name
     */
    public static ArrayList<Document> parse(String file_name) {
        ArrayList<Document> articles = new ArrayList<>();
        //open file_name
        //read line by line
        try {
            File file = new File(file_name);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Document buffer = null;
            String content_buffer = null;

            while ((line = br.readLine()) != null) {
                //if line starts with [[ and end with ]], then it is a title
                //create a new Document object
                //containing the title, and the content until the next title
                if (line.startsWith("[[") && line.endsWith("]]")) {
                    if (buffer != null) {
                        //clean the content
                        content_buffer = content_cleaner(content_buffer);
                        //make it ascii
                        content_buffer = Normalizer.normalize(content_buffer, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", ""); 
                        buffer.add(new TextField("content", content_buffer, Field.Store.NO));
                        articles.add(buffer);
                    }
                    buffer = new Document();
                    //title the document with the title
                    //initialize a buffer for the document
                    buffer.add(new StringField("title", line.substring(2, line.length() - 2), Field.Store.YES));
                    content_buffer = "";
                } else {
                    //if line does not start with [[ and end with ]], then it is a content
                    //ignore the line if starts with "CATEGORIES:" (we might want to use this later)
                    //ignore lines that starts with ==
                    if (!line.startsWith("CATEGORIES:") && !line.startsWith("==")) {
                        content_buffer += line + " ";
                    }
                    //if the line is ==See also==, then add a marker to the content buffer, so
                    //we can remove everything after this marker later
                    if (line.startsWith("==See also==")) {
                        content_buffer += " ==See also== ";
                    }
                }

            }
            br.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }

        return articles;
    }

    private static String content_cleaner(String content) {
        //first make all whitespace characters into a single space
        content = content.replaceAll("\\s+", " ");
        //remove everything after "==See also=="
        content = content.split("==See also==")[0];

        //REGEX doesn't do that well, but this is good enough
        //remove all ref tags
        content = content.replaceAll("\\[ref\\].*?\\[/ref\\]", "");
        //remove all tpl tags
        content = content.replaceAll("\\[tpl\\].*?\\[/tpl\\]", "");
        //remove all tokens starting with a pipe
        content = content.replaceAll("\\|.*?\\]", "]");

        return content;
    }


}
