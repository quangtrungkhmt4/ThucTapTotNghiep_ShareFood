<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";
	$u = $_POST['username'];
	$p = $_POST['password'];

	class User{
		function User($id, $user, $pass, $name, $permission, $isOnline){
			$this -> idUser = $id;
			$this -> userName = $user;
			$this -> password = $pass;
			$this -> name = $name;
			$this -> permission = $permission;
			$this -> isOnline = $isOnline;
		}
	}
	$arrUser = array();
	if (strlen($u) > 0 && strlen($p) > 0 ) {
		$query = "SELECT * FROM tbluser WHERE FIND_IN_SET('$u', userName) AND FIND_IN_SET('$p', password)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrUser, new User($row['idUser'], $row['userName'], $row['password'], $row['name'], $row['permission'], $row['isOnline']));
			}
			// if (count($arrUser) > 0) {
			// 	echo json_encode($post_data);
			// }else{
			// 	echo json_encode($output);
			// }
			// echo json_encode($arrUser);
			echo json_encode($arrUser);
		}else{
			echo json_encode($arrUser);
		}
	}else{
		echo json_encode($arrUser);
	}
?>