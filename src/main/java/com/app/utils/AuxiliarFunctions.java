package com.app.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.swing.*;

import java.awt.geom.Ellipse2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;

import com.app.clases.ISistema;

import com.app.datatypes.*;

import com.app.enums.TipoImagen;

public class AuxiliarFunctions {

    // Base relativa al proyecto
    private static final String BASE_DIR = Paths.get(System.getProperty("user.home"), "volandouyFiles", "lab-pa-g2-pictures").toString();

    private static final String[] SUB_DIRS = { "users", "vuelos", "rutas" };

    private ISistema sistema;
    private DefaultComboBoxModel<DtUsuario> comboUser = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtAerolinea> comboAerolinea = new DefaultComboBoxModel<>(); // Solo muestra aerolineas
    private DefaultComboBoxModel<DtCliente> comboCliente = new DefaultComboBoxModel<>(); // Solo muestra aerolineas
    private DefaultComboBoxModel<DtRuta> comboRutaDeVuelo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtCiudad> comboCiudadOrigen = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtCiudad> comboCiudadDestino = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPaquete> comboPaquete = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPaquete> comboPaqueteNoComprado = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPaquete> comboPaqueteCliente = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPaquete> comboPaquetesConRutas = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtRuta> comboRutaDeVueloAerolinea = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtVuelo> comboVueloRutaDeVuelo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtReserva> comboReserva = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtReserva> comboReservaVuelo = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtReserva> comboReservaCliente = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtVuelo> comboVuelos = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtPasajero> comboPasajerosReserva = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<DtRuta> comboRutaPaquete = new DefaultComboBoxModel<>();

    private DefaultListModel<String> modeloCategorias = new DefaultListModel<>();

    public AuxiliarFunctions(ISistema s) {
        this.sistema = s;
    }

    //Getters de los modelos
    public DefaultComboBoxModel<DtUsuario> getComboUserModel() { return comboUser; }
    public DefaultComboBoxModel<DtAerolinea> getComboAerolineaModel() { return comboAerolinea; }
    public DefaultComboBoxModel<DtCliente> getComboClienteModel() { return comboCliente; }
    public DefaultComboBoxModel<DtRuta> getComboRutaDeVueloAerolineaModel() { return comboRutaDeVueloAerolinea; }
    public DefaultComboBoxModel<DtRuta> getComboRutaDeVueloModel() { return comboRutaDeVuelo; }
    public DefaultComboBoxModel<DtCiudad> getComboCiudadOrigenModel() { return comboCiudadOrigen; }
    public DefaultComboBoxModel<DtCiudad> getComboCiudadDestinoModel() { return comboCiudadDestino; }
    public DefaultComboBoxModel<DtPaquete> getComboPaqueteModel() { return comboPaquete; }
    public DefaultComboBoxModel<DtPaquete> getComboPaqueteClienteModel() { return comboPaqueteCliente; }
    public DefaultComboBoxModel<DtPaquete> getComboPaqueteNoCompradoModel() { return comboPaqueteNoComprado; }
    public DefaultComboBoxModel<DtPaquete> getComboPaquetesConRutasModel() { return comboPaquetesConRutas; }
    public DefaultComboBoxModel<DtVuelo> getComboVueloRutaDeVueloModel() { return  comboVueloRutaDeVuelo; }
    public DefaultComboBoxModel<DtReserva> getComboReservaVueloModel() { return comboReservaVuelo; }
    public DefaultComboBoxModel<DtReserva> getComboReservaVueloClienteModel() { return comboReservaCliente; }
    public DefaultComboBoxModel<DtPasajero> getComboPasajerosReserva() { return comboPasajerosReserva; }
    public DefaultComboBoxModel<DtRuta> getComboRutaPaquete() { return comboRutaPaquete; }

    public DefaultListModel<String> getModeloCategorias() { return modeloCategorias; }

    //Función para validar 1 o más "JComboBox"
    public boolean estanVaciosJComboBox(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            Object item = combo.getSelectedItem();
            if (item == null || item.toString().trim().isEmpty() || item.toString().equals("N/A")) {
                return true;
            }
        }
        return false;
    }

    public boolean estanVaciosJTextField(JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText() == null || field.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void limpiarJTextField(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public void cargarTodosLosDatos(){
        cargarUsuariosComboBox();
        cargarAerolineasComboBox();
        cargarClientesComboBox();
        cargarCiudadesComboBox();
        cargarRutasDeVueloComboBox();
        cargarPaqueteComboBox();
        cargarPaqueteNoCompradoComboBox();
        cargarPaquetesConRutasComboBox();
    }

    public void cargarUsuariosComboBox() {
        comboUser.removeAllElements();
        List<DtUsuario> usuarios = sistema.listarUsuarios();
        if (usuarios != null && !usuarios.isEmpty()){
            for (DtUsuario u : usuarios) {
                comboUser.addElement(u);
            }
        } else {
            comboUser.addElement(new DtUsuario() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarCategorias() {
        modeloCategorias.clear();

        List<DtCategoria> categorias = sistema.listarCategorias();
        for (DtCategoria c : categorias) {
            modeloCategorias.addElement(c.getNombre());
        }
    }

    public void cargarUsuariosComboBox(String nickname) {
        comboUser.removeAllElements();
        List<DtUsuario> usuarios = sistema.listarUsuarios();
        if (usuarios != null  && !usuarios.isEmpty()){
            for (DtUsuario u : usuarios) {
                comboUser.addElement(u);
                if (u.getNickname().equals(nickname)){
                    comboUser.setSelectedItem(u);
                }
            }
        } else {
            comboUser.addElement(new DtUsuario() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarAerolineasComboBox() {
        comboAerolinea.removeAllElements();
            List<DtAerolinea> aerolineas = sistema.listarAerolineas();
            if (aerolineas != null && !aerolineas.isEmpty()){
                for (DtAerolinea a : aerolineas) {
                    comboAerolinea.addElement(a);
                }
            } else {
                comboAerolinea.addElement(new DtAerolinea() {
                @Override
                public String toString() { return "N/A"; }
            });
            }
    }

    public void cargarClientesComboBox() {
        comboCliente.removeAllElements();
        List<DtCliente> clientes = sistema.listarClientes();
        if (clientes != null && !clientes.isEmpty()){
            for (DtCliente c : clientes) {
                comboCliente.addElement(c);
            }
        } else {
            comboCliente.addElement(new DtCliente() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBox() {
        comboRutaDeVuelo.removeAllElements();
        List<DtRuta> rt = sistema.listarRutasDeVuelo();
        if (rt != null && !rt.isEmpty()) {
            for (DtRuta rtv : rt) {
                comboRutaDeVuelo.addElement(rtv);
            }
        } else {
            comboRutaDeVuelo.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBoxAerolinea(String nickname) {
        comboRutaDeVueloAerolinea.removeAllElements();
        DtAerolinea aerolineaExistente = sistema.getAerolinea(nickname);
        List<DtRuta> rutas = aerolineaExistente.getRutasDeVuelo();
        if (rutas != null && !rutas.isEmpty()) {
            for (DtRuta r : rutas) {
                comboRutaDeVueloAerolinea.addElement(r);
            }
        } else {
            comboRutaDeVueloAerolinea.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasDeVueloComboBoxAerolinea(String nickname, String nombreRuta) {
        comboRutaDeVueloAerolinea.removeAllElements();
        DtAerolinea aerolineaExistente = sistema.getAerolinea(nickname);
        List<DtRuta> rutas = aerolineaExistente.getRutasDeVuelo();
        if (rutas != null && !rutas.isEmpty()) {
            for (DtRuta r : rutas) {
                comboRutaDeVueloAerolinea.addElement(r);
                if (r.getNombre().equals(nombreRuta)){
                    comboRutaDeVueloAerolinea.setSelectedItem(r);
                }
            }
        } else {
            comboRutaDeVueloAerolinea.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarCiudadesComboBox() {
        comboCiudadOrigen.removeAllElements();
        comboCiudadDestino.removeAllElements();

        List<DtCiudad> c = sistema.listarCiudades();
        if (c != null && !c.isEmpty()) {
            for (DtCiudad ciudad : c) {
                comboCiudadOrigen.addElement(ciudad);
                comboCiudadDestino.addElement(ciudad);
            }
        } else {
            comboCiudadOrigen.addElement(new DtCiudad() {
                @Override
                public String toString() { return "N/A"; }
            });
            comboCiudadDestino.addElement(new DtCiudad() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaqueteComboBox() {
        comboPaquete.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetes();
        if (p != null && !p.isEmpty()) {
            for (DtPaquete pqt : p) {
                comboPaquete.addElement(pqt);
            }
        } else {
            comboPaquete.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaqueteNoCompradoComboBox() {
        comboPaqueteNoComprado.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetesNoComprados();
        if (p != null && !p.isEmpty()) {
            for (DtPaquete pqt : p) {
                comboPaqueteNoComprado.addElement(pqt);
            }
        } else {
            comboPaqueteNoComprado.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaquetesConRutasComboBox() {
        comboPaquetesConRutas.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetesConRutas();
        if (p != null && !p.isEmpty()) {
            for (DtPaquete pqt : p) {
                comboPaquetesConRutas.addElement(pqt);
            }
        } else {
            comboPaquetesConRutas.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarPaqueteClienteComboBox(String nickname) {
        comboPaqueteCliente.removeAllElements();
        List<DtPaquete> p = sistema.listarPaquetesCliente(nickname);
        if (p != null && !p.isEmpty()) {
            for (DtPaquete pqt : p) {
                comboPaqueteCliente.addElement(pqt);
            }
        } else {
            comboPaqueteCliente.addElement(new DtPaquete() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }


    public void cargarDatosReservaVueloComboBox(String nombreVuelo) {
        comboReservaVuelo.removeAllElements();
        List<DtReserva> reservas = sistema.listarReservasVuelo(nombreVuelo);
        if (reservas != null && !reservas.isEmpty()) {
            for (DtReserva r : reservas) {
                comboReservaVuelo.addElement(r);
            }
        } else {
            comboReservaVuelo.addElement(new DtReserva() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarDatosReservaClienteComboBox(String nickname) {
        comboReservaCliente.removeAllElements();
        List<DtReserva> reservas = sistema.listarReservasCliente(nickname);
        if (reservas != null && !reservas.isEmpty()) {
            for (DtReserva r : reservas) {
                comboReservaCliente.addElement(r);
            }
        } else {
            comboReservaCliente.addElement(new DtReserva() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarVuelosComboBoxRuta(String nombre){
        comboVueloRutaDeVuelo.removeAllElements();
        List<DtVuelo> vuelos = sistema.listarVuelosRuta(nombre);
        if (vuelos != null && !vuelos.isEmpty()) {
            for (DtVuelo v : vuelos){
                comboVueloRutaDeVuelo.addElement(v);
            }
        } else {
            comboVueloRutaDeVuelo.addElement(new DtVuelo() {
                @Override
                public String toString() { return "N/A"; }
            });
        }

    }

    public void cargarPasajeros(DtReserva r){
        comboPasajerosReserva.removeAllElements();
        List<DtPasajero> pasajeros = sistema.listarPasajeros(r);
        if (pasajeros != null && !pasajeros.isEmpty()) {
            for (DtPasajero p : pasajeros){
                comboPasajerosReserva.addElement(p);
            }
        } else {
            comboPasajerosReserva.addElement(new DtPasajero() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public void cargarRutasPaquete(String nombrePaquete){
        comboRutaPaquete.removeAllElements();
        List<DtRuta> rutas = sistema.listarRutasDeVueloPaquete(nombrePaquete);
        if (rutas != null && !rutas.isEmpty()) {
            for (DtRuta r : rutas){
                comboRutaPaquete.addElement(r);
            }
        } else {
            comboRutaPaquete.addElement(new DtRuta() {
                @Override
                public String toString() { return "N/A"; }
            });
        }
    }

    public boolean esFechaValida(LocalDate fecha) {
        if (fecha == null) {
            return false;
        }

        LocalDate hoy = LocalDate.now();
        LocalDate limitePasado = hoy.minusYears(200);

        return !fecha.isAfter(hoy) && !fecha.isBefore(limitePasado);
    }

    public void validarNombreVuelo(String nombre) {
        if (!nombre.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("El nombre del vuelo solo puede contener letras, números y espacios.");
        }
    }

    public void validarCorreo(String correo) {
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("El correo es inválido.");
        }
    }

    public void validarDocumento(String documentoCi){
        if (!documentoCi.matches("^\\d{8}$")){
            throw new IllegalArgumentException("El documento no es valido");
        }
    }

    // Obtener ruta absoluta del proyecto en tiempo de ejecución
    private static Path getProjectRoot() {
        return Paths.get("").toAbsolutePath();
    }

    public static void initFolders() {
        try {
            Path basePath = getProjectRoot().resolve(BASE_DIR);

            // Crear carpeta base si no existe
            if (!Files.exists(basePath)) {
                Files.createDirectories(basePath);
            }

            // Crear subcarpetas
            for (String sub : SUB_DIRS) {
                Path subPath = basePath.resolve(sub);
                if (!Files.exists(subPath)) {
                    Files.createDirectories(subPath);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon createRoundImageIcon(ImageIcon originalIcon) {
        Image originalImage = originalIcon.getImage();
        int diameter = Math.min(originalImage.getWidth(null), originalImage.getHeight(null));

        // Create a new BufferedImage with transparency
        BufferedImage roundImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundImage.createGraphics();

        // Set rendering hints for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a circular clip
        Shape circle = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2d.setClip(circle);

        // Draw the original image within the clip
        g2d.drawImage(originalImage, 0, 0, diameter, diameter, null);
        g2d.dispose();

        return new ImageIcon(roundImage);
    }

    public static Path getImagePath(String urlImagen, TipoImagen tipo){
      return Paths.get(BASE_DIR, tipo.getFolder(), urlImagen);
    };

    public static Path getImagePath(TipoImagen tipo){
        return Paths.get(BASE_DIR, tipo.getFolder());
    };

    public static File guardarImagen(File original, TipoImagen tipo) throws IOException {
        Path carpetaDestino = getProjectRoot().resolve(BASE_DIR).resolve(tipo.getFolder());
        File carpeta = carpetaDestino.toFile();

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombre = original.getName();
        String extension = "";
        int i = nombre.lastIndexOf('.');
        if (i > 0) {
            extension = nombre.substring(i); // incluye el punto
        }

        Set<String> extensionesPermitidas = Set.of(".png", ".jpg", ".jpeg", ".webp");
        if (!extensionesPermitidas.contains(extension)) {
            throw new IllegalArgumentException("Formato de imagen no soportado: " + extension);
        }

        String nombreUnico = UUID.randomUUID().toString() + extension;
        File nuevoArchivo = new File(carpeta, nombreUnico);
        Files.copy(original.toPath(), nuevoArchivo.toPath());

        return nuevoArchivo;
    }

    public static byte[] obtenerImagen(String nombre, TipoImagen tipo) throws IOException {

        Path carpeta = getProjectRoot().resolve(BASE_DIR).resolve(tipo.getFolder());
        Path imagen = carpeta.resolve(nombre);

        if (Files.exists(imagen)) {
            return Files.readAllBytes(imagen);
        }

        String recursoDefault;

        switch (tipo) {
            case USUARIO:
                recursoDefault = "pictures/users/default.png";
                break;
            case RUTA:
                recursoDefault = "pictures/rutas/default.png";
                break;
            case VUELO:
                recursoDefault = "pictures/vuelos/default.jpg";
                break;
            default:
                recursoDefault = "pictures/default.png"; // opcional
        }

        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(recursoDefault)) {

            if (is == null) {
                throw new FileNotFoundException("No existe el recurso por defecto: " + recursoDefault);
            }

            return is.readAllBytes();
        }
    }



    public static void borrarImagen(String imgName, TipoImagen tipo) {
        Path path = getProjectRoot().resolve(BASE_DIR).resolve(tipo.getFolder()).resolve(imgName);
        File imagenABorrar = path.toFile();

        if (imagenABorrar.exists()) {
            if (imagenABorrar.delete()) {
                System.out.println("Imagen eliminada con éxito");
            } else {
                System.out.println("No se pudo eliminar la imagen");
            }
        } else {
            System.out.println("Imagen no encontrada");
        }
    }

    public static void mostrarFoto(JPanel imagenPanel, ImageIcon imagen, int ancho, int largo, TipoImagen tipo){
        Image imgRaw = imagen.getImage();
        Image imgScaled = imgRaw.getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
        imagen = new ImageIcon(imgScaled);

        if (tipo == TipoImagen.USUARIO){
            imagen = AuxiliarFunctions.createRoundImageIcon(imagen);
        }

        JLabel imgLabel = new JLabel(imagen);

        imagenPanel.setLayout(new FlowLayout());
        imagenPanel.removeAll();
        imagenPanel.add(imgLabel);
        imagenPanel.revalidate();
        imagenPanel.repaint();
    }

    public void validarTextoSoloLetra(String nombre) {
        if (!nombre.matches("^[\\p{L} ]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras, números y espacios.");
        }
    }

}
