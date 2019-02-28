package com.neeraj.microservices.movies.springbatch.config;

import com.neeraj.microservices.movies.springbatch.domain.FileType;
import com.neeraj.microservices.movies.springbatch.domain.FileTypeFactory;
import com.neeraj.microservices.movies.springbatch.model.FilePathConfig;
import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    public static final String FILE_PATH = "C:\\Users\\nksingh\\Neeraj\\Java\\RawData\\";

    @Autowired
    private FileTypeFactory fileTypeFactory;

    @Autowired
    private FilePathConfig filePathConfig;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job job() {

        return jobBuilderFactory.get("File Loader")
                .incrementer(new RunIdIncrementer())
                .start(buildStep(filePathConfig.getTitleakas()))
                .start(buildStep(filePathConfig.getTitleepisode()))
                .start(buildStep(filePathConfig.getTitleprincipals()))
                .start(buildStep(filePathConfig.getTitleratings()))
                .start(buildStep(filePathConfig.getTitlebasics()))
                .next(buildStep(filePathConfig.getNamebasics()))
                .next(buildStep(filePathConfig.getTitlecrew()))
                .build();
    }

    private Step buildStep(String fileName) {
        FileType fileType = fileTypeFactory.getFileType(fileName);
        return stepBuilderFactory.get(fileName + " File-loader")
                .<TitleRating, TitleRating>chunk(50000)
                .reader(fileType.getItemReader(fileName))
                .processor(fileType.getItemProcesser())
                .writer(fileType.getItemWriter())
                .build();
    }

//    @Bean
//    public FlatFileItemReader<TitleRating> itemReader(@Value("${file.path.titleratings}") String resourceName) throws IOException {
//
//        String finalCompressedFile = FILE_PATH + resourceName;
//
//        FileSystemResource fileSystemResource = new FileSystemResource(finalCompressedFile);
//
//        FlatFileItemReader<TitleRating> flatFileItemReader = new FlatFileItemReader<>();
//
//        flatFileItemReader.setBufferedReaderFactory(gZipBufferedReaderFactory);
//        flatFileItemReader.setResource(fileSystemResource);
//        flatFileItemReader.setName("Ziped-File-Reader");
//        flatFileItemReader.setLinesToSkip(1);
//        flatFileItemReader.setLineMapper(titleRattingLineMapper());
//        return flatFileItemReader;
//    }
//
//    @Bean
//    public LineMapper<TitleRating> titleRattingLineMapper() {
//
//        DefaultLineMapper<TitleRating> defaultLineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//
//        lineTokenizer.setDelimiter(DELIMITER_TAB);
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames(new String[]{"tconst", "averageRating", "numVotes"});
//
//        defaultLineMapper.setLineTokenizer(lineTokenizer);
//        defaultLineMapper.setFieldSetMapper(titleRattingMapper);
//
//        return defaultLineMapper;
//    }
}
