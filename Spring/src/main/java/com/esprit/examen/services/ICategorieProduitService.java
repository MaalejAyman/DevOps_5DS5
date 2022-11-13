package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.dto.CategorieProduitRequestModel;


public interface ICategorieProduitService {

	List<CategorieProduit> retrieveAllCategorieProduits();

	CategorieProduit addCategorieProduit(CategorieProduitRequestModel cp);

	void deleteCategorieProduit(Long id);

	CategorieProduit updateCategorieProduit(CategorieProduitRequestModel cp);

	CategorieProduit retrieveCategorieProduit(Long id);
}
