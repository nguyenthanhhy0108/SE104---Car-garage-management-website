// Hiện form 5.1, ẩn form 5.2
$("#form-51").addClass("show");
$("#form-52").addClass("hidden");
// Xử lý các button
var currentTable = $("#form-51");
$("#btn-group button").on("click", function () {
  var tar = $(this).data("target");
  console.log(tar);

  currentTable.removeClass("show");
  currentTable.addClass("hidden");
  currentTable = $(tar);

  $(tar).addClass("show");
  $(tar).removeClass("hidden");
});

//
function fetch_build() {
  // Lấy ra ngày tháng
  const date = new Date();
  month = date.getMonth() + 1;
  year = date.getFullYear();
  // Thêm vào input của form 5.1
  let input_val;
  if (month < 10) {
    input_val = year.toString() + "-0" + month.toString();
  } else {
    input_val = year.toString() + "-" + month.toString;
  }
  $("#form-51 input").val(input_val);

  // Tạo bảng 5.1
  let table1 = $("#form-51 table").DataTable();
  build_and_handleForm51(month, year);
  // Xử lý khi tháng bị thay đổi.
  $("#form-51 #monthINPUT").on("change", handleMonthChange51);
  // Funciton tạo bảng
  async function build_and_handleForm51(month, year) {
    try {
      let list_form5_1 = await fetchDataform51(month, year);
      let cardbody = document.querySelectorAll(
        "#form-51 .container-fluid .card-body"
      );
      cardbody[1].innerHTML = list_form5_1.total;
      //   console.log(list_form5_1.list);
      // Tạo table body
      buildTable(list_form5_1.list);
      // Funciton tạo bảng
      function buildTable(data) {
        // console.log(data[1]);
        // console.log(data[0]);
        table1.clear().draw();
        for (let i = 0; i < data.length; i++) {
          //   console.log(data[i]);
          table1.row
            .add([
              `${data[i].brand}`,
              `${data[i].countFix}`,
              `${data[i].value}`,
              `${data[i].rate}`,
            ])
            .draw();
        }
      }
    } catch (error) {
      alert("Error: " + error);
    }
  }
  // Hàm lấy dữ liệu từ server
  async function fetchDataform51(month, year) {
    try {
      console.log(`/get-data-form5?month=${month}&year=${year}`);
      const test = await $.ajax({
        url: `/get-data-form5?month=${month}&year=${year}`,
        method: "GET",
      });
      // console.log("fetchDataform51 called");
      return test;
    } catch (error) {
      console.error(error);
    }
  }

  // Hàm xử lý thay đổi tháng
  function handleMonthChange51() {
    console.log("yes");
    const new_time = $("#form-51 input").val();
    let [yearStr, monthStr] = new_time.split("-");
    // if (monthStr.length > 1) monthStr = monthStr.split("0")[1];
    let month = parseInt(monthStr);
    let year = parseInt(yearStr);

    build_and_handleForm51(month, year);
  }

  // ----- Tạo bảng 5.2
  let table2 = $("#form-52 table").DataTable();
  // Tạo bảng 5.2
  build_Form52();
  // Hàm tạo bảng 5.2
  async function build_Form52() {
    try {
      const list_form5_2 = await fetchDataform52();
      // console.log(list_form5_2.partsForm5List);
      // Tạo bảng
      buildTable(list_form5_2.partsForm5List);
      // Hàm tạo bảng
      function buildTable(data) {
        table2.clear().draw();
        for (let i = 0; i < data.length; i++) {
          //   console.log(data[i]);
          table2.row
            .add([
              `${data[i].partName}`,
              `${data[i].before}`,
              `${data[i].used}`,
              `${data[i].after}`,
              `<div class="data-span">
                        <span data-name="${data[i].partName}" data-amount="${data[i].before}" class="material-symbols-outlined details-button" style="cursor: pointer; color: green;" title="click to change">info</span>
            </div>`,
            ])
            .draw();
        }
      }
    } catch (error) {
      console.log("ERROR", error);
    }
  }
  // Hàm lấy data
  async function fetchDataform52() {
    try {
      const test = await $.ajax({
        url: "/get-parts-form5",
        method: "GET",
      });
      //   console.log("fetchDataform52 called");
      return test;
    } catch (error) {
      console.error(error);
    }
  }
  // Hàm close form đang mở
  function close_form(ele) {
    ele.classList.add("hidden");
    ele.classList.remove("show");
  }
  // Thay đổi data bằng info
  $("#form-52 tbody").on("click", "span", function () {
    console.log("heello");
    let name = $(this).data("name");
    // let number = $(this).data("amount");
    $("#form-52 #formChange .card-body #partName").val(name);
    $("#form-52 #formChange .card-body #numberChange").val("");
    // Đặt tên cố định cho part
    $("#form-52 #formChange .card-body #partName").attr("readonly", true);
    // Hiển thị form
    $("#form-52 #formChange").addClass("show");
    $("#form-52 #formChange").removeClass("hidden");

    // Lấy ra ele close button
    var closeBtn = document.querySelector(
      "#form-52 #formChange span#closeForm"
    );
    // Set event for closeBtn
    closeBtn.addEventListener("click", function () {
      // Gọi hàm close_form() với tham số là form có id là "form-52 #formChange"
      close_form(document.querySelector("#form-52 #formChange"));
    });

    let amount = document.querySelector(
      "#form-52 #formChange #numberChange"
    ).value;

    console.log(amount);
    let form = document.querySelector("#form-52 #formChange");

    // Xoá sự kiện submit cũ
    console.log($(form));
    $(form).off("submit");
    $(form).on("submit", async function (event) {
      event.preventDefault();
      let amount = document.querySelector(
        "#form-52 #formChange #numberChange"
      ).value;
      const result = await fetchAddPart(name, amount.toString());
      close_form(document.querySelector("#form-52 #formChange"));
      build_Form52();
    });
  });
  // AIP cho hành động thêm số lượng
  async function fetchAddPart(name, amount) {
    try {
      console.log(`/add-number-part?part_name=${name}&number=${amount}`);
      const result = await $.ajax({
        url: `/add-number-part?part_name=${name}&number=${amount}`,
        method: "post",
      });
      return result;
    } catch (error) {
      console.alert(error);
    }
  }
}

fetch_build();
