package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.dto.ProduitRequestModel;
import com.esprit.examen.repositories.ProduitRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
	@Mock
	private ProduitRepository produitRepository;

	@InjectMocks
	private ProduitServiceImpl produitService;

	private Produit p1;
	private Produit p2;
	ModelMapper modelMapper;

	@BeforeEach
	void init() {
		p1 = new Produit();
		p1.setPrix(100);
		p1.setLibelleProduit("Avatar");
		p2 = new Produit();
		p2.setPrix(100);
		p2.setLibelleProduit("Avatar");
	}

	@Test
	public void testAddProduit() {
		p1 = new Produit();
		p1.setPrix(100);
		p1.setLibelleProduit("Avatar");
		p2 = new Produit();
		p2.setPrix(100);
		p2.setLibelleProduit("Avatar");
		modelMapper = new ModelMapper();
		when(produitRepository.save(any(Produit.class))).thenReturn(p1);
		ProduitRequestModel prm=modelMapper.map(p1, ProduitRequestModel.class);
		Produit pnew=produitService.addProduit(prm);
		assertNotNull(pnew);
		assertThat(pnew.getPrix()).isEqualTo(100);
	}
}
