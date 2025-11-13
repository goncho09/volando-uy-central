package com.app.datatypes;

import com.app.clases.Usuario;

import java.util.List;

public class DtUsuario {
    private String nickname;
    private String nombre;
    private String email;
    private String password;
    private String urlImage;
    private List<Usuario> seguidores;
    private List<Usuario> seguidos;

    public DtUsuario() {}

    // alta usuario
    public DtUsuario(String nickname, String nombre, String email, String password, String urlImage, List<Usuario> seguidores, List<Usuario> seguidos){
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.urlImage = urlImage;
        this.seguidores = seguidores;
        this.seguidos = seguidos;
    }

    // get datos sin contraseña
    public DtUsuario(String nickname, String nombre, String email, String urlImage, List<Usuario> seguidores, List<Usuario> seguidos){
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.password = null;
        this.urlImage = urlImage;
        this.seguidores = seguidores;
        this.seguidos = seguidos;
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

    public String getPassword() {return password; }

    public String getUrlImage() {return urlImage; }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {this.password = password; }

    public void setUrlImage(String urlImage) {this.urlImage = urlImage; }

    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Usuario> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(List<Usuario> seguidos) {
        this.seguidos = seguidos;
    }

    public boolean sigueA(String nickname) {
        for (Usuario u : this.seguidos) {
            if (u.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() { return this.nickname; }
}
