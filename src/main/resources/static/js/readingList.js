$(function () {
    $.ajax({
        type: "POST",
        url: "/getDictCache/mz,sex",
        contentType: "application/json",
        success: function(r){
        	alert(JSON.stringify(r));
        }
    });
});