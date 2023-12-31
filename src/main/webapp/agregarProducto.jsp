<%@page import="java.util.List"%>
<%@page import="Dominio.TipoProducto"%>
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
                <h1 class="h3 mb-4 text-gray-800">Agregar Producto</h1>

                <div class="row">

                    <div class="col-md-8">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Ingrese los datos correspondientes</h6>
                            </div>
                            <div class="card-body">

                                <%@include file="/componentes/mensaje.jsp" %>

                                <form action="SvAgregarProducto" method="POST">

                                    <div class="form-group">
                                        <label for="codigoBarras">C�digo de barras</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text"><i class="bi bi-upc"></i></div>
                                            </div>
                                            <input type="text" class="form-control" id="codigoBarras" name="codigoBarras" placeholder="" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="nombreProducto">Nombre del producto</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text"><i class="bi bi-prescription2"></i></div>
                                            </div>
                                            <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" placeholder="" required>
                                        </div>
                                    </div>

                                    <div class="form-row">

                                        <div class="form-group col-md-6">
                                            <label for="fechaIngreso">Fecha de ingreso</label>
                                            <input type="date" class="form-control" id="fechaIngreso" name="fechaIngreso" placeholder="" required>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label for="fechaVencimiento">Fecha de vencimiento</label>
                                            <input type="date" class="form-control" id="fechaVencimiento" name="fechaVencimiento" placeholder="Fecha de vencimiento" required>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <label for="tipoMedicamento">Tipo de medicamento</label>
                                        <div class="input-group mb-2">
                                            <div class="input-group-prepend">
                                                <div class="input-group-text"><i class="bi bi-capsule"></i></div>
                                            </div>
                                            <%                                                List<TipoProducto> tipoProductos = (List) request.getSession().getAttribute("tiposProductos");
                                            %>
                                            <select class="form-control" aria-label="Default select example" id="tipoMedicamento" name="tipoMedicamento" required="required">
                                                <option value="" >~ Selecciona tipo de medicamento ~</option>
                                                <% for (TipoProducto tp : tipoProductos) {%>
                                                <option value="<%=tp.getIdTipoProducto()%>"><%=tp.getDescripcion()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-row">

                                        <div class="form-group col-md-6">
                                            <label for="cantidad">Cantidad</label>
                                            <div class="input-group mb-2">
                                                <div class="input-group-prepend">
                                                    <div class="input-group-text"><i class="bi bi-hash"></i></div>
                                                </div>
                                                <input type="number" class="form-control" id="cantidad" name="cantidad" placeholder="" required>
                                            </div>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label for="precio">Precio</label>
                                            <div class="input-group mb-2">
                                                <div class="input-group-prepend">
                                                    <div class="input-group-text">Q</div>
                                                </div>
                                                <input type="number" step="0.01" class="form-control" id="precio" name="precio" placeholder="" required>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group" style="margin-top: 5%;">             

                                        <button type="submit" class="btn btn-success btn-icon-split">
                                            <span class="icon text-white-50">
                                                <i class="bi bi-plus-lg"></i>
                                            </span>
                                            <span class="text">Agregar</span>
                                        </button>
                                        <button type="reset" class="btn btn-warning btn-icon-split">
                                            <span class="icon text-white-50">
                                                <i class="fas bi-trash2"></i>
                                            </span>
                                            <span class="text">Limpiar</span>
                                        </button>
                                        
                                    </div>

                                </form>

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


        <%@include file="/componentes/footer.jsp" %>

        <%@include file="/componentes/scripts.jsp" %>

        <%@include file="/componentes/endPage.jsp" %>