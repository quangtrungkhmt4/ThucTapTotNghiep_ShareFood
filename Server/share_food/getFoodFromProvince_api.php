<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$p = $_POST['province'];

	class Food{
		function Food($id, $name, $image, $recipe, $idCate, $desc){
			$this -> id = $id;
			$this -> name = $name;
			$this -> image = $image;
			$this -> recipe = $recipe;
			$this -> category = $idCate;
			$this -> desc = $desc;
		}
	}

	if (strlen($p) > 0) {
		$arrFood = array();
		$idProvinceQuery = "SELECT id FROM province WHERE FIND_IN_SET('$p', name)";
		$dataProvince = mysqli_query($connect, $idProvinceQuery);
		if ($dataProvince) {
			$row = mysqli_fetch_assoc($dataProvince);
			$idProvince = $row['id'];

			$foodQuery = "SELECT DISTINCT food.id, food.name, food.image, food.recipe, food.category, food.desc FROM menu INNER JOIN food ON menu.food = food.id INNER JOIN restaurant ON restaurant.id = menu.restaurant WHERE FIND_IN_SET('$idProvince', restaurant.province)";
			$dataFood = mysqli_query($connect, $foodQuery);
			if ($dataFood) {
				while ($row = mysqli_fetch_assoc($dataFood)) {
					array_push($arrFood, new Food($row['id'], $row['name'], $row['image'], $row['recipe'], $row['category'], $row['desc']));
				}
				echo (string)json_encode($arrFood);
				// echo count($arrFood);
			}

		}else{
			echo (string)json_encode($arrFood);
			// echo count($arrFood);
		}

		// if ($data) {
		// 	while ($row = mysqli_fetch_assoc($data)) {
		// 		array_push($arrUser, new User($row['idUser'], $row['userName'], $row['password'], $row['name'], $row['permission'], $row['isOnline']));
		// 	}
		// 	// if (count($arrUser) > 0) {
		// 	// 	echo json_encode($post_data);
		// 	// }else{
		// 	// 	echo json_encode($output);
		// 	// }
		// 	echo json_encode($arrUser);
		// }
	}else{
		echo "null";
	}
?>