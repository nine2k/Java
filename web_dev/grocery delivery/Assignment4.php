<!DOCTYPE html>

<!--
Author: Siyi Emily Bao
Student Number: 10103388
CISC 282 Assignment #4
-->


<html>


<head>
<!--CSS-->
<link href="a4.css" type="text/css" rel="stylesheet" />
<link href='https://fonts.googleapis.com/css?family=Indie+Flower' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
<link rel="shortcut icon" href="http://33.media.tumblr.com/1d93f6abe022a7a7156cb806e8512839/tumblr_inline_mv98dzAPjf1qid2nw.gif">
	<title> Emily's Grocery Delivery </title>
</head>

<body>
<div id ="homepage">

	<div id="title">
		<h1> Emily's Grocery Delivery <img src="http://33.media.tumblr.com/0d6b2984ef17e3590b3f48f97313f5e3/tumblr_inline_mwmgvqIDjV1qid2nw.gif"/></a></h1>
	</div>

<div id="checkout">
	<div class = "HeadingFont">
		<div id="underline">
			<h1>Checkout</h1>
		</div>
	</div>




<?php
	
	echo"<html>";
	
	$usersName=$_POST['username'];
	$streetAddress=$_POST['streetaddress'];
	
	echo '<div class="blockStyle">'; 
	echo "Name: " . $usersName;
	echo '</div>'; 
	
	echo '<div class="blockStyle">'; 
	echo "Address: " . $streetAddress;
	echo '</div>'; 
	
?>
<div class = "HeadingFont">
<div id="underline">
	<h1>Billing Information</h1>
</div>
</div>




<?php

$price = array(0.59,3.99,1.99,3.99,1.99,38,1.99,5,1.99,1.99,5,3,20,3,2,2,1,1,6,25);
$type =array("notax","notax","tax","notax","notax","tax","tax","notax","notax","tax","notax","tax","tax","notax","notax","tax","tax","notax","tax","tax");
	
	$fixedTotal=0;
	$total=0;
	$i=0;
	foreach($_POST as $key => $val) {
		
		$i++;
		//skips first 2 $_POST values (name, address)
		if ($i>2){
			if($val>1){ 
				$subtotal=0;
				$subtotal= $val*$price[$i-3];
				echo '<div class="blockStyle">'; 
				print  $val . " ". $key."(s) at $". $price[$i-3] . " per unit, total:" . "	$". $subtotal;
				if ($type[$i-3]=="notax"){
					$fixedTotal=$fixedTotal+$subtotal; //calculates the fixed amount of cost that does not require tax
				}else {
					$total=$total+$subtotal;//accumulates the cost that requires tax
				}
				echo '</div>'; 
				
			} else if ($val==1){ //does not add "s" after the item because customer is only purchase
				$subtotal=0;
				$subtotal= $val*$price[$i-3];
				echo '<div class="blockStyle">'; 
				print  $val . " ". $key." at $". $price[$i-3] . " per unit, total:" . "	$". $subtotal;
				
				if ($type[$i-3]=="tax"){
					$fixedTotal=$fixedTotal+$subtotal; //calculates the fixed amount of cost that does not require tax
				}else {
					$total=$total+$subtotal;//accumulates the cost that requires tax
				}
				
				echo '</div>'; 
			
			}else if ($val==null){
				continue;
			}
		}//end if
	} //end for each
	$sum=$total+$fixedTotal;
	$finalTotal=$fixedTotal+($total*1.13);
	
	echo '<div class="HeadingFont">'; 
		echo '<div id="notice">'; 
		echo "Tax is charged on everything except for fruits and vegetables.";
		echo '</div>';
	echo "________________________________________";
	echo '</div>';
	
	echo '<div class="blockStyle">'; 
	echo "Total before taxes: $" . $sum;
	echo '</div>';
	
	echo '<div class="blockStyle">'; 
	echo "Total after taxes: $" . $finalTotal;
	echo '</div>';
?>


</div>

<!--Copyright-->
<div id="copyright">
	<pre>

 Copyright &#0169; 2015 CISC 282
 All Rights Reserved
	</pre>
</div>
</div>
</body>



</html>