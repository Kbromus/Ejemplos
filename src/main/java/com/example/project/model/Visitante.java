package com.example.project.model;

public class Visitante {
	
	private String nombre;
	private String ip;
	private String empresa;
	private int aportaciones;
	private String ultimoAcceso; 
	
	/**
	 * Default constructor
	 */
	private Visitante(){
	}
	/**
	 * Constructor using Builder
	 * @param builder
	 */
	private Visitante (VisitanteBuilder builder){
		setAportaciones(builder.aportaciones);
		setEmpresa(builder.empresa);
		setIp(builder.ip);
		setNombre(builder.nombre);
		setUltimoAcceso(builder.ultimoAcceso);
	}
	 
	public static VisitanteBuilder newBuilder() {
	    return new VisitanteBuilder();
	}
	public static VisitanteBuilder newBuilder(Visitante copy) {
	    	VisitanteBuilder builder = new VisitanteBuilder();
	    	builder.aportaciones = copy.getAportaciones();
	    	builder.empresa = copy.getEmpresa();
	    	builder.ip = copy.getIp();
	    	builder.nombre = copy.getNombre();
	    	builder.ultimoAcceso = copy.getUltimoAcceso();
	        return builder;
	    }
	
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the aportaciones
	 */
	public int getAportaciones() {
		return aportaciones;
	}
	/**
	 * @param aportaciones the aportaciones to set
	 */
	public void setAportaciones(int aportaciones) {
		this.aportaciones = aportaciones;
	}
	
	/**
	 * @return the ultimoAcceso
	 */
	public String getUltimoAcceso() {
		return ultimoAcceso;
	}
	/**
	 * @param ultimoAcceso the ultimoAcceso to set
	 */
	public void setUltimoAcceso(String ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	/**
	 * 
	 */
	public static final class VisitanteBuilder{
		private String ultimoAcceso;
		private String nombre;
		private String ip;
		private String empresa;
		private int aportaciones;
		
		private VisitanteBuilder() {}
		
		public VisitanteBuilder withNombre(String nombre){
			this.nombre = nombre;
			return this;
		}
		public VisitanteBuilder withIp(String ip){
			this.ip = ip;
			return this;
		}
		public VisitanteBuilder withEmpresa(String empresa){
			this.empresa = empresa;
			return this;
		}
		public VisitanteBuilder withAportaciones(int aportaciones){
			this.aportaciones = aportaciones;
			return this;
		}
		public VisitanteBuilder withAcceso(String fecha){
			this.ultimoAcceso = fecha;
			return this;
		}
		public Visitante build() {
	        return new Visitante(this);
	    }
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(nombre);
		builder.append(":");
		builder.append(ip);
		builder.append(":");
		builder.append(empresa);
		builder.append(":");
		builder.append(aportaciones);
		builder.append(":");
		builder.append(ultimoAcceso);
		return builder.toString();
	}

}
