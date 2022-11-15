package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.dto.FactureRequestModel;

public interface IFactureService {
	List<Facture> retrieveAllFactures();

	List<Facture> getFacturesByFournisseur(Long idFournisseur);

	Facture addFacture(FactureRequestModel f);

	void cancelFacture(Long id);

	Facture retrieveFacture(Long id);
	
	void assignOperateurToFacture(Long idOperateur, Long idFacture);

	float pourcentageRecouvrement(Date startDate, Date endDate);

}
