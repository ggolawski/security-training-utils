<?php
$text = "Very secret information";
echo "Beginning; message to sign: " . $text, PHP_EOL;

// Generate public and private keys
echo "Generating key pair...", PHP_EOL;
$config = array(
    "curve_name" => 'prime256v1',
    "private_key_type" => OPENSSL_KEYTYPE_EC,
);
$res = openssl_pkey_new($config);
$public_key = openssl_pkey_get_details($res)["key"];
openssl_pkey_export($res, $private_key);
echo "Public key: " . $public_key, PHP_EOL;
echo "Private key: " . $private_key, PHP_EOL;

// Sign the message
echo "Signing...", PHP_EOL;
openssl_sign($text, $signature, $private_key, OPENSSL_ALGO_SHA256);
echo "Signature: " . bin2hex($signature), PHP_EOL;

// Alter the signature
// $signature[10] = 33;

// Verify the signature
echo "Verifying...", PHP_EOL;
$valid = openssl_verify($text, $signature, $public_key, OPENSSL_ALGO_SHA256);
echo "Signature valid? " . $valid, PHP_EOL;
?>