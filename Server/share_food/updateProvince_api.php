<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$id = $_POST['id'];
	$n = $_POST['name'];
	$i = $_POST['info'];
	$l = $_POST['latLong'];

	class Province{
		function Province($id, $name, $info, $latLong){
			$this -> idProvince = $id;
			$this -> name = $name;
			$this -> info = $info;
			$this -> latLong = $latLong;
		}
	}
	$arrProvince = array();
	$queryIsExists = "SELECT * FROM tblprovince WHERE FIND_IN_SET('$id', idProvince)";
	$dataExists = mysqli_query($connect, $queryIsExists);
	if ($dataExists) {
		while ($row = mysqli_fetch_assoc($dataExists)) {
			array_push($arrProvince, new Province($row['idProvince'], $row['name'], $row['info'], $row['latLong']));
		}
		if (count($arrProvince) > 0) {
			$query = "UPDATE `tblprovince` SET `name`='$n',`info`='$i',`latLong`='$l' WHERE tblprovince.idProvince = $id";
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
	}else{
		$ouput -> result = "false";
		echo json_encode($ouput);
	}
?>