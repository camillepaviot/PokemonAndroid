/**************************************************************************
 * TypeDePokemonsProviderAdapterBase.java, pokemon Android
 *
 * Copyright 2016
 * Description : 
 * Author(s)   : Harmony
 * Licence     : 
 * Last update : May 27, 2016
 *
 **************************************************************************/
package com.kevinguegancamillepaviot.pokemon.provider.base;

import android.content.ContentUris;
import android.content.ContentValues;


import com.google.common.collect.ObjectArrays;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;



import com.kevinguegancamillepaviot.pokemon.entity.TypeDePokemons;
import com.kevinguegancamillepaviot.pokemon.provider.ProviderAdapter;
import com.kevinguegancamillepaviot.pokemon.provider.PokemonProvider;
import com.kevinguegancamillepaviot.pokemon.provider.contract.TypeDePokemonsContract;
import com.kevinguegancamillepaviot.pokemon.provider.contract.TypeDePokemonsContract;
import com.kevinguegancamillepaviot.pokemon.data.TypeDePokemonsSQLiteAdapter;
import com.kevinguegancamillepaviot.pokemon.data.TypesSQLiteAdapter;
import com.kevinguegancamillepaviot.pokemon.data.TypeDePokemonstoTypeDePokemonsSQLiteAdapter;
import com.kevinguegancamillepaviot.pokemon.data.PokedexsSQLiteAdapter;

/**
 * TypeDePokemonsProviderAdapterBase.
 */
public abstract class TypeDePokemonsProviderAdapterBase
                extends ProviderAdapter<TypeDePokemons> {

    /** TAG for debug purpose. */
    protected static final String TAG = "TypeDePokemonsProviderAdapter";

    /** TYPEDEPOKEMONS_URI. */
    public      static Uri TYPEDEPOKEMONS_URI;

    /** typeDePokemons type. */
    protected static final String typeDePokemonsType =
            "typedepokemons";

    /** TYPEDEPOKEMONS_ALL. */
    protected static final int TYPEDEPOKEMONS_ALL =
            2041820315;
    /** TYPEDEPOKEMONS_ONE. */
    protected static final int TYPEDEPOKEMONS_ONE =
            2041820316;

    /** TYPEDEPOKEMONS_TYPE. */
    protected static final int TYPEDEPOKEMONS_TYPE =
            2041820317;
    /** TYPEDEPOKEMONS_TYPEDEPOKEMON. */
    protected static final int TYPEDEPOKEMONS_TYPEDEPOKEMON =
            2041820318;
    /** TYPEDEPOKEMONS_POKEDEX. */
    protected static final int TYPEDEPOKEMONS_POKEDEX =
            2041820319;

    /**
     * Static constructor.
     */
    static {
        TYPEDEPOKEMONS_URI =
                PokemonProvider.generateUri(
                        typeDePokemonsType);
        PokemonProvider.getUriMatcher().addURI(
                PokemonProvider.authority,
                typeDePokemonsType,
                TYPEDEPOKEMONS_ALL);
        PokemonProvider.getUriMatcher().addURI(
                PokemonProvider.authority,
                typeDePokemonsType + "/#",
                TYPEDEPOKEMONS_ONE);
        PokemonProvider.getUriMatcher().addURI(
                PokemonProvider.authority,
                typeDePokemonsType + "/#" + "/type",
                TYPEDEPOKEMONS_TYPE);
        PokemonProvider.getUriMatcher().addURI(
                PokemonProvider.authority,
                typeDePokemonsType + "/#" + "/typedepokemon",
                TYPEDEPOKEMONS_TYPEDEPOKEMON);
        PokemonProvider.getUriMatcher().addURI(
                PokemonProvider.authority,
                typeDePokemonsType + "/#" + "/pokedex",
                TYPEDEPOKEMONS_POKEDEX);
    }

    /**
     * Constructor.
     * @param ctx context
     * @param db database
     */
    public TypeDePokemonsProviderAdapterBase(
            PokemonProviderBase provider) {
        super(
            provider,
            new TypeDePokemonsSQLiteAdapter(provider.getContext()));

        this.uriIds.add(TYPEDEPOKEMONS_ALL);
        this.uriIds.add(TYPEDEPOKEMONS_ONE);
        this.uriIds.add(TYPEDEPOKEMONS_TYPE);
        this.uriIds.add(TYPEDEPOKEMONS_TYPEDEPOKEMON);
        this.uriIds.add(TYPEDEPOKEMONS_POKEDEX);
    }

    @Override
    public String getType(final Uri uri) {
        String result;
        final String single =
                "vnc.android.cursor.item/"
                    + PokemonProvider.authority + ".";
        final String collection =
                "vnc.android.cursor.collection/"
                    + PokemonProvider.authority + ".";

        int matchedUri = PokemonProviderBase
                .getUriMatcher().match(uri);

        switch (matchedUri) {
            case TYPEDEPOKEMONS_ALL:
                result = collection + "typedepokemons";
                break;
            case TYPEDEPOKEMONS_ONE:
                result = single + "typedepokemons";
                break;
            case TYPEDEPOKEMONS_TYPE:
                result = single + "typedepokemons";
                break;
            case TYPEDEPOKEMONS_TYPEDEPOKEMON:
                result = collection + "typedepokemons";
                break;
            case TYPEDEPOKEMONS_POKEDEX:
                result = single + "typedepokemons";
                break;
            default:
                result = null;
                break;
        }

        return result;
    }

    @Override
    public int delete(
            final Uri uri,
            String selection,
            String[] selectionArgs) {
        int matchedUri = PokemonProviderBase
                    .getUriMatcher().match(uri);
        int result = -1;
        switch (matchedUri) {
            case TYPEDEPOKEMONS_ONE:
                String id = uri.getPathSegments().get(1);
                selection = TypeDePokemonsContract.COL_ID
                        + " = ?";
                selectionArgs = new String[1];
                selectionArgs[0] = id;
                result = this.adapter.delete(
                        selection,
                        selectionArgs);
                break;
            case TYPEDEPOKEMONS_ALL:
                result = this.adapter.delete(
                            selection,
                            selectionArgs);
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }

    @Override
    public Uri insert(final Uri uri, final ContentValues values) {
        int matchedUri = PokemonProviderBase
                .getUriMatcher().match(uri);
                Uri result = null;
        int id = 0;
        switch (matchedUri) {
            case TYPEDEPOKEMONS_ALL:
                if (values.size() > 0) {
                    id = (int) this.adapter.insert(null, values);
                } else {
                    id = (int) this.adapter.insert(TypeDePokemonsContract.COL_ID, values);
                }
                if (id > 0) {
                    result = Uri.withAppendedPath(
                            TYPEDEPOKEMONS_URI,
                            String.valueOf(id));
                }
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    @Override
    public android.database.Cursor query(final Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        final String sortOrder) {

        int matchedUri = PokemonProviderBase.getUriMatcher()
                .match(uri);
        android.database.Cursor result = null;
        android.database.Cursor typeDePokemonsCursor;
        int typedepokemonsId;

        switch (matchedUri) {

            case TYPEDEPOKEMONS_ALL:
                result = this.adapter.query(
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder);
                break;
            case TYPEDEPOKEMONS_ONE:
                result = this.queryById(uri.getPathSegments().get(1));
                break;

            case TYPEDEPOKEMONS_TYPE:
                typeDePokemonsCursor = this.queryById(
                        uri.getPathSegments().get(1));

                if (typeDePokemonsCursor.getCount() > 0) {
                    typeDePokemonsCursor.moveToFirst();
                    int typeId = typeDePokemonsCursor.getInt(
                            typeDePokemonsCursor.getColumnIndex(
                                    TypeDePokemonsContract.COL_TYPE_ID));

                    TypesSQLiteAdapter typesAdapter = new TypesSQLiteAdapter(this.ctx);
                    typesAdapter.open(this.getDb());
                    result = typesAdapter.query(typeId);
                }
                break;

            case TYPEDEPOKEMONS_TYPEDEPOKEMON:
                typedepokemonsId = Integer.parseInt(uri.getPathSegments().get(1));
                TypeDePokemonstoTypeDePokemonsSQLiteAdapter typeDePokemonAdapter = new TypeDePokemonstoTypeDePokemonsSQLiteAdapter(this.ctx);
                typeDePokemonAdapter.open(this.getDb());
                result = typeDePokemonAdapter.getByTypeDePokemonsInternalId(typedepokemonsId, TypeDePokemonsContract.ALIASED_COLS, selection, selectionArgs, null);
                break;

            case TYPEDEPOKEMONS_POKEDEX:
                typeDePokemonsCursor = this.queryById(
                        uri.getPathSegments().get(1));

                if (typeDePokemonsCursor.getCount() > 0) {
                    typeDePokemonsCursor.moveToFirst();
                    int pokedexId = typeDePokemonsCursor.getInt(
                            typeDePokemonsCursor.getColumnIndex(
                                    TypeDePokemonsContract.COL_POKEDEX_ID));

                    PokedexsSQLiteAdapter pokedexsAdapter = new PokedexsSQLiteAdapter(this.ctx);
                    pokedexsAdapter.open(this.getDb());
                    result = pokedexsAdapter.query(pokedexId);
                }
                break;

            default:
                result = null;
                break;
        }

        return result;
    }

    @Override
    public int update(
            final Uri uri,
            final ContentValues values,
            String selection,
            String[] selectionArgs) {

        
        int matchedUri = PokemonProviderBase.getUriMatcher()
                .match(uri);
        int result = -1;
        switch (matchedUri) {
            case TYPEDEPOKEMONS_ONE:
                selectionArgs = new String[1];
                selection = TypeDePokemonsContract.COL_ID + " = ?";
                selectionArgs[0] = uri.getPathSegments().get(1);
                result = this.adapter.update(
                        values,
                        selection,
                        selectionArgs);
                break;
            case TYPEDEPOKEMONS_ALL:
                result = this.adapter.update(
                            values,
                            selection,
                            selectionArgs);
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }



    /**
     * Get the entity URI.
     * @return The URI
     */
    @Override
    public Uri getUri() {
        return TYPEDEPOKEMONS_URI;
    }

    /**
     * Query by ID.
     *
     * @param id The id of the entity to retrieve
     * @return The cursor
     */
    private android.database.Cursor queryById(String id) {
        android.database.Cursor result = null;
        String selection = TypeDePokemonsContract.ALIASED_COL_ID
                        + " = ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = id;
        
        

        result = this.adapter.query(
                    TypeDePokemonsContract.ALIASED_COLS,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);
        return result;
    }
}

