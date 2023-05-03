package com.watson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.sentenceiterator.CollectionSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;

/**
 * This class is used to train the Word2Vec model on the Wikipedia dataset.
 */
public class Word2VecModel {

    public static void main(String[] args) throws IOException {
        // How many times the model has been saved
        int x = 0;
        VocabCache<VocabWord> cache = new AbstractCache();
        WeightLookupTable<VocabWord> table = new InMemoryLookupTable.Builder<VocabWord>()
                .vectorLength(100)
                .useAdaGrad(false)
                .cache(cache)
                .build();

        //Reading data
        System.out.println("Reading data...");

        File folder = new File("dataset/wiki-subset-20140602");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {

                // HAVE TO CHANGE to either accept Documents OR change parserV4 to return an arraylist of strings
                ArrayList<String> temp = WikipediaParser.parserV4(file);
                String[] temp2 = new String[temp.size()];
                for (int i = 0; i < temp.size(); i++) {temp2[i] = temp.get(i);}

                // For debugging purposes
                // for (int i = 0; i < temp2.length; i++) {System.out.println(temp2[i]);}

                // Properly tokenize data
                Collection<String> sentences = Arrays.asList(temp2);
                SentenceIterator iter =  new CollectionSentenceIterator(sentences);
                DefaultTokenizerFactory t = new DefaultTokenizerFactory();
                t.setTokenPreProcessor(new CommonPreprocessor());

                // If it's the first iterating through the list of files, it will build the model and fit it 
                // based on the data.
                if (x == 0) {
                        Word2Vec vec = new Word2Vec.Builder()
                                .minWordFrequency(5)
                                .iterations(1)
                                .epochs(1)
                                .layerSize(100)
                                .seed(42)
                                .windowSize(5)
                                .iterate(iter)
                                .tokenizerFactory(t)
                                .build();
                        vec.fit();
                        WordVectorSerializer.writeWord2VecModel(vec, "pathToSaveModel.txt");;
                } else {
                        // If the model already exists, then we open it and fit based on current and previous data
                        // It's always building, never replacing
                        Word2Vec vec = WordVectorSerializer.readWord2VecModel("pathToSaveModel.txt");
                        vec.setTokenizerFactory(t);
                        vec.setSentenceIterator(iter);
                        vec.fit();
                }
                System.out.println("Training Model " + x);
                x += 1;
        }
        System.out.println("****************Model finished********************");
    }
}

