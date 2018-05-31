$(function () {
    $.ajax({
        type: "POST",
        url: "/getDictCache/pub",
        contentType: "application/json",
        success: function(r){
        	var options = '';
        	for(i = 0; i < r.pub.length; i++) {
        		options += '<option>'+ r.pub[i].value +'</option>'
        	}
        	$("#sel").append(options);
        }
    });
});



var vm = new Vue({
	el:'#rrapp',
	data:{
		dictPub:{},
		dictArea:{}
	},
	methods: {
	}
});