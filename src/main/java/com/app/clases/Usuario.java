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
            joinColumns = @JoinColumn(name = "sigue_nickname"),
            inverseJoinColumns = @JoinColumn(name = "loSiguen_nickname")
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
        this.setSeguidos(new ArrayList<>());
        this.setSeguidores(new ArrayList<>());
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

    public boolean sigueA(String nickname) {
        for (Usuario u : this.seguidos) {
            if (u.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public void agregarSeguidorySeguido(Usuario usuarioQueSiguen) {
        if (!this.sigueA(usuarioQueSiguen.getNickname())) {
            this.seguidos.add(usuarioQueSiguen);
            usuarioQueSiguen.getSeguidores().add(this);
            return;
        }
        throw new IllegalArgumentException("El usuario ya es seguido.");
    }

    public void eliminarSeguidoYSeguidor(Usuario usuarioDejadoSeguir) {
        if (this.sigueA(usuarioDejadoSeguir.getNickname())) {
            this.seguidos.remove(usuarioDejadoSeguir);
            usuarioDejadoSeguir.getSeguidores().remove(this);
            return;
        }
        throw new IllegalArgumentException("El usuario no es seguido.");
    }

    public DtUsuario getDatos() {
        List <DtUsuario> listaSeguidores = new ArrayList<>();
        for ( Usuario u : this.getSeguidores()){
            listaSeguidores.add(u.getDatos());
        }
        List <DtUsuario> listaSeguidos = new ArrayList<>();
        for ( Usuario u : this.getSeguidos()){
            listaSeguidos.add(u.getDatos());
        }

        return new DtUsuario(
                this.getNickname(),
                this.getNombre(),
                this.getEmail(),
                this.getUrlImage(),
                listaSeguidores,
                listaSeguidos
        );
    }

    @Override
    public String toString() {
        return this.nickname;
    }
}

