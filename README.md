# sudokuvalidator
sudoku solution file validator

To run the project : 

1- clone git hub repository to your local 

2- import the project in intellij

3- in Terminal tab, run the below command
validate.bat

4- It will do these steps sequentially at once : 
    - call mvn clean package
    - run validator against sudoku.txt
    - run validator against sudoku2.txt
    - run validator against sudoku3.txt
    - mvn surefire-report:report

Note : You do not need intellij. if you have github and maven installed, 
You can clone the project and run validate.bat in any command prompt window.  