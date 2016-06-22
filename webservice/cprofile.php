<?php

require_once "conn.php";

$user_phone = filter_input(INPUT_GET,'phone_num_c');



$sql = "SELECT 	*  FROM `contractor_details` WHERE phone_num_c like '$user_phone';";
$result =mysqli_query($conn,$sql);


$rows = array();

while ($r = mysqli_fetch_assoc($result)) {
 $rows[]=$r;

}
echo json_encode($rows);

mysqli_close($conn);

