package com.shudong.spring.springrest.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "USER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String email;
    private Timestamp modifiedOn;
    @Column(columnDefinition = "mediumtext")
    private String data;
    @Version
    private long version;

    public interface Repo extends JpaRepository<UserEntity, String> {

    }
}
