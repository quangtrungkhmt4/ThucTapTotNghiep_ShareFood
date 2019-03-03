<?php
	header("Content-Type: application/json; charset=UTF-8");
    require "connection.php";
    $i = $_POST['food'];

	class Restaurant{
		function Restaurant($id, $name, $image, $latlong, $province){
			$this -> id = $id;
			$this -> name = $name;
			$this -> image = $image;
			$this -> latLong = $latlong;
			$this -> province = $province;
		}
    }
    
    if (strlen($i) > 0) {
		$arrRestaurant = array();
		$query = "Select DISTINCT restaurant.id,restaurant.name,restaurant.image,restaurant.latLong,restaurant.province FROM menu INNER JOIN restaurant ON menu.restaurant = restaurant.id INNER JOIN food ON menu.food = food.id WHERE FIND_IN_SET('$i', food.id)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrRestaurant, new Restaurant($row['id'], $row['name'], $row['image'], $row['latLong'], $row['province']));
			}
			echo (string)json_encode($arrRestaurant);
		}else{
            echo (string)json_encode($arrRestaurant);
        }
    }else{
        echo "null";
    }
?>