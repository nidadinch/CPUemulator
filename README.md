#  - CPUemulator -

## About The Project

### CSE - Computer Organization | CPU emulator project

A CPU emulator software that supports a basic instruction set given below that written in Java.
#### For printingMatrixIndices.java & printingMatrixIndices.txt : to print n×m matrix indices in row major order. I used reflection to prevent code clutter.

[![instruction set](https://i.imgur.com/29qGVur.png)](https://i.imgur.com/29qGVur.png)


#### For matrixProduct.java & matrixProduct.txt : to multiply two matrices of size m×n and n×p to produce and print mxp result matrix.And added new instrucion set given below. And used stack frame for keep the backlink address. I made random matrix generator with user input of number of rows and columns. Also you can see the program with manuel matrices by STORE and LOAD commands in matrixProductWithManualMatrices.txt file. 


[![instructions](https://i.imgur.com/M5vaj7x.png)](https://i.imgur.com/M5vaj7x.png)


## Built With 

* Java

## Getting Started 

Your Java package must be installed to compile the project. After installing Java or already installed;

1. Matrix indices:
   ```sh
   javac printingMatrixIndices.java 

   ```
2. ```sh
   java printingMatrixIndices printingMatrixIndices.txt

   ```


1. Matrix multiplication to multiply two matrices of size m×n and n×p to produce and print mxp result matrix:
   ```sh
   javac matrixProduct.java 

   ```
2. ```sh
   java matrixProduct matrixProduct.txt

   ```
   
   
1. Matrix multiplication program with manual matrices by STORE and LOAD commands: 
   ```sh
   javac matrixProduct.java 

   ```
2. ```sh
   java matrixProduct matrixProductWithManualMatrices.txt

   ```
   
   
   
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



## License

Distributed under the MIT License. See `LICENSE` for more information.


## Contact

Nida Dinç - niddinc@gmail.com

Project Link: [https://github.com/nidadinch/CPUemulator](https://github.com/nidadinch/CPUemulator)

  
