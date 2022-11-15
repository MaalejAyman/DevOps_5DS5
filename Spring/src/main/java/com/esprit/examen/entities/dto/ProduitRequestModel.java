package com.esprit.examen.entities.dto;

import java.util.Date;
import java.util.Set;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
