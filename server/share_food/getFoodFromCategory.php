<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$p = $_POST['category'];

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
        $query = "SELECT * FROM food WHERE FIND_IN_SET('$p', category)";
        $data = mysqli_query($connect, $query);
        if($data){
            while ($row = mysqli_fetch_assoc($data)) {
                array_push($arrFood, new Food($row['id'], $row['name'], $row['image'], $row['recipe'], $row['category'], $row['desc']));
            }
            echo (string)json_encode($arrFood);
        }else{
            (string)json_encode($arrFood);
        }
    }else{
        echo "null";
    }
?>