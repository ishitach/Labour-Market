<?php

require_once "conn.php";

$user_phone = filter_input(INPUT_GET,'phone_num');    
$user_pass = filter_input(INPUT_GET,'password');


$user_pass_encrpted=  md5($user_pass);
$sql = "SELECT * FROM `contractor_details` WHERE phone_num_c LIKE '$user_phone' AND password_c LIKE '$user_pass_encrpted'";
$result_con=  mysqli_query($conn, $sql);

$sql_lab = "SELECT * FROM `laborer_details` WHERE phone_num_l LIKE '$user_phone' AND password_l LIKE '$user_pass_encrpted'";
$result_lab=  mysqli_query($conn, $sql_lab);



 if(mysqli_num_rows($result_con)==1){
    
    echo 'Contractor'; 
    
}elseif(mysqli_num_rows($result_lab)==1){
    echo 'Laborer';
    
}  else {
    echo 'Login Failed ! Please check your Password';
}
    
