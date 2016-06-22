<?php 

$db_name ="webservice";

$mysql_username="root";
$mysql_password="";

$server_name="localhost";




class DB_Connection{

	var $connect;
	
	function _construct(){
		
		$connect=mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);


	}

	function get_connection(){
		return $this->connect;
	}

}

?>