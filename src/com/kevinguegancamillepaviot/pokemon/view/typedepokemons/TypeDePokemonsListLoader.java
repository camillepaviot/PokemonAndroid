/**************************************************************************
 * TypeDePokemonsListLoader.java, pokemon Android
 *
 * Copyright 2016
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : May 27, 2016
 *
 **************************************************************************/
package com.kevinguegancamillepaviot.pokemon.view.typedepokemons;


import android.net.Uri;
import android.support.v4.content.CursorLoader;

import com.kevinguegancamillepaviot.pokemon.criterias.base.CriteriaExpression;

/**
 * TypeDePokemons Loader.
 */
public class TypeDePokemonsListLoader
                extends CursorLoader {

    /**
     * Constructor.
     * @param ctx context
     */
    public TypeDePokemonsListLoader(
            final android.content.Context ctx) {
        super(ctx);
    }

    /**
     * Constructor.
     * @param ctx context
     * @param uri The URI associated with this loader
     * @param projection The projection to use
     * @param selection The selection
     * @param selectionArgs The selection Args
     * @param sortOrder The sort order
     */
    public TypeDePokemonsListLoader(
                    android.content.Context ctx,
                    Uri uri,
                    String[] projection,
                    String selection,
                    String[] selectionArgs,
                    String sortOrder) {
        super(ctx,
                uri,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }

    /**
     * Constructor.
     * @param ctx context
     * @param uri The URI associated with this loader
     * @param projection The projection to use
     * @param expression The CriteriaExpression
     * @param sortOrder The sort order
     */
    public TypeDePokemonsListLoader(
                    android.content.Context ctx,
                    Uri uri,
                    String[] projection,
                    CriteriaExpression expression,
                    String sortOrder) {
        super(ctx,
                uri,
                projection,
                expression.toSQLiteSelection(),
                expression.toSQLiteSelectionArgs(),
                sortOrder);
    }
}
