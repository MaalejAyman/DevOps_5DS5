package com.esprit.examen.entities.dto;

import java.util.Set;

import com.esprit.examen.entities.Facture;

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
public class OperateurRequestModel {
    private static final long serialVersionUID = 1L;

    private Long idOperateur;
    private String nom;
    private String prenom;

    private String password;
    private Set<Facture> factures;
}
