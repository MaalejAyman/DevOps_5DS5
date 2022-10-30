package com.esprit.examen.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.services.IProduitService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;


@RestController
@CrossOrigin("*")
@Api(tags = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitRestController {
	public class ProduitRequestModel{
		private Long idProduit;
		private String codeProduit;
		private String libelleProduit;
		private float prix;
		private Date dateCreation;
		private Date dateDerniereModification;
		private Stock stock;
		private Set<DetailFacture> detailFacture;
		private CategorieProduit categorieProduit;
	}
	@Autowired
	IProduitService produitService;

	@GetMapping("/retrieve-all-produits")
	@ResponseBody
	public List<Produit> getProduits() {
		return produitService.retrieveAllProduits();
	}

	@GetMapping("/retrieve-produit/{produit-id}")
	@ResponseBody
	public Produit retrieveRayon(@PathVariable("produit-id") Long produitId) {
		return produitService.retrieveProduit(produitId);
	}


	@PostMapping("/add-produit")
	@ResponseBody
	public Produit addProduit(@RequestBody ProduitRequestModel prod) {
		Produit p = new Produit();
		p.setCategorieProduit(prod.categorieProduit);
		p.setCodeProduit(prod.codeProduit);
		p.setDateCreation(prod.dateCreation);
		p.setDateDerniereModification(prod.dateDerniereModification);
		p.setDetailFacture(prod.detailFacture);
		p.setIdProduit(p.getIdProduit());
		p.setLibelleProduit(prod.libelleProduit);
		p.setPrix(prod.prix);
		p.setStock(prod.stock);
		return produitService.addProduit(p);
	}

	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public void removeProduit(@PathVariable("produit-id") Long produitId) {
		produitService.deleteProduit(produitId);
	}

	
	@PutMapping("/modify-produit")
	@ResponseBody
	public Produit modifyProduit(@RequestBody ProduitRequestModel prod) {
		Produit p = new Produit();
		p.setCategorieProduit(prod.categorieProduit);
		p.setCodeProduit(prod.codeProduit);
		p.setDateCreation(prod.dateCreation);
		p.setDateDerniereModification(prod.dateDerniereModification);
		p.setDetailFacture(prod.detailFacture);
		p.setIdProduit(p.getIdProduit());
		p.setLibelleProduit(prod.libelleProduit);
		p.setPrix(prod.prix);
		p.setStock(prod.stock);
		return produitService.updateProduit(p);
	}

	@PutMapping(value = "/assignProduitToStock/{idProduit}/{idStock}")
	public void assignProduitToStock(@PathVariable("idProduit") Long idProduit, @PathVariable("idStock") Long idStock) {
		produitService.assignProduitToStock(idProduit, idStock);
	}

}
