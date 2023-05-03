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

/**
 * This class is used to parse the Wikipedia dump file and return a list of
 * Lucene Document objects. Each Document object contains two fields: title and
 * content. The title field is the title of the Wikipedia article, and the
 * content field is the content of the Wikipedia article. The content field is not
 * saved after the index is built.
 */
public class WikipediaParser {

    /**
     * Parse a Wikipedia dump file and return a list of Document objects.
     * The first parser version removes all categories, links, and references,
     * and everything after "See also" section. All section headers are also removed.
     * @param file_name
     */
    public static ArrayList<Document> parserV1(String file_name) {
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
                        content_buffer = markUpRemover(content_buffer);
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

    /**
     * The second parser version is the same as V1, but instead of removing the references,
     * it tries to extract the text from the references.
     * @param file_name
     * @return
     */
    public static ArrayList<Document> parserV2(String file_name) {
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
                        content_buffer = customRemover(content_buffer);
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
                    //ignore lines that starts with ==
                    if (!line.startsWith("CATEGORIES:") && !line.startsWith("==")) {
                        content_buffer += line + " ";
                    }
                }

            }
            br.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }

        return articles;
    }

    /**
     * Same as V2, but does not extract text from references.
     * @param file_name
     * @return
     */
    public static ArrayList<Document> parserV3(String file_name) {
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
                        content_buffer = markUpRemover(content_buffer);
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
                    //ignore lines that starts with ==
                    if (!line.startsWith("==")) {
                        content_buffer += line + " ";
                    }
                }

            }
            // FOR THE LAST DOCUMENT IN FILE
            //clean the content
            content_buffer = markUpRemover(content_buffer);
            //make it ascii
            content_buffer = Normalizer.normalize(content_buffer, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", ""); 
            buffer.add(new TextField("content", content_buffer, Field.Store.NO));
            articles.add(buffer);
            br.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }

        return articles;
    }

    /**
     * The optimal parser version. It removes the references, and extracts the text from the references.
     * Keeps as much text from the article as possible.
     * @param file_name
     * @return
     */
    public static ArrayList<Document> parserV4(String file_name) {
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
                        content_buffer = customRemover(content_buffer);
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
                    //ignore lines that starts with ==
                    if (!line.startsWith("==")) {
                        content_buffer += line + " ";
                    }
                }

            }
            br.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }

        return articles;
    }
    
    /**
     * Removes all the markup tags from the content.
     * @param content
     * @return
     */
    private static String markUpRemover(String content) {
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
    
    /**
     * Removes all the markup tags from the content, but extract
     * titles from references.
     * @param content
     * @return
     */
    private static String customRemover(String content) {
        //first make all whitespace characters into a single space
        content = content.replaceAll("\\s+", " ");
        //remove tags like |name=
        content = content.replaceAll("\\|.*?=", " ");
        //remove square tags eg [tpl]
        content = content.replaceAll("\\[.*?\\]", " ");
        //remove all web links
        content = content.replaceAll("http.*?\\s", " ");

        return content;
    }


}
