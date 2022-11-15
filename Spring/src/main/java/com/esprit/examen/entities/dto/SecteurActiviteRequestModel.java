package com.esprit.examen.entities.dto;

import java.util.Set;

import com.esprit.examen.entities.Fournisseur;

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
public class SecteurActiviteRequestModel {
    private static final long serialVersionUID = 1L;

    private Long idSecteurActivite;
    private String codeSecteurActivite;
    private String libelleSecteurActivite;
    private Set<Fournisseur> fournisseurs;
}
