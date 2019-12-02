<?php
   $query=$_POST["query"];
   echo "Query = ".$query."\n";
   $message = $_POST["message"];
   $session = $_POST["session"];
   $sessionfilename = $session . ".txt";
   $sessionfile = fopen($sessionfilename, "a");
   fwrite($sessionfile, $message);
   fclose($sessionfile);
?>