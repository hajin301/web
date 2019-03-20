<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>구글 아이디로 로그인하기 2</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>

	<meta name="google-signin-client_id" content="899739515511-32bs4aqab8rdkkjhkihvu52nh1dejjim.apps.googleusercontent.com">

	<script>
    function onSignIn(googleUser) {
    	var profile = googleUser.getBasicProfile();
    	$('#upic').attr('src', profile.getImageUrl());
    	$('#uname').html('[ ' +profile.getName() + ' ]');
    }
    function onFailure(error) {
    }
    function renderButton() {
        gapi.signin.render('my-signin', {
	        'scope': 'profile email',
	        'longtitle': true,
	        'theme': 'dark',
	        'onsuccess': onSignIn,
	        'onfailure': onFailure
        });

    }
    $(document).ready(function() {
    	
    });
	</script>

</head>
<body>
    <div id="my-signin"></div>

  
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
</body>
</html>


