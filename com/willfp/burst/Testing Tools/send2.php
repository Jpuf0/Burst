<?php
   echo getcwd(). "<br>";
   chdir("sessions");
   echo getcwd(). "<br>";
   $message = $_POST["message"];
   $session = $_POST["session"];
   if (!is_dir($session)) {
     $mkdir($session, 0777);
     $chdir($session);
     $sessionfilename = "conversation.txt";
     $sessionfile = fopen($sessionfilename, "a");
     fwrite($sessionfile, $message);
     fclose($sessionfile);
   } else {
     $chdir($session);
     $sessionfilename = "conversation.txt";
     $sessionfile = fopen($sessionfilename, "a");
     fwrite($sessionfile, $message);
     fclose($sessionfile);
   }
?>