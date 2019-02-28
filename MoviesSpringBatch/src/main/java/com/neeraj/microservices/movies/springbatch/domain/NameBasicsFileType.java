package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.mapper.NameBasicsMapper;
import com.neeraj.microservices.movies.springbatch.model.NameBasics;
import com.neeraj.microservices.movies.springbatch.repository.NameBasicsRepository;
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
public class NameBasicsFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private NameBasicsRepository nameBasicsRepository;
    @Autowired
    private NameBasicsMapper nameBasicsMapper;

    @Override
    public ItemReader<NameBasics> getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<NameBasics> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<NameBasics> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(nameBasicsMapper);

        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Override
    public ItemProcessor<NameBasics, NameBasics> getItemProcesser() {
        return new ItemProcessor<NameBasics, NameBasics>() {
            @Override
            public NameBasics process(NameBasics item) {
                return item;
            }
        };
    }

    @Override
    public ItemWriter<NameBasics> getItemWriter() {
        return (List<? extends NameBasics> objects) -> {
            log.info("Storing {} {} objects to DB.", +objects.size(), objects.get(0).getClass().getName());
            nameBasicsRepository.saveAll(objects);
        };
    }
}
