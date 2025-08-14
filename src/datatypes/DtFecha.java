package datatypes;

public class DtFecha {
    private int dia;
    private int mes;
    private int año;

    public DtFecha(int dia,int mes,int año){
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DtFecha other = (DtFecha) obj;
        return dia == other.dia && mes == other.mes && año == other.año;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + año;
    }
}
