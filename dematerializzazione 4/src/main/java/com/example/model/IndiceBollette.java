package com.example.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * 
 * La classe è un  modello per la tabella che conterrà tutti gli indici delle3 bollette
 * collegate tra loro tramite chiave esterna
 *
 */
@Entity
public class IndiceBollette {

	
	@Column(name = "idBolletta")
	private @Id @GeneratedValue int id;
	
	@OneToMany(mappedBy = "bolletta")
    private Set<ApiModel> bolletta;

	
	public IndiceBollette() {
		super();
	}	
	
}
