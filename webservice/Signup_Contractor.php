<?php

require_once "conn.php";

$user_phone = filter_input(INPUT_GET, 'phone_num_c');
$user_pass = filter_input(INPUT_GET, 'password_c');
$user_name = filter_input(INPUT_GET, 'name_c');
$user_gender = filter_input(INPUT_GET, 'gender_c');
$user_village = filter_input(INPUT_GET, 'village_c');
$user_licence = filter_input(INPUT_GET, 'license_num_c');


$encrypted_password = md5($user_pass);
$sql1 = "SELECT * FROM `contractor_details` WHERE phone_num_c like $user_phone";
$result1 = mysqli_query($conn, $sql1);



if (mysqli_num_rows($result1)) {
    echo 'User Exists';
} else {

    $sql = "INSERT INTO `contractor_details` (`user_name_c`, `phone_num_c`, `village_name_c`, `password_c`, `gender_c`, `license_num_c`) VALUES ('$user_name','$user_phone','$user_village','$encrypted_password','$user_gender','$user_licence')";
    $result = mysqli_query($conn, $sql);

    if ($result) {

        echo "Success";
    } else {

        echo "Failure";
    }
}



