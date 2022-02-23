package com.example.springstore.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;
/**
 *  Base entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Getter
@MappedSuperclass
@Setter(value = AccessLevel.PRIVATE)
public abstract class BaseEntity {
    @Id
    @Type(type="uuid-char")
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
