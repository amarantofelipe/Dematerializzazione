package com.example.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class DataConfig {
	 	@PersistenceContext
	    private EntityManager entityManager;
	 	
	 	/**
	 	 * Con quersto metodo creiamo ed eseguiamo le query per la modifica della tabella che va a creare la classe ApiController
	 	 * @param tableName
	 	 */
	  	@Transactional
	    public void alterTable(String tableName) {

	        String query = "ALTER TABLE api_model RENAME TO " + tableName + ";";
	        String crea = "CREATE TABLE api_model (id int AUTO_INCREMENT PRIMARY KEY, codice varchar(255), campo_uno varchar(255), campo_due varchar(255), id_bolletta int)";
	        entityManager.createNativeQuery(query).executeUpdate();
	        entityManager.createNativeQuery(crea).executeUpdate();
	    }
}
