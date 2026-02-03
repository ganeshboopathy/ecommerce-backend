package com.Hero_Service.Hero_Service.controller;

import com.Hero_Service.Hero_Service.apiresponse.ApiResponse;
import com.Hero_Service.Hero_Service.entity.Hero;
import com.Hero_Service.Hero_Service.service.Heroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroController {
    @Autowired	Heroservice heroservice;
    
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Hero>>> getHero (){
    List<Hero> listOfHero  = heroservice.GetAllHero();
    ApiResponse<List<Hero>> response = new ApiResponse<>(HttpStatus.OK.value(), "List Hero Data's Successfully Retrieved.", listOfHero);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Hero>> CreateHero(@RequestBody Hero hero){
    	Hero savedHero  = heroservice.CreateHero(hero);
        ApiResponse<Hero> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Hero Data Created Successfully.", savedHero);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Hero>> deleteById(@PathVariable  Long id){
    	Hero deletedHero = heroservice.deleteHeroById(id);
        ApiResponse<Hero> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Hero Data Created Successfully.", deletedHero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
