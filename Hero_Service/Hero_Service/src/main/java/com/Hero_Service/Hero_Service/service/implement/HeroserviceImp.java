package com.Hero_Service.Hero_Service.service.implement;

import com.Hero_Service.Hero_Service.dto.PaginationData;
import com.Hero_Service.Hero_Service.entity.Hero;
import com.Hero_Service.Hero_Service.repository.HeroRepository;
import com.Hero_Service.Hero_Service.service.Heroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class HeroserviceImp implements Heroservice {

    @Autowired
    HeroRepository heroRepository;


    @Override
    public Hero createHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public Hero deleteHeroById(Long id) {
        Hero hero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
         heroRepository.delete(hero);
         return hero;
    }

	@Override
	public PaginationData<Hero> getAllHero(Pageable pageable) {
		Page<Hero> page = heroRepository.findAll(pageable);
		 return new PaginationData<Hero>(
				 page.getContent(),
				 page.getNumberOfElements(),
				 page.getTotalElements(),
				 page.getTotalPages()
				 );
	}
}
