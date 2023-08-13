document.getElementById("my-table2").style.display = "none";


function showToast(mensaje){
    let msgDiv = document.getElementById("msgToast");
    msgDiv.innerHTML = mensaje;
    $('#toast').toast('show');
}

function addProduct(e) {
    const infomarcionProducto = [];
    let row = e.parentNode.parentNode;
    let cells = row.cells;
    for (var i = 0; i < 5; i++) {
        console.log(cells.item(i).innerHTML);
        infomarcionProducto.push(cells.item(i).innerHTML);
    }

    let table = document.getElementById("products-list");
    let newRow = table.insertRow(-1);
    let cell0 = newRow.insertCell(0); //id
    let cell1 = newRow.insertCell(1); //item
    let cell2 = newRow.insertCell(2); //codigo
    let cell3 = newRow.insertCell(3); //nombre
    let cell4 = newRow.insertCell(4); //precio
    let cell5 = newRow.insertCell(5); //cantidad
    let cell6 = newRow.insertCell(6); //total
    let cell7 = newRow.insertCell(7); //accion (eliminar)

    let count = table.rows.length;
    cell0.innerHTML = count;
    cell1.innerHTML = infomarcionProducto[0];
    cell2.innerHTML = infomarcionProducto[1];
    cell3.innerHTML = infomarcionProducto[2];
    cell4.innerHTML = infomarcionProducto[3];
    cell5.innerHTML = "<input type='number' onchange='sumarTotal(" + infomarcionProducto[3] + ", this)' onkeydown='return false' class='form-control' value='1' min='1' max='" + infomarcionProducto[4] + "'>";
    cell6.innerHTML = "<span class='subTotal'>" + infomarcionProducto[3] + "</span>";
    cell7.innerHTML = "<button type='button' class='btn btn-danger btn-sm btn-circle' onclick='deleteRow(this)'><i class='bi bi-x-lg'></i></button>";
    let totalElemnt = document.getElementById("totalVenta");
    let total = Number(totalElemnt.innerHTML) + Number(infomarcionProducto[3]);
    totalElemnt.innerHTML = total.toFixed(2);
}

function sumarTotal(precio, e) {
    let row = e.parentNode.parentNode;
    let cells = row.cells;
    let subtotal = precio * e.value;
    cells.item(6).innerHTML = "<span class='subTotal'>" + subtotal.toFixed(2) + "</span>";
    subTotalCells = document.getElementsByClassName("subTotal");
    let total = 0;
    for (var i = 0; i < subTotalCells.length; i++) {

        total += Number(subTotalCells[i].innerHTML);
    }
    let totalElemnt = document.getElementById("totalVenta");
    totalElemnt.innerHTML = total.toFixed(2);
}

function restarTotal(subtotal) {
    let totalElemnt = document.getElementById("totalVenta");
    let total = Number(totalElemnt.innerHTML) - Number(subtotal);
    totalElemnt.innerHTML = total.toFixed(2);
}

function deleteRow(e) {
    let tableBody = document.getElementById("products-list");
    let row = e.parentNode.parentNode; // Con "parentNode" navegamos en el DOM para encontrar la fila
    tableBody.removeChild(row); // Y luego eliminamos la fila del cuerpo de la tabla

    let subtotal = row.getElementsByTagName("span")[0].innerHTML;
    restarTotal(subtotal);
    let rows = tableBody.getElementsByTagName("tr"); // calculo cuantos tr hay para hacer los ciclos for en base a ello
    for (let i = 0; i < rows.length; i++) {
        let row = rows[i]; // ingreso a todos los rows
        let items = row.getElementsByTagName("td")[0]; // y de todos los rows, ingreso a la primera columna ("items")
        items.innerHTML = i + 1; // y todos los items los modifico
    }
}

function buscarCodigoBarras() {
// Declare variables
    let input, inputLength, filter, table, tr, td, i, txtValue, searchMsg, band;
    input = document.getElementById("searchInput");
    searchMsg = document.getElementById("searchMsg");
    inputLength = input.value.length;
    table = document.getElementById("my-table2");
    if (inputLength > 0) {
        filter = input.value;
        tr = table.getElementsByTagName("tr");
        band = false;
        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    band = true;
                } else {
                    tr[i].style.display = "none";
                }
            }
        }

        if (band) {
            searchMsg.style.display = "none";
            table.style.display = "";
        } else {
            searchMsg.style.display = "";
            table.style.display = "none";
        }

    } else {
        table.style.display = "none";
        searchMsg.style.display = "";
    }
}

function buscarDescripcion() {
// Declare variables
    let input, inputLength, filter, table, tr, td, i, txtValue, searchMsg, band;
    input = document.getElementById("searchInput");
    searchMsg = document.getElementById("searchMsg");
    inputLength = input.value.length;
    table = document.getElementById("my-table2");
    if (inputLength > 0) {
        filter = input.value.toUpperCase();
        tr = table.getElementsByTagName("tr");
        band = false;
        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[2];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                    band = true;
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
        if (band) {
            searchMsg.style.display = "none";
            table.style.display = "";
        } else {
            searchMsg.style.display = "";
            table.style.display = "none";
        }
    } else {
        table.style.display = "none";
        searchMsg.style.display = "";
    }
}

function realizarVenta() {
    let tbody = document.getElementById("products-list").rows.length;
    if (tbody > 0) {
        let detallesVenta = escribirJSON();
        enviarVenta(detallesVenta);
    } else {
        showToast("No se han agregado productos en el carrito.");
    }
    
}

function escribirJSON() {

// Obtén la tabla y las filas de la tabla
    const table = document.getElementById('my-table');
    const rows = table.getElementsByTagName('tr');
    // Crea un array para almacenar los productos
    const products = [];
    // Recorre las filas de la tabla (empezando desde 1 para omitir la fila de encabezado)
    for (let i = 1; i < rows.length; i++) {
        const row = rows[i];
        const cells = row.getElementsByTagName('td');
        // Obtiene los valores de las celdas
        const item = cells[0].textContent;
        const id = cells[1].textContent;
        const codigo = cells[2].textContent;
        const nombre = cells[3].textContent;
        const precio = cells[4].textContent;
        const cantidad = cells[5].querySelector('input').value;
        const subtotal = cells[6].querySelector('.subTotal').textContent;
        // Crea un objeto para el producto actual
        const product = {
            item: item,
            id: id,
            codigo: codigo,
            nombre: nombre,
            precio: precio,
            cantidad: cantidad,
            subtotal: subtotal
        };
        // Agrega el producto al array
        products.push(product);
    }

// Obtener el total de la venta
    let totalElemnt = document.getElementById("totalVenta");
    let totalVenta = Number(totalElemnt.innerHTML);
    console.log(totalVenta);
    // Crea un objeto JSON con la información de los productos
    const jsonData = {
        productos: products,
        total: totalVenta
    };
    // Convierte el objeto JSON a una cadena JSON
    const jsonString = JSON.stringify(jsonData);
    console.log(jsonString);
    return jsonString;
}

function enviarVenta(detallesCompra) {
    // Configurar la petición fetch
    fetch('/Farmaciav1/SvRealizarVenta', {
            method: 'POST', // Método HTTP,
            redirect: "manual",
            body: detallesCompra, // Datos que enviarás (el string JSON)
            headers: {
                'Content-Type': 'application/json' // Tipo de contenido
            }
        })
        .then(function(response) {
            // Redirigir a otra página en JavaScript
            window.location.href = '/Farmaciav1/ServletControlador2';
        })
        .catch(function (error) {
            console.error('Error al enviar los datos:', error);
        });
}