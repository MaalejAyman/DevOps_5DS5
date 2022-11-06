package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.dto.SecteurActiviteRequestModel;
import com.esprit.examen.repositories.SecteurActiviteRepository;
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

public class SecteurServiceImplTest {
    @Mock
    private SecteurActiviteRepository secteurRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurService;

    private SecteurActivite s1 ;
    private SecteurActivite s2 ;
    ModelMapper modelMapper;
    @BeforeEach
    public void init() {
        this.s1 = new SecteurActivite();
        this.s1.setIdSecteurActivite(0L);
        this.s1.setLibelleSecteurActivite("ds");
        this.s1.setCodeSecteurActivite("00");
        this.s2 = new SecteurActivite();
        this.s2.setIdSecteurActivite(1L);
        this.s2.setLibelleSecteurActivite("ai");
        this.s2.setCodeSecteurActivite("01");
        this.modelMapper = new ModelMapper();
    }
    @Test
    public void testAddSecteurActivite() {
        init();
        when(secteurRepository.save(any(SecteurActivite.class))).thenReturn(s1);
        SecteurActiviteRequestModel srm=modelMapper.map(s1, SecteurActiviteRequestModel.class);
        SecteurActivite snew=secteurService.addSecteurActivite(srm);
        assertNotNull(snew);
        assertThat(snew.getLibelleSecteurActivite()).isEqualTo("ds");
    }
    @Test
    public void save() {
        init();
        when(secteurRepository.save(any(SecteurActivite.class))).thenReturn(s1);
        SecteurActiviteRequestModel srm=modelMapper.map(s1, SecteurActiviteRequestModel.class);
        SecteurActivite newsecteur = secteurService.addSecteurActivite(srm);
        assertNotNull(newsecteur);
        assertThat(newsecteur.getLibelleSecteurActivite()).isEqualTo("ds");
    }
    @Test
    public void getSecteurs() {
        init();
        List<SecteurActivite> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        when(secteurRepository.findAll()).thenReturn(list);
        List<SecteurActivite> secteurs = secteurService.retrieveAllSecteurActivite();
        assertEquals(2, secteurs.size());
        assertNotNull(secteurs);
    }
    @Test
    public void getSecteurById() {
        init();
        when(secteurRepository.save(any(SecteurActivite.class))).thenReturn(s1);
        SecteurActiviteRequestModel srm=modelMapper.map(s1, SecteurActiviteRequestModel .class);
        SecteurActivite snew= secteurService.addSecteurActivite(srm);
        when(secteurRepository.findById(anyLong())).thenReturn(Optional.of(s1));
        SecteurActivite existingsecteur= secteurService.retrieveSecteurActivite(snew.getIdSecteurActivite());
        assertNotNull(existingsecteur);
        assertThat(existingsecteur.getIdSecteurActivite()).isNotNull();
    }
    @Test
    public void updateSecteur() {
        init();
        when(secteurRepository.findById(anyLong())).thenReturn(Optional.of(s1));

        when(secteurRepository.save(any(SecteurActivite.class))).thenReturn(s1);
        s1.setCodeSecteurActivite("00");
        SecteurActiviteRequestModel srm=modelMapper.map(s1, SecteurActiviteRequestModel.class);
        SecteurActivite existingsecteur = secteurService.updateSecteurActivite(srm);

        assertNotNull(existingsecteur);
        assertEquals("00", existingsecteur.getCodeSecteurActivite());
    }
    @Test
    public void deleteOperateur() {
        init();
        Long SecteurId = 0L;
        when(secteurRepository.findById(anyLong())).thenReturn(Optional.of(s1));
        doNothing().when(secteurRepository).deleteById(anyLong());
        secteurService.deleteSecteurActivite(SecteurId);
        verify(secteurRepository, times(1)).deleteById(anyLong());

    }
}