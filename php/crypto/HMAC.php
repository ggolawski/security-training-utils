<?php
$input = "Very secret text";
echo "Beginning; text to HMAC: " . $input, PHP_EOL;

// Generate random secret key
echo "Generating secret key...", PHP_EOL;
$key = openssl_random_pseudo_bytes(16);
echo "Secret key: " . bin2hex($key), PHP_EOL;

// Generate HMAC
echo "Generating HMAC...", PHP_EOL;
$mac = hash_hmac("sha256", $input, $key);

echo "HMAC: " . $mac, PHP_EOL;
?>