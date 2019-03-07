package com.neeraj.microservice.movies.movieservice.graphql.model.entity.entitycompositesconstrain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/*
    A composite primary key is mapped using an Embeddable type in hibernate.
    We’ll first create an Embeddable type called EmployeeIdentity containing
    the employeeId and companyId fields, and then create the
    Employee entity which will embed the EmployeeIdentity type.
 */
@Embeddable
public class TitleAkasUniqueConstrain implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titleId;
    private String ordering;

    public TitleAkasUniqueConstrain() {
    }

    public TitleAkasUniqueConstrain(String titleId, String ordering) {
        this.titleId = titleId;
        this.ordering = ordering;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitleAkasUniqueConstrain)) return false;

        TitleAkasUniqueConstrain that = (TitleAkasUniqueConstrain) o;

        if (!getTitleId().equals(that.getTitleId())) return false;
        return getOrdering().equals(that.getOrdering());
    }

    @Override
    public int hashCode() {
        int result = getTitleId().hashCode();
        result = 31 * result + getOrdering().hashCode();
        return result;
    }

    public String getTitleId() {
        return titleId;
    }

    public TitleAkasUniqueConstrain setTitleId(String titleId) {
        this.titleId = titleId;
        return this;
    }

    public String getOrdering() {
        return ordering;
    }

    public TitleAkasUniqueConstrain setOrdering(String ordering) {
        this.ordering = ordering;
        return this;
    }
}