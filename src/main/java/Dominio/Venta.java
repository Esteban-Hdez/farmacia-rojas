package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venta implements Serializable{
    private int idVenta;
    private String fechaVenta;
    private double totalVenta;
    private List<DetalleVenta> detalles;

    public Venta() {
    }

    public Venta(int idVenta, String fechaVenta, double totalVenta, List<DetalleVenta> detalles) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.detalles = detalles;
    }

    public Venta(int idVenta, String fechaVenta, double totalVenta) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
    }
    
    
    
    public Venta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
        this.totalVenta = 0;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        this.totalVenta += detalle.getSubtotal();
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", totalVenta=" + totalVenta + ", detalles=" + detalles + '}';
    }

    
}
