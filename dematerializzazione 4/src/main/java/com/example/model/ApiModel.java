package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * 
 * La cklasse contiene tutti i campi da inserire nel database tramite le annotazioni
 * Effettuiamo anche un collegamneto alla tabella indiceBolletta
 */


@Entity
public class ApiModel {
	
	@Column(name = "id")
	private @Id @GeneratedValue int id;
	
	@Column(name = "codice")
	private String codice;
	
	@Column(name = "campoUno")
	private String campoUno;
	
	@Column(name = "campoDue")
	private String campoDue;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "idBolletta", nullable = false)
	private IndiceBollette bolletta;
	
	
		
	public ApiModel(String codice, String campoUno, String campoDue, IndiceBollette bolletta) {
		super();
		this.codice = codice;
		this.campoUno = campoUno;
		this.campoDue = campoDue;
		this.bolletta = bolletta;
	}

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

	public String getCampoUno() {
		return campoUno;
	}

	public void setCampoUno(String campoUno) {
		this.campoUno = campoUno;
	}

	public String getCampoDue() {
		return campoDue;
	}

	public void setCampoDue(String campoDue) {
		this.campoDue = campoDue;
	}

	@Override
	public String toString() {
		return "ApiModel [id=" + id + ", codice=" + codice + ", campoUno=" + campoUno + ", campoDue=" + campoDue + "]";
	}
	

	
}
