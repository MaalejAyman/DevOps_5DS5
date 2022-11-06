package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.dto.ReglementRequestModel;

public interface IReglementService {

	List<Reglement> retrieveAllReglements();
	Reglement addReglement(ReglementRequestModel r);
	Reglement retrieveReglement(Long id);
	List<Reglement> retrieveReglementByFacture(Long idFacture);
	float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate); 

}
