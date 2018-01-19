<?php
$PASSWORD = "Pa$\$w0rd";

function hashSHA256($password) {
    return hash("sha256", $password);
}

function hashPBKDF2($password) {
    $salt = openssl_random_pseudo_bytes(16);
    $iterations = 10000;
    return hash_pbkdf2("sha256", $password, $salt, $iterations);
}

echo "Password: " . $PASSWORD, PHP_EOL;

echo "SHA-256 hash: " . hashSHA256($PASSWORD), PHP_EOL;
echo "PBKDF2 hash : " . hashPBKDF2($PASSWORD), PHP_EOL;
?>