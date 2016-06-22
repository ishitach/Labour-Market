<?php

require_once "conn.php";


$user_skill = filter_input(INPUT_GET,'user_skill');



$sql = "SELECT * FROM `laborer_details` WHERE phone_num_l = ANY (Select `Phone_no` FROM `skills_table` WHERE `inter_cultivation` LIKE 1);";

$result =mysqli_query($conn,$sql);

$rows = array();

while ($r = mysqli_fetch_assoc($result)) {
 $rows[]=$r;

}
echo json_encode($rows);

mysqli_close($conn);