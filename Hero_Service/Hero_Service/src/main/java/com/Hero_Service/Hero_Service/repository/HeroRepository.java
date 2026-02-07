package com.Hero_Service.Hero_Service.repository;

import com.Hero_Service.Hero_Service.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero,Long> { }
