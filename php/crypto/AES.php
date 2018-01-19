<?php
$input = "Very secret text";
echo "Beginning; text to encrypt: " . $input, PHP_EOL;

// Initialize random and generate key
$key = bin2hex(openssl_random_pseudo_bytes(16));
echo "Secret key: " . $key, PHP_EOL;

// Encrypt
echo "Encrypting...", PHP_EOL;
$cipher = "aes-128-gcm";
$ivlen = openssl_cipher_iv_length($cipher);
$iv = openssl_random_pseudo_bytes($ivlen);
$ciphertext = openssl_encrypt($input, $cipher, $key, $options=0, $iv, $tag);
echo "Initialization vector: " . bin2hex($iv), PHP_EOL;
echo "Tag: " . bin2hex($tag), PHP_EOL;
echo "Encrypted message: " . bin2hex($ciphertext), PHP_EOL;

// Send tag length and initialization vector along with the encrypted message
// Key must be shared securely

// Decrypt
echo "Decrypting...", PHP_EOL;
$plaintext = openssl_decrypt($ciphertext, $cipher, $key, $options=0, $iv, $tag);
echo "Decrypted message: " . $plaintext, PHP_EOL;
?>