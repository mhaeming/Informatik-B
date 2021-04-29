Rechenoperation::= Bruch | ("( " Bruch " )") | (Bruch " " Operator " " Rechenoperation | ("( " Rechenoperation " )"))

Bruch::= "0" | (["-"] ZifferOhne0 {Ziffer}) "/" ZifferOhne0 {Ziffer}

ZifferOhne0::= "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"

Ziffer::= ZifferOhne0 | "0"

Operator::= "+" | "-" | "*" | "/"