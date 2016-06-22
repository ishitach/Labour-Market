<?php

require_once 'conn.php';
// API access key from Google API's Console
define( 'API_ACCESS_KEY', 'AIzaSyBzPHADir8rW645GH9LkXVd8QH9TEjq4jY' );

$Phone_numbers = $_GET['phone_num'];
$startDate=  filter_input(INPUT_GET, 'startDate');

$endDate= filter_input(INPUT_GET, 'endDate');
$sender=  filter_input(INPUT_GET,'sender');

print_r($Phone_numbers);

$temp = join(',',$Phone_numbers);

echo $temp;
$sql = "SELECT token FROM `gcm_registration` WHERE id in ($temp) ";
$result1 =mysqli_query($conn,$sql);

$registartionIds= array();

while ($r = mysqli_fetch_assoc($result1)) {
 $registrationIds[]=$r;

}

print_r($registrationIds);
//print_r($registrationIds[0]['token']);


$arrlength = count($registrationIds);

echo $arrlength;

$sql1 = "SELECT id FROM `gcm_registration` WHERE id in ($temp) ";
$myResult = mysqli_query($conn, $sql1);

$registaredPhones= array();

while ($r = mysqli_fetch_assoc($myResult)) {
 $registredPhones[]=$r;
}






$temp0=0;


for($x = 0; $x < $arrlength; $x++) {
    $temp5 = array(($registredPhones[$x]));
    $mytemp6 = $temp5[0];
    $myVariable=$mytemp6['id'];
     
    $sql1 = "INSERT INTO `request_table` (`request_start_date`, `request_end_date`, `requester`, `acceptor`, `request_response`) VALUES ('$startDate' , '$endDate' , '$sender' , '$myVariable','$temp0')" ; 
    $result2 = mysqli_query($conn, $sql1);
    
}