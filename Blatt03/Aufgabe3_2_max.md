Rechenoperation::= Bruch " " Operator " " Bruch | Rechenoperation | "( " Rechenoperation " )"
Bruch::= ["-"] Ziffer{Ziffer} "/" ZifferOhne0{ZifferOhne0}
ZifferOhne0::= "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
Ziffer::= ZifferOhne0 | "0"
Operator::= "+" | "-" | "*" | "/"