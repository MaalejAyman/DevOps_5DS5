package com.esprit.examen.controllers;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.services.IFournisseurService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;


@RestController
@Api(tags = "Gestion des fournisseurss")
@RequestMapping("/fournisseur")
public class FournisseurRestController {

	@Autowired
	IFournisseurService fournisseurService;
	
	public class FournisseurRequestModel {

		private Long idFournisseur;
		private String code;
		private String libelle;
		private CategorieFournisseur  categorieFournisseur;
		private Set<Facture> factures;
	    private Set<SecteurActivite> secteurActivites;
	    private DetailFournisseur detailFournisseur;
		}

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-all-fournisseurs
	@GetMapping("/retrieve-all-fournisseurs")
	@ResponseBody
	public List<Fournisseur> getFournisseurs() {
		
		return fournisseurService.retrieveAllFournisseurs();
	}

	// http://localhost:8089/SpringMVC/fournisseur/retrieve-fournisseur/8
	@GetMapping("/retrieve-fournisseur/{fournisseur-id}")
	@ResponseBody
	public Fournisseur retrieveFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		return fournisseurService.retrieveFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/add-fournisseur
	@PostMapping("/add-fournisseur")
	@ResponseBody
	public Fournisseur addFournisseur(@RequestBody FournisseurRequestModel fournisseur) {
		
		Fournisseur  f = new Fournisseur();
		
		f.setCode(fournisseur.code);
		f.setLibelle(fournisseur.libelle);
		f.setCategorieFournisseur(fournisseur.categorieFournisseur);
		f.setFactures(fournisseur.factures);
		f.setSecteurActivites(fournisseur.secteurActivites);
		f.setDetailFournisseur(fournisseur.detailFournisseur);
		
		return fournisseurService.addFournisseur(f);
	}


	@DeleteMapping("/remove-fournisseur/{fournisseur-id}")
	@ResponseBody
	public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		fournisseurService.deleteFournisseur(fournisseurId);
	}

	// http://localhost:8089/SpringMVC/fournisseur/modify-fournisseur
	@PutMapping("/modify-fournisseur")
	@ResponseBody
	public Fournisseur modifyFournisseur(@RequestBody FournisseurRequestModel fournisseur) {
		
		Fournisseur  f = new Fournisseur();
		
		f.setCode(fournisseur.code);
		f.setLibelle(fournisseur.libelle);
		f.setCategorieFournisseur(fournisseur.categorieFournisseur);
		f.setFactures(fournisseur.factures);
		f.setSecteurActivites(fournisseur.secteurActivites);
		f.setDetailFournisseur(fournisseur.detailFournisseur);
		
		return fournisseurService.updateFournisseur(f);
	}

	// http://localhost:8089/SpringMVC/fournisseur/assignSecteurActiviteToFournisseur/1/5
		@PutMapping(value = "/assignSecteurActiviteToFournisseur/{idSecteurActivite}/{idFournisseur}")
		public void assignProduitToStock(@PathVariable("idSecteurActivite") Long idSecteurActivite, @PathVariable("idFournisseur") Long idFournisseur) {
			fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);
		}

}
