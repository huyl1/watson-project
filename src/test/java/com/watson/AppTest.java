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

    // I'VE INCLUDED AN ALL-IN-ONE TEST AND INDIVIDUAL TEST CASES
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
                while (flag != true && j < 10) {
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
                try {assertTrue(topic + " " + answer + " " + docFound, flag); 
                    System.out.println("TEST CASE " + ((i / 4) + 1) + "\n" + topic + "\n" + answer + "\n" + docFound + "\n" + "TOP " + j + "\n");} 
                catch(AssertionError e) {failed += 1;}
            }
            i += 1;
        }
        System.out.println("AMOUNT PASSED: " + passed);
        System.out.println("AMOUNT FAILED: " + failed);
    }

    @Test
    public void test1() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The dominant paper in our nation's capital, it's among the top 10 U.S. papers in circulation";
        String answer = "The Washington Post";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 1", flag);
    }

    @Test
    public void test2() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The practice of pre-authorizing presidential use of force dates to a 1955 resolution re: this island near mainland China";
        String answer = "Taiwan";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 2", flag);
    }

    @Test
    public void test3() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Daniel Hertzberg & James B. Stewart of this paper shared a 1988 Pulitzer for their stories about insider trading";
        String answer = "The Wall Street Journal";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 3", flag);
    }

    @Test
    public void test4() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Song that says, \"you make me smile with my heart; your looks are laughable, unphotographable\"";
        String answer = "My Funny Valentine";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 4", flag);
    }

    @Test
    public void test5() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 2011 bell ringers for this charity started accepting digital donations to its red kettle";
        String answer = "The Salvation Army|Salvation Army";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 5", flag);
    }

    @Test
    public void test6() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Naples Museum of Art";
        String answer = "Florida";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 6", flag);
    }

    @Test
    public void test7() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This Italian painter depicted the \"Adoration of the Golden Calf\"";
        String answer = "Tintoretto";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 7", flag);
    }

    @Test
    public void test8() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This woman who won consecutive heptathlons at the Olympics went to UCLA on a basketball scholarship";
        String answer = "Jackie Joyner-Kersee";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 8", flag);
    }

    @Test
    public void test9() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Originally this club's emblem was a wagon wheel; now it's a gearwheel with 24 cogs & 6 spokes";
        String answer = "Rotary International";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 9", flag);
    }

    @Test
    public void test10() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Several bridges, including El Tahrir, cross the Nile in this capital";
        String answer = "Cairo";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 10", flag);
    }

    @Test
    public void test11() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "After the fall of France in 1940, this general told his country, \"France has lost a battle. But France has not lost the war\"";
        String answer = "Charles de Gaulle|de Gaulle";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 11", flag);
    }

    @Test
    public void test12() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Taft Museum of Art";
        String answer = "Ohio";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 12", flag);
    }

    @Test
    public void test13() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The mast from the USS Maine is part of the memorial to the ship & crew at this national cemetery";
        String answer = "Arlington National Cemetery|Arlington Cemetery";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 13", flag);
    }

    @Test
    public void test14() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 2009: Joker on film";
        String answer = "Heath Ledger";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 14", flag);
    }

    @Test
    public void test15() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "It was the peninsula fought over in the peninsular war of 1808 to 1814";
        String answer = "Iberia|Iberian Peninsula";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 15", flag);
    }

    @Test
    public void test16() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 1980 China founded a center for these cute creatures in its bamboo-rich Wolong Nature Preserve";
        String answer = "Panda|Giant panda";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 16", flag);
    }

    @Test
    public void test17() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1988: \"Father Figure\"";
        String answer = "George Michael";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 17", flag);
    }

    @Test
    public void test18() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In an essay defending this 2011 film, Myrlie Evers-Williams said, \"My mother was\" this film \"& so was her mother\"";
        String answer = "The Help";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 18", flag);
    }

    @Test
    public void test19() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Father Michael McGivney founded this fraternal society for Catholic laymen in 1882";
        String answer = "Knights of Columbus";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 19", flag);
    }

    @Test
    public void test20() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Early projects of the WWF, this organization, included work with the bald eagle & the red wolf";
        String answer = "World Wide Fund|World Wide Fund for Nature";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 20", flag);
    }

    @Test
    public void test21() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Indonesia's largest lizard, it's protected from poachers, though we wish it could breathe fire to do the job itself";
        String answer = "Komodo dragon";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 21", flag);
    }

    @Test
    public void test22() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Nov. 28, 1929! This man & his chief pilot Bernt Balchen fly to South Pole! Yowza! You'll be an admirable admiral, sir!";
        String answer = "Richard Byrd|Richard E. Byrd";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 22", flag);
    }

    @Test
    public void test23() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "On May 5, 1878 Alice Chambers was the last person buried in this Dodge City, Kansas cemetery";
        String answer = "Boot Hill";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 23", flag);
    }

    @Test
    public void test24() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Royal Palace grounds feature a statue of King Norodom, who in the late 1800s was compelled to first put his country under the control of this European power; of course, it was sculpted in that country";
        String answer = "France";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 24", flag);
    }

    @Test
    public void test25() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In the 400s B.C. this Chinese philosopher went into exile for 12 years";
        String answer = "Confucius";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 25", flag);
    }

    @Test
    public void test26() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Bessie Coleman, the first black woman licensed as a pilot, landed a street named in her honor at this Chicago airport";
        String answer = "O'Hare|O'Hare International Airport";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 26", flag);
    }

    @Test
    public void test27() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Ammonites held sway in this Mideast country in the 1200s B.C. & the capital is named for them";
        String answer = "Jordan";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 27", flag);
    }

    @Test
    public void test28() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "\"The Sum of All Fears\"; he also won a screenwriting Oscar for \"Good Will Hunting\"";
        String answer = "Ben Affleck";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 28", flag);
    }

    @Test
    public void test29() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "One of the N.Y. Times' headlines on this landmark 1973 Supreme Court decision was \"Cardinals shocked\"";
        String answer = "Roe v. Wade";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 29", flag);
    }

    @Test
    public void test30() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "France's Philip IV--known as \"The Fair\"--had Jacques De Molay, the last Grand Master of this order, burned in 1314";
        String answer = "Knights Templar";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 30", flag);
    }

    @Test
    public void test31() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Georgia O'Keeffe Museum";
        String answer = "New Mexico";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 31", flag);
    }

    @Test
    public void test32() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The name of this largest Moroccan city combines 2 Spanish words";
        String answer = "Casablanca";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 32", flag);
    }

    @Test
    public void test33() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Jell-O";
        String answer = "Kraft Foods";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 33", flag);
    }

    @Test
    public void test34() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "2011: Chicago mayor Tom Kane";
        String answer = "Kelsey Grammer";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 34", flag);
    }

    @Test
    public void test35() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Title residence of Otter, Flounder, Pinto & Bluto in a 1978 comedy";
        String answer = "Animal House";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 35", flag);
    }

    @Test
    public void test36() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Neurobiologist Amy Farrah Fowler on \"The Big Bang Theory\", in real life she has a Ph.D. in neuroscience from UCLA";
        String answer = "Mayim Bialik";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 36", flag);
    }

    @Test
    public void test37() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In \"The Deadlocked Election of 1800\", James R. Sharp outlines the fall of this dueling vice president";
        String answer = "Aaron Burr";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 37", flag);
    }

    @Test
    public void test38() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "He served in the KGB before becoming president & then prime minister of Russia";
        String answer = "Vladimir Putin|Putin";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 38", flag);
    }

    @Test
    public void test39() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "When asked to describe herself, she says first & foremost, she is Malia & Sasha's mom";
        String answer = "Michelle Obama";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 39", flag);
    }

    @Test
    public void test40() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "She wrote, \"My candle burns at both ends... but, ah, my foes, and oh, my friends--it gives a lovely light\"";
        String answer = "Edna St. Vincent Millay";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 40", flag);
    }

    @Test
    public void test41() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In this Finnish city, the Lutheran Cathedral, also known as Tuomiokirkko";
        String answer = "Helsinki";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 41", flag);
    }

    @Test
    public void test42() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Milton Bradley games";
        String answer = "Hasbro";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 42", flag);
    }

    @Test
    public void test43() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Kentucky & Virginia resolutions were passed to protest these controversial 1798 acts of Congress";
        String answer = "The Alien and Sedition Acts";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 43", flag);
    }

    @Test
    public void test44() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1983: \"Beat It\"";
        String answer = "Michael Jackson";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 44", flag);
    }

    @Test
    public void test45() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 2009: Sookie Stackhouse";
        String answer = "Anna Paquin";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 45", flag);
    }

    @Test
    public void test46() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This member of the Nixon & Ford cabinets was born in Furth, Germany in 1923";
        String answer = "Henry Kissinger";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 46", flag);
    }

    @Test
    public void test47() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The High Kirk of St. Giles, where John Knox was minister";
        String answer = "Edinburgh";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 47", flag);
    }

    @Test
    public void test48() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "For the brief time he attended, he was a rebel with a cause, even landing a lead role in a 1950 stage production";
        String answer = "James Dean";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 48", flag);
    }

    @Test
    public void test49() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Fisher-Price toys";
        String answer = "Mattel";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 49", flag);
    }

    @Test
    public void test50() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In a 1959 American kitchen exhibit in Moscow, he told Khrushchev, \"In America, we like to make life easier for women\"";
        String answer = "Richard Nixon|Nixon";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 50", flag);
    }

    @Test
    public void test51() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "One of his \"Tales of a Wayside Inn\" begins, \"Listen, my children, and you shall hear of the midnight ride of Paul Revere\"";
        String answer = "Henry Wadsworth Longfellow";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 51", flag);
    }

    @Test
    public void test52() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This bestseller about problems on the McCain-Palin ticket became an HBO movie with Julianne Moore";
        String answer = "Game Change";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 52", flag);
    }

    @Test
    public void test53() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "A 2-part episode of \"JAG\" introduced this Mark Harmon drama";
        String answer = "NCIS";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 53", flag);
    }

    @Test
    public void test54() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This port is the southernmost of South Africa's 3 capitals";
        String answer = "Cape Town";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 54", flag);
    }

    @Test
    public void test55() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Keats was quoting this Edmund Spenser poem when he told Shelley to \"'load every rift' of your subject with ore\"";
        String answer = "The Faerie Queene";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 55", flag);
    }

    @Test
    public void test56() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In an 1819 letter Keats wrote that this lord & poet \"cuts a figure, but he is not figurative\"";
        String answer = "Lord Byron";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 56", flag);
    }

    @Test
    public void test57() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This clear Greek liqueur is quite potent, so it's usually mixed with water, which turns it white & cloudy";
        String answer = "Ouzo";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 57", flag);
    }

    @Test
    public void test58() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Feb. 1, National Freedom Day, is the date in 1865 when a resolution sent the states an amendment ending this";
        String answer = "Slavery|Slavery in the United States";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 58", flag);
    }

    @Test
    public void test59() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This person is the queen's representative in Canada; currently the office is held by David Johnston";
        String answer = "Governor General of Canada";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 59", flag);
    }

    @Test
    public void test60() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "He earned the \"fifth Beatle\" nickname by producing all of the Beatles' albums";
        String answer = "George Martin";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 60", flag);
    }

    @Test
    public void test61() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Early in their careers, Mark Twain & Bret Harte wrote pieces for this California city's Chronicle";
        String answer = "San Francisco";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 61", flag);
    }

    @Test
    public void test62() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Large specimens of this marsupial can leap over barriers 6 feet high";
        String answer = "Kangaroo";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 62", flag);
    }

    @Test
    public void test63() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Because it's cured & stored in brine, this crumbly white cheese made from sheep's milk is often referred to as pickled cheese";
        String answer = "Feta";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 63", flag);
    }

    @Test
    public void test64() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1927! Gene Tunney takes a long count in the squared circle but rises to defeat this \"Manassa Mauler\"! Howzabout that!";
        String answer = "Jack Dempsey";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 64", flag);
    }

    @Test
    public void test65() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Italian for \"leader\", it was especially applied to Benito Mussolini";
        String answer = "Duce";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 65", flag);
    }

    @Test
    public void test66() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Kalamazoo Institute of Arts";
        String answer = "Michigan";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 66", flag);
    }

    @Test
    public void test67() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Sun Valley Center for the Arts";
        String answer = "Idaho";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 67", flag);
    }

    @Test
    public void test68() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "You can't mention this shortstop without mentioning his double-play associates Evers & Chance";
        String answer = "Joe Tinker";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 68", flag);
    }

    @Test
    public void test69() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 1840 Horace Greeley began publishing \"The Log Cabin\", a weekly campaign paper in support of this Whig candidate";
        String answer = "William Henry Harrison";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 69", flag);
    }

    @Test
    public void test70() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Pierre Cauchon, Bishop of Beauvais, presided over the trial of this woman who went up in smoke May 30, 1431";
        String answer = "Joan of Arc|Jeanne d'Arc";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 70", flag);
    }

    @Test
    public void test71() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This Wisconsin city claims to have built the USA's only granite dome";
        String answer = "Madison";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 71", flag);
    }

    @Test
    public void test72() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This Georgia paper is known as the AJC for short";
        String answer = "The Atlanta Journal-Constitution";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 72", flag);
    }

    @Test
    public void test73() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Wooden 2-story verandas in this Liberian capital are an architectural link to the U.S. south";
        String answer = "Monrovia";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 73", flag);
    }

    @Test
    public void test74() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This New Orleans venue reopened Sept. 25, 2006";
        String answer = "Mercedes-Benz Superdome|The Superdome";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 74", flag);
    }

    @Test
    public void test75() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "\"The Hunt for Red October\"; he went more comedic as Jack Donaghy on \"30 Rock\"";
        String answer = "Alec Baldwin";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 75", flag);
    }

    @Test
    public void test76() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Rita Dove titled a collection of poems \"On the Bus with\" this woman";
        String answer = "Rosa Parks";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 76", flag);
    }

    @Test
    public void test77() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "\"Patriot Games\"; he's had other iconic roles, in space & underground";
        String answer = "Harrison Ford";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 77", flag);
    }

    @Test
    public void test78() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This sacred structure dates from the late 600's A.D.";
        String answer = "Dome of the Rock";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 78", flag);
    }

    @Test
    public void test79() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1988: \"Man In The Mirror\"";
        String answer = "Michael Jackson";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 79", flag);
    }

    @Test
    public void test80() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Matthias Church, or Matyas Templom, where Franz Joseph was crowned in 1867";
        String answer = "Budapest";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 80", flag);
    }

    @Test
    public void test81() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Attending UCLA in the '60s, he was no \"Meathead\", he just played one later on television";
        String answer = "Rob Reiner";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 81", flag);
    }

    @Test
    public void test82() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Kinch, Carter & LeBeau were all residents of Stalag 13 on this TV show";
        String answer = "Hogan's Heroes";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 82", flag);
    }

    @Test
    public void test83() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "News flash! This less-than-yappy pappy is sixth veep to be nation's top dog after chief takes deep sleep!";
        String answer = "Calvin Coolidge";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 83", flag);
    }

    @Test
    public void test84() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 2001: The president of the United States on television";
        String answer = "Martin Sheen";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 84", flag);
    }

    @Test
    public void test85() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1989: \"Miss You Much\"";
        String answer = "Janet Jackson";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 85", flag);
    }

    @Test
    public void test86() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1922: It's the end of an empire! This empire, in fact! After 600 years, it's goodbye, this, hello, Turkish Republic!";
        String answer = "Ottoman Empire";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 86", flag);
    }

    @Test
    public void test87() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Crest toothpaste";
        String answer = "Procter & Gamble";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 87", flag);
    }

    @Test
    public void test88() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 1888 this Chancellor told the Reichstag, \"we Germans fear God, but nothing else in the world\"";
        String answer = "Otto von Bismarck|Von Bismarck";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 88", flag);
    }

    @Test
    public void test89() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 1787 he signed his first published poem \"Axiologus\"; axio- is from the Greek for \"worth\"";
        String answer = "William Wordsworth";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 89", flag);
    }

    @Test
    public void test90() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Not to be confused with karma, krama is a popular accessory sold in cambodia; the word means \"scarf\" in this national language of Cambodia";
        String answer = "Khmer language";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 90", flag);
    }

    @Test
    public void test91() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Phnom Penh's notorious gridlock is circumvented by the nimble tuk-tuk, a motorized taxi that's also known as an auto this, a similar Asian conveyance.";
        String answer = "Rickshaw";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 91", flag);
    }

    @Test
    public void test92() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "1980: \"Rock With You\"";
        String answer = "Michael Jackson";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 92", flag);
    }

    @Test
    public void test93() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The Pulitzer-winning \"The Making of the President 1960\" covered this man's successful presidential campaign";
        String answer = "JFK|John F. Kennedy";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 93", flag);
    }

    @Test
    public void test94() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 1843 Isaac Dittenhoefer became the first pres. of this Jewish club whose name means \"children of the covenant\"";
        String answer = "B'nai B'rith";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 94", flag);
    }

    @Test
    public void test95() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Don Knotts took over from Norman Fell as the resident landlord on this sitcom";
        String answer = "Three's Company";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 95", flag);
    }

    @Test
    public void test96() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "U.N. Res. 242 supports \"secure and recognized boundaries\" for Israel & neighbors following this June 1967 war";
        String answer = "The Six Day War";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 96", flag);
    }

    @Test
    public void test97() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "This blonde beauty who reprised her role as Amanda on the new \"Melrose Place\" was a psychology major";
        String answer = "Heather Locklear";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 97", flag);
    }

    @Test
    public void test98() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "The name of this dish of marinated lamb, skewered & grilled, comes from the Greek for \"skewer\" & also starts with \"s\"";
        String answer = "Souvlaki";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 98", flag);
    }

    @Test
    public void test99() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "Post-it notes";
        String answer = "3M";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 99", flag);
    }

    @Test
    public void test100() throws Exception {
        // initializing variables we're gonna use
        int j = 0;
        boolean flag = false;

        File dir = new File("indicies");
        File[] directoryListing = dir.listFiles();

        // To store our current test case topic/query/answer
        String query = "In 2010: As Sherlock Holmes on film";
        String answer = "Robert Downey, Jr.";
        // String docFound = "";

        while (flag != true && j < 10) {
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    SearchEngine engine = new SearchEngine(child.toString().split("\\\\")[1]);
                    try {
                        ArrayList<Document> documents = engine.search(query, j + 1);
                        for (Document doc : documents) {
                            if (doc.get("title").toString().toLowerCase().equals(answer.toLowerCase())) {
                                flag = true;
                                // docFound = child.toString().split("\\\\")[1];
                            }
                        }
                    } catch(Exception p) {}
                }
            }
            j += 1;
        }
        assertTrue("TEST CASE 100", flag);
    }
}
