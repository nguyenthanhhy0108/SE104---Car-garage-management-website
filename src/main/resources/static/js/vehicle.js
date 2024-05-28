var mainData, equipList, serviceList;
// Menu handler
document.addEventListener("DOMContentLoaded", function (event) {
  const showNavbar = (toggleId, navId, bodyId, headerId) => {
    const toggle = document.getElementById(toggleId),
      nav = document.getElementById(navId),
      bodypd = document.getElementById(bodyId),
      headerpd = document.getElementById(headerId),
      menuOpen = document.getElementById("menu-open");
    let menuClose;
    menuClose = document.getElementById("menu-close");

    // Validate that all variables exist
    if (toggle && nav && bodypd && headerpd && menuOpen && menuClose) {
      toggle.addEventListener("click", () => {
        // show navbar
        nav.classList.toggle("show-nav");

        // change icon
        if (menuOpen.classList.contains("show")) {
          menuOpen.classList.remove("show");
          menuOpen.classList.add("hidden");
          menuClose.classList.remove("hidden");
          menuClose.classList.add("show");
        } else {
          menuOpen.classList.remove("hidden");
          menuOpen.classList.add("show");
          menuClose.classList.remove("show");
          menuClose.classList.add("hidden");
        }

        // add padding to body
        bodypd.classList.toggle("body-pd");

        // add padding to header
        headerpd.classList.toggle("body-pd");
      });
    }
  };
  showNavbar("header-toggle", "nav-bar", "body-pd", "header");

  const linkColor = document.querySelectorAll(".nav_link");

  function colorLink() {
    if (linkColor) {
      linkColor.forEach((l) => l.classList.remove("active"));
      this.classList.add("active");
    }
  }

  linkColor.forEach((l) => l.addEventListener("click", colorLink));

  // click event Menu => change html direction
  // $("#orderMenu").click(function () {
  //   window.location.href = 'home.html';
  // });
  //
  // $("#vehicleMenu").click(function () {
  //   window.location.href = 'vehicle.html';
  // });
});

//---build table---//
function addRow(data) {
  let numOrder = data.Cars.length;

  let row = `
      <tr scope="row" class="data-row-${data.ID}" data-id=${data.ID}>
        <td rowspan="${numOrder} id=data-name-${data["Name"]} data-toggle="tooltip">${data["Name"]}</td>
        <td id=data-order-brand-${data.Cars[0]["debt"]} data-toggle="tooltip">${data.Cars[0]["debt"]}</td>
        <td id=data-order-brand-${data.Cars[0]["brand"]} data-toggle="tooltip">${data.Cars[0]["brand"]}</td>
        <td id=data-order-license-${data.Cars[0]["license-number"]} data-toggle="tooltip">${data.Cars[0]["license-number"]}</td>
      </tr>`;

  $("#myTable").append(row);

  for (let i = 1; i < numOrder; i++) {
    let subRow = `
        <tr>
          <td id=data-order-brand-${data.Cars[i]["debt"]} data-toggle="tooltip">${data.Cars[i]["debt"]}</td>
          <td id=data-order-brand-${data.Cars[i]["brand"]} data-toggle="tooltip">${data.Cars[i]["brand"]}</td>
          <td id=data-order-license-${data.Cars[i]["license-number"]} data-toggle="tooltip">${data.Cars[i]["license-number"]}</td>
          </tr>`;
    $("#myTable").append(subRow);
  }
}
function buildTable(data) {
  $("#myTable").html("");
  console.log("build table called");
  for (let i = 0; i < data.length; i++) {
    addRow(data[i]);
  }
}
// Fetch data function
async function fetchData() {
  try {
    const test = $.ajax({
      url: "http://localhost:3000/form2",
      method: "get",
      dataType: "json",
    });
    return test;
  } catch (error) {
    console.error(error);
  }
}

// Build table after load page
let dataTable;
$(async function () {
  try {
    // Fetch data
    dataTable = await fetchData();
    console.log(dataTable);
    // Build table
    buildTable(dataTable);
  } catch (error) {
    console.error(error);
  }
});
// Handler for searching and sorting
function handleChanging() {
  // Search
  $("#search-input").on("keyup", function () {
    var value = $(this).val();
    console.log("Value", value);
    // get Table Data from database
    var filteredData = searchTable(value, dataTable);
    buildTable(filteredData);
  });
  function searchTable(value, data) {
    var filteredData = [];
    for (var i = 0; i < data.length; i++) {
      value = value.toLowerCase();
      var name = data[i]["Name"].toLowerCase();
      if (name.includes(value)) {
        console.log("index of data: ", i);
        filteredData.push(data[i]);
      }
    }
    return filteredData;
  }
  // Sort
  $("th").on("click", function () {
    let column = $(this).data("column");
    let order = $(this).data("order");
    let text = $(this).html();
    if (order !== undefined) {
      text = text.substring(0, text.length - 1);
      if (order == "desc") {
        $(this).data("order", "asc");
        dataTable = dataTable.sort((a, b) => (a[column] > b[column] ? 1 : -1));
        text += "&#9650";
      } else if (order == "asc") {
        $(this).data("order", "desc");
        dataTable = dataTable.sort((a, b) => (a[column] < b[column] ? 1 : -1));
        text += "&#9660";
      }
    } else text = text;
    $(this).html(text);
    buildTable(dataTable);
  });
}
handleChanging();
