package com.watson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class Version3Build {
    @Test
    public void buildIndex() throws IOException {
        Path dir = Paths.get("indicies/V3");
        if (Files.exists(dir)) {
            File f = new File("indicies/V3");
            deleteDirectory(f);
            f.delete();
        }
        IndexBuilder.buildIndexVersion3("V3");
    }

    private void deleteDirectory(File f) {
        for (File subFile : f.listFiles()) {
            subFile.delete();
        }
    }
}
