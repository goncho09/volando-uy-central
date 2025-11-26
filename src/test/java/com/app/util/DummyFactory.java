package com.app.util;

import com.app.clases.Factory;
import com.app.clases.ISistema;
import com.app.datatypes.*;
import com.app.enums.EstadoRuta;
import com.app.enums.MetodoPago;
import com.app.enums.TipoAsiento;
import com.app.enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyFactory {

    private static ISistema s = null;

    public static ISistema crearSistema(){
        if (s != null){
            return s;
        }
        s = Factory.getSistema();


        s.vaciarBD();

        // ------- Usuarios --------- //
        List<DtAerolinea> aerolineas = new ArrayList<>();
        aerolineas.add(new DtAerolinea("crystalMotus", "VuelosCristal", "fragil@gmail.com", "1234", "default.png", "¡Vuelos cristalinos!", "VuelosCristal.com"));
        aerolineas.add(new DtAerolinea("skyHigh", "SkyHigh Airlines", "contact@skyhigh.com", "1234", "default.png", "Vuelos al cielo sin límites", ""));
        aerolineas.add(new DtAerolinea("blueWing", "BlueWing", "info@bluewing.com", "1234", "default.png", "La experiencia azul en cada vuelo", "www.bluewing.com"));
        aerolineas.add(new DtAerolinea("sunFly", "SunFly", "reservas@sunfly.com", "1234", "default.png", "Viajes soleados para todos", ""));
        aerolineas.add(new DtAerolinea("aeroDream", "AeroDream", "soporte@aerodream.com", "1234", "default.png", "Hacemos realidad tus sueños de volar", "www.aerodream.com"));

        for (DtAerolinea a : aerolineas) {
            s.registrarAerolinea(a);
        }

        List<DtCliente> clientes = new ArrayList<>();
        clientes.add(new DtCliente("power", "Bloody", "laSanguinaria@gmail.com", "1234", "db774be8-a53e-40f7-aa14-default.png", "Mary", LocalDate.of(1990, 5, 12).toString(), "Tokyo", TipoDocumento.CEDULA, 12345678));
        clientes.add(new DtCliente("zacgamer", "José", "zacgamer255@gmail.com", "1234", "default.png", "García", LocalDate.of(2004, 8, 20).toString(), "Mexicana", TipoDocumento.PASAPORTE, 87654321));
        clientes.add(new DtCliente("carlosSanchez", "Carlos", "carlos.sanchez@gmail.com", "1234", "default.png", "Sanchez", LocalDate.of(1992, 3, 3).toString(), "Chile", TipoDocumento.CEDULA, 23456789));
        clientes.add(new DtCliente("anaGomez", "Ana", "ana.gomez@gmail.com", "1234", "default.png", "Gomez", LocalDate.of(1995, 11, 17).toString(), "Paraguay", TipoDocumento.PASAPORTE, 98765432));
        clientes.add(new DtCliente("luisRamirez", "Luis", "luis.ramirez@gmail.com", "1234", "default.png", "Ramirez", LocalDate.of(1988, 1, 30).toString(), "Brasil", TipoDocumento.CEDULA, 34567890));


        for (DtCliente c : clientes) {
            s.registrarCliente(c);
        }

        // --------- Ciudades -------- //
        List<DtCiudad> ciudades = new ArrayList<>();
        ciudades.add(new DtCiudad("Montevideo", "Uruguay", "Aeropuerto Carrasco", "Capital y ciudad principal de Uruguay", "www.aeropuertocarrasco.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Ezeiza", "Capital de Argentina con importante tráfico aéreo", "www.aeropuertoezeiza.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Centro financiero y cultural de Chile", "www.aeropuertosantiago.cl", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Lima", "Perú", "Aeropuerto Jorge Chávez", "Ciudad capital del Perú", "www.aeropuertojorgechavez.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("São Paulo", "Brasil", "Aeropuerto Guarulhos", "Centro económico de Brasil", "www.aeropuertoguarulhos.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Rio de Janeiro", "Brasil", "Aeropuerto Galeão", "Famosa por sus playas y el Cristo Redentor", "www.aeropuertogaleao.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Asunción", "Paraguay", "Aeropuerto Silvio Pettirossi", "Capital y centro político de Paraguay", "www.aeropuertosilviopettirossi.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Montevideo Norte", "Uruguay", "Aeropuerto Internacional Norte", "Zona metropolitana y suburbana", "www.aeropuertonorte.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Quito", "Ecuador", "Aeropuerto Mariscal Sucre", "Capital ecuatoriana en los Andes", "www.aeropuertomariscal.com", LocalDate.now().toString()));
        ciudades.add(new DtCiudad("Bogotá", "Colombia", "Aeropuerto El Dorado", "Capital de Colombia, importante hub aéreo", "www.aeropuertobogota.com", LocalDate.now().toString()));

        for (DtCiudad c : ciudades) {
            s.altaCiudad(c);
        }

        // ---------- Categorias --------- //
        List<DtCategoria> categorias = new ArrayList<>();
        categorias.add(new DtCategoria("Turismo"));
        categorias.add(new DtCategoria("Negocios"));
        categorias.add(new DtCategoria("Aventura"));
        categorias.add(new DtCategoria("Familiar"));
        categorias.add(new DtCategoria("Lujo"));

        for (DtCategoria c : categorias) {
            s.altaCategoria(c);
        }

        // ------------ Rutas ------------ //
        List<DtRuta> rutas = new ArrayList<>();
        rutas.add(new DtRuta("Montevideo-Buenos Aires", "Ruta entre Montevideo y Buenos Aires", "Montevideo a Buenos Aires", LocalTime.of(2, 0).toString(), 150, 300, 20, LocalDate.now().toString(), "default.png", "https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(0), categorias.get(1)), ciudades.get(0), ciudades.get(1),0));
        rutas.add(new DtRuta("Buenos Aires-Santiago", "Ruta entre Buenos Aires y Santiago", "Buenos Aires a Santiago", LocalTime.of(3, 0).toString(), 200, 400, 25, LocalDate.now().toString(), "default.png", "https://www.youtube.com/watch?v=cB1FFlNk-zQ&pp=ygUZdmlkZW8gZGUgdnVlbG9zIHRpbWVsYXBzZQ%3D%3D", Arrays.asList(categorias.get(1)), ciudades.get(1), ciudades.get(2),0));
        rutas.add(new DtRuta("Santiago-Lima", "Ruta entre Santiago y Lima", "Santiago a Lima", LocalTime.of(4, 0).toString(), 220, 450, 30, LocalDate.now().toString(), "default.png", "https://www.youtube.com/watch?v=iQjSjDNhC9Y", Arrays.asList(categorias.get(2), categorias.get(3)), ciudades.get(2), ciudades.get(3),0));
        rutas.add(new DtRuta("Lima-São Paulo", "Ruta entre Lima y São Paulo", "Lima a São Paulo", LocalTime.of(5, 0).toString(), 250, 500, 35, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=iQjSjDNhC9Y", Arrays.asList(categorias.get(0), categorias.get(4)), ciudades.get(3), ciudades.get(4),0));
        rutas.add(new DtRuta("São Paulo-Rio de Janeiro", "Ruta entre São Paulo y Rio de Janeiro", "São Paulo a Rio", LocalTime.of(1, 30).toString(), 120, 240, 15, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=cB1FFlNk-zQ&pp=ygUZdmlkZW8gZGUgdnVlbG9zIHRpbWVsYXBzZQ%3D%3D", Arrays.asList(categorias.get(1), categorias.get(3)), ciudades.get(4), ciudades.get(5),0));
        rutas.add(new DtRuta("Rio de Janeiro-Asunción", "Ruta entre Rio de Janeiro y Asunción", "Rio a Asunción", LocalTime.of(4, 30).toString(), 280, 560, 40, LocalDate.now().toString(), "default.png", "https://www.youtube.com/watch?v=iQjSjDNhC9Y",Arrays.asList(categorias.get(2)), ciudades.get(5), ciudades.get(6),0));
        rutas.add(new DtRuta("Asunción-Montevideo Norte", "Ruta entre Asunción y Montevideo Norte", "Asunción a Montevideo Norte", LocalTime.of(3, 15).toString(), 210, 420, 25, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(0), categorias.get(3)), ciudades.get(6), ciudades.get(7),0));
        rutas.add(new DtRuta("Montevideo Norte-Quito", "Ruta entre Montevideo Norte y Quito", "Montevideo Norte a Quito", LocalTime.of(6, 0).toString(), 300, 600, 45, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=iQjSjDNhC9Y", Arrays.asList(categorias.get(1), categorias.get(4)), ciudades.get(7), ciudades.get(8),0));
        rutas.add(new DtRuta("Quito-Bogotá", "Ruta entre Quito y Bogotá", "Quito a Bogotá", LocalTime.of(2, 45).toString(), 180, 360, 20, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(0)),ciudades.get(8), ciudades.get(9),0));
        rutas.add(new DtRuta("Bogotá-Montevideo", "Ruta entre Bogotá y Montevideo", "Bogotá a Montevideo", LocalTime.of(7, 0).toString(), 350, 700, 50, LocalDate.now().toString(), "default.png", "https://www.youtube.com/watch?v=iQjSjDNhC9Y",Arrays.asList(categorias.get(2), categorias.get(4)), ciudades.get(9), ciudades.get(0),0));
        rutas.add(new DtRuta("Montevideo-Lima", "Ruta entre Montevideo y Lima", "Montevideo a Lima", LocalTime.of(5, 0).toString(), 260, 520, 30, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(0), categorias.get(1)), ciudades.get(0), ciudades.get(3),0));
        rutas.add(new DtRuta("Buenos Aires-Rio de Janeiro", "Ruta entre Buenos Aires y Rio de Janeiro", "Buenos Aires a Rio", LocalTime.of(3, 30).toString(), 230, 460, 25, LocalDate.now().toString(), "default.png", "https://www.youtube.com/watch?v=cB1FFlNk-zQ&pp=ygUZdmlkZW8gZGUgdnVlbG9zIHRpbWVsYXBzZQ%3D%3D",Arrays.asList(categorias.get(2), categorias.get(3)), ciudades.get(1), ciudades.get(5),0));
        rutas.add(new DtRuta("Santiago-Asunción", "Ruta entre Santiago y Asunción", "Santiago a Asunción", LocalTime.of(5, 15).toString(), 270, 540, 35, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=iQjSjDNhC9Y", Arrays.asList(categorias.get(1)), ciudades.get(2), ciudades.get(6),0));
        rutas.add(new DtRuta("Lima-Montevideo Norte", "Ruta entre Lima y Montevideo Norte", "Lima a Montevideo Norte", LocalTime.of(6, 30).toString(), 290, 580, 40, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(0), categorias.get(4)), ciudades.get(3), ciudades.get(7),0));
        rutas.add(new DtRuta("São Paulo-Quito", "Ruta entre São Paulo y Quito", "São Paulo a Quito", LocalTime.of(7, 15).toString(), 310, 620, 45, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=cB1FFlNk-zQ&pp=ygUZdmlkZW8gZGUgdnVlbG9zIHRpbWVsYXBzZQ%3D%3D", Arrays.asList(categorias.get(3)), ciudades.get(4), ciudades.get(8),0));
        rutas.add(new DtRuta("Rio de Janeiro-Bogotá", "Ruta entre Rio de Janeiro y Bogotá", "Rio a Bogotá", LocalTime.of(6, 0).toString(), 300, 600, 40, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=iQjSjDNhC9Y", Arrays.asList(categorias.get(2), categorias.get(4)), ciudades.get(5), ciudades.get(9),0));
        rutas.add(new DtRuta("Asunción-Lima", "Ruta entre Asunción y Lima", "Asunción a Lima", LocalTime.of(5, 45).toString(), 280, 560, 35, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(0)), ciudades.get(6), ciudades.get(3),0));
        rutas.add(new DtRuta("Montevideo Norte-São Paulo", "Ruta entre Montevideo Norte y São Paulo", "Montevideo Norte a São Paulo", LocalTime.of(6, 15).toString(), 300, 600, 40, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=iQjSjDNhC9Y", Arrays.asList(categorias.get(1), categorias.get(2)), ciudades.get(7), ciudades.get(4),0));
        rutas.add(new DtRuta("Quito-Rio de Janeiro", "Ruta entre Quito y Rio de Janeiro", "Quito a Rio", LocalTime.of(7, 30).toString(), 320, 640, 50, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=rNGWhSb447o", Arrays.asList(categorias.get(3), categorias.get(4)), ciudades.get(8), ciudades.get(5),0));
        rutas.add(new DtRuta("Bogotá-Santiago", "Ruta entre Bogotá y Santiago", "Bogotá a Santiago", LocalTime.of(5, 0).toString(), 250, 500, 30, LocalDate.now().toString(), "default.png","https://www.youtube.com/watch?v=cB1FFlNk-zQ&pp=ygUZdmlkZW8gZGUgdnVlbG9zIHRpbWVsYXBzZQ%3D%3D", Arrays.asList(categorias.get(0), categorias.get(2)), ciudades.get(9), ciudades.get(2),0));

        List<DtAerolinea> aerolineasAsignar = Arrays.asList(
                s.getAerolinea("crystalMotus"),
                s.getAerolinea("skyHigh"),
                s.getAerolinea("blueWing")
        );


        List<DtRuta> rutasAprobadas = new ArrayList<>();
        for (int i = 0; i < rutas.size(); i++) {
            DtAerolinea aerolinea = aerolineasAsignar.get(i % aerolineasAsignar.size());
            s.altaRutaDeVuelo(aerolinea.getNickname(), rutas.get(i));
            if (aerolinea.getNickname().equals("crystalMotus")) {
                s.actualizarEstadoRuta(rutas.get(i), EstadoRuta.APROBADA);
                rutasAprobadas.add(rutas.get(i));
            } else if (aerolinea.getNickname().equals("skyHigh")) {
                s.actualizarEstadoRuta(rutas.get(i), EstadoRuta.RECHAZADA);
            }
        }

        // -------------- Vuelos ---------------- //

        List<DtVuelo> vuelos = new ArrayList<>();
        vuelos.add(new DtVuelo("Vuelo_1", LocalDate.now().plusDays(1).toString(), LocalTime.of(2, 0).toString(), 100, 20, "default.png", LocalDate.now().toString(), rutasAprobadas.get(0), 0));
        vuelos.add(new DtVuelo("Vuelo_2", LocalDate.now().plusDays(2).toString(), LocalTime.of(3, 0).toString(), 120, 25, "default.png", LocalDate.now().toString(), rutasAprobadas.get(1), 0));
        vuelos.add(new DtVuelo("Vuelo_3", LocalDate.now().plusDays(3).toString(), LocalTime.of(1, 45).toString(), 80, 15, "default.png", LocalDate.now().toString(), rutasAprobadas.get(2), 0));
        vuelos.add(new DtVuelo("Vuelo_4", LocalDate.now().plusDays(4).toString(), LocalTime.of(2, 30).toString(), 150, 30, "default.png", LocalDate.now().toString(), rutasAprobadas.get(3), 0));
        vuelos.add(new DtVuelo("Vuelo_5", LocalDate.now().plusDays(5).toString(), LocalTime.of(2, 15).toString(), 90, 18, "default.png", LocalDate.now().toString(), rutasAprobadas.get(4), 0));
        vuelos.add(new DtVuelo("Vuelo_6", LocalDate.now().plusDays(6).toString(), LocalTime.of(3, 15).toString(), 110, 22, "default.png", LocalDate.now().toString(), rutasAprobadas.get(5), 0));
        vuelos.add(new DtVuelo("Vuelo_7", LocalDate.now().plusDays(7).toString(), LocalTime.of(1, 50).toString(), 95, 20, "default.png", LocalDate.now().toString(), rutasAprobadas.get(2), 0));
        vuelos.add(new DtVuelo("Vuelo_8", LocalDate.now().plusDays(8).toString(), LocalTime.of(2, 45).toString(), 130, 28, "default.png", LocalDate.now().toString(), rutasAprobadas.get(0), 0));
        vuelos.add(new DtVuelo("Vuelo_9", LocalDate.now().plusDays(9).toString(), LocalTime.of(2, 10).toString(), 105, 24, "default.png", LocalDate.now().toString(), rutasAprobadas.get(1), 0));
        vuelos.add(new DtVuelo("Vuelo_10", LocalDate.now().plusDays(10).toString(), LocalTime.of(3, 0).toString(), 120, 26, "default.png", LocalDate.now().toString(), rutasAprobadas.get(2), 0));

        for (DtVuelo vuelo : vuelos) {
            s.altaVuelo(vuelo);
        }

        // --------------- Paquetes -------------- //

        List<DtPaquete> paquetes = new ArrayList<>();
        paquetes.add(new DtPaquete("ExploraSur", "Viajes por el sur con descuento especial", 30, 10));
        paquetes.add(new DtPaquete("VueloExpress", "Ideal para escapadas rápidas", 15, 5));
        paquetes.add(new DtPaquete("NegociosPlus", "Paquete para viajeros de negocios frecuentes", 60, 15));
        paquetes.add(new DtPaquete("FamiliaFeliz", "Rutas ideales para disfrutar en familia", 45, 12));
        paquetes.add(new DtPaquete("AventuraTotal", "Vuelos hacia destinos de aventura extrema", 20, 0));
        paquetes.add(new DtPaquete("LujoMundial", "Viajes de lujo con beneficios exclusivos", 90, 20));
        paquetes.add(new DtPaquete("TurismoCultural", "Descubre el patrimonio y cultura mundial", 40, 10));
        paquetes.add(new DtPaquete("EscapadaRomántica", "Vuelos para parejas y lunas de miel", 25, 7));
        paquetes.add(new DtPaquete("WorkAndTravel", "Para quienes combinan trabajo y viajes", 75, 0));
        paquetes.add(new DtPaquete("EcoVuelo", "Promueve destinos ecológicos y sostenibles", 50, 9));

        List<DtPaquete> paquetesParaRutas = new ArrayList<>();

        for (DtPaquete p : paquetes) {
            s.altaPaquete(p);
            if (p.getDescuento() == 0 || p.getNombre().equals("VueloExpress") || p.getNombre().equals("LujoMundial") || p.getNombre().equals("EcoVuelo")) {
                paquetesParaRutas.add(p);
            }
        }

        s.agregarRutaAPaquete(paquetesParaRutas.get(0), rutasAprobadas.get(5), 3, TipoAsiento.TURISTA);
        s.agregarRutaAPaquete(paquetesParaRutas.get(0), rutasAprobadas.get(4), 2, TipoAsiento.EJECUTIVO);
        s.agregarRutaAPaquete(paquetesParaRutas.get(1), rutasAprobadas.get(2), 2, TipoAsiento.EJECUTIVO);
        s.agregarRutaAPaquete(paquetesParaRutas.get(2), rutasAprobadas.get(0), 4, TipoAsiento.TURISTA);
        s.agregarRutaAPaquete(paquetesParaRutas.get(3), rutasAprobadas.get(4), 1, TipoAsiento.EJECUTIVO);
        s.agregarRutaAPaquete(paquetesParaRutas.get(3), rutasAprobadas.get(6), 3, TipoAsiento.TURISTA);
        s.agregarRutaAPaquete(paquetesParaRutas.get(4), rutasAprobadas.get(1), 5, TipoAsiento.TURISTA);

        s.compraPaquete(paquetesParaRutas.get(0), clientes.get(1));
        s.compraPaquete(paquetesParaRutas.get(1), clientes.get(0));
        s.compraPaquete(paquetesParaRutas.get(2), clientes.get(1));
        s.compraPaquete(paquetesParaRutas.get(3), clientes.get(4));
        s.compraPaquete(paquetesParaRutas.get(4), clientes.get(3));

        // ------------------- Reservas ------------------- //

        List<DtPasajero> pasajeros = new ArrayList<>();

        pasajeros.add(new DtPasajero("Lucía", "Martínez"));
        pasajeros.add(new DtPasajero("Juan", "Pérez"));
        pasajeros.add(new DtPasajero("Marcos", "Fernández"));
        pasajeros.add(new DtPasajero("Sofía", "López"));
        pasajeros.add(new DtPasajero("Andrés", "García"));
        pasajeros.add(new DtPasajero("Camila", "Rodríguez"));
        pasajeros.add(new DtPasajero("Diego", "Suárez"));
        pasajeros.add(new DtPasajero("Valentina", "Gómez"));
        pasajeros.add(new DtPasajero("Felipe", "Torres"));
        pasajeros.add(new DtPasajero("Carolina", "Núñez"));
        pasajeros.add(new DtPasajero("Matías", "Vega"));
        pasajeros.add(new DtPasajero("Julieta", "Morales"));
        pasajeros.add(new DtPasajero("Agustín", "Ramos"));
        pasajeros.add(new DtPasajero("Martina", "Silva"));
        pasajeros.add(new DtPasajero("Tomás", "Castro"));
        pasajeros.add(new DtPasajero("Renata", "Herrera"));
        pasajeros.add(new DtPasajero("Benjamín", "Flores"));
        pasajeros.add(new DtPasajero("Isabella", "Mendoza"));
        pasajeros.add(new DtPasajero("Santiago", "Ruiz"));
        pasajeros.add(new DtPasajero("Victoria", "Navarro"));


        List<DtReserva> reservas = new ArrayList<>();

        reservas.add(new DtReserva(LocalDate.now().toString(), TipoAsiento.TURISTA, 2, 1, 0, Arrays.asList(pasajeros.get(0), pasajeros.get(1)), clientes.get(1), vuelos.get(5), MetodoPago.PAQUETE, paquetesParaRutas.get(0), false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(1).toString(), TipoAsiento.EJECUTIVO, 1, 0, 0, Arrays.asList(pasajeros.get(2)), clientes.get(0), vuelos.get(6), MetodoPago.PAQUETE, paquetesParaRutas.get(1), false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(2).toString(), TipoAsiento.TURISTA, 3, 2, 0, Arrays.asList(pasajeros.get(3), pasajeros.get(4), pasajeros.get(5)), clientes.get(1), vuelos.get(0), MetodoPago.PAQUETE, paquetesParaRutas.get(2), false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(3).toString(), TipoAsiento.EJECUTIVO, 2, 1, 0, Arrays.asList(pasajeros.get(6), pasajeros.get(7)), clientes.get(4), vuelos.get(4), MetodoPago.PAQUETE, paquetesParaRutas.get(3), false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(4).toString(), TipoAsiento.TURISTA, 3, 0, 0, Arrays.asList(pasajeros.get(8), pasajeros.get(18), pasajeros.get(19)), clientes.get(3), vuelos.get(8), MetodoPago.PAQUETE, paquetesParaRutas.get(4), false));

        reservas.add(new DtReserva(LocalDate.now().plusDays(5).toString(), TipoAsiento.TURISTA, 2, 0, 0, Arrays.asList(pasajeros.get(9), pasajeros.get(10)), clientes.get(0), vuelos.get(1), MetodoPago.GENERAL, false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(6).toString(), TipoAsiento.EJECUTIVO, 1, 1, 0, Arrays.asList(pasajeros.get(11)), clientes.get(1), vuelos.get(2), MetodoPago.GENERAL, false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(7).toString(), TipoAsiento.TURISTA, 3, 2, 0, Arrays.asList(pasajeros.get(12), pasajeros.get(13), pasajeros.get(14)), clientes.get(0), vuelos.get(7), MetodoPago.GENERAL, false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(8).toString(), TipoAsiento.EJECUTIVO, 2, 1, 0, Arrays.asList(pasajeros.get(15), pasajeros.get(16)), clientes.get(1), vuelos.get(3), MetodoPago.GENERAL, false));
        reservas.add(new DtReserva(LocalDate.now().plusDays(9).toString(), TipoAsiento.TURISTA, 1, 0, 0, Arrays.asList(pasajeros.get(17)), clientes.get(0), vuelos.get(9), MetodoPago.GENERAL, false));


        for (DtReserva reserva : reservas) {
            s.altaReserva(reserva);
        }

        return s;
    }

}
