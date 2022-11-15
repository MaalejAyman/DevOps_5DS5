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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.dto.FournisseurRequestModel;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {

	@Mock
	FournisseurRepository fournisseurRepository;
	
	@Mock
	SecteurActiviteRepository secteurActiviteRepository;
	
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
		f1.setIdFournisseur(1L);
		f1.setLibelle("libbele1");
		f1.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
		f1.setFactures((Set<Facture>) null);
		f1.setSecteurActivites(new HashSet<SecteurActivite>());
		f1.setCode("code1");
		
		this.f2 = new Fournisseur();
		f2.setIdFournisseur(2L);
		f2.setLibelle("libbele2");
		f2.setCategorieFournisseur(CategorieFournisseur.CONVENTIONNE);
		f2.setFactures((Set<Facture>) null);
		f2.setSecteurActivites(new HashSet<SecteurActivite>());
		f2.setCode("code2");
		
		this.modelMapper = new ModelMapper();
	}
	
	List<Fournisseur> s= new ArrayList<Fournisseur>(){{
        add(f1);
        add(f2) ;
        }
    };
    
    SecteurActivite sa = (SecteurActivite.builder().idSecteurActivite(5L).build());
	
	@Test
	public void testAddFournisseur() {
		log.info("entred function : getStockById");
		init();
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FournisseurRequestModel frm = modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur fnew = fournisseurServiceImpl.addFournisseur(frm);
		assertNotNull(fnew);
		assertThat(fnew.getCode()).isEqualTo("code1");
		log.info("exit function : getStockById");
	}
	
	@Test
	public void save() {
		log.info("entred function : getStockById");
		init();
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f2);
		FournisseurRequestModel frm= modelMapper.map(f2, FournisseurRequestModel.class);
		Fournisseur newFournisseur = fournisseurServiceImpl.addFournisseur(frm);
		assertNotNull(newFournisseur);
		assertThat(newFournisseur.getCode()).isEqualTo("code2");
		log.info("exit function : getStockById");
	}
	
	@Test
	public void getFournisseurs() {
		log.info("entred function : getStockById");
		init();
		List<Fournisseur> list = new ArrayList<>();
		list.add(f1);
		list.add(f2);
		when(fournisseurRepository.findAll()).thenReturn(list);
		List<Fournisseur> Fournisseurs = fournisseurServiceImpl.retrieveAllFournisseurs();
		assertEquals(2, Fournisseurs.size());
		assertNotNull(Fournisseurs);
		log.info("exit function : getStockById");
	}
	
	@Test
	public void getFournisseurById() {
		log.info("entred function : getStockById");
		init();
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		FournisseurRequestModel frm=modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur fnew=fournisseurServiceImpl.addFournisseur(frm);
		when(fournisseurRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		Fournisseur existingFournisseur = fournisseurServiceImpl.retrieveFournisseur(fnew.getIdFournisseur());
		assertNotNull(existingFournisseur);
		assertThat(existingFournisseur.getIdFournisseur()).isNotNull();
		log.info("exit function : getStockById");
	}
	
	@Test
	public void updateFournisseur() {
		log.info("entred function : getStockById");
		init();
		
		when(fournisseurRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		FournisseurRequestModel frm=modelMapper.map(f1, FournisseurRequestModel.class);
		Fournisseur f = fournisseurServiceImpl.addFournisseur(frm);
		f.setLibelle("fournisseurF1");
		FournisseurRequestModel fnew =modelMapper.map(f, FournisseurRequestModel.class);
		Fournisseur exisitingFournisseur = fournisseurServiceImpl.updateFournisseur(fnew);
		System.out.println("test");
		when(fournisseurRepository.save(any(Fournisseur.class))).thenReturn(f1);
		assertNotNull(exisitingFournisseur);
		assertEquals("fournisseurF1", exisitingFournisseur.getLibelle());
		log.info("exit function : getStockById");
	}
	
	@Test
	public void deleteFournisseur() {
		log.info("entred function : getStockById");
		init();
		Long FournisseurId = 1L;
		when(fournisseurRepository.findById(anyLong())).thenReturn(Optional.of(f1));
		doNothing().when(fournisseurRepository).deleteById(anyLong());
		fournisseurServiceImpl.deleteFournisseur(FournisseurId);
		verify(fournisseurRepository, times(1)).deleteById(anyLong());
		log.info("exit function : getStockById");
	}
	
	@Test
    public void assignSecteurActiviteToFournisseurTest(){
		log.info("entred function : getStockById");
		init();
        when((fournisseurRepository.findById(anyLong()))).thenReturn(Optional.of(f1));
        when(secteurActiviteRepository.findById(anyLong())).thenReturn(Optional.of(sa));
        fournisseurServiceImpl.assignSecteurActiviteToFournisseur(5L,1L);
        assertThat(f1.getSecteurActivites().contains(sa)).isTrue();
        verify(fournisseurRepository).save(any(Fournisseur.class));
        log.info("secteur activite assignd :"+f1.toString());
        log.info("exit function : getStockById");
    }
}
