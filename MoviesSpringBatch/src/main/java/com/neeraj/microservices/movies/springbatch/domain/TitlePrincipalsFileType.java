package com.neeraj.microservices.movies.springbatch.domain;

import com.neeraj.microservices.movies.springbatch.mapper.TitlePrincipalsMapper;
import com.neeraj.microservices.movies.springbatch.model.TitlePrincipals;
import com.neeraj.microservices.movies.springbatch.repository.TitlePrincipalsRepository;
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
public class TitlePrincipalsFileType implements FileType {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GZipBufferedReaderFactory gZipBufferedReaderFactory;
    @Autowired
    private TitlePrincipalsMapper titlePrincipalsMapper;
    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;

    @Override
    public ItemReader<TitlePrincipals> getItemReader(String fileName) {

        String finalCompressedFile = FILE_PATH + fileName;
        DefaultLineMapper<TitlePrincipals> lineMapper = new DefaultLineMapper<>();
        FlatFileItemReader<TitlePrincipals> flatFileItemReader = new FlatFileItemReader<>();
        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
        flatFileItemReader.setResource(fileSystemResource);
        flatFileItemReader.setName(fileName + " File-Reader");
        flatFileItemReader.setLinesToSkip(1);

        lineMapper.setLineTokenizer(getLineTokenizer());
        lineMapper.setFieldSetMapper(titlePrincipalsMapper);
        flatFileItemReader.setLineMapper(lineMapper);
        return flatFileItemReader;
    }

    @Override
    public ItemProcessor<TitlePrincipals, TitlePrincipals> getItemProcesser() {
        return new ItemProcessor<TitlePrincipals, TitlePrincipals>() {
            @Override
            public TitlePrincipals process(TitlePrincipals item) {
                log.debug("Value during ItemProcesser is {}." , item.toString());
                return item;
            }
        };
    }

    @Override
    public ItemWriter<TitlePrincipals> getItemWriter() {
        return (List<? extends TitlePrincipals> objects) -> {

            log.info("Starting writing {} {} objects to DB.", objects.size(), objects.get(0).getClass().getName());

            try{
                titlePrincipalsRepository.saveAll(objects);
            }catch (RuntimeException e){
                log.error("{} Error While processing the Below Objects: \n{}", e.getMessage() , objects.toString());
            }
        };
    }
}
