package com.api.thecat.typesofcat.controller;

import com.api.thecat.typesofcat.TDO.EventLogDTO;
import com.api.thecat.typesofcat.domain.CatBreeds;
import com.api.thecat.typesofcat.enums.EventsEnum;
import com.api.thecat.typesofcat.service.TypesCatService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@AllArgsConstructor
@Controller
public class TypesCatController {

    private TypesCatService typesCatService;

    //Criteria
    @GetMapping(value = "/types/breeds/queries")
    public ResponseEntity<List<CatBreeds>> catBreeds(
            @RequestParam( required = false) String breed,
            @RequestParam( required = false) String temperament,
            @RequestParam( required = false) String origin){

        List<CatBreeds> result = typesCatService.queriesBuilder(breed, temperament, origin);

        log.info(new EventLogDTO(EventsEnum.RESPONSE, result).toString());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/types/list")
    public ResponseEntity<List<CatBreeds>> breeds(){

        log.info(new EventLogDTO(EventsEnum.REQUEST, "genarator list of cat").toString());

        List<CatBreeds> result = typesCatService.getList();

        log.info(new EventLogDTO(EventsEnum.RESPONSE, result).toString());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/types/breed")
    public ResponseEntity<CatBreeds> catBreeds(@RequestParam( value = "breed",required = true) String breed){

        log.info(new EventLogDTO(EventsEnum.REQUEST, "genarator of cat type: "+breed).toString());

        CatBreeds result = typesCatService.getBreedsList(breed);

        log.info(new EventLogDTO(EventsEnum.REQUEST, result).toString());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/types/temperament/list")
    public ResponseEntity<List<CatBreeds>> catBreedsTemperament(@RequestParam( value = "temperament",required = true) String temperament){

        log.info(new EventLogDTO(EventsEnum.REQUEST, "genarator of cat type: "+temperament).toString());

        List<CatBreeds> result = typesCatService.getTemperamentList(temperament);

        log.info(new EventLogDTO(EventsEnum.REQUEST, result).toString());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/types/origin/list")
    public ResponseEntity<List<CatBreeds>> catBreedsOrigin(@RequestParam(value = "origin",required = true) String origin){

        log.info(new EventLogDTO(EventsEnum.REQUEST, "genarator of cat type: "+origin).toString());

        List<CatBreeds> result = typesCatService.getOriginList(origin);

        log.info(new EventLogDTO(EventsEnum.REQUEST, result).toString());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
