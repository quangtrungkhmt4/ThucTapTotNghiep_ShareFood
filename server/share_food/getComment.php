<?php
	header("Content-Type: application/json; charset=UTF-8");
    require "connection.php";
    $i = $_POST['food'];

	class Comment{
		function Comment($id, $name, $text){
			$this -> id = $id;
			$this -> name = $name;
			$this -> text = $text;
		}
    }
    
    if (strlen($i) > 0) {
		$arrComment = array();
		$query = "SELECT comment.id, customer.name, comment.text FROM comment INNER JOIN customer ON comment.customer = customer.id INNER JOIN food ON comment.food = food.id WHERE FIND_IN_SET('$i', food.id)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrComment, new Comment($row['id'], $row['name'], $row['text']));
			}
			echo (string)json_encode($arrComment);
		}else{
            echo (string)json_encode($arrComment);
        }
    }else{
        echo "null";
    }
?>