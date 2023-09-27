package com.elkased.converttovtt.model;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConverter {

    private FileConverter() {
    }

    public static String doConvert(String src) {
        String srcAddress;

        if (src.charAt(src.length() - 1) == File.separatorChar) {
            src = src.substring(0, src.length() - 1);
        }

        int lastIndex = src.lastIndexOf(File.separatorChar);

        String dest = src.substring(0, lastIndex);

        srcAddress = src.substring(lastIndex + 1, src.length() - 3);

        // Output File Full Path
        StringBuilder destAddress = new StringBuilder(dest).append(File.separatorChar).append(srcAddress).append("vtt");
        try {
            Path inputPath = Paths.get(src);
            Path outputPath = Paths.get(destAddress.toString());

            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(inputPath)));
            BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(outputPath)));

            bw.write("WEBVTT");
            bw.newLine();
            bw.newLine(); // Add a blank line after "WEBVTT"

            String line;
            boolean subtitleStarted = false;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    subtitleStarted = false;
                    bw.newLine(); // Add a blank line between subtitles
                } else if (!subtitleStarted) {
                    // Parse and format the SRT timestamp into VTT timestamp format
                    String[] timestamps = line.split(" --> ");
                    if (timestamps.length == 2) {
                        String startTimestamp = timestamps[0].replace(",", ".");
                        String endTimestamp = timestamps[1].replace(",", ".");
                        bw.write(startTimestamp + " --> " + endTimestamp);
                        bw.newLine();
                        subtitleStarted = true;
                    }
                } else {
                    // Write the subtitle text
                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            return destAddress.toString();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
