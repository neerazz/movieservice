package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NameBasicsRepositoryTest {

    @Autowired
    private NameBasicsRepository nameBasicsRepository;

    @Test
    public void insert_findById_and_delete() {
        NameBasics newNameBasics =
                new NameBasics()
                        .setNconst("nn00001")
                        .setPrimaryName("Sample Name")
                        .setBirthYear(1992)
                        .setDeathYear(0)
                        .setPrimaryProfession("Singing, Dancing, Hiking")
                        .setKnownForTitles("");
        //      This line will insert the new entity into table by avoiding cashing.
        //      But using TestEntityManager the first level cashing will not be done. This way when we
        //      find by ID it will hit the DB instead of getting it from cache.
        //    NameBasics nameBasicsSaved = entityManager.persistAndFlush(newNameBasics);

        NameBasics nameBasicsSaved = nameBasicsRepository.save(newNameBasics);

        //      This will get the find the entity from table.
        NameBasics nameBasics = nameBasicsRepository.findById(newNameBasics.getNconst()).get();

        assertThat(nameBasics).isEqualTo(nameBasicsSaved);

        //    Delete the newly inserted entity.
        nameBasicsRepository.delete(nameBasicsSaved);
    }
}
