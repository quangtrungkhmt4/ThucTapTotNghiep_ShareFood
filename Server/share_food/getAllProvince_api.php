<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

	class Province{
		function Province($id, $name, $info, $latLong){
			$this -> idProvince = $id;
			$this -> name = $name;
			$this -> info = $info;
			$this -> latLong = $latLong;
		}
	}

		$arrProvince = array();
		$query = "SELECT * FROM tblprovince";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrProvince, new Province($row['idProvince'], $row['name'], $row['info'], $row['latLong']));
			}
		}
		echo json_encode($arrProvince);
?>