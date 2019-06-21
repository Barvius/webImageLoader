<?php
include 'db.php';
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: GET, POST');


if ( isset($_GET['getImg']) && isset($_GET['id']) ) {
  DB::getInstance()->getImg($_GET['id']);
  return;
}

if (isset($_GET['getAll']) ) {
  echo json_encode(DB::getInstance()->getAll(),JSON_NUMERIC_CHECK);
  return;
}


 ?>
