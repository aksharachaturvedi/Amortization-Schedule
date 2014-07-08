Amortization-Schedule
=====================

Calculates the Amortization Schedule for loan payment

This project calculates monthly payment given the loan amount , rate of interest and duration of loan. It can render results results based on Console.

It uses Factory Pattern for creating the type of: <br/>
Mortgage :  Calculate the payments and the break-up of principle and interest.<br/>
Renderer : To display results<br/>

Formula used to calculate the Fixed mortgage:

P = L[c(1 + c)^n]/[(1 + c)^n - 1]  <br/>
       P = Fixed monthly payment <br/>
       n = months <br/>
       c = monthly interest rate of (for 6% it is .06/12) <br/>
       L = Original loan amount<br/>

Class Diagram:

![Alt text](/public/class-diagram.jpg "Class Diagram")


To compile : <br/>
       javac refactored/*.java<br/>
To run :<br/>
       java refactored/AmortizationSchedule<br/>

Reference for calculation :  <br/>         
 http://homeguides.sfgate.com/calculate-principal-interest-mortgage-2409.html
