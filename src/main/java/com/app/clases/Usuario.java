package com.app.clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.app.datatypes.DtUsuario;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Esto indica que es una herencia, por ende tendrá subclases, utilizará el método Join para crear las "subtablas"
@DiscriminatorColumn(name = "userType") // Indica que creará una columna para diferenciar las clases. El valor depende de las subclases.
public abstract class Usuario {
    @Id private String nickname; // Indica que nickname será la id

    @Column(nullable = false, length = 50) // Indica que será una columna no nula
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String urlImage;

    @ManyToMany
    @JoinTable(
            name = "usuario_seguidos",
            joinColumns = @JoinColumn(name = "seguidor_nickname"),
            inverseJoinColumns = @JoinColumn(name = "seguido_nickname")
    )
    private List<Usuario> seguidos;

    @ManyToMany(mappedBy = "seguidos")
    private List<Usuario> seguidores;

    public Usuario() {};

    public Usuario(DtUsuario usuario){
        this.setNickname(usuario.getNickname());
        this.setNombre(usuario.getNombre());
        this.setEmail(usuario.getEmail());
        this.setPassword(usuario.getPassword());
        this.setUrlImage(usuario.getUrlImage());
        this.setSeguidos(usuario.getSeguidos());
        this.setSeguidores(usuario.getSeguidores());
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(List<Usuario> seguidos) {
        this.seguidos = seguidos;
    }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public DtUsuario getDatos() {
        return new DtUsuario(
                this.getNickname(),
                this.getNombre(),
                this.getEmail(),
                this.getUrlImage(),
                this.getSeguidores(),
                this.getSeguidos()
        );
    }

    @Override
    public String toString() {
        return this.nickname;
    }
}

