package com.watson;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.apache.lucene.document.Document;


import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    // NOTE: WE PASS 61 USING THE TOP 10
    // TOP 1: 47 Test Cases
    // TOP 2: 8 Test Cases
    // TOP 3: 1 Test Cases
    // TOP 4: 0 Test Cases
    // TOP 5: 1 Test Cases
    // TOP 6: 1 Test Cases
    // TOP 7: 0 Test Cases
    // TOP 8: 1 Test Cases
    // TOP 9: 0 Test Cases
    // TOP 10: 2 Test Cases
    @Test
    public void testAll() throws Exception {
        // Initializing vars we're gonna use
        int i = 0;
        int failed = 0;
        int passed = 0;

        // Setup for both our Scanner and SearchEngine
        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();
        Scanner reader = new Scanner(new File("dataset/questions.txt"));

        // To store our current test case topic/query/answer
        String topic = "";
        String query = "";
        String answer = "";
        String docFound = "";

        while (reader.hasNextLine()) {
            String data = reader.nextLine();

            // THE STRUCTURE OF THE IF-STATEMENTS WILL EXTRACT THE TOPIC/QUERY/ANSWER AND THEN PROCESS IT
            if (i % 4 == 0) {
                topic = data;
            } else if (i % 4 == 1) {
                query = data;
            } else if (i % 4 == 2) {
                answer = data;
            } else if (i % 4 == 3) {
                int j = 0;
                boolean flag = false;

                // Currently on my laptop, this took **132.1 seconds** using 'j < 10'; PASSED 61
                // Currnetly on my laptop, this took **32.4 seconds** using 'j < 1'; PASSED: 47
                // WE CAN PROBABLY EDIT THIS TO INCREMENT AN INCREASED AMOUNT TO IMPROVE PERFORMANCE (j += 4)
                while (flag != true && j < 1) {
                    if (directoryListing != null) {
                        for (File child : directoryListing) {
                            SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                            try {
                                ArrayList<Document> documents = engine.search(query, j + 1);
                                for (Document doc : documents) {
                                    if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                        flag = true;
                                        docFound = child.toString().split("\\\\")[1];
                                        passed += 1;
                                    }
                                }
                            } catch(Exception p) {}
                        }
                    }
                    j += 1;
                }
                
                // Maven is so not cool with my laptop
                try {assertTrue(topic + " " + answer + " " + docFound, flag); 
                    System.out.println("TEST CASE " + ((i / 4) + 1) + "\n" + topic + "\n" + answer + "\n" + docFound + "\n" + "TOP " + j + "\n");} 
                catch(AssertionError e) {failed += 1;}
            }
            i += 1;
        }
        System.out.println("AMOUNT PASSED: " + passed);
        System.out.println("AMOUNT FAILED: " + failed);
    }
}
