<?php
set_time_limit(120);
require_once 'conn.php';
// API access key from Google API's Console
define( 'API_ACCESS_KEY', 'AIzaSyAqGlCfQDdaKePP39A53mkKrww-hH-1CRc' );

$Phone_numbers = $_GET['phone_num'];
$startDate=  filter_input(INPUT_GET, 'pickedStart');

$endDate= filter_input(INPUT_GET, 'pickedEnd');
$sender=  filter_input(INPUT_GET,'sender');

//print_r($Phone_numbers);

$temp = join(',',$Phone_numbers);

echo $temp;
$sql = "SELECT token FROM `gcm_registration` WHERE id in ($temp) ";
$result1 =mysqli_query($conn,$sql);

$registartionIds= array();
while ($r = mysqli_fetch_assoc($result1)) {
    $registrationIds[]=$r;

}

//$registartionIds=  mysqli_fetch_array($result);
//print_r($registartionIds);
//print_r($registrationIds);
//print_r($registrationIds[0]['token']);
$myArray;

$arrlength = count($registrationIds);

for($a=0 ; $a<$arrlength ; $a++){
    $temp10 = array(($registrationIds[$a]));
    $mytemp11 = $temp10[0];
    $myArray[$a]=$mytemp11['token'];
}
print_r($myArray);

//echo $arrlength;

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

    
   
// prep the bundle
$msg = array
(
    
        'NotificationType' => 1,
	'Message' 	=> 'You have got a request for work',
	'Title'		=> 'Request :)',
        'Phone'         => $sender,
        'StartDate'     => $startDate,
        'EndDate'       => $endDate,
        
	'subtitle'	=> 'This is a subtitle. subtitle',
	'tickerText'	=> 'Ticker text here...Ticker text here...Ticker text here',
	'vibrate'	=> 1,
	'sound'		=> 1,
	'largeIcon'	=> 'large_icon',
	'smallIcon'	=> 'small_icon'
);
$fields = array
(
	'registration_ids' 	=> $myArray,
	'data'			=> $msg
);
 
$headers = array
(
	'Authorization: key=' . API_ACCESS_KEY,
	'Content-Type: application/json'
);
 
$ch = curl_init();
curl_setopt( $ch,CURLOPT_URL, 'https://android.googleapis.com/gcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
$result = curl_exec($ch );
curl_close( $ch );
echo $result;

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

