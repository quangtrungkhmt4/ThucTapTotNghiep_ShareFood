<?php
	header("Content-Type: application/json; charset=UTF-8");
    require "connection.php";
    $customer = $_POST['idCustomer'];
    $food = $_POST['idFood'];
    $text = $_POST['text'];

	class Comment{
		function Comment($id, $name, $text){
			$this -> id = $id;
			$this -> name = $name;
			$this -> text = $text;
		}
    }
    
    if (strlen($customer) > 0) {
		$arrComment = array();
		$query = "INSERT INTO `comment`(`customer`, `food`, `text`) VALUES ($customer, $food, '$text')";
		$data = mysqli_query($connect, $query);
		if ($data) {
			echo "true";
		}else{
            echo "false";
        }
    }else{
        echo "false";
    }
?>