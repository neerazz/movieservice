package com.neeraj.microservices.movies.springbatch.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

@Component
public class FileSkipperPolicy implements SkipPolicy {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.batch.chunk}")
    private Integer chunkValue;

    @Override
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {

        /*
            If the error exceeds more than 1% of the chunk value, then fail the job.
        */
        if (skipCount > (chunkValue * 0.01)) {
            return false;
        }

        /*
            If the error is due to any DataIntegrityViolationException, then skip the record.
        */
        if (t instanceof DataIntegrityViolationException) {
            log.error("An {} error occurred while processing, Skipping the record and going ahead with the remaining file."
                    , ((DataIntegrityViolationException) t).getRootCause().getLocalizedMessage());
            return true;
        }

        if (t instanceof SQLIntegrityConstraintViolationException || t instanceof DuplicateKeyException) {
            log.error("An {} error occurred while processing, Skipping the record and going ahead with the remaining file."
                    , ((DuplicateKeyException) t).getRootCause().getLocalizedMessage());
        }

        return false;
    }
}
