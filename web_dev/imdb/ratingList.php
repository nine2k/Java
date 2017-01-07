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
<div id ="homepage">

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
		<h1>Movies Genre</h1>
	</div>
	
	<form action ="ratingList.php" method="post">
				
		<table border="0">
		
		
		<tr>
			<td>Select Genres</td>
			
			<td align="center">

	<form action = "movieGenres.php" method = "post">
	<?php
		/*make the connection*/
		require_once('./mysqli_connect.php');

		$query ="SELECT DISTINCT genre FROM movies_genres";
		
		$response = @mysqli_query($dbc, $query);

				
		$arr = array();
		$i = 0;
		// If the query executed properly proceed
		if($response){

		while($row = mysqli_fetch_array($response)){

			$arr[$i] = $row['genre'];
			$i++;
			}

			} else {

			echo "Couldn't issue database query<br />";

			echo mysqli_error($dbc);

			}
			$arr_length = count($arr);

			echo "<select name = \"genres\">";
			for ($a=0;$a<$arr_length;$a++){
				echo "<option value=".$arr[$a].">".$arr[$a]."</option>";			
			}//end for
	?>
	
	<option name="genres"> </option>   
</select> 
</p>
</td>
</tr>


<tr>
<td>Find movie based on selected genre</td>
<td><input type="submit" name="submit" value="Find Movie"/></td>
</tr>
<tr>
<td>Film</td>
<td>
<?php
	$ratingdb="SELECT rating_num FROM rating";
	$u=0;
	$ratingNum=array();
	$response1 = @mysqli_query($dbc, $ratingdb);
	echo "<pre>";
	if(isset($_POST['submit'])){
		$selected_val = $_POST['genres'];
		//echo "You have chosen the " . $selected_val . " genre";
		$query1 ="SELECT movie_id, genre FROM movies_genres";
		$moviequery="SELECT id, name, year FROM movies";
		$response1 = @mysqli_query($dbc, $query1);
		$response2 = @mysqli_query($dbc, $moviequery);
		$getMovieID = array();
		$radio="radio";
		$mv="movie";
		if($response1 && $response2){
		$count = 0;
		while($row2 = mysqli_fetch_array($response1)){
			if ($selected_val == $row2['genre']){
				$getMovieID[$count] = $row2['movie_id']; 
				$count++;
			}//end if
		}		
		if ($response1){		
			while($row1=mysqli_fetch_array($response1)){
				$ratingNum[$u]=$row1['rating_num'];
				$u++;
			}else {
				echo "Couldn't issue databse";
			}
		}
		
		while($row3 = mysqli_fetch_array($response2)){	
			for ($b=0;$b<$count;$b++){
				if ($getMovieID[$b] == $row3['id']){	
					echo "<input type=\"radio\" name=\"movie\" value=".$row3['name'].">".$row3['name'] . ", " . "<b>". $row3['year']. "</b>\n";
					
					echo "The rating of this movie is: " . $rating_num[$b] ."\n";
				}//end if
			}//end for	
		}// end while	 
	} else {
			echo "Couldn't issue database query<br />";
			echo mysqli_error($dbc);
			}
	}//end if submit
	
			
		
?>		
</td>
</tr>
<!--Copyright
<div class="blockStyle">
<div id="copyright">
	<pre>

 Copyright &#0169; 2015 Emily
 All Rights Reserved
	</pre>
</div>
</div>
-->
</body>



</html>