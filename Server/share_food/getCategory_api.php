<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

	class Category{
		function Category($id, $name){
			$this -> idCategory = $id;
			$this -> name = $name;
		}
	}

		$arrCate = array();
		$query = "SELECT * FROM tblfoodcategory";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrCate, new Category($row['idCategory'], $row['name']));
			}
			echo json_encode($arrCate);
		}
?>