package com.esprit.examen.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.dto.OperateurRequestModel;
import com.esprit.examen.repositories.OperateurRepository;

@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository  operateurRepository;
	ModelMapper modelMapper= new ModelMapper();
	
	@Override
	public List<Operateur> retrieveAllOperateurs() {
		return (List<Operateur>) operateurRepository.findAll();
	}
	
	@Override
	public Operateur addOperateur(OperateurRequestModel o) {
		return operateurRepository.save(modelMapper.map(o,Operateur.class));
	}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);
		
	}

	@Override
	public Operateur updateOperateur(OperateurRequestModel o) {
		return operateurRepository.save(modelMapper.map(o,Operateur.class));
	}

	@Override
	public Operateur retrieveOperateur(Long id) {
		return operateurRepository.findById(id).orElse(null);

	}

}
