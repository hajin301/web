<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="899739515511-32bs4aqab8rdkkjhkihvu52nh1dejjim.apps.googleusercontent.com">


<script>
function join(googleUser) {
	var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Email: ' + profile.getEmail());
  
  $('#SName').html('<input type = "hidden" name = "SName" id = "SName" value =' + profile.getName() + '>');
  $('#SEmail').html('<input type = "hidden" name = "SEmail" id = "SEmail" value =' + profile.getEmail() + '>');
  $('#SId').html('<input type = "hidden" name = "SId" id = "SId" value =' + profile.getId() + '>');
  
  document.join_frm.submit();
}


</script>

</head>
<body>
     <form action = "SNS_loginOk.do" method = "post" name = "join_frm">
		<div id ="login" class="g-signin2" data-onsuccess="join"></div>
		<span id="SId"></span> <br>
		<span id="SName"></span> <br>
		<span id="SEmail"></span> <br>
	</form>
	
	
</body>
</html>