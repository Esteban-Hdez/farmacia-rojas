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

                                <button type="button" class="btn btn-primary btn-sm" style="margin-bottom: 2%;" onclick="addRow()"><i class="bi bi-cart-plus"></i>&nbsp;&nbsp;Agregar artículo</button>

                                <div class="table-responsive">

                                    <table id="my-table" class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ítem</th>
                                                <th scope="col">Código</th>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Precio</th>
                                                <th scope="col">Cantidad</th>
                                                <th scope="col">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody id="products-list">

                                        </tbody>
                                    </table>
                                    <div class="text-right" style="margin-right: 10%;">
                                        <button type="button" style="margin-right: 5%;" class="btn btn-success"><i class="bi bi-cart-check"></i>&nbsp;&nbsp;Cerrar venta</button>
                                        <span id="total"><strong>Total:</strong> Q 0.0</span>


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

        <%@include file="/componentes/footer.jsp" %>

        <%@include file="/componentes/scripts.jsp" %>

        <script>
            function addRow() {
                let table = document.getElementById("products-list");
                
                let row = table.insertRow(-1);
                let count = table.rows.length;
                
                let cell1 = row.insertCell(0);
                let cell2 = row.insertCell(1);
                let cell3 = row.insertCell(2);
                let cell4 = row.insertCell(3);
                let cell5 = row.insertCell(4);
                let cell6 = row.insertCell(5);
                let cell7 = row.insertCell(6)
                
                cell1.innerHTML = count;
                cell2.innerHTML = "<form class='d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search'>" +
                        "<div class='input-group'>" +
                        "<input type='number' name='codigoProducto" + count + "' type='text' class='form-control bg-light border-0 small' placeholder='Código del producto...' aria-label='Search' aria-describedby'basic-addon2'>" +
                        "<div class='input-group-append'>" +
                        "<button class='btn btn-info' type='button'>" +
                        "<i class='fas fa-search fa-sm'></i>" +
                        "</button>" +
                        "</div>" +
                        "</div>" +
                        "</form>";
                
                cell5.innerHTML = "<input type='number' class='form-control' min='1' max='5'>"
                
                cell7.innerHTML = "<button type='button' class='btn btn-danger btn-sm' onclick='deleteRow(this)'><i class='bi bi-x-lg'></i></button>";
            }

            function deleteRow(e) {
                let tableBody = document.getElementById("products-list");
                let row = e.parentNode.parentNode; // Con "parentNode" navegamos en el DOM para encontrar la fila
                tableBody.removeChild(row); // Y luego eliminamos la fila del cuerpo de la tabla

                let rows = tableBody.getElementsByTagName("tr"); // calculo cuantos tr hay para hacer los ciclos for en base a ello
                for (let i = 0; i < rows.length; i++) {
                    let row = rows[i]; // ingreso a todos los rows
                    let items = row.getElementsByTagName("td")[0]; // y de todos los rows, ingreso a la primera columna ("items")
                    items.innerHTML = i + 1; // y todos los items los modifico
                }
            }
        </script>


        <%@include file="/componentes/endPage.jsp" %>

