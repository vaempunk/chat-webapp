package dev.vaem.websockets.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private Name name;

    public enum Name {
        USER, MODERATOR, ADMIN;
    }

}
