<?php 

$taxRefNumber = '123456789012345';
$hashed = hash("sha512", $taxRefNumber);
$secret_key = substr($hashed,0,32);

$data = [
	'amount' => 1500
];

$enc = openssl_encrypt($data,'aes-256-cbc',$secret_key,0,'0000000000000000');
echo $enc;