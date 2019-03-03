<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

	class Food{
		function Food($id, $name, $image, $recipe, $category, $desc){
			$this -> id = $id;
			$this -> name = $name;
			$this -> image = $image;
			$this -> recipe = $recipe;
			$this -> category = $category;
			$this -> desc = $desc;
		}
	}

		$arrFood = array();
		$query = "SELECT * FROM food";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrFood, new Food($row['id'], $row['name'], $row['image'], $row['recipe'], $row['category'], $row['desc']));
			}
			echo json_encode($arrFood);
		}
?>