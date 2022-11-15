package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.dto.FournisseurRequestModel;

public interface IFournisseurService {

	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(FournisseurRequestModel fournisseur);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(FournisseurRequestModel fournisseur);

	Fournisseur retrieveFournisseur(Long id);
	
	void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur);

}
