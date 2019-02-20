<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$id = $_POST['id'];

	if (strlen($id) > 0) {
		$arrFood = array();
		$query = "DELETE FROM `tbluser` WHERE FIND_IN_SET('$id', idUser)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			$ouput -> result = "true";
			echo json_encode($ouput);
		}else{
			$ouput -> result = "false";
			echo json_encode($ouput);
		}
	}
	else{
		$ouput -> result = "false";
		echo json_encode($ouput);
	}
?>