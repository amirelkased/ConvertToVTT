package com.elkased.converttovtt.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileConverter {

    private FileConverter() {
    }

    public static String doConvert(String src) {
        try {
            String newFilename;

            if (src.charAt(src.length() - 1) == File.separatorChar) {
                src = src.substring(0, src.length() - 1);
            }

            int lastIndex = src.lastIndexOf(File.separatorChar);

            String dest = src.substring(0, lastIndex);

            newFilename = src.substring(lastIndex + 1, src.length() - 3);

            Path pathOfSRTFile = Path.of(src);

            // Fetch lines as a stream
            Stream<String> lines = Files.lines(pathOfSRTFile);

            List<String> updatedLines = lines.filter(line -> !line.matches("\\d+|.*<\\d{2}:\\d{2}:\\d{2}\\.\\d{3}>.*"))
                    .toList();

            StringBuilder builder = new StringBuilder(dest).append(File.separatorChar).append(newFilename).append("vtt");
            FileWriter fileWriter = new FileWriter(builder.toString());

            updatedLines.forEach(e -> {
                try {
                    fileWriter.write(e);
                    fileWriter.write("\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            return builder.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
