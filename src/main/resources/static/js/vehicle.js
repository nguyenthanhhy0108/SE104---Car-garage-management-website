
var test;

async function fetchData() {
  try {
    test = await $.ajax({
      url: "http://localhost:3000/form2",
      method: "GET",
      dataType: "json",
    });

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
        var name = data[i]["Name"].toLowerCase();
        if (name.includes(value)) {
          console.log("index of data: ", i);
          filteredData.push(data[i]);
        }
      }
      return filteredData;
    }
    // Build Table function
    // assist-function for build table
    function addRow(data) {
      // console.log(`${data.ID}`)
      var row = `<tr> 
        <td> ${data["ID"]}</td>
        <td> ${data["Vehicle license number"]}</td>
        <td> ${data["Vehicle brand"]}</td>
        <td> ${data["Name"]}</td>
        <td> ${data["Debt"]}</td>
        </tr>`;
      $("#myTable").append(row);
    }
    // build table function
    function buildTable(data) {
      console.log("build table called");
      var table = document.getElementById("myTable");
      table.innerHTML = "";
      for (var i in data) {
        addRow(data[i]);
      }
    }
    buildTable(test);
  } catch (error) {
    alert("Error: " + error);
  }
}

fetchData();
