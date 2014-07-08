Amortization-Schedule
=====================

Calculates the Amortization Schedule for loan payment

This project Calculats monthly payment given the loan amount , rate of interest and duration of loan. It can render results results based on Console.

It used Factory Pattern for creating the type of: 
Mortgage :  Calculate the payments and the break-up of principle and interest.
Renderer : To display results

Formula used to calculate the Fixed mortgage:

P = L[c(1 + c)^n]/[(1 + c)^n - 1]
       P = Fixed monthly payment
            n = months
            c = monthly interest rate of (for 6% it is .06/12)
            L = Original loan amount


Reference for calculation :           
 http://homeguides.sfgate.com/calculate-principal-interest-mortgage-2409.html
