package com.RetoBack.RetoBack.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
//@NoArgsConstructor
//@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
@Entity
public class Post extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
