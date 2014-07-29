choice-error-handling
=====================

This example demonstrates choice error handling. The input data is validated and in case of missing or invalid data, an appropriate exception is thrown.

Step 1: import and run an app
Step 2: use postman to make a POST request using json:

{
 "email": "aaa@aaa.aa", 
 "item name": "aa", 
  "item units": 10, 
"item price per unit": 1,
 "membership": "free"
}

this case is valid.

{
 "item name": "aa", 
  "item units": 10, 
"item price per unit": 1,
 "membership": "free"
}

is invalid, email is missing. Status code 400 is returned, see the console log for more info.

{
 "email": "aaa@aaa.aa", 
 "item name": "aa", 
  "item units": 10, 
"item price per unit": -1,
 "membership": "free"
}

is invalid, item price per unit is negative, see the console log for more info. this applies to negative **item units** as well.
=======
