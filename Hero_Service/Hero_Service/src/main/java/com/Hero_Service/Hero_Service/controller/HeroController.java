package com.Hero_Service.Hero_Service.controller;

import com.Hero_Service.Hero_Service.entity.Hero;
import com.Hero_Service.Hero_Service.service.Heroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hero")
public class HeroController {
    @Autowired
    Heroservice heroservice;
    @GetMapping("/get")
    public List<Hero> getHero (){
        return heroservice.GetAllHero();
    }

    @PostMapping
    public Hero CreateHero(@RequestBody Hero hero){
        return heroservice.CreateHero(hero);
    }

    @DeleteMapping("/{id}")
    public Hero deleteById(@PathVariable  Long id){
        return heroservice.deleteHeroById(id);
    }
}
