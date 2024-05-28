// handle button
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

// Begining
const date = new Date();
$(document).ready(function () {
  console.log("loadpage function called");
  $("#form-51").addClass("show");
  $("#form-52").addClass("hidden");

  // handle month and year
  month = date.getMonth() + 1;
  year = date.getFullYear();
  let input_val;
  if (month < 10) {
    input_val = year.toString() + "-0" + month.toString();
  } else {
    input_val = year.toString() + "-" + month.toString;
  }
  $("#form-51 input").val(input_val);
  $("#form-52 input").val(input_val);

  // build table of form 5.1
  build_and_handleForm51(input_val);
  build_and_handleForm52(input_val);
});

async function fetchDataform51(request_time) {
  try {
    const test = await $.ajax({
      url: "http://localhost:3000/form5.1",
      // method: "POST", // official
      // data: request_time, //official
      method: "GET",
    });
    console.log("fetchDataform51 called");
    return test;
  } catch (error) {
    console.error(error);
  }
}

async function fetchDataform52(request_time) {
  try {
    const test = await $.ajax({
      url: "http://localhost:3000/form5.2",
      // method: "POST", // official
      // data: request_time, //official
      method: "GET",
    });
    console.log("fetchDataform52 called");
    return test;
  } catch (error) {
    console.error(error);
  }
}

function handleMonthChange51() {
  const new_time = $("#form-51 input").val();
  build_and_handleForm51(new_time);
}
function handleMonthChange52() {
  const new_time = $("#form-52 input").val();
  build_and_handleForm52(new_time);
}

async function build_and_handleForm51(time_request) {
  try {
    let list_form5_1 = await fetchDataform51(time_request);
    // show revernue
    var temp = list_form5_1.shift();
    $("#form-51 #numericRevernue").html(temp["TotalRevernue"]);
    // build table body
    buildTable(list_form5_1);
    new DataTable("#form-51 table");

    // Build Table function
    // assist-function for build table
    function addRow(data) {
      // console.log(`${data.ID}`)
      var row = `<tr> 
        <td> ${data["ID"]}</td>
        <td> ${data["Vehicle brand"]}</td>
        <td> ${data["Number of repairs"]}</td>
        <td> ${data["Revernue"]}</td>
        <td> ${data["Percentage"]}</td>
        </tr>`;
      $("#form-51 tbody").append(row);
      // console.log($("#form-51 tbody"))
    }
    // build table function
    function buildTable(data) {
      console.log("build table called");
      // var table = document.getElementById("myTable");
      var table = document.querySelector("#form-51 tbody");
      table.innerHTML = "";
      for (var i in data) {
        addRow(data[i]);
      }
    }
  } catch (error) {
    alert("Error: " + error);
  }
}

async function build_and_handleForm52(time_request) {
  try {
    let list_form5_2 = await fetchDataform52(time_request);

    // build table body
    buildTable(list_form5_2);
    new DataTable("#form-52 table");
    // Build Table function
    // assist-function for build table
    function addRow(data) {
      var row = `<tr>
        <td> ${data["ID"]}</td>
        <td> ${data["Supplies name"]}</td>
        <td> ${data["Initial amount"]}</td>
        <td> ${data["Generated amount"]}</td>
        <td> ${data["Final amount"]}</td>
        </tr>`;

      $("#form-52 tbody").append(row);
    }
    // build table function
    function buildTable(data) {
      console.log("build table called");
      // var table = document.getElementById("myTable");
      var table = document.querySelector("#form-52 tbody");
      table.innerHTML = "";
      for (var i in data) {
        addRow(data[i]);
      }
    }
  } catch (error) {
    console.log("ERROR", error);
  }
}
