package com.kevinguegancamillepaviot.pokemon.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import com.tactfactory.harmony.annotation.Column;
import com.tactfactory.harmony.annotation.GeneratedValue;
import com.tactfactory.harmony.annotation.Id;
import com.tactfactory.harmony.annotation.OneToMany;
import com.tactfactory.harmony.bundles.rest.annotation.Rest;
import com.tactfactory.harmony.annotation.Column.Type;
import com.tactfactory.harmony.annotation.Entity;
import com.tactfactory.harmony.annotation.GeneratedValue.Strategy;

@Entity
@Rest
public class Dresseurs  implements Serializable , Parcelable {

    /** Parent parcelable for parcellisation purposes. */
    protected List<Parcelable> parcelableParents;


	@Id
    @Column(type = Type.INTEGER, hidden = true)
    @GeneratedValue(strategy = Strategy.MODE_IDENTITY)
	private int id;
	
	@Column(type = Type.STRING)
	private String nom;
	
	@Column(type = Type.STRING)
	private String login;	
	
	@Column(type = Type.STRING)
	private String password;
	
	@OneToMany()
	@Column()
	private ArrayList<Npcs> npc;

    /**
     * Default constructor.
     */
    public Dresseurs() {

    }

     /**
     * Get the Id.
     * @return the id
     */
    public int getId() {
         return this.id;
    }
     /**
     * Set the Id.
     * @param value the id to set
     */
    public void setId(final int value) {
         this.id = value;
    }
     /**
     * Get the Nom.
     * @return the nom
     */
    public String getNom() {
         return this.nom;
    }
     /**
     * Set the Nom.
     * @param value the nom to set
     */
    public void setNom(final String value) {
         this.nom = value;
    }
     /**
     * Get the Login.
     * @return the login
     */
    public String getLogin() {
         return this.login;
    }
     /**
     * Set the Login.
     * @param value the login to set
     */
    public void setLogin(final String value) {
         this.login = value;
    }
     /**
     * Get the Password.
     * @return the password
     */
    public String getPassword() {
         return this.password;
    }
     /**
     * Set the Password.
     * @param value the password to set
     */
    public void setPassword(final String value) {
         this.password = value;
    }
     /**
     * Get the Npc.
     * @return the npc
     */
    public ArrayList<Npcs> getNpc() {
         return this.npc;
    }
     /**
     * Set the Npc.
     * @param value the npc to set
     */
    public void setNpc(final ArrayList<Npcs> value) {
         this.npc = value;
    }
    /**
     * This stub of code is regenerated. DO NOT MODIFY.
     * 
     * @param dest Destination parcel
     * @param flags flags
     */
    public void writeToParcelRegen(Parcel dest, int flags) {
        if (this.parcelableParents == null) {
            this.parcelableParents = new ArrayList<Parcelable>();
        }
        if (!this.parcelableParents.contains(this)) {
            this.parcelableParents.add(this);
        }
        dest.writeInt(this.getId());
        if (this.getNom() != null) {
            dest.writeInt(1);
            dest.writeString(this.getNom());
        } else {
            dest.writeInt(0);
        }
        if (this.getLogin() != null) {
            dest.writeInt(1);
            dest.writeString(this.getLogin());
        } else {
            dest.writeInt(0);
        }
        if (this.getPassword() != null) {
            dest.writeInt(1);
            dest.writeString(this.getPassword());
        } else {
            dest.writeInt(0);
        }

        if (this.getNpc() != null) {
            dest.writeInt(this.getNpc().size());
            for (Npcs item : this.getNpc()) {
                if (!this.parcelableParents.contains(item)) {
                    item.writeToParcel(this.parcelableParents, dest, flags);
                } else {
                    dest.writeParcelable(null, flags);
                }
            }
        } else {
            dest.writeInt(-1);
        }
    }

    /**
     * Regenerated Parcel Constructor. 
     *
     * This stub of code is regenerated. DO NOT MODIFY THIS METHOD.
     *
     * @param parc The parcel to read from
     */
    public void readFromParcel(Parcel parc) {
        this.setId(parc.readInt());
        int nomBool = parc.readInt();
        if (nomBool == 1) {
            this.setNom(parc.readString());
        }
        int loginBool = parc.readInt();
        if (loginBool == 1) {
            this.setLogin(parc.readString());
        }
        int passwordBool = parc.readInt();
        if (passwordBool == 1) {
            this.setPassword(parc.readString());
        }

        int nbNpc = parc.readInt();
        if (nbNpc > -1) {
            ArrayList<Npcs> items =
                new ArrayList<Npcs>();
            for (int i = 0; i < nbNpc; i++) {
                items.add((Npcs) parc.readParcelable(
                        Npcs.class.getClassLoader()));
            }
            this.setNpc(items);
        }
    }

    /**
     * Parcel Constructor.
     *
     * @param parc The parcel to read from
     */
    public Dresseurs(Parcel parc) {
        // You can chose not to use harmony's generated parcel.
        // To do this, remove this line.
        this.readFromParcel(parc);

        // You can  implement your own parcel mechanics here.

    }

    /* This method is not regenerated. You can implement your own parcel mechanics here. */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // You can chose not to use harmony's generated parcel.
        // To do this, remove this line.
        this.writeToParcelRegen(dest, flags);
        // You can  implement your own parcel mechanics here.
    }

    /**
     * Use this method to write this entity to a parcel from another entity.
     * (Useful for relations)
     *
     * @param parent The entity being parcelled that need to parcel this one
     * @param dest The destination parcel
     * @param flags The flags
     */
    public synchronized void writeToParcel(List<Parcelable> parents, Parcel dest, int flags) {
        this.parcelableParents = new ArrayList<Parcelable>(parents);
        dest.writeParcelable(this, flags);
        this.parcelableParents = null;
    }

    @Override
    public int describeContents() {
        // This should return 0 
        // or CONTENTS_FILE_DESCRIPTOR if your entity is a FileDescriptor.
        return 0;
    }

    /**
     * Parcelable creator.
     */
    public static final Parcelable.Creator<Dresseurs> CREATOR
        = new Parcelable.Creator<Dresseurs>() {
        public Dresseurs createFromParcel(Parcel in) {
            return new Dresseurs(in);
        }
        
        public Dresseurs[] newArray(int size) {
            return new Dresseurs[size];
        }
    };

}
