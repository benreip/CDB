
$().ready(function(){
	$("addComputer").validate({
		rules: {
			computerName : {
				required : true,
				minlength : 1
				
			}
		},
		messages : {
			computerName : "Le nom de l'ordinateur ne peut-pas être vide"
		}
	})
})




$("#introduced").change(function(){
	$("#discontinued").attr("min", this.value);
});

$("#discontinued").change(function(){
	$("#introduced").attr("max", this.value);
});

function checkDate(){
	var test = true;
	if($("#introduced").value != '' && $("#discontinued").value != ''){
		var introduced = new Date($("#introduced").value);
		var discontinued = new Date($("#discontinued").value);
		if(introduced > discontinued){
			test = false;
			alert("La date de fin doit-être plus récente que la date de sortie de l'ordinateur");
		}
	}
	if($("#computerName").val().trim() == ''){
		test = false;
		alert("Computer Name must not be empty");
	}
	return test;
}