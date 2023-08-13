<%@page import="java.util.List"%>
<%@page import="Dominio.Producto"%>
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
                <h1 class="h3 mb-4 text-gray-800">Realizar Venta</h1>

                <div class="row">

                    <div class="col-md-8">
                        <div class="card shadow mb-4">
                            <div class="card-body">

                                <%@include file="/componentes/mensaje.jsp" %>


                                <button type="button" class="btn btn-primary btn-icon-split" style="margin-bottom: 2%;" data-toggle="modal" data-target="#articulosModal">
                                    <span class="icon text-white-50">
                                        <i class="bi bi-cart-plus"></i>
                                    </span>
                                    <span class="text">Agregar artículo</span>
                                </button>

                                <div class="table-responsive">

                                    <table id="my-table" class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ítem</th>
                                                <th scope="col">Id</th>
                                                <th scope="col">Código</th>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Precio (Q)</th>
                                                <th scope="col">Cantidad</th>
                                                <th scope="col">Subtotal (Q)</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody id="products-list">

                                        </tbody>
                                    </table>
                                    
                                    <div class="text-right" style="margin-right: 10%; margin-bottom: 1%; margin-top: 5%;">
                                        <button type="button" class="btn btn-success btn-icon-split btn-lg" style="margin-right: 5%;">
                                            <span class="icon text-white-50">
                                                <i class="bi bi-cart-check"></i>
                                            </span>
                                            <span class="text">Cerrar venta</span>
                                        </button>
                                        <span class="h4"><strong>Total:</strong> Q <span id="totalVenta">0.00</span></span>


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
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->


        <!-- Buscar productos modal -->
        <div class="modal fade" id="articulosModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Buscar artículo</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body container-fluid">

                        <div class="form-inline my-2 my-lg-0">
                            <input id="searchInput" class="form-control mr-sm-4 w-50" type="search" placeholder="Buscar...">
                            <button onclick="buscarCodigoBarras()" class="btn btn-secondary my-2 my-sm-0 mr-sm-2 btn-icon-split">
                                <span class="icon text-white-50">
                                    <i class="bi bi-search"></i>
                                </span>
                                <span class="text">Código de Barra</span>
                            </button>
                            <button onclick="buscarDescripcion()" class="btn btn-info my-2 my-sm-0 mr-sm-2 btn-icon-split">
                                <span class="icon text-white-50">
                                    <i class="bi bi-search"></i>
                                </span>
                                <span class="text">Descripción</span>
                            </button>
                        </div>

                        <div class="table-responsive" style="margin-top: 2%;">
                            
                            <h3 id="searchMsg">Sin resultados</h3>

                            <table id="my-table2" class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Código</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Precio (Q)</th>
                                        <th scope="col">Disponibilidad</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <%List<Producto> productos = (List) request.getSession().getAttribute("productos"); %>
                                <tbody>
                                    <% for (Producto p : productos) {%>
                                    <tr>
                                        <td><%=p.getIdProducto()%></td>
                                        <td><%=p.getCodigoBarras()%></td>
                                        <td><%=p.getNombre()%></td>
                                        <td><%=p.getPrecio()%></td>
                                        <td><%=p.getCantidad()%></td>
                                        <td>
                                            <button type="button" class="btn btn-success btn-sm btn-delete btn-circle" onclick="addProduct(this)"><i class="bi bi-plus-lg"></i></a>
                                        </td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>

                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/componentes/footer.jsp" %>

        <%@include file="/componentes/scripts.jsp" %>

        <script src="js/ventasFunciones.js"></script>


        <%@include file="/componentes/endPage.jsp" %>

