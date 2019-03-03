<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

	class Category{
		function Category($id, $name, $image){
			$this -> id = $id;
			$this -> name = $name;
			$this -> image = $image;
		}
	}

		$arrCate = array();
		$query = "SELECT * FROM category";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrCate, new Category($row['id'], $row['name'], $row['image']));
			}
			echo json_encode($arrCate);
		}
?>