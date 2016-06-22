        <?php

require_once "conn.php";


$user_name = filter_input(INPUT_GET,'name_l');
$user_phone = filter_input(INPUT_GET,'phone_num_l');
$user_pass = filter_input(INPUT_GET,'password_l');
$user_age=filter_input(INPUT_GET, 'age_l');
$user_gender = filter_input(INPUT_GET,'gender_l');
$user_rating = filter_input(INPUT_GET, 'rating_l');
$user_village = filter_input(INPUT_GET,'village_l');

$inter_cultivation=filter_input(INPUT_GET, 'inter_cultivation');
$ploughing=filter_input(INPUT_GET, 'ploughing');
$pesticides=filter_input(INPUT_GET, 'pesticides');
$crop_seeding=filter_input(INPUT_GET, 'crop_seeding');
$plumbing=filter_input(INPUT_GET, 'plumbing');
$digging=filter_input(INPUT_GET, 'digging');
$flooring_ceiling=filter_input(INPUT_GET, 'flooring_ceiling');
$brick_work=filter_input(INPUT_GET, 'brick_work');
$other=filter_input(INPUT_GET, 'other');



$encrypted_password = md5($user_pass);


$sql = "INSERT INTO `laborer_details` (`user_name_l`, `phone_num_l`, `password_l`, `gender_l`, `age_l`, `rating_l`, `village_l`) VALUES ('$user_name','$user_phone','$encrypted_password','$user_gender','$user_age','$user_rating','$user_village')";
$result =mysqli_query($conn,$sql);

$sql1 = "INSERT INTO `skills_table` (`Phone_no`, `inter_cultivation`, `ploughing`, `pesticides`, `crop_seeding`, `plumbing`, `digging`, `flooring_ceiling`, `brick_work`, `other`) VALUES  ('$user_phone','$inter_cultivation','$ploughing','$pesticides','$crop_seeding','$plumbing','$digging', '$flooring_ceiling', '$brick_work', '$other')";
$result2 = mysqli_query($conn,$sql1);

if ($result) {
echo "Success";
}
else {
echo "Registration not successfull";
}

if (!$result2) {
echo "   "+$brick_work,"   "+ $digging ,"   "+ $plumbing, "   "+$flooring_ceiling,"   "+ $other, "   "+$crop_seeding ,"   "+ $pesticides ,"   "+$ploughing ,"   "+$inter_cultivation;
}
