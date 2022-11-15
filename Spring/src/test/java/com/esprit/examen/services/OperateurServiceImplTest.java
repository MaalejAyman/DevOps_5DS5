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

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.dto.OperateurRequestModel;
import com.esprit.examen.repositories.OperateurRepository;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)

public class OperateurServiceImplTest {
    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService ;

    private Operateur o1 ;
    private Operateur o2 ;
    ModelMapper modelMapper;
    @BeforeEach
    public void init() {
        this.o1 = new Operateur();
        this.o1.setIdOperateur(0L);
        this.o1.setNom("hassen");
        this.o1.setPrenom("Naim");
        this.o2 = new Operateur();
        this.o2.setIdOperateur(1L);
        this.o2.setNom("saighi");
        this.o2.setPrenom("swervo");
        this.modelMapper = new ModelMapper();
    }
    @Test
    public void testAddOperateur() {
    	log.info("entred function : testAddOperateur");
        init();
        when(operateurRepository.save(any(Operateur.class))).thenReturn(o1);
        OperateurRequestModel orm=modelMapper.map(o1, OperateurRequestModel.class);
        Operateur onew=operateurService.addOperateur(orm);
        assertNotNull(onew);
        assertThat(onew.getNom()).isEqualTo("hassen");
        log.info("exit function : testAddOperateur");
    }

    @Test
    public void getOperateurs() {
    	log.info("entred function : getOperateurs");
        init();
        List<Operateur> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        when(operateurRepository.findAll()).thenReturn(list);
        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();
        assertEquals(2, operateurs.size());
        assertNotNull(operateurs);
        log.info("exit function : getOperateurs");
    }
    @Test
    public void getOperateurById() {
    	log.info("entred function : getOperateurById");
        init();
        when(operateurRepository.save(any(Operateur.class))).thenReturn(o1);
        OperateurRequestModel orm=modelMapper.map(o1, OperateurRequestModel.class);
        Operateur onew= operateurService.addOperateur(orm);
        when(operateurRepository.findById(anyLong())).thenReturn(Optional.of(o1));
        Operateur existingoperateur = operateurService.retrieveOperateur(onew.getIdOperateur());
        assertNotNull(existingoperateur);
        assertThat(existingoperateur.getIdOperateur()).isNotNull();
        log.info("exit function : getOperateurById");
    }
    @Test
    public void updateOperateur() {
    	log.info("entred function : updateOperateur");
        init();
        when(operateurRepository.findById(anyLong())).thenReturn(Optional.of(o1));

        when(operateurRepository.save(any(Operateur.class))).thenReturn(o1);
        o1.setPassword("001");
        OperateurRequestModel orm=modelMapper.map(o1, OperateurRequestModel.class);
        Operateur exisitingOperateur = operateurService.updateOperateur(orm);

        assertNotNull(exisitingOperateur);
        assertEquals("001", exisitingOperateur.getPassword());
        log.info("exit function : updateOperateur");
    }
    @Test
    public void deleteOperateur() {
    	log.info("entred function : deleteOperateur");
        init();
        Long ProduitId = 0L;
        when(operateurRepository.findById(anyLong())).thenReturn(Optional.of(o1));
        doNothing().when(operateurRepository).deleteById(anyLong());
        operateurService.deleteOperateur(ProduitId);
        verify(operateurRepository, times(1)).deleteById(anyLong());
        log.info("exit function : deleteOperateur");

    }

}


