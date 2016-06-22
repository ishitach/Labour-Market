<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once "conn.php";

$phone = filter_input(INPUT_GET, 'phone');
$token = filter_input(INPUT_GET, 'token');

$sql1 = "Select * From `gcm_registration` where id = '$phone'";
$result1 = mysqli_query($conn, $sql1);

if(mysqli_num_rows($result1)==0){
$sql = "INSERT INTO `gcm_registration` (`id`, `token`) VALUES ('$phone','$token')";
$result = mysqli_query($conn, $sql);    
}



