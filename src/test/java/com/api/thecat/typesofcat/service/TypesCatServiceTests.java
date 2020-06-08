package com.api.thecat.typesofcat.service;

import com.api.thecat.typesofcat.criteria.CriteriaBuilder;
import com.api.thecat.typesofcat.domain.AddressCats;
import com.api.thecat.typesofcat.domain.CatBreeds;
import com.api.thecat.typesofcat.repository.AddressCatsRepository;
import com.api.thecat.typesofcat.repository.TypesCatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@ActiveProfiles("test")
public class TypesCatServiceTests {

    @Autowired
    private TypesCatService typesCatService;

    @MockBean
    private TypesCatRepository typesCatRepository;

    @MockBean
    private AddressCatsRepository addressCatsRepository;

    @MockBean
    private CriteriaBuilder criteriaBuilder;

    private  CatBreeds catBreeds;

    private AddressCats addressCats;


    @Test
    public void test_list_cats(){

        this.catBreeds = new CatBreeds();

        List<CatBreeds> list = new ArrayList<>();
        list.add(catBreeds);

        Mockito.when(typesCatRepository.findAll()).thenReturn(list);

        typesCatService.getList();
    }


    @Test
    public void test_list_cats_breeds(){

        this.addressCats = new AddressCats();
        this.catBreeds = new CatBreeds();
        catBreeds.setName("test");

        Mockito.when(typesCatRepository.findByName(anyString())).thenReturn(catBreeds);

        Mockito.when(addressCatsRepository.findByName(anyString())).thenReturn(addressCats);

        typesCatService.getCatByBreeds(catBreeds.getName());
    }

    @Test
    public void test_list_cats_origin(){

        this.catBreeds = new CatBreeds();

        catBreeds.setOrigin("test");

        Mockito.when(typesCatRepository.findAllByOrigin(anyString())).thenReturn(Arrays.asList(catBreeds));

        typesCatService.getListByOrigin(catBreeds.getOrigin());
    }

    @Test
    public void test_list_cats_temperament(){

        this.catBreeds = new CatBreeds();

        catBreeds.setTemperament("test");

        Mockito.when(typesCatRepository.findAllByTemperamentLike(anyString())).thenReturn(Arrays.asList(catBreeds));

        typesCatService.getListByTemperament(catBreeds.getTemperament());
    }

    @Test
    public void test_list_cats_queriesBuilder(){

        this.addressCats = new AddressCats();
        this.catBreeds = new CatBreeds();
        catBreeds.setName("test");

        Mockito.when(criteriaBuilder.getListByQueries(anyString(), anyString(), anyString())).thenReturn(Arrays.asList(catBreeds));
        Mockito.when(addressCatsRepository.findByName(anyString())).thenReturn(addressCats);
    }


}
