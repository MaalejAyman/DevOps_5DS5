package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.dto.OperateurRequestModel;


public interface IOperateurService {

	List<Operateur> retrieveAllOperateurs();

	Operateur addOperateur(OperateurRequestModel o);

	void deleteOperateur(Long id);

	Operateur updateOperateur(OperateurRequestModel o);

	Operateur retrieveOperateur(Long id);

}
