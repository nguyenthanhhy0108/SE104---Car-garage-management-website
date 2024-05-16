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
  }
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

async function deleteForm1(vehicleLicensePlate) {
  try {
    const mainData = await $.ajax({
      url: `/delete-row-form1?license_plate=${vehicleLicensePlate}`,
      method: 'DELETE',
      dataType: 'json'
    });
    if (mainData.success == false) {
      popupDialog(
          "Error",
      "You must delete all receipts first !!!"
      )
    }
    return mainData;
  } catch (error) {
    alert(error);
  }
}

async function callAPIChangeForm1(oldPhoneNumber, newData, type) {
  let name = null;
  let phoneNumber = null;
  let email = null;
  let address = null;

  let dataObject = {
    name: name,
    phoneNumber: phoneNumber,
    email: email,
    address: address,
    oldPhoneNumber: oldPhoneNumber
  };

  if (type === "email") {
    dataObject.email = newData;
  }
  if (type === "phone") {
    dataObject.phoneNumber = newData;
  }
  if (type === "name") {
    dataObject.name = newData;
  }
  if (type === "address") {
    dataObject.address = newData;
  }

  $.ajax({
    url: '/change-form1',
    type: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(dataObject),
    success: function(response) {
      // alert(response.status);
    },
    error: function(xhr, status, error) {
      alert(error)
    }
  });
}

async function fetchData() {
  try {
    mainData = await $.ajax({
      url: "/get-all-records",
      method: "GET",
      dataType: "json",
    });
    equipList = await $.ajax({
      url: "/get-all-parts",
      method: "GET",
      dataType: "json",
    });
    serviceList = await $.ajax({
      url: "/get-all-services",
      method: "GET",
      dataType: "json",
    });


    // console.log(mainData);
    checkLicensePlate()
    // console.log("maindata, equip, service", mainData, equipList, serviceList)
    // Sort table function
    $("th").on("click", function () {
      var column = $(this).data("column");
      var order = $(this).data("order");
      var text = $(this).html();
      if (order !== undefined) {
        text = text.substring(0, text.length - 1);
        if (order === "desc") {
          $(this).data("order", "asc");
          mainData = mainData.sort((a, b) => (a[column] > b[column] ? 1 : -1));
          text += "&#9660";
        } else if (order === "asc") {
          $(this).data("order", "desc");
          mainData = mainData.sort((a, b) => (a[column] < b[column] ? 1 : -1));
          text += "&#9650";
        }
      } else text = text;
      $(this).html(text);
      buildTable(mainData);
    });

    document.getElementById("addDate").addEventListener("input", function () {
      if (this.value.length > 10) {
        this.value = this.value.slice(0, 10);
      }

      var currentDay = new Date();
      currentDay.setHours(0, 0, 0, 0);

      var typedDay = new Date(this.value);
      typedDay.setHours(0, 0, 0, 0);

      if (typedDay < currentDay) {
        popupDialog(
            "Error",
            "Your typed day must greater than current day !!!"
        );
        this.value = "";
      }
    });

    // Search function
    $("#search-input").on("keyup", function () {
      var value = $(this).val();
      console.log("Value", value);
      //// get Table Data from database
      // tableData = getDataFromServer()
      // var data = searchTable(value, tableData)
      var data = searchTable(value, mainData);
      buildTable(data);
    });
    function searchTable(value, data) {
      var filteredData = [];
      for (var i = 0; i < data.length; i++) {
        value = value.toLowerCase();
        var name = data[i].Name.toLowerCase();
        if (name.includes(value)) {
          console.log("index of data: ", i);
          filteredData.push(data[i]);
        }
      }
      return filteredData;
    }
    function getDataFromServer() {
      $.ajax({
        method: "GET",
        url: "/data", // Thay thế đường dẫn với URL của dữ liệu JSON trên máy chủ của bạn
        dataType: "json",
        success: function (response) {
          // Xử lý dữ liệu JSON được nhận từ máy chủ ở đây
          console.log("Data from server:", response);
          return response;
        },
        error: function (xhr, status, error) {
          console.error("Error fetching data:", error);
        },
      });
    }

    // Build Table function
    function buttonEvent() {
      // Gỡ bỏ sự kiện click trước khi gán lại
      $('.details-button[data-vehicle-license-number]').off('click').on('click', function() {
        detailsData.call(this);
      });

      $('.delete-button[data-vehicle-license-number]').off('click').on('click', deleteData);

      $('.confirm-button[data-vehicle-license-number]').off('click').on('click', function() {
        confirmDeletion.call(this);
      });

      // $('.cancel-button[data-vehicle-license-number]').off('click').on('click', cancelDeletion);
      $('[id^="data-"]').off('click').on('click', function(){
        editDataForm1.call(this);
      })
    }
    function addRow(data){
      var numOrders = data.Cars.length
      // console.log(numOrders)
      // console.log(`${data.ID}`)
      var row = `<tr scope="row" class="data-row-${data.ID}" data-id=${data.ID}>
            <td rowspan="${numOrders}" id="data-name-${data.ID}" data-toggle="tooltip" title="click for edit">${data.Name}</td>
            <td rowspan="${numOrders}" id="data-phone-${data.ID}" data-toggle="tooltip" title="click for edit">${data.Phone}</td>
            <td rowspan="${numOrders}" id="data-email-${data.ID}" data-toggle="tooltip" title="click for edit">${data.Email}</td>
            <td rowspan="${numOrders}" id="data-address-${data.ID}" data-toggle="tooltip" title="click for edit">${data.Address}</td>
            <td id=data-order-brand-${data.Cars[0].licenseNumber}>${data.Cars[0].brand}</td>
            <td id=data-order-num-${data.Cars[0].licenseNumber}>${data.Cars[0].licenseNumber}</td>
            <td>
                 <div class = "row col-sm-2 show" id = "change-del-btn-${data.Cars[0].licenseNumber}">
                     <span style="cursor: pointer; color:green"  class="material-symbols-outlined details-button" data-id"="${data.ID}" data-vehicle-license-number="${data.Cars[0].licenseNumber}" data-toggle="tooltip" title="click for details">info</span>
                     <span style="cursor: pointer; color:red" class="material-symbols-outlined delete-button" data-id="${data.ID}" data-vehicle-license-number="${data.Cars[0].licenseNumber}" data-toggle="tooltip" title="click for delete">delete</span>
                 </div>
              </td>`;

      $('#myTable').append(row)
      buttonEvent(data.Cars[0].licenseNumber, data.ID)

      row =''
      for (var i = 1; i < numOrders; i++) {
        var subRow = `
            <tr>
                <td id=data-order-brand-${data.Cars[i].licenseNumber}>${data.Cars[i].brand}</td>
                <td id=data-order-num-${data.Cars[i].licenseNumber}>${data.Cars[i].licenseNumber}</td>
                <td>
                    <div class = "row col-sm-2 show" id = "change-del-btn-${data.Cars[i].licenseNumber}">
                        <span style="cursor: pointer; color:green"  class="material-symbols-outlined details-button" data-id"="${data.ID}" data-vehicle-license-number="${data.Cars[i].licenseNumber}" data-toggle="tooltip" title="click for details">info</span>
                        <span style="cursor: pointer; color:red" class="material-symbols-outlined delete-button" data-id="${data.ID}" data-vehicle-license-number="${data.Cars[i].licenseNumber}" data-toggle="tooltip" title="click for delete">delete</span>
                      </div> 
                </td>
            </tr>`
        $('#myTable').append(subRow)
        buttonEvent(data.Cars[i].licenseNumber, data.ID)
      }
    }
    function buildTable(data) {
      // console.log("build table called")
      var table = document.getElementById('myTable')
      table.innerHTML = ''
      for (var i in data) {
        // console.log(data[i])
        addRow(data[i])
      }
    }
    buildTable(mainData);

    // ----------------- edit form1 function
    function getID(id){
      var idArray = id.split("-");
      return idArray[idArray.length - 1];
    }
    function getPhoneNumber(dataID) {
      var phoneNumber = "";

      $('table tr').each(function() {
        var phoneCell = $(this).find(`td[id^="data-phone-${dataID}"]`);
        if (phoneCell.length > 0) {
          phoneNumber = phoneCell.text();
          return false;
        }
      });
      return phoneNumber;
    }
    function editDataForm1(){
      var dataID =  $(this).attr('id')
      var rowID = getID(dataID)
      console.log(rowID)
      var oldPhone = getPhoneNumber(rowID)
      console.log(oldPhone)
      if (dataID.includes('data-order-')){
        return ;
      }
      var currentData = $(this).text()
      var inputElement = $('<input type="text" class="form-control">').val(currentData)
      $(this).empty().append(inputElement);
      inputElement.focus();
      var beforeInput = currentData
      // console.log("before", beforeInput)
      var originalElement = $(this);
      inputElement.blur(function() {
        var newData = $(this).val();
        $(this).parent().text(newData);

        if (newData !== currentData) {
          Swal.fire({
            title: 'Changes Confirmation',
            text: 'Are you sure to save these changes?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Save',
            cancelButtonText: 'Cancel'
          }).then((result) => {
            if (result.isConfirmed) {
              // console.log("old Phone, new Data: ", oldPhone, newData)
              let type = null;
              if (dataID.toString().includes("email")) {
                type = "email";
              }
              if (dataID.toString().includes("name")) {
                type = "name";
              }
              if (dataID.toString().includes("phone")) {
                type = "phone";
              }
              if (dataID.toString().includes("address")) {
                type = "address";
              }
              callAPIChangeForm1(oldPhone, newData, type);

              setTimeout(function () {
                Swal.fire('Success', 'Changes saved', 'success');
              }, 3000);

              window.location.reload();

            } else {
              // console.log("after", beforeInput)
              originalElement.text(beforeInput)
            }
          });
        }
      });

      $(`input`).on('keyup', function(event) {
        if (event.keyCode === 13) {
          $(this).blur();
        }
      });

      // call api change data
    }
    //Add data functions
    // ------------------ Form Appear after click add button
    $('#addData').on('click', function () {
      $('#formAdd').addClass('show');
      $('#dataTable').css({
        'opacity': '0.5',
        'pointer-events': 'none'
      });
      var currForm = 1;

      function showForm(formNum) {
        console.log("currform", formNum)
        $(".formAdd").addClass("hidden");
        $(".formAdd").removeClass("show");
        $("#formAdd-" + formNum).removeClass("hidden");
        $("#formAdd-" + formNum).addClass("show");
      }

      $(".moveRight").click(function () {
        if (currForm < $(".formAdd").length) {
          currForm++;
          showForm(currForm);
        }
      });
      $(".moveLeft").click(function () {
        if (currForm > 1) {
          currForm--;
          showForm(currForm);
        }
      });
    });
    function closeForm(idForm, idClose, tableBackground, event) {
      // ---Example use:
      // closeForm('#formAdd_1', '#closeForm_1', '#dataTable', 'click');
      $(idClose).on(event, function () {
        $(idForm).addClass('hidden');
        $(idForm).removeClass('show');
        // $('body').css('overflow', 'auto');
        $(tableBackground).css({
          'opacity': '',
          'pointer-events': ''
        });
      });
    }
    closeForm('#formAdd', '#closeForm_1', '#dataTable', 'click');


    // Delete data fucntion
    // ---------------- Delete data function
    async function deleteData() {
      var vehicleID = $(this).attr('data-vehicle-license-number');

      if (vehicleID) {
        Swal.fire({
          title: 'Delete Confirmation',
          text: 'Are you sure to delete this data?',
          icon: 'question',
          showCancelButton: true,
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel'
        }).then(async (result) => {
          if (result.isConfirmed) {
            if (await confirmDeletion(vehicleID))
              Swal.fire('Success', 'Deleted ', 'success');
            else {
              return;
            }
            setTimeout(function () {
              console.log("Waiting 5 seconds")
              window.location.reload();
            }, 3000);
          } else
            Swal.fire('Cancel', 'Cancel Deletion', 'info')
        });
      } else {
        console.log("vehicleID is undefined or empty");
      }
    }
    async function confirmDeletion(vehicleID) {
      console.log(vehicleID)
      // var vehicleID = $(this).data('vehicle-license-number')
      let response = await deleteForm1(vehicleID);
      // return !!response.success;
      return response.success;
    }
    
    
    // ------------ FORM 2 ---------------
    // Right Table
    function addRowDetails(data) {
      var numDetails = 0
      data = data[0]
      console.log("data",data)
      let numVehicle = 0;
      // console.log("date", data[0]['dates'])
      // Tính tổng số lượng chi tiết
      data.dates.forEach(function(date) {
        numDetails += date.orderDetails.length;
      });

      data.dates.forEach(function(dateData, dateIndex) {
        dateData.orderDetails.forEach(function(detail, detailIndex) {
          console.log(detail)
          var orderNumber = detail["orderNumber"];
          var numDetail = dateData.orderDetails.length
          var row = '';
          // console.log("detailIdx", detail)
          if (detailIndex === 0) {
            row += `<tr>`;
            if (dateIndex === 0)
              row += `<td id="data-order-num-${data.licenseNumber}" rowspan="${numDetails}">${data.licenseNumber}</td>`;
            row += `<td id="data-order-date-${data.dates.length}" rowspan="${numDetail}">${dateData.orderDate}</td>`;
          }
          row += `
                        <td data-order-id="${orderNumber}" id="data-order-equip-${orderNumber}" data-toggle="tooltip" title="click for edit">${detail.part["partName"]}</td>
                        <td data-order-id="${orderNumber}" id="data-order-quantity-${orderNumber}" data-toggle="tooltip" title="click for edit">${detail.quantity}</td>
                        <td data-order-id="${orderNumber}" id="data-order-price-${orderNumber}" data-toggle="tooltip" title="click for edit">${detail.part["price"]}</td>
                        <td data-order-id="${orderNumber}" id="data-order-service-${orderNumber}" data-toggle="tooltip" title="click for edit">${detail.service["serviceName"]}</td>
                        <td data-order-id="${orderNumber}" id="data-order-charge-${orderNumber}" data-toggle="tooltip" title="click for edit">${detail.service["serviceCost"]}</td>
                        <td data-order-id="${orderNumber}" id="data-order-total-${orderNumber}" data-toggle="tooltip" title="click for edit">${detail.total}</td>
                        <td>
                            <span style="cursor: pointer; color:red" class="material-symbols-outlined delete-button" data-order="${detail.OrderNumber}" data-vehicle-license-number="${data.licenseNumber}" data-toggle="tooltip" title="click for delete">delete</span>
                        </td>
                    </tr>`;

          $('#detailsTable').append(row);
          $('.delete-button[data-order-]').off('click').on('click', function(){
            deleteDetails.call(this)
          })
          $('[id^="data-order-"]').off('click').on('click', function(){
            if ($(this).hasClass('sub-input'))
              return;
            editDataDetails.call(this);
          })
        })
      });
    }
    async function getAllReceipts() {
      try {
        const response = await $.ajax({
          url: "/get-all-receipt",
          method: "GET",
          dataType: "json",
        });
        console.log("all receipts: ", response)
        return response;
      } catch (error) {
        console.error("Error fetching receipts:", error);
        throw error;
      }
    }
    function getDate(getDay= false){
      var date = new Date()
      var daysofWeek =  ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      var today = daysofWeek[date.getDay()]
      var month = date.getMonth()
      var day = date.getDate()
      var year = date.getFullYear()
      if (getDay) {
        return today + ', ' + day + ' / ' + month + ' / ' + year
      }
      return day + '/' + month + '/' + year
    }
    async function detailsData() {
      $('#detailsTable').empty()
      var vehicleID = $(this).data('vehicle-license-number')
      console.log(vehicleID);

      $.ajax({
        url: '/get-license-number?license_number=' + vehicleID,
        type: 'GET',
        success: function(response) {
          $('#response').html(response)
        },
        error: function(xhr, status, error) {
          console.error('Error:', error);
        }
      });

      var date = getDate(true)

      var allReceipts = await getAllReceipts()
      var thisReceipts = allReceipts.filter(receipt => receipt['licenseNumber'] === vehicleID)
      console.log("this receipt: ", thisReceipts)
      // console.log("all receipts: ", allReceipts)
      // var idVehicle = allReceipts['licenseNumber']
      closeForm('#formDetails', '#closeForm', '#dataTable', 'click');
      $('#formDetails').addClass('show').removeClass('hidden');
      $('#dataTable').css({
        'opacity': '0.5',
        'pointer-events': 'none'
      });

      $('#addDate').text(date)
      if (allReceipts.length === 0) return;
      addEquipServiceSelection()
      form2Interaction()
      addRowDetails(thisReceipts)
      addOrder(vehicleID)

    }
    function deleteDetails(){
      var orderID = $(this).data('order-id')
      console.log(orderID)
      if (orderID){
        Swal.fire({
          title: 'Delete Confirmation',
          text: 'Are you sure to delete this data?',
          icon: 'question',
          showCancelButton: true,
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel'
        }).then((result) => {
          if (result.isConfirmed) {
            if (confirmDeletionDetails(vehicleID))
              Swal.fire('Success', 'Deleted ', 'success');
            // console.log(vehicleID)
            else
              Swal.fire('Cancel', 'Cancel Deletion', 'info')

          }
        });
      } else {
        console.log("vehicleID is undefined or empty");
      }
    }
    function confirmDeletionDetails(){
      return true
      return false
    }

    function createJSONformat (orderID, partName, price, serviceName, serviceCost, quantity) {
      return {
        "orderNumber":orderID,
        "notes": "aaaa",
        "part": {
          "partName" : partName,
          "price": price
        },
        "quantity": quantity,
        "service": {
          "serviceName":serviceName,
          "serviceCost": serviceCost
        }
      }
    }
    function editDataDetails(data){
      $(this).addClass('sub-input')
      var dataID =  $(this).attr('id')
      if (dataID.includes('num') || dataID.includes('date') || dataID.includes('price') || dataID.includes('charge') || dataID.includes('total'))
        return ;
      var orderID = getID(dataID)
      console.log(dataID, orderID)
      var originalElement = $(this);
      var currentData = $(this).text()
      var beforeInput = currentData
      var inputElement = null
      // console.log("curr", currentData)
      if (dataID.includes('quantity')){
        inputElement = $('<input type="number" class="form-control">').val(currentData)
        $(this).empty().append(inputElement);
        inputElement.on("keyup", function(event) {
          var newData = $(this).val();
          var newdata = newData
          $(this).parent().text(newData);
          if (event.keyCode === 13){
            if (newData !== currentData) {
              Swal.fire({
                title: 'Changes Confirmation',
                text: 'Are you sure to save these changes?',
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Save',
                cancelButtonText: 'Cancel'
              }).then((result) => {
                if (result.isConfirmed) {
                  // console.log("orderID, new Data: ", orderID, newData)
                  Swal.fire('Success', 'Changes saved', 'success');
                  var price = parseFloat($('#data-order-price-' + orderID).text())
                  var charge = parseFloat($('#data-order-charge-' + orderID).text())
                  var quantity = parseFloat(newdata)
                  var newTotal = calculateTotalPrice(quantity, price, charge)
                  var equipName =  $('#data-order-equip-'+orderID).text()
                  var serviceName = $('#data-order-service-'+orderID).text()
                  // console.log(equipName, serviceName)
                  $('#data-order-total-' + orderID).text(newTotal.toString())
                  var returnData = createJSONformat(orderID, equipName ,price, serviceName,charge,quantity)
                  console.log(returnData)
                } else {
                  // console.log("after", beforeInput)
                  originalElement.text(beforeInput)
                }
              });
            }
            originalElement.removeClass('sub-input')
          }
        });

      }
      else if (dataID.includes('equip') || dataID.includes('service')) {
        var selectElement = $('<select id="subSelect" class="form-control">');
        selectElement.append('<option selected> Choose </option>' )

        originalElement.empty().append(selectElement);
        var dataList = dataID.includes('equip') ? equipList : serviceList;
        // console.log(dataList)
        // selectElement.append('<option selected>' + currentData + '</option>');
        dataList.forEach(function (option) {
          if (dataID.includes('equip'))
            selectElement.append('<option>' + option['partName'] + '</option>');
          else selectElement.append('<option>' + option['serviceName'] + '</option>');
        });
        var parent = selectElement.closest('td');
        selectElement.select2({
              dropdownParent: $(parent)
            }
        )
        selectElement.on('select2:select', function () {
          originalElement.removeClass('sub-input')
          var newData = $(this).val(); // Update inputElement with the selected value
          var newdata = newData
          // console.log("newdata", newData)
          originalElement.text(newData);
          selectElement.remove();
          if (newData !== currentData) {
            Swal.fire({
              title: 'Changes Confirmation',
              text: 'Are you sure to save these changes?',
              icon: 'question',
              showCancelButton: true,
              confirmButtonText: 'Save',
              cancelButtonText: 'Cancel'
            }).then((result) => {
              if (result.isConfirmed) {
                if (dataID.includes('equip')) {
                  var textNewdata = getPriceService(newdata, "equip")
                  $('#data-order-price-' + orderID).text(textNewdata)
                }
                else if (dataID.includes('service')) {
                  var textNewdata = getPriceService(newdata, "service")
                  $('#data-order-charge-' + orderID).text(textNewdata)
                }
                var price = parseFloat($('#data-order-price-' + orderID).text())
                var charge = parseFloat($('#data-order-charge-' + orderID).text())
                var quantity = parseFloat($('#data-order-quantity-' + orderID).text())
                var newTotal = calculateTotalPrice(quantity, price, charge)
                var equipName =  $('#data-order-equip-'+orderID).text()
                var serviceName = $('#data-order-service-'+orderID).text()
                // console.log(equipName, serviceName)
                $('#data-order-total-' + orderID).text(newTotal.toString())
                var returnData = createJSONformat(orderID, equipName, price, serviceName, charge, quantity)
                console.log(returnData)
                Swal.fire('Success', 'Changes saved', 'success');
              } else {
                // console.log("after", beforeInput)
                originalElement.text(beforeInput)
              }
            });
          }

        });
      }
      // call api change data
    }

  // Left form interaction
    async function sendtoBackEnd(data) {
      await $.ajax({
        url: "",
        data: JSON.stringify(data),
        method: "POST",
        dataType: "json",
        success: function (response) {
          console.log("sendtoBackEnd success")
        }
      })
    }
    function addEquipServiceSelection(){
      equipList.forEach(function(option) {
        $('#addEquip').append('<option>' + option['partName'] + '</option>');
      });
      $('#addEquip').select2();
      console.log(serviceList)
      serviceList.forEach(function(option){
        $('#addService').append('<option>' + option['serviceName'] + '</option>');
      })
      $('#addService').select2();
    }
    function getPriceService(name, type) {
      console.log(name)
      var price = null
      if (type === 'equip') {
        equipList.forEach(function(eq) {
          if (eq['partName'] === name) {
            price =  eq['price'];
            return false;
          }
        });
      }
      else if (type === 'service'){
        serviceList.forEach(function(eq) {
          if (eq['serviceName'] === name) {
            price =  eq['serviceCost'];
            return false;
          }
        });
      }
      return price
    }
    function form2Interaction(){
      $('#addEquip').on('change', function () {
        var equipVal = $(this).val()
        var equipPrice = getPriceService(equipVal, 'equip')
        // console.log(price)
        $('#price').val(equipPrice);
      })
      $('#addService').on('change', function() {
        var serVal = $(this).val()
        var serPrice = getPriceService(serVal, 'service')
        $('#charge').val(serPrice)
      })
      $('#quantity, #addEquip, #addService').on('input change', function(){
        if ($('#price').val() && $('#charge').val() && $('#quantity').val()){
          var quantity = parseFloat($('#quantity').val())
          var price = parseFloat($('#price').val())
          var charge = parseFloat($('#charge').val())
          $('#totalPrice').val(calculateTotalPrice(quantity, price, charge))
        }


      });
    }
    function calculateTotalPrice(quantity, price, charge){
      var totalPrice = (quantity * price) + charge;
      return totalPrice.toFixed(2);
    }
    function addOrder(idVehicle){
      $('#buttonEquip').on('click', function(event){
        event.preventDefault()
        var newData = {
          'licenseNumber': idVehicle,
          'dates': [
            {
              'orderDate': getDate(),
              'orderDetails': [
                {
                  'part': {
                    'partName': $('#addEquip').val(),
                    'price': $('#price').val()
                  },
                  'quantity': $('#quantity').val(),
                  'service': {
                    'serviceName':$('#addService').val(),
                    'serviceCost': $('#charge').val()
                  },
                  'total': $('#totalPrice').val()
                }
              ]
            }
          ]
        }
        console.log("new", newData)
        sendtoBackEnd(newData)
      })
    }





    function checkLicensePlate() {
    var urlParams = new URLSearchParams(window.location.search);
    var check = urlParams.get("exist");
    if (check) {
      popupDialog("Error", "This car is fixing !!!");
    }
  }
}
catch (error) {
    alert("Error: " + error);
  }
}

fetchData();