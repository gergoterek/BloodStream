package com.elte.BloodStream;

import com.elte.BloodStream.model.*;
import com.elte.BloodStream.repository.DonorRepository;
import com.elte.BloodStream.repository.OpeningTimeRepository;
import com.elte.BloodStream.repository.PlaceRepository;
import com.elte.BloodStream.service.PlaceService;
import org.apache.catalina.startup.CopyParentClassLoaderRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceServiceTests {

        @Autowired
        private PlaceService placeService;

        @MockBean
        private PlaceRepository placeRepository;
        @MockBean
        private OpeningTimeRepository openingTimeRepository;

        Place place = null;
        Place place2 = null;
        Place place3 = null;

        @Before
        public void init() {
            place  = Mockito.mock(Place.class);
            place2 = Mockito.mock(Place.class);
            place3 = Mockito.mock(Place.class);
        }
        @After
        public void cleanUp() {
            place = null;
            place2 = null;
            place3 = null;
        }

        @Test
        public void getAllPlacesTest() {
            //given
            //when
            when(placeRepository.findAll()).thenReturn(
                    Stream.of( place, place2, place3 ).collect(Collectors.toList())
            );
            //then
            assertEquals(3, placeRepository.findAll().size());
            verify(placeRepository, times(1)).findAll();
        }

        @Test
        public void getPlaceTest() {
            //given
            int placeID = 1;
            Optional<Place> oPlace = Optional.of(place);
            //when
            when(place.getId()).thenReturn(placeID);
            when(placeRepository.findById(place.getId())).thenReturn(oPlace);
            //then
            assertEquals(new ResponseEntity(place, HttpStatus.OK), placeService.getPlace(place.getId()));
            verify(placeRepository, times(1)).findById(place.getId());
        }

        @Test
        public void createPlaceTest() {
            //given
            //when
            Mockito.when(placeRepository.save(any(Place.class))).thenReturn(place);
            //then
            assertEquals(new ResponseEntity(place, HttpStatus.OK), placeService.createPlace(place));
            verify(placeRepository, times(1)).save(any(Place.class));
        }

        @Test
        public void modifyPlaceTest() {
            //given
            int placeID = 1;
            int openID = 1;
            OpeningTime ot = Mockito.mock(OpeningTime.class);
            //when
            when(ot.getId()).thenReturn(openID);
            when(openingTimeRepository.findById(ot.getId())).thenReturn(Optional.of( ot ));

            when(place.getId()).thenReturn(placeID);
            when(place2.getId()).thenReturn(placeID);
            when(place2.getOpeningTime()).thenReturn(ot);

            when(placeRepository.findById(place.getId())).thenReturn(Optional.of( place ));
            when(placeRepository.save(any(Place.class))).thenReturn(place );

            //then
            assertEquals(new ResponseEntity(place, HttpStatus.OK), placeService.modifyPlace(place2, place2.getId()));
            verify(placeRepository, times(1)).findById(place.getId());
            verify(placeRepository, times(1)).save(any(Place.class));
            verify(openingTimeRepository, times(1)).findById(ot.getId());
        }
}
