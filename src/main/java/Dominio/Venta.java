package Dominio;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private Date fechaVenta;
    private double totalVenta;
    private List<DetalleVenta> detalles;

    public Venta() {
    }

    public Venta(int idVenta, Date fechaVenta, double totalVenta, List<DetalleVenta> detalles) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.detalles = detalles;
    }
    
    public Venta(Date fechaVenta) {
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

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
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

    
}
