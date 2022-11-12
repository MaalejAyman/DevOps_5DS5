package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.dto.FactureRequestModel;
import com.esprit.examen.entities.dto.ProduitRequestModel;
import com.esprit.examen.entities.dto.ReglementRequestModel;
import com.esprit.examen.repositories.FactureRepository;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FactureServiceImplTest {
	
	@Mock
	private FactureRepository factureRepository;
	
	@InjectMocks
	private FactureServiceImpl factureService ;
	
	private Facture f1 ;
	private Facture f2 ;
	ModelMapper modelMapper;
	
	@BeforeEach
	public void init() {
		this.f1 = new Facture();
		this.f1.setIdFacture(0L);
		this.f1.setMontantRemise(20);
		this.f1.setMontantFacture(100);
		this.f1.setArchivee(true);
		this.f2 = new Facture();
		this.f2.setIdFacture(1L);
		this.f2.setMontantRemise(10);
		this.f2.setMontantFacture(200);
		this.f2.setArchivee(false);
		this.modelMapper = new ModelMapper();
	}

	
	@Test
	public void testAddFacture() {
		init();
		when(factureRepository.save(any(Facture.class))).thenReturn(f1);
		FactureRequestModel frm=modelMapper.map(f1, FactureRequestModel.class);
		Facture fnew=factureService.addFacture(frm);
		assertNotNull(fnew);
		assertThat(fnew.getMontantFacture()).isEqualTo(100);
	}
	
	@Test
	public void save() {
		init();
		when(factureRepository.save(any(Facture.class))).thenReturn(f1);
		FactureRequestModel frm=modelMapper.map(f1, FactureRequestModel.class);
		Facture newFacture = factureService.addFacture(frm);
		assertNotNull(newFacture);
		assertThat(newFacture.getMontantFacture()).isEqualTo(100);
	}
	
	@Test
	public void getFactures() {
		init();
		List<Facture> list = new ArrayList<>();
		list.add(f1);
		list.add(f2);
		when(factureRepository.findAll()).thenReturn(list);
		List<Facture> factures = factureService.retrieveAllFactures();
		assertEquals(2, factures.size());
		assertNotNull(factures);
	}
	@Test
	public void getFacturetById() {
		init();
		when(factureRepository.save(any(Facture.class))).thenReturn(f1);
		FactureRequestModel frm=modelMapper.map(f1, FactureRequestModel.class);
		Facture fnew=factureService.addFacture(frm);
		when(factureRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		Facture existingFacture = factureService.retrieveFacture(fnew.getIdFacture());
		assertNotNull(existingFacture);
		assertThat(existingFacture.getIdFacture()).isNotNull();
	}

	@Test
	public void updateFacture() {
		init();
		when(factureRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		
		when(factureRepository.save(any(Facture.class))).thenReturn(f1);
		f1.setArchivee(true);
		FactureRequestModel frm=modelMapper.map(f1, FactureRequestModel.class);
		factureService.cancelFacture(frm.getIdFacture());
		Facture exisitingFacture =factureRepository.findById(frm.getIdFacture()).orElse(null);
		assertNotNull(exisitingFacture);
		assertEquals(true, exisitingFacture.getArchivee());
	}
	

	
	@Test
	public void testGetFacturesByFournisseur() {
		init();
		when(factureRepository.save(any(Facture.class))).thenReturn(f1);
		when(factureRepository.save(any(Facture.class))).thenReturn(f2);
		FactureRequestModel frm1=modelMapper.map(f1, FactureRequestModel.class);
		FactureRequestModel frm2=modelMapper.map(f2, FactureRequestModel.class);
		Facture snew1=factureService.addFacture(frm1);
		Facture snew2=factureService.addFacture(frm2);
		List<Facture> list = Mockito.mock(List.class);;
		list.add(f1);
		list.add(f2);
		//when(factureRepository.getFactureByFournisseur(anyLong())).thenReturn(list);
		List<Facture> existingFactures = factureService.getFacturesByFournisseur(snew1.getIdFacture());
		assertNotNull(existingFactures);
		assertEquals(2, existingFactures.size());
	}
	
}
