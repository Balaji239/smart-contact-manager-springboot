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

document.querySelector(".select-relationship")?.addEventListener("change",(event)=>{
    const filterTerm = $(".select-relationship").val();
    console.log(filterTerm);
    window.location = "/user/view-contacts/0?sortField=name&sortDir=asc&filter=" + filterTerm;
})

document.querySelector("#search-btn")?.addEventListener("click",()=>{
    const searchVal = $("#con-search").val();
    window.location = "/user/view-contacts/0?sortField=name&sortDir=asc&searchTerm="+searchVal;
})

document.querySelector(".add-con-btn")?.addEventListener("click", ()=>{
    window.location = "/user/add-contact";
})

document.querySelectorAll(".fav-con-star")?.forEach(ele => ele.addEventListener("click", updateContact));

function updateContact(event){
    const element = $(event.target);
    let isFavourite = false;
    const id = element.attr('id');
    if(element.hasClass('fa-regular')){
        element.removeClass('fa-regular')
        element.addClass('fa-solid');
        isFavourite = true;
    }
    else if(element.hasClass('fa-solid')){
        element.removeClass('fa-solid');
        element.addClass('fa-regular');
        isFavourite = false;
    }

    const url = "/user/con-favourite?conId="+id+"&isFavourite="+isFavourite;

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    fetch(url, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log('Response data:', data);
        })
        .catch(error => {
            console.error('There was a problem with the POST request:', error);
        });
}

document.querySelector("#edit-con-form")?.addEventListener("submit",(event)=>{
    // event.target.preventDefault();

})



