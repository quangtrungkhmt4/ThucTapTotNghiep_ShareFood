<?php
$json = '[
    {
        "title": "Professional JavaScript",
        "author": "Nicholas C. Zakas"
    },
    {
        "title": "JavaScript: The Definitive Guide",
        "author": "David Flanagan"
    },
    {
        "title": "High Performance JavaScript",
        "author": "Nicholas C. Zakas"
    }
]';

$books = json_encode($json);
// access property of object in array
echo $books;

?>