package ec.edu.puce.professorCheck.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

import ec.edu.puce.professorCheck.constantes.EnumEstado;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "email", length = 200)
	private String email;
	@Column(name = "identificacion", length = 20)
	private String identificacion;// o username
	@Column(name = "nombre", nullable = false, length = 200)
	private String nombre;
	@Column(name = "apellido", nullable = false, length = 200)
	private String apellido;
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	@Column(name = "direccion", length = 255)
	private String direccion;
	@Column(name = "password", length = 64)
	private String password;
	@Column(name = "foto", length = 4000)
	private String foto;
	@ManyToOne(optional = true)
	@JoinColumn(name = "especialidad", referencedColumnName = "codigo", nullable = true)
	private Parametro especialidad;
	@Column(name = "fecha_creacion", nullable = true)
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaNacimiento;
	@Column(name = "genero", length = 1, nullable = true)
	private String genero;
	@Column(name = "codigo_medico", length = 20, nullable = true)
	private String codigoMedico;
	@ManyToOne(optional = true)
	@JoinColumn(name = "pais", referencedColumnName = "codigo", nullable = true)
	private Parametro pais;
	@ManyToOne(optional = true)
	@JoinColumn(name = "ciudad", referencedColumnName = "codigo", nullable = true)
	private Parametro ciudad;
	@Column(name = "telefono_fijo", length = 20, nullable = true)
	private String telefonoFijo;
	@Column(name = "celular", length = 20, nullable = true)
	private String celular;
	@Column(name = "valor_consulta", nullable = true)
	private BigDecimal valorConsulta;
	@Column(name = "tiene_Asistente", nullable = true)
	private Boolean tieneAsistente;
	@Column(name = "email_asistente", length = 200)
	private String emailAsistente;
	@ManyToOne(optional = true)
	@JoinColumn(name = "asistente", referencedColumnName = "email", nullable = true)
	private Usuario asistente;

	@Transient
	private boolean tachado;
	@Transient
	private boolean registroNuevo;
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioRegistra")
	// private List<Ficha> ficha;
	// @ManyToMany(mappedBy = "usuarios")
	// private List<Rol> roles;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = { @JoinColumn(name = "email", referencedColumnName = "email") }, inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private List<Rol> roles;
	@Transient
	private StreamedContent fotoTransient;

	// @ManyToOne(optional = false)
	// @JoinColumn(name = "institucion_id", referencedColumnName = "id")
	// private Institucion institucion;

	// public Institucion getInstitucion() {
	// return institucion;
	// }
	//
	// public void setInstitucion(Institucion institucion) {
	// this.institucion = institucion;
	// }
	private String confirmaPassword;

	public Usuario() {
	}

	public Usuario(String email) {
		this.email = email;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public boolean isRegistroNuevo() {
		return registroNuevo;
	}

	public void setRegistroNuevo(boolean registroNuevo) {
		this.registroNuevo = registroNuevo;
	}

	public EnumEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumEstado estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isTachado() {
		if (this.estado.equals(EnumEstado.ACT)) {
			tachado = true;
		} else {
			tachado = false;
		}
		return tachado;
	}

	public void setTachado(boolean tachado) {
		this.tachado = tachado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean tieneRolPaciente() {
		for (Rol rol : roles) {
			if (rol.getId().esPaciente()) {
				return true;
			}
		}
		return false;
	}

	public Parametro getEspecialidad() {
		if(especialidad==null){
			especialidad=new Parametro();
		}
		return especialidad;
	}

	public void setEspecialidad(Parametro especialidad) {
		this.especialidad = especialidad;
	}

	public String getConfirmaPassword() {
		return confirmaPassword;
	}

	public void setConfirmaPassword(String confirmaPassword) {
		this.confirmaPassword = confirmaPassword;
	}

	public StreamedContent getFotoTransient() {
		return fotoTransient;
	}

	public void setFotoTransient(StreamedContent fotoTransient) {
		this.fotoTransient = fotoTransient;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Parametro getPais() {
		if(pais==null){
			pais=new Parametro();
		}
		return pais;
	}

	public void setPais(Parametro pais) {
		this.pais = pais;
	}

	public Parametro getCiudad() {
		if(ciudad==null){
			ciudad=new Parametro();
		}
		return ciudad;
	}

	public void setCiudad(Parametro ciudad) {
		this.ciudad = ciudad;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public BigDecimal getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(BigDecimal valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	public Boolean getTieneAsistente() {
		return tieneAsistente;
	}

	public void setTieneAsistente(Boolean tieneAsistente) {
		this.tieneAsistente = tieneAsistente;
	}

	public String getEmailAsistente() {
		return emailAsistente;
	}

	public void setEmailAsistente(String emailAsistente) {
		this.emailAsistente = emailAsistente;
	}

	public Usuario getAsistente() {
		return asistente;
	}

	public void setAsistente(Usuario asistente) {
		this.asistente = asistente;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getCodigoMedico() {
		return codigoMedico;
	}

	public void setCodigoMedico(String codigoMedico) {
		this.codigoMedico = codigoMedico;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder("Usuario{"
				+ "identificacion=" + identificacion + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", estado=" + estado
				+ ", direccion=" + direccion + ", email=" + email
				+ ", registroNuevo=" + registroNuevo + '}');

		toString.append(", roles=[");

		if (roles != null && roles.size() > 0) {
			for (Rol r : roles) {
				toString.append(r.getId()).append(",");
			}
			toString.deleteCharAt(toString.length() - 1);
		}
		toString.append("]");
		toString.append("}");

		return toString.toString();
	}
}