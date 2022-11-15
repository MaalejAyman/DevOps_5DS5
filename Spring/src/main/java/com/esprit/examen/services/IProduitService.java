package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.dto.ProduitRequestModel;

public interface IProduitService {

	List<Produit> retrieveAllProduits();

	Produit addProduit(ProduitRequestModel p);

	void deleteProduit(Long id);

	Produit updateProduit(ProduitRequestModel prod);

	Produit retrieveProduit(Long id);

	void assignProduitToStock(Long idProduit, Long idStock);

}
