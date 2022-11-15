package com.esprit.examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.dto.CategorieProduitRequestModel;
import com.esprit.examen.repositories.CategorieProduitRepository;

@Service
public class CategorieProduitServiceImpl implements ICategorieProduitService {

	
	@Autowired
	CategorieProduitRepository categorieProduitRepository;
	@Override
	public List<CategorieProduit> retrieveAllCategorieProduits() {
		return categorieProduitRepository.findAll();
	}

	@Override
	public CategorieProduit addCategorieProduit(CategorieProduitRequestModel cp) {
		return	categorieProduitRepository.save(CategorieProduit.builder()
				.codeCategorie(cp.getCodeCategorie())
				.libelleCategorie(cp.getLibelleCategorie())
				.produits(cp.getProduits())
				.build());
	}

	@Override
	public void deleteCategorieProduit(Long id) {
		categorieProduitRepository.deleteById(id);
	}

	@Override
	public CategorieProduit updateCategorieProduit(CategorieProduitRequestModel cp) {
		return categorieProduitRepository.save(CategorieProduit.builder()
				.codeCategorie(cp.getCodeCategorie())
				.libelleCategorie(cp.getLibelleCategorie())
				.produits(cp.getProduits())
				.build());
	}

	@Override
	public CategorieProduit retrieveCategorieProduit(Long id) {
		return  categorieProduitRepository.findById(id).orElse(null);
		}

}
