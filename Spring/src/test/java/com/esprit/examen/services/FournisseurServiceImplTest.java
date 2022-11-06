package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.dto.FournisseurRequestModel;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {

	@Mock
	FournisseurRepository fournisseurRepository;
	
	@InjectMocks
	FournisseurServiceImpl fournisseurServiceImpl;
	
	@Mock
	DetailFournisseurRepository detailFournisseurRepository;
	Fournisseur f1;
	Fournisseur f2;
	ModelMapper modelMapper;
	
	@BeforeEach
	public void init() {
		this.f1 = new Fournisseur();
		f1.setIdFournisseur(0L);
		f1.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
		f1.setCode("2022");
		
		this.f2 = new Fournisseur();
		f2.setIdFournisseur(0L);
		f2.setCategorieFournisseur(CategorieFournisseur.CONVENTIONNE);
		f1.setCode("2022");
		
		this.modelMapper = new ModelMapper();
	}
	
	@Test
	public void testAddFournisseur() {
		init();
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FournisseurRequestModel frm = modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur fnew = fournisseurServiceImpl.addFournisseur(frm);
		assertNotNull(fnew);
		assertThat(fnew.getCode()).isEqualTo("2022");
	}
	
	@Test
	public void save() {
		init();
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		FournisseurRequestModel frm= modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur newFournisseur = fournisseurServiceImpl.addFournisseur(frm);
		assertNotNull(newFournisseur);
		assertThat(newFournisseur.getCode()).isEqualTo("2022");
	}
	
	@Test
	public void getFournisseurs() {
		init();
		List<Fournisseur> list = new ArrayList<>();
		list.add(f1);
		list.add(f2);
		when(fournisseurRepository.findAll()).thenReturn(list);
		List<Fournisseur> Fournisseurs = fournisseurServiceImpl.retrieveAllFournisseurs();
		assertEquals(2, Fournisseurs.size());
		assertNotNull(Fournisseurs);
	}
	
	@Test
	public void getFournisseurById() {
		init();
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		FournisseurRequestModel frm=modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur fnew=fournisseurServiceImpl.addFournisseur(frm);
		when(fournisseurRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		Fournisseur existingFournisseur = fournisseurServiceImpl.retrieveFournisseur(fnew.getIdFournisseur());
		assertNotNull(existingFournisseur);
		assertThat(existingFournisseur.getIdFournisseur()).isNotNull();
	}
	
	@Test
	public void updateFournisseur() {
		init();
		when(fournisseurRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		FournisseurRequestModel frm=modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur f = fournisseurServiceImpl.addFournisseur(frm);
		f.setLibelle("fournisseurF1");
		f.getDetailFournisseur().setIdDetailFournisseur(1L);
		FournisseurRequestModel fnew =modelMapper.map(f, FournisseurRequestModel.class);
		Fournisseur exisitingFournisseur = fournisseurServiceImpl.updateFournisseur(fnew);
		System.out.println("test");
		assertNotNull(exisitingFournisseur);
		assertEquals("fournisseurF1", exisitingFournisseur.getLibelle());
	}
	
	@Test
	public void deleteFournisseur() {
		init();
		Long FournisseurId = 1L;
		when(fournisseurRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		doNothing().when(fournisseurRepository).deleteById(anyLong());
		fournisseurServiceImpl.deleteFournisseur(FournisseurId);
		verify(fournisseurRepository, times(1)).deleteById(anyLong());
		
	}
}
