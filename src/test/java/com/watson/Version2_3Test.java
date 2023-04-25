package com.watson;

import static org.junit.Assert.assertTrue;

import org.apache.lucene.document.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class Version2_3Test 
{
    // To test different versions, change string 'version', and 'searchVX' to desired version 
    @Test
    public void testQuestions() throws Exception {
        // Initializing vars we're gonna use
        int i = 0;                  // iterating through 
        int cap = 1;                // how many doc titles we're skimming through
        int failed = 0;
        int passed = 0;
        int[] mrr = new int[cap];
        Arrays.fill(mrr, 0);
        
        // Change this to change version
        String version = "V2_3";
        Scanner reader = new Scanner(new File("dataset/questions.txt"));

        // To store our current test case topic/query/answer
        String topic = "";
        String query = "";
        String answer = "";
        String docTitle = "";

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
                SearchEngine engine = new SearchEngine(version);
                try {
                    String queryString = engine.queryBuilderV2(query, topic);
                    ArrayList<Document> documents = engine.searchV2_2(queryString, cap);
                    for (Document doc : documents) {
                        // In the case that there are multiple answers
                        if (answer.contains("|")) {
                            for (int k = 0; k < answer.split("|").length; k++) {
                                if (doc.get("title").toString().toLowerCase().equals(answer.split("|")[k].toLowerCase())) {
                                    if (flag == false) {passed += 1; mrr[j] += 1; docTitle = doc.get("title").toString();}
                                    flag = true;
                                }
                            }
                        }
                        else if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                            if (flag == false) {passed += 1; mrr[j] += 1; docTitle = doc.get("title").toString();}
                            flag = true;
                        }
                        j += 1;
                        if (flag) {break;}
                    }
                } catch(Exception p) {}
                try {assertTrue(topic + " " + answer, flag); 
                    System.out.println("TEST CASE " + ((i / 4) + 1) + "\n" + answer + "\t" + docTitle + "\n" + "TOP " + j + "\n");
                } 
                catch(AssertionError e) {failed += 1;}
            }
            i += 1;
        }
        // Calculates MRR Score
        double mrrScore = 0.0;
        for (int j = 0; j < mrr.length; j++) {double temp = (1.0 / (j + 1.0)) * mrr[j]; mrrScore += temp;}
        mrrScore = (mrrScore / 100.0);
        System.out.println("AMOUNT PASSED: " + passed);
        System.out.println("AMOUNT FAILED: " + failed);
        System.out.println("MRR SCORE (TOP " + cap + "): " + mrrScore);
    }
}
