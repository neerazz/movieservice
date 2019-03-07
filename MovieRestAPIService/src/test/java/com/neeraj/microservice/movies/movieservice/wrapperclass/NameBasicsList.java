package com.neeraj.microservice.movies.movieservice.wrapperclass;

import com.neeraj.microservice.movies.movieservice.model.NameBasics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NameBasicsList implements Serializable {

    private List<NameBasics> nameBasics;

    public NameBasicsList() {
        nameBasics = new ArrayList<>();
    }

    public NameBasicsList(List<NameBasics> nameBasics) {
        this.nameBasics = nameBasics;
    }

    public List<NameBasics> getNameBasics() {
        return nameBasics;
    }

    public NameBasicsList setNameBasics(List<NameBasics> nameBasics) {
        this.nameBasics = nameBasics;
        return this;
    }
}
