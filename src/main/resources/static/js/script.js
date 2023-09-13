
// ADD NEW CONTACT - AJAX
// $(window).on('load',(function () {
//
//     $("#addContactForm").submit(function (e) {
//         e.preventDefault();
//
//         const form = $('#addContactForm').get(0);
//         let formData = new FormData(form);
//             for (let pair of formData.entries()) {
//                 console.log(pair[0]+ ' - ' + pair[1]);
//             }
//         $.ajax({
//             url: '/user/process-contact',
//             type: 'post',
//             data: formData,
//             processData: false,
//             cache : false,
//             contentType:false,
//             success : function (data) {
//                 console.log("Submitted from ajax");
//                 console.log('Response : ' + data);
//                 $('.toast-header').removeClass('toast-alert');
//                 $('.toast .toast-header .me-auto').text("Contact saved successfully");
//                 $('.toast').addClass('toast-success');
//                 $('.toast').toast('show');
//
//             },
//             error : function (error) {
//                 // console.log("Error from ajax");
//                 // $('.toast-header').removeClass('toast-success');
//                 // $('.toast .toast-header .me-auto').text("Something went wrong try again");
//                 // $('.toast-header').addClass('toast-alert');
//                 // $('.toast').toast('show');
//                 window.location = "/user/add-contact";
//             }
//         })
//     })
// }));


function showToast() {

    if($('.toast').attr('value')==='success'){
        $('.toast .toast-header .me-auto').text("Contact saved successfully");
        $('.toast').addClass('toast-success');
        $('.toast').toast('show');
    }
    else{
        $('.toast .toast-header .me-auto').text("Something went wrong! Try again");
        $('.toast').addClass('toast-alert');
        $('.toast').toast('show');
    }
}

// DELETE CONTACT - SWEET ALERT
function deleteContact(id) {

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {

            window.location = "/user/delete-contact/" + id;

            // Swal.fire(
            //     'Deleted!',
            //     'Your Contact has been deleted.',
            //     'success'
            // )
            // setTimeout(function () {
            //     // window.location = "/user/delete-contact/" + id;
            // }, 4000);

        }
    })
}

function deletePopup(){
    if($('#con-del-popup').length){
        Swal.fire(
            'Deleted!',
            'Your Contact has been deleted.',
            'success'
        )
    }
}

const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
});

// CONTACT SEARCH AJAX
const searchContact = (inp) => {
    console.log(inp.value);
    let searchTerm = inp.value.trim();
    console.log(searchTerm);
    let url = "http://localhost:8080/user/con-search/"+searchTerm;
    if(searchTerm!==""){
        fetch(url).then((response) => {
            return response.text();
        }).then((data) => {
            // console.log(data);
            $('#con-tbl').hide();
            $('#search-tbl').html(data).show();
        })
    }
    else{
        $('#con-tbl').show();
        $('#search-tbl').hide();
    }
}






