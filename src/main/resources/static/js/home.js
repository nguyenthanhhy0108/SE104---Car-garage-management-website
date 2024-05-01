var test;

async function fetchData() {
  try {
    test = await $.ajax({
      url: "/get-all-records",
      method: "GET",
      dataType: "json",
    });
    console.log(test);
    checkLicensePlate();
    // Sort table function
    $("th").on("click", function () {
      var column = $(this).data("column");
      var order = $(this).data("order");
      var text = $(this).html();
      if (order !== undefined) {
        text = text.substring(0, text.length - 1);
        if (order == "desc") {
          $(this).data("order", "asc");
          test = test.sort((a, b) => (a[column] > b[column] ? 1 : -1));
          text += "&#9660";
        } else if (order == "asc") {
          $(this).data("order", "desc");
          test = test.sort((a, b) => (a[column] < b[column] ? 1 : -1));
          text += "&#9650";
        }
      } else text = text;
      $(this).html(text);
      buildTable(test);
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
      var data = searchTable(value, test);
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
    function buttonEvent(vehicleLicenseNumber, dataID) {
      $(`#details-${vehicleLicenseNumber}`).on('click', function () {
        detailsData.call(this, dataID)
      });
      $(`#delete-${vehicleLicenseNumber}`).on('click', deleteData);
      $(`#cancel-${vehicleLicenseNumber}`).on('click', cancelDeletion);
      $(`#confirm-${vehicleLicenseNumber}`).on('click', function () {
        confirmDeletion.call(this, dataID);
      });
    }

    function addRow(data) {
      var numOrders = data.Car.length
      // console.log(numOrders)
      // console.log(`${data.ID}`)
      var row = `<tr scope="row" class="data-row-${data.ID}" data-id=${data.ID}>
            <td rowspan="${numOrders}" id="data-name-${data.ID}">${data.Name}</td>
            <td rowspan="${numOrders}" id="data-phone-${data.ID}">${data.Phone}</td>
            <td rowspan="${numOrders}" id="data-email-${data.ID}">${data.Email}</td>
            <td rowspan="${numOrders}" id="data-address-${data.ID}">${data.Address}</td>
            <td id=data-order-brand-${data.Car[0]['Vehicle license number']}>${data.Car[0]['Vehicle brand']}</td>
            <td id=data-order-num-${data.Car[0]['Vehicle license number']}>${data.Car[0]['Vehicle license number']}</td>
            <td>
               <div class = "row col-sm-2 show" id = "change-del-btn-${data.Car[0]['Vehicle license number']}">
                   <button class="btn btn-sm btn-info" data-id=${data.Car[0]['Vehicle license number']} id="details-${data.Car[0]['Vehicle license number']}">Details</button>
                   <button class="btn btn-sm btn-danger" data-id=${data.Car[0]['Vehicle license number']} id="delete-${data.Car[0]['Vehicle license number']}">Delete</button>
               </div>
               <div class="row col-sm-2 hidden" id = "cancel-confirm-btn-${data.Car[0]['Vehicle license number']}">
                   <button class="btn btn-sm btn-danger" data-id=${data.Car[0]['Vehicle license number']} id="confirm-${data.Car[0]['Vehicle license number']}">Confirm</button>
                   <button class="btn btn-sm btn-success" data-id=${data.Car[0]['Vehicle license number']} id="cancel-${data.Car[0]['Vehicle license number']}">Cancel</button>
               </div>
            </td>`;

      $('#myTable').append(row)
      buttonEvent(data.Car[0]['Vehicle license number'], data.ID)

      row = ''
      for (var i in data.Car) {
        if (i === 0) continue
        else {
          row += `
                <tr>
                    <td id=data-order-brand-${data.Car[i]['Vehicle license number']}>${data.Car[i]['Vehicle brand']}</td>
                    <td id=data-order-num-${data.Car[i]['Vehicle license number']}>${data.Car[i]['Vehicle license number']}</td>
                    <td>
                        <div class = "row col-sm-2 show" id = "change-del-btn-${data.Car[i]['Vehicle license number']}">
                            <button class="btn btn-sm btn-info" data-id=${data.Car[i]['Vehicle license number']} id="details-${data.Car[i]['Vehicle license number']}">Details</button>
                            <button class="btn btn-sm btn-danger" data-id=${data.Car[i]['Vehicle license number']} id="delete-${data.Car[i]['Vehicle license number']}">Delete</button>
                        </div>
                        <div class="row col-sm-2 hidden" id = "cancel-confirm-btn-${data.Car[i]['Vehicle license number']}">
                            <button class="btn btn-sm btn-danger" data-id=${data.Car[i]['Vehicle license number']} id="confirm-${data.Car[i]['Vehicle license number']}">Confirm</button>
                            <button class="btn btn-sm btn-success" data-id=${data.Car[i]['Vehicle license number']} id="cancel-${data.Car[i]['Vehicle license number']}">Cancel</button>
                        </div>
                    </td>
                </tr>`
          $('#myTable').append(row)
          buttonEvent(data.Car[i]['Vehicle license number'], data.ID)
        }
      }


    }

    function buildTable(data) {
      console.log("build table called")
      var table = document.getElementById('myTable')
      table.innerHTML = ''
      for (var i in data) {
        // console.log(data[i])
        addRow(data[i])
      }
    }

    buildTable(test);

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
    // ---------------- Delete data fucntion
    function deleteData() {
      console.log("deldata called")
      var dataID = $(this).data('id')
      var change_del_Btn = $(`#change-del-btn-${dataID}`)
      var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`)

      change_del_Btn.addClass('hidden')
      change_del_Btn.removeClass('show')
      cancel_confirm_Btn.addClass('show')
      cancel_confirm_Btn.removeClass('hidden')

    };

    function cancelDeletion() {
      var dataID = $(this).data('id')
      var change_del_Btn = $(`#change-del-btn-${dataID}`)
      var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`)

      change_del_Btn.addClass('show')
      change_del_Btn.removeClass('hidden')
      cancel_confirm_Btn.addClass('hidden')
      cancel_confirm_Btn.removeClass('show')
    };

    // 1/5
    function confirmDeletion(dataID) {
      var vehicleID = $(this).data('id')
      data = getDatasInRow(dataID, vehicleID)
      console.log(data)
      popupDialog("Success", "Data deleted")
    };


    // Table 2
    //add row table 2
    function addRowDetails(data) {
      data.Dates.forEach(function (dateData, dateIndex) {
        var isFirstDateRow = (dateIndex === 0);
        dateData.Details.forEach(function (detail, detailIndex) {
          var newRow = $('<tr>');
          if (isFirstDateRow && detailIndex === 0) {
            $('<td>').attr('rowspan', data.Dates.length * detailIndex).text(data["Vehicle license number"]).appendTo(newRow);
          }
          if (detailIndex === 0) {
            $('<td>').attr('rowspan', data.Dates.length).text(dateData.Date).appendTo(newRow);
          }
          $('<td>').text(detail.notes).appendTo(newRow);
          $('<td>').text(detail.equip).appendTo(newRow);
          $('<td>').text(detail.quantity).appendTo(newRow);
          $('<td>').text(detail.price).appendTo(newRow);
          $('<td>').text(detail.charge).appendTo(newRow);
          $('<td>').text(detail.total).appendTo(newRow);

          $('#detailsTable').append(newRow);
        });
      });
    }

    // -----------------Detailed datas of vehicle of customer
    function detailsData(dataID) {
      var vehicleID = $(this).data('id')
      // console.log(vehicleID)
      // firstData = getDatafromDB(vehicleID) // return JSON
      firstData = {
        "Vehicle license number": vehicleID,
        "Dates": [
          {
            'Date': '01/05/2024',
            'Details': [
              {
                'notes': 'aaaa',
                'equip': 'wheels',
                'quantity': '2',
                'price': '10',
                'charge': '4',
                'total': '24'
              },
              {
                'notes': 'bbbbb',
                'equip': 'big wheels',
                'quantity': '2',
                'price': '20',
                'charge': '4',
                'total': '24'
              }
            ]
          },
          {
            'Date': '1/1/2005',
            'Details': [
              {
                'notes': 'bbbbb',
                'equip': 'small wheels',
                'quantity': '2',
                'price': '8',
                'charge': '4',
                'total': '24'
              },
              {
                'notes': 'bbbbb',
                'equip': 'big glass',
                'quantity': '2',
                'price': '10',
                'charge': '4',
                'total': '24'
              }
            ]
          }
        ]
      }
      closeForm('#formDetails', '#closeForm', '#dataTable', 'click')
      // Show the form
      $('#formDetails').addClass('show')
      $('#formDetails').removeClass('hidden')
      $('#dataTable').css({
        'opacity': '0.5',
        'pointer-events': 'none'
      });
      // add the initial details in database
      addRowDetails(firstData)
      // submit create order after choose the date
      dateCurr = null
      $('#confirm-create').click(function (event) {
        event.preventDefault();
        var dateInput = $('#addDate').val()
        console.log(dateInput)
        if (firstData.Dates.includes(dateInput)) {
          dateCurr = dateInput
        }
        console.log(dateCurr)
      })
    }

    // Handle updatedData to database

    // close change form with x
    $("#closeFormChange").on("click", function () {
      $("#formChange").addClass("hidden");
      $("#formChange").removeClass("show");
      $("body").css("overflow", "auto");
      $("#dataTable").css({
        opacity: "",
        "pointer-events": "",
      });
      $("#formChange input").val("");
    });

  function updateData(dataID, newData) {
    console.log("after sub ", test);
    var index = test.findIndex((item) => item.ID === dataID);
    console.log("index: ", index);
    if (index !== -1) {
      test[index] = newData;
    }

    buildTable(test);
  }

  // Form Appear after click add button
  $("#addData").on("click", function () {
    $("#formAdd").addClass("show");
    $("#dataTable").css({
      opacity: "0.5",
      "pointer-events": "none",
    });
  });

  // $('#formAdd form').on('submit', function(event) {
  //     event.preventDefault();
  //     $('#formAdd').addClass('hidden');
  //     $('#formAdd').removeClass('show');
  //     $('body').css('overflow', 'auto');
  //     $('#dataTable').css({
  //         'opacity': '',
  //         'pointer-events': ''
  //     });
  //     var newID = uuid.v4()
  //     var newData = {
  //         ID: newID,
  //         Name: $('#addName').val(),
  //         Phone: $('#addPhone').val(),
  //         Email: $('#addEmail').val(),
  //         Address: $('#addAdress').val(),
  //         'Vehicle license number': $('#addVeID').val(),
  //         'Vehicle brand': $('#addBrand').val(),
  //         'Date': $('#addDate').val()
  //     };
  //     addRow(newData)
  //
  //     // Handle add data at Back End
  // });

  $("#closeForm").on("click", function () {
    $("#formAdd").addClass("hidden");
    $("#formAdd").removeClass("show");
    $("body").css("overflow", "auto");
    $("#dataTable").css({
      opacity: "",
      "pointer-events": "",
    });
  });

  function checkLicensePlate() {
    var urlParams = new URLSearchParams(window.location.search);
    var check = urlParams.get("exist");
    if (check) {
      popupDialog("Error", "This car is fixing !!!");
    }
  }

  //Menu handle
  document.addEventListener("DOMContentLoaded", function (event) {
    const showNavbar = (toggleId, navId, bodyId, headerId) => {
      const toggle = document.getElementById(toggleId),
          nav = document.getElementById(navId),
          bodypd = document.getElementById(bodyId),
          headerpd = document.getElementById(headerId),
          menuOpen = document.getElementById("menu-open");
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
}
catch (error) {
    alert("Error: " + error);
  }
}

fetchData();