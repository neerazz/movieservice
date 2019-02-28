package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.mapper.TitleCrewMapper;
import com.neeraj.microservices.movies.springbatch.model.TitleCrew;
import com.neeraj.microservices.movies.springbatch.repository.TitleCrewRepository;
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
public class TitleCrewFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private TitleCrewMapper titleCrewMapper;
    @Autowired
    private TitleCrewRepository titleCrewRepository;

    @Override
    public ItemReader getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<TitleCrew> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<TitleCrew> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(titleCrewMapper);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Override
    public ItemProcessor<TitleCrew, TitleCrew> getItemProcesser() {
        return new ItemProcessor<TitleCrew, TitleCrew>() {
            @Override
            public TitleCrew process(TitleCrew item) {
                return item;
            }
        };
    }

    @Override
    public ItemWriter<TitleCrew> getItemWriter() {
        return (List<? extends TitleCrew> objects) -> {
            log.info("Storing {} {} objects to DB.", +objects.size(), objects.get(0).getClass().getName());
            titleCrewRepository.saveAll(objects);
        };
    }
}
