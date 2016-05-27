/**************************************************************************
 * TypeObjetsTestProviderBase.java, pokemon Android
 *
 * Copyright 2016
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : May 27, 2016
 *
 **************************************************************************/
package com.kevinguegancamillepaviot.pokemon.test.base;

import android.test.suitebuilder.annotation.SmallTest;

import com.kevinguegancamillepaviot.pokemon.provider.TypeObjetsProviderAdapter;
import com.kevinguegancamillepaviot.pokemon.provider.utils.TypeObjetsProviderUtils;
import com.kevinguegancamillepaviot.pokemon.provider.contract.TypeObjetsContract;

import com.kevinguegancamillepaviot.pokemon.data.TypeObjetsSQLiteAdapter;

import com.kevinguegancamillepaviot.pokemon.entity.TypeObjets;


import java.util.ArrayList;
import com.kevinguegancamillepaviot.pokemon.test.utils.*;


import android.content.ContentResolver;
import android.content.ContentValues;


import android.net.Uri;

import junit.framework.Assert;

/** TypeObjets database test abstract class <br/>
 * <b><i>This class will be overwrited whenever you regenerate the project with Harmony.
 * You should edit TypeObjetsTestDB class instead of this one or you will lose all your modifications.</i></b>
 */
public abstract class TypeObjetsTestProviderBase extends TestDBBase {
    protected android.content.Context ctx;

    protected TypeObjetsSQLiteAdapter adapter;

    protected TypeObjets entity;
    protected ContentResolver provider;
    protected TypeObjetsProviderUtils providerUtils;

    protected ArrayList<TypeObjets> entities;

    protected int nbEntities = 0;
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        this.ctx = this.getContext();

        this.adapter = new TypeObjetsSQLiteAdapter(this.ctx);

        this.provider = this.getContext().getContentResolver();
        this.providerUtils = new TypeObjetsProviderUtils(this.getContext());
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /********** Direct Provider calls. *******/

    /** Test case Create Entity */
    @SmallTest
    public void testCreate() {
        Uri result = null;
        if (this.entity != null) {
            TypeObjets typeObjets = TypeObjetsUtils.generateRandom(this.ctx);

            try {
                ContentValues values = TypeObjetsContract.itemToContentValues(typeObjets);
                values.remove(TypeObjetsContract.COL_ID);
                result = this.provider.insert(TypeObjetsProviderAdapter.TYPEOBJETS_URI, values);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(result);
            Assert.assertTrue(Integer.parseInt(result.getPathSegments().get(1)) > 0);        
            
        }
    }

    /** Test case Read Entity */
    @SmallTest
    public void testRead() {
        TypeObjets result = null;

        if (this.entity != null) {
            try {
                android.database.Cursor c = this.provider.query(Uri.parse(
                        TypeObjetsProviderAdapter.TYPEOBJETS_URI
                                + "/" 
                                + this.entity.getId()),
                        this.adapter.getCols(),
                        null,
                        null,
                        null);
                c.moveToFirst();
                result = TypeObjetsContract.cursorToItem(c);
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            TypeObjetsUtils.equals(this.entity, result);
        }
    }

    /** Test case ReadAll Entity */
    @SmallTest
    public void testReadAll() {
        ArrayList<TypeObjets> result = null;
        try {
            android.database.Cursor c = this.provider.query(TypeObjetsProviderAdapter.TYPEOBJETS_URI, this.adapter.getCols(), null, null, null);
            result = TypeObjetsContract.cursorToItems(c);
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(result);
        if (result != null) {
            Assert.assertEquals(result.size(), this.nbEntities);
        }
    }

    /** Test case Update Entity */
    @SmallTest
    public void testUpdate() {
        int result = -1;
        if (this.entity != null) {
            TypeObjets typeObjets = TypeObjetsUtils.generateRandom(this.ctx);

            try {
                typeObjets.setId(this.entity.getId());

                ContentValues values = TypeObjetsContract.itemToContentValues(typeObjets);
                result = this.provider.update(
                    Uri.parse(TypeObjetsProviderAdapter.TYPEOBJETS_URI
                        + "/"
                        + typeObjets.getId()),
                    values,
                    null,
                    null);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Assert.assertTrue(result > 0);
        }
    }

    /** Test case UpdateAll Entity */
    @SmallTest
    public void testUpdateAll() {
        int result = -1;
        if (this.entities != null) {
            if (this.entities.size() > 0) {
                TypeObjets typeObjets = TypeObjetsUtils.generateRandom(this.ctx);
    
                try {
                    ContentValues values = TypeObjetsContract.itemToContentValues(typeObjets);
                    values.remove(TypeObjetsContract.COL_ID);
    
                    result = this.provider.update(TypeObjetsProviderAdapter.TYPEOBJETS_URI, values, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
    
                Assert.assertEquals(result, this.nbEntities);
            }
        }
    }

    /** Test case Delete Entity */
    @SmallTest
    public void testDelete() {
        int result = -1;
        if (this.entity != null) {
            try {
                result = this.provider.delete(
                        Uri.parse(TypeObjetsProviderAdapter.TYPEOBJETS_URI
                            + "/" 
                            + this.entity.getId()),
                        null,
                        null);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.assertTrue(result >= 0);
        }

    }

    /** Test case DeleteAll Entity */
    @SmallTest
    public void testDeleteAll() {
        int result = -1;
        if (this.entities != null) {
            if (this.entities.size() > 0) {
    
                try {
                    result = this.provider.delete(TypeObjetsProviderAdapter.TYPEOBJETS_URI, null, null);
    
                } catch (Exception e) {
                    e.printStackTrace();
                }
    
                Assert.assertEquals(result, this.nbEntities);
            }
        }
    }

    /****** Provider Utils calls ********/

    /** Test case Read Entity by provider utils. */
    @SmallTest
    public void testUtilsRead() {
        TypeObjets result = null;

        if (this.entity != null) {
            result = this.providerUtils.query(this.entity);

            TypeObjetsUtils.equals(this.entity, result);
        }
    }

    /** Test case ReadAll Entity by provider utils. */
    @SmallTest
    public void testUtilsReadAll() {
        ArrayList<TypeObjets> result = null;
        result = this.providerUtils.queryAll();

        Assert.assertNotNull(result);
        if (result != null) {
            Assert.assertEquals(result.size(), this.nbEntities);
        }
    }

    /** Test case Update Entity by provider utils. */
    @SmallTest
    public void testUtilsUpdate() {
        int result = -1;
        if (this.entity != null) {
            TypeObjets typeObjets = TypeObjetsUtils.generateRandom(this.ctx);

            typeObjets.setId(this.entity.getId());
            result = this.providerUtils.update(typeObjets);

            Assert.assertTrue(result > 0);
        }
    }


    /** Test case Delete Entity by provider utils. */
    @SmallTest
    public void testUtilsDelete() {
        int result = -1;
        if (this.entity != null) {
            result = this.providerUtils.delete(this.entity);
            Assert.assertTrue(result >= 0);
        }

    }
}