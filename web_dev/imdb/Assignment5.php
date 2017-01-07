<!DOCTYPE html>

<!--
Author: Siyi Emily Bao
Student Number: 10103388
CISC 282 Assignment #5
-->


<html>


<head>
<!--CSS-->
<link href="a5.css" type="text/css" rel="stylesheet" />
<link href='https://fonts.googleapis.com/css?family=Indie+Flower' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Sigmar+One' rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="http://33.media.tumblr.com/c884ed25ea777ed03c294ede6d1f6c5e/tumblr_inline_mgs8islSNU1qid2nw.gif">
	<title> Queen's Rideshare </title>
</head>

<body>
<div id ="homepage">

	<div id="title">
		<h1> Queen's Rideshare <img src="https://cdn2.iconfinder.com/data/icons/photo-and-video/500/Camera_cinema_consume_entertainment_film_media_movie_photo_play_record_video_television_theater-512.png"/></a></h1>
	</div>
	<div id="linkborder">
	<nav>
		<ol>
			<li><a href="http://52.10.239.98/13syeb/Assignment5.php">| Search |</a></li>
			<li><a href="http://52.10.239.98/13syeb/movieGenres.php">| Results |</a></li>
		</ol>
	</nav>
	</div>
	

	<div class = "HeadingFont">
		<h1>Rideshare Search</h1>
	</div>
	<form action ="Assignment5.php" method="post">
				
		<table border="0">
		<tr>
			<td>Where</td>
			<td align="center"><input type="text" name="actor_firstname" /></td>
		</tr>
		
		<tr>
			<td>When</td>
			<td align="center"><input type="text" name="actor_lastname" /></td>
		</tr>
		</table>
		
		<input name = "submitbutton" type="submit" value="Search"/>
		
		
	</form>
	
	
	
	
	
	<?php
		
	
		$act_firstName = $_POST["actor_firstname"]; /*retrieve actor first name*/
		$act_lastName = $_POST["actor_lastname"]; /*retrieve actor last name*/
		$submitbutton= $_POST['submitbutton'];
		
		if (isset($submitbutton)){
		if (!empty($act_firstName) && !empty($act_lastName)) {
			echo '<div class="blockStyle">'; 
			echo "Results for: " . $act_firstName . " " . $act_lastName;
			echo '</div>'; 
			echo '<table>
				 
				<tr><td><b>Role</b></td>
				<td><b>Movie Name</b></td>
				<td><b>Date</b></td></tr>';
		} else {
			echo '<div class="blockStyle">'; 
			echo "Please enter in both fields";
			echo '</div>'; 
		}//end else
		}//end if

		require_once('./mysqli_connect.php');
		$query ="SELECT id, first_name, last_name FROM actors";
		$rowquery="SELECT actor_id, movie_id, role FROM roles";
		$moviequery="SELECT id, name, year FROM movies";
		
		$response = @mysqli_query($dbc, $query);
		$response1 = @mysqli_query($dbc, $rowquery);
		$response2 = @mysqli_query($dbc, $moviequery);
		
		if($response && $response1 && $response2){
			
				
		while($row = mysqli_fetch_array($response)){
			 if ($act_firstName == $row['first_name'] && $act_lastName == $row['last_name'] ){
				 $fetchID = $row['id']; 
			 }
		}
		$count = 0;
		$check = True;
		while($row3 = mysqli_fetch_array($response1)){
		 if ($fetchID == $row3['actor_id'] && $check==True){
				$count++;
				echo '<tr><td>' . 
				$row3['role'] . '</td>'; 
				$check = False;
				$fetchMovieID = $row3['movie_id'];
			
				 while($check == False && $row1 = mysqli_fetch_array($response2)){
					if ($fetchMovieID == $row1['id']){
						
						
						echo '<td>' . 
						$row1['name'] . '</td><td>' .
						$row1['year'] . '</td></tr>';
						$check = True;
					}//end if
				 }//end while
			}//end if
		}//end while
			

		echo '</table>';
			} else {

			echo "Couldn't issue database query<br />";

			echo mysqli_error($dbc);

			}

			// Close connection to the database
			mysqli_close($dbc);

	?>		


<!--Copyright-->
<div id="copyright">
	<pre>

 Copyright &#0169; 2016 QHacks
 Emily, Dayi, Michal, John
 All Rights Reserved
	</pre>
</div>

</div>
</body>



</html>