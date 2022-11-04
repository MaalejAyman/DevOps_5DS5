package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Facture;
import lombok.*;

import java.util.Set;
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
