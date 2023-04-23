package com.watson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class Version2Build {
    @Test
    public void buildIndex() throws IOException {
        Path dir = Paths.get("indicies/V2");
        if (Files.exists(dir)) {
            File f = new File("indicies/V2");
            deleteDirectory(f);
            f.delete();
        }
        IndexBuilder.buildIndexVersion2("V2");
    }

    private void deleteDirectory(File f) {
        for (File subFile : f.listFiles()) {
            subFile.delete();
        }
    }
}
