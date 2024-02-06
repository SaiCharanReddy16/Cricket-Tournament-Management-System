package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.tournment.cricket.model.Venue;
import com.tournment.cricket.repository.VenueRepository;

@RunWith(MockitoJUnitRunner.class)
public class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @InjectMocks
    private VenueService venueService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Venue createMockVenue(Long id, String name, String city, String state, String country, String address) {
        Venue venue = new Venue();
        venue.setId(id);
        venue.setName(name);
        venue.setCity(city);
        venue.setState(state);
        venue.setCountry(country);
        venue.setAddress(address);
        return venue;
    }

    @Test
    public void testGetAllVenues() {
        Venue venue1 = createMockVenue(1L, "Venue 1", "City 1", "State 1", "Country 1", "Address 1");
        Venue venue2 = createMockVenue(2L, "Venue 2", "City 2", "State 2", "Country 2", "Address 2");
        List<Venue> venueList = Arrays.asList(venue1, venue2);

        when(venueRepository.findAll()).thenReturn(venueList);

        List<Venue> result = venueService.getAllVenues();

        assertEquals(venueList, result);
    }

    @Test
    public void testCreateVenue() {
        Venue venue = createMockVenue(1L, "Venue 1", "City 1", "State 1", "Country 1", "Address 1");

        when(venueRepository.save(venue)).thenReturn(venue);

        Venue result = venueService.createVenue(venue);

        assertEquals(venue, result);
    }

    @Test
    public void testGetVenueById() {
        Venue venue = createMockVenue(1L, "Venue 1", "City 1", "State 1", "Country 1", "Address 1");

        when(venueRepository.findById(1L)).thenReturn(Optional.of(venue));

        Venue result = venueService.getMatchById(1L);

        assertEquals(venue, result);
    }

    @Test
    public void testUpdateVenue() {
        Venue existingVenue = createMockVenue(1L, "Venue 1", "City 1", "State 1", "Country 1", "Address 1");
        Venue updatedVenue = createMockVenue(1L, "Venue 1 Updated", "City 1 Updated", "State 1 Updated", "Country 1 Updated", "Address 1 Updated");

        when(venueRepository.findById(1L)).thenReturn(Optional.of(existingVenue));
        when(venueRepository.save(existingVenue)).thenReturn(updatedVenue);

        Venue result = venueService.updateVenue(1L, updatedVenue);

        assertEquals(updatedVenue, result);
    }

    @Test
    public void testGetVenueById_ReturnsNull() {
        when(venueRepository.findById(1L)).thenReturn(Optional.empty());

        Venue result = venueService.getMatchById(1L);

        assertNull(result);
    }

}
