An "Any Terms (OR)" query of "a test" should return test2.txt, test3.txt, and test4.txt.
The same query as "All Terms (AND)" should return only test3.txt and test4.txt.
And for "Exact Phrase (PHRASE)" it should return test4.txt only.

Contents:
test1.txt - "Zed"
test2.txt - "12345 a"
test3.txt - "this is test a"
test4.txt - "this is not a test"