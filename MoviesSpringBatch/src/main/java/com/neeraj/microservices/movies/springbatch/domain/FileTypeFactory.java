package com.neeraj.microservices.movies.springbatch.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileTypeFactory {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NameBasicsFileType nameBasicsFileType;
    @Autowired
    private TitleAkasFileType titleAkasFileType;
    @Autowired
    private TitleBasicsFileType titleBasicsFileType;
    @Autowired
    private TitleCrewFileType titleCrewFileType;
    @Autowired
    private TitleEpisodeFileType titleEpisodeFileType;
    @Autowired
    private TitlePrincipalsFileType titlePrincipalsFileType;
    @Autowired
    private TitleRatingFileType titleRatingFileType;

    public FileType getFileType(String fileName) {
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("RATING")) {
            log.info("titleRatingFileType Satisfied");
            return titleRatingFileType;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("PRINCIPALS")) {
            return titlePrincipalsFileType;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("EPISODE")) {
            return titleEpisodeFileType;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("CREW")) {
            return titleCrewFileType;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("BASICS")) {
            return titleBasicsFileType;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("AKAS")) {
            return titleAkasFileType;
        }
        if (fileName.toUpperCase().contains("NAME") && fileName.toUpperCase().contains("BASICS")) {
            return nameBasicsFileType;
        }
        log.info("Nothing Satisfied");
        return null;
    }

}
