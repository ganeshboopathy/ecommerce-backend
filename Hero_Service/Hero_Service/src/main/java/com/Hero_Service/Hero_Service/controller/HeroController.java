package com.Hero_Service.Hero_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import com.Hero_Service.Hero_Service.apiresponse.ApiResponse;
import com.Hero_Service.Hero_Service.dto.PaginationData;
import com.Hero_Service.Hero_Service.entity.Hero;
import com.Hero_Service.Hero_Service.service.Heroservice;

@RestController
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private Heroservice heroservice;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<PaginationData<Hero>>> getHero(Pageable pageable) {

        PaginationData<Hero> heroes = heroservice.getAllHero(pageable);
        ApiResponse<PaginationData<Hero>> response =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "List Of Hero Data Successfully Retrieved.",
                        heroes
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Hero>> createHero(@RequestBody Hero hero) {

        Hero savedHero = heroservice.createHero(hero);
        ApiResponse<Hero> response =
                new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Hero Data Created Successfully.",
                        savedHero
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Hero>> deleteById(@PathVariable Long id) {

        Hero deletedHero = heroservice.deleteHeroById(id);
        ApiResponse<Hero> response =
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Hero Data Deleted Successfully.",
                        deletedHero
                );

        return ResponseEntity.ok(response);
    }
}
