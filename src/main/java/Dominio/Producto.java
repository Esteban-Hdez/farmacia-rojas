package Dominio;

import java.util.Date;

public class Producto {
    private int idProducto;
    private String codigoBarras;
    private String nombre;
    private String fechaIngreso;
    private String fechaVencimiento;
    private String tipo;
    private int idTipo;
    private int cantidad;
    private double precio;

    public Producto() {
    }

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(String codigoBarras, String nombre, String fechaIngreso, String fechaVencimiento, int idTipo, int cantidad, double precio) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.idTipo = idTipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto(int idProducto, String codigoBarras, String nombre, String fechaIngreso, String fechaVencimiento, String tipo, int cantidad, double precio) {
        this.idProducto = idProducto;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public Producto(int idProducto, String codigoBarras, String nombre, String fechaIngreso, String fechaVencimiento, int idTipo, int cantidad, double precio) {
        this.idProducto = idProducto;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.idTipo = idTipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", codigoBarras=" + codigoBarras + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso + ", fechaVencimiento=" + fechaVencimiento + ", tipo=" + idTipo + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
}
