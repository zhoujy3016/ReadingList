$(function () {
	// spring security 的csrf默认打开，post必须验证token
    // $.ajax({
     //    type: "POST",
     //    url: "/getDictCache/pub",
     //    contentType: "application/json",
     //    success: function(r){
     //    	var options = '';
     //    	for(var i = 0; i < r.pub.length; i++) {
     //    		options += '<option>'+ r.pub[i].value +'</option>'
     //    	}
     //    	$("#sel").append(options);
     //    }
    // });

    $.get("/getDictCache/sex", function(r){
    	console.log(r);
       	// var options = '';
       	// for(var i = 0; i < r.pub.length; i++) {
       	// 	options += '<option>'+ r.pub[i].value +'</option>'
       	// }
       	// $("#sel").append(options);
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