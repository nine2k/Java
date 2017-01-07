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
	<title> Movie Madness </title>
</head>

<body>
<div id ="homepage1">

	<div id="title">
		<h1> Movie Madness <img src="https://cdn2.iconfinder.com/data/icons/photo-and-video/500/Camera_cinema_consume_entertainment_film_media_movie_photo_play_record_video_television_theater-512.png"/></a></h1>
	</div>
	<div id="linkborder">
	<nav>
		<ol>
			<li><a href="http://52.10.239.98/13syeb/Assignment5.php">| Search |</a></li>
			<li><a href="http://52.10.239.98/13syeb/movieGenres.php">| Movie Genres |</a></li>
			<li><a href="http://52.10.239.98/13syeb/userRating.php">| User Rating |</a></li>
			<li><a href="http://52.10.239.98/13syeb/ratingList.php">| Ratings List |</a></li>
			<li><a href="http://52.10.239.98/13syeb/actors.php">| Check Search |</a></li>
	
		</ol>
	</nav>
	</div>
	
	<div class = "HeadingFont">
		<h1>Actors List</h1>
	</div>

	
<?php

		/*make the connection*/
		require_once('./mysqli_connect.php');

		// Create a query for the database
		$query ="SELECT id, first_name, last_name FROM actors";
		
		// Get a response from the database by sending the connection
		// and the query
		$response = @mysqli_query($dbc, $query);
		
		// If the query executed properly proceed
		if($response){
		echo '<table>
				 
				<tr><td><b>ID</b></td>
				<td><b>First Name</b></td>
				<td><b>Last Name</b></td></tr>';

		// mysqli_fetch_array will return a row of data from the query
		// until no further data is available
		//$query ="SELECT id, first_name, last_name, gender FROM actors";
		while($row = mysqli_fetch_array($response)){
			echo '<tr><td>' . 
			$row['id'] . '</td><td>' . 
			$row['first_name'] . '</td><td>' .
			$row['last_name'] . '</td></tr>';

			}

			echo '</table>';

			} else {

			echo "Couldn't issue database query<br />";

			echo mysqli_error($dbc);

			}

			

	?>
	<div class = "HeadingFont">
		<h1>Roles List</h1>
	</div>
	
	<?php
		// Create a query for the database
		$query1 ="SELECT actor_id, movie_id, role FROM roles";
		
		// Get a response from the database by sending the connection
		// and the query
		$response1 = @mysqli_query($dbc, $query1);
		
		// If the query executed properly proceed
		if($response1){
		echo '<table>
				 
				<tr><td><b>Actor ID</b></td>
				<td><b>Movie ID</b></td>
				<td><b>Role</b></td></tr>';

		// mysqli_fetch_array will return a row of data from the query
		// until no further data is available
		//$query ="SELECT id, first_name, last_name, gender FROM actors";
		while($row = mysqli_fetch_array($response1)){
			echo '<tr><td>' . 
			$row['actor_id'] . '</td><td>' . 
			$row['movie_id'] . '</td><td>' .
			$row['role'] . '</td></tr>';

			}

			echo '</table>';

			} else {

			echo "Couldn't issue database query<br />";

			echo mysqli_error($dbc);

			}


	?>
	
<div class = "HeadingFont">
		<h1>Movies List</h1>
</div>

	<?php


		// Create a query for the database
		$query2 ="SELECT id, name, year FROM movies";
		
		// Get a response from the database by sending the connection
		// and the query
		$response2 = @mysqli_query($dbc, $query2);
		
		// If the query executed properly proceed
		if($response2){
		echo '<table>
				 
				<tr><td><b>Movie ID</b></td>
				<td><b>Movie Name</b></td>
				<td><b>Movie Year</b></td></tr>';

		// mysqli_fetch_array will return a row of data from the query
		// until no further data is available
		//$query ="SELECT id, first_name, last_name, gender FROM actors";
		while($row = mysqli_fetch_array($response2)){
			echo '<tr><td>' . 
			$row['id'] . '</td><td>' . 
			$row['name'] . '</td><td>' .
			$row['year'] . '</td></tr>';

			}

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

 Copyright &#0169; 2015 Emily
 All Rights Reserved
	</pre>
</div>

</div>
</body>



</html>