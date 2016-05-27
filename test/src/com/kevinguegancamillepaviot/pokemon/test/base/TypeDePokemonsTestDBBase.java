/**************************************************************************
 * TypeDePokemonsTestDBBase.java, pokemon Android
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

import android.test.suitebuilder.annotation.SmallTest;

import com.kevinguegancamillepaviot.pokemon.data.TypeDePokemonsSQLiteAdapter;
import com.kevinguegancamillepaviot.pokemon.entity.TypeDePokemons;


import com.kevinguegancamillepaviot.pokemon.test.utils.*;

import junit.framework.Assert;

/** TypeDePokemons database test abstract class <br/>
 * <b><i>This class will be overwrited whenever you regenerate the project with Harmony.
 * You should edit TypeDePokemonsTestDB class instead of this one or you will lose all your modifications.</i></b>
 */
public abstract class TypeDePokemonsTestDBBase extends TestDBBase {
    protected android.content.Context ctx;

    protected TypeDePokemonsSQLiteAdapter adapter;

    protected TypeDePokemons entity;
    protected ArrayList<TypeDePokemons> entities;
    protected int nbEntities = 0;
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        this.ctx = this.getContext();

        this.adapter = new TypeDePokemonsSQLiteAdapter(this.ctx);
        this.adapter.open();

    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        this.adapter.close();

        super.tearDown();
    }

    /** Test case Create Entity */
    @SmallTest
    public void testCreate() {
        int result = -1;
        if (this.entity != null) {
            TypeDePokemons typeDePokemons = TypeDePokemonsUtils.generateRandom(this.ctx);

            result = (int)this.adapter.insert(typeDePokemons);

            Assert.assertTrue(result >= 0);
        }
    }

    /** Test case Read Entity */
    @SmallTest
    public void testRead() {
        TypeDePokemons result = null;
        if (this.entity != null) {
            result = this.adapter.getByID(this.entity.getId());

            TypeDePokemonsUtils.equals(this.entity, result);
        }
    }

    /** Test case Update Entity */
    @SmallTest
    public void testUpdate() {
        int result = -1;
        if (this.entity != null) {
            TypeDePokemons typeDePokemons = TypeDePokemonsUtils.generateRandom(this.ctx);
            typeDePokemons.setId(this.entity.getId());

            result = (int) this.adapter.update(typeDePokemons);

            Assert.assertTrue(result >= 0);
        }
    }

    /** Test case Update Entity */
    @SmallTest
    public void testDelete() {
        int result = -1;
        if (this.entity != null) {
            result = (int) this.adapter.remove(this.entity.getId());
            Assert.assertTrue(result >= 0);
        }
    }
    
    /** Test the get all method. */
    @SmallTest
    public void testAll() {
        int result = this.adapter.getAll().size();
        int expectedSize = this.nbEntities;
        Assert.assertEquals(expectedSize, result);
    }
}
