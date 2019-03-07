package com.neeraj.microservice.movies.movieservice.service;

import com.neeraj.microservice.movies.movieservice.mapper.MapperFactory;
import com.neeraj.microservice.movies.movieservice.mapper.StringToClassMapper;
import com.neeraj.microservice.movies.movieservice.model.FilePathConfig;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import static com.neeraj.microservice.movies.movieservice.service.FileZipUnZipService.FILE_PATH;

@Service
public class FileProcessingService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MapperFactory mapperFactory;
    @Autowired
    private FilePathConfig pathConfig;
    private CSVParser parser;

    public FileProcessingService() {
        parser = new CSVParserBuilder()
                .withSeparator('\t')
                .withIgnoreQuotations(true)
                .build();
    }

    public void processAllFiles() {

        log.info("Loading job to process all files");

//        new Thread(() -> processZipFile(pathConfig.getTitleakas())      ).start();
//        new Thread(() -> processZipFile(pathConfig.getTitlebasics())    ).start();
//        new Thread(() -> processZipFile(pathConfig.getTitlecrew())      ).start();
//        new Thread(() -> processZipFile(pathConfig.getTitleepisode())   ).start();
//        new Thread(() -> processZipFile(pathConfig.getTitleratings())   ).start();
//        new Thread(() -> processZipFile(pathConfig.getNamebasics())     ).start();
//        new Thread(() -> processZipFile(pathConfig.getTitleprincipals())).start();

        processZipFile(pathConfig.getTitleakas());
        processZipFile(pathConfig.getTitlebasics());
        processZipFile(pathConfig.getTitleepisode());
        processZipFile(pathConfig.getTitleprincipals());
        processZipFile(pathConfig.getNamebasics());
//        processZipFile(pathConfig.getTitlecrew());
//        processZipFile(pathConfig.getTitleratings());
    }

    public void processZipFile(String compressedFile) {

//        Append the Path to the file name.
        String finalCompressedFile = FILE_PATH + compressedFile;
        StringToClassMapper mapper = mapperFactory.getMapper(compressedFile);

        log.info("Processing the file {} of object type {}.", finalCompressedFile, mapper.getClass().getName());

//        Read the compressed File
        try (FileInputStream fis = new FileInputStream(finalCompressedFile);
             GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);
             InputStreamReader isr = new InputStreamReader(gZIPInputStream);
             CSVReader reader = new CSVReaderBuilder(isr)
                     .withSkipLines(1)
                     .withCSVParser(parser)
                     .build()
        ) {
            reader.iterator().forEachRemaining(strings -> {
                Object convertedObject = mapper.convertToObject(strings);
                log.debug("Storing the object {} to DB: ", convertedObject.toString());
                mapper.saveObject(convertedObject);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
