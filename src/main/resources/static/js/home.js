var test;

async function fetchData() {
    try {
        test = await $.ajax({
            url: '/get-all-records',
            method: 'GET',
            dataType: 'json'
        });

        checkLicensePlate();
// Sort table function
        $('th').on('click', function(){
            var column = $(this).data('column')
            var order = $(this).data('order')
            var text = $(this).html()
            if (order !== undefined){
                text = text.substring(0, text.length-1)
                if (order == 'desc'){
                    $(this).data('order', 'asc')
                    test = test.sort((a,b) => a[column] > b[column] ? 1 : -1)
                    text += '&#9660'
                }else if(order == 'asc') {
                    $(this).data('order', 'desc')
                    test = test.sort((a,b) => a[column] < b[column] ? 1 : -1)
                    text += '&#9650'
                }
            }
            else text = text
            $(this).html(text)
            buildTable(test)

        })

        document.getElementById("addDate").addEventListener("input", function() {
            if (this.value.length > 10) {
                this.value = this.value.slice(0, 10);
            }

            var currentDay = new Date();
            currentDay.setHours(0, 0, 0, 0);

            var typedDay = new Date(this.value);
            typedDay.setHours(0, 0, 0, 0);

            if(typedDay < currentDay) {
                popupDialog("Error", "Your typed day must greater than current day !!!");
                this.value = "";
            }
        });


// Search function
        $('#search-input').on('keyup', function(){
            var value = $(this).val()
            console.log('Value', value)
            //// get Table Data from database
            // tableData = getDataFromServer()
            // var data = searchTable(value, tableData)
            var data = searchTable(value, test)
            buildTable(data)
        })
        function searchTable(value, data){
            var filteredData = []
            for (var i = 0; i < data.length; i++){
                value=value.toLowerCase()
                var name = data[i].Name.toLowerCase()
                if(name.includes(value)){
                    console.log("index of data: ", i)
                    filteredData.push(data[i])
                }
            }
            return filteredData
        }
// Build Table function
        function addRow(data){
            // console.log(`${data.ID}`)
            var row = `<tr scope="row" class="data-row-${data.ID}" data-id=${data.ID}>
                    <td id="data-name-${data.ID}">${data.Name}</td>
                    <td id="data-phone-${data.ID}">${data.Phone}</td>
                    <td id="data-email-${data.ID}">${data.Email}</td>
                    <td id="data-address-${data.ID}">${data.Address}</td>
                    <td id="data-license-${data.ID}">${data['Vehicle license number']}</td>
                    <td id="data-brand-${data.ID}">${data['Vehicle brand']}</td>
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
                </tr>`
            $('#myTable').append(row)

            $(`#delete-${data.ID}`).on('click', deleteData)
            $(`#cancel-${data.ID}`).on('click', cancelDeletion)
            $(`#confirm-${data.ID}`).on('click', confirmDeletion)
            $(`#change-${data.ID}`).on('click', editData)
        }

        function buildTable(data){
            console.log("build table called")
            var table = document.getElementById('myTable')
            table.innerHTML = ''
            for (var i in data){
                addRow(data[i])
            }
        }
        buildTable(test)

    // Delete data fucntion
        function deleteData(){
            // console.log("deldata called")
            var dataID = $(this).data('id')
            var change_del_Btn = $(`#change-del-btn-${dataID}`)
            var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`)

            change_del_Btn.addClass('hidden')
            change_del_Btn.removeClass('show')
            cancel_confirm_Btn.addClass('show')
            cancel_confirm_Btn.removeClass('hidden')


        };
        function cancelDeletion(){
            var dataID = $(this).data('id')
            var change_del_Btn = $(`#change-del-btn-${dataID}`)
            var cancel_confirm_Btn = $(`#cancel-confirm-btn-${dataID}`)

            change_del_Btn.addClass('show')
            change_del_Btn.removeClass('hidden')
            cancel_confirm_Btn.addClass('hidden')
            cancel_confirm_Btn.removeClass('show')
        };
        function confirmDeletion(){
            var dataID = $(this).data('id');

            var data = getDatasInRow(dataID);

            $.ajax({
                url: '/delete-row-form1',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    // alert('Data sent successfully:');
                },
                error: function(xhr, status, error) {
                    alert(status + ': ' + error);
                }
            });

            var row = $(`.data-row-${dataID}`);
            row.remove();

            popupDialog("Success", "Data deleted")

        };


        function getDatasInRow(dataID){
            var phoneNumber = document.getElementById("data-phone-" + dataID.toString()).textContent;
            var name = document.getElementById("data-name-" + dataID.toString()).textContent;
            var email = document.getElementById("data-email-" + dataID.toString()).textContent;
            var address = document.getElementById("data-address-" + dataID.toString()).textContent;
            var license = document.getElementById("data-license-" + dataID.toString()).textContent;
            var brand = document.getElementById("data-brand-" + dataID.toString()).textContent;
            var date = document.getElementById("data-date-" + dataID.toString()).textContent;

            return  data = {
                recordID: dataID,
                name: name,
                phone: phoneNumber,
                email: email,
                address: address,
                license: license,
                brand: brand,
                date: date
            };
        }

// Edit data
        function editData(){
            var dataID = $(this).data('id')
            console.log(dataID)
            $('#formChange').addClass('show')
            $('#dataTable').css({
                'opacity': '0.5',
                'pointer-events': 'none'
            });

            var data = getDatasInRow(dataID);

            $.ajax({
                url: '/get-old-data-change-form1',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response) {
                    // alert("abc")
                },
                error: function(xhr, status, error) {
                    alert(status + ': ' + error);
                }
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
            // console.log("before sub", test);
            // $("#formChange form")
            //     .data("id", dataID)
            //     .off("submit")
            //     .on("submit", function (event) {
            //         console.log("dataID sub", dataID);
            //         // var datasubID = $(this).data('id')
            //         // console.log("submitted")
            //         event.preventDefault();
            //
            //         var updatedData = {
            //             ID: dataID,
            //             Name: $("#changeName").val(),
            //             Phone: $("#changePhone").val(),
            //             Email: $("#changeEmail").val(),
            //             Address: $("#changeAdress").val(),
            //             "Vehicle license number": $("#changeVeID").val(),
            //             "Vehicle brand": $("#changeBrand").val(),
            //             Date: $("#changeDate").val(),
            //         };
            //         console.log("updated data: ", updatedData);
            //         updateData(dataID, updatedData);
            //
            //         $("#formChange").addClass("hidden");
            //         $("#formChange").removeClass("show");
            //         $("body").css("overflow", "auto");
            //         $("#dataTable").css({
            //             opacity: "",
            //             "pointer-events": "",
            //         });
            //     });


            // close change form with x
            $('#closeFormChange').on('click', function(){
                $('#formChange').addClass('hidden');
                $('#formChange').removeClass('show');
                $('body').css('overflow', 'auto');
                $('#dataTable').css({
                    'opacity': '',
                    'pointer-events': ''
                });
                $('#formChange input').val('');
            });

        };


        function updateData(dataID, newData) {
            console.log("after sub ",test)
            var index = test.findIndex(item => item.ID === dataID);
            console.log("index: ", index)
            if (index !== -1) {
                test[index] = newData;
            }

            buildTable(test)

        }


// Form Appear after click add button
        $('#addData').on('click', function(){
            $('#formAdd').addClass('show');
            $('#dataTable').css({
                'opacity': '0.5',
                'pointer-events': 'none'
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

        $('#closeForm').on('click', function(){
            $('#formAdd').addClass('hidden');
            $('#formAdd').removeClass('show');
            $('body').css('overflow', 'auto');
            $('#dataTable').css({
                'opacity': '',
                'pointer-events': ''
            });
        });

        function checkLicensePlate() {
            var urlParams = new URLSearchParams(window.location.search);
            var check = urlParams.get('exist');
            if(check) {
                popupDialog("Error", "This car is fixing !!!");
            }
        }

//Menu handle
        document.addEventListener("DOMContentLoaded", function(event) {

            const showNavbar = (toggleId, navId, bodyId, headerId) =>{
                const toggle = document.getElementById(toggleId),
                    nav = document.getElementById(navId),
                    bodypd = document.getElementById(bodyId),
                    headerpd = document.getElementById(headerId),
                    menuOpen = document.getElementById('menu-open')
                menuClose = document.getElementById('menu-close')

                // Validate that all variables exist
                if(toggle && nav && bodypd && headerpd && menuOpen && menuClose){
                    toggle.addEventListener('click', ()=>{
                        // show navbar
                        nav.classList.toggle('show-nav')

                        // change icon
                        if (menuOpen.classList.contains('show')) {
                            menuOpen.classList.remove('show');
                            menuOpen.classList.add('hidden');
                            menuClose.classList.remove('hidden');
                            menuClose.classList.add('show');
                        } else {
                            menuOpen.classList.remove('hidden');
                            menuOpen.classList.add('show');
                            menuClose.classList.remove('show');
                            menuClose.classList.add('hidden');
                        }

                        // add padding to body
                        bodypd.classList.toggle('body-pd')

                        // add padding to header
                        headerpd.classList.toggle('body-pd')
                    })
                }
            }

            showNavbar('header-toggle','nav-bar','body-pd','header')

            const linkColor = document.querySelectorAll('.nav_link')

            function colorLink(){
                if(linkColor){
                    linkColor.forEach(l=> l.classList.remove('active'))
                    this.classList.add('active')
                }
            }
            linkColor.forEach(l=> l.addEventListener('click', colorLink))

        });
    } catch (error) {
        alert('Error: ' + error);
    }
}

fetchData();

