package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.mapper.TitleAkasMapper;
import com.neeraj.microservices.movies.springbatch.model.TitleAkas;
import com.neeraj.microservices.movies.springbatch.repository.TitleAkasRepository;
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
public class TitleAkasFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private TitleAkasMapper titleAkasMapper;
    @Autowired
    private TitleAkasRepository titleAkasRepository;

    @Override
    public ItemReader<TitleAkas> getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<TitleAkas> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<TitleAkas> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(titleAkasMapper);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Override
    public ItemProcessor<TitleAkas, TitleAkas> getItemProcesser() {
        return new ItemProcessor<TitleAkas, TitleAkas>() {
            @Override
            public TitleAkas process(TitleAkas item) {
                return item;
            }
        };
    }

    @Override
    public ItemWriter<TitleAkas> getItemWriter() {
        return (List<? extends TitleAkas> objects) -> {
            log.info("Storing {} {} objects to DB.", +objects.size(), objects.get(0).getClass().getName());
            titleAkasRepository.saveAll(objects);
        };
    }
}
