package com.esprit.examen.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.dto.ProduitRequestModel;
import com.esprit.examen.services.IProduitService;

import io.swagger.annotations.Api;


@RestController
@CrossOrigin("*")
@Api(tags = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitRestController {
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
		return produitService.addProduit(prod);
	}

	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public void removeProduit(@PathVariable("produit-id") Long produitId) {
		produitService.deleteProduit(produitId);
	}

	
	@PutMapping("/modify-produit")
	@ResponseBody
	public Produit modifyProduit(@RequestBody ProduitRequestModel prod) {
		return produitService.updateProduit(prod);
	}

	@PutMapping(value = "/assignProduitToStock/{idProduit}/{idStock}")
	public void assignProduitToStock(@PathVariable("idProduit") Long idProduit, @PathVariable("idStock") Long idStock) {
		produitService.assignProduitToStock(idProduit, idStock);
	}

}
