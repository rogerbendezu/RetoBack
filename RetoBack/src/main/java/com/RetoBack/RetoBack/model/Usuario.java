package com.RetoBack.RetoBack.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
//@NoArgsConstructor
//@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
@Entity
public class Usuario extends Auditoria implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cellphone;

    private String name;

    private String lastName;

    private String password;

    private String username;
    @Enumerated(EnumType.STRING)
    Role role;

    //@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    //private List<Post> posts;
    private Set<Post> posts = new HashSet<>();

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
        for(Post post : posts) {
            post.setUsuario(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
