// Thông báo hiển thị lỗi
function showError() {
  Swal.fire("Error", "Something wrong.", "error");
}
// Thông báo hiển thị thảnh công
function showSuccess() {
  Swal.fire("Success", "Changes saved.", "success");
}
async function test() {
  try {
    // --------------------------------------------------------------------------- //
    // ------------------------------Delete brand--------------------------------- //
    // --------------------------------------------------------------------------- //
    // Lấy dữ liệu về các hiệu xe
    let brandList = await fetchAllBrands();
    // Gọi hàm thêm hiệu xe
    addToCarsList(brandList);
    // Nhận tín hiệu khi select delete brand thay đổi
    $("#rule-1").on("change", "#brands-list", function () {
      if ($(this).val() === "None") {
        $("#rule-1 #delete-brand").prop("disabled", true);
        $("#rule-1 #delete-brand").addClass("disabled-button");
      } else {
        $("#rule-1 #delete-brand").prop("disabled", false);
        $("#rule-1 #delete-brand").removeClass("disabled-button");
      }
    });
    // Nhận tín hiệu khi btn delete dc nhấn
    $("#rule-1 #delete-brand").on("click", async function () {
      let brandToDelete = $("#rule-1 #brands-list").val();
      let result = await fetchDeleteBrand(brandToDelete);
      if (result) {
        console.log("nice");
        Swal.fire("Success", "Brand deleted.", "success");
        brandList = await fetchAllBrands();
        addToCarsList(brandList);
      } else {
        Swal.fire("Error", "Brand can not be deleted.", "error");
        console.log("Ko thanh cong");
      }
    });

    // --------------------------------------------------------------------------- //
    // ------------------------------Change limit--------------------------------- //
    // --------------------------------------------------------------------------- //
    // Đặt giá trị ban đầu của max repairs = 0
    setDefaultValue4ChangeDayLimit();
    // Hàm dặt giá trị ban đầu cho change day limit
    function setDefaultValue4ChangeDayLimit() {
      $("#rule-1 #max-repairs").val(0);
      // Disable button accept
      $("#rule-1 #change-limits").prop("disabled", true);
      $("#rule-1 #change-limits").addClass("disabled-button");
    }
    //Nhận tín hiệu khi max repair thay đổi
    $("#rule-1").on("change", "#max-repairs", function () {
      if ($(this).val() == 0) {
        $("#rule-1 #change-limits").prop("disabled", true);
        $("#rule-1 #change-limits").addClass("disabled-button");
      } else {
        $("#rule-1 #change-limits").prop("disabled", false);
        $("#rule-1 #change-limits").removeClass("disabled-button");
      }
    });
    // Nhận tín hiệu khi button accept được nhấn
    $("#rule-1 #change-limits").on("click", async function () {
      let newDayLimit = $("#rule-1 #max-repairs").val();
      //   console.log(typeof parseInt(newDayLimit));
      let result = await fetchChangeDayLimit(parseInt(newDayLimit));
      console.log(result);
      if (result) {
        console.log("Thanh cong");
        showSuccess();
        setDefaultValue4ChangeDayLimit();
      } else {
        console.log("Ko thanh cong");
        showError();
      }
    });

    // --------------------------------------------------------------------------- //
    // ------------------------------Table control section------------------------ //
    // --------------------------------------------------------------------------- //
    let allPartsTable = $("#rule-2 #allPartsTable").DataTable();
    let allServiceTable = $("#rule-2 #allServiceTable").DataTable();
    $("#rule-2 #allServiceSection").addClass("hidden");
    // Ấn btn all parts
    $("#rule-2 #allPartsTableBtn").on("click", function () {
      console.log(1);
      // hiện table all parts
      $("#rule-2 #allPartsSection").addClass("show");
      $("#rule-2 #allPartsSection").removeClass("hidden");
      // ẩn table all service
      $("#rule-2 #allServiceSection").removeClass("show");
      $("#rule-2 #allServiceSection").addClass("hidden");
    });
    // Ấn btn all service
    $("#rule-2 #allServiceTableBtn").on("click", function () {
      console.log(2);
      // hiện table all service
      $("#rule-2 #allServiceSection").removeClass("hidden");
      $("#rule-2 #allServiceSection").addClass("show");
      // ẩn table all parts
      $("#rule-2 #allPartsSection").removeClass("show");
      $("#rule-2 #allPartsSection").addClass("hidden");
    });

    // --------------------------------------------------------------------------- //
    // ------------------------------All parts table------------------------------ //
    // --------------------------------------------------------------------------- //

    // Lấy ra toàn bộ vật tư
    let allParts = await fetchAllParts();

    // Hiển thị bảng vật tư
    displayPartTable(allParts);

    // Nhận tín hiệu khi nhấn vào remove
    $("#rule-2 #allPartsSection table tbody").on("click", "span", function () {
      // Lấy ra tên vật tư
      let partName = $(this).data("name");
      $("#confirmDeletedPartModal").modal("show");
      $("#confirmDeletedPartModal .modal-footer #confirmDeletePartButton").on(
        "click",
        async function () {
          try {
            console.log(partName);
            let result = await fetchDeletePart(partName);
            if (result) {
              console.log("Xoa thanh cong part", partName);
              // Lấy ra toàn bộ vật tư
              allParts = await fetchAllParts();
              // Hiển thị bảng vật tư
              displayPartTable(allParts);
              $("#confirmDeletedPartModal").modal("hide");
              // Gỡ sự kiện click hiện tại
              $(this).off("click");
              Swal.fire("Success", "Part deleted.", "success");
            } else {
              console.log("False");
              showError();
            }
          } catch (error) {}
        }
      );
    });
    // Nhận tín hiệu khi nhấn add
    $("#rule-2 #allPartsSection .btn#addPart").on("click", function () {
      function resetForm() {
        // Reset name, price, amount
        $("#rule-2 #allPartsSection #formAddPart .card-body #partName").val("");
        $("#rule-2 #allPartsSection #formAddPart .card-body #price").val("");
        $(
          "#rule-2 #allPartsSection #formAddPart .card-body #number_initialized"
        ).val("");
      }
      // Hiển thị form add part
      $("#rule-2 #allPartsSection #formAddPart").removeClass("hidden");
      $("#rule-2 #allPartsSection #formAddPart").addClass("show");

      function closeForm() {
        $("#rule-2 #allPartsSection #formAddPart").addClass("hidden");
        $("#rule-2 #allPartsSection #formAddPart").removeClass("show");
        resetForm();
      }
      /// Tương tác
      // Ẩn
      $("#rule-2 #allPartsSection #formAddPart #closeFormAddPart").on(
        "click",
        closeForm
      );
      // Thêm
      $("#rule-2 #allPartsSection #formAddPart .card-body form").on(
        "submit",
        async function (event) {
          try {
            event.preventDefault();
            $("#rule-2 #allPartsSection #formAddPart .card-body form").off(
              "submit "
            );
            let newPart = {
              partName: `${$(
                "#rule-2 #allPartsSection #formAddPart .card-body #partName"
              ).val()}`,
              price: parseFloat(
                $(
                  "#rule-2 #allPartsSection #formAddPart .card-body #price"
                ).val()
              ),
            };

            let newPartInitializedAmount = parseInt(
              $(
                "#rule-2 #allPartsSection #formAddPart .card-body #number_initialized"
              ).val()
            );

            let result = await fetchAddPart(newPart, newPartInitializedAmount);
            if (result) {
              Swal.fire(
                "Success",
                `Successfully added ${newPart.partName}.`,
                "success"
              );
              closeForm();
              // Lấy ra toàn bộ vật tư
              allParts = await fetchAllParts();
              // Hiển thị bảng vật tư
              displayPartTable(allParts);
            } else {
              console.log("Ko thanh cong");
              Swal.fire("Error", "Part is already added.", "error");
              closeForm();
            }
          } catch (error) {
            console.error(error);
            closeForm();
          }
        }
      );
    });
    // --------------------------------------------------------------------------- //
    // ------------------------------All Service table---------------------------- //
    // --------------------------------------------------------------------------- //

    // Lấy ra toàn bộ dịch vụ
    let allService = await fetchAllService();

    // Hiển thị bảng tiền công
    displayServiceTable(allService);

    // Nhận tín hiệu khi nhấn vào remove
    $("#rule-2 #allServiceSection table tbody").on(
      "click",
      "span",
      function () {
        // Lấy ra tên service
        let serviceName = $(this).data("name");
        $("#confirmDeletedServiceModal").modal("show");
        $(
          "#confirmDeletedServiceModal .modal-footer #confirmDeleteServiceButton"
        ).on("click", async function () {
          try {
            let result = await fetchDeleteService(serviceName);
            if (result) {
              console.log("Xoa thanh cong service", serviceName);
              // Lấy ra toàn bộ vật tư
              allService = await fetchAllService();
              // Hiển thị bảng vật tư
              displayServiceTable(allService);
              $("#confirmDeletedServiceModal").modal("hide");
              // Gỡ hành động click hiện tại
              $(this).off("click");
              Swal.fire("Success", "Service deleted.", "success");
            } else {
              console.log("False");
              showError();
            }
          } catch (error) {
            showError();
          }
        });
      }
    );

    // Nhận tín hiệu khi nhấn add
    $("#rule-2 #allServiceSection .btn#addService").on("click", function () {
      function resetForm() {
        // Reset name, cost
        $(
          "#rule-2 #allServiceSection #formAddService .card-body #serviceName"
        ).val("");
        $(
          "#rule-2 #allServiceSection #formAddService .card-body #serviceCost"
        ).val("");
      }
      // Hiển thị form add service
      $("#rule-2 #allServiceSection #formAddService").removeClass("hidden");
      $("#rule-2 #allServiceSection #formAddService").addClass("show");

      function closeForm() {
        $("#rule-2 #allServiceSection #formAddService").addClass("hidden");
        $("#rule-2 #allServiceSection #formAddService").removeClass("show");
        resetForm();
      }
      /// Tương tác
      // Ẩn
      $("#rule-2 #allServiceSection #formAddService #closeFormAddService").on(
        "click",
        closeForm
      );

      // Thêm
      $("#rule-2 #allServiceSection #formAddService .card-body form").on(
        "submit",
        async function (event) {
          try {
            event.preventDefault();
            // Gỡ những submit trước đó
            $("#rule-2 #allServiceSection #formAddService .card-body form").off(
              "submit "
            );
            let newService = {
              serviceName: `${$(
                "#rule-2 #allServiceSection #formAddService .card-body #serviceName"
              ).val()}`,
              serviceCost: parseFloat(
                $(
                  "#rule-2 #allServiceSection #formAddService .card-body #serviceCost"
                ).val()
              ),
            };

            let result = await fetchAddService(newService);
            if (result) {
              Swal.fire(
                "Success",
                `Successfully added ${newService.serviceName}.`,
                "success"
              );
              closeForm();
              // Lấy ra toàn bộ service
              allService = await fetchAllService();
              // Hiển thị bảng dịch vụ
              displayServiceTable(allService);
            } else {
              console.log("Ko thanh cong");
              Swal.fire("Error", "Service is already added.", "error");
              closeForm();
            }
          } catch (error) {
            console.error(error);
            closeForm();
          }
        }
      );
    });
    // --------------------------------------------------------------------------- //
    // ------------------------------Assist function------------------------------ //
    // --------------------------------------------------------------------------- //
    // Hàm thêm hiệu xe vào trong list
    function addToCarsList(data) {
      let brandsRemove = $("#rule-1 #brands-list");
      brandsRemove.empty();
      // Vo hiệu button delete
      brandsRemove.append(`<option selected>None</option>`);
      $("#rule-1 #delete-brand").prop("disabled", true);
      $("#rule-1 #delete-brand").addClass("disabled-button");
      for (let i = 0; i < data.length; i++) {
        let option = `<option>${data[i].brandName}</option>`;
        brandsRemove.append(option);
      }
    }
    // Hàm hiển thị parts ra bảng
    function displayPartTable(allParts) {
      allPartsTable.clear().draw();
      // console.log(allParts);

      for (let i = 0; i < allParts.length; i++) {
        allPartsTable.row
          .add([
            `${allParts[i]["partName"]}`,
            `${allParts[i]["price"]}`,
            `<div class="data-span">
                <span data-name="${allParts[i]["partName"]}" class="material-symbols-outlined delete-button" style="cursor: pointer; color: red;" title=
                "click to remove">delete</span>
            </div>`,
          ])
          .draw();
      }
    }
    // Hàm hiển thị service ra bảng
    function displayServiceTable(allService) {
      allServiceTable.clear().draw();

      for (let i = 0; i < allService.length; i++) {
        allServiceTable.row
          .add([
            `${allService[i]["serviceName"]}`,
            `${allService[i]["serviceCost"]}`,
            `<div class="data-span">
                <span data-name="${allService[i]["serviceName"]}" class="material-symbols-outlined delete-button" style="cursor: pointer; color: red;" title=
                "click to remove">delete</span>
            </div>`,
          ])
          .draw();
      }
    }
    // 1. Hàm fetch về tên hiệu xe
    async function fetchAllBrands() {
      try {
        const data = $.ajax({
          url: "/get-all-brands",
          method: "get",
        });
        return data;
      } catch (error) {
        console.error(error);
      }
    }
    // 2. Hàm gửi hiệu xe càn xoá về server
    async function fetchDeleteBrand(data) {
      try {
        const result = $.ajax({
          url: `/delete-brand?brand_name=${data}`,
          method: "DELETE",
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
    // 3. Hàm gửi day limits về server
    async function fetchChangeDayLimit(data) {
      try {
        const result = $.ajax({
          url: `/change-day-limit?day_limit=${data}`,
          method: "POST",
        });
        return result;
      } catch (error) {
        consolo.error(error);
      }
    }
    // 4. Hàm fetch toàn bộ vật tư
    async function fetchAllParts() {
      try {
        const result = $.ajax({
          url: "/get-all-parts",
          method: "GET",
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
    // 5. Hàm fetch toàn bộ service
    async function fetchAllService() {
      try {
        const result = $.ajax({
          url: "/get-all-services",
          method: "GET",
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
    // 6. Hàm fetch để xoá part
    async function fetchDeletePart(partName) {
      try {
        const result = $.ajax({
          url: `/delete-part?part_name=${partName}`,
          method: "DELETE",
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
    // 7. Hàm fetch để xoá service
    async function fetchDeleteService(serviceName) {
      try {
        const result = $.ajax({
          url: `/delete-service?service_name=${serviceName}`,
          method: "DELETE",
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
    // 8. Hàm fetch để post vật tư
    async function fetchAddPart(newPart, amount) {
      try {
        const result = $.ajax({
          url: `/add-part?number_initialized=${amount}`,
          method: "POST",
          contentType: "application/json",
          data: JSON.stringify(newPart),
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
    // 9. Hàm fetch để post service
    async function fetchAddService(newService) {
      try {
        const result = $.ajax({
          url: "/add-service",
          method: "POST",
          contentType: "application/json",
          data: JSON.stringify(newService),
        });
        return result;
      } catch (error) {
        console.error(error);
      }
    }
  } catch (error) {
    console.error(error);
  }
}
test();
