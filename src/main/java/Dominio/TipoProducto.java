package Dominio;

import java.io.Serializable;

public class TipoProducto implements Serializable{
    private int idTipoProducto;
    private String descripcion;

    public TipoProducto() {
    }

    public TipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoProducto(int idTipoProducto, String descripcion) {
        this.idTipoProducto = idTipoProducto;
        this.descripcion = descripcion;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoProducto{" + "idTipoProducto=" + idTipoProducto + ", descripcion=" + descripcion + '}';
    }
}
