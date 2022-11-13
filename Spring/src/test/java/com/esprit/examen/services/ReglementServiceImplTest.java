package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.dto.ReglementRequestModel;
import com.esprit.examen.repositories.ReglementRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {
	@Mock
	private ReglementRepository reglementRepository;

	@InjectMocks
	private ReglementServiceImpl reglementService;

	private Reglement r1;
	private Reglement r2;
	ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		this.r1 = new Reglement();
		this.r1.setIdReglement(0L);
		this.r1.setMontantPaye(100);
		this.r1.setMontantRestant(50);
		this.r2 = new Reglement();
		this.r2.setIdReglement(1L);
		this.r1.setMontantPaye(200);
		this.r1.setMontantRestant(100);
		this.modelMapper = new ModelMapper();
	}

/*	@Test
	public void testAddReglement() {
		init();
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r1);
		ReglementRequestModel srm=modelMapper.map(r1, ReglementRequestModel.class);
		Reglement snew = reglementService.addReglement(srm);
		assertNotNull(snew);
		assertThat(snew.getMontantPaye()).isEqualTo(100);
	}*/
	
	@Test
	public void getReglements() {
		init();
		List<Reglement> list = new ArrayList<>();
		list.add(r1);
		list.add(r2);
		when(reglementRepository.findAll()).thenReturn(list);
		List<Reglement> Stocks = reglementService.retrieveAllReglements();
		assertEquals(2, Stocks.size());
		assertNotNull(Stocks);
	}
	
	@Test
	public void getReglementById() {
		init();
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r1);
		ReglementRequestModel srm=modelMapper.map(r1, ReglementRequestModel.class);
		Reglement snew=reglementService.addReglement(srm);
		when(reglementRepository.findById(anyLong())).thenReturn(Optional.of(r1));
		Reglement existingReglement = reglementService.retrieveReglement(snew.getIdReglement());
		assertNotNull(existingReglement);
		assertThat(existingReglement.getIdReglement()).isNotNull();
	}
	
/*	@Test
	public void getReglementByFacture() {
		init();
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r1);
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r2);
		ReglementRequestModel srm1=modelMapper.map(r1, ReglementRequestModel.class);
		ReglementRequestModel srm2=modelMapper.map(r2, ReglementRequestModel.class);
		Reglement snew1=reglementService.addReglement(srm1);
		Reglement snew2=reglementService.addReglement(srm2);
		List<Reglement> list = new ArrayList<>();
		list.add(r1);
		list.add(r2);
		when(reglementRepository.retrieveReglementByFacture(anyLong())).thenReturn(list);
		List<Reglement> existingReglements = reglementService.retrieveReglementByFacture(snew1.getIdReglement());
		assertNotNull(existingReglements);
		assertEquals(2, existingReglements.size());
	}*/
	
/*	@Test
	public void getReglementChiffreAffaireEntreDeuxDate() {
		init();
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r1);
		when(reglementRepository.save(any(Reglement.class))).thenReturn(r2);
		ReglementRequestModel srm1=modelMapper.map(r1, ReglementRequestModel.class);
		ReglementRequestModel srm2=modelMapper.map(r2, ReglementRequestModel.class);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = cal.getTime();
		Date endDate = new Date();
		Reglement snew1=reglementService.addReglement(srm1);
		Reglement snew2=reglementService.addReglement(srm2);
		when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(0f);
		float chiffre = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
		Assert.assertNotEquals(0f, chiffre);
		assertThat(chiffre).isNotZero();
	}*/
}