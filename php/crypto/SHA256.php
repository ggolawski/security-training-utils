<?php
$text = bin2hex(openssl_random_pseudo_bytes(rand(10, 100)));;
echo "Beginning; text to hash: " . $text, PHP_EOL;

echo "Hashing...", PHP_EOL;
$hash = hash("sha256", $text);
echo "Hash: " . $hash, PHP_EOL;
?>