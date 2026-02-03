package com.Hero_Service.Hero_Service.service;

import com.Hero_Service.Hero_Service.dto.PaginationData;
import com.Hero_Service.Hero_Service.entity.Hero;
import org.springframework.data.domain.Pageable;

public interface Heroservice {

	PaginationData<Hero> getAllHero(Pageable pageable);

    Hero createHero(Hero hero);
    Hero deleteHeroById(Long id);
}
