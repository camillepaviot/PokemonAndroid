package com.kevinguegancamillepaviot.pokemon.entity;

import org.joda.time.DateTime;

import com.tactfactory.harmony.annotation.Column;
import com.tactfactory.harmony.annotation.Column.Type;
import com.tactfactory.harmony.annotation.Entity;
import com.tactfactory.harmony.annotation.GeneratedValue;
import com.tactfactory.harmony.annotation.GeneratedValue.Strategy;
import com.tactfactory.harmony.annotation.Id;
import com.tactfactory.harmony.annotation.ManyToOne;
import com.tactfactory.harmony.annotation.OneToOne;

@Entity
public class Pokemons {
	
	@Id
    @Column(type = Type.INTEGER, hidden = true)
    @GeneratedValue(strategy = Strategy.MODE_IDENTITY)
	private int id;
	
	@Column(type = Type.STRING)
	private String surnom;
	
	@Column(type = Type.INTEGER)
	private int niveau;
	
	@Column(type = Type.DATETIME)
	private DateTime capture;
	
	@ManyToOne()
	@Column()
	private TypeDePokemons typePokemon;
	
	@OneToOne()
	@Column()
	private Attaques attaque1;
	
	@OneToOne()
	@Column(nullable = true)
	private Attaques attaque2;
	
	@OneToOne()
	@Column(nullable = true)
	private Attaques attaque3;
	
	@OneToOne()
	@Column(nullable = true)
	private Attaques attaque4;
}