package com.app.util;

import com.app.clases.Factory;
import com.app.clases.ISistema;
import com.app.datatypes.*;
import com.app.enums.EstadoRuta;
import com.app.enums.TipoAsiento;
import com.app.enums.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyFactory {

    public static ISistema crearSistema(){
        ISistema s = Factory.getSistema();

        s.vaciarBD();
        s.cargarDatos();

        // ------- Usuarios --------- //
        List<DtAerolinea> aerolineas = new ArrayList<>();
        aerolineas.add(new DtAerolinea("crystalMotus", "VuelosCristal", "fragil@gmail.com", "1234", "default.png", "¡Vuelos cristalinos!", "VuelosCristal.com"));
        aerolineas.add(new DtAerolinea("skyHigh", "SkyHigh Airlines", "contact@skyhigh.com", "1234", "default.png", "Vuelos al cielo sin límites"));
        aerolineas.add(new DtAerolinea("blueWing", "BlueWing", "info@bluewing.com", "1234", "default.png", "La experiencia azul en cada vuelo", "www.bluewing.com"));
        aerolineas.add(new DtAerolinea("sunFly", "SunFly", "reservas@sunfly.com", "1234", "default.png", "Viajes soleados para todos"));
        aerolineas.add(new DtAerolinea("aeroDream", "AeroDream", "soporte@aerodream.com", "1234", "default.png", "Hacemos realidad tus sueños de volar", "www.aerodream.com"));

        for(DtAerolinea a : aerolineas){
            s.registrarAerolinea(a);
        }

        List<DtCliente> clientes = new ArrayList<>();
        clientes.add(new DtCliente("power", "Bloody", "laSanguinaria@gmail.com", "1234", "db774be8-a53e-40f7-aa14-default.png", "Mary", LocalDate.of(1990, 5, 12), "Tokyo", TipoDocumento.CEDULA, 12345678));
        clientes.add(new DtCliente("zacgamer", "José", "zacgamer255@gmail.com", "1234", "default.png", "García", LocalDate.of(2004, 8, 20), "Mexicana", TipoDocumento.PASAPORTE, 87654321));
        clientes.add(new DtCliente("carlosSanchez", "Carlos", "carlos.sanchez@gmail.com", "1234", "default.png", "Sanchez", LocalDate.of(1992, 3, 3), "Chile", TipoDocumento.CEDULA, 23456789));
        clientes.add(new DtCliente("anaGomez", "Ana", "ana.gomez@gmail.com", "1234", "default.png", "Gomez", LocalDate.of(1995, 11, 17), "Paraguay", TipoDocumento.PASAPORTE, 98765432));
        clientes.add(new DtCliente("luisRamirez", "Luis", "luis.ramirez@gmail.com", "1234", "default.png", "Ramirez", LocalDate.of(1988, 1, 30), "Brasil", TipoDocumento.CEDULA, 34567890));


        for(DtCliente c : clientes){
            s.registrarCliente(c);
        }

        // --------- Ciudades -------- //
        List<DtCiudad> ciudades = new ArrayList<>();
        ciudades.add(new DtCiudad("Montevideo", "Uruguay", "Aeropuerto Carrasco", "Capital y ciudad principal de Uruguay", "www.aeropuertocarrasco.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Buenos Aires", "Argentina", "Aeropuerto Ezeiza", "Capital de Argentina con importante tráfico aéreo", "www.aeropuertoezeiza.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Santiago", "Chile", "Aeropuerto Arturo Merino Benítez", "Centro financiero y cultural de Chile", "www.aeropuertosantiago.cl", LocalDate.now()));
        ciudades.add(new DtCiudad("Lima", "Perú", "Aeropuerto Jorge Chávez", "Ciudad capital del Perú", "www.aeropuertojorgechavez.com", LocalDate.now()));
        ciudades.add(new DtCiudad("São Paulo", "Brasil", "Aeropuerto Guarulhos", "Centro económico de Brasil", "www.aeropuertoguarulhos.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Rio de Janeiro", "Brasil", "Aeropuerto Galeão", "Famosa por sus playas y el Cristo Redentor", "www.aeropuertogaleao.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Asunción", "Paraguay", "Aeropuerto Silvio Pettirossi", "Capital y centro político de Paraguay", "www.aeropuertosilviopettirossi.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Montevideo Norte", "Uruguay", "Aeropuerto Internacional Norte", "Zona metropolitana y suburbana", "www.aeropuertonorte.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Quito", "Ecuador", "Aeropuerto Mariscal Sucre", "Capital ecuatoriana en los Andes", "www.aeropuertomariscal.com", LocalDate.now()));
        ciudades.add(new DtCiudad("Bogotá", "Colombia", "Aeropuerto El Dorado", "Capital de Colombia, importante hub aéreo", "www.aeropuertobogota.com", LocalDate.now()));

        for(DtCiudad c : ciudades){
            s.altaCiudad(c);
        }

        // ---------- Categorias --------- //
        List<DtCategoria> categorias = new ArrayList<>();
        categorias.add(new DtCategoria("Turismo"));
        categorias.add(new DtCategoria("Negocios"));
        categorias.add(new DtCategoria("Aventura"));
        categorias.add(new DtCategoria("Familiar"));
        categorias.add(new DtCategoria("Lujo"));

        for(DtCategoria c : categorias){
            s.altaCategoria(c);
        }

        // ------------ Rutas ------------ //
        List<DtRuta> rutas = new ArrayList<>();
        rutas.add(new DtRuta("Montevideo-Buenos Aires", "Ruta entre Montevideo y Buenos Aires", "Montevideo a Buenos Aires", LocalTime.of(2, 0), 150, 300, 20, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0), categorias.get(1)), ciudades.get(0), ciudades.get(1)));
        rutas.add(new DtRuta("Buenos Aires-Santiago", "Ruta entre Buenos Aires y Santiago", "Buenos Aires a Santiago", LocalTime.of(3, 0), 200, 400, 25, LocalDate.now(), "default.png", Arrays.asList(categorias.get(1)), ciudades.get(1), ciudades.get(2)));
        rutas.add(new DtRuta("Santiago-Lima", "Ruta entre Santiago y Lima", "Santiago a Lima", LocalTime.of(4, 0), 220, 450, 30, LocalDate.now(), "default.png", Arrays.asList(categorias.get(2), categorias.get(3)), ciudades.get(2), ciudades.get(3)));
        rutas.add(new DtRuta("Lima-São Paulo", "Ruta entre Lima y São Paulo", "Lima a São Paulo", LocalTime.of(5, 0), 250, 500, 35, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0), categorias.get(4)), ciudades.get(3), ciudades.get(4)));
        rutas.add(new DtRuta("São Paulo-Rio de Janeiro", "Ruta entre São Paulo y Rio de Janeiro", "São Paulo a Rio", LocalTime.of(1, 30), 120, 240, 15, LocalDate.now(), "default.png", Arrays.asList(categorias.get(1), categorias.get(3)), ciudades.get(4), ciudades.get(5)));
        rutas.add(new DtRuta("Rio de Janeiro-Asunción", "Ruta entre Rio de Janeiro y Asunción", "Rio a Asunción", LocalTime.of(4, 30), 280, 560, 40, LocalDate.now(), "default.png", Arrays.asList(categorias.get(2)), ciudades.get(5), ciudades.get(6)));
        rutas.add(new DtRuta("Asunción-Montevideo Norte", "Ruta entre Asunción y Montevideo Norte", "Asunción a Montevideo Norte", LocalTime.of(3, 15), 210, 420, 25, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0), categorias.get(3)), ciudades.get(6), ciudades.get(7)));
        rutas.add(new DtRuta("Montevideo Norte-Quito", "Ruta entre Montevideo Norte y Quito", "Montevideo Norte a Quito", LocalTime.of(6, 0), 300, 600, 45, LocalDate.now(), "default.png", Arrays.asList(categorias.get(1), categorias.get(4)), ciudades.get(7), ciudades.get(8)));
        rutas.add(new DtRuta("Quito-Bogotá", "Ruta entre Quito y Bogotá", "Quito a Bogotá", LocalTime.of(2, 45), 180, 360, 20, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0)), ciudades.get(8), ciudades.get(9)));
        rutas.add(new DtRuta("Bogotá-Montevideo", "Ruta entre Bogotá y Montevideo", "Bogotá a Montevideo", LocalTime.of(7, 0), 350, 700, 50, LocalDate.now(), "default.png", Arrays.asList(categorias.get(2), categorias.get(4)), ciudades.get(9), ciudades.get(0)));
        rutas.add(new DtRuta("Montevideo-Lima", "Ruta entre Montevideo y Lima", "Montevideo a Lima", LocalTime.of(5, 0), 260, 520, 30, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0), categorias.get(1)), ciudades.get(0), ciudades.get(3)));
        rutas.add(new DtRuta("Buenos Aires-Rio de Janeiro", "Ruta entre Buenos Aires y Rio de Janeiro", "Buenos Aires a Rio", LocalTime.of(3, 30), 230, 460, 25, LocalDate.now(), "default.png", Arrays.asList(categorias.get(2), categorias.get(3)), ciudades.get(1), ciudades.get(5)));
        rutas.add(new DtRuta("Santiago-Asunción", "Ruta entre Santiago y Asunción", "Santiago a Asunción", LocalTime.of(5, 15), 270, 540, 35, LocalDate.now(), "default.png", Arrays.asList(categorias.get(1)), ciudades.get(2), ciudades.get(6)));
        rutas.add(new DtRuta("Lima-Montevideo Norte", "Ruta entre Lima y Montevideo Norte", "Lima a Montevideo Norte", LocalTime.of(6, 30), 290, 580, 40, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0), categorias.get(4)), ciudades.get(3), ciudades.get(7)));
        rutas.add(new DtRuta("São Paulo-Quito", "Ruta entre São Paulo y Quito", "São Paulo a Quito", LocalTime.of(7, 15), 310, 620, 45, LocalDate.now(), "default.png", Arrays.asList(categorias.get(3)), ciudades.get(4), ciudades.get(8)));
        rutas.add(new DtRuta("Rio de Janeiro-Bogotá", "Ruta entre Rio de Janeiro y Bogotá", "Rio a Bogotá", LocalTime.of(6, 0), 300, 600, 40, LocalDate.now(), "default.png", Arrays.asList(categorias.get(2), categorias.get(4)), ciudades.get(5), ciudades.get(9)));
        rutas.add(new DtRuta("Asunción-Lima", "Ruta entre Asunción y Lima", "Asunción a Lima", LocalTime.of(5, 45), 280, 560, 35, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0)), ciudades.get(6), ciudades.get(3)));
        rutas.add(new DtRuta("Montevideo Norte-São Paulo", "Ruta entre Montevideo Norte y São Paulo", "Montevideo Norte a São Paulo", LocalTime.of(6, 15), 300, 600, 40, LocalDate.now(), "default.png", Arrays.asList(categorias.get(1), categorias.get(2)), ciudades.get(7), ciudades.get(4)));
        rutas.add(new DtRuta("Quito-Rio de Janeiro", "Ruta entre Quito y Rio de Janeiro", "Quito a Rio", LocalTime.of(7, 30), 320, 640, 50, LocalDate.now(), "default.png", Arrays.asList(categorias.get(3), categorias.get(4)), ciudades.get(8), ciudades.get(5)));
        rutas.add(new DtRuta("Bogotá-Santiago", "Ruta entre Bogotá y Santiago", "Bogotá a Santiago", LocalTime.of(5, 0), 250, 500, 30, LocalDate.now(), "default.png", Arrays.asList(categorias.get(0), categorias.get(2)), ciudades.get(9), ciudades.get(2)));

        List<DtAerolinea> aerolineasAsignar = Arrays.asList(
                s.getAerolinea("crystalMotus"),
                s.getAerolinea("skyHigh"),
                s.getAerolinea("blueWing")
        );


        List<DtRuta> rutasAprobadas = new ArrayList<>();
        for (int i = 0; i < rutas.size(); i++) {
            DtAerolinea aerolinea = aerolineasAsignar.get(i % aerolineasAsignar.size());
            s.altaRutaDeVuelo(aerolinea.getNickname(), rutas.get(i));
            if(aerolinea.getNickname().equals("crystalMotus")){
                s.actualizarEstadoRuta(rutas.get(i), EstadoRuta.APROBADA);
                rutasAprobadas.add(rutas.get(i));
            }else if(aerolinea.getNickname().equals("skyHigh")){
                s.actualizarEstadoRuta(rutas.get(i), EstadoRuta.RECHAZADA);
            }
        }

        // -------------- Vuelos ---------------- //

        List<DtVuelo> vuelos = new ArrayList<>();
        vuelos.add(new DtVuelo("Vuelo1", LocalDate.now().plusDays(1), LocalTime.of(2, 0), 100, 20, "default.png", LocalDate.now(), rutasAprobadas.get(0), 0));
        vuelos.add(new DtVuelo("Vuelo2", LocalDate.now().plusDays(2), LocalTime.of(3, 0), 120, 25, "default.png", LocalDate.now(), rutasAprobadas.get(1), 0));
        vuelos.add(new DtVuelo("Vuelo3", LocalDate.now().plusDays(3), LocalTime.of(1, 45), 80, 15, "default.png", LocalDate.now(), rutasAprobadas.get(2), 0));
        vuelos.add(new DtVuelo("Vuelo4", LocalDate.now().plusDays(4), LocalTime.of(2, 30), 150, 30, "default.png", LocalDate.now(), rutasAprobadas.get(3), 0));
        vuelos.add(new DtVuelo("Vuelo5", LocalDate.now().plusDays(5), LocalTime.of(2, 15), 90, 18, "default.png", LocalDate.now(), rutasAprobadas.get(4), 0));
        vuelos.add(new DtVuelo("Vuelo6", LocalDate.now().plusDays(6), LocalTime.of(3, 15), 110, 22, "default.png", LocalDate.now(), rutasAprobadas.get(5), 0));
        vuelos.add(new DtVuelo("Vuelo7", LocalDate.now().plusDays(7), LocalTime.of(1, 50), 95, 20, "default.png", LocalDate.now(), rutasAprobadas.get(2), 0));
        vuelos.add(new DtVuelo("Vuelo8", LocalDate.now().plusDays(8), LocalTime.of(2, 45), 130, 28, "default.png", LocalDate.now(), rutasAprobadas.get(0), 0));
        vuelos.add(new DtVuelo("Vuelo9", LocalDate.now().plusDays(9), LocalTime.of(2, 10), 105, 24, "default.png", LocalDate.now(), rutasAprobadas.get(1), 0));
        vuelos.add(new DtVuelo("Vuelo10", LocalDate.now().plusDays(10), LocalTime.of(3, 0), 120, 26, "default.png", LocalDate.now(), rutasAprobadas.get(2), 0));

        for(DtVuelo vuelo: vuelos){
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

        for(DtPaquete p : paquetes){
            s.altaPaquete(p);
            if(p.getDescuento() == 0 || p.getNombre().equals("VueloExpress") || p.getNombre().equals("LujoMundial") || p.getNombre().equals("EcoVuelo")){
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



        return s;
    }

}
