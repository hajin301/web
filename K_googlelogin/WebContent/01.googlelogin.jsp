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
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
  
  $('#login').css('display', 'none');
  $('#logout').css('display', 'block');
  $('#upic').attr('src', profile.getImageUrl());
  $('#uname').html('[' + profile.getName() + ']');
}

function signOut() {
	  // Useful data for your client-side scripts:
	   var auth2 = gapi.auth2.getAuthInstance();
          
	      auth2.signOut().then(function () {
    	  console.log('User signed out.');
		  
		  $('#login').css('display', 'block');
		  $('#logout').css('display', 'none');
		  $('#upic').attr('src', '');
		  $('#uname').html(''); 
	  });
	 
	}

</script>

</head>
<body>
	<div id ="login" class="g-signin2" data-onsuccess="onSignIn"></div>
		
		<img id="upic" src=" "><br>
		<span id="uname"></span>

</body>
</html>