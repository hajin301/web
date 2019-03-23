<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$("select[name='product_option']").change(function(){
	  var v1 = $("#product_option > option:selected").val(); //기본 value값
	  var v2 = $("#product_option > option:selected").attr("value2"); //지정 value2 값
	  var v3 = $("#product_option > option:selected").attr("value3");  //지정 value3값
	alert(v3);

	});
	
</script>
</head>
<body>
<select name="product_option" id="product_option" style="width:250px;" onchange="addbuylist(this);">

  <option value="10" value2="20" value3="30">10-20-30</option>

  <option value="40" value2="50" value3="60">40-50-60</option>

</select>
</body>
</html>