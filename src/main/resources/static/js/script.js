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
// const searchContact = (inp) => {
//     console.log(inp.value);
//     let searchTerm = inp.value.trim();
//     console.log(searchTerm);
//     // window.location = "/user/view-contacts/0?sortField=name&sortDir=asc&searchTerm=" + searchTerm;
//     let url = "/user/con-search/"+searchTerm+"/0?sortField=name&sortDir=asc";
//     if(searchTerm!==""){
//         fetch(url).then((response) => {
//             return response.text();
//         }).then((data) => {
//             console.log(data);
//             $('#con-tbl').hide();
//             $('#search-tbl').html(data).show();
//         })
//     }
//     else{
//         $('#con-tbl').show();
//         $('#search-tbl').hide();
//     }
// }

document.querySelector(".view-relationship").addEventListener("change",(event)=>{
    const filterTerm = $(".view-relationship").val();
    console.log(filterTerm);
    window.location = "/user/view-contacts/0?sortField=name&sortDir=asc&filter=" + filterTerm;
})

document.querySelector("#search-icon").addEventListener("click",()=>{
    const searchVal = $("#con-search").val();
    window.location = "/user/view-contacts/0?sortField=name&sortDir=asc&searchTerm="+searchVal;
})



