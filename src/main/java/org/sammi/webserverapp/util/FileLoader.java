package org.sammi.webserverapp.util;

import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

// Contains utility methods for loading small-to-medium-sized text files (such as HTML files).
public class FileLoader {
    final static Charset ENCODING = StandardCharsets.UTF_8;

    public static String loadFile(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        List<String> lines = Files.readAllLines(path, ENCODING);
        String output = "";
        for (String s : lines) {
            output += s + "\n";
        }
        return output;
    }
}

