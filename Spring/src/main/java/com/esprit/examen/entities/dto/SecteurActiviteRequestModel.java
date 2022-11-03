package com.esprit.examen.entities.dto;

import com.esprit.examen.entities.Fournisseur;
import lombok.*;

import java.util.Set;
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
