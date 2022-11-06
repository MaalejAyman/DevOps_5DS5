package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.dto.SecteurActiviteRequestModel;

public interface ISecteurActiviteService {

	List<SecteurActivite> retrieveAllSecteurActivite();

	SecteurActivite addSecteurActivite(SecteurActiviteRequestModel sa);

	void deleteSecteurActivite(Long id);

	SecteurActivite updateSecteurActivite(SecteurActiviteRequestModel sa);

	SecteurActivite retrieveSecteurActivite(Long id);

}
