# Sort Names

Sort Names is a Spring Boot application for sorting a list of names and surnames.

## Prerequisites
Java 11+ installed and setup in your path

## Installation
Clone the current repository or download the jar sort-names-1.0.0.jar from target folder.

## Usage
Go to the folder the sole sort-names-1.0.0.jar is and execute the command below.
```Java
java -jar sort-names-1.0.0.jar c:\names.txt
```
## Rules and Validation
There should be a valid path to a valid file of type 'plain/text'. 
If the file name and the path are not correct then a error is going to be displayed.
Same happens of the file type is not a text. If you try to pass a zip file, for example.

All the names are going to converted to Upper Case.

Ignored Lines in the text files.
   - Blank lines
   - Names not following the format SURNAME, NAME or SURNAME,NAME 

## Results
The algorithm will create a new file with the sorted names.
At end of the processing the new file name will be displayed.

## Contribution
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
