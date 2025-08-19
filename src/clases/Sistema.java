package clases;

import datatypes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Sistema implements ISistema {
    private static Sistema instancia;
    private List<Aerolinea> aerolineas = new ArrayList<>();
    private List<RutaDeVuelo> rutas = new ArrayList<>();
    private List<Vuelo> vuelos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioSeleccionado; // Guarda selección actual


    private Sistema() {}

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public List<String> listarAerolineas() {
        List<String> nickname = new ArrayList<>();
        for (Aerolinea a : aerolineas) {
            nickname.add(a.getNickname());
        }
        return nickname;
    }

    public List<String> listarRutasDeAerolinea(String nickname) {
        List<String> nombresRutas = new ArrayList<>();
        for (Aerolinea a : aerolineas) {
            if (a.getNickname().equals(nickname)) {
                for (RutaDeVuelo r : a.getRutasDeVuelo()) {
                    nombresRutas.add(r.getNombre());
                }
                break;
            }
        }
        return nombresRutas;
    }

    public DtRuta consultarRuta(String nombre) {
        for (RutaDeVuelo r : rutas) {
            if (r.getNombre().equals(nombre)) {
                // Convertir RutaDeVuelo para mostrar datos
                return new DtRuta(
                        r.getNombre(),
                        r.getDescripcion(),
                        r.getHora(),
                        r.getCostoTurista(),
                        r.getCostoEjecutivo(),
                        r.getEquipajeExtra(),
                        r.getFechaAlta(),
                );
            }
        }
        return null;
    }

    public DtVuelo consultarVuelo(String nickename) {
        for (Vuelo v : vuelos) {
            if (v.getNombre().equals(nickename)) {
                return v.getDatos();
            }
        }
        return null;
    }

    public boolean existeRuta(String nombre) {
        for (RutaDeVuelo r : rutas) {
            if (r.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void altaRutaDeVuelo(String nombreAerolinea, DtRuta datosRuta) {
        Aerolinea aerolinea = null;
        for (Aerolinea a : aerolineas) {
            if (a.getNickname().equals(nombreAerolinea)) {
                aerolinea = a;
                break;
            }
        }
        if (aerolinea == null) return;

        // Nueva ruta
        RutaDeVuelo nuevaRuta = new RutaDeVuelo(datosRuta);

        rutas.add(nuevaRuta);
        aerolinea.getRutasDeVuelo().add(nuevaRuta);
    }

    public List<DtUsuario> listarUsuarios() {
        return usuarios.stream()
                .map(u -> {
                    if (u instanceof Cliente) {
                        Cliente c = (Cliente)u;
                        return new DtCliente(
                                c.getNickname(),
                                c.getNombre(),
                                c.getEmail(),
                                c.getApellido(),
                                c.getFechaNacimiento(),
                                c.getNacionalidad(),
                                c.getTipoDocumento(),
                                c.getNumeroDocumento(),
                                null
                        );
                    } else if (u instanceof Aerolinea) {
                        Aerolinea a = (Aerolinea)u;
                        return new DtAerolinea(
                                a.getNickname(),
                                a.getNombre(),
                                a.getEmail(),
                                a.getDescripcion(),
                                a.getLinkWeb(),
                                null
                        );
                    }
                    return new DtUsuario(u.getNickname(), u.getNombre(), u.getEmail());
                })
                .collect(Collectors.toList());
    }

    public void elegirUsuario(String nickname) {
        this.usuarioSeleccionado = usuarios.stream()
                .filter(u -> u.getNickname().equals(nickname))
                .findFirst()
                .orElse(null);
    }

    public DtUsuario getUsuarioSeleccionado() {
        if (usuarioSeleccionado == null) return null;

        if (usuarioSeleccionado instanceof Cliente) {
            Cliente c = (Cliente)usuarioSeleccionado;
            return new DtCliente(
                    c.getNickname(),
                    c.getNombre(),
                    c.getEmail(),
                    c.getApellido(),
                    c.getFechaNacimiento(),
                    c.getNacionalidad(),
                    c.getTipoDocumento(),
                    c.getNumeroDocumento(),
                    null
            );
        } else if (usuarioSeleccionado instanceof Aerolinea) {
            Aerolinea a = (Aerolinea)usuarioSeleccionado;
            return new DtAerolinea(
                    a.getNickname(),
                    a.getNombre(),
                    a.getEmail(),
                    a.getDescripcion(),
                    a.getLinkWeb(),
                    null
            );
        }
        return new DtUsuario(
                usuarioSeleccionado.getNickname(),
                usuarioSeleccionado.getNombre(),
                usuarioSeleccionado.getEmail()
        );
    }

    public List<DtReserva> mostrarReservas() {
        if (usuarioSeleccionado instanceof Cliente) {
            Cliente c = (Cliente) usuarioSeleccionado;
            List<DtReserva> dtReservas = new ArrayList<>();

            for (DtReserva r : c.getReservas()) {
                dtReservas.add(new DtReserva(
                        r.getFecha(),
                        r.getTipoAsiento(),
                        r.getCantPasajes(),
                        r.getEquipajeExtra(),
                        r.getCosto(),
                        r.getPasajeros(),
                        c, // Cliente
                        r.getVuelo(),
                        r.getPaquete()
                ));
            }
            return dtReservas;
        }
        return new ArrayList<>();
    }
    public List<DtPaquete> mostrarPaquetes() {
        List<DtPaquete> paquetes = new ArrayList<>();

        if (usuarioSeleccionado instanceof Cliente) {
            Cliente cliente = (Cliente) usuarioSeleccionado;
            for (DtPaquete paquete : cliente.getPaquetesAdquiridos()) {
                paquetes.add(new DtPaquete(
                        paquete.getNombre(),
                        paquete.getDescripcion(),
                        paquete.getValidezDias(),
                        paquete.getDescuento(),
                        paquete.getCosto(),
                ));
            }
        }
        return paquetes;
    }

    public List<DtRuta> mostrarRutasDeVuelo() {
        List<DtRuta> rutas = new ArrayList<>();

        if (usuarioSeleccionado instanceof Aerolinea) {
            Aerolinea aerolinea = (Aerolinea) usuarioSeleccionado;

            // Convertir cada RutaDeVuelo a DtRutaDeVuelo
            for (RutaDeVuelo ruta : aerolinea.getRutasDeVuelo()) {
                rutas.add(convertirRutaADtRuta(ruta));
            }
        }
        return rutas;
    }

    private DtRuta convertirRutaADtRuta(RutaDeVuelo ruta) {
        return new DtRuta(
                ruta.getNombre(),
                ruta.getDescripcion(),
                ruta.getHora(),
                ruta.getCostoTurista(),
                ruta.getCostoEjecutivo(),
                ruta.getEquipajeExtra(),
                ruta.getFechaAlta(),
        );
    }

    @Override
    public void ejecutar() {
        System.out.println("Sistema ejecutando...");
    }
}
