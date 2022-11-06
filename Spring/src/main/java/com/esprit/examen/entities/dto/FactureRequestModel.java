package com.esprit.examen.entities.dto;

import java.util.Date;
import java.util.Set;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Reglement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureRequestModel {

	private Long idFacture;
	private float montantRemise;
	private float montantFacture;
	private Date dateCreationFacture;
	private Date dateDerniereModificationFacture;
	private Boolean archivee;
	private Set<DetailFacture> detailsFacture;
	private Fournisseur fournisseur;
	private Set<Reglement> reglements;
}
