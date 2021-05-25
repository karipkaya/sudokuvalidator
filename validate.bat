call echo " ********************************Running project***********************"
call mvn clean package
call echo "**********************************************"
call echo "**********************************************"
call echo "**** running JAR FILE against sudoku.txt *****"
call echo "*****************************************************************"
call echo "------1) RUNNING java -jar target\sudokuvalidator-1.0-SNAPSHOT.jar sudoku.txt"
call java -jar target\sudokuvalidator-1.0-SNAPSHOT.jar sudoku.txt
call echo "------2) RUNNING java -jar target\sudokuvalidator-1.0-SNAPSHOT.jar sudoku2.txt"
call java -jar target\sudokuvalidator-1.0-SNAPSHOT.jar sudoku2.txt
call echo "------3) RUNNING java -jar target\sudokuvalidator-1.0-SNAPSHOT.jar sudoku3.txt"
call java -jar target\sudokuvalidator-1.0-SNAPSHOT.jar sudoku3.txt
call echo "------4) RUNNING surefire report-------------------"
call mvn surefire-report:report


