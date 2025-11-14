package com.app.soap;

import javax.xml.ws.Endpoint;

public class Publicador {

    public static void publicar() {

        String ip = Config.get("soap.ip");
        String port = Config.get("soap.port");
        String path = Config.get("soap.path");

        String url = "http://" + ip + ":" + port + path;

        System.out.println("Publicando servicio SOAP en: " + url);
        Endpoint.publish(url, new VolandoService());
        System.out.println("Servicio SOAP publicado.");
    }
}
