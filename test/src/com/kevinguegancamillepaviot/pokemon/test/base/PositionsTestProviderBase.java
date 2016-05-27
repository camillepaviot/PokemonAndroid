/**************************************************************************
 * PositionsTestProviderBase.java, pokemon Android
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

import com.kevinguegancamillepaviot.pokemon.provider.PositionsProviderAdapter;
import com.kevinguegancamillepaviot.pokemon.provider.utils.PositionsProviderUtils;
import com.kevinguegancamillepaviot.pokemon.provider.contract.PositionsContract;

import com.kevinguegancamillepaviot.pokemon.data.PositionsSQLiteAdapter;

import com.kevinguegancamillepaviot.pokemon.entity.Positions;


import java.util.ArrayList;
import com.kevinguegancamillepaviot.pokemon.test.utils.*;


import android.content.ContentResolver;
import android.content.ContentValues;


import android.net.Uri;

import junit.framework.Assert;

/** Positions database test abstract class <br/>
 * <b><i>This class will be overwrited whenever you regenerate the project with Harmony.
 * You should edit PositionsTestDB class instead of this one or you will lose all your modifications.</i></b>
 */
public abstract class PositionsTestProviderBase extends TestDBBase {
    protected android.content.Context ctx;

    protected PositionsSQLiteAdapter adapter;

    protected Positions entity;
    protected ContentResolver provider;
    protected PositionsProviderUtils providerUtils;

    protected ArrayList<Positions> entities;

    protected int nbEntities = 0;
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        this.ctx = this.getContext();

        this.adapter = new PositionsSQLiteAdapter(this.ctx);

        this.provider = this.getContext().getContentResolver();
        this.providerUtils = new PositionsProviderUtils(this.getContext());
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
            Positions positions = PositionsUtils.generateRandom(this.ctx);

            try {
                ContentValues values = PositionsContract.itemToContentValues(positions);
                values.remove(PositionsContract.COL_ID);
                result = this.provider.insert(PositionsProviderAdapter.POSITIONS_URI, values);

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
        Positions result = null;

        if (this.entity != null) {
            try {
                android.database.Cursor c = this.provider.query(Uri.parse(
                        PositionsProviderAdapter.POSITIONS_URI
                                + "/" 
                                + this.entity.getId()),
                        this.adapter.getCols(),
                        null,
                        null,
                        null);
                c.moveToFirst();
                result = PositionsContract.cursorToItem(c);
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            PositionsUtils.equals(this.entity, result);
        }
    }

    /** Test case ReadAll Entity */
    @SmallTest
    public void testReadAll() {
        ArrayList<Positions> result = null;
        try {
            android.database.Cursor c = this.provider.query(PositionsProviderAdapter.POSITIONS_URI, this.adapter.getCols(), null, null, null);
            result = PositionsContract.cursorToItems(c);
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
            Positions positions = PositionsUtils.generateRandom(this.ctx);

            try {
                positions.setId(this.entity.getId());

                ContentValues values = PositionsContract.itemToContentValues(positions);
                result = this.provider.update(
                    Uri.parse(PositionsProviderAdapter.POSITIONS_URI
                        + "/"
                        + positions.getId()),
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
                Positions positions = PositionsUtils.generateRandom(this.ctx);
    
                try {
                    ContentValues values = PositionsContract.itemToContentValues(positions);
                    values.remove(PositionsContract.COL_ID);
    
                    result = this.provider.update(PositionsProviderAdapter.POSITIONS_URI, values, null, null);
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
                        Uri.parse(PositionsProviderAdapter.POSITIONS_URI
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
                    result = this.provider.delete(PositionsProviderAdapter.POSITIONS_URI, null, null);
    
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
        Positions result = null;

        if (this.entity != null) {
            result = this.providerUtils.query(this.entity);

            PositionsUtils.equals(this.entity, result);
        }
    }

    /** Test case ReadAll Entity by provider utils. */
    @SmallTest
    public void testUtilsReadAll() {
        ArrayList<Positions> result = null;
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
            Positions positions = PositionsUtils.generateRandom(this.ctx);

            positions.setId(this.entity.getId());
            result = this.providerUtils.update(positions);

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
