<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
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
	if (strlen($n) > 0 && strlen($i) > 0) {
		$arrProvince = array();
		$query = "SELECT * FROM tblprovince WHERE FIND_IN_SET('$n', name)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrProvince, new Province($row['idProvince'], $row['name'], $row['info'], $row['latLong']));
			}
			if (count($arrProvince) > 0) {
				$ouput -> result = "exists";
				echo json_encode($ouput);
			}else{
				$insert = "INSERT INTO tblprovince VALUES(null, '$n', '$i', '$l')";
				$dataInsert = mysqli_query($connect, $insert);
				if ($dataInsert) {
					$ouput -> result = "true";
					echo json_encode($ouput);
				}else{
					$ouput -> result = "false";
					echo json_encode($ouput);
				}
			}
		}
	}else{
		$ouput -> result = "false";
		echo json_encode($ouput);
	}
?>