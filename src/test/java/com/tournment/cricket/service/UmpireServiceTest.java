package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.tournment.cricket.model.Umpire;
import com.tournment.cricket.repository.UmpireRepository;

@RunWith(MockitoJUnitRunner.class)
public class UmpireServiceTest {

    @Mock
    private UmpireRepository umpireRepository;

    @InjectMocks
    private UmpireService umpireService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUmpireList() {
        Umpire umpire1 = new Umpire();
        umpire1.setId(1L);
        umpire1.setName("John");
        umpire1.setEmail("john@example.com");

        Umpire umpire2 = new Umpire();
        umpire2.setId(2L);
        umpire2.setName("Jane");
        umpire2.setEmail("jane@example.com");

        List<Umpire> umpireList = Arrays.asList(umpire1, umpire2);

        when(umpireRepository.findAll()).thenReturn(umpireList);

        List<Umpire> result = umpireService.getAllUmpireList();

        assertEquals(umpireList, result);
    }

    @Test
    public void testCreateUmpire() {
        Umpire umpire = new Umpire();
        umpire.setId(1L);
        umpire.setName("John");
        umpire.setEmail("john@example.com");

        when(umpireRepository.save(umpire)).thenReturn(umpire);

        Umpire result = umpireService.createUmpire(umpire);

        assertNotNull(result);
        assertEquals(umpire, result);
    }

    @Test
    public void testGetUmpireById() {
        Umpire umpire = new Umpire();
        umpire.setId(1L);
        umpire.setName("John");
        umpire.setEmail("john@example.com");

        when(umpireRepository.findById(1L)).thenReturn(Optional.of(umpire));

        Umpire result = umpireService.getUmpireById(1L);

        assertEquals(umpire, result);
    }

    @Test
    public void testGetUmpireByIdNotFound() {
        when(umpireRepository.findById(1L)).thenReturn(Optional.empty());

        Umpire result = umpireService.getUmpireById(1L);

        assertNull(result);
    }
}