<<<<<<< HEAD
var test = (test = [
  {
    ID: "001",
    Name: "John Doe",
    Phone: "123-456-7890",
    Email: "john.doe@example.com",
    Address: "123 Main St, Cityville, USA",
    "Vehicle license number": "ABC123",
    "Vehicle brand": "Toyota",
    Date: "2024-04-19",
  },
  {
    ID: "002",
    Name: "Jane Smith",
    Phone: "987-654-3210",
    Email: "jane.smith@example.com",
    Address: "456 Elm St, Townsville, USA",
    "Vehicle license number": "XYZ789",
    "Vehicle brand": "Honda",
    Date: "2024-04-19",
  },
  {
    ID: "003",
    Name: "Michael Johnson",
    Phone: "555-555-5555",
    Email: "michael.johnson@example.com",
    Address: "789 Oak St, Villagetown, USA",
    "Vehicle license number": "DEF456",
    "Vehicle brand": "Ford",
    Date: "2024-04-19",
  },
  {
    ID: "004",
    Name: "Emily Brown",
    Phone: "111-222-3333",
    Email: "emily.brown@example.com",
    Address: "321 Pine St, Hamletville, USA",
    "Vehicle license number": "GHI789",
    "Vehicle brand": "Chevrolet",
    Date: "2024-04-19",
  },
  {
    ID: "005",
    Name: "Christopher Lee",
    Phone: "444-444-4444",
    Email: "christopher.lee@example.com",
    Address: "555 Cedar St, Suburbia, USA",
    "Vehicle license number": "JKL012",
    "Vehicle brand": "BMW",
    Date: "2024-04-19",
  },
  {
    ID: "006",
    Name: "Jessica Martinez",
    Phone: "666-777-8888",
    Email: "jessica.martinez@example.com",
    Address: "777 Maple St, Ruralville, USA",
    "Vehicle license number": "MNO345",
    "Vehicle brand": "Mercedes-Benz",
    Date: "2024-04-19",
  },
  {
    ID: "007",
    Name: "Daniel Taylor",
    Phone: "999-999-9999",
    Email: "daniel.taylor@example.com",
    Address: "888 Walnut St, Countryside, USA",
    "Vehicle license number": "PQR678",
    "Vehicle brand": "Audi",
    Date: "2024-04-19",
  },
  {
    ID: "008",
    Name: "Sarah Clark",
    Phone: "000-111-2222",
    Email: "sarah.clark@example.com",
    Address: "999 Birch St, Mountainville, USA",
    "Vehicle license number": "STU901",
    "Vehicle brand": "Volkswagen",
    Date: "2024-04-19",
  },
  {
    ID: "009",
    Name: "David Garcia",
    Phone: "333-333-3333",
    Email: "david.garcia@example.com",
    Address: "1010 Oak St, Lakeside, USA",
    "Vehicle license number": "VWX234",
    "Vehicle brand": "Subaru",
    Date: "2024-04-19",
  },
  {
    ID: "010",
    Name: "Maria Hernandez",
    Phone: "777-777-7777",
    Email: "maria.hernandez@example.com",
    Address: "1212 Maple St, Rivershore, USA",
    "Vehicle license number": "YZA567",
    "Vehicle brand": "Tesla",
    Date: "2024-04-19",
  },
  {
    ID: "011",
    Name: "James Wilson",
    Phone: "888-888-8888",
    Email: "james.wilson@example.com",
    Address: "1313 Pine St, Hilltop, USA",
    "Vehicle license number": "BCD890",
    "Vehicle brand": "Jeep",
    Date: "2024-04-19",
  },
  {
    ID: "012",
    Name: "Linda Young",
    Phone: "222-222-2222",
    Email: "linda.young@example.com",
    Address: "1414 Elm St, Valleyview, USA",
    "Vehicle license number": "EFG123",
    "Vehicle brand": "Volvo",
    Date: "2024-04-19",
  },
  {
    ID: "013",
    Name: "Kevin Anderson",
    Phone: "555-123-4567",
    Email: "kevin.anderson@example.com",
    Address: "1515 Oak St, Lakeside, USA",
    "Vehicle license number": "HIJ456",
    "Vehicle brand": "Lexus",
    Date: "2024-04-19",
  },
  {
    ID: "014",
    Name: "Amanda Thompson",
    Phone: "999-888-7777",
    Email: "amanda.thompson@example.com",
    Address: "1616 Maple St, Rivershore, USA",
    "Vehicle license number": "KLM789",
    "Vehicle brand": "Mazda",
    Date: "2024-04-19",
  },
  {
    ID: "015",
    Name: "Ryan Walker",
    Phone: "444-555-6666",
    Email: "ryan.walker@example.com",
    Address: "1717 Pine St, Hilltop, USA",
    "Vehicle license number": "NOP012",
    "Vehicle brand": "Porsche",
    Date: "2024-04-19",
  },
  {
    ID: "016",
    Name: "Jennifer Moore",
    Phone: "777-666-5555",
    Email: "jennifer.moore@example.com",
    Address: "1818 Elm St, Valleyview, USA",
    "Vehicle license number": "QRS345",
    "Vehicle brand": "Ferrari",
    Date: "2024-04-19",
  },
  {
    ID: "017",
    Name: "Matthew King",
    Phone: "222-333-4444",
    Email: "matthew.king@example.com",
    Address: "1919 Oak St, Lakeside, USA",
    "Vehicle license number": "TUV678",
    "Vehicle brand": "McLaren",
    Date: "2024-04-19",
  },
  {
    ID: "018",
    Name: "Rebecca White",
    Phone: "888-999-0000",
    Email: "rebecca.white@example.com",
    Address: "2020 Maple St, Rivershore, USA",
    "Vehicle license number": "WXY901",
    "Vehicle brand": "Rolls-Royce",
    Date: "2024-04-19",
  },
  {
    ID: "019",
    Name: "Joshua Carter",
    Phone: "333-444-5555",
    Email: "joshua.carter@example.com",
    Address: "2121 Pine St, Hilltop, USA",
    "Vehicle license number": "YZA234",
    "Vehicle brand": "Land Rover",
    Date: "2024-04-19",
  },
  {
    ID: "020",
    Name: "Kimberly Hall",
    Phone: "555-666-7777",
    Email: "kimberly.hall@example.com",
    Address: "2222 Elm St, Valleyview, USA",
    "Vehicle license number": "BCD567",
    "Vehicle brand": "Jaguar",
    Date: "2024-04-19",
  },
]);

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
function addRow(data) {
  // console.log(`${data.ID}`)
  var row = `<tr scope="row" class="data-row-${data.ID}" data-id=${data.ID}>
=======
var test;

async function fetchData() {
  try {
    test = await $.ajax({
      url: "/get-all-records",
      method: "GET",
      dataType: "json",
    });

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
    function addRow(data) {
      // console.log(`${data.ID}`)
      var row = `<tr scope="row" class="data-row-${data.ID}" data-id=${data.ID}>
>>>>>>> Nhaan
                    <td id="data-name-${data.ID}">${data.Name}</td>
                    <td id="data-phone-${data.ID}">${data.Phone}</td>
                    <td id="data-email-${data.ID}">${data.Email}</td>
                    <td id="data-address-${data.ID}">${data.Address}</td>
                    <td id="data-license-${data.ID}">${data["Vehicle license number"]}</td>
                    <td id="data-brand-${data.ID}">${data["Vehicle brand"]}</td>
                    <td id="data-date-${data.ID}">${data.Date}</td>
                    <td>
                        <div class = "row col-sm-2 show" id = "change-del-btn-${data.ID}">
                            <button class="btn btn-sm btn-info" data-id=${data.ID} id="change-${data.ID}">Change</button>
                            <button class="btn btn-sm btn-danger" data-id=${data.ID} id="delete-${data.ID}">Delete</button>
                        </div>
                        <div class="row col-sm-2 hidden" id = "cancel-confirm-btn-${data.ID}">
                            <button class="btn btn-sm btn-danger" data-id=${data.ID} id="confirm-${data.ID}">Confirm</button>
                            <button class="btn btn-sm btn-success" data-id=${data.ID} id="cancel-${data.ID}">Cancel</button>
                        </div>
                    </td>
                </tr>`;
<<<<<<< HEAD
  $("#myTable").append(row);

  $(`#delete-${data.ID}`).on("click", deleteData);
  $(`#cancel-${data.ID}`).on("click", cancelDeletion);
  $(`#confirm-${data.ID}`).on("click", confirmDeletion);
  $(`#change-${data.ID}`).on("click", editData);
}

function buildTable(data) {
  console.log("build table called");
  var table = document.getElementById("myTable");
  table.innerHTML = "";
  for (var i in data) {
    addRow(data[i]);
  }
}
buildTable(test);

// Delete data fucntion
function deleteData() {
  // console.log("deldata called")
  var dataID = $(this).data("id");
  var change_del_Btn = $(`#change-del-btn-${dataID}`);
  var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`);

  change_del_Btn.addClass("hidden");
  change_del_Btn.removeClass("show");
  cancel_confirm_Btn.addClass("show");
  cancel_confirm_Btn.removeClass("hidden");
}
function cancelDeletion() {
  var dataID = $(this).data("id");
  var change_del_Btn = $(`#change-del-btn-${dataID}`);
  var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`);

  change_del_Btn.addClass("show");
  change_del_Btn.removeClass("hidden");
  cancel_confirm_Btn.addClass("hidden");
  cancel_confirm_Btn.removeClass("show");
}
function confirmDeletion() {
  var dataID = $(this).data("id");
  var row = $(`.data-row-${dataID}`);
  row.remove();

  // handle Backend database
  // after actually delete the data in database
  // $.ajax({
  //     method: 'GET',
  //     url: , //url send data
  //     dataType: 'json' ,
  //     success:function(response){
  //         data = response.data
  //         buildTable(data)
  //         // console.log(data)
  //     }
  // })
  popupDialog("Success", "Data deleted");
}

// Edit data
function editData() {
  var dataID = $(this).data("id");
  console.log(dataID);
  $("#formChange").addClass("show");
  $("#dataTable").css({
    opacity: "0.5",
    "pointer-events": "none",
  });

  // Get old data
  $("#changeName").val($(`#data-name-${dataID}`).text());
  $("#changePhone").val($(`#data-phone-${dataID}`).text());
  $("#changeEmail").val($(`#data-email-${dataID}`).text());
  $("#changeAdress").val($(`#data-address-${dataID}`).text());
  $("#changeVeID").val($(`#data-license-${dataID}`).text());
  $("#changeBrand").val($(`#data-brand-${dataID}`).text());
  $("#changeDate").val($(`#data-date-${dataID}`).text());
  // submit event
  console.log("before sub", test);
  $("#formChange form")
    .data("id", dataID)
    .off("submit")
    .on("submit", function (event) {
      console.log("dataID sub", dataID);
      // var datasubID = $(this).data('id')
      // console.log("submitted")
      event.preventDefault();
      var updatedData = {
        ID: dataID,
        Name: $("#changeName").val(),
        Phone: $("#changePhone").val(),
        Email: $("#changeEmail").val(),
        Address: $("#changeAdress").val(),
        "Vehicle license number": $("#changeVeID").val(),
        "Vehicle brand": $("#changeBrand").val(),
        Date: $("#changeDate").val(),
      };
      console.log("updated data: ", updatedData);
      updateData(dataID, updatedData);

      $("#formChange").addClass("hidden");
      $("#formChange").removeClass("show");
      $("body").css("overflow", "auto");
      $("#dataTable").css({
        opacity: "",
        "pointer-events": "",
      });
    });
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
}
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

$("#formAdd form").on("submit", function (event) {
  event.preventDefault();
  $("#formAdd").addClass("hidden");
  $("#formAdd").removeClass("show");
  $("body").css("overflow", "auto");
  $("#dataTable").css({
    opacity: "",
    "pointer-events": "",
  });
  var newID = uuid.v4();
  var newData = {
    ID: newID,
    Name: $("#addName").val(),
    Phone: $("#addPhone").val(),
    Email: $("#addEmail").val(),
    Address: $("#addAdress").val(),
    "Vehicle license number": $("#addVeID").val(),
    "Vehicle brand": $("#addBrand").val(),
    Date: $("#addDate").val(),
  };
  addRow(newData);

  // Handle add data at Back End
});

$("#closeForm").on("click", function () {
  $("#formAdd").addClass("hidden");
  $("#formAdd").removeClass("show");
  $("body").css("overflow", "auto");
  $("#dataTable").css({
    opacity: "",
    "pointer-events": "",
  });
});

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

// JS cho form2

// print data
function printData(data) {
  let html = "";
  for (let i = 0; i < data.length; i++) {
    html += `
=======
      $("#myTable").append(row);

      $(`#delete-${data.ID}`).on("click", deleteData);
      $(`#cancel-${data.ID}`).on("click", cancelDeletion);
      $(`#confirm-${data.ID}`).on("click", confirmDeletion);
      $(`#change-${data.ID}`).on("click", editData);
    }

    function buildTable(data) {
      console.log("build table called");
      var table = document.getElementById("myTable");
      table.innerHTML = "";
      for (var i in data) {
        addRow(data[i]);
      }
    }
    buildTable(test);

    // Delete data fucntion
    function deleteData() {
      // console.log("deldata called")
      var dataID = $(this).data("id");
      var change_del_Btn = $(`#change-del-btn-${dataID}`);
      var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`);

      change_del_Btn.addClass("hidden");
      change_del_Btn.removeClass("show");
      cancel_confirm_Btn.addClass("show");
      cancel_confirm_Btn.removeClass("hidden");
    }
    function cancelDeletion() {
      var dataID = $(this).data("id");
      var change_del_Btn = $(`#change-del-btn-${dataID}`);
      var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`);

      change_del_Btn.addClass("show");
      change_del_Btn.removeClass("hidden");
      cancel_confirm_Btn.addClass("hidden");
      cancel_confirm_Btn.removeClass("show");
    }
    function confirmDeletion() {
      var dataID = $(this).data("id");
      var row = $(`.data-row-${dataID}`);
      row.remove();

      // handle Backend database
      // after actually delete the data in database
      // $.ajax({
      //     method: 'GET',
      //     url: , //url send data
      //     dataType: 'json' ,
      //     success:function(response){
      //         data = response.data
      //         buildTable(data)
      //         // console.log(data)
      //     }
      // })
      popupDialog("Success", "Data deleted");
    }

    // Edit data
    function editData() {
      var dataID = $(this).data("id");
      console.log(dataID);
      $("#formChange").addClass("show");
      $("#dataTable").css({
        opacity: "0.5",
        "pointer-events": "none",
      });

      // Get old data
      $("#changeName").val($(`#data-name-${dataID}`).text());
      $("#changePhone").val($(`#data-phone-${dataID}`).text());
      $("#changeEmail").val($(`#data-email-${dataID}`).text());
      $("#changeAdress").val($(`#data-address-${dataID}`).text());
      $("#changeVeID").val($(`#data-license-${dataID}`).text());
      $("#changeBrand").val($(`#data-brand-${dataID}`).text());
      $("#changeDate").val($(`#data-date-${dataID}`).text());
      // submit event
      console.log("before sub", test);
      $("#formChange form")
        .data("id", dataID)
        .off("submit")
        .on("submit", function (event) {
          console.log("dataID sub", dataID);
          // var datasubID = $(this).data('id')
          // console.log("submitted")
          event.preventDefault();
          var updatedData = {
            ID: dataID,
            Name: $("#changeName").val(),
            Phone: $("#changePhone").val(),
            Email: $("#changeEmail").val(),
            Address: $("#changeAdress").val(),
            "Vehicle license number": $("#changeVeID").val(),
            "Vehicle brand": $("#changeBrand").val(),
            Date: $("#changeDate").val(),
          };
          console.log("updated data: ", updatedData);
          updateData(dataID, updatedData);

          $("#formChange").addClass("hidden");
          $("#formChange").removeClass("show");
          $("body").css("overflow", "auto");
          $("#dataTable").css({
            opacity: "",
            "pointer-events": "",
          });
        });
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
    }
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
  } catch (error) {
    alert("Error: " + error);
  }
}

fetchData();

function handleForm2() {
  // User nhập số đt vào ô, nhấn search => Gửi form về server => server gửi data.
  let list = [];
  $(document).ready(() => {
    $("#main-screen-content-order2 form").submit(function (event) {
      event.preventDefault();
      $("#main-screen-content-order2 table").css("display", "table");
      $.ajax({
        url: "http://localhost:3000/list",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
          printData(data);
          list = data;
        },
        error: function (error) {
          console.log(error);
        },
      });
    });
  });
  // Print data
  function printData(data) {
    let html = "";
    for (let i = 0; i < data.length; i++) {
      html += `
>>>>>>> Nhaan
    <tr> 
    <td> ${data[i]["ID"]}</td>
    <td> ${data[i]["Vehicle license number"]}</td>
    <td> ${data[i]["Vehicle brand"]}</td>
    <td> ${data[i]["Name"]}</td>
    <td> ${data[i]["Debt"]}</td>
    </tr>
    `;
<<<<<<< HEAD
  }
  // console.log(html);
  $(document).ready(function () {
    const body = $("#main-screen-content-order2 tbody");
    body.html(html);
  });
}

// User nhập số đt vào ô, nhấn search => Gửi form về server => server gửi lại data user trùng với sđt
let list = [];
$(document).ready(() => {
  $("#main-screen-content-order2 form").submit(function (event) {
    event.preventDefault();
    $("#main-screen-content-order2 table").css("display", "table");
    $.ajax({
      url: "http://localhost:3000/list",
      method: "GET",
      dataType: "JSON",
      success: function (data) {
        console.log("succ");
        printData(data);
        list = data;
      },
      error: function (error) {
        console.log(error);
      },
    });
  });
});

// Sort function

$("#main-screen-content-order2 th").on("click", function () {
  const column = $(this).data("column");
  const order = $(this).data("order");
  const textArray = $(this).html().split(" ");
  console.log(textArray[0]);
  if (order !== undefined) {
    if (order === "desc") {
      list = list.sort((a, b) => (a[column] > b[column] ? 1 : -1));
      $(this).data("order", "asc");
      $(this).html(textArray[0] + " &#9650");
    } else if (order === "asc") {
      list = list.sort((a, b) => (a[column] < b[column] ? 1 : -1));
      $(this).data("order", "desc");
      $(this).html(textArray[0] + " &#9660");
    }
  }
  printData(list);
  console.log($(this).html());
});
=======
    }
    // console.log(html);
    $(document).ready(function () {
      const body = $("table tbody#myTableBody");
      body.html(html);
    });
  }

  // Sort data
  $("#main-screen-content-order2 th").on("click", function () {
    const column = $(this).data("column");
    const order = $(this).data("order");
    const textArray = $(this).html().split(" ");
    console.log(textArray[0]);
    if (order !== undefined) {
      if (order === "desc") {
        list = list.sort((a, b) => (a[column] > b[column] ? 1 : -1));
        $(this).data("order", "asc");
        $(this).html(textArray[0] + " &#9650");
      } else if (order === "asc") {
        list = list.sort((a, b) => (a[column] < b[column] ? 1 : -1));
        $(this).data("order", "desc");
        $(this).html(textArray[0] + " &#9660");
      }
    }
    printData(list);
    console.log($(this).html());
  });
}
>>>>>>> Nhaan
