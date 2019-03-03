<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$u = $_POST['username'];
	$p = $_POST['password'];
	$n = $_POST['name'];

	class User{
		function User($id, $user, $pass, $name, $permission, $isOnline){
			$this -> id = $id;
			$this -> userName = $user;
			$this -> password = $pass;
			$this -> name = $name;
			$this -> permission = $permission;
			$this -> isOnline = $isOnline;
		}
	}
	if (strlen($u) > 0 && strlen($p) > 0 && strlen($n) > 0) {
		$arrUser = array();
		$query = "SELECT * FROM customer WHERE FIND_IN_SET('$u', userName)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrUser, new User($row['id'], $row['userName'], $row['password'], $row['name'], $row['permission'], $row['isOnline']));
			}
			if (count($arrUser) > 0) {
				echo "exists";
			}else{
				$insert = "INSERT INTO customer VALUES(null, '$u', '$p', '$n', 0, 0)";
				$dataInsert = mysqli_query($connect, $insert);
				if ($dataInsert) {
					echo "true";
				}else{
					echo "false";
				}
			}
		}
	}else{
		echo "false";
	}
?>