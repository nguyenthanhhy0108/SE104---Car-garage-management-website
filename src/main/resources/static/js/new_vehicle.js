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
    // Lấy ra số entry
    let entries = parseInt($("#number-entry").val());

    // Page hiển thị khi vừa vào trang là 1
    let cur_page = 1;

    // Khoi tao mang chua toan bo du lieu
    let dataTable = await fetchData();
    // Mảng thật sự sử dụng
    let dataInUse = dataTable;

    // Hiển thị thanh chuyển trang
    setupPagination();

    //  Hiển thị bảng
    displayTable(cur_page, entries, dataInUse);

    // Khi người dùng nhấn chuyển trang
    $("#pagination").on("click", "button", function () {
      console.log("successj");
      cur_page = $(this).html;
      goToPage($(this).html());
    });

    // Nhận tín hiệu khi có sự thay đổi về số phần tử của một trang
    $("#number-entry").on("change", function () {
      entries = parseInt($("#number-entry").val());
      cur_page = 1;
      setupPagination();
      displayTable(cur_page, entries, dataInUse);
    });

    // Xử lý search
    $("#search-input").on("keyup", function () {
      var value = $(this).val();
      console.log("Value", value);
      // get Table Data from database
      var filteredData = searchTable(value, dataTable);
      dataInUse = filteredData;
      // Sửa lại số trang
      setupPagination();
      cur_page = 1;
      displayTable(cur_page, entries, dataInUse);
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

    // Hàm chuyển trang
    function goToPage(page) {
      cur_page = page;
      displayTable(cur_page, entries, dataInUse);
    }
    // Hàm hiển thị bảng theo trang
    function displayTable(page, entry, data) {
      const tableBody = $("#myTable");
      tableBody.empty();

      const start = (page - 1) * entry;
      const end = entry + start;
      console.log(start, end);
      const paginatedData = data.slice(start, end);
      for (let i = 0; i < paginatedData.length; i++) {
        // console.log(paginatedData.length);
        // console.log(paginatedData[i]);
        let numOrder = paginatedData[i].cars.length;
        // console.log(numOrder);
        let row = `<tr scope="row" class="data-row-${paginatedData[i].id}" data-id="${paginatedData[i].id}">
                      <td rowspan="${numOrder}" id="data-name-${paginatedData[i]["id"]}" >${paginatedData[i]["id"]}</td>
                      <td rowspan="${numOrder}" id="data-name-${paginatedData[i]["name"]}" >${paginatedData[i]["name"]}</td>
                      <td id="data-order-brand-${paginatedData[i].cars[0]["brand"]}" >${paginatedData[i].cars[0]["brand"]}</td>
                      <td id="data-order-license-${paginatedData[i].cars[0]["licenseNumber"]}" >${paginatedData[i].cars[0]["licenseNumber"]}</td>
                      <td id="data-order-debt-${paginatedData[i].cars[0]["debt"]}" >${paginatedData[i].cars[0]["debt"]}</td>
                    </tr>`;

        tableBody.append(row);

        for (let j = 1; j < numOrder; j++) {
          let subRow = `<tr>
                          <td id="data-order-brand-${paginatedData[i].cars[j]["brand"]}" >${paginatedData[i].cars[j]["brand"]}</td>
                          <td id="data-order-license-${paginatedData[i].cars[j]["licenseNumber"]}" >${paginatedData[i].cars[j]["licenseNumber"]}</td>
                          <td id="data-order-debt-${paginatedData[i].cars[j]["debt"]}" >${paginatedData[i].cars[j]["debt"]}</td>
                        </tr>`;
          tableBody.append(subRow);
        }
      }
    }
    // Hàm hiển thị thanh chuyển trang
    function setupPagination() {
      const totalPage = Math.ceil(dataInUse.length / entries);
      const pagination = $("#pagination");

      pagination.empty();
      122;

      for (let i = 1; i <= totalPage; i++) {
        pagination.append(`<button>${i}</button>`);
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
