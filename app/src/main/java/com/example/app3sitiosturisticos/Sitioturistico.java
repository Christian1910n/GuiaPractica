package com.example.app3sitiosturisticos;

import android.os.Parcel;
import android.os.Parcelable;

public class Sitioturistico implements Parcelable {
    private String nombre;
    private String ubicacion;
    private String informacion;
    private float valoracion;

    public Sitioturistico(String nombre, String ubicacion, String informacion, float valoracion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.informacion = informacion;
        this.valoracion = valoracion;
    }

    protected Sitioturistico(Parcel in) {
        nombre = in.readString();
        ubicacion = in.readString();
        informacion = in.readString();
        valoracion = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(ubicacion);
        dest.writeString(informacion);
        dest.writeFloat(valoracion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sitioturistico> CREATOR = new Creator<Sitioturistico>() {
        @Override
        public Sitioturistico createFromParcel(Parcel in) {
            return new Sitioturistico(in);
        }

        @Override
        public Sitioturistico[] newArray(int size) {
            return new Sitioturistico[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
}
