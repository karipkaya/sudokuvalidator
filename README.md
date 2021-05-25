# sudokuvalidator
sudoku solution file validator


To run the project : 

1- Rename validate.bat_rename to validate.bat

2- in a cmd run the below command
validate.bat

3- It will do these steps sequentially at once : 
    - call mvn clean package
    - run validator against sudoku.txt
    - run validator against sudoku2.txt
    - run validator against sudoku3.txt
    - mvn surefire-report:report
