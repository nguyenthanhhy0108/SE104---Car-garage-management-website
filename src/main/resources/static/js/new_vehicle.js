// Form 2

var mainData, equipList, serviceList;
// Xử lý navbar
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
});
// Tạo table
fetch_build();
async function fetch_build() {
  try {
    // Khoi tao mang chua toan bo du lieu
    let dataTable = await fetchData();
    // Mảng thật sự sử dụng
    let dataInUse = dataTable;

    //  Hiển thị bảng
    displayTable(dataInUse);

    // Xử lý search
    $("#search-input").on("keyup", function () {
      var value = $(this).val();
      console.log("Value", value);
      // get Table Data from database
      var filteredData = searchTable(value, dataTable);
      // Sửa lại dataInUse
      dataInUse = filteredData;
      // Hiển thị bảng
      displayTable(dataInUse);
    });
    function searchTable(value, data) {
      var filteredData = [];
      for (var i = 0; i < data.length; i++) {
        value = value.toLowerCase();
        var name = data[i]["name"].toLowerCase();
        if (name.includes(value)) {
          console.log("index of data: ", i);
          filteredData.push(data[i]);
        }
      }
      return filteredData;
    }

    // Hàm hiển thị bảng theo trang
    function displayTable(data) {
      const tableBody = $("#myTable");
      tableBody.empty();

      for (let i = 0; i < data.length; i++) {
        // Đếm số lượng xe
        let numOrder = data[i].cars.length;
        // console.log(numOrder);
        if (numOrder != 0) {
          let row = `<tr scope="row" class="data-row-${data[i].id}" data-id="${data[i].id}">
          <td rowspan="${numOrder}" id="data-name-${data[i]["id"]}" >${data[i]["id"]}</td>
          <td rowspan="${numOrder}" id="data-name-${data[i]["name"]}" >${data[i]["name"]}</td>
          <td id="data-order-brand-${data[i].cars[0]["brand"]}" >${data[i].cars[0]["brand"]}</td>
          <td id="data-order-license-${data[i].cars[0]["licenseNumber"]}" >${data[i].cars[0]["licenseNumber"]}</td>
          <td id="data-order-debt-${data[i].cars[0]["debt"]}" >${data[i].cars[0]["debt"]}</td>
          </tr>`;

          tableBody.append(row);

          for (let j = 1; j < numOrder; j++) {
            let subRow = `<tr>
            <td id="data-order-brand-${data[i].cars[j]["brand"]}" >${data[i].cars[j]["brand"]}</td>
            <td id="data-order-license-${data[i].cars[j]["licenseNumber"]}" >${data[i].cars[j]["licenseNumber"]}</td>
            <td id="data-order-debt-${data[i].cars[j]["debt"]}" >${data[i].cars[j]["debt"]}</td>
            </tr>`;
            tableBody.append(subRow);
          }
        }
      }
    }

    // Hàm lấy data
    async function fetchData() {
      try {
        const test = $.ajax({
          url: "/get-all-vehicles",
          // url: "http://localhost:3000/form2",
          dataType: "json",
          method: "get",
        });
        console.log(test);
        return test;
      } catch (error) {
        console.error(error);
      }
    }
  } catch (error) {
    console.error(error);
  }
}
