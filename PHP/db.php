<?php
include './config.php';

class DB {
  private static $instance;
  private $pdo;

  public static function getInstance(): DB{
    if (null === static::$instance) {
      static::$instance = new static();
    }
    return static::$instance;
  }

  public function __construct() {
    $this->pdo = new PDO("mysql:host=localhost;dbname=androidSkype;charset=utf8", DBUser, DBPassword);
  }

  public function __destruct(){
    $this->pdo = null;
  }


  public function getImg($id) {
	if (ctype_digit($id)) {
	     header('Content-type: image/jpeg');
	     $mr = $this->pdo->prepare("SELECT image FROM uzvers WHERE id=:id");
	     $mr->bindParam(':id', $id);
	     $mr->execute();
	     $rr = $mr->fetch(PDO::FETCH_ASSOC);
	     echo $rr['image'];	
	} else {
	return array(
	     'error' => 'bad argument'
	  );
	}
  }


 public function getAll() {
        $res = $this->pdo->prepare("SELECT id,un FROM uzvers");
        $res->execute();
        return $res->fetchAll(PDO::FETCH_ASSOC);
  }

}

 ?>
