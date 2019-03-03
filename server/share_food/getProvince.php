<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

	class Province{
		function Province($id, $name, $info, $latLong){
			$this -> id = $id;
			$this -> name = $name;
			$this -> info = $info;
			$this -> latLong = $latLong;
		}
	}

		$arrProvince = array();
		$query = "SELECT * FROM province";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrProvince, new Province($row['id'], $row['name'], $row['info'], $row['latLong']));
			}
			echo json_encode($arrProvince);
		}
?>