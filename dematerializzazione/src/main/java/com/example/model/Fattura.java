package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fattura {

	@Column(name= "contatore")
	private int contatore;
	
	public int getContatore() {
		return contatore;
	}

	public void setContatore(int contatore) {
		this.contatore = contatore;
	}

	@Column(name= "id")
	private @Id @GeneratedValue int id;
	
	@Column(name= "codice")
	private String codice;
	
	@Column(name= "campoUno")
	private String campoUno;
	
	@Column(name= "campoDue")
	private String campoDue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCampo1() {
		return campoUno;
	}

	public void setCampo1(String campo1) {
		this.campoUno = campo1;
	}

	public String getCampo2() {
		return campoDue;
	}

	public void setCampo2(String campo2) {
		this.campoDue = campo2;
	}

	public Fattura(String codice, String campo1, String campo2) {
		super();
		this.codice = codice;
		this.campoUno = campo1;
		this.campoDue = campo2;
	}

	@Override
	public String toString() {
		return "Fattura [id=" + id + ", codice=" + codice + ", campo1=" + campoUno + ", campo2=" + campoDue + "]";
	}
	
}
