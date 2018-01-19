<?php
$text = "Very secret information";
echo "Beginning; text to encrypt: " . $text, PHP_EOL;

// Generate public and private keys
echo "Generating key pair...", PHP_EOL;
$config = array(
    "private_key_bits" => 2048,
    "private_key_type" => OPENSSL_KEYTYPE_RSA,
);
$res = openssl_pkey_new($config);
$public_key = openssl_pkey_get_details($res)["key"];
openssl_pkey_export($res, $private_key);
echo "Public key: " . $public_key, PHP_EOL;
echo "Private key: " . $private_key, PHP_EOL;

// Encrypt the message
echo "Encrypting...", PHP_EOL;
openssl_public_encrypt($text, $cipher_text, $public_key);
echo "Encrypted message: " . bin2hex($cipher_text), PHP_EOL;

// Decrypt the message
echo "Decrypting...", PHP_EOL;
openssl_private_decrypt($cipher_text, $plain_text, $private_key);
echo "Decrypted message: " . $plain_text, PHP_EOL;
?>