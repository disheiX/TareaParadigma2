import java.util.Date;

public class Client {
    private String nombre;
    private int edad;
    private String rut;
    private Plan plan;
    private Sede sede;
    private Fechas fechas;

    // Constructor
    public Client(String _nombre, int _edad, String _rut, Plan _plan, Sede _sede, Fechas _fechas) {
        this.nombre = _nombre;
        this.edad = _edad;
        this.rut = _rut;
        this.plan = _plan;
        this.sede = _sede;
        this.fechas = _fechas;
    }

    // Setters
    public void setRut(String _rut) {
        this.rut = _rut;
    }
    public void setNombre(String _nombre) {
        this.nombre = _nombre;
    }
    public void setEdad(int _edad) {
        this.edad = _edad;
    }

    public void setFecha(Date _desde, Date _hasta) {
        this.fechas.desde = _desde;
        this.fechas.hasta = _hasta;
    }

    public void setPlan(String _codPlan, String _descPlan) {
        this.plan.codPlan = _codPlan;
        this.plan.descPlan = _descPlan;
    }

    public void setSede(String _codSede, String _ubicSede) {
        this.sede.codSede = _codSede;
        this.sede.ubicSede = _ubicSede;
    }

    // Getters
    public String getNombre() {
        return this.nombre;
    }

    public String getRut() {
        return this.rut;
    }

    public String getSede() {
        return this.sede.codSede;
    }

    public String getPlan() {
        return this.plan.codPlan;
    }

    public String[] getData() {
        return new String[] {
                this.rut,
                this.nombre,
                Integer.toString(this.edad),
                this.plan.codPlan,
                this.plan.descPlan,
                Utils.fechaToString(this.fechas.desde),
                Utils.fechaToString(this.fechas.hasta),
                this.sede.codSede,
                this.sede.ubicSede
        };
    }

    public static class Fechas {
        private Date desde;
        private Date hasta;

        public Fechas(Date _desde, Date _hasta) {
            this.desde = _desde;
            this.hasta = _hasta;
        }
    }
}
