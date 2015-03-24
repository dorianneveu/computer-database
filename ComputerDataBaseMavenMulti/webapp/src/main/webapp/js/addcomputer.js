(function($) {
	$.fn.checkvalue = function(){

		var x = $("#name").val().length;
			if(x==0){
				$("#name").css("border", "solid");
			    $("#name").css("border-color", "#ff0000");
			}else{
			    $("#name").css("border", "none");
			}
	};
}(jQuery));

(function ( $ ) {
    $.fn.addComputer = function() {
    	var x = $("#name").val().length;
		if(x==0){
			$("#name").css("border", "solid");
		    $("#name").css("border-color", "#ff0000");
			alert("You have to enter a name!");
			preventDefault();
			return false;
		}
		return true;
    };
}( jQuery ));


$(document).ready(function() {
	$("#send").click(function(e) {
		var name = $("#name").val();
		if (name == '') {
			e.preventDefault();
	    	$("#name").css("border", "solid");
		    $("#name").css("border-color", "#ff0000");
			alert("You have to enter a name!");
		} else if ($("#introduced").val() != '') {
			if (!isDate($("#introduced").val())) {
				e.preventDefault();
				$("#introduced").css("border", "solid");
			    $("#introduced").css("border-color", "#ff0000");
				alert("Date format yyyy-mm-dd");
			} else {
			    $("#introduced").css("border", "none");
			}
		}if ($("#discontinued").val() != '') {
			if (!isDate($("#discontinued").val())) {
				e.preventDefault();
				$("#discontinued").css("border", "solid");
			    $("#discontinued").css("border-color", "#ff0000");
				alert("Date format yyyy-mm-dd");
			} else {
			    $("#discontinued").css("border", "none");
			}
		}
	});
});

function isDate(txtDate)
	{
	  var currVal = txtDate;
	  if(currVal == '')
	    return false;
	   
	  //Declare Regex 
	  var rxDatePattern = /^(\d{4})(-)(\d{1,2})(-)(\d{1,2})$/;
	  var dtArray = currVal.match(rxDatePattern); // is format OK?
	 
	  if (dtArray == null)
	     return false;
	  
	  //Checks for mm/dd/yyyy format.
	  dtMonth = dtArray[5];
	  dtDay= dtArray[7];
	  dtYear = dtArray[1];
	 
	  if (dtMonth < 1 || dtMonth > 12)
	      return false;
	  else if (dtDay < 1 || dtDay> 31)
	      return false;
	  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
	      return false;
	  else if (dtMonth == 2)
	  {
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap))
	          return false;
	  }
	  return true;
	}


