package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.dto.OperateurRequestModel;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

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
        init();
        when(operateurRepository.save(any(Operateur.class))).thenReturn(o1);
        OperateurRequestModel orm=modelMapper.map(o1, OperateurRequestModel.class);
        Operateur onew=operateurService.addOperateur(orm);
        assertNotNull(onew);
        assertThat(onew.getNom()).isEqualTo("hassen");
    }
    @Test
    public void save() {
        init();
        when(operateurRepository.save(any(Operateur.class))).thenReturn(o1);
        OperateurRequestModel orm=modelMapper.map(o1, OperateurRequestModel.class);
        Operateur newOperateur = operateurService.addOperateur(orm);
        assertNotNull(newOperateur);
        assertThat(newOperateur.getNom()).isEqualTo("hassen");
    }
    @Test
    public void getOperateurs() {
        init();
        List<Operateur> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        when(operateurRepository.findAll()).thenReturn(list);
        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();
        assertEquals(2, operateurs.size());
        assertNotNull(operateurs);
    }
}


