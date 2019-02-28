package com.neeraj.microservices.movies.springbatch.domain;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_TAB;

public interface FileType {

    ItemReader getItemReader(String fileName);

    ItemProcessor getItemProcesser();

    ItemWriter getItemWriter();

    default DelimitedLineTokenizer getLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DELIMITER_TAB);
        lineTokenizer.setStrict(false);
        return lineTokenizer;
    }
}
