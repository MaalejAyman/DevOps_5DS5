package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ReglementServiceImpl reglementServiceImpl;

    private Reglement r1;
    private Reglement r2;
    private Reglement r3;
    private Facture f1;
    ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        this.r1 = new Reglement();
        this.r1.setIdReglement(0L);
        this.r1.setMontantPaye(100);
        this.r1.setMontantRestant(10);
        this.r1.setDateReglement(new Date());
        this.r1.setFacture(null);
        this.r2 = new Reglement();
        this.r2.setIdReglement(1L);
        this.r2.setMontantPaye(90);
        this.r2.setMontantRestant(0);
        this.r2.setDateReglement(null);
        this.r2.setFacture(null);
        this.modelMapper = new ModelMapper();
    }


    @Test
    public void addReglementTest() {
    	log.info("entred function : addReglementTest");
        init();
        when(reglementRepository.save(any(Reglement.class))).thenReturn(r1);
        ReglementRequestModel prm = modelMapper.map(r1, ReglementRequestModel.class);
        Reglement newReglement = reglementServiceImpl.addReglement(prm);
        assertNotNull(newReglement);
        assertThat(newReglement.getMontantRestant()).isEqualTo(10);
        log.info("exit function : addReglementTest");
    }

    @Test
    public void getReglement() {
    	log.info("entred function : getReglement");
        init();
        List < Reglement > list = new ArrayList < > ();
        list.add(r1);
        list.add(r2);
        when(reglementRepository.findAll()).thenReturn(list);
        List < Reglement > Reglements = reglementServiceImpl.retrieveAllReglements();
        assertEquals(2, Reglements.size());
        assertNotNull(Reglements);
        log.info("exit function : getReglement");
    }

    @Test
    public void retrieveReglementByFacture() {
    	log.info("entred function : retrieveReglementByFacture");
        this.f1 = new Facture();
        this.f1.setIdFacture(1L);

        this.r2 = new Reglement();
        this.r2.setIdReglement(2L);
        this.r2.setFacture(f1);
        this.r3 = new Reglement();
        this.r3.setIdReglement(3L);
        this.r3.setFacture(f1);
        this.modelMapper = new ModelMapper();

        System.out.println("Factures: " + r2.getFacture().toString() + " " + r3.getFacture().toString());
        List < Reglement > Reglements = reglementServiceImpl.retrieveReglementByFacture(f1.getIdFacture());
        System.out.println(reglementServiceImpl.retrieveReglementByFacture(f1.getIdFacture()));
        assertEquals(0, Reglements.size());
        log.info("exit function : retrieveReglementByFacture");
    }

    @Test
    public void retrieveReglement() {
    	log.info("entred function : retrieveReglement");
        init();
        when(reglementRepository.save(any(Reglement.class))).thenReturn(r2);
        ReglementRequestModel prm = modelMapper.map(r2, ReglementRequestModel.class);
        Reglement pnew = reglementServiceImpl.addReglement(prm);
        when(reglementRepository.findById(anyLong())).thenReturn(Optional.of(r2));
        Reglement existingProduit = reglementServiceImpl.retrieveReglement(pnew.getIdReglement());
        assertNotNull(existingProduit);
        assertThat(existingProduit.getIdReglement()).isNotNull();
        log.info("exit function : retrieveReglement");
    }

    @Test
    public void getChiffreAffaireEntreDeuxDate() {
    	log.info("entred function : getChiffreAffaireEntreDeuxDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strStartDate = "11-11-2022 11:35:42";
        String strEndDate = "24-11-2022 11:35:42";
        try {
            Date startDate = dateFormat.parse(strStartDate);
            Date endDate = dateFormat.parse(strEndDate);
            init();
            when(reglementServiceImpl.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(r1.getMontantPaye());
            assertThat(reglementServiceImpl.getChiffreAffaireEntreDeuxDate(startDate, endDate)).isEqualTo(100f);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.info("exit function : getChiffreAffaireEntreDeuxDate");
    }
}