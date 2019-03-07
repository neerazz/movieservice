package com.neeraj.microservice.movies.movieservice.service;

import com.neeraj.microservice.movies.movieservice.model.FilePathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Service
public class FileZipUnZipService {

    public static final String FILE_PATH = "C:\\Users\\nksingh\\Neeraj\\Java\\RawData\\";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FilePathConfig pathConfig;

    public String zipFile(String source_filepath, String destinaton_zip_filepath) {
        byte[] buffer = new byte[1024];
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    destinaton_zip_filepath);
            GZIPOutputStream gzipOuputStream = new GZIPOutputStream(
                    fileOutputStream);
            FileInputStream fileInput = new FileInputStream(source_filepath);
            int bytes_read;
            while ((bytes_read = fileInput.read(buffer)) > 0) {
                gzipOuputStream.write(buffer, 0, bytes_read);
            }
            fileInput.close();

            gzipOuputStream.finish();
            gzipOuputStream.close();
            log.info("The file was compressed successfully!");
            return "The file was compressed successfully!";
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String unZipFile(String compressedFile, String decompressedFile) {
        compressedFile = FILE_PATH + compressedFile;
        decompressedFile = FILE_PATH + decompressedFile;

        byte[] buffer = new byte[1024];
        try {
            FileInputStream fileIn = new FileInputStream(compressedFile);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
            FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);
            int bytes_read;
            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytes_read);
            }
            gZIPInputStream.close();
            fileOutputStream.close();
            log.info("The file {} decompressed successfully!", compressedFile);
            return "The file was decompressed successfully!";
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String unZipFile(String compressedFilePath) {
        String[] split = compressedFilePath.split("\\.");
        String decompossedFilePath = split[0] + "." + split[1] + ".txt";
        File decompossedFile = new File(decompossedFilePath);

        try {
            decompossedFile.createNewFile();
            unZipFile(compressedFilePath, decompossedFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decompossedFilePath;
    }

    public String unZipAllFile() {

        unZipFile(pathConfig.getNamebasics());
        unZipFile(pathConfig.getTitleakas());
        unZipFile(pathConfig.getTitlebasics());
        unZipFile(pathConfig.getTitlecrew());
        unZipFile(pathConfig.getTitleepisode());
        unZipFile(pathConfig.getTitleprincipals());
        unZipFile(pathConfig.getTitleratings());

        return "Batch job submitted to unzip all the files.";
    }
}
