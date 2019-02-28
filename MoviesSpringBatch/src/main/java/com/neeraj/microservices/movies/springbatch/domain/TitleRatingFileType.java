package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.batch.GZipBufferedReaderFactory;
import com.neeraj.microservices.movies.springbatch.mapper.TitleRattingMapper;
import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import com.neeraj.microservices.movies.springbatch.repository.TitleRatingRepository;
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
public class TitleRatingFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private TitleRattingMapper titleRattingMapper;
    @Autowired
    private TitleRatingRepository titleRatingRepository;

    @Override
    public ItemReader<TitleRating> getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<TitleRating> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<TitleRating> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(titleRattingMapper);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;

    }

    @Override
    public ItemProcessor<TitleRating, TitleRating> getItemProcesser() {
        return new ItemProcessor<TitleRating, TitleRating>() {
            @Override
            public TitleRating process(TitleRating item) {
                return item;
            }
        };
    }

    @Override
    public ItemWriter<TitleRating> getItemWriter() {
        return (List<? extends TitleRating> objects) -> {
            log.info("Storing {} {} objects to DB.", +objects.size(), objects.get(0).getClass().getName());
            titleRatingRepository.saveAll(objects);
        };
    }
}
