<?php
	header("Content-Type: application/json; charset=UTF-8");
	require "connection.php";

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
		$query = "SELECT * FROM tbluser WHERE FIND_IN_SET(0, permission)";
		$data = mysqli_query($connect, $query);
		if ($data) {
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrUser, new User($row['idUser'], $row['userName'], $row['password'], $row['name'], $row['permission'], $row['isOnline']));
			}
		}
		echo json_encode($arrUser);
?>