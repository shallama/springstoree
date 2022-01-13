package com.example.springstore.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
@Setter(value = AccessLevel.PRIVATE)
public class BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Integer version;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
            return false;
        final BaseEntity that = (BaseEntity) obj;
        return Objects.equals(id, that.id);
    }
}
