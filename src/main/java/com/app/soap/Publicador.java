package com.app.soap;

import javax.xml.ws.Endpoint;

public class Publicador {

    public static void publicar() {

        String ip = Config.get("soap.ip");
        String port = Config.get("soap.port");
        String path = Config.get("soap.path");

        String url = "http://" + ip + ":" + port + path;

        System.out.println("Publicando servicio SOAP en: " + url);

        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

        Endpoint.publish(url, new Volando());

        System.out.println("Servicio SOAP publicado.");
    }
}
