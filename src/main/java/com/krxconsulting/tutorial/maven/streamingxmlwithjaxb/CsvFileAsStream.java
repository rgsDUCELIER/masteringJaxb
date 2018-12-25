package com.krxconsulting.tutorial.maven.streamingxmlwithjaxb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Transformer un fichier CSV en Strem
 *
 */
public class CsvFileAsStream implements Iterator<String> {

    BufferedReader underlyingReader = null;
    private boolean usable = false;
    private String bufferedLine = null;
    private boolean hasNextFlag = false;


    // BufferedReader br = new BufferedReader(new FileReader(file)
    public CsvFileAsStream( String csvPAth ) throws FileNotFoundException, IOException {
        underlyingReader = new BufferedReader(new FileReader(csvPAth));
        usable = true;
        bufferedLine = underlyingReader.readLine();
        if (bufferedLine == null) {
            hasNextFlag = false;
            // close silently
        } else {
            hasNextFlag = true;
        }
    }



    /**  */
    public void finalize()  {
        usable = false;

    }

    @Override
    public boolean hasNext() {
        return hasNextFlag;
    }

    @Override
    public String next() throws NoSuchElementException {
        if (! hasNextFlag) {
            throw new NoSuchElementException(".");
        } else {
            String currentNext = bufferedLine;
            // Prepare the next one
            try {
                bufferedLine = underlyingReader.readLine();
                if (bufferedLine == null) {
                    hasNextFlag = false;
                    // closeSilently
                } else {
                    hasNextFlag = true;
                }
            } catch (Exception ex) {
                hasNextFlag = false;
            }
            return  currentNext;
        }
    }
}
