package com.Hero_Service.Hero_Service.service.implement;

import com.Hero_Service.Hero_Service.entity.Hero;
import com.Hero_Service.Hero_Service.repository.HeroRepository;
import com.Hero_Service.Hero_Service.service.Heroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroserviceImp implements Heroservice {

    @Autowired
    HeroRepository heroRepository;

    @Override
    public List<Hero> GetAllHero() {
        return heroRepository.findAll();
    }

    @Override
    public Hero CreateHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public Hero deleteHeroById(Long id) {
        Hero hero = heroRepository.findById(id).orElse(null);
         heroRepository.deleteById(id);
         return hero;
    }
}
