package com.Hero_Service.Hero_Service.service;

import com.Hero_Service.Hero_Service.entity.Hero;

import java.util.List;

public interface Heroservice {

    List<Hero> GetAllHero();

    Hero CreateHero(Hero hero);
    Hero deleteHeroById(Long id);
}
