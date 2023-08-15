<%@page import="Dominio.DetalleVenta"%>
<%@page import="Dominio.Venta"%>
<%@include file="/componentes/encabezado.jsp" %>

<body id="page-top">

    <%@include file="/componentes/menu.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <%@include file="/componentes/encabezado_usuario.jsp" %>


            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-4 text-gray-800">Historial de Ventas</h1>

                <div class="row">

                    <div class="col-md-8">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Tabla de ventas realizadas</h6>

                            </div>
                            <div class="card-body">

                                <%@include file="/componentes/mensaje.jsp" %>

                                <div class="table-responsive">

                                    <table id="my-table" class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Fecha de venta</th>
                                                <th scope="col">Total (Q)</th>
                                                <th scope="col">Acciones</th>
                                            </tr>
                                        </thead>
                                        <%                                            List<Venta> ventas = (List) request.getSession().getAttribute("ventas");
                                            int index = 0;
                                        %>
                                        <tbody>
                                            <% for (Venta v : ventas) {%>
                                            <tr>
                                                <th class="prod-id" scope="row"><%=v.getIdVenta()%></th>
                                                <td><%=v.getFechaVenta()%></td>
                                                <td><%=v.getTotalVenta()%></td>
                                                <td>
                                                    <div class="row">
                                                        <button type="button" class="btn btn-danger btn-sm btn-delete-venta btn-circle"
                                                                data-venta-id="<%= v.getIdVenta()%>">
                                                            <i class="bi bi-trash"></i>
                                                        </button>
                                                        &nbsp;
                                                        <button id="btnDetalleVenta" type="button" data-toggle="modal" onclick="obtenerDetalles(<%=v.getIdVenta()%>)" data-target="#detalleVenta" class="btn btn-info btn-sm btn-circle"><i class="bi bi-eye-fill"></i></button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%
                                                    index++;
                                                }%>
                                        </tbody>
                                    </table>

                                    <!-- Modal de confirmaci�n de eliminaci�n de venta -->
                                    <div class="modal fade" id="deleteVentaModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="deleteVentaLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteVentaLabel"><i class="bi bi-trash text-danger"></i>&nbsp;&nbsp;Eliminar Venta</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form id="deleteVentaForm" method="POST">
                                                    <div class="modal-body">
                                                        <input type="hidden" name="idVenta" id="idVentaToDelete">
                                                        �Est�s seguro de que deseas eliminar esta venta?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>


                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <%@include file="/componentes/status.jsp" %>
                    </div>

                </div>

            </div>

        </div>
        <!-- End of Main Content -->

        <!-- Detalle venta modal -->
        <div class="modal fade" id="detalleVenta" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Detalles de la venta</h5>
                        <button type="button" onclick="vaciarTabla()" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body container-fluid">

                        <div class="table-responsive" style="margin-top: 2%;">

                            <table id="my-table2" class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Producto</th>
                                        <th scope="col">Precio (Q)</th>
                                        <th scope="col">Cantidad Vendida</th>
                                        <th scope="col">Subtotal (Q)</th>
                                    </tr>
                                </thead>
                                <tbody id="tbDetalles">
                                </tbody>
                            </table>

                            <div class="text-right" style="margin-right: 10%; margin-bottom: 1%; margin-top: 5%;">
                                <span class="h4"><strong>Total:</strong> Q <span id="totalVenta"></span></span>
                            </div>

                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="vaciarTabla()" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/componentes/footer.jsp" %>

        <%@include file="/componentes/scripts.jsp" %>


        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
        <script>
                            new DataTable('#my-table', {
                                lengthMenu: [
                                    [10, 20, -1],
                                    [10, 20, 'Todos']
                                ],
                                language: {
                                    url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/es-ES.json',
                                }
                            });
        </script>

        <script>
            $(document).ready(function () {
                $('.btn-delete-venta').click(function () {
                    const ventaId = $(this).data('venta-id');
                    $('#idVentaToDelete').val(ventaId);
                    $('#deleteVentaModal').modal('show');
                });
            });
        </script>

        <script>
            function obtenerDetalles(index) {
                // Obtener los datos de productos del backend usando JavaScript
                let total = 0;
                let elementTotal = document.getElementById("totalVenta");
                elementTotal.innerHTML = "0.00"
                fetch('/Farmaciav1/SvDetalleVenta?index=' + index)
                        .then(response => response.json()) // Aqu� se espera una respuesta JSON
                        .then(data => {
                            data.forEach(detalle => {

                                console.log(detalle.producto.nombre);
                                console.log(detalle.producto.precio);
                                console.log(detalle.cantidadVendida);

                                let table = document.getElementById("tbDetalles");
                                let row = table.insertRow(-1);
                                let cell0 = row.insertCell(0); //nombre
                                let cell1 = row.insertCell(1); //precio
                                let cell2 = row.insertCell(2); //cantidad vendida
                                let cell3 = row.insertCell(3); //subtotal

                                let subtotal = detalle.producto.precio * detalle.cantidadVendida;

                                cell0.innerHTML = detalle.producto.nombre;
                                cell1.innerHTML = detalle.producto.precio;
                                cell2.innerHTML = detalle.cantidadVendida;
                                cell3.innerHTML = subtotal.toFixed(2);

                                total += subtotal;

                            });
                            console.log(total);
                            elementTotal.innerHTML = total.toFixed(2);
                        });

            }

            function vaciarTabla() {
                $("#tbDetalles tr").remove();
                let elementTotal = document.getElementById("totalVenta");
                elementTotal.innerHTML = "0.00";
            }
        </script>

        <%@include file="/componentes/endPage.jsp" %>

