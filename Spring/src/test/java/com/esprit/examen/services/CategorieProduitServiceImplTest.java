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

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.dto.CategorieProduitRequestModel;
import com.esprit.examen.repositories.CategorieProduitRepository;
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class CategorieProduitServiceImplTest {
	@Mock
	private CategorieProduitRepository categorieProduitRepository;
	@InjectMocks
	private CategorieProduitServiceImpl categorieProduitServiceImpl;

	private CategorieProduit cp1;
	private CategorieProduit cp2;
	ModelMapper modelMapper;
	
	@BeforeEach
	public void init() {
		this.cp1 = new CategorieProduit();
		this.cp1.setIdCategorieProduit(1L);
		this.cp1.setCodeCategorie(null);
		this.cp1.setLibelleCategorie("test");
		this.cp2 = new CategorieProduit();
		this.cp2.setIdCategorieProduit(2L);
		this.cp2.setCodeCategorie(null);
		this.cp2.setLibelleCategorie("test2");
		this.modelMapper = new ModelMapper();
	}
	@Test
	public void testAddcategorieProduit() {
		init();
		when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(cp1);
		CategorieProduitRequestModel cprm=modelMapper.map(cp1, CategorieProduitRequestModel.class);
		CategorieProduit pnew=categorieProduitServiceImpl.addCategorieProduit(cprm);
		assertNotNull(pnew);
		assertThat(pnew.getIdCategorieProduit()).isEqualTo(1L);
	}
	@Test
	public void getCategorieProduits() {
		init();
		List<CategorieProduit> list = new ArrayList<>();
		list.add(cp1);
		list.add(cp2);
		when(categorieProduitRepository.findAll()).thenReturn(list);
		List<CategorieProduit> Produits = categorieProduitServiceImpl.retrieveAllCategorieProduits();
		assertEquals(2, Produits.size());
		assertNotNull(Produits);
	}
	
	@Test
	public void getCategorieProduitsById() {
		init();
		when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(cp1);
		CategorieProduitRequestModel prm=modelMapper.map(cp1, CategorieProduitRequestModel.class);
		CategorieProduit pnew=categorieProduitServiceImpl.addCategorieProduit(prm);
		when(categorieProduitRepository.findById(anyLong())).thenReturn(Optional.of(cp1));
		CategorieProduit existingProduit = categorieProduitServiceImpl.retrieveCategorieProduit(pnew.getIdCategorieProduit());
		assertNotNull(existingProduit);
		assertThat(existingProduit.getIdCategorieProduit()).isNotNull();
	}
	@Test
	public void updateCategorieProduit() {
		init();
		when(categorieProduitRepository.findById(anyLong())).thenReturn(Optional.of(cp1));
		
		when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(cp1);
		cp1.setCodeCategorie("Fantacy");
		CategorieProduitRequestModel prm=modelMapper.map(cp1, CategorieProduitRequestModel.class);
		CategorieProduit exisitingProduit = categorieProduitServiceImpl.updateCategorieProduit(prm);
		
		assertNotNull(exisitingProduit);
		assertEquals("Fantacy", exisitingProduit.getCodeCategorie());
	}
	@Test
	public void deleteCategorieProduit() {
		init();
		Long ProduitId = 1L;
		when(categorieProduitRepository.findById(anyLong())).thenReturn(Optional.of(cp1));
		doNothing().when(categorieProduitRepository).deleteById(anyLong());
		categorieProduitServiceImpl.deleteCategorieProduit(ProduitId);
		verify(categorieProduitRepository, times(1)).deleteById(anyLong());
		
	}
}
