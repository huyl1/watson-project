package com.watson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class Version1Build {
    @Test
    public void buildIndex() throws IOException {
        Path dir = Paths.get("indicies/V1");
        if (Files.exists(dir)) {
            File f = new File("indicies/V1");
            deleteDirectory(f);
            f.delete();
        }
        IndexBuilder.buildIndexVersion1("V1");
    }

    private void deleteDirectory(File f) {
        for (File subFile : f.listFiles()) {
            subFile.delete();
        }
    }
}
