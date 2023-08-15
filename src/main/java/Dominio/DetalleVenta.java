package Dominio;

import java.io.Serializable;

public class DetalleVenta implements Serializable{
    private Producto producto;
    private int cantidadVendida;

    public DetalleVenta() {
    }
    
    public DetalleVenta(Producto producto, int cantidadVendida) {
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidadVendida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "producto=" + producto + ", cantidadVendida=" + cantidadVendida + '}';
    }
    
}
