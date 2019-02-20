<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$p = $_POST['province'];

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

	if (strlen($p) > 0) {
		$arrFood = array();
		$idProvinceQuery = "SELECT idProvince FROM tblprovince WHERE FIND_IN_SET('$p', name)";
		$dataProvince = mysqli_query($connect, $idProvinceQuery);
		if ($dataProvince) {
			$row = mysqli_fetch_assoc($dataProvince);
			$idProvince = $row['idProvince'];

			$foodQuery = "SELECT tblfood.idFood, tblfood.name, tblfood.image, tblfood.recipe, tblfood.idFoodCategory, tblfood.description FROM tblmenu INNER JOIN tblfood ON tblmenu.idFood = tblfood.idFood WHERE FIND_IN_SET('$idProvince', tblmenu.idProvince)";
			$dataFood = mysqli_query($connect, $foodQuery);
			if ($dataFood) {
				while ($row = mysqli_fetch_assoc($dataFood)) {
					array_push($arrFood, new Food($row['idFood'], $row['name'], $row['image'], $row['recipe'], $row['idFoodCategory'], $row['description']));
				}
				echo json_encode($arrFood);
			}

		}else{
			echo json_encode($arrFood);
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