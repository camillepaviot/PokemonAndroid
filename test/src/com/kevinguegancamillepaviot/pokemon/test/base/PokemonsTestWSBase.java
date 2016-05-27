/**************************************************************************
 * PokemonsTestWSBase.java, pokemon Android
 *
 * Copyright 2016
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : May 27, 2016
 *
 **************************************************************************/
package com.kevinguegancamillepaviot.pokemon.test.base;

import java.util.ArrayList;

import android.database.Cursor;

import com.kevinguegancamillepaviot.pokemon.data.PokemonsWebServiceClientAdapter;
import com.kevinguegancamillepaviot.pokemon.data.RestClient.RequestConstants;
import com.kevinguegancamillepaviot.pokemon.entity.Pokemons;
import com.kevinguegancamillepaviot.pokemon.fixture.PokemonsDataLoader;
import com.kevinguegancamillepaviot.pokemon.test.utils.PokemonsUtils;
import com.kevinguegancamillepaviot.pokemon.test.utils.TestUtils;

import com.google.mockwebserver.MockResponse;

import junit.framework.Assert;

/** Pokemons Web Service Test.
 * 
 * @see android.app.Fragment
 */
public abstract class PokemonsTestWSBase extends TestWSBase {
    /** model {@link Pokemons}. */
    protected Pokemons model;
    /** web {@link PokemonsWebServiceClientAdapter}. */
    protected PokemonsWebServiceClientAdapter web;
    /** entities ArrayList<Pokemons>. */
    protected ArrayList<Pokemons> entities;
    /** nbEntities Number of entities. */
    protected int nbEntities = 0;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        String host = this.server.getHostName();
        int port = this.server.getPort();

        this.web = new PokemonsWebServiceClientAdapter(
                this.ctx, host, port, RequestConstants.HTTP);
        
        this.entities = new ArrayList<Pokemons>();        
        this.entities.addAll(PokemonsDataLoader.getInstance(this.ctx).getMap().values());
        
        if (entities.size() > 0) {
            this.model = this.entities.get(TestUtils.generateRandomInt(0,entities.size()-1));
        }

        this.nbEntities += PokemonsDataLoader.getInstance(this.ctx).getMap().size();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /** Test case Create Entity */
    public void testInsert() {
        this.server.enqueue(new MockResponse().setBody("{'result'='0'}"));

        int result = this.web.insert(this.model);

        Assert.assertTrue(result >= 0);
    }
    
    /** Test case Get Entity. */
    public void testGet() {
        this.server.enqueue(new MockResponse().setBody(
                this.web.itemToJson(this.model).toString()));

        int result = this.web.get(this.model);

        Assert.assertTrue(result >= 0);
    }

    /** Test case Read Entity. */
    public void testQuery() {
        this.server.enqueue(new MockResponse().setBody(
                this.web.itemToJson(this.model).toString()));

        Cursor result = this.web.query(this.model.getId());
        
        Assert.assertTrue(result.getCount() >= 0);
    }

    /** Test case get all Entity. */
    public void testGetAll() {
        this.server.enqueue(new MockResponse().setBody("{Pokemonss :"
            + this.web.itemsToJson(this.entities).toString() + "}"));

        ArrayList<Pokemons> pokemonsList = 
                new ArrayList<Pokemons>();

        int result = this.web.getAll(pokemonsList);

        Assert.assertEquals(pokemonsList.size(), this.entities.size());
    }
    
    /** Test case Update Entity. */
    public void testUpdate() {
        this.server.enqueue(new MockResponse().setBody("{'result'='1'}"));

        int result = this.web.update(this.model);

        Assert.assertTrue(result >= 0);
        
        this.server.enqueue(new MockResponse().setBody(
                this.web.itemToJson(this.model).toString()));

        Pokemons item = new Pokemons();
        item.setId(this.model.getId());

        result = this.web.get(item);
        
        PokemonsUtils.equals(this.model, item);
    }
    
    /** Test case Delete Entity. */
    public void testDelete() {
        this.server.enqueue(new MockResponse().setBody("{'result'='1'}"));

        int result = this.web.delete(this.model);

        Assert.assertTrue(result == 0);

        this.server.enqueue(new MockResponse().setBody("{}"));

        result = this.web.get(this.model);

        Assert.assertTrue(result < 0);
    }
}