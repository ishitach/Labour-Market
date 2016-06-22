<?php

require_once 'connection.php';
header('content_type:application/json');

class User{

	private $connection;
	private $db;
	function _construct(){
		$this->db =new DB_Connection();
		$this->connection = $this->db->get_connection();


	}

	public function does_user_exists($phone_num,$password)
	{
		$query1 ="SELECT * FROM contractor_details where phone_num_c ='$phone_num';";

		$result1 = mysqli_query($this->connection,$query1);

		if(mysqli_num_rows($result1)>0){

			$json['success'] = 'Welcome !!!'
			echo json_encode($json);
 			mysqli_close($this->connection);
 		}else{

 		}
	}
	
}

$user = new User();

if (isset($_POST['phone_num'],$_POST['password'])) {
	$phone_num = 8511455160;
	$password = 12345678912345678912345678912345;

	$encrypted_password = md5($password);
	$user->does_user_exists($phone_num,$encrypted_password);



}

?>