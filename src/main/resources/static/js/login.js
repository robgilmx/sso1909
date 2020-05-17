

$( document ).ready(function() {
    checkQueryParams();
});

function checkQueryParams() {
    var pathname = window.location.search;
    if (pathname.includes("error")){
        $('#error-div').show();
    }else {
        $('#error-div').hide();
    }
    if (pathname.includes("blocked")){
        $('#blocked-div').show();
    }else {
        $('#blocked-div').hide();
    }
    console.log("JS is working btw.")
}
