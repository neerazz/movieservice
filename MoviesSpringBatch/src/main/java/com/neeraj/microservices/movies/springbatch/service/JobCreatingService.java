package com.neeraj.microservices.movies.springbatch.service;

import com.neeraj.microservices.movies.springbatch.domain.FileType;
import com.neeraj.microservices.movies.springbatch.domain.FileTypeFactory;
import com.neeraj.microservices.movies.springbatch.model.FilePathConfig;
import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobCreatingService {

    public static final String FILE_PATH = "C:\\Users\\nksingh\\Neeraj\\Java\\RawData\\";

    @Autowired
    private FileTypeFactory fileTypeFactory;

    @Autowired
    private FilePathConfig filePathConfig;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    public Job createJobWithAllSteps() {
        return jobBuilderFactory.get("All File Loader")
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

    public Job createJobWithOneStep(int stepNumber) {

        Step step = null;

        switch (stepNumber) {
            case 1:
                step = buildStep(filePathConfig.getTitleakas());
                break;
            case 2:
                step = buildStep(filePathConfig.getTitleepisode());
                break;
            case 3:
                step = buildStep(filePathConfig.getTitleprincipals());
                break;
            case 4:
                step = buildStep(filePathConfig.getTitleratings());
                break;
            case 5:
                step = buildStep(filePathConfig.getTitlebasics());
                break;
            case 6:
                step = buildStep(filePathConfig.getNamebasics());
                break;
            case 7:
                step = buildStep(filePathConfig.getTitlecrew());
                break;
        }
        return jobBuilderFactory.get("Individual File Loader")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    private Step buildStep(String fileName) {
        FileType fileType = fileTypeFactory.getFileType(fileName);
        return stepBuilderFactory.get(fileName + " File-loader")
                .<TitleRating, TitleRating>chunk(20000)
                .reader(fileType.getItemReader(fileName))
                .processor(fileType.getItemProcesser())
                .writer(fileType.getItemWriter())
                .build();
    }
}
