package com.neeraj.microservice.movies.movieservice.model.entitycompositesconstrain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/*
    A composite primary key is mapped using an Embeddable type in hibernate.
    We’ll first create an Embeddable type called EmployeeIdentity containing
    the employeeId and companyId fields, and then create the
    Employee entity which will embed the EmployeeIdentity type.
 */
@Embeddable
public class TitlePrincipalsUniqueConstrain implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tconst;
    private int ordering;

    public TitlePrincipalsUniqueConstrain() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitlePrincipalsUniqueConstrain)) return false;

        TitlePrincipalsUniqueConstrain that = (TitlePrincipalsUniqueConstrain) o;

        if (getOrdering() != that.getOrdering()) return false;
        return getTconst().equals(that.getTconst());
    }

    @Override
    public int hashCode() {
        int result = getTconst().hashCode();
        result = 31 * result + getOrdering();
        return result;
    }

    public String getTconst() {
        return tconst;
    }

    public TitlePrincipalsUniqueConstrain setTconst(String tconst) {
        this.tconst = tconst;
        return this;
    }

    public int getOrdering() {
        return ordering;
    }

    public TitlePrincipalsUniqueConstrain setOrdering(int ordering) {
        this.ordering = ordering;
        return this;
    }
}
