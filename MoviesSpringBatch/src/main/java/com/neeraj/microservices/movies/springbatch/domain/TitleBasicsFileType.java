package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.batch.GZipBufferedReaderFactory;
import com.neeraj.microservices.movies.springbatch.mapper.TitleBasicsMapper;
import com.neeraj.microservices.movies.springbatch.model.TitleBasics;
import com.neeraj.microservices.movies.springbatch.repository.TitleBasicsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.neeraj.microservices.movies.springbatch.config.SpringBatchConfig.FILE_PATH;

@Component
public class TitleBasicsFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private TitleBasicsMapper titleBasicsMapper;
    @Autowired
    private TitleBasicsRepository titleBasicsRepository;

    @Override
    public ItemReader<TitleBasics> getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<TitleBasics> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<TitleBasics> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(titleBasicsMapper);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Override
    public ItemProcessor<TitleBasics, TitleBasics> getItemProcesser() {
        return new ItemProcessor<TitleBasics, TitleBasics>() {
            @Override
            public TitleBasics process(TitleBasics item) {
                return item;
            }
        };
    }

    @Override
    public ItemWriter<TitleBasics> getItemWriter() {
        return (List<? extends TitleBasics> objects) -> {
            log.info("Storing {} {} objects to DB.", +objects.size(), objects.get(0).getClass().getName());
            titleBasicsRepository.saveAll(objects);
        };
    }
}
