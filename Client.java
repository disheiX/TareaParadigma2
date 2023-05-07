import java.util.Date;

public class Client {
    private Credenciales credenciales;
    private Plan plan;
    private Sede sede;
    private Fechas fechas;

    // Constructor
    public Client(Credenciales _credenciales, Plan _plan, Sede _sede, Fechas _fechas) {
        this.credenciales = _credenciales;
        this.plan = _plan;
        this.sede = _sede;
        this.fechas = _fechas;
    }

    // Setters
    public void setCredenciales(String _rut, String _nombre, int _edad) {
        this.credenciales.nombre = _nombre;
        this.credenciales.edad = _edad;
        this.credenciales.rut = _rut;
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
        return this.credenciales.nombre;
    }

    public String getRut() {
        return this.credenciales.rut;
    }

    public String[] getData() {
        return new String[] {
                this.credenciales.rut,
                this.credenciales.nombre,
                Integer.toString(this.credenciales.edad),
                this.plan.codPlan,
                this.plan.descPlan,
                this.sede.codSede,
                this.sede.ubicSede,
                Utils.fechaToString(this.fechas.desde),
                Utils.fechaToString(this.fechas.hasta)
        };
    }

    public static class Credenciales {
        private String nombre;
        private int edad;
        private String rut;

        public Credenciales(String _nombre, int _edad, String _rut) {
            this.nombre = _nombre;
            this.edad = _edad;
            this.rut = _rut;
        }
    }

    public static class Plan {
        private String codPlan;
        private String descPlan;

        public Plan(String _codPlan, String _descPlan) {
            this.codPlan = _codPlan;
            this.descPlan = _descPlan;
        }
    }

    public static class Sede {
        private String codSede;
        private String ubicSede;

        public Sede(String _codSede, String _ubicSede) {
            this.codSede = _codSede;
            this.ubicSede = _ubicSede;
        }
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
