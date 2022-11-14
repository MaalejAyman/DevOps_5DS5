package com.esprit.examen.entities.dto;


import java.util.Set;

import com.esprit.examen.entities.Produit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorieProduitRequestModel  {
	private Long idCategorieProduit;
	private String codeCategorie;
	private String libelleCategorie;
	private Set<Produit> produits;
}