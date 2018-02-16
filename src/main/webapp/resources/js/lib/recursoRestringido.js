$(function() {
	$("#form_usuario").attr("action", $("#action").val()); 
	$("#form_usuario").attr("method", $('#method').val());

	$("#action").change(function() {
		$("#form_usuario").attr("action", "/admin/" + $("#action").val()); 
	});
	$("#method").change(function() {
		$("#form_usuario").attr("method", $('#method').val());
	});
	$("#submit_form_usuario").click(function() {
		preventDefault();
		$("#target").load($("#action"));
	})
});