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
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.dto.FactureRequestModel;
import com.esprit.examen.entities.dto.ProduitRequestModel;
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
		this.f2 = new Facture();
		this.f2.setIdFacture(1L);
		this.f2.setMontantRemise(10);
		this.f2.setMontantFacture(200);
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

	
}
