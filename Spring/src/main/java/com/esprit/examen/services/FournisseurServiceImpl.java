package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.dto.FournisseurRequestModel;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;

import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	DetailFournisseurRepository detailFournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		List<Fournisseur> fournisseurs =  fournisseurRepository.findAll();
		for (Fournisseur fournisseur : fournisseurs) {
			log.info(" fournisseur : " + fournisseur);
		}
		return fournisseurs;
	}


/*
	public Fournisseur addFournisseur(Fournisseur f ) {
		DetailFournisseur df= new DetailFournisseur();//Slave
		df.setDateDebutCollaboration(new Date()); //util
		//On affecte le "Slave" au "Master"
		f.setDetailFournisseur(df);	
		fournisseurRepository.save(f);
		return f;
	}
*/
	
	public Fournisseur addFournisseur(FournisseurRequestModel FRM ) {
		
		Fournisseur fournisseur = modelMapper.map(FRM, Fournisseur.class);
		
		DetailFournisseur df= new DetailFournisseur();//Slave
		df.setDateDebutCollaboration(new Date()); //util
		//On affecte le "Slave" au "Master"
		fournisseur.setDetailFournisseur(df);	
		fournisseurRepository.save(fournisseur);
		return fournisseur;
	}
	
/*	private DetailFournisseur  saveDetailFournisseur(Fournisseur f){
		DetailFournisseur df = f.getDetailFournisseur();
		detailFournisseurRepository.save(df);
		return df;
	}*/

	private DetailFournisseur  saveDetailFournisseur(FournisseurRequestModel FRM){
		Fournisseur fournisseur = modelMapper.map(FRM, Fournisseur.class);
		DetailFournisseur df = fournisseur.getDetailFournisseur();
		detailFournisseurRepository.save(df);
		return df;
	}
	
	/*
	public Fournisseur updateFournisseur(Fournisseur f) {
		DetailFournisseur df = saveDetailFournisseur(f);
		f.setDetailFournisseur(df);	
		fournisseurRepository.save(f);
		return f;
	}*/
	
	public Fournisseur updateFournisseur(FournisseurRequestModel FRM) {
		
		DetailFournisseur df = saveDetailFournisseur(FRM);
		FRM.setDetailFournisseur(df);	
		
		Fournisseur fournisseur = modelMapper.map(FRM, Fournisseur.class);
		fournisseurRepository.save(fournisseur);
		return fournisseur;
	}

	@Override
	public void deleteFournisseur(Long fournisseurId) {
		fournisseurRepository.deleteById(fournisseurId);

	}

	@Override
	public Fournisseur retrieveFournisseur(Long fournisseurId) {

		return fournisseurRepository.findById(fournisseurId).orElse(null);
	}

	@Override
	public void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur) {
		Optional<Fournisseur> fournisseur = fournisseurRepository.findById(idFournisseur);
		
		if (fournisseur.isPresent() ) {
			
			Optional<SecteurActivite> secteurActivite = secteurActiviteRepository.findById(idSecteurActivite);
			
			if (secteurActivite.isPresent()) {
				
				fournisseur.get().getSecteurActivites().add(secteurActivite.get());
				fournisseurRepository.save(fournisseur.get());
			}
			
			
		}

	}

	

}