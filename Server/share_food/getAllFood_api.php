<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

	class Food{
		function Food($id, $name, $image, $recipe, $idCate, $desc){
			$this -> idFood = $id;
			$this -> name = $name;
			$this -> image = $image;
			$this -> recipe = $recipe;
			$this -> idFoodCategory = $idCate;
			$this -> description = $desc;
		}
	}

		$arrFood = array();
		$query = "SELECT * FROM tblfood";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrFood, new Food($row['idFood'], $row['name'], $row['image'], $row['recipe'], $row['idFoodCategory'], $row['description']));
			}
			echo json_encode($arrFood);
		}
?>