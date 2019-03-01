package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.mapper.TitleEpisodeMapper;
import com.neeraj.microservices.movies.springbatch.model.TitleEpisode;
import com.neeraj.microservices.movies.springbatch.repository.TitleEpisodeRepository;
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

import static com.neeraj.microservices.movies.springbatch.service.JobCreatingService.FILE_PATH;

@Component
public class TitleEpisodeFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private TitleEpisodeMapper titleEpisodeMapper;
    @Autowired
    private TitleEpisodeRepository titleEpisodeRepository;

    @Override
    public ItemReader<TitleEpisode> getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<TitleEpisode> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<TitleEpisode> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(titleEpisodeMapper);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Override
    public ItemProcessor<TitleEpisode, TitleEpisode> getItemProcesser() {
        return new ItemProcessor<TitleEpisode, TitleEpisode>() {
            @Override
            public TitleEpisode process(TitleEpisode item) {
                log.debug("Value during ItemProcesser is {}." , item.toString());
                return item;
            }
        };
    }

    @Override
    public ItemWriter<TitleEpisode> getItemWriter() {
        return (List<? extends TitleEpisode> objects) -> {

            log.info("Starting writing {} {} objects to DB.", objects.size(), objects.get(0).getClass().getName());

            try{
                titleEpisodeRepository.saveAll(objects);
            }catch (RuntimeException e){
                log.error("{} Error While processing the Below Objects: \n{}", e.getMessage() , objects.toString());
            }
        };
    }
}
