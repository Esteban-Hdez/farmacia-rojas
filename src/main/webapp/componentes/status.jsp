<%@page import="java.util.List"%>
<%@page import="Dominio.Producto"%>
<!-- Earnings (Monthly) Card Example -->

<%
    List<Producto> productosEstadiscas = (List) request.getSession().getAttribute("productosEstadisticos");
    
    String masVendido = "No hay Ventas para el mes elegido";
    String cantidad = "";
    if(productosEstadiscas.size()>3){
        masVendido=productosEstadiscas.get(3).getNombre();
        cantidad = productosEstadiscas.get(3).getCantidad() + " ventas";
    }
%>
<div class="mb-4 col">
    <div class="card border-left-success shadow h-100 py-2">
        <div class="card-body" style="display: grid;">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                        <h6 class="font-weight-bold">Producto más vendido</h6>
                    </div>
                    <div class="row no-gutters align-items-center">
                        <div class="col">
                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><%=productosEstadiscas.get(0).getNombre()%></div>
                        </div>
                        <div class="col">
                            <div class="h6 mb-0 mr-3"><%=productosEstadiscas.get(0).getCantidad() + " ventas"%></div>
                        </div>
                    </div>
                </div>
                <div class="col-ms-2">
                    <i class="fas fa-crown fa-2x text-gray-300"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Earnings (Monthly) Card Example -->
<div class="mb-4 col">
    <div class="card border-left-info shadow h-100 py-2">
        <div class="card-body" style="display: grid;">
            <div class="row no-gutters align-items-center" >
                <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                        <h6 class="font-weight-bold">Producto más vendido por temporada (mes)</h6>
                    </div>
                    <div class="row no-gutters align-items-center">
                        <div class="col">
                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><%=masVendido%></div>
                        </div>
                        <div class="col">
                            <div class="h6 mb-0 mr-3"><%=cantidad%></div>
                        </div>
                    </div>
                </div>
                <div class="col-ms-2">
                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Earnings (Monthly) Card Example -->
<div class="mb-4 col">
    <div class="card border-left-danger shadow h-100 py-2">
        <div class="card-body" style="display: grid;">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                        <h6 class="font-weight-bold">Producto menos vendido</h6>
                    </div>
                    <div class="row no-gutters align-items-center">
                        <div class="col">
                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><%=productosEstadiscas.get(1).getNombre()%></div>
                        </div>
                        <div class="col">
                            <div class="h6 mb-0 mr-3"><%=productosEstadiscas.get(1).getCantidad() + " ventas"%></div>
                        </div>
                    </div>
                </div>
                <div class="col-ms-2">
                    <i class="fas fa-thumbs-down fa-2x text-gray-300"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Pending Requests Card Example -->
<div class="mb-4 col">
    <div class="card border-left-primary shadow h-100 py-2">
        <div class="card-body" style="display: grid;">
            <div class="row no-gutters align-items-center">
                <div class="col mr-2">
                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                        <h6 class="font-weight-bold">Producto con más tiempo en stock</h6>
                    </div>
                    <div class="row no-gutters align-items-center">
                        <div class="col">
                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><%=productosEstadiscas.get(2).getNombre()%></div>
                        </div>
                    </div>
                </div>
                <div class="col-ms-2">
                    <i class="fas fa-boxes fa-2x text-gray-300"></i>
                </div>
            </div>
        </div>
    </div>
</div>