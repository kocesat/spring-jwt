package com.kocesat.project.springjwt.base.model;

import org.bson.types.ObjectId;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    public abstract ObjectId getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
